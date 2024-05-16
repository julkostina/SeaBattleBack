package com.example.seabattlebacklocal.source;

import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer {
    private String soundFileName;
    private int volume;
    public SoundPlayer(String soundFileName, int volume ) {
        this.soundFileName = soundFileName;
        this.volume = volume;
    }
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Control the volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); 

            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}