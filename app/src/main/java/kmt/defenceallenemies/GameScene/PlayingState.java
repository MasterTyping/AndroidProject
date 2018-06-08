package kmt.defenceallenemies.GameScene;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import junit.framework.Test;

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
import kmt.defenceallenemies.GameFactor.button;
import kmt.defenceallenemies.R;

import static java.lang.String.*;

/**
 * Created by Sonic on 2018-04-12.
 */

public class PlayingState implements iState {
    private Paint font = new Paint();
    private static final int COL = 50;
    private static final int ROW = 20;
    private int Width = AppManager.getInstance().getGameView().getWidth()/COL;
    private int Height = AppManager.getInstance().getGameView().getHeight()/ROW;
    private int ShootCount =0;
    public static CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<Player>();
    private GraphicObject UI;
    private int cSize;
    private background bg1 = new background(Width*50,Height*16);
    private background bg2 = new background(Width*50,Height*16);
    private CopyOnWriteArrayList<Missile> Missiles = new CopyOnWriteArrayList<Missile>();
    private CopyOnWriteArrayList<Monster> Monsters = new CopyOnWriteArrayList<Monster>();
    private CopyOnWriteArrayList<SpriteObject>Boom  = new CopyOnWriteArrayList<SpriteObject>();
    private final static int MissileNum = 20;
    private int countMissile =0;
    private int MonsterCount =0;
    public static int pCounter = 0;
    private long lastRegenMonster = System.currentTimeMillis();
    private long lastShootedMissile = System.currentTimeMillis();
    private long lastEffect = System.currentTimeMillis();
    private long lastboom = System.currentTimeMillis();
    private button exit = new button(R.drawable.button_back);
    private CopyOnWriteArrayList<SpriteObject>Shoot = new CopyOnWriteArrayList<SpriteObject>();
    @Override
    public void Init() {

        cSize = Width*5;
        font.setAntiAlias(true);
        font.setColor(Color.BLACK);
        //init bmp data

        UI = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ui));


        bg1.SetPosition(0,0);
        bg2.SetPosition(Width*50,0);
        UI.SetPosition(0,Height*16);
        bg1.SetMaxFilm(Width*50);
        bg2.SetMaxFilm(Width*50);

        for(int i =0;i<players.size();i++)
        {
            players.get(i).SetPosSprite(Width*4+(Width*2*i),Height*16-cSize);
        }
        exit.SetButtonSize(Width*3,Height*1);
        exit.SetPosition(Width*45,Height*17);
    }

    @Override
    public void Destroy() {
        for(Player p:players)
            players.remove(p);
    }

    @Override
    public void Update() {

        long GameTime = System.currentTimeMillis();
        for(Player player:players)
            player.update(GameTime);
        bg1.update();
        bg2.update();
        MakeMonster();

        MakeMisslie();
        DeleteMonster();
        DeleteBoom();
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
        for(SpriteObject shoot:Shoot)
        {
            shoot.update(GameTime);
        }
        for(SpriteObject boom:Boom)
        {
            boom.update(GameTime);
        }
    }

    public void DeleteBoom()
    {
        if(System.currentTimeMillis() - lastboom>=500) {
            lastboom = System.currentTimeMillis();
            for (SpriteObject boom : Boom) {
                Boom.remove(boom);
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
                    SpriteObject beffect = new SpriteObject(AppManager.getInstance().getBitmap(R.drawable.boomeffect));
                    beffect.SetCOLROWS(5,1);
                    beffect.setSize(Width*5);
                    beffect.setFps(10);
                    beffect.Setwh(AppManager.getInstance().getBitmap(R.drawable.shooteffect).getWidth()/5,AppManager.getInstance().getBitmap(R.drawable.shooteffect).getHeight());
                    beffect.SetPosSprite(Mon.getX(), Mon.getY());

                    Boom.add(beffect);
                    return;
                }
            }
        }
    }
    public void MakeMonster()
    {
        if(System.currentTimeMillis() - lastRegenMonster>=4000)
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
        if(System.currentTimeMillis() - lastShootedMissile>=1000)
        {

            lastShootedMissile = System.currentTimeMillis();

            for(int i=0;i< players.size();i++){

                Missile mis = new Missile();
                mis.SetSize(Width*3,Height*1);

                mis.SetPosition(players.get(i).getX() + cSize, players.get(i).getY() + (int) (cSize * 0.5));
                Missiles.add(mis);
               
            }

        }
    }
    public void Render(Canvas canvas) {


        bg1.bgDraw(canvas);
        bg2.bgDraw(canvas);
        UI.DrawRR(canvas,Width*25,Height*4);
        for(Player p:players)
            p.onDraw(canvas);
        for(Missile missile:Missiles)
        {
                missile.Missiledraw(canvas);
        }
        for(Monster monster:Monsters)
        {
            monster.onDraw(canvas);
        }
        for (SpriteObject shoot : Shoot) {
            shoot.onDraw(canvas);
        }
        for (SpriteObject boom : Boom) {
            boom.onDraw(canvas);
        }
        exit.DrawButton(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(exit.GetBox().contains((int)event.getX(),(int)event.getY())==true)
                AppManager.getInstance().getGameView().ChangeGameState(new AdjustState());
        }

        return false;
    }
}
