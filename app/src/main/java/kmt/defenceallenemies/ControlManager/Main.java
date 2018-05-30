package kmt.defenceallenemies.ControlManager;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import kmt.defenceallenemies.R;

public class Main extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AppManager.getInstance().setActivity(this);
        setContentView(new GameView(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        boolean retry = true;
        Thread _thread =  AppManager.getInstance().getThread();
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }

    }

}
