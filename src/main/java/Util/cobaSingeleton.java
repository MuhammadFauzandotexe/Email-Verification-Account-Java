package Util;
import io.quarkus.logging.Log;
import javax.inject.Singleton;
public class cobaSingeleton {
    @Singleton
    public void cek(){
        Log.info(" ======================> Hai i'm done <==========================");
    }
}



