package com.project.Boot4;

import java.awt.print.Pageable;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionVO, Long>{
}
