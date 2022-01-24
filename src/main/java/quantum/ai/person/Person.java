package quantum.ai.person;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Person {

    private String firstname, lastname, email, phone, address;
}
