/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaguestats;



/**
 *
 * @author pinja
 */
public class Team {
    
    private String name;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Integer games;
    private Integer points;
    public Team(String name){
        this.games = 0;
        this.losses = 0;
        this.draws = 0;
        this.wins = 0;
        this.name = name;
        this.points = 0;  
    }

    public String getName() {
        return name;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getDraws() {
        return draws;
    }

    public Integer getGames() {
        return games;
    }

    public Integer getPoints() {
        return points;
    }
    
    
    public void addGame(int ScoresTeam, int ScoresOpponent){
        if(ScoresTeam > ScoresOpponent){
            this.wins ++;
            this.games++;
            this.points += 2;
        }else if (ScoresTeam < ScoresOpponent){
            this.losses ++;
            this.games ++;
            
        }else{
            this.draws ++;
            this.games ++;
            this.points++;
        }
    }
    
    
    

}
