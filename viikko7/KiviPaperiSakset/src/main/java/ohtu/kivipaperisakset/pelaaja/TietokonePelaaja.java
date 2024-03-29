/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.pelaaja;

import ohtu.kivipaperisakset.Siirto;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public abstract class TietokonePelaaja extends Pelaaja {
    
    public TietokonePelaaja(Kayttoliittyma kayttis) {
        super(kayttis, "Tietokone");
    }
    
    @Override
    public void naytaSiirto(Siirto siirto) {
        getKayttoliittyma().naytaTeksti(this.getNimi() + " valitsi: " + siirto);
    }
}
