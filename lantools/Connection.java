package lantools;

import java.net.Socket;

public class Connection {
    public boolean isConnected(String ip,int port){
        try(Socket socket = new Socket(ip,port)){
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
