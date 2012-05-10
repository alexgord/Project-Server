import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Conversation extends Thread {

	private Socket p1, p2;
	private String name1, name2;
	private Scanner in1, in2;
	private PrintStream out1, out2;

	public Conversation(String name) {
		super(name);
	}

	public void setPerson(int p, Socket socket) {
		if(p==1) 
			this.p1 = socket;
		else
			this.p2 = socket;
	}

	@Override
	public void run() {

		if(p1 == null || p2 == null) {
			System.err.println("Knight's Watch " + getName() + " is missing a participant.");
			return;
		}

		try {
			in1 = new Scanner(p1.getInputStream());
			in2 = new Scanner(p2.getInputStream());
			out1 = new PrintStream(p1.getOutputStream());
			out2 = new PrintStream(p2.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			this.closeAll();
			return;
		}
	
		try {
			name1 = in1.nextLine();
			System.out.println("First person is " + name1);
			name2 = in2.nextLine();
			System.out.println("Second person is " + name2);
			
			String message = "Welcome to Knight's Watch " + getName() + ", you are playing against ";
			out1.println(message + name2);
			out2.println(message + name1);
			
			out1.println("Please start the conversation.");
			
			int count = 0;
			while(true) {
				String text;
				if(count % 2 == 0) {
					text = in1.nextLine();
					System.out.println(name1 + " says \"" + text + "\"");
					out2.println(name1 + " says \"" + text + "\"");
				}
				else {
					text = in2.nextLine();
					System.out.println(name2 + " says \"" + text + "\"");	
					out1.println(name2 + " says \"" + text + "\"");					
				}
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.closeAll();
			return;
		}
	}

	private void closeAll() {
		if(in1 != null)
			in1.close();
		if(in2 != null)
			in2.close();
		if(out1 != null)
			out1.close();
		if(out2 != null)
			out2.close();
		if(p1 != null)
			try {
				p1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(p2 != null)
			try {
				p2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}


}
