package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import static com.company.Main.CARS_COUNTS;

public class Car implements Runnable {

    private static CyclicBarrier waitCars = new CyclicBarrier (CARS_COUNTS);
    public static final CountDownLatch leatsGoRacing = new CountDownLatch(CARS_COUNTS);
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
            waitCars.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
