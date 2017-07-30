package com.project.Boot4;


import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;


public interface QuestionRepository extends JpaRepository<QuestionVO, Long>{
}
