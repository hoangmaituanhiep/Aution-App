package app.controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import app.controllers.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import app.functions.*;
import javafx.util.Duration;
import javafx.scene.image.*;

public class MainWebController {
    @FXML
    private TextField searchItems;
    @FXML
    private Button logIn;
    @FXML
    private Button join;
    @FXML
    private Label logInLabel;
    @FXML
    private TilePane auctionPane;

    private static MainWebController instance;

    public MainWebController() {
        instance = this;
    }

    public static MainWebController getInstance() {
        return instance;
    }

    @FXML
    public void callLogIn() {
        try {
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/app/login.fxml"));
            Scene loginScene = new Scene(loginLoader.load());

            ConnectionController connectionController = loginLoader.getController();
            connectionController.setLoginListener(username -> {
                // this is for loginSucceeded function
                logIn.setVisible(false);
                logInLabel.setText("Wellcome " + username + " !");
                logInLabel.setVisible(true);
            });

            Stage loginStage = new Stage();
            loginStage.setScene(loginScene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addAuctionItem(Item item) {
        VBox card = new VBox(10);
        card.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-background-color: #f9f9f9;");

        ImageView imageView = new ImageView(item.getImage());
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        Label nameLabel = new Label(item.getName());
        Label priceLabel = new Label("Giá khởi điểm: " + item.getStartingPrice());

        Button chooseImageBtn = new Button("Chọn ảnh");
        chooseImageBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Chọn ảnh sản phẩm");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                item.setImage(image);
                imageView.setImage(image);
            }
        });

        card.getChildren().addAll(imageView, nameLabel, priceLabel);
        auctionPane.getChildren().add(card);

        // Timeline kiểm tra hết hạn
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (LocalDateTime.now().isAfter(item.getEndTime())) {
                auctionPane.getChildren().remove(card);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
