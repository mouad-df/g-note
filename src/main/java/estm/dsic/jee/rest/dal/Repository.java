package estm.dsic.jee.rest.dal;

import java.util.List;

public interface Repository<T,I> {
    void create(T entity);
    T auth(T entity);
    List<T> getAll();
    void delete(T entity,I index);
    void update(T entity,I index);

    
} 
