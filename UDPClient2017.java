
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class UDPClient2017 {
	public static void main(String[] args) {
       try {
			Integer port = 11111;
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the string: ");
			String string = scan.nextLine();
			String S1, S2;
			// split the string in two parts depending on the initial string
			// length
			if (string.length() % 2 == 1) {
				S1 = string.substring(0, (string.length() + 1) / 2);
				S2 = string.substring((string.length() + 1) / 2);
			} else {
				S1 = string.substring(0, string.length() / 2);
				S2 = string.substring(string.length() / 2);
			} 
			// send data to the server
			DatagramSocket socket = new DatagramSocket();
			byte[] buff1 = new byte[1024];
			buff1 = S1.getBytes();
			byte[] buff2 = new byte[1024];
			buff2 = S2.getBytes();
			DatagramPacket packet1 = new DatagramPacket(buff1, buff1.length, InetAddress.getByName("127.0.0.1"), port);
			DatagramPacket packet2 = new DatagramPacket(buff2, buff2.length, InetAddress.getByName("127.0.0.1"), port);
			socket.send(packet1);
			socket.send(packet2);
            // wait a reply from the server
			byte[] listenBuff = new byte[2048];
			DatagramPacket receivePacket = new DatagramPacket(listenBuff, listenBuff.length);
			socket.receive(receivePacket);
			String receivedS = new String(receivePacket.getData(), receivePacket.getOffset(),
					receivePacket.getLength());
			System.out.println("FROM SERVER: " + receivedS);
			// check if the data received from the server is equal to the reversed initial sent data
			boolean result = new String(new StringBuilder(string).reverse()).equals(receivedS);
			System.out.println(result ? "The data received from the server is equal to the reveresed intial sent data."
					: "" + "The data received from the server is not equal to the reversed initial sent data");
			socket.close();
          } catch (Exception e) {
			e.printStackTrace();
		}
   }
}
