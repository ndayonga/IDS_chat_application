import java.rmi.*; 
import java.rmi.server.*;
import java.util.ArrayList;
import java.rmi.registry.*;

public class ChatServer {

  public static void  main(String [] args) {
	  try {
		  // Create a join remote object
	    ChatImpl chat = new ChatImpl();
	    Chat chat_stub = (Chat)UnicastRemoteObject.exportObject(chat, 0);

	    // Register the remote object in RMI registry with a given identifier
	    Registry registry = null;
	    if (args.length>0)
		    registry= LocateRegistry.getRegistry(Integer.parseInt(args[0])); 
	    else
		    registry = LocateRegistry.getRegistry();

	    registry.rebind("ChatService", chat_stub);

	    System.out.println ("Server ready");


	  } catch (Exception e) {
		  System.err.println("Error on server :" + e) ;
		  e.printStackTrace();
	  }
  }
}