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
		System.out.print("���ܵ���Ϣ\t");
		// TODO Auto-generated method stub
		message = new String(dp.getData(), 0, dp.getLength());
		if (message.startsWith("Login")) {
			// �ͻ��������������Ӵ�ͨUDP
			message = message.split(":", 2)[1]; // userName
			System.out.print("LoginMessage(userName): " + message + "\t");
			SocketAddress socketAddress = dp.getSocketAddress();
			Server.map.put(message, socketAddress);
			by = "Success".getBytes();
			dp.setData(by, 0, by.length);
			System.out.print("����Success���ص��ͻ��˵�ַ: " + socketAddress + "\t");
			try {
				ds.send(dp); // dp�л������ŷ��͵�ַ
				System.out.println("���ͷ���ֵSuccess���");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (message.startsWith("RequestChat")) {
			message = message.split(":", 2)[1]; // (another)userName
			System.out.println("ChatMessage(anotherUserName): " + message);
			SocketAddress anotherSocketAddress = Server.map.get(message);
			if (anotherSocketAddress != null) {
				System.out.print("SocketAddress��String��ʽ: " + anotherSocketAddress.toString() + "\t");
				by = ("ChatAddress:" + anotherSocketAddress.toString()).getBytes();
				dp.setData(by, 0, by.length);
				System.out.print("���ͶԷ���ַ������ͻ��˵�ַ: " + dp.getSocketAddress() + "\t");
				try {
					ds.send(dp);
					System.out.print("��ַ�������\t");
					dp.setSocketAddress(anotherSocketAddress);
					by = ("RequestChat:" + dp.getSocketAddress().toString()).getBytes();
					dp.setData(by, 0, by.length);
					System.out.print("���ʹ����󵽱�����ͻ��˵�ַ: " + anotherSocketAddress.toString() + "\t");
					ds.send(dp);
					System.out.println("��ַ�������");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("ѡ���û�������!");
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
			System.out.println("�ͻ��˵�SocketAddress: " + dp.getSocketAddress());
		}
	}

}
