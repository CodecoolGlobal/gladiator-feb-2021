package com.codecool.gladiator.util;

import java.util.List;
import java.util.Random;

public class RandomUtils {

  private static final Random RANDOM = new Random();

  public static int getRandomNumberFromRange(int lowerBoundary, int upperBoundary) {
    return RANDOM.nextInt(upperBoundary - lowerBoundary + 1) + lowerBoundary;
  }

  public static <T> T getRandomItemFromList(List<T> list) {
    int randomInt = getRandomNumberFromRange(list.size());
    return list.get(randomInt);
  }

  public static int getRandomNumberFromRange(int boundary) {
    return RANDOM.nextInt(boundary);
  }

  public static double getRandomDoubleFromRange(double lowerBoundary, double upperBoundary) {
    return RANDOM.nextDouble() * (upperBoundary - lowerBoundary) + lowerBoundary;
  }

  public static boolean getRandomBoolean() {
    return RANDOM.nextBoolean();
  }

  public static boolean isSuccess(int successPercentageChance) {
    int randomNumber = RANDOM.nextInt(101);
    return randomNumber <= successPercentageChance;
  }
}
