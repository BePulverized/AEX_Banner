package Classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.TimerTask;

/**
 * Created by BePulverized on 2-4-2017.
 */
public class AEXBanner extends Application{

    public static final int width = 1000;
    public static final int height = 100;
    public static final int nano_ticks = 20000000;

    private Text text;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;

    public AEXBanner(Text text)
    {
        this.text = text;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new BannerController(this);

        Font font = new Font("Arial", height);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().add(text);
        Scene scene = new Scene(root, width, height);

        primaryStage.setTitle("AEX banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();


        // Start animation: text moves from right to left
        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                long lag = now - prevUpdate;
                if (lag >= nano_ticks) {
                    // calculate new location of text
                    textPosition = textPosition + 1;
                    text.relocate(textPosition,0 );
                    prevUpdate = now;
                }
            }
            @Override
            public void start() {
                prevUpdate = System.nanoTime();
                textPosition = width;
                text.relocate(textPosition, 0);
                super.start();
            }
        };
        animationTimer.start();
        controller = new BannerController(this);
    }

    public void setKoersen(String koersen) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                text.setText(koersen);
                textLength = text.getLayoutBounds().getWidth();
            }
        });

    }

    @Override
    public void stop() {

        animationTimer.stop();
        controller.stop();

    }


}



