package Util;
import java.util.Base64;
public class Bash64Token {
    Base64.Encoder encoder = Base64.getEncoder();
    public String data;
    public Bash64Token(String data){
        this.data = data;
    }
    public String getBash64(){
        String mydata = encoder.encodeToString(data.getBytes());;
        return mydata;
    }
}
