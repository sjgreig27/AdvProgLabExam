import java.io.*;
import java.net.*;

/**
 *	Class representing the client in the client-server architecture
 * PROTOCOL FOR CLIENT-SERVER INTERACTION:
 * Upon connecting to the server, the client initiates the first message to the server
 * in the form of a string. The String message should in the form of a command followed 
 * by a calculation as necessary. The server will always respond to the primary message 
 * from the client. The two valid commands are "CALCULATE:" and "EXIT:". The command
 * "CALCULATE:" should be followed by a calculation in the form of Reverse Polish Notation.
 * The calculation may only consist of two integers followed by a single operation symbol. The
 * components of the calculation must be separated by a single ','. An example of a valid message
 * would be "CALCULATE: 2,3,+" or "CALCULATE: 2,3,-". The operations supported by the server are '+' for addition and
 * '-' for subtraction. The inclusion of any unsupported operations returns the response "OPERATION IS NOT SUPPORTED".
 * In response to a correctly formatted calculation request, the server will response "ANSWER: x" where x is the
 * integer value of the answer to the calculation.
 * The second command which is valid is the "EXIT:" command. In this instance there is no subsequent calculation added
 * to the end of the string. This command terminates the connection between the server and client. However, it should be
 * noted there is no response from the server in response to this message.
 * The entry of any command which does not begin "CALCULATE:" or "EXIT:" will generate a response from the server
 * which informs the client that the command could not be recognised. In this case, the response from the server
 * will be a String "UNKNOWN COMMAND".
 * In order to maintain the connection between the client and server over multiple commands, 
 * the client must send a response back to the client to denote that the server's response was successfully 
 * received by the client. There is not need for this to be displayed to the user and therefore the format of 
 * the String response from the client can take any form. 
 */
public class Question2Client{
	private static String SERVER = "127.0.0.1";
	private static int PORT = 8765;
	// A private array list containing the commands used to automate the client
	private static String[] commands;
	
	public static void main (String[] args) throws IOException{

		// Set up the predetermined commands
		// Commands demonstrate a successful addition and subtraction. Additionally,
		// there are two commands to demonstrate an unsupported operation and a command
		// which is unsupported. Finally, the 'EXIT' command is included to terminate the
		// client-server connection
		commands = new String[]{"CALCULATE: 2,3,+", "CALCULATE: 1,3,-", "CALCULATE: 5,6,*",
				"ADD: 1,1,+", "EXIT:"};
		
		// The socket requests connection to the server
		Socket server = new Socket(SERVER,PORT);
		// Use PrintWriter and BufferedReader to send and recieve message from the server, respectively
		PrintWriter out = new PrintWriter(server.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		// Set up an index value for the String array
		int index = 0;
		// The while loop ensures the communication between client and server is maintained for
		// as long as the 'user' requires
		while(true){
			// String message = System.console().readLine();
			// Above would be here for user input, however, commands are hard coded
			String message = commands[index];
			// Display the message to the user
			System.out.println(message);
			// Sent the message to the server
			out.println(message);
			// Wait for the response of the server
			String response = in.readLine();
			// If no response is received then the connection to the server has been terminated
			if (in.readLine()==null){
				break;
			}
			// If not null, display the response to the user
			System.out.print(response);
			// Send a response to the server. Ensures that the connection is maintained. However,
			// there is no need to display this to the user.
			out.println("RECIEVED");
			// Increment the index for the next command
			index++;
		}
		// If the no response is received from the server then ensure the PrintWriter, the BufferedReader
		// and the socket are closed
		if (in!=null)
			in.close();
		if (out!=null)
			out.close();
		if (server!=null)
			server.close();
	}
}
