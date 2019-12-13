package ohtu.kivipaperisakset.pelaaja;

import ohtu.kivipaperisakset.Siirto;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;

public class TekoalyPelaaja extends TietokonePelaaja {

    private int siirto;

    public TekoalyPelaaja(Kayttoliittyma kayttis) {
        super(kayttis);
        siirto = 0;
    }

    @Override
    public Siirto annaSiirto() {
        siirto++;
        siirto = siirto % 3;

        if (siirto == 0) {
            return new Siirto(Siirto.SIIRTO.KIVI);
        } else if (siirto == 1) {
            return new Siirto(Siirto.SIIRTO.PAPERI);
        } else {
            return new Siirto(Siirto.SIIRTO.SAKSET);
        }
    }
}
