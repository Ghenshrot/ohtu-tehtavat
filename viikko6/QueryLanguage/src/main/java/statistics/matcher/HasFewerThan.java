/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class HasFewerThan implements Matcher {

    private final int    value;
    private final String fieldName;

    public HasFewerThan(int value, String category) {
        this.value = value;
        fieldName = Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
    }

    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod("get" + fieldName);
            int playersValue = (Integer)method.invoke(p);
            return playersValue < value;
            
        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }
    }    
}