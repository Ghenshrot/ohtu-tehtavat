package ohtu;

public class TennisGame {
    private int player1Points = 0;
    private int player2Points = 0;
    private final String player1Name;
    private final String player2Name;
    // The possible scores before "advantage":
    private final String[] scoreNames = { "Love", "Fifteen", "Thirty", "Forty" };
    

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Points += 1;
        } else {
            player2Points += 1;
        }
    }

    
    public String getScore() {
        String score = checkForEqualScores();
        if (score != null) {
            return score;
        }
        score = checkForAdvantageAndWin();
        if (score != null) {
            return score;
        }
        return getScoreName(player1Points) + "-" + getScoreName(player2Points);
    }

    
    private String checkForEqualScores() {
        if (player1Points != player2Points) {
            return null;
        }
        if (player1Points >= scoreNames.length) {
            return "Deuce";
        }
        return getScoreName(player1Points) + "-All";
    }

    
    private String checkForAdvantageAndWin() {
        if (!isAdvantage(player1Points) && !isAdvantage(player2Points)) {
            return null;
        }

        int pointsDiff = player1Points - player2Points;
        String winningPlayer = pointsDiff > 0 ? player1Name : player2Name;

        if (Math.abs(pointsDiff) == 1) {
            return "Advantage " + winningPlayer;
        }
        return "Win for " + winningPlayer;
    }
    

    private String getScoreName(int score) {
        return scoreNames[score];
    }
    

    private boolean isAdvantage(int points) {
        return points >= scoreNames.length;
    }
}
