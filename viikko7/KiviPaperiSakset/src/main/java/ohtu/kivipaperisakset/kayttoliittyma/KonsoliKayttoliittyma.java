/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.kayttoliittyma;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class KonsoliKayttoliittyma implements Kayttoliittyma {
    
    private final PrintStream tulostusVuo;
    private final Scanner     lukija;
    
    public KonsoliKayttoliittyma(PrintStream tulostusVuo, InputStream syoteVuo) {
        this.tulostusVuo = tulostusVuo;
        this.lukija      = new Scanner(syoteVuo);
    }

    @Override
    public String lueSyote(String otsikko) {
        tulostusVuo.print(otsikko + ": ");
        return lukija.nextLine();
    }

    @Override
    public void naytaTeksti(String teksti) {
        tulostusVuo.println(teksti);
    }
}
