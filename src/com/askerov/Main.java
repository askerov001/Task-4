package com.askerov;

import java.util.Random;

// Добавить 4-го игрока Medic,у которого есть способоность лечить после
//// каждого раунда на N-ное количество единиц здоровья только одного из
////членов команды, имеющего здоровья не менее 100 единиц. Мертвых героев медик
//// оживльять не может, и лечит он до тех пор пока жив сам. Медик не
//// участвует в бою, но получает урон от Босса

//ДЗ на сообразительность:
//● Добавить n-го игрока, Golem, который имеет увеличенную жизнь но слабый удар. Может принимать на
// себя 1/5 часть урона исходящего от босса по другим игрокам.
//● Добавить n-го игрока, Lucky, имеет шанс уклонения от ударов босса.
//● Добавить n-го игрока, Berserk, блокирует часть удара босса по себе и прибавляет
//заблокированный урон к своему урону и возвращает его боссу
//● Добавить n-го игрока, Thor, удар по боссу имеет шанс оглушить босса на 1 раунд,
//вследствие чего босс пропускает 1 раунд и не наносит урон героям. Дедлайн 04.03.2021
//Подсказка использовать Switch case//

public class Main {

    public static int[] heroesHealth = {270, 280, 250, 200};
    // Создали массив их всего лиш 3. То есть 3 гером с здровоьем
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static String[] hereosAttackType = {"Physykal",
            "Magical", "Kinetic", "Medic"};

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefensType = "";
    public static int roundNumber = 0;


    public static void main(String[] args) {
        // write your code here
        printStatistics();
        System.out.println("The game is started");
        while (!isGameFinished()) {
            round();

        }

    }
//    public static boolean isTreat(){
//        boolean treat = false;
//        for (int i = 0; i < heroesHealth.length; i++) {
//            if (heroesHealth[i] < 0){
//                treat = false;
//            }else if (heroesHealth [i] < 0){
//                treat=false;
//            }else if (heroesHealth[i] > 0 ){
//                heroesHealth[i] = heroesHealth[i]+ 100;
//                treat = true;
//                System.out.println("Medic treated: " + heroesHealth[i]);
//
//            }
//
//        }
//        return treat;
//    }

    public static void round() {
        roundNumber++;
        System.out.println("______Round - " + roundNumber
                + "________");
        bossDefensType = changeBossDefense();
        System.out.println("Hero " + bossDefensType + " Boss receive supper damage");
        bossHits();
        heroesHits();
        medicHealth();
        printStatistics();

    }

    public static boolean isGameFinished() {
//        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
//                heroesHealth[2] <= 0) {
//            System.out.println("Boss win");
//            return true;
//        } // Hard code

        if (bossHealth <= 0) {
            System.out.println("Heroes Win");
            return true;
        }

        boolean allHeroesDead = true;

        for (int hero : heroesHealth) {
            if (hero > 0) {
                allHeroesDead = false;
                break;
            }

        }

        if (allHeroesDead) {
            System.out.println("Boss Win");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            }
        }
    }

    public static void heroesHits() {
        Random random = new Random();

        int coeff = random.nextInt(8) + 2; //2,3,4,5,6,7,8,9,10
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (hereosAttackType[i] == bossDefensType) {
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Supper damage" + heroesDamage[i]
                            * coeff + "[" + coeff + "]");

                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
                if (bossHealth < 0) {
                    bossHealth = 0;
                }
            }
        }
    }

    public static String changeBossDefense() {
        Random random = new Random();
        int randomIndex = random.nextInt(hereosAttackType.length);
        return hereosAttackType[randomIndex];

    }

    public static void printStatistics() {
        System.out.println("Boss heath: " + bossHealth +
                "[" + bossHealth + "]");
        for (int i = 0; i < hereosAttackType.length; i++) {
            System.out.println(hereosAttackType[i] + " health: " +
                    heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
    }
    public static void medicHealth(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[3] > 0 && heroesHealth[i] > 0) {
                heroesHealth[i] += new Random().nextInt(100);
                return;
            }
        }
    }
}
