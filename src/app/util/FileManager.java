package app.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.pattern.SingletonPath;
import app.pattern.SingletonUtils;

public class FileManager {

    private Path path;

    public FileManager(File file) {
        path = Paths.get(file.getAbsolutePath());
    }

    public Optional<String> getById(String id) {
        try (Stream<String> stream = Files.lines(path)) {
            return stream.skip(1)
                    .filter(Objects::nonNull)
                    .filter(i -> i.split(SingletonUtils.getCSVColumnDelimiter())[0].equals(id))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return Optional.empty();
    }

    public Stream<String> getAll() throws IOException {
        return Files.lines(path).skip(1).filter(Objects::nonNull);
    }

    public Optional<List<String>> getAllByid(String id) {
        try (Stream<String> stream = Files.lines(path)) {
            return Optional.of(stream.skip(1)
                    .filter(Objects::nonNull)
                    .filter(i -> i.split(SingletonUtils.getCSVColumnDelimiter())[0].equals(id))
                    .collect(Collectors.toList()));
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

    public void add(File file, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write(data);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
