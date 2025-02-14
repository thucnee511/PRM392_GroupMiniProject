package vn.edu.fpt.groupminiproject.repositories;

import android.widget.SeekBar;

import vn.edu.fpt.groupminiproject.models.Animal;

public class AnimalRepository {
    private static AnimalRepository instance;
    private final Animal giraffe;
    private final Animal lion;
    private final Animal squirrel;

    private AnimalRepository(SeekBar giraffeSeekBar, SeekBar lionSeekBar, SeekBar squirrelSeekBar) {
        giraffe = new Animal("Giraffe", giraffeSeekBar);
        lion = new Animal("Lion", lionSeekBar);
        squirrel = new Animal("Squirrel", squirrelSeekBar);
    }

    public static AnimalRepository getInstance(SeekBar giraffeSeekBar, SeekBar lionSeekBar, SeekBar squirrelSeekBar) {
        if (instance == null) {
            instance = new AnimalRepository(giraffeSeekBar, lionSeekBar, squirrelSeekBar);
        }
        return instance;
    }

    public Animal getGiraffe() {
        return giraffe;
    }

    public Animal getLion() {
        return lion;
    }

    public Animal getSquirrel() {
        return squirrel;
    }
}
