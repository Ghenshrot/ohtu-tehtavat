package ohtu.kivipaperisakset.pelaaja;

import java.util.List;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;

public class TekoalyPelaaja extends TietokonePelaaja {

    private int siirto;

    public TekoalyPelaaja(Kayttoliittyma kayttis) {
        super(kayttis);
        siirto = 0;
    }

    @Override
    public String annaSiirto() {
        siirto++;
        siirto = siirto % 3;

        if (siirto == 0) {
            return "k";
        } else if (siirto == 1) {
            return "p";
        } else {
            return "s";
        }
    }
}
