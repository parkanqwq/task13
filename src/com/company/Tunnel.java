package com.company;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private static Semaphore racingTunnel = new Semaphore(2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                racingTunnel.acquire(1);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                racingTunnel.release(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
