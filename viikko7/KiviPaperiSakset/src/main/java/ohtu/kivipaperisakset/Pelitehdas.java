/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;
import ohtu.kivipaperisakset.pelaaja.PelaajaTehdas;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class Pelitehdas {
    
    private static Random random = new Random();
    
    public static Peli peliIhmistaVastaan(Kayttoliittyma kayttis) {
        return luoPeli("ihmistä vastaan", kayttis, 
                PelaajaTehdas.annaIhmispelaaja(kayttis, "A"),
                PelaajaTehdas.annaIhmispelaaja(kayttis, "B")
        );
    }

    public static Peli peliTekoalyaVastaan(Kayttoliittyma kayttis) {
        return luoPeli("tekoälyä vastaan", kayttis,
                PelaajaTehdas.annaIhmispelaaja(kayttis, ""),
                PelaajaTehdas.annaTekoalyPelaaja(kayttis)
        );
    }
    
    public static Peli peliParannettuaTekoalyaVastaan(Kayttoliittyma kayttis) {
        return luoPeli("parannettua tekoälyä vastaan", kayttis,
                PelaajaTehdas.annaIhmispelaaja(kayttis, ""),
                PelaajaTehdas.annaParempiTekoAlyPelaaja(kayttis)
        );
    }

    public static Peli peliKahtaSatunnaistaTekoalyaVastaan(Kayttoliittyma kayttis) {
        return peliSatunnaisiaTekoalyjaVastaan("kahta satunnaista tekoälyä vastaan", kayttis, 2);
    }

    public static Peli peliSatunnaistaMaaraaSatunnaistaTekoalyaVastaan(Kayttoliittyma kayttis) {
        return peliSatunnaisiaTekoalyjaVastaan("satunnaista määrää (3..10) satunnaista tekoälyä vastaan", kayttis, 3 + random.nextInt(7));
    }

    public static Peli peliSatunnaisiaTekoalyjaVastaan(String nimi, Kayttoliittyma kayttis, int tekoalypelaajienLkm) {
        Pelaaja[] pelaajat = new Pelaaja[1 + tekoalypelaajienLkm];
        pelaajat[0] = PelaajaTehdas.annaIhmispelaaja(kayttis, "");
        for (int i = 0; i < tekoalypelaajienLkm; i++) {
            if (random.nextBoolean()) {
                pelaajat[1 + i] = PelaajaTehdas.annaTekoalyPelaaja(kayttis);
            } else {
                pelaajat[1 + i] = PelaajaTehdas.annaParempiTekoAlyPelaaja(kayttis);
            }
        }
        return luoPeli(nimi, kayttis, pelaajat);
    }
 

    private static Peli luoPeli(String nimi, Kayttoliittyma kayttis, Pelaaja... pelaajat) {
        List<Pelaaja> pelaajalista = new ArrayList<>();
        pelaajalista.addAll(Arrays.asList(pelaajat));
        Tuomari tuomari = new Tuomari(pelaajalista);
        return new Peli(nimi, kayttis, tuomari, pelaajalista);
    }
}
