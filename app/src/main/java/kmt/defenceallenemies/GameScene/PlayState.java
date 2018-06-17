package kmt.defenceallenemies.GameScene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.concurrent.CopyOnWriteArrayList;

import kmt.defenceallenemies.ControlManager.AppManager;
import kmt.defenceallenemies.ControlManager.GameView;
import kmt.defenceallenemies.ControlManager.GraphicObject;
import kmt.defenceallenemies.ControlManager.MediaPlayerManager;
import kmt.defenceallenemies.ControlManager.SoundManager;
import kmt.defenceallenemies.ControlManager.SpriteObject;
import kmt.defenceallenemies.ControlManager.iState;
import kmt.defenceallenemies.GameFactor.Missile;
import kmt.defenceallenemies.GameFactor.Monster;
import kmt.defenceallenemies.GameFactor.Player;
import kmt.defenceallenemies.GameFactor.background;
import kmt.defenceallenemies.R;

/**
 * Created by Sonic on 2018-06-16.
 */

public class PlayState implements iState {

    protected CopyOnWriteArrayList<Player>players;
    protected CopyOnWriteArrayList<Monster>monsters;
    protected CopyOnWriteArrayList<SpriteObject>effects;
    protected CopyOnWriteArrayList<Missile>Misslies;
    protected background BG1,BG2;
    protected GraphicObject Skiil;
    protected GraphicObject icon,icon2;
    protected long LastMonRegerateTime = System.currentTimeMillis();
    protected long LastRegerateMissileTime = System.currentTimeMillis();
    protected long LastDeleteEffectTime = System.currentTimeMillis();
    protected long CountTime = System.currentTimeMillis();
    protected int RegenMonsterTime = 2000;
    protected int KillCount;
    protected Paint p;
    protected int Minute,Second, dmg;
    protected CopyOnWriteArrayList<Rect> SkillButton = new CopyOnWriteArrayList<Rect>();
    @Override
    public void Init() {
        players = new CopyOnWriteArrayList<Player>();
        monsters = new CopyOnWriteArrayList<Monster>();
        Misslies = new CopyOnWriteArrayList<Missile>();
        effects = new CopyOnWriteArrayList<SpriteObject>();
        BG1 = new background(AppManager.getInstance().getBitmap(R.drawable.bground));
        BG2 = new background(AppManager.getInstance().getBitmap(R.drawable.bground));
        Skiil = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.ui));
        Skiil.SetPosition(0,850);
        Skiil.SetSize(1000,AppManager.getInstance().getGameView().getHeight()-850);
        BG1.SetPosition(0,0);
        BG2.SetPosition(AppManager.getInstance().getGameView().getWidth(),0);
        BG1.SetSize(AppManager.getInstance().getGameView().getWidth(),850);
        BG2.SetSize(AppManager.getInstance().getGameView().getWidth(),850);
        icon = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.skillicon));
        icon.SetSize(100,100);
        icon.SetPosition(50,910);
        icon2 = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.pup));
        icon2.SetSize(100,100);
        icon2.SetPosition(210,910);
        MediaPlayerManager.getInstance().play(AppManager.getInstance().getGameView().getContext(),R.raw.ready);
        MediaPlayerManager.getInstance().playLooped();
        SoundManager.getInstance().Init(AppManager.getInstance().getGameView().getContext());
        SoundManager.getInstance().addSound(1,R.raw.shotgun);
        SoundManager.getInstance().addSound(2,R.raw.skillsound);
        Addplayer();

        for(int i=0;i<6;i++)
        {
                Rect Temp = new Rect(50+i*170,910,50+i*170+75,910+75);
            if((i%2) ==1)
                Temp.set(50+i*160,950,50+i*160+100,950+100);
            else
                Temp.set(50+i*160,910,50+i*160+100,910+100);
            SkillButton.add(Temp);
        }
        p = new Paint();
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setTextSize(100);
    }

    @Override
    public void Destroy() {
        MediaPlayerManager.getInstance().Stop();
    }

    @Override
    public void Update() {
        long CurrentTime = System.currentTimeMillis();
        CountTime();
        if(KillCount < 100)
            AddMonster();
        if(Minute>=1)
            RegenMonsterTime = 1000;

        AddMissile();
        DeleteEffect();
        BG1.update(CurrentTime);
        BG2.update(CurrentTime);
        for(Missile missile:Misslies)
            missile.update(CurrentTime);
        for(Player player:players){
            player.Animate(CurrentTime);
            if(player.getX()>AppManager.getInstance().getGameView().getWidth())
            {
                players.remove(player);
            }
        }
        for(Monster monster:monsters){
            monster.Animate(CurrentTime);
            if(monster.getX()<-monster.getSize())
            {
                monsters.remove(monster);
            }
        }
        for(Missile missile:Misslies)
        {
            for(Monster monster:monsters)
            {
                if(missile.GetX()+100>monster.getX()) {
                    Misslies.remove(missile);
                    monster.HP -= missile.AttackDamage;
                    Addeffect(monster.getX());
                    if(monster.HP<=0){
                        monsters.remove(monster);
                        KillCount++;
                    }
                    //return;
                }
            }
        }
        for(SpriteObject effect:effects)
        {
            effect.update(CurrentTime);
        }
    }
    public void AddMissile()
    {
        if(System.currentTimeMillis()-LastRegerateMissileTime>300 & monsters.isEmpty() == false){
            LastRegerateMissileTime = System.currentTimeMillis();
            Missile temp = new Missile(AppManager.getInstance().getBitmap(R.drawable.missile));
            temp.SetSize(100,30);
            temp.SetPosition(100,800);
            temp.AttackDamage+=dmg;
            Misslies.add(temp);
            SoundManager.getInstance().play(1);
        }
    }
    public void DeleteEffect()
    {
        if(System.currentTimeMillis()-LastDeleteEffectTime>100){
            LastDeleteEffectTime = System.currentTimeMillis();
            if(effects.isEmpty()==false)
                effects.remove(0);
         }
    }
    public void CountTime()
    {
        if(System.currentTimeMillis()-CountTime>1000)
        {
            CountTime = System.currentTimeMillis();
            Second++;
            if(Second>=60)
            {
                Second=0;
                Minute++;
            }

        }
    }
    public void Addeffect(int x)
    {
       SpriteObject temp = new SpriteObject(AppManager.getInstance().getBitmap(R.drawable.boomeffect));
       temp.SetPosSprite(x,750);
       temp.SetCOLROWS(5,1);
       temp.SetSize(100,100);
       effects.add(temp);
    }
    public void Addplayer()
    {
        Player temp = new Player(AppManager.getInstance().getBitmap(R.drawable.spritetanker));
        temp.SetPosSprite(0, 700);
        temp.SetSize(150,150);
        players.add(temp);
    }
    public void AddMonster()
    {
        if(System.currentTimeMillis()-LastMonRegerateTime>RegenMonsterTime)
        {
            LastMonRegerateTime = System.currentTimeMillis();
            Monster temp = new Monster(AppManager.getInstance().getBitmap(R.drawable.spritestomatopodaa));
            temp.SetPosSprite(AppManager.getInstance().getGameView().getWidth(), 800);
            temp.SetSize(150,50);
            monsters.add(temp);
        }
    }
    public void DropWater()
    {
        for(Monster monster:monsters)
        {
            SpriteObject temp = new SpriteObject(AppManager.getInstance().getBitmap(R.drawable.dropeffect));
            temp.SetPosSprite(monster.getX(),650);
            temp.SetCOLROWS(5,1);
            temp.SetSize(200,200);
            effects.add(temp);
            monster.HP -=2;
        }
        SoundManager.getInstance().play(2);
    }

    @Override
    public void Render(Canvas canvas) {

        BG1.Render(canvas);
        BG2.Render(canvas);
        Skiil.DrawResize(canvas);
        icon.DrawResize(canvas);
        icon2.DrawResize(canvas);
        for(Player player:players)
            player.Render(canvas);
        for(Monster monster:monsters)
            monster.Render(canvas);
        for(Missile missile:Misslies)
            missile.Render(canvas);
        for(SpriteObject effect:effects)
            effect.onDraw(canvas);

        canvas.drawText(Integer.toString(KillCount),0,100,p);
        canvas.drawText(Integer.toString(Minute),703,100,p);
        canvas.drawText(" : ",783,100,p);
        canvas.drawText(Integer.toString(Second),833,100,p);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() ==  MotionEvent.ACTION_DOWN && SkillButton.get(0).contains((int)event.getX(),(int)event.getY()))
        {
            DropWater();
        }
        if(event.getAction() ==  MotionEvent.ACTION_DOWN && SkillButton.get(1).contains((int)event.getX(),(int)event.getY()))
        {
            dmg++;
        }
        if(event.getAction() ==  MotionEvent.ACTION_DOWN && SkillButton.get(2).contains((int)event.getX(),(int)event.getY()))
        {}
        if(event.getAction() ==  MotionEvent.ACTION_DOWN && SkillButton.get(3).contains((int)event.getX(),(int)event.getY()))
        {}
        if(event.getAction() ==  MotionEvent.ACTION_DOWN && SkillButton.get(4).contains((int)event.getX(),(int)event.getY()))
        {}
        if(event.getAction() ==  MotionEvent.ACTION_DOWN && SkillButton.get(5).contains((int)event.getX(),(int)event.getY()))
        {}

        return false;
    }
}
