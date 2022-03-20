package edu.umb.cs681.hw02;

import java.util.ArrayList;

public class Main {
	
	 public static void main(String[] args) {

	        ArrayList<Car> cars = new ArrayList<>();

	        Car car1 = new Car("BMW","X7", 2022, 8, 74900);
	        Car car2 = new Car("Lexus", "NX250", 2020, 15, 39500);
	        Car car3 = new Car("Tesla", "Model S", 2021, 20, 85000);
	        Car car4 = new Car("Ford","Ranger", 2018, 12, 40000);
	        Car car5 = new Car("Audi", "Q7",2021, 10, 61500);

	        cars.add(car1);
	        cars.add(car2);
	        cars.add(car3);
	        cars.add(car4);
	        cars.add(car5);

	        double minimum = cars.stream().mapToDouble(Car::getPrice).min().getAsDouble();
	        System.out.println("Minimum price of the car, " + minimum);

	        double maximum= cars.stream().mapToDouble(Car::getPrice).max().getAsDouble();
	        System.out.println("Maximum price of the car, " + maximum);


	        Integer averagePrice = cars.stream().map(car-> car.getPrice()).reduce(new int[2], (result, price) ->{
	            double average = Math.round((result[0] * result[1] + price)/(result[0]+1));
	            result[0]++;
	            result[1] = (int) average;
	            return result;},(finalResult, intermediateResult) -> finalResult)[1];

	        System.out.println("Average price of cars: " + averagePrice);

	    }
}
