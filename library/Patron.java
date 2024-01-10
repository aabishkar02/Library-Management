package library;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * The `Patron` class represents a patron of a library with a name and email address.
 */
public class Patron {

    private String name;
    private String email;

    /**
     * Constructs a new `Patron` object with the given name and email address.
     *
     * @param name  The name of the patron.
     * @param email The email address of the patron.
     */
    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Constructs a `Patron` object from a BufferedReader.
     *
     * @param br The BufferedReader containing patron information.
     * @throws IOException If there is an I/O error while reading.
     */
    public Patron(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.email = br.readLine();
    }

    /**
     * Saves the patron's information to a BufferedWriter.
     *
     * @param bw The BufferedWriter to which the patron information will be saved.
     * @throws IOException If there is an I/O error while writing.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }

    /**
     * Converts the `Patron` object to a string representation.
     *
     * @return A string containing the patron's name and email address.
     */
    @Override
    public String toString() {
        String patronInfo = name + " -> " + email;
        return patronInfo;
    }
}

