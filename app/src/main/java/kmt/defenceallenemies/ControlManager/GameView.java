package kmt.defenceallenemies.ControlManager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import kmt.defenceallenemies.GameScene.LoadState;
import kmt.defenceallenemies.GameScene.TitleState;

/**
 * Created by Sonic on 2018-04-09.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameViewThread _thread;

    private iState m_state;


    public GameView(Context context) {
        super(context);

        setFocusable(true);
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());
        AppManager.getInstance().setSize(getWidth(), getHeight());

        ChangeGameState(new LoadState());

        getHolder().addCallback(this);
        _thread = new GameViewThread(getHolder(),this);
        AppManager.getInstance().setThread(_thread);
//        getHolder().setFixedSize(getWidth(),getHeight());
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        m_state.Render(canvas);
    }

    public void myDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        m_state.Render(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        _thread.setRunning(true);
        _thread.start();
    }

    public void Update() {
        m_state.Update();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {

        boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        m_state.onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_state.onTouchEvent(event);
        return true;

    }

    public void ChangeGameState(iState _state){
        if(m_state!=null)
            m_state.Destroy();
        _state.Init();
        m_state = _state;
    }


}
