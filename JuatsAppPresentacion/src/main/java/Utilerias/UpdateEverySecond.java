/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilerias;

/**
 *
 * @author santi
 */
import java.util.Timer;
import java.util.TimerTask;

public class UpdateEverySecond {
    private Timer timer;

    public UpdateEverySecond() {
        timer = new Timer();
    }

    public void startUpdating() {
        // Schedule a task to run every second
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Place your update logic here
                System.out.println("Updating every second...");
            }
        }, 0, 1000); // Delay of 0ms (start immediately), repeat every 1000ms (1 second)
    }

    public void stopUpdating() {
        timer.cancel(); // Stop the timer when no longer needed
    }

}