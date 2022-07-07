package app.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.pattern.SingletonUtils;

public class FileManager {

    private Path path;

    public FileManager(File file) {
        path = file.toPath();
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

    public Stream<String> getAllById(String id) throws IOException {
        return Files.lines(path).skip(1)
                .filter(Objects::nonNull)
                .filter(i -> i.split(SingletonUtils.getCSVColumnDelimiter())[0].equals(id));
    }

    public void edit(String data) {
        try (Stream<String> stream = Files.lines(path)) {
            Optional<String> title = stream.findFirst();

            String rewrite = title.get() + data;

            Files.writeString(path, rewrite);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void add(String data) {
        try {
            Files.writeString(path,
                    data,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
