package kmt.defenceallenemies.GameScene;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.concurrent.CopyOnWriteArrayList;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GameView;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.MediaPlayerManager;
import kmt.defenceallenemies.ControlManager.iState;
import kmt.defenceallenemies.GameFactor.Player;
import kmt.defenceallenemies.GameFactor.background;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-06-16.
 */

public class TitleState implements iState {

    protected GraphicObject TitleObjects;
    protected GraphicObject TitlenameObjects;
    protected GraphicObject TitletouchObjects;

    @Override
    public void Init() {

        TitleObjects = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.title));
        TitlenameObjects = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.titlename));
        TitletouchObjects = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.toucj));
        TitletouchObjects.SetPosition(633,600);
        TitletouchObjects.SetSize(633,100);
        TitlenameObjects.SetPosition(480,200);
        TitlenameObjects.SetSize(960,200);
        TitleObjects.SetPosition(0,0);

        MediaPlayerManager.getInstance().play(AppManager.getInstance().getGameView().getContext(),R.raw.steambgm);
        MediaPlayerManager.getInstance().playLooped();
    }

    @Override
    public void Destroy() {
        MediaPlayerManager.getInstance().Stop();
    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        TitleObjects.DrawResolution(canvas);
        TitletouchObjects.DrawResize(canvas);
        TitlenameObjects.DrawResize(canvas);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
            AppManager.getInstance().getGameView().ChangeGameState(new AdjustState());
        return false;
    }
}
