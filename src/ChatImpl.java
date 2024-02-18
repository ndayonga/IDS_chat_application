import java.rmi.*;
import java.util.ArrayList;

public  class ChatImpl implements Chat {

    private String joinMessage = "joined the Chat";
    private String leaveMessage = "left the chat";
    private ArrayList<Info_itf> clients = new ArrayList<>();

    private ArrayList<String> history = new ArrayList<>();

	public ChatImpl() {
	}

    public ArrayList<Info_itf> getClients(){
        return clients;
    }

	public void joinChat(Info_itf client) throws RemoteException {
        clients.add(client);
        String joinMessage = client.getName() + " " + this.joinMessage;

        history.add(joinMessage);
        notifyClients(client, joinMessage);
    }

    public void leaveChat(Info_itf client) throws RemoteException {
        clients.remove(client);
        String leaveMessage = client.getName() + " " + this.leaveMessage;
        
        history.add(leaveMessage);
        notifyClients(client, leaveMessage);
	}

    public void sendMessage(Info_itf client, String message)  throws RemoteException{
        String messageSent= client.getName() + " : " + message;

        history.add(messageSent);
        notifyClients(client, messageSent);
    }

    public void get_History(Info_itf client) throws RemoteException{
        notifyClient(client, "============= HISTORY ============");
        for (String m : history){
            notifyClient(client, m);
        }
        notifyClient(client, "============== . ==============");
    }

    public void notifyClient(Info_itf client, String message) throws RemoteException {
        for (Info_itf clientX : clients) {
            if(clientX.equals(client))   clientX.receiveNotification(message);
        }
    }

    public void notifyClients(Info_itf client, String message) throws RemoteException {
        for (Info_itf clientX : clients) {
            if(!(clientX.equals(client)))   clientX.receiveNotification(message);
        }
    }
}