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
public class Kayttoliittyma {
    private TextField        tuloskentta;
    private TextField        syotekentta;
    private Button           nollaa;
    private Button           undo;
    private Sovelluslogiikka sovellus;
    
    public Kayttoliittyma(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta   = tuloskentta;
        this.syotekentta   = syotekentta;
        this.nollaa        = nollaa;
        this.undo          = undo;
        this.sovellus      = sovellus;
    }
    
    public int getSyote() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        return arvo;
    }
    
    public void paivitaTulos() {
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
