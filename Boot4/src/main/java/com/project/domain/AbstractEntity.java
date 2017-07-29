package com.project.domain;

import java.time.*;
import java.time.format.*;

import javax.persistence.*;
import javax.persistence.Id;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import com.fasterxml.jackson.annotation.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

@Id
@GeneratedValue
@JsonProperty
private Long id;

@CreatedDate
private LocalDateTime createDate;

@LastModifiedDate
private LocalDateTime ModifiedDate;


public Long getId() {
	return id;
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
	AbstractEntity other = (AbstractEntity) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}


public String getFormatTime(){
	return getFormatedModifiedTime(createDate, "yyyy-MM-dd HH:mm:ss");
}

public String getModifiedTime(){
	return getFormatedModifiedTime(ModifiedDate, "yyyy-MM-dd HH:mm:ss");
}

public String getFormatedModifiedTime(LocalDateTime dateTime,String format){
	if(dateTime == null){
		return "";
	}
	return dateTime.format(DateTimeFormatter.ofPattern(format));
}

	
}
