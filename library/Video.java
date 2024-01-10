package library;

import java.time.Duration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * The `Video` class represents a video publication in a library.
 * It extends the `Publication` class and includes information about the video's runtime.
 */
public class Video extends Publication {

    private Duration runtime;

    /**
     * Constructs a new `Video` object with the specified title, author, copyright year, and runtime in minutes.
     *
     * @param title          The title of the video.
     * @param author         The author or creator of the video.
     * @param copyright      The year when the video was copyrighted.
     * @param runtimeMinutes The duration of the video in minutes.
     * @throws InvalidRuntimeException If the provided runtime is not a positive value.
     */
    public Video(String title, String author, int copyright, int runtimeMinutes) {
        super(title, author, copyright);

        if (runtimeMinutes <= 0) {
            throw new InvalidRuntimeException(title, runtimeMinutes);
        }

        this.runtime = Duration.ofMinutes(runtimeMinutes);
    }

    /**
     * Constructs a `Video` object from a BufferedReader.
     *
     * @param br The BufferedReader containing video information.
     * @throws IOException             If there is an I/O error while reading.
     * @throws InvalidRuntimeException If the runtime is zero, indicating an error.
     */
    public Video(BufferedReader br) throws IOException {
        super(br);

        String runTimeStr = br.readLine();

        if (runTimeStr != null) {
            this.runtime = Duration.parse(runTimeStr);
        } else {
            this.runtime = Duration.ZERO;
        }

        if (this.runtime.equals(Duration.ZERO)) {
            throw new InvalidRuntimeException("Runtime Error");
        }
    }

    /**
     * Saves the `Video` object's information to a BufferedWriter.
     *
     * @param bw The BufferedWriter to which the video information will be saved.
     * @throws IOException If there is an I/O error while writing.
     */
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(runtime.toString() + '\n');
    }

    /**
     * Converts the `Video` object to a string representation.
     *
     * @return A string containing the title and runtime of the video.
     */
    @Override
    public String toString() {
        long minutes = runtime.toMinutes();
        StringBuilder builder = toStringBuilder("Video", minutes + " minutes");
        return builder.toString();
    }
}

