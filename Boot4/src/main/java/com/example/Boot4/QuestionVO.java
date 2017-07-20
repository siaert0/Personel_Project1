package com.example.Boot4;

import java.time.*;
import java.time.format.*;

import javax.persistence.*;

@Entity
public class QuestionVO {

@Id
@GeneratedValue
private Long id;

@ManyToOne
@JoinColumn(foreignKey= @ForeignKey(name="fk_questionVO_writer"))
private UserVO writer;
private String title;
private String contents;
private LocalDateTime time;


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
	this.time = LocalDateTime.now();
}

public String getFormatTime(){
	if(time == null){
		return "";
	}
	return time.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm:ss"));
}

public void update(String title, String contents) {
	this.title = title;
	this.contents = contents;
}




}
