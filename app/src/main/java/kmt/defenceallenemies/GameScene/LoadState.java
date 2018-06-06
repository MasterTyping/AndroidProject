package kmt.defenceallenemies.GameScene;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.iState;

/**
 * Created by Sonic on 2018-06-07.
 */

public class LoadState implements iState {
    @Override
    public void Init() {

    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        AppManager.getInstance().getGameView().ChangeGameState(new TitleState());
    }

    @Override
    public void Render(Canvas canvas) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
