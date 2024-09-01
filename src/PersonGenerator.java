import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String[]> persons = new ArrayList<>();

        SafeInput.prettyHeader("Enter person(s) data below. ");

        while (true) {
            // Gather person data
            String id = getValidId(scanner);
            String firstName = getValidName(scanner, "Enter person's first name ");
            String lastName = getValidName(scanner, "Enter person's last name ");
            String title = getValidTitle(scanner);
            int yearOfBirth = SafeInput.getInt(scanner, "Enter person's year of birth ");

            // Add the person to the list
            persons.add(new String[]{id, firstName, lastName, title, String.valueOf(yearOfBirth)});

            // Check if the user wants to continue
            boolean moreData = SafeInput.getYNConfirm(scanner, "Do you want to enter another person ");
            if (!moreData) {
                break;
            }
        }

        // Save the data to a file
        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save data ");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] person : persons) {
                writer.write(String.join(",", person));
                writer.newLine();
            }
            System.out.println("Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }

        scanner.close();
    }

    // Method to ensure that the ID is a valid number
    private static String getValidId(Scanner scanner) {
        String id;
        while (true) {
            id = SafeInput.getNonZeroLenString(scanner, "Enter person's ID ");
            if (isValidId(id)) {
                return id;
            } else {
                System.out.println("Invalid ID entered. Please enter a numeric ID, no letters or characters, etc. ");
            }
        }
    }

    // Validate the ID
    private static boolean isValidId(String id) {
        return id.matches("\\d+");
    }

    // Method to ensure name is string
    private static String getValidName(Scanner scanner, String prompt) {
        String name;
        while (true) {
            name = SafeInput.getNonZeroLenString(scanner, prompt);
            if (isValidName(name)) {
                return name;
            } else {
                System.out.println("Invalid input. Please enter a valid name, no digits or characters, etc. ");
            }
        }
    }

    // Validate that the name is a valid string
    private static boolean isValidName(String name) {
        return !name.trim().isEmpty() && name.matches("[a-zA-Z ]+");
    }

    // Method to ensure that the title is a valid string
    private static String getValidTitle(Scanner scanner) {
        String title;
        while (true) {
            title = SafeInput.getNonZeroLenString(scanner, "Enter person's title (e.g., Mr., Mrs., Ms., Dr.) ");
            if (isValidTitle(title)) {
                return title;
            } else {
                System.out.println("Invalid title entered. Please enter a valid title, no digits or characters, etc. ");
            }
        }
    }

    // Validate that the title is a valid string
    private static boolean isValidTitle(String title) {
        return !title.trim().isEmpty() && title.matches("[a-zA-Z .,]+");
    }
}
