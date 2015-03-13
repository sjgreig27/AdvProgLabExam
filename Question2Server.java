import java.io.*;
import java.net.*;

/**
 * Class representing the server in the client-server architecture.
 * 
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
 * 
 *
 */
public class Question2Server{
	private static int PORT = 8765;
	
	public static void main (String[] args) throws IOException{
		
		// Generate the server socket for connection with the client
		ServerSocket serverSocket = new ServerSocket(PORT);
		// Socket accepts the connection from the client
		Socket client = serverSocket.accept();
		PrintWriter out = null;
		BufferedReader in = null;
		
		try{
			// Generate the PrintWriter and BufferedReader to allow for communication to the client
			out = new PrintWriter(client.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			// Create a string variable for responding to input from the client 
			String response = "";
			// The while loop ensures the connection is maintained for as long as is required
			while(true){
				// Wait for a message to be received from the client
				String message = in.readLine();
				// If the message is null, then the connection to the client has been lost
				if (message == null){
					break;
				}
				else{
					// If the message starts with the keyword "CALCULATE:"
					if (message.startsWith("CALCULATE:")){
						// Split the message by the ':' to remove the command from the calculation
						String[] operation = message.trim().split("[:]");
						// The second split obtains the components of the calculation
						// This was done in case there was additional white space around the calculation
						// eg. CALCULATION:2,3,+ and CALCULATION: 2,3,- would both be accepted
						String[] calculation = operation[1].trim().split("[,]");
						try{
							// obtain the two operands from the Reverse Polish notation
							int operand1 = Integer.parseInt(calculation[0].trim());
							int operand2 = Integer.parseInt(calculation[1].trim());
							// create a variable to store the result of the calculation
							int result = 0;
							// determine if the calculation is addition or subtraction
							if (calculation[2].equals("+")){
								result = operand1+operand2;
								// create the response string
								response = "ANSWER: "+result;
							}
							else if(calculation[2].equals("-")){
								result = operand1-operand2;
								response = "ANSWER: "+result;
							}
							else{
								// if the operation is not addition or subtraction
								// then inform the user that the operation is not supported
								response="OPERATION IS NOT SUPPORTED";
							}
						}
						// if the format of the calculation is incorrect, inform the user
						catch(NumberFormatException nfx){
							response = "CALCULATION FORMAT INCORRECT";
						}
					}
					// if the message begins with EXIT
					else if(message.startsWith("EXIT:")){
						// break from the while loop to close the connection
						break;
					}
					else {
						// all other commands inform the user that the command cannot be identified
						response = "UNKNOWN COMMAND";
					}
				}
				// relay the response to the client
				out.println (response);
				// wait for a response from the client
				message = in.readLine();
				// if there is no response then the client is no longer connected to the server
				if (message==null){
					break;
				}
			}
		}
		catch(IOException io){}
		finally{
			// ensure the PrintWriter, the BufferedReader and the Server Socket are all closed
			if (in!=null)
				in.close();
			if (out!=null)
				out.close();
			if (serverSocket!=null)
				serverSocket.close();
		}
	}
}