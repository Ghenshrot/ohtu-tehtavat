/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public abstract class TietokonePelaaja implements Pelaaja {
    
    private final Kayttoliittyma kayttis;
    
    public TietokonePelaaja(Kayttoliittyma kayttis) {
        this.kayttis = kayttis;
    }
    
    @Override
    public void naytaSiirto(String siirto) {
        kayttis.naytaTeksti("Tietokone valitsi: " + siirto);
    }
}
