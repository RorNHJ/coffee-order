package com.hyunjina.coffeeorder.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyunjina.coffeeorder.entity.SequenceEntity;
import com.hyunjina.coffeeorder.repository.SequenceRepository;

/**
 * @class Name : SequenceService.java
 * @Description : 시퀀스 생성 서비스
 * @Modification Information
 * @ Date			Author			Description
 * @ ------------	-------------	-------------
 * @ 2022. 7. 10.		나현지			최초 생성
 */
@Service
public class SequenceService {
	private static final Logger logger = LoggerFactory.getLogger(SequenceService.class);

	@Autowired
	private SequenceRepository sequenceRepository;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getSequence(String seqNm) {
		
		Optional<SequenceEntity> sequenceOpt = sequenceRepository.findById(seqNm);
		
		SequenceEntity sequenceEntity = sequenceOpt.get();
		
		sequenceEntity = sequenceEntity.toBuilder().nowVal(sequenceEntity.getNowVal().add(new BigDecimal(1))).build();
		
		sequenceRepository.save(sequenceEntity);
		String rtn = seqNm +"-"+ String.format("%08d", sequenceEntity.getNowVal().toBigInteger());
		return rtn;
	}
}
