package com.mycompany.lukegreen_assignment01_csc311;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setTitle("CSC 311 - Homework 1 - Luke Green");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        //insertDBData("tony13001", "Overwatch 2 comin out soon", 5);
        //showDBData();
        Tweet[] t = new Tweet[4];

        t[0] = new Tweet();
        t[0].setUsername("@MeCookieMonster");
        t[0].setMessage("Me no cry because cookie is finished. Me smile because cookie happened.");
        t[0].setLikes(365000);

        t[1] = new Tweet();
        t[1].setUsername("@jack");
        t[1].setMessage("just setting up my twttr");
        t[1].setLikes(179000);

        t[2] = new Tweet();
        t[2].setUsername("@CIA");
        t[2].setMessage("We can neither confirm nor deny that this is our first tweet.");
        t[2].setLikes(222000);

        t[3] = new Tweet();
        t[3].setUsername("@chribhibble");
        t[3].setMessage("It's Google's 15th birthday today. Typical fifteen year old. It's got an answer for everything.");
        t[3].setLikes(577);

        //writeToJSONFile(t);
        //readFromJSONFile();
        launch();
    }

    public static void insertDBData(String username, String message, int likes) {
        String databaseURL = "";
        Connection conn = null;
        try {
            databaseURL = "jdbc:ucanaccess://.//Tweets.accdb";
            conn = DriverManager.getConnection(databaseURL);
            
        } catch (SQLException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "INSERT INTO TweetTable (Username, Message, Likes) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, message);
            preparedStatement.setInt(3, likes);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Row inserted");
            }
        } catch (SQLException e) {
            System.out.println("Error: File not Found");
        }
    }

    public static void showDBData() {
        String databaseURL = "";
        Connection conn = null;
        try {
            databaseURL = "jdbc:ucanaccess://.//Tweets.accdb";
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException ex) {
            System.out.println("Error: File not Found");
        }

        try {
            String tableName = "TweetTable";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from " + tableName);
            while (result.next()) {
                int id = result.getInt("ID");
                String Username = result.getString("Username");
                String Message = result.getString("Message");
                int Likes = result.getInt("Likes");
                System.out.printf("%d %s %s %d", id, Username, Message, Likes);
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
    }

    public static void readFromJSONFile() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            FileReader fr = new FileReader("SampleTweets.json");
            Tweet[] t = gson.fromJson(fr, Tweet[].class);
            for (int i = 0; i < t.length; i++) {
                String username = t[i].getUsername();
                String message = t[i].getMessage();
                int likes = t[i].getLikes();
                System.out.printf("%s %s %d", username, message, likes);
                System.out.println();
            }
        } catch (FileNotFoundException ex) {

        }

    }

    //works but is an infinite loop
    public static void writeToJSONFile(Tweet[] ta) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonString = gson.toJson(ta);
        try {
            PrintStream ps = new PrintStream("SampleTweets.json");
            ps.println(jsonString);
        } catch (FileNotFoundException ex) {

        }

    }

}
