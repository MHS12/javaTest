package lantools.file_rw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lantools.FilePaths;
import lantools.crypto.crypo;

public class FileHandle {
    Properties properties;
    FileInputStream in;

    public void setFileInputStream(String path) {
        try {
            in = new FileInputStream(path);
            properties.load(in);
            in.close();
        } catch (Exception e) {
            System.out.println("Setting Path is fail " + e);
        }
    }

    public FileHandle() {
        try {
            properties = properties_obj();
            in = new FileInputStream(FilePaths.ipfile);
            properties.load(in);
            in.close();
        } catch (Exception e) {
            System.out.println("Setting Path is fail " + e);
        }
    }

    public Properties properties_obj() {
        if (properties != null) {
            return properties;
        } else {
            return new Properties();
        }
    }

    public void write(String key, String value) throws IOException {
        properties.setProperty(crypo.encode(key), crypo.encode(value));
        int p = properties.size();
        FileOutputStream out = new FileOutputStream(FilePaths.ipfile);
        properties.store(out, "Added " + p);
    }

    public String read(String key) {
        try {
            String value = properties.getProperty(crypo.encode(key));
            if (value != null) {
                return crypo.decode(properties.getProperty(crypo.encode(key)));
            } else {
                throw new Exception("Properties object is not initialized.");
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Map<String, String> readAll() {
        Map<String, String> keyvaluepairs = new HashMap<String, String>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = new String(crypo.decode((String) entry.getKey()));
            String value = new String(crypo.decode((String) entry.getValue()));
            keyvaluepairs.put(key, value);
        }
        return keyvaluepairs;
    }
}
