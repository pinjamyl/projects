/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaguestats;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author pinja
 */
public class TableWindow {

    private ArrayList<Team> teams;
    
    public TableWindow(ArrayList<Team> teams){
        this.teams = teams;
    }
    
    
    public Parent getWindow(){
        BorderPane layout = new BorderPane();
        TableView table = new TableView();
        
        table.setPlaceholder(new Label("No teams added yet."));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Team, String> teamCol = new TableColumn<>("Team");
        teamCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Team, Integer> gamesCol = new TableColumn<>("GP");
        gamesCol.setCellValueFactory(new PropertyValueFactory<>("games"));
        
        TableColumn<Team, Integer> winsCol = new TableColumn<>("W");
        winsCol.setCellValueFactory(new PropertyValueFactory<>("wins"));
        
        TableColumn<Team, Integer> drawsCol = new TableColumn<>("D");
        drawsCol.setCellValueFactory(new PropertyValueFactory<>("draws"));
        
        TableColumn<Team, Integer> lossesCol = new TableColumn<>("L");
        lossesCol.setCellValueFactory(new PropertyValueFactory<>("losses"));
        
        TableColumn<Team, Integer> pointsCol = new TableColumn<>("PTS");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        table.setItems(getTeams());
        
        table.getColumns().addAll(teamCol,gamesCol, winsCol, drawsCol, lossesCol, pointsCol);
        layout.setCenter(table);
       
        
        return layout;
    }
    
    public ObservableList<Team> getTeams(){
        ObservableList<Team> data = FXCollections.observableArrayList();
        for(Team team : teams){
            data.add(team);
        }
        return data;
    }
}
