package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url =
                // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
                "https://nhl27112019.herokuapp.com/players.txt"
                // ajan tasalla olevat tilastot osoitteessa
                //"https://nhlstatisticsforohtu.herokuapp.com/players.txt"
                ;

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
        
        Matcher m;
        if (false) {
            m = new And(new HasAtLeast(5, "goals"),
                        new HasAtLeast(5, "assists"),
                        new PlaysIn("PHI")
            );
        } else if (false) {
            m = new And( 
                new Not( new HasAtLeast(1, "goals") ), 
                new PlaysIn("NYR")
            );
        } else if (false) {
            m = new And( 
                new HasFewerThan(1, "goals"), 
                new PlaysIn("NYR")
            );            
        } else {
            m = new And( 
                new All(), 
                new PlaysIn("NYR")
            );
        }
        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
