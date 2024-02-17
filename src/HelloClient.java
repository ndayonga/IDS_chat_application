import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class HelloClient implements Info_itf{
	private String clientName;

	public HelloClient(String name){
		this.clientName = name;
	}

	@Override
	public String getName() throws RemoteException{
		return clientName;
	}
  public static void main(String [] args) {
	
	try {
	  if (args.length < 2) {
	   System.out.println("Usage: java HelloClient <rmiregistry host> <rmiregistry port>");
	   return;}

	String host = args[0];
	int port = Integer.parseInt(args[1]);

	Registry registry = LocateRegistry.getRegistry(host, port); 
	Hello h = (Hello) registry.lookup("HelloService");

	HelloClient client = new HelloClient("Client_Name");
	Info_itf clientObject = (Info_itf)UnicastRemoteObject.exportObject(client, 0);

	// Remote method invocation
	String res = h.sayHello(clientObject);
	System.out.println(res);

	// On arrête l'objet Info_itf après avoir donné le nom
	if (clientObject != null) {
		UnicastRemoteObject.unexportObject(client, true);
	}
	

	} catch (Exception e)  {
//		System.err.println("Error on client: " + e);
		e.printStackTrace();
	}
  }
}