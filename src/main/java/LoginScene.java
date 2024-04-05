import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoginScene {
    private Scene loginScene;
    private BorderPane background;

    private VBox vBox;
    private Image logo;

    private ImageView logoView;

    private Text title;

    private TextField usernameField;

    private Button loginBtn;



    public LoginScene(){
        background = createBackground();
        logo = createLogo();
        logoView = createLogoView();
        title = createText();
        usernameField = createUsernameField();
        loginBtn = createLoginButton();
        vBox = createVBox();
        background.setCenter(vBox);

        loginScene = new Scene(background, 800,800);


    }

    private BorderPane createBackground(){
        BorderPane background = new BorderPane();

        background.setStyle(
                "-fx-background-color: #5E5E5E;"
        );

        return background;
    }

    private Image createLogo(){
        return new Image("/Message Square.png");
    }

    private  ImageView createLogoView(){
        return new ImageView(logo);
    }

    private Text createText(){
        Text title = new Text("UICHAT");
        title.setStyle(
                "-fx-fill: #D9D9D9;"+
                "-fx-font-size: 62px;"+
                "-fx-font-family: 'Droid Sans';"+
                "-fx-font-weight: 600"//semi bold
        );

        return title;
    }

    private TextField createUsernameField(){
        TextField t1 = new TextField();

        t1.setPromptText("Username");

        t1.setPrefHeight(65);
        t1.setMaxHeight(65);
        t1.setMinHeight(65);

        t1.setPrefWidth(365);
        t1.setMaxWidth(365);
        t1.setMinWidth(365);

        t1.setStyle(
                "-fx-prompt-text-fill: #D9D9D9;" +
                "-fx-text-fill: #D9D9D9;" +
                "-fx-border-width: 2;"+
                "-fx-border-radius: 20px;"+
                "-fx-border-color: #D9D9D9;"+
                "-fx-background-color: transparent;"+
                "-fx-font-size: 25px;"+
                "-fx-alignment: center;"
        );

        return t1;
    }


    private Button createLoginButton(){
        Button b1 = new Button();
        b1.setCursor(Cursor.HAND);
        b1.setText("Login");
        b1.setStyle(
                "-fx-background-color: #D9D9D9;"+
                "-fx-fill: #5E5E5E;"+
                "-fx-background-radius: 20px;"+
                "-fx-font-size: 24px;"
        );


        b1.setMaxWidth(235);
        b1.setPrefWidth(235);
        b1.setMinWidth(235);
        b1.setMinHeight(55);
        b1.setMaxHeight(55);
        b1.setPrefHeight(55);


        return b1;

    }
    private VBox createVBox(){
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(logoView, title, usernameField, loginBtn);
        vBox.setAlignment(Pos.CENTER);
//        vBox.setStyle(
//                "-fx-border-color: black;"+
//                "-fx-border-width: 2;"
//        );


        return vBox;

    }

    public Scene getScene(){return loginScene;}

    public Button getLoginBtn(){
        return loginBtn;
    }

    public TextField getUsernameField(){return usernameField;}


}
