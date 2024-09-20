package org.lab1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOperations {

    public static void main(String[] args) {
        System.out.println("\n-----------------------------------------");
        System.out.println("\nTASK");
        System.out.println("\n-----------------------------------------");
        ArrayList<String> invalidInputs = new ArrayList<>();
        List<Number> numbers = createNumberList(invalidInputs);

        System.out.println("1) Our list: " + numbers);
        //System.out.println(numbers);

        if (!invalidInputs.isEmpty()) {
            System.out.println("\nInvalid inputs ignored: " + String.join(", ", invalidInputs));
        }

        printAsIntegers(numbers);

        printAsFractions(numbers);

        Map<String, List<? extends Number>> separatedLists = separateByType(numbers);

        System.out.println("\n4) Separated list of integers: "+separatedLists.get("integers"));
        System.out.println("\n5) Separated list of fractional numbers: "+separatedLists.get("fractions"));

        System.out.println("\n-----------------------------------------");
        System.out.println("\nADDITIONAL TASKS");
        System.out.println("\n-----------------------------------------");
        print_BigDecimal(numbers);
        System.out.println("\nSum: " + calculateSum(numbers));
        System.out.println("Product of the first 5 numbers: " + String.format("%.3f", calculateProduct(numbers)));
        System.out.println("Average value: " + String.format("%.3f", calculateAverage(numbers)));
        System.out.println("Largest number: " + findMax(numbers));
        System.out.println("Sum of squares: " + String.format("%.3f", sumOfSquares(numbers)));
        List<Integer> integerNumbers = getIntegerList(numbers);
        System.out.println("List of integers: " + integerNumbers);
        //System.out.println(integerNumbers);

        List<Number> multipliedList = multiplyByTwo(numbers);
        System.out.print("Multiplied by 2: ");
        System.out.println(multipliedList);

        List<Number> formulaList = applyFormula(numbers);
        System.out.print("Formula (number + 10) / 2: ");
        System.out.println(formulaList);
        System.out.println("Sum of numbers greater than 50: " + String.format("%.2f", sumGreaterThan50(numbers)));
        //ystem.out.println("Multiplied by 2: " + multiplyByTwo(numbers));
        //System.out.println("Formula (number + 10) / 2: " + applyFormula(numbers));
    }

    //public static void printNumbers(List<Number> numbers) {
    //    System.out.print("Formatted list: ");
     //   for (Number number : numbers) {
     //       if (number instanceof Integer) {
     //           System.out.print(number.intValue() + " ");
     //       } else if (number instanceof Double) {
      //          System.out.printf("%.2f ", number.doubleValue());
      //      }
      //  }
      //  System.out.println();
    //}

    public static void printAsIntegers(List<Number> numbers) {
        System.out.print("2) Numbers as integers: ");
        for (Number number : numbers) {
            System.out.print(number.intValue() + " ");
        }
        System.out.println();
    }
    public static void printAsFractions(List<Number> numbers) {
        System.out.print("3) Numbers in fraction format with 2 decimal places: ");
        for (Number number : numbers) {
            if (number instanceof Double) {
                System.out.printf("%.2f ", number.doubleValue());
            } else {
                System.out.printf("%.2f ", number.doubleValue());
            }
        }
        System.out.println();
    }
    public static Map<String, List<? extends Number>> separateByType(List<Number> numbers) {
        List<Integer> integerList = new ArrayList<>();
        List<Double> fractionalList = new ArrayList<>();

        for (Number number : numbers) {
            if (number.doubleValue() == Math.floor(number.doubleValue()) && !Double.isInfinite(number.doubleValue())) {
                // Whole numbers, store as Integer
                integerList.add(number.intValue());
            } else {
                // Fractional numbers, store as Double
                fractionalList.add(number.doubleValue());
            }
        }

        Map <String, List<? extends Number>> separatedLists = new HashMap <> ();
        separatedLists.put("integers", integerList);
        separatedLists.put("fractions", fractionalList);

        return separatedLists;
    }

    public static List<Integer> getIntegerList(List<Number> numbers) {
        List<Integer> integerNumbers = new ArrayList<>();
        for (Number number : numbers) {
            if (number instanceof Integer || number instanceof Long || number.doubleValue() == Math.floor(number.doubleValue())) {
                integerNumbers.add(number.intValue());  // Add as integer
            }
        }
        return integerNumbers;
    }
    public static double calculateSum(List<Number> numbers) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Number number : numbers) {
            sum = sum.add(BigDecimal.valueOf(number.doubleValue()));
        }
        return sum.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static void print_BigDecimal(List<Number> numbers) {
        System.out.print("Numbers in BigDecimal format: ");
        for (int i = 0; i < numbers.size(); i++) {
            BigDecimal bigDecimalNumber = BigDecimal.valueOf(numbers.get(i).doubleValue());
            System.out.print(bigDecimalNumber.toPlainString());
            if (i < numbers.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    public static double calculateProduct(List<Number> numbers) {
        double product = 1;
        for (int i = 0; i < 5 && i < numbers.size(); i++) {
            product *= numbers.get(i).doubleValue();  // Convert to double for consistent product calculation
        }
        return product;
    }

    public static double calculateAverage(List<Number> numbers) {
        double sum = calculateSum(numbers);
        return Math.round(sum / numbers.size() * 100.0) / 100.0;
    }

    public static Number findMax(List<Number> numbers) {
        double max = numbers.get(0).doubleValue();
        for (Number number : numbers) {
            if (number.doubleValue() > max) {
                max = number.doubleValue();
            }
        }
        return max;
    }

    public static double sumOfSquares(List<Number> numbers) {
        double sumOfSquares = 0;
        for (Number number : numbers) {
            sumOfSquares += Math.pow(number.doubleValue(), 2);  // Use double for square calculation
        }
        return sumOfSquares;
    }

    public static double sumGreaterThan50(List<Number> numbers) {
        double sum = 0;
        for (Number number : numbers) {
            if (number.doubleValue() > 50) {
                sum += number.doubleValue();
            }
        }
        return sum;
    }

    public static List<Number> multiplyByTwo(List<Number> numbers) {
        List<Number> multipliedNumbers = new ArrayList<>();
        for (Number number : numbers) {
            if (number instanceof Integer) {
                multipliedNumbers.add(number.intValue() * 2);  // Maintain integer type if original was integer
            } else {
                multipliedNumbers.add(number.doubleValue() * 2);
            }
        }
        return multipliedNumbers;
    }

    public static List<Number> applyFormula(List<Number> numbers) {
        List<Number> results = new ArrayList<>();
        for (Number number : numbers) {
            double result = (number.doubleValue() + 10) / 2;
            results.add(Math.round(result * 100.0) / 100.0);
        }
        return results;
    }

    public static List<Number> createNumberList(ArrayList<String> invalid) {
        List<Number> numbers = new ArrayList<>();

        addNumberToList(numbers, "10", invalid);
        addNumberToList(numbers, "20.5", invalid);
        //addNumberToList(numbers, "-37.23", invalid);
        //addNumberToList(numbers, "3000001200", invalid);
        //addNumberToList(numbers, "..", invalid);
        addNumberToList(numbers, "30", invalid);
        addNumberToList(numbers, "40.7", invalid);
        addNumberToList(numbers, "50", invalid);
        //addNumberToList(numbers, ",", invalid);
        //addNumberToList(numbers, "50000000000", invalid);
        addNumberToList(numbers, "60.3", invalid);
        addNumberToList(numbers, "70", invalid);
        addNumberToList(numbers, "80.1", invalid);
        addNumberToList(numbers, "90", invalid);
        addNumberToList(numbers, "100.9", invalid);

        return numbers;
    }
    public static void addNumberToList(List<Number> numbers, String value, ArrayList<String> invalid) {
        try {
            if (value != null) {
                if (value.matches("-?\\d+")) {
                    numbers.add(Integer.parseInt(value));
                } else {
                    numbers.add(Double.parseDouble(value));
                }
            } else {
                invalid.add("null");
            }
        } catch (NumberFormatException e) {
            invalid.add(value);
        }
    }
    //public static void addNumberToList(List<Number> numbers, String value, ArrayList<String> invalid) {
   //     try {
    //        if (value != null) {
    //            // If the value matches an integer, check if it's within the int range, otherwise use long
    //            if (value.matches("-?\\d+")) {
    //                long longValue = Long.parseLong(value);
    //                if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
    //                    numbers.add((int) longValue);  // If within int range, add as Integer
   //                 } else {
   //                     numbers.add(longValue);  // If larger, add as Long
   //                 }
   //             } else {
   //                 numbers.add(Double.parseDouble(value));  // Add as a Double if it's not an integer
   //             }
    //        } else {
    //            invalid.add("null");
    //       }
   //     } catch (NumberFormatException e) {
   //         invalid.add(value);
    //    }
    //}

}
