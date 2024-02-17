import java.rmi.*;

public interface Hello extends Remote {
	public String sayHello(Info_itf client)  throws RemoteException;
	
}