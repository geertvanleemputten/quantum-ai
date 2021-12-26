package quantum.ai.namefake;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.Locale;

@Setter
public class NameFakeAPIResponse {

    private String name;
    @JsonProperty("email_u")
    private String emailA;
    @JsonProperty("email_d")
    private String emailB;

    public String getFirstname() {
        return name.substring(0, name.indexOf(" "));
    }

    public String getLastname() {
        return name.substring(name.indexOf(" ") + 1);
    }

    public String getEmail() {
        return emailA + "@" + emailB;
    }

    public String getBEmail() {
        return emailA + "@gmail.com";
    }
}
