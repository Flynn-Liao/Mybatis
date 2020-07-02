package com.lh;

import com.lh.mapper.UserMapper;
import com.lh.model.User;
import com.lh.sqlsession.MySqlsession;

/**
 * 测试
 *
 * @author huan
 */
public class Test {

	public static void main(String[] args) {
		MySqlsession sqlSession = new MySqlsession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.getUserById("1");
		System.out.println("用户的姓名：" + user.getUsername());
	}
}
