package quantum.ai.person;

import quantum.ai.namefake.NameFakeAPIResponse;
import quantum.ai.namefake.NameFakeAPIService;
import quantum.ai.phone.PhoneService;

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

        System.out.println("Generated " + person);
        return person;
    }

}
