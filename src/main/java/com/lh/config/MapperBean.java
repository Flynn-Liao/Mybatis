package com.lh.config;

import lombok.Data;

import java.util.List;

/**
 * mapper文件中的内容
 *
 * @author huan
 */
@Data
public class MapperBean {
	/**
	 * 接口名
	 */
	private String interfaceName;
	/**
	 * 接口下所有方法
	 */
	private List<Function> list;
}
