

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer2017 {
	public static void main(String[] args) {

		try {

			DatagramSocket socket = new DatagramSocket(11111);
			byte[] listenBuff1 = new byte[1024];
			byte[] listenBuff2 = new byte[1024];
			byte[] sendBuff = new byte[2048];

			while (true) {
                // wait for data from the client
				DatagramPacket receivePacket1 = new DatagramPacket(listenBuff1, listenBuff1.length);
				socket.receive(receivePacket1);
				String receivedS1 = new String(receivePacket1.getData(), 0, receivePacket1.getLength());

				DatagramPacket receivePacket2 = new DatagramPacket(listenBuff2, listenBuff2.length);
				socket.receive(receivePacket2);
				String receivedS2 = new String(receivePacket2.getData(), 0, receivePacket2.getLength());

				System.out.println("RECEIVED: " + receivedS1 + " " + receivedS2);

				// send data to the client
				Integer senderPort = receivePacket1.getPort();
				InetAddress senderAddress = receivePacket1.getAddress();
                
				String messageToUDP = new String(new StringBuilder(receivedS1 + receivedS2).reverse());
				System.out.println("Message to be sent to the client: " + messageToUDP);
				sendBuff = messageToUDP.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendBuff, sendBuff.length, senderAddress, senderPort);
				socket.send(sendPacket);

			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}
}
