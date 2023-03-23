package Model;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class UserData extends PanacheEntityBase {
    @Id
    @Column(name = "uuid", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;
    public String username;
    public String email;
    public String password;
    public Date createat;
    public Date updateat;
    public String verification;

    @JsonGetter
    public String getUuid() {
        return uuid;
    }
    @JsonIgnore
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
