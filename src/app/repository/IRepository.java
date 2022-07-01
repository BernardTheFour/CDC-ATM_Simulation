package app.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    Optional<T> getById(String id);
    List<T> getAllById(String id);
    void add(T data);
    void edit(T data);
    void save();
    Optional<List<T>> getAll();
}
