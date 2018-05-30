package kmt.defenceallenemies.ControlManager;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by Sonic on 2018-04-09.
 */

public interface iState {



    public void Init();
    // 이상태로 바뀌었을때 실행될것들
    public void Destroy();
    // 다른상태로 바뀔때 실행될것들
    public void Update();
    // 지속적으로 수행할것들
    public void Render(Canvas canvas);
    // 그려줘야할것들
    public boolean onKeyDown(int keyCode, KeyEvent event);
    // 키입력처리
    public boolean onTouchEvent(MotionEvent event);
    // 터치입력처리


}
