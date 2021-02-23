/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaguestats;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * Description:
 * The purpose of the program is to calculate
 * points of a league and maintain a league table. 
 * At least two teams will be added to the league and 
 * then games can be added to the stats. In this program, 
 * teams get 2 points for winning and one for a draw.
 * 
 * @author pinja
 */
public class Menu extends Application{

    private ArrayList<Team> teams;
    @Override
    public void init() throws Exception {
        this.teams = new ArrayList<Team>();
    }
    
    public void start(Stage window) {
        AddTeamWindow addTeamWindow = new AddTeamWindow(teams);
        AddGameWindow addGameWindow = new AddGameWindow(teams);
        TableWindow tableWindow = new TableWindow(teams);
        
        BorderPane layout = new BorderPane();
        HBox options = new HBox();
        options.setPadding(new Insets(20, 20, 20, 20));
        options.setSpacing(10);
        
        Button addTeam = new Button("Add team");
        Button addGame = new Button("Add game");
        Button table = new Button("Table");
        layout.setTop(options);
        options.getChildren().addAll(addTeam, addGame, table);
        addTeam.setOnAction((event) -> layout.setCenter(addTeamWindow.getWindow()));
        table.setOnAction((event) ->  layout.setCenter(tableWindow.getWindow()));
        addGame.setOnAction((event) -> {
            if(teams.size() > 1){
                layout.setCenter(addGameWindow.getWindow());
            }else{
                Label warning = new Label("Please add at least 2 teams first.");
                warning.setTextFill(Color.RED);
                layout.setCenter(warning);
            }          
        });
        
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
        
    }

    public static void main(String[] args) {
        launch(Menu.class);
    }

    
}

