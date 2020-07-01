package com.nami.hamakko.repsitory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nami.hamakko.mybatis.domain.MUserInfo;
import com.nami.hamakko.mybatis.mapper.MUserInfoMapper;

@Repository
@MapperScan("com.nami.hamakko.mybatis.mapper")
public class MUserInfoRepository {

	@Autowired
	MUserInfoMapper mui;
	
	public MUserInfo getUserInfo(String userId) {
		return mui.selectByPrimaryKey(userId);
	}
	
}
