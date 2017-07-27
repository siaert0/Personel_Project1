package com.example.Boot4;

import java.time.*;
import java.time.format.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
public class AnswerVO {

	public AnswerVO() {
		super();
	}

	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;
	
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
	
	private LocalDateTime createDate;  // JsonProperty를 추가하지 않는건 밑에 getFormatTime을 쓰기 때문에!
	
	public AnswerVO(UserVO writer, String contents, QuestionVO question) {
		super();
		this.writer = writer;
		this.contents = contents;
		this.question = question;
		this.createDate = LocalDateTime.now();
	}
	
	public String getFormatTime(){
		if(createDate == null){
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yy.MM.dd HH:mm:ss"));
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
		AnswerVO other = (AnswerVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + ", createDate=" + createDate
				+ "]";
	}
	
}
