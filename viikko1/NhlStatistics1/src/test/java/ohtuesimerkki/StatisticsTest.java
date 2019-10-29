/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Joni Yrjänä <joni.yrjana@helsinki.fi>
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchFindsExistingPlayer() {
        Player p = stats.search("Kurri");
        assertNotNull(p);
    }
    
    @Test
    public void searchDoesNotFindNonExistingPlayer() {
        Player p = stats.search("Karri");
        assertNull(p);
    }
    
    @Test
    public void teamFindsPlayers() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
    }
    
    @Test
    public void teamFindsNoPlayersForNonExistingTeam() {
        List<Player> players = stats.team("DEM");
        assertEquals(0, players.size());
    }
    
    @Test
    public void topScorersReturnsTheTopScorers() {
        List<Player> players = stats.topScorers(5);
        assertEquals(5, players.size());
        assertTrue(players.get(0).getName().equals("Gretzky"));
    }
    
    @Test
    public void topScorersReturnsCorrectAmount() {
        List<Player> players = stats.topScorers(3);
        assertEquals(3, players.size());
    }
    
    @Test
    public void topScorersReturnsTheTopScorersWithLargerParameter() {
        List<Player> players = stats.topScorers(999);
        assertEquals(5, players.size());
    }
    
    @Test
    public void topScorersReturnsEmptyListWithNegativeAmount() {
        List<Player> players = stats.topScorers(-2);
        assertEquals(0, players.size());
    }
}
