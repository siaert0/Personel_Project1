package com.project.domain;

import org.springframework.data.jpa.repository.*;

public interface UservoRepository extends JpaRepository<UserVO, Long>{ // 참조클래스와 프라이머리키 타입
	UserVO findByUserId(String userId);
}
