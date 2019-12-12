package ohtu.kivipaperisakset;

import java.util.ArrayList;
import java.util.List;

public class Paaohjelma {

    public static void main(String[] args) {

        Kayttoliittyma kayttis = new KonsoliKayttoliittyma(System.out, System.in);
        
        while (true) {
            kayttis.naytaTeksti(
                    "\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = kayttis.lueSyote("");
            Tuomari tuomari = new Tuomari();
            List<Pelaaja> pelaajat = new ArrayList<>();
            if (vastaus.endsWith("a")) {
                pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, "A"));
                pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, "B"));
            } else if (vastaus.endsWith("b")) {
                pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, ""));
                pelaajat.add(KiviPaperiSaksetPeli.annaTekoalyPelaaja(kayttis));
            } else if (vastaus.endsWith("c")) {
                pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, ""));
                pelaajat.add(KiviPaperiSaksetPeli.annaParempiTekoAlyPelaaja(kayttis));
            } else {
                break;
            }
            
            KiviPaperiSaksetPeli peli = new KiviPaperiSaksetPeli(kayttis, tuomari, pelaajat);
            peli.pelaa();
        }
    }
}
