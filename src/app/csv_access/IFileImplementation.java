package app.csv_access;

import java.util.List;
import java.util.Optional;

public interface IFileImplementation<T> {
    Optional<T> getById(String id);
    List<T> getAllById(String id);
    void add(T data);
    void edit(T data);
    void save();
    Optional<List<T>> getAll();
}
