import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient implements Info_itf{
	private String clientName;

	public ChatClient(String name){
		this.clientName = name;
	}

	public String getName() throws RemoteException{
		return clientName;
	}

	public void receiveNotification(String message) throws RemoteException {
        System.out.println(message);
    }

  public static void main(String [] args) {
	
	try {
	  if (args.length < 3) {
	   System.out.println("Usage: java ChatClient <rmiregistry host> <rmiregistry port> <nom> ");
	   return;
	}

	String host = args[0];
	int port = Integer.parseInt(args[1]);

	String name = args[2];

	Registry registry = LocateRegistry.getRegistry(host, port); 

	Chat chat = (Chat) registry.lookup("ChatService");

	ChatClient client = new ChatClient(name);
	Info_itf clientObject = (Info_itf)UnicastRemoteObject.exportObject(client, 0);

	// Remote method invocation
	chat.joinChat(clientObject);

	System.out.print("Welcome to the chat, here is what you can do :\n- Enter your message\n- type 'history' to show the history from the launching of the server\n- type 'exit' to leave\n");

	Scanner scanner = new Scanner(System.in);

	while (true) {
		String message = scanner.nextLine();

		if ("exit".equalsIgnoreCase(message)) {
			// Exit the loop and leave the chat
			chat.leaveChat(clientObject);
			break;
		}

		if("history".equalsIgnoreCase(message)){
			chat.get_History(clientObject);
		}

		if(!("history".equalsIgnoreCase(message)))	chat.sendMessage(clientObject, message);
	}

	// On arrête l'objet Info_itf après avoir quitté le serveur
	if (clientObject != null ) {
		UnicastRemoteObject.unexportObject(client, true);
	}
	

	} catch (Exception e)  {
		e.printStackTrace();
	}
  }
}