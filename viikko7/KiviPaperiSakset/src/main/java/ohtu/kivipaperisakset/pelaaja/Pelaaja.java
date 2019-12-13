/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.pelaaja;

import java.util.List;
import ohtu.kivipaperisakset.Siirto;
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public abstract class Pelaaja {
    private final Kayttoliittyma kayttis;
    private final String         nimi;
    private int                  pisteet;
    
    public Pelaaja(Kayttoliittyma kayttis, String nimi) {
        this.kayttis = kayttis;
        this.nimi    = nimi;
        this.pisteet = 0;
    }
    public abstract Siirto annaSiirto();
    
    public void naytaSiirto(Siirto siirto) {
    }
    
    public void asetaToistenPelaajienSiirrot(List<Siirto> siirrot) {
    }
    
    protected final Kayttoliittyma getKayttoliittyma() {
        return kayttis;
    }
    
    public final String getNimi() {
        return nimi;
    }
    
    public final void lisaaPisteita(int maara) {
        this.pisteet += maara;
    }
    
    public final int getPisteet() {
        return this.pisteet;
    }
}
