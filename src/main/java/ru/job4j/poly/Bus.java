package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void ride() {
        System.out.println("Bus is riding");
    }

    @Override
    public void passengers(int count) {
        System.out.println("Количество пассажиров в автобусе : " + count);
    }

    @Override
    public int refuel(int fuelValue) {
        return fuelValue * 48;
    }
}
