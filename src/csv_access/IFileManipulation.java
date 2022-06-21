package csv_access;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface IFileManipulation<T> {
    Optional<T> getById(String id);
    void edit(T data);
    void save();
    Optional<List<T>> getAll();
}
