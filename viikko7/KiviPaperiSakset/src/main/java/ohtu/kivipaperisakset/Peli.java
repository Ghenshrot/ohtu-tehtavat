/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class Peli {
    private final String         nimi;
    private final Kayttoliittyma kayttis;
    private final Tuomari        tuomari;
    private final List<Pelaaja>  pelaajat;
    
    public Peli(String nimi, Kayttoliittyma kayttis, Tuomari tuomari, List<Pelaaja> pelaajat) {
        this.nimi     = nimi;
        this.kayttis  = kayttis;
        this.tuomari  = tuomari;
        this.pelaajat = pelaajat;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public void pelaa() {
        kayttis.naytaTeksti("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");

        while (true) {
            List<Siirto> siirrot = kysyPelaajienSiirrot();
            if (siirrot == null) {
                break;
            }
            
            tuomari.kirjaaSiirrot(siirrot);
            kayttis.naytaTeksti(tuomari.toString());
            kayttis.naytaTeksti("");

            for (int i = 0; i < pelaajat.size(); i++) {
                List<Siirto> muidenSiirrot = toistenPelaajienSiirrot(siirrot, i);
                pelaajat.get(i).asetaToistenPelaajienSiirrot(muidenSiirrot);
            }
        }

        kayttis.naytaTeksti("");
        kayttis.naytaTeksti("Kiitos!");
        kayttis.naytaTeksti(tuomari.toString());
    }
    
    private List<Siirto> kysyPelaajienSiirrot() {
        List<Siirto> siirrot = new ArrayList<>();
        for (Pelaaja p : pelaajat) {
            Siirto siirto = p.annaSiirto();
            if (siirto == null) {
                return null;
            }
            siirrot.add(siirto);
            p.naytaSiirto(siirto);
        }
        return siirrot;
    }
    
    private List<Siirto> toistenPelaajienSiirrot(List<Siirto> kaikkiSiirrot, int pelaajanIndeksi) {
        List<Siirto> muidenSiirrot = new ArrayList<>();
        for (int i = 0; i < pelaajat.size(); i++) {
            if (i == pelaajanIndeksi) {
                continue;
            }
            muidenSiirrot.add(kaikkiSiirrot.get(i));
        }
        return muidenSiirrot;
    }
}
