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
                pelaajat.add(KiviPaperitSaksetPeli.annaIhmispelaaja(kayttis));
                pelaajat.add(KiviPaperitSaksetPeli.annaIhmispelaaja(kayttis));
            } else if (vastaus.endsWith("b")) {
                pelaajat.add(KiviPaperitSaksetPeli.annaIhmispelaaja(kayttis));
                pelaajat.add(KiviPaperitSaksetPeli.annaTekoalyPelaaja(kayttis));
            } else if (vastaus.endsWith("c")) {
                pelaajat.add(KiviPaperitSaksetPeli.annaIhmispelaaja(kayttis));
                pelaajat.add(KiviPaperitSaksetPeli.annaParempiTekoAlyPelaaja(kayttis));
            } else {
                break;
            }
            
            kayttis.naytaTeksti("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");

            KiviPaperitSaksetPeli peli = new KiviPaperitSaksetPeli(tuomari, pelaajat);
            peli.pelaa();
        }
    }
}
