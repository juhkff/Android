package com.example.portmappingchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tools.internet.ReceiveThread;
import tools.internet.Request;

public class ShowFriendActivity extends AppCompatActivity {
    private final String SERVERIP = "123.207.13.112";
    private final int SERVERPORT = 6060;
    private static DatagramSocket datagramSocket;
    private SocketAddress socketAddress;
    private ArrayList<String> list;
    private ArrayAdapter<String> friendsListAdapter;
    private ListView friendsList;
    private ProgressDialog progressDialog;
    private String userName;
    private String anotherUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friend);
        init();
    }

    @Override
    public void onBackPressed() {
        new Thread(new OffLineThread()).start();
        //super.onBackPressed();
    }

    private void init() {
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        initProgressDialog();
        this.socketAddress = new InetSocketAddress(SERVERIP, SERVERPORT);
        try {
            this.datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024 * 4];
        friendsList = findViewById(R.id.listView1);
        friendsListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        friendsList.setAdapter(friendsListAdapter);
        //设置列表单元点击监听
        friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                anotherUserName = list.get(position);
                new Thread(new SendChatRequestThread(anotherUserName)).start();
            }
        });
        progressDialog.show();
        new Thread(new ConnectThread()).start();
        new Thread(new ReceiveThread(userName, internetHandle, datagramSocket, socketAddress)).start();
    }


    private void initProgressDialog() {
        progressDialog = new ProgressDialog(ShowFriendActivity.this);
        progressDialog.setIndeterminate(false);  //循环滚动
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("客户端上线中...");
        progressDialog.setCancelable(false);    //不能取消显示
    }

    /**
     * 获得某一指定联系人的外网映射地址
     */
    private class SendChatRequestThread implements Runnable {
        private String userName;

        public SendChatRequestThread(String userName) {
            this.userName = userName;
        }

        @Override
        public void run() {
            byte[] sendBys = ("Chat:" + userName).getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(sendBys, sendBys.length, socketAddress);
            //datagramPacket.setData(sendBys, 0, sendBys.length);
            try {
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 告知服务器自己已上线
     */
    private class ConnectThread implements Runnable {
        @Override
        public void run() {
            Request request = new Request("home/Connect", handler);
            Map<String, String> param = new HashMap<String, String>();
            param.put("userName", userName);
            try {
                request.post(param);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 与公网服务器建立通信的线程
     */
    private class GetListThread implements Runnable {

        @Override
        public void run() {
            Request request = new Request("home/GetList", handler3);
            Map<String, String> param = new HashMap<String, String>();
            param.put("userName", userName);
            try {
                request.post(param);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class OffLineThread implements Runnable {
        @Override
        public void run() {
            Request request = new Request("home/Exit", handler2);
            Map<String, String> param = new HashMap<String, String>();
            param.put("userName", userName);
            try {
                byte[] sendBys = ("Offline:" + userName).getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(sendBys, sendBys.length, socketAddress);
                //datagramPacket.setData(sendBys, 0, sendBys.length);
                datagramSocket.send(datagramPacket);
                request.post(param);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String resultStr = data.getString("resultStr");
            Log.i("客户端上线结果(Success-成功)", resultStr);
            progressDialog.dismiss();
            if (resultStr != null && resultStr.equals("Success")) {
                Toast.makeText(ShowFriendActivity.this, "客户端上线成功!", Toast.LENGTH_SHORT).show();
                new Thread(new GetListThread()).start();
            } else {
                Toast.makeText(ShowFriendActivity.this, "客户端上线失败...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String resultStr = data.getString("resultStr");
            Log.i("退出程序的返回值:", resultStr);
            if (resultStr != null && resultStr.equals("Success")) {
                ShowFriendActivity.super.onBackPressed();
            } else {
                Toast.makeText(ShowFriendActivity.this, "退出异常...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String resultStr = data.getString("resultStr");
            Log.i("获得联系人列表返回结果:", resultStr);
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            list = new Gson().fromJson(resultStr, new TypeToken<ArrayList<String>>() {
            }.getType());
            for (String each : list) {
                friendsListAdapter.add(each);
            }
        }
    };


    private Handler internetHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String message = data.getString("receiveStr");
            switch (message) {
                case "Success":
                    Toast.makeText(ShowFriendActivity.this, "通信机制连接成功!", Toast.LENGTH_SHORT).show();
                    break;
                case "Failed":
                    Toast.makeText(ShowFriendActivity.this, "通信机制连接失败...", Toast.LENGTH_SHORT).show();
                    break;
                case "Offline":

                    break;
                default:
                    if (message.startsWith("ChatAddress")) {
                        message = message.split(":", 2)[1];
                        Intent intent = new Intent(ShowFriendActivity.this, ChatActivity.class);
                        intent.putExtra("userName", userName);
                        intent.putExtra("anotherUserName", anotherUserName);
                        intent.putExtra("address", message);
                        startActivity(intent);
                    } else if (message.startsWith("Chat")) {
                        Toast.makeText(ShowFriendActivity.this, "收到" + message.split(":")[1] + "的消息", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ShowFriendActivity.this, "通信机制发生未知错误...", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    public static DatagramSocket getDatagramSocket() {
        return datagramSocket;
    }
}
