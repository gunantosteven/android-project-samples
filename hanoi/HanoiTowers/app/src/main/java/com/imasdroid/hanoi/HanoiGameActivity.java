package com.imasdroid.hanoi;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.impl.client.DefaultHttpClient;

import java.util.Timer;
import java.util.TimerTask;

public class HanoiGameActivity extends Activity {

	private static final int DEFAULT_NUMBER_OF_DISKS = 3;

	private HanoiGameView hanoiGame;

    int count = 0;

    Timer t;
    TimerTask task;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// initialize Hanoi Game
		hanoiGame = new HanoiGameView(this, DEFAULT_NUMBER_OF_DISKS);

		// Hide the window title and top bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		
		setContentView(hanoiGame);

        startTimer(); // Run Solving Tower Hanoi
    }

    public void startTimer(){
        t = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if(count == hanoiGame.listLangkah.size())
                        {
                            t.cancel();
                            return;
                        }
                        hanoiGame.solveHanoi(count);
                        count++;
                    }
                });
            }
        };
        t.scheduleAtFixedRate(task, 0, 1500);
    }

    /**
	 * Handles the touch screen motion event to detect when user touches down
	 * the screen and pass the control to hanoiGame.onTouch() to perform the
	 * specific action
	 * 
	 * @param event The motion event
	 * @return True if the event was handled, false otherwise.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			if (hanoiGame.onTouch(event.getX(), event.getY())) {

				// when game is completed, shows an alert and starts a new game

				Toast.makeText(getApplicationContext(), "You Win!!", Toast.LENGTH_LONG).show();

				hanoiGame.startGame(DEFAULT_NUMBER_OF_DISKS);
			}
		}

		return true;
	}
}