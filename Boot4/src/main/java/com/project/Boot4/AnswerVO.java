package com.project.Boot4;

import java.time.*;
import java.time.format.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
public class AnswerVO extends AbstractEntity{

	public AnswerVO() {
		super();
	}
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_writer"))
	@JsonProperty
	private UserVO writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_to_question"))
	@JsonProperty
	private QuestionVO question;
	
	@Lob
	@JsonProperty
	private String contents;
	
	public AnswerVO(UserVO writer, String contents, QuestionVO question) {
		super();
		this.writer = writer;
		this.contents = contents;
		this.question = question;
	}

	public boolean isSameUser(UserVO loginUser) {
		return this.writer.equals(loginUser);
	}
	
}
