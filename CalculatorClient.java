import java.net.*;
import java.io.*;

public class CalculatorClient{
	private static String SERVER = "127.0.0.1";
	private static Integer PORT = 8765;

	public static void main (String[] args) throws IOException{
		Socket socket = new Socket(SERVER, PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		while(true){
			String message = System.console().readLine();
			out.println(message);
			message = in.readLine();
			if (message==null){
				break;
			}
			System.out.println(message);
			out.println("RECEIVED");
		}
		out.close();
		in.close();
		socket.close();
	}
}