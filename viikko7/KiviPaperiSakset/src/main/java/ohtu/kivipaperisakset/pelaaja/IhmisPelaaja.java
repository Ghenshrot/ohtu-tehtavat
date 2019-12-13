package ohtu.kivipaperisakset.pelaaja;

import ohtu.kivipaperisakset.Siirto;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;

public class IhmisPelaaja extends Pelaaja {

    public IhmisPelaaja(Kayttoliittyma kayttis, String nimi) {
        super(kayttis, nimi);
    }
    
    @Override
    public Siirto annaSiirto() {
        try {
            String space = this.getNimi().length() == 0 ? "" : " ";
            String syote = getKayttoliittyma().lueSyote("Pelaajan " + this.getNimi() + space + "siirto");

            Siirto siirto = new Siirto(syote);
            return siirto;
            
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
