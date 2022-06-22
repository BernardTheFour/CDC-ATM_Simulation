package csv_access;

import java.util.List;
import java.util.Optional;

public interface IFileManipulation<T> {
    Optional<T> getById(String id);
    void add(T data);
    void edit(T data);
    void save();
    Optional<List<T>> getAll();
}
