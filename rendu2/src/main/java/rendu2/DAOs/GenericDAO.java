package rendu2.DAOs;

import java.util.List;

 interface GenericDAO<T> {
    public void add(T entity);
    public T get(int id);
    public List<T> getAll();
    public void update(T entity);
    public void delete(int id);
}
