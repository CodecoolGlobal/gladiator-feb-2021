package com.codecool.gladiator.model.gladiators;

import com.codecool.gladiator.util.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GladiatorFactory {

    private static final int LOWER_STAT_VALUE = 25;
    private static final int UPPER_STAT_VALUE = 100;
    private static final int LOWER_LEVEL_VALUE = 1;
    private static final int UPPER_LEVEL_VALUE = 5;

    private List<String> names;


    public GladiatorFactory(String fileOfNames) {
        try {
            File file = new File(getClass().getClassLoader().getResource(fileOfNames).getFile());
            names = Files.readAllLines(file.toPath());
        } catch (IOException|NullPointerException e) {
            System.out.println("Names file not found or corrupted!");
            System.exit(1);
        }
    }

    /**
     * Picks a random name from the file given in the constructor
     *
     * @return gladiator name
     */
    private String getRandomName() {
        return RandomUtils.getRandomItemFromList(names);
    }

    /**
     * Instantiates a new gladiator with random name and type.
     * Creating an Archer, an Assassin, or a Brutal has the same chance,
     * while the chance of creating a Swordsman is the double of the chance of creating an Archer.
     * @return new Gladiator
     */
    public Gladiator generateRandomGladiator() {
        int randomHp = RandomUtils.getRandomNumberFromRange(LOWER_STAT_VALUE, UPPER_STAT_VALUE);
        int randomSp = RandomUtils.getRandomNumberFromRange(LOWER_STAT_VALUE, UPPER_STAT_VALUE);
        int randomDex = RandomUtils.getRandomNumberFromRange(LOWER_STAT_VALUE, UPPER_STAT_VALUE);
        int randomLevel = RandomUtils.getRandomNumberFromRange(LOWER_LEVEL_VALUE, UPPER_LEVEL_VALUE);

        int randomGladiatorIndicator = RandomUtils.getRandomNumberFromRange(1,5);
        switch (randomGladiatorIndicator){
            case 1:
            case 2:
                return new Swordsman(getRandomName(), randomHp, randomSp, randomDex, randomLevel);
            case 3:
                return new Archer(getRandomName(), randomHp, randomSp, randomDex, randomLevel);
            case 4:
                return new Assassin(getRandomName(), randomHp, randomSp, randomDex, randomLevel);
            default:
                return new Brutal(getRandomName(), randomHp, randomSp, randomDex, randomLevel);
        }
    }
}
