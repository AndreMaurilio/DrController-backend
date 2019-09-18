package br.com.fatec.drawingController.generic;

import java.util.List;
import java.util.Optional;

public interface IGenericServiceCrud<T, L> {
	public boolean save(T entity);

	public T saveEnt(T entity);

	public T remove(T entity);

	public boolean update(T entity, T entityUpdate);

	public Optional<T> findById(L id);

	public List<T> findAll();
};