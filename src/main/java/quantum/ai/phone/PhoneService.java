package quantum.ai.phone;

import java.util.Random;

public class PhoneService {

    private static final String[] PHONES = new String[]{
            "460260705",
            "460224237",
            "460222691",
            "460225129",
            "460222611",
            "460222538",
            "460206031",
            "460215316"
    };

    /*public String getRandomPhone() {
        int rnd = new Random().nextInt(PHONES.length);
        return PHONES[rnd];
    }*/


    public String getRandomPhone() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return "460" + n;
    }
}
