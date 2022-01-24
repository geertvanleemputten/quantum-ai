package quantum.ai.deprecated;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.StopWatch;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import quantum.ai.person.Person;
import quantum.ai.person.PersonFacade;

public class JsoupRegistrationService {

    private PersonFacade personFacade = new PersonFacade();

    @SneakyThrows
    public void register() {
        StopWatch stopWatch = StopWatch.createStarted();

        final String url = getUrl();
        System.out.println("getting url from google. " + stopWatch.formatTime());
        System.out.println("url = " + url);
        final Connection connection =  Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                .followRedirects(true)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Accept-Language", "nl-NL,nl;q=0.9,en-US;q=0.8,en;q=0.7");
        final Connection.Response response = connection.execute();
        final Document document = response.parse();

        System.out.println(response.statusCode());
        System.out.println(response.statusMessage());
        System.out.println(response.url());
        System.out.println(response.body());
        System.out.println("initial connection. " + stopWatch.formatTime());
        final Person person = personFacade.generatePerson();
        System.out.println("person created. " + stopWatch.formatTime());

        document.select("input[name=\"firstName\"]").first().val(person.getFirstname());
        document.select("input[name=\"lastName\"]").first().val(person.getLastname());
        document.select("input[name=\"email\"]").first().val(person.getEmail());
        document.select("input[name=\"password\"]").first().val("1234Abcd");
        document.select("input[name=\"phone\"]").first().val(person.getPhone());
        System.out.println("filling form. " + stopWatch.formatTime());


        final Connection.Response registrationResponse = ((FormElement) document.select(".sdk-form").first()).submit().followRedirects(true).execute();
        final Document registrationResult = registrationResponse.parse();
        System.out.println("form submitted. " + stopWatch.formatTime());

        System.out.println(registrationResponse.url());
        System.out.println(registrationResponse.body());
    }

    @SneakyThrows
    private String getUrl() {
        return Jsoup.connect("https://www.google.com/search?q=quantum+ai").execute().parse().select("a.sVXRqc").first().attr("data-rw");
    }
}
