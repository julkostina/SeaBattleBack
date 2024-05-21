//package com.example.seabattlebacklocal.source;
//import javafx.application.Platform;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import java.io.File;
//
//public class SoundPlayer {
//    String filePath = "src/main/java/com/example/seabattlebacklocal/source/sounds/328713-Ambience_Exterior_Wave_Boulders_Pier_Between_Rocks_More_Distant_Waves_Hard_Loop.wav";
//    private MediaPlayer mediaPlayer;
//
//    public SoundPlayer(float volume) {
//        // Initialize the JavaFX toolkit if it's not already initialized
//        if (!Platform.isFxApplicationThread()) {
//            Platform.startup(() -> {
//                mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
//                mediaPlayer.setVolume(volume);
//            });
//        } else {
//            mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
//            mediaPlayer.setVolume(volume);
//        }
//    }
//
//    public void playSound() {
//        Platform.runLater(() -> mediaPlayer.play());
//    }
//
//    public void setSound(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public void playSoundContinuously() {
//        Platform.runLater(() -> {
//            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//            mediaPlayer.play();
//        });
//    }
//}


package com.example.seabattlebacklocal.source;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer {
    private static SoundPlayer instance;
    private MediaPlayer mediaPlayer;
    private String filePath = "src/main/java/com/example/seabattlebacklocal/source/sounds/328713-Ambience_Exterior_Wave_Boulders_Pier_Between_Rocks_More_Distant_Waves_Hard_Loop.wav";

    private SoundPlayer() {
        // Initialize the JavaFX toolkit if it's not already initialized
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> initializeMediaPlayer());
        } else {
            initializeMediaPlayer();
        }
    }

    public static  SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    private void initializeMediaPlayer() {
        mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
        mediaPlayer.setVolume(1.0); // Default volume
    }

    public void playSound() {
        Platform.runLater(() -> {
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                mediaPlayer.play();
            }
        });
    }

    public void setSound(String filePath) {
        this.filePath = filePath;
        Platform.runLater(() -> {
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
        });
    }

    public void playSoundContinuously() {
        Platform.runLater(() -> {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                mediaPlayer.play();
            }
        });
    }

    public void setVolume(float volume) {
        Platform.runLater(() -> mediaPlayer.setVolume(volume));
    }

    public void stopSound() {
        Platform.runLater(() -> mediaPlayer.stop());
    }
}