package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

  public static final double MIN_DAMAGE_MULTIPLAYER = 0.1;
  public static final double MAX_DAMAGE_MULTIPLIER = 0.5;
  private final Gladiator gladiator1;
  private final Gladiator gladiator2;
  private final List<String> combatLog;
  private boolean isGladiator1Turn;

  public Combat(Contestants contestants) {
    this.gladiator1 = contestants.gladiator1;
    this.gladiator2 = contestants.gladiator2;
    this.combatLog = new ArrayList<>();
    this.isGladiator1Turn = RandomUtils.getRandomBoolean();
  }

  public Gladiator getGladiator1() {
    return gladiator1;
  }

  public Gladiator getGladiator2() {
    return gladiator2;
  }

  public String getCombatLog(String separator) {
    return String.join(separator, combatLog);
  }

  /**
   * Simulates the combat and returns the winner.
   * If one of the opponents is null, the winner is the one that is not null
   * If both of the opponents are null, the return value is null
   *
   * @return winner of combat
   */
  public Gladiator simulate() {
    if (gladiator1 == null)
      return gladiator2;
    if (gladiator2 == null)
      return gladiator1;

    boolean isSomeoneDead;
    do {
      simulateTurn();
      isSomeoneDead = (gladiator1.isDead() || gladiator2.isDead());
    } while (!isSomeoneDead);
    return pickWinner();
  }

  private void simulateTurn() {
    Gladiator actualAttacker = isGladiator1Turn ? gladiator1 : gladiator2;
    Gladiator actualDefender = isGladiator1Turn ? gladiator2 : gladiator1;
    int percentageChanceForHitting = getPercentageChanceForHitting(actualAttacker, actualDefender);

    if (RandomUtils.isSuccess(percentageChanceForHitting)) {
      hit(actualAttacker, actualDefender);
    } else {
      miss(actualAttacker);
    }
    isGladiator1Turn = !isGladiator1Turn;
  }

  private Gladiator pickWinner() {
    Gladiator winner = gladiator1.isDead() ? gladiator2 : gladiator1;
    Gladiator looser = gladiator1.isDead() ? gladiator1 : gladiator2;
    combatLog.add(String.format("%s dies, %s wins!", looser.getName(), winner.getName()));

    return winner;
  }

  private int getPercentageChanceForHitting(Gladiator actualAttacker, Gladiator actualDefender) {
    int attackerDex = actualAttacker.getMaxDex();
    int defendersDex = actualDefender.getMaxDex();

    int dexDifference = attackerDex - defendersDex;
    int percentageChanceForHitting = Math.max(10, dexDifference);
    return Math.min(100, percentageChanceForHitting);
  }

  private void hit(Gladiator actualAttacker, Gladiator actualDefender) {
    int attackerAttack = getAttackerDamage(actualAttacker);
    actualDefender.decreaseHpBy(attackerAttack);
    combatLog.add(String.format("%s deals %s damage.", actualAttacker.getName(), attackerAttack));
  }

  private void miss(Gladiator actualAttacker) {
    combatLog.add(String.format("%s missed.", actualAttacker.getName()));
  }

  private int getAttackerDamage(Gladiator actualAttacker) {
    return (int) (actualAttacker.getMaxSp() * RandomUtils.getRandomDoubleFromRange(MIN_DAMAGE_MULTIPLAYER, MAX_DAMAGE_MULTIPLIER));
  }

}
