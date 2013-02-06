package com.badlogic.androidgames.framework.impl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.Music;
import com.badlogic.androidgames.framework.Sound;

public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        // we don't release the sound pool, instead we let the activity
        // destroy it as soon as the game (activity) ends.
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    public Music newMusic(String fileName) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e) {
            // throw unchecked exception at this point since it's a
            // programming error and we don't want to clutter calling code
            throw new RuntimeException("Couldn't load music '" + fileName + "'");
        }
    }

    public Sound newSound(String fileName) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            // throw unchecked exception at this point since it's a
            // programming error and we don't want to clutter calling code
            throw new RuntimeException("Couldn't load sound '" + fileName + "'");
        }
    }
}
