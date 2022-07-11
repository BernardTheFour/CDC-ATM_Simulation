package app.interfaces;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    Optional<T> getById(String id);

    Optional<List<T>> getAllById(String id);

    void add(T data);

    void edit(T data);

    Optional<List<T>> getAll();
}
