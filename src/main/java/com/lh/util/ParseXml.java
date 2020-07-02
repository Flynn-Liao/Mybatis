package com.lh.util;

import com.lh.config.DbConfig;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;

/**
 * 解析xml工具
 *
 * @author huan
 */
public class ParseXml {
	private static DbConfig dbConfig = new DbConfig();

	/**
	 * 解析xml
	 *
	 * @param stream
	 * @return
	 * @throws DocumentException
	 */
	public static Element readXMl(InputStream stream) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(stream);
		return document.getRootElement();
	}

	/**
	 * 获取数据库配置信息
	 *
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Connection connection = dbConfig.build("config.xml");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
