package com.lh.executor;

/**
 * 数据库增删查改模板
 *
 * @author huan
 */
public interface Executor {
	/**
	 * 实现查询
	 *
	 * @param statement
	 * @param parameter
	 * @param <T>
	 * @return
	 */
	<T> T query(String statement, Object parameter);

	/**
	 * 添加
	 *
	 * @param statement
	 * @param parameter
	 * @param <T>
	 * @return
	 */
	<T> T create(String statement, Object parameter);

	/**
	 * 修改
	 *
	 * @param statement
	 * @param parameter
	 * @param <T>
	 * @return
	 */
	<T> T update(String statement, Object parameter);

	/**
	 * 删除
	 *
	 * @param statement
	 * @param parameter
	 * @param <T>
	 * @return
	 */
	<T> T delete(String statement, Object parameter);
}
