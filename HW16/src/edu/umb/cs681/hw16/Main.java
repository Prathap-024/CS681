package edu.umb.cs681.hw16;

import java.util.ArrayList;

public class Main {
	
	 public static void main(String[] args) {

	        ArrayList<Car> cars = new ArrayList<>();
	        cars.add(new Car("Acura", "M20", 2019, 20, 8_000f));
	        cars.add(new Car("Ford", "RANGER", 2017, 15, 6_000f));
	        cars.add(new Car("Honda", "civic", 2010, 20, 9_000f));
	        cars.add(new Car("Volkswagon", "Golf", 2002, 25,3_000f));
	        cars.add(new Car("Benz", "Cls220", 2020, 10, 8_000f));
	        cars.add(new Car("Audi", "A5", 2019, 18, 5_000f));
	        cars.add(new Car("Chevrolet", "Malibu", 2009, 20, 1_000f));
	        cars.add(new Car("BMW", "X5", 2012, 28, 12_000f));

	        Integer carMakeNum = cars
	                .stream()
	                .parallel()
	                .map((Car car) -> car.getMake())
	                .reduce(
	                        0,
	                        (result, make) -> ++result,
	                        (finalResult, intermediateResult) -> finalResult + intermediateResult
	                );

	        System.out.println("Number of Car Makers: " + carMakeNum);

	        Integer carModelNum = cars
	                .stream()
	                .parallel()
	                .map((Car car) -> car.getModel())
	                .reduce(
	                        0,
	                        (result, model) -> ++result,
	                        (finalResult, intermediateResult) -> finalResult + intermediateResult
	                );

	        System.out.println("Number of Car Models: " + carModelNum);
	    }

}
