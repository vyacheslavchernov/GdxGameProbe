package com.vych.game.managers.resources.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicResource extends BasicResource {
    @Override
    public Music getContentCasted() {
        return (Music) this.content;
    }

    @Override
    public GameResource load() {
        checkResourceType(ResourceType.MUSIC);
        this.content = Gdx.audio.newMusic(Gdx.files.internal(this.path));
        loaded = true;
        return this;
    }
}
