package temkarus0070.firstTask.repository;

import java.util.Optional;

public interface Repository<T,ID> {
    public Optional<T> get(ID id);
    public void add(T ... entity);
    public void remove(ID id);



}
