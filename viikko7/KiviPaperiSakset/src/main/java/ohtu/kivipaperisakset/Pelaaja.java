/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.List;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public interface Pelaaja {
    public String annaSiirto();
    public void   naytaSiirto(String siirto);
    public void   asetaToistenPelaajienSiirrot(List<String> siirrot);
}
