package edu.umb.cs681.hw17;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) throws Exception {

        var path = Paths.get("src/edu/umb/cs681/hw17/data/pvi_data.csv");

        // To Find  neighboring counties
        var neighbouringCounties = Files.readAllLines(path)
                .stream()
                .parallel()
                .filter(line -> line.contains("Massachusetts, Barnstable")
                        || line.contains("Massachusetts, New Hampshire")
                        || line.contains("Massachusetts, Essex")
                        || line.contains("Massachusetts, Suffolk")
                        || line.contains("Massachusetts, Franklin")
                        || line.contains("Massachusetts, Bristol")
                        || line.contains("Rhode Island, Berkshire"))
                .map(line -> line.split(","))
                .collect(Collectors.toList());

        var avPvi = neighbouringCounties.stream()
                .parallel()
                .map(line -> Double.parseDouble(line[0].replace("\"", "")))
                .reduce(
                        new double[2],
                        (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult)[1];


        System.out.print("Average PVI of  neighbouring counties and  counties is: ");
        System.out.println(avPvi);

        var avInfectionRate = neighbouringCounties.stream()
                .parallel()
                .map(line -> Double.parseDouble(line[7].replace("\"", "")))
                .reduce(
                        new double[2],
                        (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult)[1];
        System.out.print("Average infection rate of  neighbouring counties and  counties is: ");
        System.out.println(avInfectionRate);

        var avVaxRate = neighbouringCounties.stream()
                .parallel()
                .map(line -> Double.parseDouble(line[11].replace("\"", "")))
                .reduce(
                        new double[2],
                        (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult)[1];
        System.out.print("Average vaccination rate of  neighbouring counties and  counties is: ");
        System.out.println(avVaxRate);

        // Calculate Standard Deviation of Massachusetts counties
        // Finding Massachusetts counties
        var massCounties = Files.readAllLines(path)
                .stream()
                .parallel()
                .filter(line -> line.contains("Massachusetts"))
                .map(line -> line.split(","))
                .collect(Collectors.toList());

        var meanPVI = massCounties.stream()
                .parallel()
                .map(line -> Double.parseDouble(line[0].replace("\"", "")))
                .reduce(
                        new double[2],

                        (result, pvi) -> {
                            var av = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, av};
                        },
                        (finalResult, intermediateResult) -> finalResult)[1];

        var massSD = 0.0d;
        for (String[] county : massCounties) {
            var a = Double.parseDouble(county[0].replace("\"", ""));
            massSD += Math.pow(a - meanPVI, 2);
        }
        massSD = Math.sqrt(massSD / massCounties.size());
        System.out.println("Mean of Massachusetts counties: " + meanPVI);
        System.out.print("Standard Deviation of Massachusetts counties: ");
        System.out.println(massSD);

    }

}
