package Controller;
import Model.UserData;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
@Path("notverified")
public class NotVerified {
    @GET
    @Transactional
    //@Scheduled(every = "59s")
    public void getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String day = formatter.format(date).toString();
        Long hour = (long) java.time.LocalTime.now().getHour();
        Long minute = (long) java.time.LocalTime.now().getMinute()-5;
        String linkExpire = day+" "+hour.toString()+":"+minute;
        Long results = UserData.delete("updateat <'"+ linkExpire +"' and verification = 'notverified'");
        Log.info("link expire : "+results);
    }
}
