package kmt.defenceallenemies.ControlManager;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Sonic on 2018-06-16.
 */

public class MediaPlayerManager {
    private static MediaPlayerManager manager;

    public static MediaPlayerManager getInstance() {
        if (manager == null) {
            manager = new MediaPlayerManager();
        }
        return manager;
    }

    private MediaPlayer mediaPlayer;

    public void play(Context context, int resId) {
        play(context, resId, null, null);
    }

    public void play(Context context, int resId, MediaPlayer.OnCompletionListener completionListener) {
        play(context, resId, completionListener, null);
    }

    public void play(Context context, int resId, MediaPlayer.OnErrorListener errorListener) {
        play(context, resId, null, errorListener);
    }

    public void play(Context context, int resId, MediaPlayer.OnCompletionListener completionListener, MediaPlayer.OnErrorListener errorListener) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(context.getApplicationContext(), resId);
        mediaPlayer.setOnCompletionListener(completionListener);
        mediaPlayer.setOnErrorListener(errorListener);
        mediaPlayer.start();
    }
    public void Stop()
    {
        mediaPlayer.stop();
    }
    public void playLooped()
    {
        mediaPlayer.setLooping(true);
    }
}