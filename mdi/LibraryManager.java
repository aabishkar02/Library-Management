package mdi;

import library.Library;
import library.Patron;
import library.Publication;
import library.Video;
import library.InvalidRuntimeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * The `LibraryManager` class provides a command-line interface to manage a library.
 * It allows users to list publications, add publications (videos and books), check out publications,
 * check in publications, list patrons, add patrons, save library data, and open library data.
 */
public class LibraryManager {

    private Library myLibrary;
    private Scanner scanner;

    /**
     * Constructs a `LibraryManager` object with the given library and initializes a scanner for user input.
     *
     * @param library The library to be managed.
     */
    public LibraryManager(Library library) {
        this.myLibrary = library;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a list of publications in the library.
     */
    public void listPublications() {
        System.out.println("\n" + myLibrary);
    }

    /**
     * Adds a new publication (book) to the library based on user input.
     */
    public void addPublication() {
        System.out.println("\nEnter the Title: ");
        String title = scanner.nextLine();
        System.out.println("Enter name of the author: ");
        String authorName = scanner.nextLine();
        System.out.println("Enter the copyright Year: ");
        int copyrightYear = scanner.nextInt();
        scanner.nextLine();

        myLibrary.addPublication(new Publication(title, authorName, copyrightYear));
    }

    /**
     * Adds a new video publication to the library based on user input.
     */
    public void addVideo() {
        System.out.println("\nEnter the Title: ");
        String title = scanner.nextLine();
        System.out.println("Enter name of the author: ");
        String authorName = scanner.nextLine();
        System.out.println("Enter the copyright Year: ");
        int copyrightYear = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Runtime in minutes: ");
        int runtime = scanner.nextInt();
        scanner.nextLine();

        try {
            myLibrary.addPublication(new Video(title, authorName, copyrightYear, runtime));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        }
    }

    /**
     * Allows a patron to check out a publication.
     */
    public void checkOutPublication() {
        listPublications();
        System.out.println("\nWhich publication do you want to check out: ");
        int publicationIndex = scanner.nextInt();
        scanner.nextLine();
        listPatron();
        System.out.println("\nWho are you: ");
        int patronIndex = scanner.nextInt();
        scanner.nextLine();
        try {
            myLibrary.checkOut(publicationIndex, patronIndex);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Index Error: Invalid publication or patron index.");
        }
    }

    /**
     * Allows a patron to check in a publication.
     */
    public void checkInPublication() {
        listPublications();
        System.out.println("\nWhich publication do you want to check in: ");
        int publicationIndex = scanner.nextInt();
        scanner.nextLine();
        try {
            myLibrary.checkIn(publicationIndex);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Index Error: Invalid publication or patron index.");
        }
    }

    /**
     * Displays a list of patrons in the library.
     */
    public void listPatron() {
        System.out.println("\n" + myLibrary.patronMenu());
    }

    /**
     * Adds a new patron to the library based on user input.
     */
    public void addPatron() {
        System.out.println("\nEnter the name: ");
        String name = scanner.nextLine();
        System.out.println("\nEnter the email: ");
        String email = scanner.nextLine();
        myLibrary.addPatron(new Patron(name, email));
    }

    /**
     * Opens a library from a file specified by the user.
     */
    public void openLibrary() {
        System.out.println("Enter Filename: ");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            myLibrary = new Library(br);
        } catch (IOException e) {
            System.err.println("Error Printed");
        }
    }

    /**
     * Saves the library data to a file specified by the user.
     */
    public void saveLibrary() {
        System.out.println("Enter Filename: ");
        String fileName = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            myLibrary.save(bw);
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }

    /**
     * The main method to run the Library Manager application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String args[]) {
        Library myLibrary = new Library("UTA Library");
        LibraryManager myLibManager = new LibraryManager(myLibrary);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n:::::::: MAIN MENU ::::::::\n\n" + ">> Publication\n" + "1) List\n" + "2) Add (Video)\n"
                    + "3) Add (Book)\n" + "4) Check Out\n" + "5) Check In\n\n" + ">> Patrons\n" + "6) List\n" + "7) Add\n\n" + "8) Save\n" + "9) Open\n"
                    + "10) Load\n" + "0) Exit\n");

            System.out.println("Choose an option: ");
            int selection = myLibManager.scanner.nextInt();
            myLibManager.scanner.nextLine();

            switch (selection) {

                case 1:
                    myLibManager.listPublications();
                    break;
                case 2:
                    myLibManager.addVideo();
                    break;
                case 3:
                    myLibManager.addPublication();
                    break;
                case 4:
                    myLibManager.checkOutPublication();
                    break;
                case 5:
                    myLibManager.checkInPublication();
                    break;
                case 6:
                    myLibManager.listPatron();
                    break;
                case 7:
                    myLibManager.addPatron();
                    break;
                case 8:
                    myLibManager.saveLibrary();
                    break;
                case 9:
                    myLibManager.openLibrary();
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

