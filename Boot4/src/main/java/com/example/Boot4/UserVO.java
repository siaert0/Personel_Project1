package com.example.Boot4;

import javax.persistence.*;

@Entity
public class UserVO {

@Id
@GeneratedValue
private Long id;

@Column(nullable=false,unique=true)
private String userId;
private String password;
private String name;
private String email;




public Long getId() {
	return id;
}
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


public boolean matchId(Long id2){
	if(id2 == null){
		return false; 
	}
	return id2.equals(id);
}


public boolean matchUserId(String inUserId){
	if(inUserId == null){
		return false; 
	}
	return inUserId.equals(userId);
}


public boolean matchPassword(String inPassword){
	if(inPassword == null){
		return false; 
	}
	return inPassword.equals(password);
}


public void update(UserVO newUser) {
	this.userId = newUser.userId;
	this.name = newUser.name;
	this.email = newUser.email;
}



}
