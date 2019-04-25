package portMappingChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class Server {
	public static Map<String, SocketAddress> map;

	public static void main(String[] args) throws IOException {
		Server.map = new HashMap<String, SocketAddress>();
		DatagramSocket ds = new DatagramSocket(6060);
		byte[] by;
		DatagramPacket dp;
		String message;

		while (true) {
			by = new byte[1024 * 4];
			dp = new DatagramPacket(by, 0, by.length);
			ds.receive(dp);
			new Thread(new BasicThread(ds, dp)).start();
		}

	}

}
