
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiClient extends Application{

	LoginScene loginScene;
	ChatListScene chatListScene;
	
	UserListScene userListScene;

	
	TextField c1;
	Button b1;

	Button back;
	HashMap<String, Scene> sceneMap;
	VBox clientBox;
	Client clientConnection;
	
	ListView<String> listItems2;


	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		clientConnection = new Client(data->{
				Platform.runLater(()->{listItems2.getItems().add(data.toString());
			});
		});
							
		clientConnection.start();

		listItems2 = new ListView<String>();
		
		c1 = new TextField();
		b1 = new Button("Send");
		back = new Button("back");
		b1.setOnAction(e->{clientConnection.send(new Message(c1.getText()) ); c1.clear();});
		back.setOnAction(e->{
			primaryStage.setScene(sceneMap.get("chat list"));
		});
		
		sceneMap = new HashMap<String, Scene>();
		loginScene = new LoginScene();
		chatListScene = new ChatListScene();
		userListScene = new UserListScene();

		sceneMap.put("login", loginScene.getScene());
		sceneMap.put("chat list", chatListScene.getScene());
		sceneMap.put("client",  createClientGui());
		sceneMap.put("users list", userListScene.getScene());

		loginScene.getLoginBtn().setOnAction(e->{
			TextField uField = loginScene.getUsernameField();
			String username = uField.getText();
			if(username.isEmpty())
			{

				uField.setPromptText("Invalid Username");
			}
			else if(clientConnection.checkUserNameDupe(username))
			{
				uField.clear();
				uField.setPromptText("Username Taken");
			}
			else
				primaryStage.setScene(sceneMap.get("chat list"));

		});
		chatListScene.getGlobeBtn().setOnAction(e->{
			primaryStage.setScene(sceneMap.get("client"));
		});
		// Assuming clientConnection is your Client instance
		clientConnection.setOnUsersListReceivedListener(usersList -> {
			// Since this callback comes from a non-JavaFX thread, use Platform.runLater
			Platform.runLater(() -> {
//				System.out.println(usersList.size());
				userListScene.refreshUserList(usersList);
//				for(String u : usersList){
//					System.out.println(u);
//				}
				primaryStage.setScene(sceneMap.get("users list"));
			});
		});
		chatListScene.getAddBtn().setOnAction(e -> {
			clientConnection.sendRequest(new Message("GET_USERS_LIST"));
			// No need to change the scene or refresh the list here;
			// it's handled by the listener once the list is received.
		});





		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });


		primaryStage.setScene(sceneMap.get("login"));
		primaryStage.setTitle("Client");
		primaryStage.show();
		
	}
	

	
	public Scene createClientGui() {
		
		clientBox = new VBox(10, c1,b1, back,listItems2);
		clientBox.setStyle("-fx-background-color: blue;"+"-fx-font-family: 'serif';");
		return new Scene(clientBox, 400, 300);
		
	}

}
