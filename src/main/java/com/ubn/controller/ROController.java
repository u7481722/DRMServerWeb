package com.ubn.controller;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twm.ebook.drm.crypto.util.security.XmlSec;
import com.ubn.dto.DCFFormDTO;
import com.ubn.dto.RODTO;
import com.ubn.dto.ROFormDTO;
import com.ubn.model.DCF;
import com.ubn.service.DCFService;

import net.ubn.td.ro.GenRO;

@RestController
public class ROController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ROController.class);
	
	@Resource
	DCFService dcfService;

	@RequestMapping(value = {"/getRO"}, method = {RequestMethod.POST})
	public @ResponseBody RODTO getRO(@RequestBody ROFormDTO dto) {
		LOGGER.debug("publickey==>" + dto.getPublickey());
		try {
			XmlSec xs = new XmlSec();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String strDeviceId = dto.getDeviceId();
		String cek = "";
		String roTemplate = "";
		String contentType = "";
		String strContentId = dto.getContentId();
		String strPublicKey = dto.getPublickey();

		DCF dcf = dcfService.findByContentId(strContentId);
		if (dcf != null) {
			roTemplate = dcf.getRoTemplate();
			cek = dcf.getCek();
			contentType = dcf.getContentType();
		}
		RODTO roDTO = new RODTO();
		try {
			GenRO ro = new GenRO();
			String strRO = ro.genRO(strContentId, strDeviceId, contentType, roTemplate, cek, strPublicKey);
			roDTO.setReturnCode(0);
			roDTO.setReturnMessage("success");
			roDTO.setRo(strRO);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			roDTO.setReturnCode(3);
			roDTO.setReturnMessage(e.getMessage());
		}
		return roDTO;
	}

	@RequestMapping(value = {"/setRO"}, method = {RequestMethod.POST})
	@ResponseBody
	public RODTO setRO(@RequestBody DCFFormDTO dcfDTO) {
		RODTO dto = new RODTO();
		try {
			dcfService.save(constructEntity(new DCF(), dcfDTO));

			dto.setReturnCode(0);
			dto.setReturnMessage("success");
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage(), ex);
			dto.setReturnCode(-1);
			dto.setReturnMessage(ex.getMessage());
		}
		return dto;
	}

	private DCF constructEntity(DCF dcf, DCFFormDTO dto) {
		dcf.setCek(dto.getCek());
		dcf.setContentId(dto.getContentId());
		dcf.setContentType(dto.getContentType());
		dcf.setRoTemplate(dto.getRoTemplate());
		return dcf;
	}

}
