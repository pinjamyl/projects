/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaguestats;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


/**
 *
 * @author pinja
 */
class AddTeamWindow {
    private ArrayList<Team> teams;
    

    public AddTeamWindow(ArrayList<Team> teams) {
        this.teams = teams;
    }
    
    public Parent getWindow() {
        GridPane layout = new GridPane();

        Label addTeam = new Label("Team name");
        TextField team =new  TextField();
        Button addButton = new Button("Add team");
        Label warningTeam = new Label("The team has already been added.");
        warningTeam.setTextFill(Color.RED);
        Label warningBlank = new Label("Please enter a name \nbefore adding a team.");
        warningBlank.setTextFill(Color.RED);
        
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        layout.add(addTeam, 0, 1);
        layout.add(team, 1, 1);
        layout.add(addButton, 1, 2);
        layout.add(warningTeam, 1,3);
        layout.add(warningBlank, 1,3);
        warningBlank.setVisible(false);
        warningTeam.setVisible(false);
        

        addButton.setOnMouseClicked((event) -> {
            if(isAlreadyAdded(team.getText())){
                warningTeam.setVisible(true);
                team.clear();
            }else if(team.getText().isBlank()){
                warningBlank.setVisible(true);
            }else{
               warningBlank.setVisible(false);
               warningTeam.setVisible(false);
               teams.add(new Team(team.getText()));
               team.clear(); 
            }
        });        

        return layout;
    }
    
    public boolean isAlreadyAdded(String teamName){
        boolean isAdded = false;
        for(Team team : teams){
            if(team.getName().equals(teamName)){
                isAdded = true;
            }
        }
        return isAdded;
    }

}
