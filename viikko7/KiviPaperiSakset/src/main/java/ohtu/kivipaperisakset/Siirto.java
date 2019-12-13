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
public class Siirto {
    public enum SIIRTO {
        KIVI,
        PAPERI,
        SAKSET
    };
    
    private SIIRTO tyyppi;
    
    public Siirto(Siirto.SIIRTO tyyppi) {
        this.tyyppi = tyyppi;
    }
    
    public Siirto(String merkkijonosta) {
        if ("k".equals(merkkijonosta) || SIIRTO.KIVI.toString().toLowerCase().equals(merkkijonosta.toLowerCase())) {
            tyyppi = SIIRTO.KIVI;
        } else if ("p".equals(merkkijonosta) || SIIRTO.PAPERI.toString().toLowerCase().equals(merkkijonosta.toLowerCase())) {
            tyyppi = SIIRTO.PAPERI;
        } else if ("s".equals(merkkijonosta) || SIIRTO.SAKSET.toString().toLowerCase().equals(merkkijonosta.toLowerCase())) {
            tyyppi = SIIRTO.SAKSET;
        } else {
            throw new IllegalArgumentException("Virheellinen siirron tyyppi.");
        }
    }
    
    public SIIRTO getTyyppi() {
        return tyyppi;
    }
    
    @Override
    public String toString() {
        return tyyppi.toString().toLowerCase();
    }
}
