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
    private TextField        tuloskentta;
    private TextField        syotekentta;
    private Button           nollaa;
    private Button           undo;
    private Sovelluslogiikka sovellus;
    private int              edellinenArvo;
    
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta   = tuloskentta;
        this.syotekentta   = syotekentta;
        this.nollaa        = nollaa;
        this.undo          = undo;
        this.sovellus      = sovellus;
        this.edellinenArvo = 0;
    }
    
    public abstract void suorita();
    
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(edellinenArvo);
        tulos();
    }
    
    protected Sovelluslogiikka getSovellus() {
        return sovellus;
    }
    
    protected void talletaUndoaVarten() {
        this.edellinenArvo = sovellus.tulos();
    }
    
    private int getLuku(TextField kentta) {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(kentta.getText());
        } catch (Exception e) {
        }
        return arvo;
    }
    
    // todo: refactor to somewhere else:
    protected int getLuku1() {
        return getLuku(tuloskentta);
    }
    
    protected int getLuku2() {
        return getLuku(syotekentta);
    }
    
    protected void tulos() {
        int laskunTulos = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        
        if ( laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }
}
