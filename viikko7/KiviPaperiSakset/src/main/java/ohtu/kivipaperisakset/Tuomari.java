package ohtu.kivipaperisakset;

// Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.

import java.util.List;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;

public class Tuomari {

    private final List<Pelaaja> pelaajat;
    private int                 tasapelit;

    public Tuomari(List<Pelaaja> pelaajat) {
        this.pelaajat  = pelaajat;
        this.tasapelit = 0;
    }

    public void kirjaaSiirrot(List<String> siirrot) {
        int voittaja = etsiVoittaja(siirrot);
        if (voittaja >= 0) {
            pelaajat.get(voittaja).lisaaPisteita(1);
        } else {
            tasapelit++;
        }
    }
    
    private int etsiVoittaja(List<String> siirrot) {
        for (int i = 0; i < siirrot.size(); i++) {
            boolean voittaa = true;
            for (int j = 0; j < siirrot.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (!ekaVoittaa(siirrot.get(i), siirrot.get(j))) {
                    voittaa = false;
                    break;
                }
            }
            if (voittaa) {
                return i;
            }
        }
        return -1;
    }

    // sisäinen metodi, jolla tarkastetaan tuliko tasapeli
    private boolean tasapeli(String eka, String toka) {
        if (eka.equals(toka)) {
            return true;
        }

        return false;
    }

    // sisäinen metodi joka tarkastaa voittaako eka pelaaja tokan
    private boolean ekaVoittaa(String eka, String toka) {
        if ("k".equals(eka) && "s".equals(toka)) {
            return true;
        } else if ("s".equals(eka) && "p".equals(toka)) {
            return true;
        } else if ("p".equals(eka) && "k".equals(toka)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String s = "Pelitilanne: ";
        for (int i = 0; i < pelaajat.size(); i++) {
            if (i > 0) {
                s += " - ";
            }
            s += pelaajat.get(i).getPisteet();
        }
        s += "\nTasapelit: " + tasapelit;
        return s;
    }
}