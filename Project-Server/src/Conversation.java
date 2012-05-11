import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Conversation extends Thread
{

	private Socket p1, p2;
	private String name1, name2;
	private ObjectInputStream in1, in2;
	private ObjectOutputStream out1, out2;

	public Conversation(String name)
	{
		super(name);
	}

	public void setPerson(int p, Socket socket)
	{
		if(p==1) 
			this.p1 = socket;
		else
			this.p2 = socket;
	}

	@Override
	public void run()
	{

		if(p1 == null || p2 == null)
		{
			System.err.println("Knight's Watch " + getName() + " is missing a participant.");
			return;
		}

		try
		{
			out1 = new ObjectOutputStream(p1.getOutputStream());
			out1.flush();
			out2 = new ObjectOutputStream(p2.getOutputStream());
			out2.flush();
			in1 = new ObjectInputStream(p1.getInputStream());
			in2 = new ObjectInputStream(p2.getInputStream());

		}
		catch (IOException e)
		{
			e.printStackTrace();
			try
			{
				this.closeAll();
			}
			catch (IOException e1)
			{
				System.out.println("Connections could not be closed!");
				e1.printStackTrace();
			}
			return;
		}

		try
		{
			out1.writeObject("1");
			out1.flush();
			out2.writeObject("2");
			out2.flush();

			int count = 0;
			Move move;
			while(true)
			{
				if(count % 2 == 0)
				{
					move = (Move) in1.readObject();
					System.out.print("Game " + this.getName() + " ");
					System.out.println("White has made this move:  From " + move.getA().toString() + " to " + move.getB().toString());//(name1 + " says \"" + text + "\"");
					out2.writeObject(move);
					out2.flush();
					if (move.getResultingGameStatus() != GameStatus.CONTINUE)
					{
						break;
					}
				}
				else
				{
					move = (Move) in2.readObject();
					System.out.print("Game " + this.getName() + " ");
					System.out.println("Black has made this move:  From " + move.getA().toString() + " to " + move.getB().toString());//(name2 + " says \"" + text + "\"");
					out1.writeObject(move);
					out1.flush();
					if (move.getResultingGameStatus() != GameStatus.CONTINUE)
					{
						break;
					}
				}
				count++;
			}
			this.closeAll();
			System.out.print("Game " + this.getName() + " has finished, and ");
			switch (move.getResultingGameStatus())
			{
			case WHITEWINS:
				System.out.println("White has won!");
				break;
			case BLACKWINS:
				System.out.println("Black has won!");
				break;
			case DRAW:
				System.out.println("it was a draw!");
				break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				this.closeAll();
			} 
			catch (IOException e1)
			{
				System.out.println("Connections could not be closed!");
				e1.printStackTrace();
			}
			return;
		}
	}

	private void closeAll() throws IOException
	{
		if(in1 != null)
			in1.close();
		if(in2 != null)
			in2.close();
		if(out1 != null)
			out1.close();
		if(out2 != null)
			out2.close();
		if(p1 != null)
		{
			try
			{
				p1.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if(p2 != null)
		{
			try
			{
				p2.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
