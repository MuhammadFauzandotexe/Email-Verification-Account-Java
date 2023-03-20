package Controller;
import Model.UserData;

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

//2016-07-07 17:01:18.410677
//2023-03-20 21:31:35.26
@Path("register")
public class Registration {
    @GET
    public List<UserData> getData(){
        return UserData.listAll();
    }
    @POST
    @Transactional
    public String register(JsonObject body){
        String status = null;
        Date date = new Date();
        Timestamp timeNow = new Timestamp(date.getTime());
        UserData userData = new UserData();

        String emailPattern = "^((?!\\.)[\\w_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])";
        String email = body.getString("email");
        String username = body.getString("username");
        String password = body.getString("password");

        if(EmailAddressCheck(email,emailPattern)){
            userData.email = email;
            userData.password = password;
            userData.username = username;
            userData.createat = timeNow;
            userData.updateat = timeNow;
            userData.persist();
            if (userData.isPersistent()){
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
