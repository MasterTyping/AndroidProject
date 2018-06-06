package kmt.defenceallenemies.GameScene;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.SpriteObject;
import kmt.defenceallenemies.ControlManager.iState;
import kmt.defenceallenemies.GameFactor.Missile;
import kmt.defenceallenemies.GameFactor.Monster;
import kmt.defenceallenemies.GameFactor.Player;
import kmt.defenceallenemies.GameFactor.background;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-04-12.
 */

public class PlayingState implements iState {

    private static final int COL = 50;
    private static final int ROW = 20;
    private int Width = AppManager.getInstance().getGameView().getWidth()/COL;
    private int Height = AppManager.getInstance().getGameView().getHeight()/ROW;
    private int ShootCount =0;
    private Player player;
    private GraphicObject UI;
    private int cSize;
    private background bg1,bg2;
    private CopyOnWriteArrayList<Missile> Missiles = new CopyOnWriteArrayList<Missile>();
    private CopyOnWriteArrayList<Monster> Monsters = new CopyOnWriteArrayList<Monster>();
    private CopyOnWriteArrayList<SpriteObject>Boom  = new CopyOnWriteArrayList<SpriteObject>();
    private final static int MissileNum = 20;
    private int countMissile =0;
    private int MonsterCount =0;

    private long lastRegenMonster = System.currentTimeMillis();
    private long lastShootedMissile = System.currentTimeMillis();

    @Override
    public void Init() {

        cSize = 200;

        //init bmp data
        player = new Player();

        UI = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ui));
        bg1 = new background(AppManager.getInstance().getBitmap(R.drawable.bground),Width*52,Height*16);
        bg2 = new background(AppManager.getInstance().getBitmap(R.drawable.bground),Width*52,Height*16);

        player.SetPosSprite(Width*4,Height*16-cSize);


        bg1.SetPosition(0,0);
        bg2.SetPosition(Width*50,0);
        UI.SetPosition(0,Height*16);
        bg1.SetMaxFilm(Width*50);
        bg2.SetMaxFilm(Width*50);
        player.InitSprite(cSize,5,4,10);
        player.sethFrame(2);



    }

    @Override
    public void Destroy() {

    }
    public void GotoMonster()
    {

    }
    @Override
    public void Update() {
        long GameTime = System.currentTimeMillis();
        player.update(GameTime);
        bg1.update();
        bg2.update();
        MakeMonster();
        MakeMisslie();
        DeleteMonster();
        for(Monster monster:Monsters)
        {
            monster.update(GameTime);
        }

        for(Monster Mon:Monsters)
        {
            if(Mon.getX()<-cSize)
            {
                Monsters.remove(Mon);
                return;
            }
        }
        for(Missile missile:Missiles)
        {
            missile.update();
        }
        for(Missile mis:Missiles)
        {
            if(mis.GetX()>Width*51)
            {
                Missiles.remove(mis);
                return;
            }
        }
    }

    public void DeleteMonster()
    {
        for(Missile missile:Missiles)
        {
            for(Monster Mon:Monsters)
            {
                if(Mon.GetCbox().Collision(missile.GetCbox().GetBox()))
                {
                    Missiles.remove(missile);
                    Mon.HP -=1;
                    if(Mon.HP<=0)
                        Monsters.remove(Mon);
                    return;
                }
            }
        }
    }
    public void MakeMonster()
    {
        if(System.currentTimeMillis() - lastRegenMonster>=5000)
        {

            lastRegenMonster = System.currentTimeMillis();
            Monster mon = new Monster();
            mon.InitSprite(cSize,3,1,10);
            mon.SetPosSprite(Width*50,Height*16-cSize);
            mon.sethFrame(0);
            Monsters.add(mon);
        }
    }
    public void MakeMisslie()
    {
        if(System.currentTimeMillis() - lastShootedMissile>=1500)
        {

            lastShootedMissile = System.currentTimeMillis();
            Missile mis = new Missile();
            mis.SetSize(Width*3,Height*1);
            mis.SetPosition(player.getX()+cSize,player.getY()+(int)(cSize*0.5));
            Missiles.add(mis);
        }
    }
    public void Render(Canvas canvas) {

        bg1.bgDraw(canvas);
        bg2.bgDraw(canvas);
        UI.DrawRR(canvas,Width*25,Height*4);
        player.onDraw(canvas);
        for(Missile missile:Missiles)
        {
                missile.Missiledraw(canvas);
        }
        for(Monster monster:Monsters)
        {
            monster.onDraw(canvas);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
        }

        return false;
    }
}
