
package ohtu.intjoukkosovellus;

public class IntJoukko {

    // Oletusarvot constructoreille:
    private final static int OLETUSKAPASITEETTI = 5;
    private final static int OLETUSKASVATUS     = 5;

    private int   kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;       // Joukon luvut säilytetään taulukon alkupäässä pienimmästä suurimpaan. 
    private int   alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int alkukapasiteetti) {
        this(alkukapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int alkukapasiteetti, int kasvatuskoko) {
        if (alkukapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Negatiivinen alkukapasitteetti");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Negatiivinen kasvatuskoko");
        }
        alkiot = new int[alkukapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public IntJoukko(IntJoukko alkuperainen) {
        this(alkuperainen.alkiot.length, alkuperainen.kasvatuskoko);
        alkioidenLkm = alkuperainen.alkioidenLkm;
        System.arraycopy(alkuperainen.alkiot, 0, alkiot, 0, alkioidenLkm);
    }
    
    private void lisaaTilaa() {
        int[] vanhat = alkiot;
        alkiot = new int[alkiot.length + kasvatuskoko];
        System.arraycopy(vanhat, 0, alkiot, 0, alkioidenLkm);
    }
    
    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        if (alkioidenLkm >= alkiot.length) {
            lisaaTilaa();
        }
        lisaaIlmanTarkistuksia(luku);
        return true;
    }
    
    private void lisaaIlmanTarkistuksia(int luku) {
        alkiot[alkioidenLkm] = luku;
        alkioidenLkm++;
    }
    
    public boolean kuuluu(int luku) {
        return etsi(luku) >= 0;
    }
    
    private int etsi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (alkiot[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        return poistaKohdasta(etsi(luku));
    }
    
    private boolean poistaKohdasta(int indeksi) {
        if (indeksi < 0 || indeksi >= alkioidenLkm) {
            return false;
        }
        alkioidenLkm--;
        if (alkioidenLkm > 0) {
            System.arraycopy(alkiot, indeksi + 1, alkiot, indeksi, alkioidenLkm - indeksi);
        }
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            if (i > 0) {
                tuotos += ", ";
            }
            tuotos += alkiot[i];
        }
        return tuotos + "}";
    }

    public int[] toIntArray() {
        return java.util.Arrays.copyOfRange(alkiot, 0, alkioidenLkm);
    }
    
    
    public void yhdiste(IntJoukko toinen) {
        for (int i = 0; i < toinen.alkioidenLkm; i++) {
            lisaa(toinen.alkiot[i]);
        }
    }
    
    public void leikkaus(IntJoukko toinen) {
        int vanhaLkm = alkioidenLkm;
        alkioidenLkm = 0;
        for (int i = 0; i < vanhaLkm; i++) {
            if (toinen.kuuluu(alkiot[i])) {
                lisaaIlmanTarkistuksia(alkiot[i]);
            }
        }
    }
    
    public void erotus(IntJoukko toinen) {
        for (int i = 0; i < toinen.alkioidenLkm; i++) {
            poista(toinen.alkiot[i]);
        }
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko uusi = new IntJoukko(a);
        uusi.yhdiste(b);
        return uusi;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko uusi = new IntJoukko(a);
        uusi.leikkaus(b);
        return uusi;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko uusi = new IntJoukko(a);
        uusi.erotus(b);
        return uusi;
    }
}
