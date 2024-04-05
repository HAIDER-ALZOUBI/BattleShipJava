import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ChatListScene {

    private Scene chatListScene;

    private BorderPane background;
    private HBox topBox;
    private Image globe;
    private ImageView globeView;
    private Image plus;
    private ImageView plusView;
    private Button addBtn;
    private Button globeBtn;
    private Text chatsText;
    private ListView chatList;
    public ChatListScene(){
        chatList = new ListView<HBox>();
        background = createBackground();
        createTopNodes();
        topBox = createHbox();
        background.setTop(topBox);

        chatListScene = new Scene(background, 800 , 800);
    }
    private BorderPane createBackground(){
        BorderPane bg = new BorderPane();
        bg.setStyle(
                "-fx-background-color: #5E5E5E"
        );

        return bg;
    }

    private void createTopNodes(){
        this.globe = new Image("/World.png");
        this.globeView = new ImageView(globe);
        this.plus = new Image("/Plus.png");
        this.plusView = new ImageView(plus);
        this.chatsText = new Text("Chats");
        this.chatsText.setStyle(
                "-fx-fill: #D9D9D9;"+
                "-fx-font-size: 62px;"+
                "-fx-font-family: 'Droid Sans';"+
                "-fx-font-weight: 600"//semi bold
        );
    }

    private HBox createHbox() {
        HBox hbox = new HBox();


        Pane spacer1 = new Pane();
        Pane spacer2 = new Pane();

        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        globeBtn = new Button();
        globeBtn.setGraphic(globeView);
        globeBtn.setCursor(Cursor.HAND);
        globeBtn.setStyle(
                "-fx-background-color: transparent;"+
                "-fx-background-radius: 5em;"+
                "-fx-border-radius: 5em;"
        );

        addBtn = new Button();
        addBtn.setGraphic(plusView);
        addBtn.setCursor(Cursor.HAND);
        addBtn.setStyle(
                "-fx-background-color: transparent;"+
                "-fx-background-radius: 5em;"+
                "-fx-border-radius: 5em;"
        );




        hbox.getChildren().addAll(globeBtn, spacer1, chatsText, spacer2, addBtn);

        return hbox;
    }

    public HBox createChatHbox(String username, String lastMessage){
        HBox chat = new HBox(30);
        VBox vBox = new VBox(20);
        Image person = new Image("/Profile.png");
        ImageView personView = new ImageView(person);
        Text user = new Text(username);
        Text lastChat = new Text(lastMessage);
        vBox.getChildren().addAll(user,lastChat);
        chat.getChildren().addAll(personView, vBox);

        return chat;
    }

    public void addToList(HBox chat){
        chatList.getItems().add(chat);
    }

    public Scene getScene(){return chatListScene;}

    public Button getGlobeBtn(){return globeBtn;}

    public Button getAddBtn(){return addBtn;}
}
