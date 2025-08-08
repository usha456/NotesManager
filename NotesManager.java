import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. View Notes");
            System.out.println("2. Write New Note");
            System.out.println("3. Append to Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewNotes();
                    break;
                case 2:
                    writeNewNote(scanner);
                    break;
                case 3:
                    appendToNote(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Notes Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }

    private static void viewNotes() {
        try (FileReader fr = new FileReader(FILE_NAME)) {
            System.out.println("\n--- Your Notes ---");
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println("\n------------------");
        } catch (IOException e) {
            System.out.println("No existing notes found.");
        }
    }

    private static void writeNewNote(Scanner scanner) {
        System.out.println("\nEnter your note (end with a single line 'EOF'):");
        StringBuilder note = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("EOF")) {
            note.append(line).append("\n");
        }

        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write(note.toString());
            System.out.println("Note written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing the note: " + e.getMessage());
        }
    }

    private static void appendToNote(Scanner scanner) {
        System.out.println("\nEnter text to append (end with a single line 'EOF'):");
        StringBuilder note = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("EOF")) {
            note.append(line).append("\n");
        }

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(note.toString());
            System.out.println("Note appended successfully.");
        } catch (IOException e) {
            System.out.println("Error appending the note: " + e.getMessage());
        }
    }
}
