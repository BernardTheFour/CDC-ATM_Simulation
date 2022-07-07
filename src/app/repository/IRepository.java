package app.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface IRepository<T> {
    Optional<T> getById(String id);
    Optional<List<T>> getAllById(String id);
    void add(T data);
    Stream<T> edit(T data);
    void save(Stream<T> stream);
    Optional<List<T>> getAll();
}
