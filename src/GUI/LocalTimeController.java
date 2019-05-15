package GUI;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Data.Stopwatch;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Duration;

public class LocalTimeController implements Initializable {

	public static DateTimeFormatter timeFormat;
	
	@FXML
	public Label localTimeLabel;

	public Label stopwatchLabel;
	public Button stopwatchButton;
	public Button stopwatchResetButton;
	public Boolean stopwatchRunning = false;
	public Stopwatch stopWatch;

	public Spinner<Integer> hoursSpinner, minutesSpinner, secondsSpinner;
	public Boolean timerRunning = false;
	public Button timerStartButton;
	public Label timerLabel;
	public Timeline timerTimeline;
	public double timerInSeconds;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
			localTimeLabel.setText(LocalTime.now().format(timeFormat));
			if(stopwatchRunning)
				stopwatchLabel.setText(stopWatch.secondsToHMS());
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		stopwatchResetButton.setDisable(true);

		SpinnerValueFactory<Integer> hoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0);
		hoursSpinner.setValueFactory(hoursFactory);

		SpinnerValueFactory<Integer> minutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
		minutesSpinner.setValueFactory(minutesFactory);

		SpinnerValueFactory<Integer> secondsFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
		secondsSpinner.setValueFactory(secondsFactory);
	}

	public void stopwatchButtonAction() {
		stopwatchRunning = !stopwatchRunning;
		if (stopwatchRunning == true) {
			stopWatch = new Stopwatch();
			stopwatchButton.setText("Stop");
		} else {
			stopwatchButton.setDisable(true);
			stopwatchButton.setText("Start");
		}
		stopwatchResetButton.setDisable(false);
	}

	public void stopwatchResetButtonAction() {
		stopwatchRunning = false;
		stopwatchButton.setText("Start");
		stopwatchButton.setDisable(false);
		stopwatchResetButton.setDisable(true);
		stopwatchLabel.setText("00:00:00");
	}
	
	public void timerStartButtonAction() {
		timerRunning = !timerRunning;
		if(timerRunning == true) {
			timerInSeconds = secondsSpinner.getValue() + minutesSpinner.getValue() * 60 + hoursSpinner.getValue() * 3600;
			timerTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
				timerInSeconds--;
				if(timerInSeconds == 0) {
					timerTimeline.stop();
					timerRunning = false;
					timerStartButton.setText("Start");
				}
				int secondsTimer = (int) (timerInSeconds % 60);
		    	int minutesTimer = ((int) (timerInSeconds / 60)) % 60;
		    	int hoursTimer = (int) (timerInSeconds / 3600);
				timerLabel.setText(LocalTime.of(hoursTimer, minutesTimer, secondsTimer).format(LocalTimeController.timeFormat));
			}));
			timerTimeline.setCycleCount(Timeline.INDEFINITE);
			timerTimeline.play();
			timerStartButton.setText("Stop");
		} else {
			timerTimeline.stop();
			timerStartButton.setText("Start");
		}
	}
}