package com.codecool.gladiator.util;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static int getRandomNumberFromRange(int boundary){
        return RANDOM.nextInt(boundary);
    }

    public static int getRandomNumberFromRange(int lowerBoundary, int upperBoundary){
        return RANDOM.nextInt(upperBoundary - lowerBoundary + 1) + lowerBoundary;
    }

    public static <T> T getRandomItemFromList(List<T> list){
        int randomInt = getRandomNumberFromRange(list.size());
        return list.get(randomInt);
    }


}
