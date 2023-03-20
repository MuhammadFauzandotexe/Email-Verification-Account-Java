package Util;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
@Path("test")
public class testEmail {
    @Inject
    Mailer mailer;
    @GET
    public String testEmail(){
        mailer.send(Mail.withText("mfauzan51087@gmail.com",
                "Mr. Dengklek's Harvest Reporting",
                "Hello Mr. Dengklek.\n" +
                        " I hope you are in good health.\n" +
                        " This is the reporting of your garden results for this month.\n"));
        return "secuu";
    }
}
