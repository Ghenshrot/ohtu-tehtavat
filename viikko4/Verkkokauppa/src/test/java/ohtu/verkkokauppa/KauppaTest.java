package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    private Pankki            pankki;
    private Viitegeneraattori viite;
    private Varasto           varasto;
    private Kauppa            kauppa;

    private final int viiteNumero      = 42;
    private final int maidonId         = 1;
    private final int maidonSaldo      = 10;
    private final int maidonHinta      = 5;
    private final int kossunId         = 8;
    private final int kossunSaldo      = 30;
    private final int kossunHinta      = 9;
    private final int kaljanId         = 28;
    private final int kaljanSaldo      = 0;
    private final int kaljanHinta      = 3;
    private final String tilinOmistaja = "pekka";
    private final String tiliNumero    = "12345";

    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        
        viite = mock(Viitegeneraattori.class);
        int i = 0;
        when(viite.uusi())
                .thenReturn(viiteNumero + i++)
                .thenReturn(viiteNumero + i++)
                .thenReturn(viiteNumero + i++)
                .thenReturn(viiteNumero + i++)
                .thenReturn(viiteNumero + i++)
                .thenReturn(viiteNumero + i++)
                .thenReturn(viiteNumero + i++);

        varasto = mock(Varasto.class);
        
        when(varasto.saldo(maidonId)).thenReturn(maidonSaldo); 
        when(varasto.haeTuote(maidonId)).thenReturn(new Tuote(maidonId, "maito", maidonHinta));

        when(varasto.saldo(kossunId)).thenReturn(kossunSaldo); 
        when(varasto.haeTuote(kossunId)).thenReturn(new Tuote(kossunId, "koskenkorva", kossunHinta));
        
        kauppa = new Kauppa(varasto, pankki, viite);              
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(maidonId);
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(maidonId);
        kauppa.tilimaksu(tilinOmistaja, tiliNumero);

        verify(pankki).tilisiirto(tilinOmistaja, viiteNumero, tiliNumero, kauppa.getKaupanTili(), maidonHinta);
    }
    
    @Test
    public void tuplaOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(maidonId);
        kauppa.lisaaKoriin(kossunId);
        kauppa.tilimaksu(tilinOmistaja, tiliNumero);

        verify(pankki).tilisiirto(tilinOmistaja, viiteNumero, tiliNumero, kauppa.getKaupanTili(), maidonHinta + kossunHinta);
    }
    
    @Test
    public void tuplaOstoksenSamaaTuotettaPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(kossunId);
        kauppa.lisaaKoriin(kossunId);
        kauppa.tilimaksu(tilinOmistaja, tiliNumero);

        verify(pankki).tilisiirto(tilinOmistaja, viiteNumero, tiliNumero, kauppa.getKaupanTili(), kossunHinta + kossunHinta);
    }

    @Test
    public void tuplaOstoksenToisenTuotteenPuuttuessaPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(kossunId);
        kauppa.lisaaKoriin(kaljanId);
        kauppa.tilimaksu(tilinOmistaja, tiliNumero);

        verify(pankki).tilisiirto(tilinOmistaja, viiteNumero, tiliNumero, kauppa.getKaupanTili(), kossunHinta);
    }
    
    @Test
    public void edellisenOstoksenHintaEiNayUudenOstoksenHinnassa() {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(maidonId);
        kauppa.tilimaksu(tilinOmistaja, tiliNumero);
        
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(maidonHinta));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(kossunId);
        kauppa.tilimaksu(tilinOmistaja, tiliNumero);

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(kossunHinta));
        
    }
    
    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleMaksutapahtumalle() {
        for (int i = 0; i < 3; i++) {
            kauppa.aloitaAsiointi();
            kauppa.lisaaKoriin(kossunId);
            kauppa.tilimaksu(tilinOmistaja, tiliNumero);

            verify(pankki).tilisiirto(anyString(), eq(viiteNumero + i), anyString(), anyString(), anyInt());
        }
    }
}

