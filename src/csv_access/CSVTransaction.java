package csv_access;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import domains.Transactions;
import domains.Transactions.Type;
import pattern.SingletonUtils;

public class CSVTransaction implements IFileManipulation<Transactions> {

    private DataAccess dataAccess;

    public CSVTransaction(File file) {
        dataAccess = new DataAccess(file);
    }

    @Override
    public Optional<Transactions> getById(String id) {
        Optional<String> result = dataAccess.getById(id);

        if (result.isEmpty()) {
            System.out.printf("failed: account %s not found%n", id);
            return Optional.empty();
        }

        List<String> data = List.of(result.get().split(SingletonUtils.getCSVColumnDelimiter()));

        return Optional.of(read(data));
    }

    @Override
    public void edit(Transactions data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void save() {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<List<Transactions>> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(Transactions data) {
        // TODO Auto-generated method stub

    }

    private Transactions read(List<String> data) {
        Transactions transaction = new Transactions(
                data.get(0),
                Type.valueOf(data.get(1)),
                data.get(2),
                Integer.valueOf(data.get(3)),
                LocalDateTime.parse(data.get(4)));

        return transaction;
    }
}
