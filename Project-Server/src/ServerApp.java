/**************************************ID BLOCK*******************************************************

Due Date:			May 11th, 2012
Software Designers:	Alexandre Simard & Peter Johnston
Course:				420-603  Winter 2012
Deliverable:		Project --- Knight's Watch --- Server
Description:		This program creates a Socket Server which listens to a specific port.  When
					2 players connect to the server, it commences a game.  The server can support
					any number of games, the only limitation is the memory.  During the game, the
					server receives and transmits moves to and from the players, and keeps track of
					the game (board, who's turn, etc.).  When one of the clients detects an end of 
					game, it transmits it to the server, then to the other client, and the game ends.
					
******************************************************************************************************/

import java.io.IOException;
import java.net.ServerSocket;

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
		//set port and start server
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
