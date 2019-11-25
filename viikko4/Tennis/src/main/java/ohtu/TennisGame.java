package ohtu;

public class TennisGame {
    private int serversPoints   = 0;
    private int receiversPoints = 0;
    private final String serversName;
    private final String receiversName;
    // The possible scores before "advantage":
    private final String[] scoreNames = { "Love", "Fifteen", "Thirty", "Forty" };
    

    public TennisGame(String serversName, String receiversName) {
        this.serversName   = serversName;
        this.receiversName = receiversName;
    }

    
    public void wonPoint(String playerName) {
        if (playerName.equals(serversName)) {
            serversPoints += 1;
        } else {
            receiversPoints += 1;
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
        return getScoreName(serversPoints) + "-" + getScoreName(receiversPoints);
    }

    
    private String checkForEqualScores() {
        if (serversPoints != receiversPoints) {
            return null;
        }
        if (serversPoints >= scoreNames.length) {
            return "Deuce";
        }
        return getScoreName(serversPoints) + "-All";
    }

    
    private String checkForAdvantageAndWin() {
        if (!isAdvantage(serversPoints) && !isAdvantage(receiversPoints)) {
            return null;
        }

        int pointsDiff = serversPoints - receiversPoints;
        String winningPlayer = pointsDiff > 0 ? serversName : receiversName;

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
