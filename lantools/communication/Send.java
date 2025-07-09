package lantools.communication;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import lantools.*;;

public class Send {
    Connection connection;
    FinalVar finalVar;
    public Send(){
        connection = new Connection();
        finalVar = new FinalVar();
    }

    public void sendFile() throws UnknownHostException, IOException{
        if(connection.isConnected(finalVar.ip,finalVar.port)){
            System.out.println("Connected");

            Socket socket = new Socket(finalVar.ip,finalVar.port);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            File folderToSend = new File("C:\\Users\\amahe\\Desktop\\TestFolder");

            sendff(folderToSend,dos,folderToSend.getAbsolutePath());
            dos.close();
        }else{
            System.out.println("Not Connected");
        }
    }


    void sendff(File folder, DataOutputStream dos, String basePath) throws IOException{
       for (File file : folder.listFiles()) {
        if (file.isDirectory()) {
            sendff(file, dos, basePath); 
        } else {
            String relativePath = file.getAbsolutePath().substring(basePath.length());
            dos.writeUTF(relativePath); 
            dos.writeLong(file.length()); 

            // send file data
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int count;
                while ((count = fis.read(buffer)) > 0) {
                    dos.write(buffer, 0, count);
                }
            }
        }
    }
    dos.writeUTF("EOF");
    }
    
}
