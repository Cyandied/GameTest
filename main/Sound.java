package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL sound_url[] = new URL[30];

    public Sound(){

        sound_url[0] = getClass().getResource("/res/sound/theme.wav");
        sound_url[1] = getClass().getResource("/res/sound/coin.wav");
        sound_url[2] = getClass().getResource("/res/sound/powerup.wav");
        sound_url[3] = getClass().getResource("/res/sound/unlock.wav");
        sound_url[4] = getClass().getResource("/res/sound/fanfare.wav");

    }

    public void set_file(int i){

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(sound_url[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            
        } catch (Exception e) {

        }

    }

    public void play() {

        clip.start();

    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop(){

        clip.stop();

    }

}
