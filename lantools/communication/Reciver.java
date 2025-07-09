package lantools.communication;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Reciver {

    public Reciver(){

    }

    public void ReciverFile() throws IOException{
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket socket = serverSocket.accept();DataInputStream dis = new DataInputStream(socket.getInputStream());
        receiveFolder(dis, "C:\\TestFolderREc\\");
        dis.close();
    }

    void receiveFolder(DataInputStream dis, String destDir) throws IOException {
    while (true) {
        String relativePath = dis.readUTF();
        if (relativePath.equals("EOF")) break;

        long fileSize = dis.readLong();
        File outFile = new File(destDir + File.separator + relativePath);
        outFile.getParentFile().mkdirs(); // create directories

        try (FileOutputStream fos = new FileOutputStream(outFile)) {
            byte[] buffer = new byte[4096];
            long remaining = fileSize;
            int count;
            while (remaining > 0 && (count = dis.read(buffer, 0, (int)Math.min(buffer.length, remaining))) > 0) {
                fos.write(buffer, 0, count);
                remaining -= count;
            }
        }
    }
}

}
