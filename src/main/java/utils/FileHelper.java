package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {
    public List<String> readFile(String fileName) {
        List<String> out = new ArrayList<>();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (in == null) {
                System.out.println("NO FILE FOUND TO READ");
                return null;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            for (String line = br.readLine();
                 line != null;
                 line = br.readLine()) {
                out.add(line);
            }
        } catch (IOException e) {
            System.out.println("FILE READ FAILED");
        }
        return out;
    }

    public List<Integer> readFileAsIntegers(String fileName) {
        return readFile(fileName).stream().map(Integer::valueOf).collect(Collectors.toList());
    }
}
