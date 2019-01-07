package com.admin.dao;

import com.admin.VO.GoodsVO;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class GoodsDaoImpl extends SqlSessionDaoSupport implements GoodsDao {
	
	private final static String NAMESPACE = "GoodsDao";
	
	public List<GoodsVO> list(Map<String, Object> map) {
		List<GoodsVO> list = getSqlSession().selectList(NAMESPACE + ".goodsList", map);
		return list;
	}

	public int getCount(Map<String, Object> map) {
		return ((Integer) getSqlSession().selectOne(NAMESPACE + ".goodsCount", map)).intValue();
	}

	public int insert(GoodsVO vo) {
		return getSqlSession().insert(NAMESPACE + ".insert", vo);
		
	}
	public int getMaxgNum(){
		return getSqlSession().selectOne(NAMESPACE + ".getMaxgNum");
	}

	public GoodsVO getInfoGoods(int gNum) {
		return getSqlSession().selectOne(NAMESPACE + ".getInfoGoods", gNum);
	}

	public int addHit(int gNum) {
		return getSqlSession().update(NAMESPACE + ".addHit", gNum);
		
	}

}
