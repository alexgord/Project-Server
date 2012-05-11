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
			out1.writeObject("1");//.println("1");
			out1.flush();
			out2.writeObject("2");
			out2.flush();

			/*name1 = in1.nextLine();
			System.out.println("First person is " + name1);
			name2 = in2.nextLine();
			System.out.println("Second person is " + name2);

			String message = "Welcome to Knight's Watch " + getName() + ", you are playing against ";
			out1.println(message + name2);
			out2.println(message + name1);

			out1.println("Please start the conversation.");*/

			int count = 0;
			while(true)
			{
				Move move;
				System.out.print(this.getName() + " ");
				if(count % 2 == 0)
				{
					move = (Move) in1.readObject();//.nextLine();
					if (move.getResultingGameStatus() == GameStatus.CONTINUE)
					{
						System.out.println("White has made this move:  From " + move.getA().toString() + " to " + move.getB().toString());//(name1 + " says \"" + text + "\"");
						out2.writeObject(move);//(name1 + " says \"" + text + "\"");//.println(text);//(name1 + " says \"" + text + "\"");
						out2.flush();
					}
					else
					{
						break;
					}
				}
				else
				{
					move = (Move) in2.readObject();//.nextLine();
					if (move.getResultingGameStatus() == GameStatus.CONTINUE)
					{
						System.out.println("Black has made this move:  From " + move.getA().toString() + " to " + move.getB().toString());//(name2 + " says \"" + text + "\"");
						out1.writeObject(move);//(name2 + " says \"" + text + "\"");//.println(text);//(name2 + " says \"" + text + "\"");
						out1.flush();
					}
					else
					{
						break;
					}
				}
				count++;
			}
			this.closeAll();
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
