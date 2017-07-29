package com.example.Boot4;

import java.time.*;
import java.time.format.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;

@Entity
public class QuestionVO {

@Id
@GeneratedValue
@JsonProperty
private Long id;

@ManyToOne
@JoinColumn(foreignKey= @ForeignKey(name="fk_questionVO_writer"))
@JsonProperty
private UserVO writer; 

@JsonProperty
private String title;

@JsonProperty
private String contents;

@JsonProperty
private Integer countOfAnswer = 0;

private LocalDateTime createTime;

@OneToMany(mappedBy="question")    //한개의 질문은 다수개의 댓글을 가지고 있을 수 있다!!, mappedBy의 값은  answervo의 필드값
@OrderBy("id DESC")
private List<AnswerVO> answers;



@Override
public String toString() {
	return "라이터"+writer+"제목"+title+"내용"+contents; 
}

public QuestionVO(){};

public QuestionVO(UserVO writer, String title, String contents) {
	super();
	this.writer = writer;
	this.title = title;
	this.contents = contents;
	this.createTime = LocalDateTime.now();
}

public String getFormatTime(){
	if(createTime == null){
		return "";
	}
	return createTime.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm:ss"));
}

public void update(String title, String contents) {
	this.title = title;
	this.contents = contents;
}

public boolean isSameUser(UserVO loginUser) {
	return this.writer.equals(loginUser); // 이렇게만 하면 무조건 false가 떨어진다. 이유는 
										  // equals가 갖는 특징떄문이다. 
}


public void addAnswer() {
	this.countOfAnswer += 1;
}

public void deleteAnswer() {
	this.countOfAnswer -= 1;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	QuestionVO other = (QuestionVO) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}






}
