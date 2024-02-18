import java.rmi.*;

public interface Chat extends Remote{
    public void joinChat(Info_itf client)  throws RemoteException;
    public void leaveChat(Info_itf client)  throws RemoteException;
    public void sendMessage(Info_itf client, String message)  throws RemoteException;
    public void get_History(Info_itf client) throws RemoteException;
    public void notifyClient(Info_itf client, String message) throws RemoteException;
    public void notifyClients(Info_itf client, String message) throws RemoteException;
}
