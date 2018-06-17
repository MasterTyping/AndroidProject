package kmt.defenceallenemies.GameScene;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.MediaPlayerManager;
import kmt.defenceallenemies.ControlManager.iState;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-06-17.
 */

public class AdjustState implements iState {
    protected GraphicObject AdjustObect1;
    protected GraphicObject AdjustObect2;
    protected GraphicObject AdjustObect3;
    protected GraphicObject AdjustObect4;
    protected GraphicObject AdjustObect5;
    protected GraphicObject AdjustObect6;

    @Override
    public void Init(){

        AdjustObect1 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.menu_background));
        AdjustObect2 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.button_completion));
        AdjustObect3 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.button_back));
        AdjustObect2.SetPosition(1600,800);
        AdjustObect2.SetSize(200,100);
        AdjustObect3.SetPosition(20,20);
        AdjustObect3.SetSize(200,140);
    }

    @Override
    public void Destroy() {
        MediaPlayerManager.getInstance().Stop();
    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas)
    {

        AdjustObect1.DrawResolution(canvas);
        AdjustObect2.DrawResize(canvas);
        AdjustObect3.DrawResize(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
            AppManager.getInstance().getGameView().ChangeGameState(new PlayState());
        return false;
    }
}
