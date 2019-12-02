/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public abstract class Komento {
    private Kayttoliittyma   kayttoliittyma;
    private Sovelluslogiikka sovellus;
    private int              edellinenArvo;
    
    public Komento(Kayttoliittyma kayttoliittyma, Sovelluslogiikka sovellus) {
        this.kayttoliittyma = kayttoliittyma;
        this.sovellus       = sovellus;
        this.edellinenArvo  = 0;
    }
    
    public abstract void suorita();
    
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(edellinenArvo);
        kayttoliittyma.paivitaTulos();
    }

    protected Kayttoliittyma getKayttoliittyma() {
        return kayttoliittyma;
    }
    
    protected Sovelluslogiikka getSovellus() {
        return sovellus;
    }
    
    protected void talletaUndoaVarten() {
        this.edellinenArvo = sovellus.tulos();
    }
}
