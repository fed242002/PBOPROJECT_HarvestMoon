package Main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3; // 0 = kecil 1 = lumayan 2 = oke besar 3 - 5 besar
    float volume;

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80.0f; // Very low volume
                break;
            case 1:
                volume = -20.0f; // low volume
                break;
            case 2:
                volume = -12.0f; // kinda low
                break;
            case 3:
                volume = -5.0f; // ok
                break;
            case 4:
                volume = 1f; // ok big
                break;
            case 5:
                volume = 6f; // Very big
                break;
        }
        if (fc != null) {
            fc.setValue(volume);
        }
    }
}
