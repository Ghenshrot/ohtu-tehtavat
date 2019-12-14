package ohtu.kivipaperisakset;

import java.util.List;
import ohtu.kivipaperisakset.pelaaja.Pelaaja;

public class Tuomari {

    private final List<Pelaaja> pelaajat;
    private int                 tasapelit;

    public Tuomari(List<Pelaaja> pelaajat) {
        this.pelaajat  = pelaajat;
        this.tasapelit = 0;
    }

    public void kirjaaSiirrot(List<Siirto> siirrot) {
        int voittaja = etsiVoittaja(siirrot);
        if (voittaja >= 0) {
            pelaajat.get(voittaja).lisaaPisteita(1);
        } else {
            tasapelit++;
        }
    }
    
    private int etsiVoittaja(List<Siirto> siirrot) {
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

    private boolean ekaVoittaa(Siirto eka, Siirto toka) {
        Siirto.SIIRTO seka  = eka.getTyyppi();
        Siirto.SIIRTO stoka = toka.getTyyppi();
        
        Siirto.SIIRTO kivi   = Siirto.SIIRTO.KIVI;
        Siirto.SIIRTO paperi = Siirto.SIIRTO.PAPERI;
        Siirto.SIIRTO sakset = Siirto.SIIRTO.SAKSET;
        
        if (seka == kivi && stoka == sakset) {
            return true;
        } else if (seka == sakset && stoka == paperi) {
            return true;
        } else if (seka == paperi && stoka == kivi) {
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