package ohtu.kivipaperisakset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paaohjelma {

    private interface Pelityyppi {
        public void alusta(Kayttoliittyma kayttis, List<Pelaaja> pelaajat);
    }
    
    private static boolean juoksee;
    
    public static void main(String[] args) {
        Map<String, Pelityyppi> pelityypit = new HashMap<>();
        pelityypit.put("a", Paaohjelma::alustaPeliIhmistaVastaan);
        pelityypit.put("b", Paaohjelma::alustaPeliTekoalyaVastaan);
        pelityypit.put("c", Paaohjelma::alustaPeliParannettuaTekoalyaVastaan);

        Kayttoliittyma kayttis = new KonsoliKayttoliittyma(System.out, System.in);
        
        juoksee = true;
        while (juoksee) {
            kayttis.naytaTeksti(
                    "\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = kayttis.lueSyote("");
            Pelityyppi pelityyppi = pelityypit.getOrDefault(vastaus.trim(), Paaohjelma::alustaTuntematonPelityyppi);

            List<Pelaaja> pelaajat = new ArrayList<>();
            pelityyppi.alusta(kayttis, pelaajat);
            if (!juoksee) {
                break;
            }
            
            Tuomari tuomari = new Tuomari();
            KiviPaperiSaksetPeli peli = new KiviPaperiSaksetPeli(kayttis, tuomari, pelaajat);
            peli.pelaa();
        }
    }
    
    private static void alustaPeliIhmistaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, "A"));
        pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, "B"));
    }

    private static void alustaPeliTekoalyaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, ""));
        pelaajat.add(KiviPaperiSaksetPeli.annaTekoalyPelaaja(kayttis));
    }
    
    private static void alustaPeliParannettuaTekoalyaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        pelaajat.add(KiviPaperiSaksetPeli.annaIhmispelaaja(kayttis, ""));
        pelaajat.add(KiviPaperiSaksetPeli.annaParempiTekoAlyPelaaja(kayttis));
    }

    private static void alustaTuntematonPelityyppi(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        juoksee = false;
    }
}
