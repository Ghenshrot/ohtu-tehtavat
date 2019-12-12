/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class KiviPaperitSaksetPeli {
    public static Pelaaja annaIhmispelaaja(Kayttoliittyma kayttis) {
        return new IhmisPelaaja(kayttis);
    }
    
    public static Pelaaja annaTekoalyPelaaja(Kayttoliittyma kayttis) {
        return new TekoalyPelaaja(kayttis);
    }
    
    public static Pelaaja annaParempiTekoAlyPelaaja(Kayttoliittyma kayttis) {
        return new ParempiTekoalyPelaaja(kayttis, 20);
    }

    
    private final Tuomari       tuomari;
    private final List<Pelaaja> pelaajat;
    
    public KiviPaperitSaksetPeli(Tuomari tuomari, List<Pelaaja> pelaajat) {
        this.tuomari = tuomari;
        this.pelaajat = pelaajat;
    }
    
    public void pelaa() {
        while (true) {
            List<String> siirrot = new ArrayList<>();
            boolean lopeta = false;
            for (Pelaaja p : pelaajat) {
                String siirto = p.annaSiirto();
                if (!onkoOkSiirto(siirto)) {
                    lopeta = true;
                    break;
                }
                siirrot.add(siirto);
                p.naytaSiirto(siirto);
            }
            if (lopeta) {
                break;
            }
            
            tuomari.kirjaaSiirto(siirrot.get(0), siirrot.get(1));
            System.out.println(tuomari);
            System.out.println();

            for (int i = 0; i < pelaajat.size(); i++) {
                List<String> muidenSiirrot = muidenPelaajienSiirrot(siirrot, i);
                pelaajat.get(i).asetaToistenPelaajienSiirrot(muidenSiirrot);
            }
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
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
