package com.taxi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



// Базовий клас автомобіля
class Car {
    private String brand;
    private double fuelConsumption;
    private double cost; // Добавлено поле для стоимости автомобиля

    public Car(String brand, double fuelConsumption, double cost) {
        this.brand = brand;
        this.fuelConsumption = fuelConsumption;
        this.cost = cost;
    }

    public String getBrand() {
        return brand;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", cost=" + cost +
                '}';
    }
}

// Клас легкового автомобіля, що наслідується від базового класу Car
class Sedan extends Car {
    private int maxSpeed;

    public Sedan(String brand, double fuelConsumption, double cost, int maxSpeed) {
        super(brand, fuelConsumption, cost);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "Sedan{" +
                "brand='" + getBrand() + '\'' +
                ", fuelConsumption=" + getFuelConsumption() +
                ", cost=" + getCost() +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}

// Клас таксопарку
class TaxiPark {
    private List<Car> cars;

    public TaxiPark(List<Car> cars) {
        this.cars = cars;
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Car car : cars) {
            totalCost += car.getCost();
        }
        return totalCost;
    }

    public void sortByFuelConsumption() {
        cars.sort(Comparator.comparingDouble(Car::getFuelConsumption));
    }

    public Car findCarBySpeedRange(int minSpeed, int maxSpeed) {
        for (Car car : cars) {
            if (car instanceof Sedan) {
                Sedan sedan = (Sedan) car;
                if (sedan.getMaxSpeed() >= minSpeed && sedan.getMaxSpeed() <= maxSpeed) {
                    return sedan;
                }
            }
        }
        return null;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("TaxiPark{\n");
        for (Car car : cars) {
            result.append("  ").append(car).append('\n');
        }
        result.append('}');
        return result.toString();
    }
}

public class Taksopark {
    public static void main(String[] args) {
        // Створення об'єктів легкових автомобілів
        Sedan sedan1 = new Sedan("Toyota", 8.5, 12000, 180);
        Sedan sedan2 = new Sedan("Honda", 7.8, 15000, 200);
        Sedan sedan3 = new Sedan("Ford", 9.2, 11000, 170);

        // Створення списку автомобілів і додавання до нього
        List<Car> carList = new ArrayList<>();
        carList.add(sedan1);
        carList.add(sedan2);
        carList.add(sedan3);

        // Створення таксопарку
        TaxiPark taxiPark = new TaxiPark(carList);

        // Розрахунок вартості автопарку
        double totalCost = taxiPark.calculateTotalCost();
        System.out.println("Total Cost of the Taxi Park: $" + totalCost);

        // Сортування автомобілів за витратами палива
        taxiPark.sortByFuelConsumption();
        System.out.println("Cars sorted by fuel consumption: " + taxiPark);

        // Знайти автомобіль у компанії, що відповідає заданому діапазону параметрів швидкості
        Car carInSpeedRange = taxiPark.findCarBySpeedRange(180, 190);
        System.out.println("Car in speed range(180-190): " + carInSpeedRange);
    }
}

