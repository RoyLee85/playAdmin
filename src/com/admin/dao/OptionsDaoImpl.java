package com.admin.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.admin.VO.OptionsVO;

@Component
public class OptionsDaoImpl extends SqlSessionDaoSupport implements OptionsDao {
	
	private final static String NAMESPACE = "OptionsDao";

	public List<OptionsVO> optionsList(int gNum){
		List<OptionsVO> list = getSqlSession().selectList(NAMESPACE + ".optionsList", gNum);
		return list;
	}
	
	public int optionsCount(int gNum){
		return getSqlSession().selectOne(NAMESPACE + ".optionsCount", gNum);
				
	}
	
	public int insert(OptionsVO vo) {
		return getSqlSession().insert(NAMESPACE + ".insert", vo);
		
	}
	
	public int getMaxONum(){
		return getSqlSession().selectOne(NAMESPACE + ".getMaxONum");
	}
	
	

}
