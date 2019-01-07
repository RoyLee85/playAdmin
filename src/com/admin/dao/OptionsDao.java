package com.admin.dao;

import java.util.List;

import com.admin.VO.OptionsVO;

public abstract interface OptionsDao {

	public abstract List<OptionsVO> optionsList(int gNum);
	
	public abstract int optionsCount(int gNum);
	
	public abstract int insert(OptionsVO vo);
	
	public abstract int getMaxONum ();
	
	
	
}
