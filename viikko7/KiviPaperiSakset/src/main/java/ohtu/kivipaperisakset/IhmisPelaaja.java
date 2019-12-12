package ohtu.kivipaperisakset;

import java.util.List;
import java.util.Scanner;

public class IhmisPelaaja implements Pelaaja {

    private final Kayttoliittyma kayttis;
    
    public IhmisPelaaja(Kayttoliittyma kayttis) {
        this.kayttis = kayttis;
    }

    @Override
    public String annaSiirto() {
        return kayttis.lueSyote("Pelaajan siirto");
    }
    
    @Override
    public void naytaSiirto(String siirto) {
    }

    @Override
    public void asetaToistenPelaajienSiirrot(List<String> siirrot) {
    }
}