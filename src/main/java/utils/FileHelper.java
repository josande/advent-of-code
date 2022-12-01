package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public List<String> readFile(String fileName)  {
        List<String> out = new ArrayList<>();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (in == null) {
                throw new RuntimeException("NO FILE FOUND TO READ AT: "+fileName);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                out.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("FILE READ FAILED");
        }
        return out;
    }

    public List<Integer> readFileAsIntegers(String fileName) {
        List<Integer> ints=new ArrayList<>();
        for (String s : readFile(fileName)) {
            ints.add(Integer.parseInt(s));
        }
        return ints;
    }
}
