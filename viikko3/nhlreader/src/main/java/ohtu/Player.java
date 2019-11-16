
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private String team;
    private int    goals;
    private int    assists;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setGoals(int goals) {
        this.goals = goals;
    }
    
    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Override
    public String toString() {
        return name + " team " + team + " goals " + goals + " assists " + assists;
    }
      
}
