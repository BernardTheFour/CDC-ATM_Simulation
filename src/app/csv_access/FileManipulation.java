package app.csv_access;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import app.pattern.SingletonPath;
import app.pattern.SingletonUtils;

public class FileManipulation {

    private File file;

    public FileManipulation(File file) {
        this.file = file;
    }

    public Optional<String> getById(String id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                line = reader.readLine();
                String readId = line.split(SingletonUtils.getCSVColumnDelimiter())[0];

                if (readId.equals(id)) {
                    reader.close();
                    return Optional.of(line);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return Optional.empty();
    }

    public Optional<List<String>> getAll() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();

            reader.readLine();
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();
            return Optional.of(lines);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return Optional.empty();
    }

    public Optional<List<String>> getAllByid(String id) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> lines = new ArrayList<>();

            reader.readLine();
            String line = reader.readLine();

            while (line != null) {
                String readId = line.split(SingletonUtils.getCSVColumnDelimiter())[0];

                if (readId.equals(id)) {
                    lines.add(line);
                }
                line = reader.readLine();
            }

            reader.close();
            return Optional.of(lines);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        return Optional.empty();
    }

    public File save(File file, String data) {
        try {
            // create scanner to read prev file
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(SingletonUtils.getCSVRowDelimiter());

            // create temporary file
            File tempFile = new File(SingletonPath.getPath() + "\\tmp.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // copy first line of prev file to temp file
            writer.write(scanner.nextLine());

            // write data to the temp file and flush
            writer.newLine();
            writer.write(data);

            // close stream
            scanner.close();
            writer.close();

            // copy prev file path for renaming
            Path filePath = Paths.get(file.getAbsolutePath());

            // delete file
            file.delete();

            // replace file with tmp file
            Path newFilePath = Paths.get(tempFile.getAbsolutePath());
            Files.move(newFilePath,
                    filePath.resolveSibling(filePath.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);

            // return file reference
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public File add(File file, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write(data);

            writer.close();

            return file;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}
