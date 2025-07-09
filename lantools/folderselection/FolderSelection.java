package lantools.folderselection;

import java.io.*;

public class FolderSelection {

    public void print(){
       File ff= new File("C:\\");
       if(ff.isDirectory()){
        for(File file : ff.listFiles()){
            System.out.println(file);
        }
       }
    }
}
