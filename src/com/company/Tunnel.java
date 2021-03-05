package com.company;

import java.util.concurrent.Semaphore;

import static com.company.Main.CARS_COUNTS;

public class Tunnel extends Stage {

    private static Semaphore racingTunnel = new Semaphore(CARS_COUNTS/2);

    public Tunnel() {
        this.length = 80.0f;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                racingTunnel.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep((long) (length / c.getSpeed() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                racingTunnel.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
