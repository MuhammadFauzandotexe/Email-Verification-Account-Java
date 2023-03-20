package Controller;
import Model.UserData;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import Util.Bash64Token;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Path("register")
public class Registration {
    @GET
    public List<UserData> getData(){
        return UserData.listAll();
    }
    @Inject
    Mailer mailer;
    @POST
    @Transactional
    public String register(JsonObject body){
        String status = null;
        Date date = new Date();
        Timestamp timeNow = new Timestamp(date.getTime());
        UserData userData = new UserData();
        String Domain = "http://localhost:9000/verify/";

        String emailPattern = "^((?!\\.)[\\w_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])";
        String email = body.getString("email");
        String username = body.getString("username");
        String password = body.getString("password");

        if(EmailAddressCheck(email,emailPattern)){
            userData.password = password;
            userData.createat = timeNow;
            userData.updateat = timeNow;
            userData.verification = "notverified";
            userData.persist();
            if (userData.isPersistent()){
                Bash64Token bash64Token = new Bash64Token(userData.getUuid()+"&="+email+"&="+username);
                String subject = "Hello "+username+", immediately verify your Beretta account";
                String message = "Hello " +username+".\n" +
                        "Thank you for registering as a member of Beretta Indonesia.\n" +
                        "Next, let's verify your account via the following link: \n"+
                        Domain+bash64Token.getBash64();
                mailer.send(Mail.withText(email, "Beretta Email Verification",message));
                status = "successful registration \n" +
                        "verify your account via the link we sent to your email address \n" +
                        "the link will expire after 24 hours.\n";
            }
        }else {
            status = "your email address is invalid";
        }
        return status;
    }
    boolean EmailAddressCheck(String email,String pattern){
        Pattern emailPattern = Pattern.compile(pattern);
        Matcher emailMatc = emailPattern.matcher(email);
        boolean status = emailMatc.matches();
        return status;
    }
}
