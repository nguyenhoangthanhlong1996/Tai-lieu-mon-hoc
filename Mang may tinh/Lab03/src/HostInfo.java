import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInfo {
	public static void main(String[] args) {
		InetAddress[] addresses;
		try {
			addresses = InetAddress.getAllByName("www.hcmut.edu.vn");
			for (int i = 0; i < addresses.length; i++) {
				System.out.println(addresses[i]);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
