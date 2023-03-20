package Controller;
import Model.UserData;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Path("verify")
public class Verification {
    @GET
    @Transactional
    @Path("{data}")
    public String verify(@PathParam("data")String data){
        Integer result = null;
        Base64.Decoder decoder = Base64.getDecoder();
        String dataString = new String(decoder.decode(data));
        List<String> dataUser = new ArrayList<>();
        String[] dataUserSplit = dataString.split("&=");
        for (String i:dataUserSplit){
            dataUser.add(i);
        }
        String uuid = dataUser.get(0);
        String email = dataUser.get(1);
        String username = dataUser.get(2);
        String verification = "verified";
        result = UserData.update("verification = ?1, email = ?2, username = ?3 where uuid = ?4 ",verification,email,username,uuid);
        String status = (result >= 1)? "your account has been verified":"the link cannot be used";
        return status;
    }
}
