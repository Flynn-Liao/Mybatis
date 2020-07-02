package com.lh.mapper;

import com.lh.model.User;

/**
 * mapper文件
 *
 * @author huan
 */
public interface UserMapper {
	/**
	 * 通过用户id查询内容
	 *
	 * @param id
	 * @return
	 */
	User getUserById(String id);
}
