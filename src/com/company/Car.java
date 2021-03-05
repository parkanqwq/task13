package com.company;

import java.util.concurrent.CountDownLatch;

import static com.company.Main.*;

public class Car implements Runnable {

    private static CountDownLatch leatsGoRacing = new CountDownLatch(CARS_COUNTS);
    private static int coreWin = 0;
    private Race race;
    private float speed;
    private String name;
    private boolean winner = false;

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getName() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }

    public Car(Race race, float speed, int name) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + name;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            leatsGoRacing.countDown();
            leatsGoRacing.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        coreWin++;
        name = name + " занял " + coreWin + " место!";
        if (coreWin == 1) {
            setWinner(true);
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> " + name);
        }
        if (coreWin == CARS_COUNTS) {
            System.out.println();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            System.out.println();
            for (int i = 0; i < cars.length; i++) {
                String winner = " Winner";
                String notWinner = " Not winner";
                if (cars[i].isWinner()) System.out.println(cars[i].getName() + winner);
                else System.out.println(cars[i].getName() + notWinner);
            }
        }
    }

    public static CountDownLatch getLeatsGoRacing() {
        return leatsGoRacing;
    }
}
