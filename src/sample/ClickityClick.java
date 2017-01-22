package sample;

import javafx.concurrent.Task;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by Roman on 22.01.17.
 */
public class ClickityClick<Void> extends Task<Void> {
    private int i = 0;
    private int rate = 1000;
    private boolean isInfinite = false;
    private Robot robot;

    public void setI(int i){
        this.i=i;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setInfinite(boolean infinite) {
        isInfinite = infinite;
    }

    @Override
    public Void call()
    {
        try {
            robot = new Robot();
            if(isInfinite){
                while(isInfinite){
                    try {
                        Thread.sleep(rate);
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    } catch (InterruptedException ex) {}
                }
            } else {
                int current = 0;
                while (i >= current) {
                    try {
                        Thread.sleep(rate);
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        current++;
                        updateProgress(current,i);
                    } catch (InterruptedException ex) {}
                }
            }

        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }




}
