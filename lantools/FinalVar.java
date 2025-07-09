package lantools;

import java.io.IOException;
import java.net.*;
import java.util.Properties;

import lantools.crypto.crypo;
import lantools.file_rw.FileHandle;

public class FinalVar {

    public String ip;
    public int port;

    FileHandle filehandle;

    public FinalVar() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            port = getPort();
            filehandle = new FileHandle();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    int getPort() throws IOException {
        try {
            Properties properties = filehandle.properties_obj();
            String port = (String) properties.getProperty(crypo.encode("port"));
            System.out.println("h");
            if (port == null)
                throw new Exception("Can't find  Port");
            else
                return Integer.parseInt(crypo.decode(port));
        } catch (Exception e) {
            setPort(8080);
            return port;
        }
        
    }

    void setPort(int port) throws IOException {
        filehandle = new FileHandle();
        filehandle.write("Port", "8220");
        this.port = port;
    }

}