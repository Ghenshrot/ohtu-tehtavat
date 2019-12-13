package ohtu.kivipaperisakset.pelaaja;

// "Muistava tekoäly"
import ohtu.kivipaperisakset.kayttoliittyma.Kayttoliittyma;
import java.util.List;
import ohtu.kivipaperisakset.Siirto;

public class ParempiTekoalyPelaaja extends TietokonePelaaja {

    private final Siirto.SIIRTO[] muisti;
    private int vapaaMuistiIndeksi;
    // Seuraavat kuvaavat kyseisten siirtojen lukumääriä, lasketaan laskeMuistinSeuraavatSiirrot():ssa:
    private int kivet;
    private int paperit;
    private int sakset;
    

    public ParempiTekoalyPelaaja(Kayttoliittyma kayttis, int muistinKoko) {
        super(kayttis);
        muisti = new Siirto.SIIRTO[muistinKoko];
        vapaaMuistiIndeksi = 0;
        kivet   = 0;
        paperit = 0;
        sakset  = 0;
    }

    @Override
    public Siirto annaSiirto() {
        if (vapaaMuistiIndeksi == 0 || vapaaMuistiIndeksi == 1) {
            return new Siirto(Siirto.SIIRTO.KIVI);
        }
        
        // Tehdään siirron valinta esimerkiksi seuraavasti;
        // - jos kiviä eniten, annetaan aina paperi
        // - jos papereita eniten, annetaan aina sakset
        // muulloin annetaan aina kivi
        laskeMuistinSeuraavatSiirrot(muisti[vapaaMuistiIndeksi - 1]);
        
        if (kivet > paperit && kivet > sakset) {
            return new Siirto(Siirto.SIIRTO.PAPERI);
        } else if (paperit > kivet && paperit > sakset) {
            return new Siirto(Siirto.SIIRTO.SAKSET);
        } else {
            return new Siirto(Siirto.SIIRTO.KIVI);
        }

        // Tehokkaampiakin tapoja löytyy, mutta niistä lisää 
        // Johdatus Tekoälyyn kurssilla!
    }

    @Override
    public void asetaToistenPelaajienSiirrot(List<Siirto> siirrot) {
        siirrot.forEach(s ->
                lisaaSiirto(s.getTyyppi())
        );
    }
    
    private void lisaaSiirto(Siirto.SIIRTO siirto) {
        // jos muisti täyttyy, unohdetaan viimeinen alkio
        if (vapaaMuistiIndeksi == muisti.length) {
            for (int i = 1; i < muisti.length; i++) {
                muisti[i - 1] = muisti[i];
            }

            vapaaMuistiIndeksi--;
        }

        muisti[vapaaMuistiIndeksi] = siirto;
        vapaaMuistiIndeksi++;
    }
    
    private void laskeMuistinSeuraavatSiirrot(Siirto.SIIRTO viimeisinSiirto) {
        kivet   = 0;
        paperit = 0;
        sakset  = 0;

        for (int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if (viimeisinSiirto == muisti[i]) {
                Siirto.SIIRTO seuraava = muisti[i + 1];

                switch (seuraava) {
                    case KIVI:   kivet++;   break;
                    case PAPERI: paperit++; break;
                    case SAKSET: sakset++;  break;
                }
            }
        }
    }
}
