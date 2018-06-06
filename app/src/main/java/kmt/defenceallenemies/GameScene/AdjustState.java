package kmt.defenceallenemies.GameScene;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.iState;
import kmt.defenceallenemies.GameFactor.button;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-04-29.
 */

public class AdjustState implements iState {

    private GraphicObject AdjustBackground;
    private CopyOnWriteArrayList<button> SelectCharacter = new CopyOnWriteArrayList<button>();
    private button StartGame;
    private button GotoTitle;
    private button fomation;
    private button store;
    private int Width,Height;
    private static final int Col = 50;
    private static final int Row = 30;

    @Override
    public void Init() {

        Width = AppManager.getInstance().getGameView().getWidth()/Col;
        Height = AppManager.getInstance().getGameView().getHeight()/Row;

        AdjustBackground= new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.menu_background));

        StartGame = new button(R.drawable.button_completion);
        StartGame.SetButtonSize(Width*5,Height*2);
        StartGame.SetPosition(Width*44,Height*27);

        GotoTitle = new button(R.drawable.button_back);
        GotoTitle.SetButtonSize(Width*6,Height*3);
        GotoTitle.SetPosition(Width,Height);

        fomation = new button(R.drawable.button_fomation);
        fomation.SetButtonSize(Width*4,Height*2);
        fomation.SetPosition(Width,Height*6);

        store = new button(R.drawable.button_store);
        store.SetButtonSize(Width*4,Height*2);
        store.SetPosition(Width,Height*9
        );

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<2;j++)
            {
            button temp = new button(R.drawable.device_full);
            temp.SetButtonSize(Width*10,Height*9);
            temp.SetPosition(Width*10+Width*13*(i),Height*6+Height*11*(j));
            SelectCharacter.add(temp);
            }
        }

    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        AdjustBackground.DrawRR(canvas,Width*Col,Height*Row);
        StartGame.DrawButton(canvas);
        GotoTitle.DrawButton(canvas);
        fomation.DrawButton(canvas);
        store.DrawButton(canvas);

        for(button b:SelectCharacter)
        {
            b.DrawButton(canvas);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(StartGame.GetBox().contains((int)event.getX(),(int)event.getY())==true)
                AppManager.getInstance().getGameView().ChangeGameState(new PlayingState());
            if(GotoTitle.GetBox().contains((int)event.getX(),(int)event.getY())==true)
                AppManager.getInstance().getGameView().ChangeGameState(new TitleState());
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
        }
        return false;

    }
}
