package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.geom.Arc2D;

public class Controller {
    @FXML
    public Button commenceButton = new Button();
    @FXML
    public javafx.scene.control.TextField howManyTimes = new javafx.scene.control.TextField("10");
    @FXML
    public Slider latency = new Slider(10, 1000, 100);
    @FXML
    public CheckBox isInfinite = new CheckBox();
    @FXML
    public Button stopButton = new Button();
    @FXML
    public ProgressBar pBar = new ProgressBar();

    Robot robot;
    int i = 0;
    private int rate = 500;
    private ClickityClick clicker = new ClickityClick();
    private Thread clickerThread;


    @FXML
    public void onCommenceButton(ActionEvent actionEvent) {
            try {
                clicker=new ClickityClick();
                pBar.progressProperty().bind(clicker.progressProperty());
                System.out.println(howManyTimes.getText());
                clicker.setI(Integer.parseInt(howManyTimes.getText()));
                clicker.setRate((int) (1000/latency.getValue()));
                clicker.setInfinite(isInfinite.isSelected());
                stopButton.setDisable(false);
                commenceButton.setDisable(true);
                clicker.setOnSucceeded(e->{
                    commenceButton.setDisable(false);
                    stopButton.setDisable(true);
                });
                clickerThread = new Thread(clicker);
                clickerThread.setDaemon(true);
                clickerThread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void stop(){
        clicker.setInfinite(false);
        clicker.setI(0);
        stopButton.setDisable(true);
        clickerThread.interrupt();
    }
}
