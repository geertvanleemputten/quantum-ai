package quantum.ai.apexchange24;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.StopWatch;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import quantum.ai.person.Person;
import quantum.ai.person.PersonFacade;

public class JsoupRegistrationService {

    private int count = 0;
    private PersonFacade personFacade = new PersonFacade();

    @SneakyThrows
    public void register() {
        final StopWatch stopWatch = StopWatch.createStarted();

        final String url = "https://www.apexchange24.com/real";
        final Connection connection = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36 OPR/62.0.3331.99")
                .followRedirects(true);
        final Connection.Response response = connection.execute();
        final Document document = response.parse();

        //System.out.println(response.statusCode());
        //System.out.println(response.statusMessage());
        //System.out.println(response.url());
        //System.out.println(response.headers());
        //System.out.println(document.body());
        //System.out.println("initial connection. " + stopWatch.formatTime());
        final Person person = personFacade.generatePerson();
        //System.out.println("person created. " + stopWatch.formatTime());
        System.out.println(person);

        document.select("input[name=\"FullName\"]").first().val(person.getFirstname() + " " + person.getLastname());
        document.select("input[name=\"UncheckedEmail\"]").first().val(person.getEmail());
        document.select("input[name=\"Email\"]").first().val(person.getEmail());
        //document.select("input[name=\"password\"]").first().val("1234Abcd");
        document.select("input[name=\"PhoneAreaCode\"]").first().val("32");
        document.select("input[name=\"PhoneNumber\"]").first().val(person.getPhone());
        document.select("input[name=\"IsTermsAccepted\"]").forEach(x -> {
            x.attr("checked", "checked");
            x.attr("value", "true");
        });
        document.select("input[name=\"IsUpgrade\"]").first().val("true");
        document.select("input[name=\"IsUpgrade\"]").first().attr("checked", "checked");

        final Connection.Response registrationResponse = ((FormElement) document.select("#actionForm").first()).submit().followRedirects(true).execute();

        //System.out.println("\"" + registrationResponse.url() + "\"");
        //final Document registrationResult = registrationResponse.parse();
        //System.out.println(registrationResult.body());

        final boolean success = "https://www.apexchange24.com/welcome".equals(registrationResponse.url().toString());
        if (success) {
            count++;
        }
        System.out.println("SUCCESS = " + success + ". Finished in " + stopWatch.formatTime() + ". Total = " + count);
    }
}
