package com.lh.config;

import lombok.Data;

/**
 * mybatis中对应的函数内容
 *
 * @author huan
 */
@Data
public class Function {

	/**
	 * sql类型（如select，insert，update，delete）
	 */
	private String sqlType;

	/**
	 * 函数名称
	 */
	private String funcName;

	/**
	 * sql语句
	 */
	private String sql;

	/**
	 * 返回类型
	 */
	private Object resultType;

	/**
	 * 参数类型
	 */
	private String parameterType;
}
