package com.milos.neo4j.converter;

import java.util.Set;

import org.springframework.beans.BeanUtils;

public abstract class AbstractConverter<T, V> {
	public void copyFromEntityToData(T entity, V data) {
		BeanUtils.copyProperties(entity, data);
	}

	public void copyFromEntityToData(T entity, V data, String... ignoreProperties) {
		BeanUtils.copyProperties(entity, data, ignoreProperties);
	}

	public void copyFromDataToEntity(V data, T entity) {
		BeanUtils.copyProperties(data, entity);
	}

	public void copyFromDataToEntity(V data, T entity, String... ignoreProperties) {
		BeanUtils.copyProperties(data, entity, ignoreProperties);
	}


	@SuppressWarnings("unchecked")
	public void copyFromEntitySetToDataSet(Set<T> entities, Set<V> datas, V v) {
		if (!entities.isEmpty()) {
			for (T t : entities) {
				try {
					v = (V) v.getClass().newInstance();
					copyFromEntityToData(t, v);
					datas.add(v);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	@SuppressWarnings("unchecked")
	public void copyFromDataSetToEntitySet(Set<V> datas, Set<T> entities, T t) {
		if (!datas.isEmpty()) {
			for (@SuppressWarnings("unused") V v : datas) {
				try {
					t = (T) t.getClass().newInstance();
					copyFromDataSetToEntitySet(datas, entities, t);
					entities.add(t);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}
