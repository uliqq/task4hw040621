package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 4000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {450, 350, 300, 400, 1000, 300, 400, 500};
    public static int[] heroesDamage = {45, 35, 30, 0, 15, 30, 25, 35};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Healing", "Tanky", "Dodgy", "Berserk", "Electric"};
    public static String[] heroesNames = {"Warrior", "Magician", "Kinetic", "Healer", "Tank", "Dodger", "Berserk", "Thor"};

    public static void main(String[] args) {

        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        changeBossDefence();
        bossHit();
        heroesHit();
        fightInfo();
    }

    public static void changeBossDefence() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        } else if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0 && heroesHealth[4] <= 0 && heroesHealth[5] <= 0 && heroesHealth[6] <= 0 && heroesHealth[7] <= 0) {
            System.out.println("Boss won!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[3]) {
                    continue;
                }
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int odd = random2.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * odd < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * odd;
                    }
                    System.out.println(heroesAttackType[i] + " critical hit: " + heroesDamage[i] * odd + "\n");
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }


        if (heroesHealth[4] <= 0) {
            heroesHealth[4] = 0;
        }

        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[5] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[5])) {
                    heroesHealth[5] = heroesHealth[5] + 50;
                    heroesHealth[4] = heroesHealth[4] + 5;

                    System.out.println("Dodger successfully dodged Boss's hit!");
                    System.out.print("\n");
                    break;
                }
            }
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[6] > 0) {
                heroesHealth[6] = heroesHealth[6] + 15;
                bossHealth = bossHealth - 15;
                System.out.println(heroesNames[6] + " blocked and returned 15 damage to the Boss!\n");
                break;
            }
            if (heroesHealth[6] <= 0) {
                heroesHealth[6] = 0;
            }

        }
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0 && heroesHealth[4] > 0) {

                heroesHealth[4] = heroesHealth[4] - 5;
                heroesHealth[i] = heroesHealth[i] + 5;
                System.out.println("Tank took Boss's 5 damage to himself from " + heroesNames[i]);
            }
        }
        System.out.print("\n");
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0 && heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + 25;
                System.out.println(heroesNames[i] + " got healed by Healer: 25");

            }
        }
        System.out.println("\n");
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[7] > 0) {
                if (bossDefenceType.equals(heroesAttackType[7])) {
                    heroesHealth[i] = heroesHealth[i] + bossDamage - 5;
                    heroesHealth[4] = heroesHealth[4] + 5;
                    heroesHealth[6] = heroesHealth[6] - 15;
                    bossHealth = bossHealth + 15;
                    System.out.println(heroesNames[7] + " successfully stunned the Boss for a round!");
                }
            }
        }
    }

    // Статистика боя
    public static void fightInfo() {
        System.out.println("______________________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magician health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Healer health: " + heroesHealth[3]);
        System.out.println("Tank health: " + heroesHealth[4]);
        System.out.println("Dodger health: " + heroesHealth[5]);
        System.out.println("Berserk health: " + heroesHealth[6]);
        System.out.println("Thor health: " + heroesHealth[7]);
        System.out.println("______________________________");

    }
}