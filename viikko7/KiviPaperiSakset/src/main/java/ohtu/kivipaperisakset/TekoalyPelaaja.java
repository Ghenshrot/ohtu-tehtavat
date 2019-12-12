package ohtu.kivipaperisakset;

import java.util.List;

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

    @Override
    public void asetaToistenPelaajienSiirrot(List<String> siirrot) {
    }
}
