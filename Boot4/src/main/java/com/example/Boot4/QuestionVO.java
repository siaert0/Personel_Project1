package com.example.Boot4;

import javax.persistence.*;

@Entity
public class QuestionVO {

@Id
@GeneratedValue
private Long id;


@Column(nullable=false,unique=true)
private String title;
private String contents;

@Column
private String writer;


public String getWriter() {
	return writer;
}
public void setWriter(String writer) {
	this.writer = writer;
}



@Override
public String toString() {
	return "라이터"+writer+"제목"+title+"내용"+contents; 
}

public QuestionVO(){};

public QuestionVO(String writer, String title, String contents) {
	super();
	this.writer = writer;
	this.title = title;
	this.contents = contents;
}




}
