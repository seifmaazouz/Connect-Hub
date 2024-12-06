package connecthub.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;

public class JSONParser {

    private final ObjectMapper objectMapper;

    public JSONParser() {
        this.objectMapper = new ObjectMapper(); 
    }

    /**
     * Reads a JSON file and converts it into a Java object.
     *
     * @param filePath      Path to the JSON file.
     * @param typeReference TypeReference for the target Java object type.
     * @param <T>           The type of the Java object to parse into.
     * @return The parsed Java object.
     * @throws IOException If reading or parsing fails.
     */
    public <T> T readJSON(String filePath, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(new File(filePath), typeReference);
    }

    /**
     * Writes a Java object into a JSON file.
     *
     * @param filePath Path to the output JSON file.
     * @param data     The Java object to write.
     * @throws IOException If writing fails.
     */
    public void writeJSON(String filePath, Object data) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }

    /**
     * Updates a JSON file by overwriting it with modified data.
     *
     * @param filePath Path to the JSON file.
     * @param updater  Functional interface to modify the data before saving.
     * @param <T>      The type of the Java object to update.
     * @throws IOException If reading, updating, or writing fails.
     */
    public <T> void updateJSON(String filePath, TypeReference<T> typeReference, Updater<T> updater) throws IOException {
        // Read existing data
        T data = readJSON(filePath, typeReference);

        // Update data using the updater function
        T updatedData = updater.update(data);

        // Write the updated data back to the file
        writeJSON(filePath, updatedData);
    }

    /*
     * The Updater<T> functional interface is designed to provide flexibility in how you update data within a JSON file.
     * It acts as a mechanism to apply custom modifications to the data during the update process. By accepting a lambda
     * or method reference, it allows various types of operations to be performed without altering the underlying codebase
     * each time you need a different update behavior.
     *
     * This is especially useful in scenarios where you might:
     *
     * - **Update the number of likes** on a post (increment or decrement the likes count).
     * - **Delete a post** based on specific criteria (e.g., contentId, authorId).
     * - **Create or add a new post** to the existing list of posts in the JSON database.
     * - **Modify post content** (e.g., change text, add tags).
     * - **Update complex structures** like comments, images, or timestamps.
     *
     * By using this interface, you separate the logic of how data is updated from the JSON parsing and file handling,
     * keeping the codebase clean, modular, and easier to maintain. You can pass different behaviors without touching
     * the core logic of the update process itself. This promotes reusability and extensibility in your application by applying
     * the SRP.
     */

    @FunctionalInterface
    public interface Updater<T> {
        T update(T data);
    }
}
