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
public class Summa extends Komento {

    public Summa(Kayttoliittyma kayttoliittyma, Sovelluslogiikka sovellus) {
        super(kayttoliittyma, sovellus);
    }

    @Override
    protected void suoritaKomento(Kayttoliittyma kayttoliittyma, Sovelluslogiikka sovellus) {
        sovellus.plus(kayttoliittyma.getSyote());
    }
}
