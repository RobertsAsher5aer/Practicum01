import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class PersonReader {

    public static void main(String[] args) {
        // JFileChooser Instance
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person Data File");

        // Set file filter to only show text files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));

        // Show the file chooser dialog
        int userSelection = fileChooser.showOpenDialog(null);

        // Checkpoint
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Read and display whats in the file
            displayFileContents(file);
        } else {
            System.out.println("No file selected.");
        }
    }

    private static void displayFileContents(File file) {
        // Define column widths, format
        int idWidth = 10;
        int firstNameWidth = 15;
        int lastNameWidth = 15;
        int titleWidth = 10;
        int yobWidth = 5;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Print the header
            System.out.printf("%-" + idWidth + "s %-"+ firstNameWidth + "s %-"+ lastNameWidth + "s %-"+ titleWidth + "s %-"+ yobWidth + "s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
            System.out.println("=".repeat(idWidth + firstNameWidth + lastNameWidth + titleWidth + yobWidth + 4)); // +4 for the spaces between columns

            // Read and print each line from the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Ensure correct number of parts is in format
                if (parts.length == 5) {
                    // Print the formatted line
                    System.out.printf("%-" + idWidth + "s %-"+ firstNameWidth + "s %-"+ lastNameWidth + "s %-"+ titleWidth + "s %-"+ yobWidth + "s%n",
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim());
                } else {
                    System.out.println("Invalid record format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
