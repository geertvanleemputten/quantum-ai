package quantum.ai.person;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import quantum.ai.namefake.NameFakeAPIResponse;
import quantum.ai.namefake.NameFakeAPIService;
import quantum.ai.phone.PhoneService;

@Log4j2
public class PersonFacade {

    private NameFakeAPIService nameFakeAPIService = new NameFakeAPIService();
    private PhoneService phoneService = new PhoneService();

    public Person generatePerson() {
        final NameFakeAPIResponse nameFakePerson = nameFakeAPIService.getFakePerson();
        final String phone = phoneService.getRandomPhone();

        final Person person = Person.builder()
                .firstname(nameFakePerson.getFirstname())
                .lastname(nameFakePerson.getLastname())
                .email(nameFakePerson.getEmail())
                .address(RandomStringUtils.random(10, true, false) + " " + RandomStringUtils.random(3, false, true))
                .postalcode(RandomStringUtils.random(4, false, true))
                .phone(phone)
                .build();

        log.info("Generated " + person);
        return person;
    }

}
