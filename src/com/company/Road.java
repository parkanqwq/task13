package com.company;

import java.util.concurrent.CountDownLatch;

public class Road extends Stage {

    private static CountDownLatch finishRacing = new CountDownLatch(8);
    private static int winnerCar = 0;

    public Road(float length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep((long) (length / c.getSpeed() * 1000));
            System.out.println(c.getName() + " закончил этап: " + description);
            finishRacing.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        winnerCar++;
        if (winnerCar == 5) c.setWinner(true);
        if (winnerCar == 5) c.setName(c.getName() + " - занял 1 место,");
        if (winnerCar == 6) c.setName(c.getName() + " - занял 2 место,");
        if (winnerCar == 7) c.setName(c.getName() + " - занял 3 место,");
        if (winnerCar == 8) c.setName(c.getName() + " - занял 4 место,");
    }

    public static CountDownLatch getFinishRacing() {
        return finishRacing;
    }
}
