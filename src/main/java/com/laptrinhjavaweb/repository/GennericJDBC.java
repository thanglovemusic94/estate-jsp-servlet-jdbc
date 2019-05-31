package com.laptrinhjavaweb.repository;

import java.util.List;

public interface GennericJDBC<T> {

	List<T> query(String sql, Object... parameters);

	void update(String sql, Object... parameters);

	Long insert(String sql, Object... parameters);

}
