package com.admin.dao;

import java.util.List;

import com.admin.VO.PImageVO;

public abstract interface PImageDao {
	
	public abstract List<PImageVO> pImageList(int gNum);
	
	public abstract int pImageCount(int gNum);

	public abstract int insert(PImageVO vo);
	
	public abstract int getMaxINum ();
}
