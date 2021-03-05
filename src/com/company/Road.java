package com.company;

import java.util.concurrent.CountDownLatch;

public class Road extends Stage {

    private static CountDownLatch finishRacing = new CountDownLatch(8);

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
        if (finishRacing.getCount() == 3) {
            c.setWinner(true);
            System.out.println();
            System.out.println(c.getName() + " выйграл гонку заняв ПЕРВОЕ МЕСТО!");
            System.out.println();
        }
        if (finishRacing.getCount() == 3) c.setName(c.getName() + " - занял 1 место,");
        if (finishRacing.getCount() == 2) c.setName(c.getName() + " - занял 2 место,");
        if (finishRacing.getCount() == 1) c.setName(c.getName() + " - занял 3 место,");
        if (finishRacing.getCount() == 0) c.setName(c.getName() + " - занял 4 место,");
    }

    public static CountDownLatch getFinishRacing() {
        return finishRacing;
    }
}
