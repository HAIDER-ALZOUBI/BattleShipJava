import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class UserListScene {
    Scene userListScene;
    BorderPane borderPane;
    private ListView<Text> usersList;
    public UserListScene() {
        borderPane = new BorderPane();
        usersList = new ListView<>(); // Initialize usersList before using it

        borderPane.setStyle("-fx-background-color: white");

        usersList.setStyle("-fx-border-color: red;" +
                "-fx-border-width: 4px;"); // Note: It's `-fx-border-width`, not `-fx-border`

        borderPane.setCenter(usersList); // Now usersList is not null when being set

        userListScene = new Scene(borderPane, 800, 800);
    }

    //    public void createMessage(String username, String message){
//        HBox m = new HBox(10);
//        Text un = new Text(username);
//        Text msg = new Text(message);
//        m.getChildren().addAll(un,msg);
//        
//        usersList.getItems().add(m);
//    }
    public void refreshUserList(ArrayList<String> userList){
        if(userList != null)
        {
            for(String user : userList){
                Text t = new Text(user);
                System.out.println(user);
                t.setStyle(
                        "-fx-fill: #D9D9D9;"+
                        "-fx-font-size: 62px;"+
                        "-fx-font-family: 'Droid Sans';"+
                        "-fx-font-weight: 300"
                );
                usersList.getItems().add(t);
            }

        }

    }
    public Scene getScene(){return userListScene;}



}
