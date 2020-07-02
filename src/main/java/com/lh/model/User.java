package com.lh.model;

import lombok.Data;
import lombok.ToString;

/**
 * 用户实体
 *
 * @author huan
 */
@Data
@ToString
public class User {
	private String id;
	private String username;
	private String password;
}
