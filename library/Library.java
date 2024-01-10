package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The `Library` class represents a library that holds publications and patrons.
 */
public class Library {

    /**
     * The name of the library.
     */
    String name;

    /**
     * A list of publications in the library.
     */
    ArrayList<Publication> publications;

    /**
     * A list of patrons in the library.
     */
    ArrayList<Patron> patrons;

    /**
     * Constructs a new library with the given name.
     *
     * @param name The name of the library.
     */
    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    /**
     * Constructs a library by reading data from a BufferedReader.
     *
     * @param br The BufferedReader from which to read library data.
     * @throws IOException If an I/O error occurs.
     */
    public Library(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.publications = new ArrayList<>();
        this.patrons = new ArrayList<>();
        int numPublications = Integer.parseInt(br.readLine());
        for (int i = 0; i < numPublications; i++) {
            String type = br.readLine();
            if (type.equals("video")) {
                publications.add(new Video(br));
            } else if (type.equals("publication")) {
                publications.add(new Publication(br));
            }
        }
        int numPatrons = Integer.parseInt(br.readLine());
        for (int i = 0; i < numPatrons; i++) {
            String type = br.readLine();
            patrons.add(new Patron(br));
        }
    }

    /**
     * Saves the library data to a BufferedWriter.
     *
     * @param bw The BufferedWriter to which to save library data.
     * @throws IOException If an I/O error occurs.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(Integer.toString(publications.size()) + '\n');
        for (Publication publication : publications) {
            if (publication instanceof Video) {
                bw.write("video\n");
            } else if (publication instanceof Publication) {
                bw.write("publication\n");
            }
            publication.save(bw);
        }
        bw.write(Integer.toString(patrons.size()) + '\n');
        for (Patron patron : patrons) {
            bw.write("Patron\n");
            patron.save(bw);
        }
    }

    /**
     * Adds a publication to the library.
     *
     * @param publication The publication to add.
     */
    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    /**
     * Adds a patron to the library.
     *
     * @param patron The patron to add.
     */
    public void addPatron(Patron patron) {
        this.patrons.add(patron);
    }

    /**
     * Generates a menu of patrons in the library.
     *
     * @return A string containing the patron menu.
     */
    public String patronMenu() {
        StringBuilder patronInfo = new StringBuilder();
        patronInfo.append(">>>  ").append("Patron").append("  <<<").append("\n\n");
        int num = 0;
        for (Patron patron : patrons) {
            patronInfo.append(num).append(". ").append(patron).append("\n");
            num++;
        }
        return patronInfo.toString();
    }

    /**
     * Checks out a publication to a patron.
     *
     * @param publicationIndex The index of the publication to check out.
     * @param patronIndex      The index of the patron checking out the publication.
     * @throws IndexOutOfBoundsException If the publication index is invalid.
     */
    public void checkOut(int publicationIndex, int patronIndex) {
        if (publicationIndex <= publications.size() && publicationIndex >= 0) {
            Publication publication = publications.get(publicationIndex);
            Patron patron = patrons.get(patronIndex);
            publication.checkOut(patron);
        } else {
            throw new IndexOutOfBoundsException("Invalid Publication");
        }
    }

    /**
     * Checks in a publication.
     *
     * @param publicationIndex The index of the publication to check in.
     * @throws IndexOutOfBoundsException If the publication index is invalid.
     */
    public void checkIn(int publicationIndex) {
        if (publicationIndex <= publications.size() && publicationIndex >= 0) {
            Publication publication = publications.get(publicationIndex);
            publication.checkIn();
        } else {
            throw new IndexOutOfBoundsException("Invalid Publication");
        }
    }

    /**
     * Returns a string representation of the library.
     *
     * @return A string containing information about the library and its publications.
     */
    @Override
    public String toString() {
        StringBuilder libraryInfo = new StringBuilder();
        libraryInfo.append("-->>  ").append(name).append("  <<--").append("\n\n");
        int num = 0;
        for (Publication publication : publications) {
            libraryInfo.append(num).append(". ").append(publication).append("\n");
            num++;
        }
        return libraryInfo.toString();
    }
}

