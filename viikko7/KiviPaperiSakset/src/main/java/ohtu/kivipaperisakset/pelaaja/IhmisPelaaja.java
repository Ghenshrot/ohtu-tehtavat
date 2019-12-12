package ohtu.kivipaperisakset.pelaaja;

import java.util.List;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;

public class IhmisPelaaja extends Pelaaja {

    public IhmisPelaaja(Kayttoliittyma kayttis, String nimi) {
        super(kayttis, nimi);
    }
    
    @Override
    public String annaSiirto() {
        String space = this.getNimi().length() == 0 ? "" : " ";
        return getKayttoliittyma().lueSyote("Pelaajan " + this.getNimi() + space + "siirto");
    }
}
