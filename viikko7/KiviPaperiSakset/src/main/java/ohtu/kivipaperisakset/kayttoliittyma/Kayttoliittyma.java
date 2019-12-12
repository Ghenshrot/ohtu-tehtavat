/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset.kayttoliittyma;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public interface Kayttoliittyma {
    public String lueSyote(String otsikko);
    public void   naytaTeksti(String teksti);
}
