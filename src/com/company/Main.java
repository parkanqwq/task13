package com.company;


import static com.company.Car.leatsGoRacing;
import static com.company.Road.finishRacing;
import static com.company.Road.winnerCar;

public class Main {

    public static final int CARS_COUNT = 4;
    public static String winner = " Winner";
    public static String notWinner = " Not winner";

    public static void main(String[] args) throws InterruptedException {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        leatsGoRacing.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        finishRacing.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        for (int i = 0; i < cars.length; i++) {
            if (cars[i].isWinner()) System.out.println(cars[i].getName() + winner);
            else System.out.println(cars[i].getName() + notWinner);
        }
    }
}

