import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp
{

	final private static int DEFAULT_PORT = 9999;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HTTPException 
	 */
	public static void main(String[] args) throws IOException
	{
		//@SuppressWarnings("unused")
		//Game game1 = new Game();

		int port = args.length > 0 ? Integer.parseInt(args[0]) :  DEFAULT_PORT;
		ServerSocket server = null;
		try
		{
			server = new ServerSocket(port);
		}
		catch (IOException e)
		{
			System.err.println("Could not listen on port: " + port + ".");
			System.exit(-1);
		}
		System.out.println("Listening on port: " + port + ".");


		// Now listen for a client
		int count = 0;
		while(true)
		{
			try 
			{
				Conversation conv = new Conversation(Integer.toString(++count));
				System.out.println("Starting Game " + count + ".");

				conv.setPerson(1, server.accept());
				System.out.println("First person has joined.");

				conv.setPerson(2, server.accept());
				System.out.println("Second person has joined.");

				conv.start();

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
