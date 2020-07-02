package com.lh.sqlsession;

import com.lh.config.Function;
import com.lh.config.MapperBean;
import com.lh.config.DbConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyMapperProxy implements InvocationHandler {

	private MySqlsession mySqlsession;

	private DbConfig dbConfig;

	public MyMapperProxy(DbConfig dbConfig, MySqlsession mySqlsession) {
		this.dbConfig = dbConfig;
		this.mySqlsession = mySqlsession;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		MapperBean readMapper = dbConfig.readMapper("UserMapper.xml");
		// 是否是xml文件对应的接口
		if (!method.getDeclaringClass().getName().equals(readMapper.getInterfaceName())) {
			return null;
		}
		List<Function> list = readMapper.getList();
		if (null != list || 0 != list.size()) {
			for (Function function : list) {
				// id是否和接口方法名一样
				if (method.getName().equals(function.getFuncName())) {
					return mySqlsession.selectOne(function.getSql(), String.valueOf(args[0]));
				}
			}
		}
		return null;
	}
}