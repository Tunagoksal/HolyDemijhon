package com.holydemijon.Tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager extends AssetManager {

    private Music music;
    public static float volume;

    public AudioManager() {
        this.load("audio/musics/main_menu_music.mp3", Music.class);
        this.load("audio/musics/level_1_music.mp3", Music.class);
        this.load("audio/musics/level_2_music.mp3", Music.class);
        this.load("audio/musics/level_3_music.mp3", Music.class);
        this.load("audio/musics/game_ending_music.mp3", Music.class);

        this.load("audio/sound_effects/button_click_sound.mp3", Sound.class);
        this.load("audio/sound_effects/dash_sound.mp3", Sound.class);
        this.load("audio/sound_effects/simple_attack_sound.mp3", Sound.class);
        this.load("audio/sound_effects/heavy_attack_sound.mp3", Sound.class);
        this.load("audio/sound_effects/jumping_sound.mp3", Sound.class);
        this.load("audio/sound_effects/spike_sound.mp3", Sound.class);
        this.load("audio/sound_effects/trampoline_sound.mp3", Sound.class);
        this.load("audio/sound_effects/bear_trap_sound.mp3", Sound.class);
        this.load("audio/sound_effects/chest_sound.mp3", Sound.class);
        this.load("audio/sound_effects/john_takedamage_sound.mp3", Sound.class);
        this.load("audio/sound_effects/john_dying_sound.mp3", Sound.class);
        this.load("audio/sound_effects/zombie_takedamage_sound.mp3", Sound.class);
        this.load("audio/sound_effects/orc_wizard_takedamage_sound.mp3", Sound.class);

        this.finishLoading();

        volume = 1;
        music = this.get("audio/musics/main_menu_music.mp3", Music.class);
        music.setLooping(true);
        music.play();
    }

    public void playMusic(int musicIndex) {

        if (musicIndex == 0) {
            music.stop();
            music = this.get("audio/musics/main_menu_music.mp3", Music.class);
            music.setLooping(true);
            music.setVolume(volume);
            music.play();
        }
        else if (musicIndex == 1) {
            music.stop();
            music = this.get("audio/musics/level_1_music.mp3", Music.class);
            music.setLooping(true);
            music.setVolume(volume);
            music.play();
        }
        else if (musicIndex == 2) {
            music.stop();
            music = this.get("audio/musics/level_2_music.mp3", Music.class);
            music.setLooping(true);
            music.setVolume(volume);
            music.play();
        }
        else if (musicIndex == 3) {
            music.stop();
            music = this.get("audio/musics/level_3_music.mp3", Music.class);
            music.setLooping(true);
            music.setVolume(volume);
            music.play();
        }
        else if (musicIndex == 4) {
            music.stop();
            music = this.get("audio/musics/game_ending_music.mp3", Music.class);
            music.setLooping(true);
            music.setVolume(volume);
            music.play();
        }
    }

    public void playSound(int soundIndex) {
        if (soundIndex == 0)
            this.get("audio/sound_effects/button_click_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 1)
            this.get("audio/sound_effects/dash_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 2)
            this.get("audio/sound_effects/simple_attack_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 3)
            this.get("audio/sound_effects/heavy_attack_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 4)
            this.get("audio/sound_effects/jumping_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 5)
            this.get("audio/sound_effects/spike_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 6)
            this.get("audio/sound_effects/trampoline_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 7)
            this.get("audio/sound_effects/bear_trap_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 8)
            this.get("audio/sound_effects/chest_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 9)
            this.get("audio/sound_effects/john_takedamage_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 10)
            this.get("audio/sound_effects/john_dying_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 11)
            this.get("audio/sound_effects/zombie_takedamage_sound.mp3", Sound.class).play(volume);
        else if (soundIndex == 12)
            this.get("audio/sound_effects/orc_wizard_takedamage_sound.mp3", Sound.class).play(volume);
    }

    public void setGameVolume(float volume) {
        this.volume = volume;
        music.setVolume(volume);
    }
}
