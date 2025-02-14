package vn.edu.fpt.groupminiproject.models;

import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.Serializable;

public class Animal implements Serializable {
    private String displayName;
    private SeekBar seekBar;

    public Animal(String displayName, SeekBar seekBar) {
        this.displayName = displayName;
        this.seekBar = seekBar;
    }

    public String getDisplayName() {
        return displayName;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public int getRandomSpeed() {
        // Implement logic to generate a random speed for the animal from 1 - 5
        int speed = (int) (Math.random() * 5) + 1;
        return Math.min(speed, 5);
    }
}
