import java.io.IOException;
import java.util.Scanner;

import lantools.communication.*;

public class LanCom {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Press 1 for send
                Press 2 for Recive
                """);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n == 1){
            Send s= new Send();
            s.sendFile();
        }else if(n == 2){
            Reciver rec = new Reciver();
            rec.ReciverFile();
        }else{
            System.out.println("Try Again...!");
        }

    }
}