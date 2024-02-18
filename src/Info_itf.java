import java.rmi.*;

public interface Info_itf extends Remote{
    public String getName() throws RemoteException;
    public void receiveNotification(String message) throws RemoteException;
}
