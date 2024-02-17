import java.rmi.*;

public interface Hello extends Remote {
	public String sayHello(String clientName)  throws RemoteException;
}