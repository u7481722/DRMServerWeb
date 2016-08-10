package com.ubn.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ubn.model.DCF;
import com.ubn.repository.DCFRepository;

@Service
public class DCFServiceImpl implements DCFService {
	
	@Resource
	DCFRepository dcfRepository;

	@Override
	public void save(DCF dcf) {
		this.dcfRepository.save(dcf);
	}

	@Override
	public DCF findByContentId(String contentId) {
		return this.dcfRepository.findByContentId(contentId);
	}

}
