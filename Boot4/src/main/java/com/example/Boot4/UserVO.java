package com.example.Boot4;

import javax.persistence.*;

@Entity
public class UserVO {

@Id
@GeneratedValue
private Long id;

@Column(nullable=false)
private String userId;
private String password;
private String name;
private String email;
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public void update(UserVO newUser) {
	this.userId = newUser.userId;
	this.name = newUser.name;
	this.email = newUser.email;
}


}
