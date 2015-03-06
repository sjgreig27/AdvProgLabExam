import java.net.*;
import java.io.*;

public class CalculatorClient{
	private static String SERVER = "127.0.0.1";
	private static Integer PORT = 8765;
	private static String[] commands;

	public static void main (String[] args) throws IOException{
		
		//Define client statements
		commands = new String[]{"CALCULATE: 2,3,+", "CALCULATE: 5,1,-", "CALCULATE: 3,2,/", "", "EXIT:"};
		
		Socket socket = new Socket(SERVER, PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		int index =0;
		while(true){
			String message = commands[index];
			System.out.println (message);
			out.println(message);
			message = in.readLine();
			if (message==null){
				break;
			}
			System.out.println(message);
			out.println("RECEIVED");
			index++;
		}
		out.close();
		in.close();
		socket.close();
	}
}