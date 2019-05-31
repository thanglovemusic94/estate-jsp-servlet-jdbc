package com.laptrinhjavaweb.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.anotation.Column;
import com.laptrinhjavaweb.anotation.Entity;

public class ResultSetMapper<T> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> mapRow(ResultSet rs, Class zClass) {
		List<T> result = new ArrayList<>();
		try {
			if (zClass.isAnnotationPresent(Entity.class)) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				Field[] fields = zClass.getDeclaredFields();
				while (rs.next()) {
					T object = (T) zClass.newInstance();
					// get giá trị của 1 row trong resultSet và set vào trong Entity
					for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnName = resultSetMetaData.getColumnName(i + 1);
						Object columnValue = rs.getObject(i + 1);
						convertResultSetToEntity(fields, columnName, columnValue, object);
						// parent class
						Class parentClass = zClass.getSuperclass();
						while (parentClass != null) {
							Field[] fieldsParents = parentClass.getDeclaredFields();
							convertResultSetToEntity(fieldsParents, columnName, columnValue, object);
							parentClass = parentClass.getSuperclass();
						}
					}
					result.add(object);
				}
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return result;
	}

	private void convertResultSetToEntity(Field[] fields, String columnName, Object columnValue, T object)
			throws IllegalAccessException, InvocationTargetException {
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if (columnName.equals(column.name()) && columnName != null) {
					BeanUtils.setProperty(object, field.getName(), columnValue);
					break;
				}
			}
		}
	}

}
