package com.project.Boot4;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerVO, Long>{ 
	
}
