package com.ubn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ubn.model.DCF;

public interface DCFRepository extends JpaRepository<DCF, Long>{

	DCF findByContentId(String paramString);
	
}
