package com.project.Boot4;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
public class UserVO extends AbstractEntity{


@Column(nullable=false,unique=true)
@JsonProperty  // JSON으로 값을 내려보내기 위해 getter 메소드를 추가해 줘야 하지만 이렇게 애노테이션으로도 추가가능
private String userId;

@JsonIgnore // 패스워드 보안상의 문제도 JSON에서 IGNORE 처리 시킨다.
private String password;

@JsonProperty
private String name;

@JsonProperty
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


public boolean matchId(Long id2){
	if(id2 == null){
		return false; 
	}
	return id2.equals(getId());
}


public boolean matchUserLogin(String inUserId,String isUserPassword){
	return inUserId.equals(userId) && isUserPassword.equals(password); 
}

public void update(UserVO newUser) {
	this.userId = newUser.userId;
	this.name = newUser.name;
	this.email = newUser.email;
}


}
