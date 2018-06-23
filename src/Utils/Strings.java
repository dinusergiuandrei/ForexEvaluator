package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Strings {
    public static String getStringFromBufferedReader(BufferedReader reader) throws IOException {
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        return result.toString();
    }

    public static String getStringFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (sc.hasNextLine()) {
            stringBuilder.append(sc.nextLine());
        }
        return stringBuilder.toString();
    }
}
