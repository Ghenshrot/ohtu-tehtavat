package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private final Button           undo;
    private final Sovelluslogiikka sovellus;
    
    private Map<Button, Komento> komennot;
    private Komento              edellinen = null;
 

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        Kayttoliittyma kayttis = new Kayttoliittyma(tuloskentta, syotekentta, nollaa, undo, sovellus);
        komennot = new HashMap<>();
        komennot.put(plus,   new Summa(kayttis,  sovellus) );
        komennot.put(miinus, new Erotus(kayttis, sovellus) );
        komennot.put(nollaa, new Nollaa(kayttis, sovellus) );
    }
    
    @Override
    public void handle(Event event) {
        if ( event.getTarget() != undo ) {
            Komento komento = komennot.get((Button)event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else {
            if (edellinen != null) {
                edellinen.peru();
                edellinen = null;
            }
        }                  
    }
}
