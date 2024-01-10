 package library;

/**
 * The InvalidRuntimeException is a custom exception thrown when an invalid runtime value is encountered.
 */
 

 
public class InvalidRuntimeException extends ArithmeticException {

    /**
     * Constructs a new InvalidRuntimeException with the specified error message.
     *
     * @param message The error message.
     */
    public InvalidRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidRuntimeException with the title of the video and invalid runtime value.
     *
     * @param title   The title of the video.
     * @param runtime The invalid runtime value.
     */
    public InvalidRuntimeException(String title, int runtime) {
        super("Invalid runtime for video '" + title + "': " + runtime);
    }
}

