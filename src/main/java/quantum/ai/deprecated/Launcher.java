package quantum.ai.deprecated;

import java.util.Arrays;

public class Launcher {

    private static final String[] threads = new String[5];
    private static final JsoupRegistrationService registrationService = new JsoupRegistrationService();
    private static final SeleniumRegistrationService seleniumRegistrationService = new SeleniumRegistrationService();

    public static void main(String... args) {
        //registrationService.register();
        //seleniumRegistrationService.register();
        Arrays.stream(threads).parallel().forEach(x -> {
            while(true) {
                seleniumRegistrationService.register();
            }
        });
    }
}
