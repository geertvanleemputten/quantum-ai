package quantum.ai.btreservecc;

import java.util.Arrays;

public class BTReserveCC {

    private static final String[] threads = new String[6];
    private static final JsoupRegistrationService registerService = new JsoupRegistrationService();

    public static void main(final String... args) {
        //registerService.register();
        Arrays.stream(threads).parallel().forEach(x -> {
            while (true) {
                try {
                    registerService.register();
                } catch (final Throwable t) {
                    System.out.println(t.getMessage());
                }
            }
        });
    }
}
