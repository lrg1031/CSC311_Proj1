package com.mycompany.lukegreen_assignment01_csc311;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void loadDBFromJSONfile() {
        
        try {
        String databaseURL = "";
        Connection conn = null;
        databaseURL = "jdbc:ucanaccess://.//Tweets.accdb";
        conn = DriverManager.getConnection(databaseURL);
        String sql = "DELETE FROM TweetTable";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: File not Found");
        }
            
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            FileReader fr = new FileReader("SampleTweets.json");
            Tweet[] t = gson.fromJson(fr, Tweet[].class);
            for (int i = 0; i < t.length; i++) {
                String username = t[i].getUsername();
                String message = t[i].getMessage();
                int likes = t[i].getLikes();
                App.insertDBData(username, message, likes);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not Found");
        }
    }
    
    @FXML
    private void loawdListViewFromDB() {
        
    }
    
    @FXML
    private void closeGUI() {
        System.exit(0);
    }
}
