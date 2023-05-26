import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String inputFilePath1 = "input1.txt";
        String inputFilePath2 = "input2.txt";
        String mergedFilePath = "merged.txt";
        String commonFilePath = "common.txt";

        try {
            Set<Integer> numbers1 = readNumbersFromFile(inputFilePath1);
            Set<Integer> numbers2 = readNumbersFromFile(inputFilePath2);

            Set<Integer> commonNumbers = findCommonNumbers(numbers1, numbers2);

            mergeNumbersToFile(numbers1, numbers2, mergedFilePath);
            writeNumbersToFile(commonNumbers, commonFilePath);

            System.out.println("Merging and finding common numbers completed.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + e.getMessage());
        }
    }

    private static Set<Integer> readNumbersFromFile(String filePath) throws IOException, NumberFormatException {
        Set<Integer> numbers = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        }
        return numbers;
    }

    private static Set<Integer> findCommonNumbers(Set<Integer> numbers1, Set<Integer> numbers2) {
        Set<Integer> commonNumbers = new HashSet<>();
        for (int number : numbers1) {
            if (numbers2.contains(number)) {
                commonNumbers.add(number);
            }
        }
        return commonNumbers;
    }

    private static void mergeNumbersToFile(Set<Integer> numbers1, Set<Integer> numbers2, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writeNumbers(writer, numbers1);
            writeNumbers(writer, numbers2);
        }
    }

    private static void writeNumbersToFile(Set<Integer> numbers, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writeNumbers(writer, numbers);
        }
    }

    private static void writeNumbers(BufferedWriter writer, Set<Integer> numbers) throws IOException {
        for (int number : numbers) {
            writer.write(Integer.toString(number));
            writer.newLine();
        }
    }
}
