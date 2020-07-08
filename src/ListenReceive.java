import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
// sever class handles the messages and opens the portal :working on this: Manpreet
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
public class ListenReceive {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	
	  ServerSocket serverSoc = new ServerSocket(4545);
	  
	  
	  System.out.println("ServerSocket awaiting connections...");
	  
	  Socket socket= serverSoc.accept();
	  
	  System.out.println("Connection from " + socket + "!");

	  // get the input stream from the connected socket
      InputStream inputStream = socket.getInputStream();

      // create a ObjectInputStream so we can read data from it.
	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
	 List<ChatBox> listOfMessages = (List<ChatBox>) objectInputStream.readObject();
     System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
		
		
  // for every message, call printMessage(message);
     System.out.println("All messages:");
     listOfMessages.forEach(msg -> printMessage(msg));

     System.out.println("Closing sockets.");
     serverSoc.close();
     socket.close();
		
		
	}
		
	 private static void printMessage(ChatBox msg){
	        System.out.println("ID: " + msg.getID());
	        System.out.println("Type: " + msg.getType());
	        System.out.println("Text: " + msg.getText());
	    }
	
	
	
	
}
