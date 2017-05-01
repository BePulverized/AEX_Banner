package Classes;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.rmi.RemoteException;
import java.util.TimerTask;

/**
 * Created by BePulverized on 2-4-2017.
 */
public class AEXBanner extends Application{

    public static final int width = 1000;
    public static final int height = 100;
    public static final int nano_ticks = 20000000;

    private Text stocks;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;

    public AEXBanner()
    {

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

            stocks = new Text("MORE COWBELL");
            stocks.setFont(new Font("Segoe UI", 80));
            stocks.fillProperty().set(Color.YELLOW);
            stocks.setManaged(false);
            stocks.setLayoutY(80);

            Pane pane = new Pane(stocks);
            pane.setMinSize(700, 100);
            pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            Timeline timeline = new Timeline();

            KeyFrame updateFrame = new KeyFrame(Duration.seconds(1 / 60d), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    double layoutX = stocks.getLayoutX();

                    if (layoutX <= 0 - stocks.getLayoutBounds().getMaxX()) {
                        layoutX = primaryStage.getWidth();
                    }

                    layoutX -= 1;
                    stocks.setLayoutX(layoutX);
                }

            });

            timeline.getKeyFrames().add(updateFrame);
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.resizableProperty().set(false);
            primaryStage.show();
            try{
                controller = new BannerController(this);
            }
            catch(RemoteException ex){
                ex.printStackTrace();
            }
    }

    public void setKoersen(String koersen) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stocks.setText(koersen);
            }
        });



    }

    @Override
    public void stop() throws Exception {
        super.stop();
        animationTimer.stop();
        controller.stop();

    }


}



