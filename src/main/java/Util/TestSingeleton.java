package Util;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import javax.inject.Singleton;


public class TestSingeleton {
    q
}
@Singleton
class MyJobs extends Jobs {

}
class Jobs {
    @Scheduled(every = "1s")
    void everySecond() {
        Log.info(" ====================================================");
        Log.info(" ====================================================");
        Log.info(" ==============                         =============");
        Log.info(" ==============                         =============");
        Log.info(" ==============                         =============");
        Log.info(" ==============                         =============");
        Log.info(" ==============                         =============");
        Log.info(" ==============                         =============");
        Log.info(" ====================================================");
        Log.info(" ====================================================");
    }
}