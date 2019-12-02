/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public abstract class Komento {
    private final Kayttoliittyma   kayttoliittyma;
    private final Sovelluslogiikka sovellus;
    private int                    edellinenArvo;
    
    public Komento(Kayttoliittyma kayttoliittyma, Sovelluslogiikka sovellus) {
        this.kayttoliittyma = kayttoliittyma;
        this.sovellus       = sovellus;
        this.edellinenArvo  = 0;
    }
    
    public void suorita() {
        this.edellinenArvo = sovellus.tulos();
        suoritaKomento(kayttoliittyma, sovellus);
        kayttoliittyma.paivitaTulos();
    }

    protected abstract void suoritaKomento(Kayttoliittyma kayttoliittyma, Sovelluslogiikka sovellus);
    
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(edellinenArvo);
        kayttoliittyma.paivitaTulos();
    }
}
