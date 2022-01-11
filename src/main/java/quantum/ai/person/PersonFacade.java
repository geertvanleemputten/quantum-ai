package quantum.ai.person;

import lombok.extern.log4j.Log4j2;
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

        Person person = Person.builder()
                .firstname(nameFakePerson.getFirstname())
                .lastname(nameFakePerson.getLastname())
                .email(nameFakePerson.getEmail())
                .phone(phone)
                .build();

        log.info("Generated " + person);
        return person;
    }

}
