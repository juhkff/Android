package portMappingChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class BasicThread implements Runnable {
	private DatagramSocket ds;
	private DatagramPacket dp;
	private String message;
	private byte[] by;

	public BasicThread(DatagramSocket ds, DatagramPacket dp) {
		super();
		this.ds = ds;
		this.dp = dp;
	}

	@Override
	public void run() {
		System.out.print("接受到消息\t");
		// TODO Auto-generated method stub
		message = new String(dp.getData(), 0, dp.getLength());
		if (message.startsWith("Login")) {
			// 客户端上线请求连接打通UDP
			message = message.split(":", 2)[1]; // userName
			System.out.print("LoginMessage(userName): " + message + "\t");
			SocketAddress socketAddress = dp.getSocketAddress();
			Server.map.put(message, socketAddress);
			by = "Success".getBytes();
			dp.setData(by, 0, by.length);
			System.out.print("发送Success返回到客户端地址: " + socketAddress + "\t");
			try {
				ds.send(dp); // dp中还保留着发送地址
				System.out.println("发送返回值Success完毕");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (message.startsWith("RequestChat")) {
			message = message.split(":", 2)[1]; // (another)userName
			System.out.println("ChatMessage(anotherUserName): " + message);
			SocketAddress anotherSocketAddress = Server.map.get(message);
			if (anotherSocketAddress != null) {
				System.out.print("SocketAddress的String形式: " + anotherSocketAddress.toString() + "\t");
				by = ("ChatAddress:" + anotherSocketAddress.toString()).getBytes();
				dp.setData(by, 0, by.length);
				System.out.print("发送对方地址到请求客户端地址: " + dp.getSocketAddress() + "\t");
				try {
					ds.send(dp);
					System.out.print("地址发送完毕\t");
					dp.setSocketAddress(anotherSocketAddress);
					by = ("RequestChat:" + dp.getSocketAddress().toString()).getBytes();
					dp.setData(by, 0, by.length);
					System.out.print("发送打洞请求到被请求客户端地址: " + anotherSocketAddress.toString() + "\t");
					ds.send(dp);
					System.out.println("地址发送完毕");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("选定用户已下线!");
			}
		} else if (message.startsWith("Offline")) {
			message = message.split(":", 2)[1]; // (Offline)userName
			System.out.println("OfflineMessage(userName): " + message);
			Server.map.remove(message);
			by = "Offline".getBytes();
			dp.setData(by, 0, by.length);
			try {
				ds.send(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("客户端的SocketAddress: " + dp.getSocketAddress());
		}
	}

}
