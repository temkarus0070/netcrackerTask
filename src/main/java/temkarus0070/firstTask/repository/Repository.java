package temkarus0070.firstTask.repository;
public interface Repository<T,ID> {
    public T get(ID id);
    public void add(T ... entity);
    public void remove(ID id);



}
