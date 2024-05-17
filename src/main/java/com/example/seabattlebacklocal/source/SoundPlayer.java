package com.example.seabattlebacklocal.source;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer {
    String filePath = "src/main/java/com/example/seabattlebacklocal/source/sounds/328713-Ambience_Exterior_Wave_Boulders_Pier_Between_Rocks_More_Distant_Waves_Hard_Loop.wav";
    private MediaPlayer mediaPlayer;

    public SoundPlayer(float volume) {
        // Initialize the JavaFX toolkit if it's not already initialized
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {
                mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
                mediaPlayer.setVolume(volume);
            });
        } else {
            mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
            mediaPlayer.setVolume(volume);
        }
    }

    public void playSound() {
        Platform.runLater(() -> mediaPlayer.play());
    }

    public void setSound(String filePath) {
        this.filePath = filePath;
    }

    public void playSoundContinuously() {
        Platform.runLater(() -> {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        });
    }
}
