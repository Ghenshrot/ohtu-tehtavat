/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.List;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public abstract class Pelaaja {
    private final Kayttoliittyma kayttis;
    private final String         nimi;
    
    public Pelaaja(Kayttoliittyma kayttis, String nimi) {
        this.kayttis = kayttis;
        this.nimi    = nimi;
    }
    public abstract String annaSiirto();
    
    public void naytaSiirto(String siirto) {
    }
    
    public void asetaToistenPelaajienSiirrot(List<String> siirrot) {
    }
    
    protected Kayttoliittyma getKayttoliittyma() {
        return kayttis;
    }
    
    public String getNimi() {
        return nimi;
    }
}
