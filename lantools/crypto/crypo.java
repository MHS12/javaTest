package lantools.crypto;

import java.util.Base64;

public  class crypo {
    public static String encode(String text){
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
    
    public static String decode(String text){
        return new String( Base64.getDecoder().decode(text));
    }
}
