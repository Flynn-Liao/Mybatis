package com.lh.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lh.util.ParseXml;
import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 读取与解析配置信息，并返回处理后的Environment
 *
 * @author huan
 */
public class DbConfig {
	private static ClassLoader loader = ClassLoader.getSystemClassLoader();
	private String DATABASE = "database";

	/**
	 * 读取xml信息并处理
	 *
	 * @param resource
	 * @return
	 */
	public Connection build(String resource) {
		try {
			InputStream stream = loader.getResourceAsStream(resource);
			Element root = ParseXml.readXMl(stream);
			return evalDs(root);
		} catch (Exception e) {
			throw new RuntimeException("error occured while evaling xml " + resource);
		}
	}

	/**
	 * xml节点
	 *
	 * @param node
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Connection evalDs(Element node) throws ClassNotFoundException {
		if (!DATABASE.equals(node.getName())) {
			throw new RuntimeException("root should be <database>");
		}
		String driverClassName = null;
		String url = null;
		String username = null;
		String password = null;
		List property = node.elements("property");
		// 获取属性节点
		for (Object item : property) {
			Element i = (Element) item;
			String value = getValue(i);
			String name = i.attributeValue("name");
			if (name == null || value == null) {
				throw new RuntimeException("[database]: <property> should contain name and value");
			}
			// 赋值
			switch (name) {
				case "url":
					url = value;
					break;
				case "username":
					username = value;
					break;
				case "password":
					password = value;
					break;
				case "driverClassName":
					driverClassName = value;
					break;
				default:
					throw new RuntimeException("[database]: <property> unknown name");
			}
		}

		Class.forName(driverClassName);
		Connection connection = null;
		try {
			// 建立数据库链接
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 获取property属性的值,如果有value值,则读取 没有设置value,则读取内容
	 *
	 * @param node
	 * @return
	 */
	private String getValue(Element node) {
		return node.hasContent() ? node.getText() : node.attributeValue("value");
	}

	/**
	 * 读取mapper文件
	 *
	 * @param path
	 * @return
	 */
	public MapperBean readMapper(String path) {
		MapperBean mapper = new MapperBean();
		try {
			InputStream stream = loader.getResourceAsStream(path);
			Element root = ParseXml.readXMl(stream);
			// 把mapper节点的nameSpace值存为接口名
			mapper.setInterfaceName(root.attributeValue("nameSpace").trim());
			// 用来存储方法的List
			List<Function> list = new ArrayList<>();
			// 遍历根节点下所有子节点
			for (Iterator rootIter = root.elementIterator(); rootIter.hasNext(); ) {
				// 用来存储一条方法的信息
				Function fun = new Function();
				Element e = (Element) rootIter.next();
				String sqlType = e.getName().trim();
				String funcName = e.attributeValue("id").trim();
				String sql = e.getText().trim();
				String resultType = e.attributeValue("resultType").trim();
				fun.setSqlType(sqlType);
				fun.setFuncName(funcName);
				Object newInstance = null;
				try {
					newInstance = Class.forName(resultType).newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				fun.setResultType(newInstance);
				fun.setSql(sql);
				list.add(fun);
			}
			mapper.setList(list);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return mapper;
	}
}