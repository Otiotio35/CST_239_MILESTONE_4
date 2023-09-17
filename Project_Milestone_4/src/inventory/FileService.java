package inventory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides services for reading from and writing to files. This class abstracts
 * file operations like reading entire files into strings and writing strings to
 * files.
 * 
 * @version 09/17/2023 ID: 21024608
 * @author toafik otiotio
 */
public class FileService {
	/**
	 * Initializes a new instance of the FileService class.
	 */
	public FileService() {
		// constructor body...
	}

	/**
	 * Reads the content of a file and returns it as a String.
	 *
	 * @param path The path to the file to be read.
	 * @return The content of the file as a String.
	 * @throws IOException If there's an error reading the file.
	 */

	public String readFile(String path) throws IOException {
		try (FileReader fileReader = new FileReader(path)) {
			int character;
			StringBuilder data = new StringBuilder();
			while ((character = fileReader.read()) != -1) {
				data.append((char) character);
			}
			return data.toString();
		}
	}

	/**
	 * Writes a string to a file.
	 *
	 * @param path The path to the file to be written.
	 * @param data The string data to be written to the file.
	 * @throws IOException If there's an error writing to the file.
	 */
	public void writeFile(String path, String data) throws IOException {
		try (FileWriter fileWriter = new FileWriter(path)) {
			fileWriter.write(data);
		}
	}
}
