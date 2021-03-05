package com.company;

public class Main {

    public static final int CARS_COUNTS = 4;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNTS];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), i+1);
        }

        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i].getSpeed() + " - скорость " + (i+1) + " участника");
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        Car.getLeatsGoRacing().await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        Road.getFinishRacing().await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        for (int i = 0; i < cars.length; i++) {
            String winner = " Winner";
            String notWinner = " Not winner";
            if (cars[i].isWinner()) System.out.println(cars[i].getName() + winner);
            else System.out.println(cars[i].getName() + notWinner);
        }
    }
}

