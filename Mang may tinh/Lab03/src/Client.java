import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Client {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			Socket socket = new Socket("localhost", 3000);
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			String message;
			while (true) {
				message = scanner.next();
				pw.print(message);
				pw.flush();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
