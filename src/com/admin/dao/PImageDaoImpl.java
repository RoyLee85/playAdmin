package com.admin.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.admin.VO.PImageVO;

@Component
public class PImageDaoImpl extends SqlSessionDaoSupport implements PImageDao {
	
	private final static String NAMESPACE = "PImageDao";
	
	public List<PImageVO> pImageList(int gNum){
		List<PImageVO> list = getSqlSession().selectList(NAMESPACE + ".pImageList", gNum);
		return list;
	}
	
	public int pImageCount(int gNum){
		return getSqlSession().selectOne(NAMESPACE + ".pImageCount", gNum);
				
	}

	public int insert(PImageVO vo) {
		return getSqlSession().insert(NAMESPACE + ".insert", vo);
		
	}
	public int getMaxINum(){
		return getSqlSession().selectOne(NAMESPACE + ".getMaxINum");
	}

}
