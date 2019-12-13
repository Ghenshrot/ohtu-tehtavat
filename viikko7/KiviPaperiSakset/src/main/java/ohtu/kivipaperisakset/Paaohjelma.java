package ohtu.kivipaperisakset;

import ohtu.kivipaperisakset.kayttoliittyma.KonsoliKayttoliittyma;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;
import java.util.HashMap;
import java.util.Map;

public class Paaohjelma {
    
    public static void main(String[] args) {
        Kayttoliittyma kayttis = new KonsoliKayttoliittyma(System.out, System.in);
        
        while (true) {
            Map<String, Peli> pelityypit = new HashMap<>();
            pelityypit.put("a", Pelitehdas.peliIhmistaVastaan(kayttis));
            pelityypit.put("b", Pelitehdas.peliTekoalyaVastaan(kayttis));
            pelityypit.put("c", Pelitehdas.peliParannettuaTekoalyaVastaan(kayttis));
            pelityypit.put("d", Pelitehdas.peliKahtaSatunnaistaTekoalyaVastaan(kayttis));
            pelityypit.put("e", Pelitehdas.peliSatunnaistaMaaraaSatunnaistaTekoalyaVastaan(kayttis));

            kayttis.naytaTeksti("\nValitse pelataanko");
            pelityypit.keySet().forEach(k ->
                    kayttis.naytaTeksti(" (" + k + ") " + pelityypit.get(k).getNimi())
            );
            kayttis.naytaTeksti("muilla valinnoilla lopetataan");

            String vastaus = kayttis.lueSyote("");
            Peli peli = pelityypit.get(vastaus.trim());
            if (peli == null) {
                break;
            }
            peli.pelaa();
        }
    }
}
