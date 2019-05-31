package com.laptrinhjavaweb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractJDBC<T> {

	public Connection getConnection() {

		try {
			String databaseURL = "jdbc:mysql://localhost:3306/estate-4month2019";
			String user = "root";
			String password = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(databaseURL, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @SuppressWarnings("hiding") public <T> List<T> query(String sql, RowMapper<T>
	 * rowMapper, Object... parameters) { List<T> results = new ArrayList<T>(); try
	 * (Connection conn = getConnection(); PreparedStatement statement =
	 * conn.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
	 * if (conn != null) { while (resultSet.next()) {
	 * results.add(rowMapper.mapRow(resultSet)); } return results; } } catch
	 * (SQLException e) { System.out.print(e.getMessage()); }
	 * 
	 * return null; }
	 */

	public <T> List<T> query(String sql, Class<T> zClass, Object... parameters) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			if (conn != null) {
				return resultSetMapper.mapRow(resultSet, zClass);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}

		return null;
	}

	public void update(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql);
			if (conn != null) {
				// Set parameter to statement
				for (int i = 0; i < parameters.length; i++) {
					Object parameter = parameters[i];
					int index = i + 1;
					statement.setObject(index, parameter);
				}

				statement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				// Set parameter to statement
				for (int i = 0; i < parameters.length; i++) {
					Object parameter = parameters[i];
					int index = i + 1;
					statement.setObject(index, parameter);
				}

				int rowsInserted = statement.executeUpdate();
				resultSet = statement.getGeneratedKeys();
				conn.commit();
				if (rowsInserted > 0) {
					while (resultSet.next()) {
						Long id = resultSet.getLong(1);
						return id;
					}
					System.out.println("A new Building was inserted Successfully");
				}
			}
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
