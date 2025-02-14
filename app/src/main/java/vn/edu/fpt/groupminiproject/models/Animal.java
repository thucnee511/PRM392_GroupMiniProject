package vn.edu.fpt.groupminiproject.models;

import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.Serializable;

public class Animal implements Serializable {
    private String displayName;
    private SeekBar seekBar;
    private ImageView badge;

    public Animal(String displayName, SeekBar seekBar, ImageView badge) {
        this.displayName = displayName;
        this.seekBar = seekBar;
        this.badge = badge;
    }

    public String getDisplayName() {
        return displayName;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public ImageView getBadge() {
        return badge;
    }

    public int getRandomSpeed() {
        // Implement logic to generate a random speed for the animal from 1 - 3
        int speed = (int) (Math.random() * 3) + 1;
        return Math.min(speed, 3);
    }
}
