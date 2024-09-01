import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class ProductReader {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Product Data File");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            displayFileContents(file);
        } else {
            System.out.println("No file selected.");
        }
    }

    private static void displayFileContents(File file) {
        int idWidth = 10;
        int nameWidth = 20;
        int descriptionWidth = 30;
        int costWidth = 10;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            System.out.printf("%-" + idWidth + "s %-"+ nameWidth + "s %-"+ descriptionWidth + "s %-"+ costWidth + "s%n", "ID", "Name", "Description", "Cost");
            System.out.println("=".repeat(idWidth + nameWidth + descriptionWidth + costWidth + 8)); // +8 for spaces between columns

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    String costString = parts[3].trim();
                    double cost = Double.parseDouble(costString);

                    System.out.printf("%-" + idWidth + "s %-"+ nameWidth + "s %-"+ descriptionWidth + "s %10.2f%n",
                            id, name, description, cost);
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
