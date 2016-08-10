package com.ubn.service;

import com.ubn.model.DCF;

public interface DCFService {

	void save(DCF dcf);

	DCF findByContentId(String contentId);

}