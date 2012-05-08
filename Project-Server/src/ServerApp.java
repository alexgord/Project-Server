import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.ws.http.HTTPException;

import com.sun.net.httpserver.HttpServer;


public class ServerApp  {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HTTPException 
	 */
	public static void main(String[] args) throws HTTPException, IOException
	{
		//MessageList messageList = new MessageList();
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/", new EchoServer());
	    server.setExecutor(null); // creates a default executor
	    server.start();
	}
}
