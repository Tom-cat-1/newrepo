import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
//  w  w  w.j av a2s  . c  o m
public class Browser extends Application {
  @Override
  public void start(final Stage stage) {
    stage.setWidth(1200);
    stage.setHeight(800);

    ScrollPane scrollPane = new ScrollPane();
    Scene scene = new Scene(scrollPane);


    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    scrollPane.setContent(browser);

    webEngine.getLoadWorker().stateProperty()
        .addListener(new ChangeListener<State>() {
          @Override
          public void changed(ObservableValue ov, State oldState, State newState) {

            if (newState == Worker.State.SUCCEEDED) {
              stage.setTitle(webEngine.getLocation());
            }

          }
        });
    webEngine.load("http://google.com");

    scene.setRoot(scrollPane);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}