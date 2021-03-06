package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {270, 280, 240, 500, 400, 250};
    public static int[] heroesDamage = {20, 15, 25, 0, 10,20};
    public static String[] heroesType = {"Physical", "Magical", "Kinetic", "Medic",
            "Golem", "Lucky" };

    public static void Lucky() {
        Random ais = new Random();
        boolean k = ais.nextBoolean();
        if (heroesHealth[5]>0) {
            if (k) heroesHealth[5] += bossDamage;
            System.out.println("Lucky увернулся от удара" + k);
        }

    }


    public static void golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0){
                heroesHealth[4] -= bossDamage * 1/5;
                heroesHealth[i] += bossDamage * 1/5;
                System.out.println("golem get  "  + bossDamage * 1/5);


            }

        }


    }

    public static void medicHeal() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] += 100;
                System.out.println("Medic heal " + heroesType[i]);
                break;
            }
        }
    }


    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {

            round();
            golem();
            medicHeal();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesType.length);
        bossDefence = heroesType[randomIndex];

        System.out.println("Boss choose " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseBossDefence();
        if (bossHealth > 0) { // РЅР° РІСЃСЏРєРёР№ СЃР»СѓС‡Р°Р№
            bossHits();
        }
        heroesHit();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(10);
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }


        }

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }

        }


    }
    public static void printStatistics() {
        System.out.println(round_number + " ROUND ______________");
        System.out.println("Boss health: " + bossHealth
                + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesType[i]
                    + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }
        System.out.println("____________________");


    }
}