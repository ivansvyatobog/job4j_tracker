package ru.job4j.poly;

public class VehicleRunner {
    public static void main(String[] args) {
        Plane plane = new Plane();
        Bus bus = new Bus();
        Train train = new Train();
        Vehicle pl = plane;
        Vehicle bs = bus;
        Vehicle tr = train;
        Vehicle[] transports = new Vehicle[] {pl, bs, tr};
        for (Vehicle transport : transports) {
            transport.move();
        }
    }
}
