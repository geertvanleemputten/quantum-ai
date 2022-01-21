package quantum.ai.apexchange24;

import java.util.Arrays;

public class ApexChange24 {

    private static final String[] threads = new String[6];
    private static final JsoupRegistrationService registerService = new JsoupRegistrationService();

    public static void main(String... args) {
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
