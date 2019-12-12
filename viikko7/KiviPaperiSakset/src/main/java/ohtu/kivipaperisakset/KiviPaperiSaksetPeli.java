/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class KiviPaperiSaksetPeli {
    public static Pelaaja annaIhmispelaaja(Kayttoliittyma kayttis, String nimi) {
        return new IhmisPelaaja(kayttis, nimi);
    }
    
    public static Pelaaja annaTekoalyPelaaja(Kayttoliittyma kayttis) {
        return new TekoalyPelaaja(kayttis);
    }
    
    public static Pelaaja annaParempiTekoAlyPelaaja(Kayttoliittyma kayttis) {
        return new ParempiTekoalyPelaaja(kayttis, 20);
    }

    
    private final Kayttoliittyma kayttis;
    private final Tuomari        tuomari;
    private final List<Pelaaja>  pelaajat;
    
    public KiviPaperiSaksetPeli(Kayttoliittyma kayttis, Tuomari tuomari, List<Pelaaja> pelaajat) {
        this.kayttis  = kayttis;
        this.tuomari  = tuomari;
        this.pelaajat = pelaajat;
    }
    
    public void pelaa() {
        kayttis.naytaTeksti("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");

        while (true) {
            List<String> siirrot = kysyPelaajienSiirrot();
            if (siirrot == null) {
                break;
            }
            
            tuomari.kirjaaSiirto(siirrot.get(0), siirrot.get(1));
            kayttis.naytaTeksti(tuomari.toString());
            kayttis.naytaTeksti("");

            for (int i = 0; i < pelaajat.size(); i++) {
                List<String> muidenSiirrot = muidenPelaajienSiirrot(siirrot, i);
                pelaajat.get(i).asetaToistenPelaajienSiirrot(muidenSiirrot);
            }
        }

        kayttis.naytaTeksti("");
        kayttis.naytaTeksti("Kiitos!");
        kayttis.naytaTeksti(tuomari.toString());
    }
    
    private List<String> kysyPelaajienSiirrot() {
        List<String> siirrot = new ArrayList<>();
        for (Pelaaja p : pelaajat) {
            String siirto = p.annaSiirto();
            if (!onkoOkSiirto(siirto)) {
                return null;
            }
            siirrot.add(siirto);
            p.naytaSiirto(siirto);
        }
        return siirrot;
    }
    
    private List<String> muidenPelaajienSiirrot(List<String> kaikkiSiirrot, int pelaajanIndeksi) {
        List<String> muidenSiirrot = new ArrayList<>();
        for (int i = 0; i < pelaajat.size(); i++) {
            if (i == pelaajanIndeksi) {
                continue;
            }
            muidenSiirrot.add(kaikkiSiirrot.get(i));
        }
        return muidenSiirrot;
    }

    private boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
