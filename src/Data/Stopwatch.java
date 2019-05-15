package Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import GUI.LocalTimeController;

public class Stopwatch { 

    private long start;

    /**
     * Initializes a new Stopwatch.
     */
    public Stopwatch() {
    	this.start = System.currentTimeMillis();
    } 

    /**
     * @return elapsed CPU time (in milliseconds) since the Stopwatch was created
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
         
    /**
     * @return the current elapsed time
     */
    public String secondsToHMS() {
    	int seconds = (int) (this.elapsedTime() % 60);
    	int minutes = ((int) (this.elapsedTime() / 60)) % 60;
    	int hours = (int) (this.elapsedTime() / 3600);
    	
    	return LocalTime.of(hours, minutes, seconds).format(LocalTimeController.timeFormat);
    }
} 