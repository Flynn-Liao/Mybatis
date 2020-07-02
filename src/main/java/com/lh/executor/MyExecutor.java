package com.lh.executor;

import com.lh.model.User;
import com.lh.util.ParseXml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库增删查改模板
 *
 * @author huan
 */
public class MyExecutor implements Executor {

	/**
	 * 查询
	 *
	 * @param sql
	 * @param parameter
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> T query(String sql, Object parameter) {
		Connection conn = ParseXml.getConnection();
		ResultSet set = null;
		PreparedStatement pre = null;
		try {
			pre = conn.prepareStatement(sql);
			//设置参数
			pre.setString(1, parameter.toString());
			set = pre.executeQuery();
			User u = new User();
			//遍历结果集
			while (set.next()) {
				u.setId(set.getString(1));
				u.setUsername(set.getString(2));
				u.setPassword(set.getString(3));
			}
			return (T) u;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (set != null) {
					set.close();
				}
				if (pre != null) {
					pre.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 添加
	 *
	 * @param sql
	 * @param parameter
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> T create(String sql, Object parameter) {
		Connection conn = ParseXml.getConnection();
		PreparedStatement pre = null;
		try {
			pre = conn.prepareStatement(sql);
			//设置参数
			pre.setString(1, parameter.toString());
			pre.executeUpdate();
			User u = new User();
			return (T) u;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 修改
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 */
	@Override
	public <T> T update(String statement, Object parameter) {
		return null;
	}

	/**
	 * 删除
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 */
	@Override
	public <T> T delete(String statement, Object parameter) {
		return null;
	}
}