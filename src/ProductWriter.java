import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ProductWriter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();

        System.out.println("Enter product information below. ");
        while (true) {
            // Get and validate product ID
            String id = SafeInput.getRegExString(scanner, "Enter Product ID: ", "\\d+");

            // Get and validate product name
            String name = getValidName(scanner);

            // Get and validate product description
            String description = getValidDescription(scanner);

            // Get and validate product cost
            double cost = SafeInput.getDouble(scanner, "Enter Product Cost: ");

            // Format and add product to the list
            String productRecord = String.format("%s, %s, %s, %.2f", id, name, description, cost);
            products.add(productRecord);

            // Check if the user wants to add another product
            boolean done = SafeInput.getYNConfirm(scanner, "Add another product? ");
            if (!done) {
                break;
            }
        }

        // Getter and validation checkpoint
        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter file name to save data: ");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }
            System.out.println("Data successfully saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static String getValidName(Scanner scanner) {
        String name;
        while (true) {
            name = SafeInput.getNonZeroLenString(scanner, "Enter Product Name: ");
            if (isValidText(name)) {
                break;
            } else {
                System.out.println("Invalid input. Product name cannot be numbers or characters, etc. ");
            }
        }
        return name;
    }

    private static String getValidDescription(Scanner scanner) {
        String description;
        while (true) {
            description = SafeInput.getNonZeroLenString(scanner, "Enter Product Description: ");
            if (isValidText(description)) {
                break;
            } else {
                System.out.println("Invalid input. Product description cannot be numbers or characters, etc. ");
            }
        }
        return description;
    }

    private static boolean isValidText(String input) {
        // Checkpoint for input
        Pattern pattern = Pattern.compile(".*\\d.*");
        Matcher matcher = pattern.matcher(input);
        return !matcher.matches();
    }
}
