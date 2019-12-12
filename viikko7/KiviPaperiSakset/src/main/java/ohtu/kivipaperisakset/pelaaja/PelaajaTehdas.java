/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.pelaaja;

import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class PelaajaTehdas {
    public static Pelaaja annaIhmispelaaja(Kayttoliittyma kayttis, String nimi) {
        return new IhmisPelaaja(kayttis, nimi);
    }
    
    public static Pelaaja annaTekoalyPelaaja(Kayttoliittyma kayttis) {
        return new TekoalyPelaaja(kayttis);
    }
    
    public static Pelaaja annaParempiTekoAlyPelaaja(Kayttoliittyma kayttis) {
        return new ParempiTekoalyPelaaja(kayttis, 20);
    }
}
