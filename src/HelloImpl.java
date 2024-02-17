// package src;

// import src.Hello;
import java.rmi.*;

public  class HelloImpl implements Hello {

	private String message;
 
	public HelloImpl(String s) {
		message = s ;
	}

	public String sayHello(Info_itf client) throws RemoteException {
		return message + " " + client.getName();
	}
}
