package tools.internet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.portmappingchat.ShowFriendActivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * 接收线程
 */
public class ReceiveThread implements Runnable {
    private String userName;
    private Handler handler;
    private static Handler chatHandler;
    private DatagramSocket datagramSocket;
    private SocketAddress socketAddress;

    public ReceiveThread(String userName, Handler handler, DatagramSocket datagramSocket, SocketAddress socketAddress) {
        this.userName = userName;
        this.handler = handler;
        this.datagramSocket = datagramSocket;
        this.socketAddress = socketAddress;
    }

    public static void setChatHandler(Handler chatHandler) {
        ReceiveThread.chatHandler = chatHandler;
    }

    public static void removeChatHandler() {
        ReceiveThread.chatHandler = null;
    }

    public static Handler getChatHandler() {
        return chatHandler;
    }

    @Override
    public void run() {
        try {
            byte[] sendBys = ("Login:" + userName).getBytes();
            byte[] getBys = new byte[1024 * 4];
            DatagramPacket datagramPacket = new DatagramPacket(sendBys, sendBys.length, socketAddress);
            datagramSocket.send(datagramPacket);
            while (true) {
                getBys = new byte[1024 * 4];
                //datagramPacket.setData(getBys);
                datagramPacket = new DatagramPacket(getBys, 0, getBys.length);
                datagramSocket.receive(datagramPacket);

                String receiveStr = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                Log.i("收到信息", receiveStr);
                Message message = new Message();
                Bundle data = new Bundle();
                if (receiveStr.equals("Success")) {
                    data.putString("receiveStr", receiveStr);
                    message.setData(data);
                    handler.sendMessage(message);
                    new Thread(new HeartBeat()).start();
                } else if (receiveStr.startsWith("ChatAddress")) {
                    String addr = receiveStr.split(":", 2)[1];  //拿到另一方的地址
                    Log.i("获得要通讯方的地址", addr);
                    data.putString("receiveStr", receiveStr);
                    message.setData(data);
                    handler.sendMessage(message);
                } else if (receiveStr.startsWith("RequestChat")) {
                    String addr = receiveStr.split(":", 2)[1]; //拿到要打洞的地址
                    Log.i("获得要打洞的地址", addr);
                    data.putString("receiveStr", receiveStr);
                    message.setData(data);
                    handler.sendMessage(message);
                } else if (receiveStr.startsWith("Punch")) {
                    String anotherUserName = receiveStr.split(":", 2)[1];
                    Log.w("被用户" + anotherUserName + "打洞", "full-core时可接收到,非对称时该步可能无法打成但不影响结果");
                } else if (receiveStr.equals("Offline")) {
                    data.putString("receiveStr", receiveStr);
                    message.setData(data);
                    handler.sendMessage(message);
                } else if (receiveStr.startsWith("Chat:")) {
                    if (chatHandler != null) {
                        data.putString("Message", receiveStr.split(":", 3)[2]);
                        message.setData(data);
                        chatHandler.sendMessage(message);
                    } else {
                        data.putString("receiveStr", receiveStr);
                        message.setData(data);
                        handler.sendMessage(message);
                    }
                } else {
                    data.putString("receiveStr", "Failed");
                    message.setData(data);
                    handler.sendMessage(message);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class HeartBeat implements Runnable {
        public HeartBeat() {

        }

        @Override
        public void run() {
            while (true) {
                byte[] sendBys = "Heartbeat".getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(sendBys, sendBys.length, socketAddress);
                try {
                    datagramSocket.send(datagramPacket);
                    Thread.sleep(1000 * 30);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
