package millne.felix.trains.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NetworkFileReader {
    public String getNetworkStringFromFile(String fileName) {
        String input;
        System.out.println("Reading input file " + fileName);
        StringBuilder inputStringBuilder = new StringBuilder();
        try {
            Files.lines(Paths.get(fileName), StandardCharsets.UTF_8).forEach(inputStringBuilder::append);
        } catch (IOException e) {
            System.out.println("Unable to read file " + fileName + ". Error: " + e.getMessage());
            System.exit(1);
        }

        input = inputStringBuilder.toString();
        return input;
    }
}