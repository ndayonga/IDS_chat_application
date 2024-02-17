import java.rmi.*;
import java.rmi.registry.*;

public class HelloClient {
  public static void main(String [] args) {
	
	try {
	  if (args.length < 2) {
	   System.out.println("Usage: java HelloClient <rmiregistry host> <rmiregistry port>");
	   return;}

	String host = args[0];
	int port = Integer.parseInt(args[1]);

	Registry registry = LocateRegistry.getRegistry(host, port); 
	Hello h = (Hello) registry.lookup("HelloService");

	// Remote method invocation
	String res = h.sayHello("Client_Name");
	System.out.println(res);

	} catch (Exception e)  {
//		System.err.println("Error on client: " + e);
		e.printStackTrace();
	}
  }
}