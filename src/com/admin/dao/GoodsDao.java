package com.admin.dao;

import com.admin.VO.GoodsVO;

import java.util.List;
import java.util.Map;

public abstract interface GoodsDao {
	
	public abstract List<GoodsVO> list(Map<String, Object> paramMap);

	public abstract int getCount(Map<String, Object> paramMap);

	public abstract int insert(GoodsVO vo);
	
	public abstract int getMaxgNum ();
	
	public abstract GoodsVO getInfoGoods(int gNum);
	
	public abstract int addHit(int gNum);
	
}
