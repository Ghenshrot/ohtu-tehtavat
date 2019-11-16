package ohtu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.client.fluent.Request;

public class Main {
    
    private static List<Player> all_players;
    
    public static void main(String[] args) throws IOException {
        readPlayerData();
        
        String country = "FIN";
        System.out.println("Players from " + country + ":");
        List<Player> players = getPlayersByNationality(country);
        players.sort((a, b) -> b.getScore() - a.getScore());
        printPlayers(players);
    }
    
    public static void readPlayerData() throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        String player_data = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Type t = new TypeToken<Collection<Player>>() {}.getType();
        all_players = mapper.fromJson(player_data, t);
    }
    
    public static void printPlayers(List<Player> players) {
        players.forEach(p -> {
            System.out.format("%-30s %3s %3d + %3d = %3d\n",
                    p.getName(),
                    p.getNationality(),
                    p.getGoals(),
                    p.getAssists(),
                    p.getScore()
            );
        });
    }
    
    public static List<Player> getPlayersByNationality(String nationality) {
        return all_players
                .stream()
                .filter(p -> p.getNationality().equals(nationality))
                .collect(Collectors.toList());
    }
}
