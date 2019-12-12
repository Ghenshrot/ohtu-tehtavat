package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.kayttoliittyma.KonsoliKayttoliittyma;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ohtu.kivipaperisakset.pelaaja.PelaajaTehdas;

public class Paaohjelma {

    private interface Pelityyppi {
        public boolean alusta(Kayttoliittyma kayttis, List<Pelaaja> pelaajat);
    }
    
    public static void main(String[] args) {
        Map<String, Pelityyppi> pelityypit = new HashMap<>();
        pelityypit.put("a", Paaohjelma::alustaPeliIhmistaVastaan);
        pelityypit.put("b", Paaohjelma::alustaPeliTekoalyaVastaan);
        pelityypit.put("c", Paaohjelma::alustaPeliParannettuaTekoalyaVastaan);
        pelityypit.put("d", Paaohjelma::alustaPeliKahtaSatunnaistaTekoalyaVastaan);
        pelityypit.put("e", Paaohjelma::alustaPeliSatunnaistaMaaraaSatunnaistaTekoalyaVastaan);

        Kayttoliittyma kayttis = new KonsoliKayttoliittyma(System.out, System.in);
        
        while (true) {
            kayttis.naytaTeksti(
                    "\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\n (d) kahta satunnaista tekoälyä vastaan"
                    + "\n (e) satunnaista määrää (3..10) satunnaista tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = kayttis.lueSyote("");
            Pelityyppi pelityyppi = pelityypit.getOrDefault(vastaus.trim(), Paaohjelma::alustaTuntematonPelityyppi);

            List<Pelaaja> pelaajat = new ArrayList<>();
            if (!pelityyppi.alusta(kayttis, pelaajat)) {
                break;
            }
            
            Tuomari tuomari = new Tuomari(pelaajat);
            KiviPaperiSaksetPeli peli = new KiviPaperiSaksetPeli(kayttis, tuomari, pelaajat);
            peli.pelaa();
        }
    }
    
    private static boolean alustaPeliIhmistaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        pelaajat.add(PelaajaTehdas.annaIhmispelaaja(kayttis, "A"));
        pelaajat.add(PelaajaTehdas.annaIhmispelaaja(kayttis, "B"));
        return true;
    }

    private static boolean alustaPeliTekoalyaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        pelaajat.add(PelaajaTehdas.annaIhmispelaaja(kayttis, ""));
        pelaajat.add(PelaajaTehdas.annaTekoalyPelaaja(kayttis));
        return true;
    }
    
    private static boolean alustaPeliParannettuaTekoalyaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        pelaajat.add(PelaajaTehdas.annaIhmispelaaja(kayttis, ""));
        pelaajat.add(PelaajaTehdas.annaParempiTekoAlyPelaaja(kayttis));
        return true;
    }

    private static boolean alustaPeliKahtaSatunnaistaTekoalyaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        return alustaPeliSatunnaisiaTekoalyjaVastaan(kayttis, pelaajat, 2);
    }

    private static boolean alustaPeliSatunnaistaMaaraaSatunnaistaTekoalyaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        Random r = new Random();
        return alustaPeliSatunnaisiaTekoalyjaVastaan(kayttis, pelaajat, 3 + r.nextInt(7));
    }
 
    private static boolean alustaPeliSatunnaisiaTekoalyjaVastaan(Kayttoliittyma kayttis, List<Pelaaja> pelaajat, int tietokonepelaajienLkm) {
        pelaajat.add(PelaajaTehdas.annaIhmispelaaja(kayttis, ""));
        Random r = new Random();
        for (int i = 0; i < tietokonepelaajienLkm; i++) {
            if (r.nextBoolean()) {
                pelaajat.add(PelaajaTehdas.annaTekoalyPelaaja(kayttis));
            } else {
                pelaajat.add(PelaajaTehdas.annaParempiTekoAlyPelaaja(kayttis));
            }
        }
        return true;
    }
    
    private static boolean alustaTuntematonPelityyppi(Kayttoliittyma kayttis, List<Pelaaja> pelaajat) {
        return false;
    }
}
