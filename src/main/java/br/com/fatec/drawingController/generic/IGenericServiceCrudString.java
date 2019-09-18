package br.com.fatec.drawingController.generic;

import java.util.List;
import java.util.Optional;

public interface IGenericServiceCrudString<T, S> {
    public boolean save(T entity);

    public T saveEnt(T entity);

    public T remove(T entity);

    public boolean update(T entity, T entityUpdate);

    public Optional<T> findById(S id);

    public List<T> findAll();
};