package com.company;

import java.util.concurrent.CountDownLatch;

public class Road extends Stage {

    public static final CountDownLatch finishRacing = new CountDownLatch(8);
    public static int winnerCar = 0;

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            finishRacing.countDown();
            winnerCar++;
            if (winnerCar == 5) c.setWinner(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
