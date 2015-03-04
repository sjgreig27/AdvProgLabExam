import java.net.*;
import java.io.*;

public class CalculatorServer{
	private static int PORT = 8765;
	public static void main (String[] args) throws IOException{

		ServerSocket serverSocket = new ServerSocket(PORT);
		Socket client = serverSocket.accept();
		PrintWriter out=null;
		BufferedReader in=null;

		try{
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String response = "";
			while(true){
				String message = in.readLine();
				if(message == null) {
					break;
				}
				else if(message.startsWith("[0-9]+")) {
					String [] calculation = message.trim().split(",");
					int operand1 = Integer.parseInt(calculation[0]);
					int operand2 = Integer.parseInt(calculation[1]);
					int result = 0;
					if (calculation[2].equals("+")){
						result=operand1+operand2;
						response = ""+result;
					}
					else if (calculation[2].equals("-")){
						result=operand1-operand2;
						response = ""+result;
					}
					else{
						response = "OPERATION NOT SUPPORTED";
					}
				}
				else if (message.startsWith("EXIT")){
					break;
				}
				else {
					response = "SORRY, UNKNOWN COMMAND";
				}
				// send the response
				out.println(response);
				// Wait for a response. If none comes, the client is dead
				if(in.readLine() == null) {
					break;
				}
			}
		}
		catch (Exception e){}
		finally{
			if(in!=null)
				in.close();
			if(out!=null)
				out.close();
			if(serverSocket!=null)
				serverSocket.close();
		}
	}
}
