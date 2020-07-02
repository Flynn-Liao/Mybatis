package com.lh.sqlsession;

import com.lh.config.DbConfig;
import com.lh.executor.Executor;
import com.lh.executor.MyExecutor;

import java.lang.reflect.Proxy;

public class MySqlsession {
    private Executor executor = new MyExecutor();
    private DbConfig dbConfig = new DbConfig();

    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    public <T> T getMapper(Class<T> clazz) {
        // 动态代理调用
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new MyMapperProxy(dbConfig, this));
    }
}
