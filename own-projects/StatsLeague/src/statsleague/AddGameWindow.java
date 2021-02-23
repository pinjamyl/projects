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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author pinja
 */
public class AddGameWindow {
    private ArrayList<Team> teams;
    public AddGameWindow(ArrayList<Team> teams){
        this.teams = teams;
    }
    
    public Parent getWindow(){
        GridPane layout = new GridPane();

        Label homeText = new Label("\t\tHome");
        Label guestText = new Label("Guest");
        Label line = new Label("-");
        TextField goalsHomeField = new TextField();
        TextField goalsGuestField = new TextField();
        Label warningGoals = new Label("Please check that \nyou entered numbers.");
        warningGoals.setTextFill(Color.RED);
        Label warningTeams = new Label("Please enter two \ndifferent teams.");
        warningTeams.setTextFill(Color.RED);
        ComboBox teamsHome = new ComboBox();
        ComboBox teamsGuest = new ComboBox();
        teamsHome.setMinWidth(150);
        teamsGuest.setMinWidth(150);
        Button addButton = new Button("Add");
        for(Team team : teams){
            teamsHome.getItems().add(team.getName());
            teamsGuest.getItems().add(team.getName());
        }
        
        HBox homeGuestRow = new HBox();
        homeGuestRow.getChildren().addAll(homeText, guestText );
        homeGuestRow.setSpacing(150);
        
        HBox comboboxRow = new HBox();
        comboboxRow.getChildren().addAll(teamsHome, teamsGuest);
        comboboxRow.setSpacing(25);
        
        HBox goalsRow = new HBox();
        goalsRow.getChildren().addAll(goalsHomeField,line, goalsGuestField);
        goalsRow.setSpacing(10);
        
        HBox lowestRow = new HBox();
        lowestRow.getChildren().add(addButton);
        lowestRow.setSpacing(10);
        lowestRow.getChildren().add(warningGoals);
        lowestRow.getChildren().add(warningTeams);
        warningGoals.setVisible(false);
        warningTeams.setVisible(false);
        
        
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        layout.add(homeGuestRow, 0, 0);
        layout.add(comboboxRow, 0, 1);
        layout.add(goalsRow, 0, 2);
        layout.add(lowestRow, 0, 3);
        
        

        addButton.setOnMouseClicked((event) -> {
            String home = teamsHome.getValue().toString();
            String guest = teamsGuest.getValue().toString();
            String goalsH = goalsHomeField.getText();
            String goalsG = goalsGuestField.getText();
        
            if(!home.equals(guest)){
                Integer goalsHInt = 0;
                Integer goalsGInt = 0;
                warningGoals.setVisible(false);
                if(isNumeric(goalsH) && isNumeric(goalsG)){
                    goalsHInt = Integer.parseInt(goalsH);
                    goalsGInt = Integer.parseInt(goalsG);
                    for(Team team : teams){
                    if (team.getName().equals(home)){
                        team.addGame(goalsHInt, goalsGInt);
                        goalsHomeField.clear();
                        goalsGuestField.clear();
                        teamsHome.getSelectionModel().clearSelection();
                        teamsGuest.getSelectionModel().clearSelection();
                    } else if(team.getName().equals(guest)){
                        team.addGame(goalsGInt, goalsHInt);
                        goalsHomeField.clear();
                        goalsGuestField.clear();
                        teamsHome.getSelectionModel().clearSelection();
                        teamsGuest.getSelectionModel().clearSelection();
                    }
                }
                }else{
                    warningGoals.setVisible(true);
                    warningTeams.setVisible(false);
                }
                
            }else{
                warningTeams.setVisible(true);
                warningGoals.setVisible(false);
            }
        });
        
        return layout;
    }
    
    //This method checks if the string is numeric
    public boolean isNumeric(String string){
        boolean numeric = true;
        try {
            Integer num = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }
}
