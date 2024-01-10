package library;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * The `Publication` class represents a publication in a library, such as a book or video.
 * It includes information about the title, author, copyright year, loan status, and due date.
 */
public class Publication {

    private String title;
    private String author;
    private int copyright;
    private Patron loanedTo;
    private LocalDate dueDate;

    /**
     * Constructs a new `Publication` object with the given title, author, and copyright year.
     *
     * @param title     The title of the publication.
     * @param author    The author or creator of the publication.
     * @param copyright The year when the publication was copyrighted.
     * @throws IllegalArgumentException If the provided copyright year is outside the valid range.
     */
    public Publication(String title, String author, int copyright) {
        if (copyright < 1900 || copyright > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Invalid Copyright Year");
        }
        this.title = title;
        this.author = author;
        this.copyright = copyright;
    }

    /**
     * Constructs a `Publication` object from a BufferedReader.
     *
     * @param br The BufferedReader containing publication information.
     * @throws IOException If there is an I/O error while reading.
     */
    public Publication(BufferedReader br) throws IOException {
        this.title = br.readLine();
        this.author = br.readLine();
        this.copyright = Integer.parseInt(br.readLine());

        String checkInOut = br.readLine();

        if (checkInOut.equals("checked in")) {
            this.loanedTo = null;
            this.dueDate = null;
        } else {
            this.loanedTo = new Patron(br);
            String dueDateStr = br.readLine();
            this.dueDate = LocalDate.parse(dueDateStr);
        }
    }

    /**
     * Saves the publication's information to a BufferedWriter.
     *
     * @param bw The BufferedWriter to which the publication information will be saved.
     * @throws IOException If there is an I/O error while writing.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(title + '\n');
        bw.write(author + '\n');
        bw.write(Integer.toString(copyright) + '\n');

        if (loanedTo == null) {
            bw.write("checked in\n");
        } else {
            bw.write("checked out\n");
            loanedTo.save(bw);
            bw.write(dueDate.toString() + '\n');
        }

    }

    /**
     * Checks out the publication to a patron, setting the due date to 14 days from the current date.
     *
     * @param patron The patron who is checking out the publication.
     */
    public void checkOut(Patron patron) {
        this.loanedTo = patron;
        this.dueDate = LocalDate.now().plusDays(14);
    }

    /**
     * Checks in the publication, marking it as available in the library.
     */
    public void checkIn() {
        this.loanedTo = null;
    }

    /**
     * Generates a StringBuilder with common publication details.
     *
     * @param type The type of publication (e.g., "Book" or "Video").
     * @param add  Additional information specific to the publication type.
     * @return A StringBuilder with formatted publication details.
     */
    protected StringBuilder toStringBuilder(String type, String add) {
        StringBuilder builder = new StringBuilder();
        builder.append(type).append(": ").append(title).append("\n Author: ").append(author).append("\n Copyright Year: ").append(copyright);

        if (add != null) {
            builder.append("\n ").append("RunTime Minutes").append(": ").append(add);
        }

        if (loanedTo != null) {
            builder.append("\n   >>> loaned to ").append(loanedTo).append(" until ").append(dueDate);
        }

        builder.append("\n\n");

        return builder;
    }

    /**
     * Converts the `Publication` object to a string representation.
     *
     * @return A string containing the publication's title, author, and loan status.
     */
    @Override
    public String toString() {
        return toStringBuilder("Book", null).toString();
    }
}

