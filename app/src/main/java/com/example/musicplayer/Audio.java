package com.example.musicplayer;

import android.net.Uri;

public class Audio {

    String name;
    Uri audioUri;

    public Audio(String name, Uri audioUri) {
        this.name = name;
        this.audioUri = audioUri;
    }
}
