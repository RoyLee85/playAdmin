package com.admin.controller;

import com.admin.VO.GoodsVO;
import com.admin.VO.OptionsVO;
import com.admin.VO.PImageVO;
import com.admin.dao.GoodsDao;
import com.admin.dao.OptionsDao;
import com.admin.dao.PImageDao;
import com.admin.paging.Paging;
import com.nexacro.xapi.data.DataSet;
import com.nexacro.xapi.data.DataTypes;
import com.nexacro.xapi.data.PlatformData;
import com.nexacro.xapi.data.VariableList;
import com.nexacro.xapi.data.datatype.DataType;
import com.nexacro.xapi.tx.HttpPlatformRequest;
import com.nexacro.xapi.tx.HttpPlatformResponse;
import com.nexacro.xapi.tx.PlatformException;
import com.nexacro.xapi.tx.PlatformType;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(getClass());
	private int pageSize = 10;
	private int blockCount = 10;
	int count = 0;
		
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private OptionsDao optionsDao;
	
	@Autowired
	private PImageDao pImageDao;

	@RequestMapping({ "/list.do" })
	public void list(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord1) throws PlatformException, UnsupportedEncodingException{

		
		String keyWord = new String(keyWord1.getBytes("8859_1"), "UTF-8");
		System.out.println(keyWord);
		
		PlatformData pData = new PlatformData();
		
		System.out.println("현재페이지:" + currentPage);
		System.out.println("검색항목:" + keyField);
		System.out.println("검색어:" + keyWord);
		
		int nErrorCode = 0;
		String strErrorMsg = "START";
		
		    DataSet ds = new DataSet("listGoods");			
		    
		    ds.addColumn("gNum", DataTypes.INT, 5);
		    ds.addColumn("title", DataTypes.STRING, 100);
		    ds.addColumn("category", DataTypes.STRING, 100);
			ds.addColumn("mCompany", DataTypes.STRING, 50);
			ds.addColumn("origin", DataTypes.STRING, 30);
			ds.addColumn("dBasePrice", DataTypes.INT, 30);
			ds.addColumn("dPrice", DataTypes.INT, 30);
			ds.addColumn("gRegDate", DataTypes.DATE, 30);
			ds.addColumn("hit", DataTypes.INT, 6);
			ds.addColumn("rowNum", DataTypes.INT, 10);

			@SuppressWarnings({ "unchecked", "rawtypes" })
			HashMap<String, Object> map = new HashMap();
			map.put("keyField", keyField);
			map.put("keyWord", keyWord);
			
			count = this.goodsDao.getCount(map);
			System.out.println("카운트 : " + count);
			
			Paging page = new Paging(keyField, keyWord, currentPage, count,
					this.pageSize, this.blockCount, "list.do");
			
			map.put("start", Integer.valueOf(page.getStartCount()));
			map.put("end",Integer.valueOf(page.getEndCount()));
			
			System.out.println("start : " + Integer.valueOf(page.getStartCount()));
			System.out.println("end : " + Integer.valueOf(page.getEndCount()));
			
			List<GoodsVO> list = null;
			if (count > 0) {
				list = this.goodsDao.list(map);
				for (int i=0; i<list.size(); i++) {
					 int row = ds.newRow(); // new Row feed
					    
					 	GoodsVO vo = new GoodsVO();
					    vo = list.get(i);
					    
					    ds.set(row, "gNum", vo.getgNum()); 
					    ds.set(row, "title", vo.getTitle());
					    String category = "";
					    switch(vo.getCategory()){
					    case "v" : category = "버라이어티";
					    			break;
					    case "l" : category = "리빙";
		    						break;
					    case "c" : category = "컴퓨터";
		    						break;
					    case "e" : category = "가전";
		    						break;
					    case "s" : category = "문구";
		    						break;
					    case "o" : category = "아웃도어";
		    						break;	
					    }
					    ds.set(row, "category", category);
					    ds.set(row, "mCompany", vo.getmCompany());
					    ds.set(row, "gRegDate", vo.getgRegDate());
					    ds.set(row, "hit", vo.getHit());
					    ds.set(row, "rowNum",pageSize*(currentPage-1)+(i+1));
					}
				pData.addDataSet(ds);
					
			} else {
				list = Collections.emptyList();
			}
			
		VariableList varList = pData.getVariableList();
				
		varList.add("ErrorCode", nErrorCode);
		varList.add("ErrorMsg", strErrorMsg);
		varList.add("totalCount", count);
		
		pData.setVariableList(varList);
				
		// send the result data(XML) to Client
		HttpPlatformResponse res 
		    = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML,"UTF-8");
		res.setData(pData); 
		res.sendData();		// Send Data
	}
	
	@RequestMapping({ "/insert.do" })
	public void insert (HttpServletRequest request, HttpServletResponse response) throws PlatformException{
		PlatformData pData = new PlatformData();
		
		int nErrorCode = 0;
		String strErrorMsg = "START";
		
		HttpPlatformRequest req = new HttpPlatformRequest(request);
		req.receiveData();
		pData = req.getData();
		
		
		//---------- 상품 글 저장 ------------//
		
		DataSet ds = pData.getDataSet("goods");
		
		String title = ds.getString(0, "title");
		String content = ds.getString(0, "content");
		int price = ds.getInt(0, "price");
		String category = ds.getString(0, "category");
		String mCompany = ds.getString(0, "mCompany");
		String origin = ds.getString(0, "origin");
		int dBasePrice = ds.getInt(0, "dBasePrice");
		int dPrice = ds.getInt(0, "dPrice");
		
		GoodsVO vo = new GoodsVO();
		
		int gNum = goodsDao.getMaxgNum()+1;
		vo.setgNum(gNum);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPrice(price);
		vo.setCategory(category);
		vo.setmCompany(mCompany);
		vo.setOrigin(origin);
		vo.setdBasePrice(dBasePrice);
		vo.setdPrice(dPrice);
		
		int intError = goodsDao.insert(vo);
		
		if(intError>0){
			strErrorMsg = "게시글 정보가 성공적으로 저장되었습니다";
		}else{
			strErrorMsg = "게시글 정보 저장에 실패하였습니다";
		}
		
		//---------- Options 저장 ------------//
		
		DataSet ds1 = pData.getDataSet("options");
		
		List<OptionsVO> list = null;
		if(ds1.getRowCount() > 0){
			for(int i = 0; i < ds1.getRowCount(); i++){
				int oNum = optionsDao.getMaxONum()+1;
				String oName1 = ds1.getString(i, "oName1");
				String oName2 = ds1.getString(i, "oName2");
				int oPrice = ds1.getInt(i, "oPrice");
				int oQuantity = ds1.getInt(i, "oQuantity");
				
				OptionsVO vo1 = new OptionsVO();
				
				vo1.setoNum(oNum);
				vo1.setoName1(oName1);
				vo1.setoName2(oName2);
				vo1.setoPrice(oPrice);
				vo1.setoQuantity(oQuantity);
				vo1.setgNum(gNum);
				
				optionsDao.insert(vo1);
				
			}
		}
		
		VariableList varList = pData.getVariableList();
		
		varList.add("ErrorCode", nErrorCode);
		varList.add("ErrorMsg", strErrorMsg);
		varList.add("gNum", gNum);
		
		HttpPlatformResponse res = new HttpPlatformResponse(response,PlatformType.CONTENT_TYPE_XML,"UTF-8");
		res.setData(pData);
		res.sendData();
	}
	
	@RequestMapping({ "/imageUpload.do" })
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, int gNum) throws PlatformException,IOException{
		
		PlatformData resData = new PlatformData();
		VariableList resVarList = resData.getVariableList();
		
		String sHeader = request.getHeader("Content-Type");
		if (sHeader == null){
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		String sRealPath = request.getSession().getServletContext().getRealPath("/");
		String sPath = request.getParameter("PATH");
		String sUpPath = sRealPath + sPath;
		int nMaxSize = 20 * 1024 * 1024;	// 최대 업로드 파일 크기 : 20MB
		
		MultipartRequest multi = new MultipartRequest(request, sUpPath, nMaxSize, "utf-8", new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		
		String sFName = "";
		String sName  = "";
		String stype  = "";
		long fileSize = 0;
		File f = null;
		
		while(files.hasMoreElements()){
			sName = (String)files.nextElement();
			sFName = multi.getFilesystemName(sName);
			stype = multi.getContentType(sName);
			
			f = multi.getFile(sName);
			if (f != null){
				fileSize = f.length();
			}
			//----------DB입력-------------
			PImageVO vo = new PImageVO();
			int iNum = pImageDao.getMaxINum()+1;
			String orgName = multi.getOriginalFileName(sName);
			
			vo.setINum(iNum);
			vo.setgNum(gNum);
			vo.setOrgName(orgName);
			vo.setSaveName(sFName);
			vo.setFileSize(fileSize);
			vo.setPath(sUpPath);
			
			
			pImageDao.insert(vo);
		}
		
		HttpPlatformResponse res = new HttpPlatformResponse(response);
		res.setData(resData);
		res.sendData();
		System.out.println("서버 주소 : " + sUpPath);
	}
	
	@RequestMapping({ "/detail.do" })
	public void detail(HttpServletRequest request, HttpServletResponse response, int gNum) throws PlatformException{
		PlatformData pData = new PlatformData();
		
		int nErrorCode = 0;
		String strErrorMsg = "START" ;
		
		DataSet ds = new DataSet("goods");
		
		ds.addColumn("gNum", DataTypes.INT, 5);
	    ds.addColumn("title", DataTypes.STRING, 100);
	    ds.addColumn("price", DataTypes.INT, 15);
	    ds.addColumn("category", DataTypes.STRING, 100);
		ds.addColumn("mCompany", DataTypes.STRING, 50);
		ds.addColumn("origin", DataTypes.STRING, 30);
		ds.addColumn("dBasePrice", DataTypes.INT, 30);
		ds.addColumn("dPrice", DataTypes.INT, 30);
		ds.addColumn("gRegDate", DataTypes.DATE, 30);
		ds.addColumn("hit", DataTypes.INT, 6);
		
		GoodsVO vo = goodsDao.getInfoGoods(gNum);
		goodsDao.addHit(gNum);
				
		int row = ds.newRow(); 
		
		ds.set(row, "gNum", vo.getgNum()); 
		ds.set(row, "title", vo.getTitle());
		ds.set(row, "price", vo.getPrice());
		ds.set(row, "category", vo.getCategory());
		ds.set(row, "mCompany", vo.getmCompany());
		ds.set(row, "origin", vo.getOrigin());
		ds.set(row, "dBasePrice", vo.getdBasePrice());
		ds.set(row, "dPrice", vo.getdPrice());
		ds.set(row, "gRegDate",vo.getgRegDate());
		ds.set(row, "hit", vo.getHit());
	
		pData.addDataSet(ds);
		
		DataSet ds1 = new DataSet("options");
		ds1.addColumn("oNum",DataTypes.INT, 5);
		ds1.addColumn("gNum",DataTypes.INT, 5);
		ds1.addColumn("oName1",DataTypes.STRING, 100);
		ds1.addColumn("oName2",DataTypes.STRING, 100);
		ds1.addColumn("oPrice",DataTypes.INT, 50);
		ds1.addColumn("oQuantity",DataTypes.INT, 20);
		
		List<OptionsVO> list = null;
			list = this.optionsDao.optionsList(gNum);
			if(optionsDao.optionsCount(gNum)>0) {
				for (int i=0; i<list.size(); i++) {
					
					int row1 = ds1.newRow();
					
					OptionsVO vo1 = new OptionsVO();
					vo1 = list.get(i);
					
					ds1.set(row1, "oNum", vo1.getoNum());
					ds1.set(row1, "gNum", vo1.getgNum());
					ds1.set(row1, "oName1", vo1.getoName1());
					ds1.set(row1, "oName2", vo1.getoName2());
					ds1.set(row1, "oPrice", vo1.getoPrice());
					ds1.set(row1, "oQuantity", vo1.getoQuantity());
					
				}
				
				pData.addDataSet(ds1);
				
			} else {
				list = Collections.emptyList();
			}
		
		DataSet ds2 = new DataSet("images");
		ds2.addColumn("gridNum",DataTypes.INT, 5);
		ds2.addColumn("iNum",DataTypes.INT, 5);
		ds2.addColumn("path",DataTypes.STRING, 256);
		ds2.addColumn("fileName",DataTypes.STRING, 100);
	
		List<PImageVO> list1 = null;
		list1 = this.pImageDao.pImageList(gNum);
		if(pImageDao.pImageCount(gNum)>0) {
			for (int i=0; i<list1.size(); i++) {
				
				int row2 = ds2.newRow();
				
				PImageVO vo2 = new PImageVO();
				vo2 = list1.get(i);
				
				ds2.set(row2, "iNum", vo2.getINum());
				ds2.set(row2, "path", vo2.getPath()+"\\"+vo2.getSaveName());
				ds2.set(row2, "fileName", vo2.getOrgName());

			}
			
			pData.addDataSet(ds2);
			
		} else {
			list = Collections.emptyList();
		}
		
			
			VariableList varList = pData.getVariableList();
			
			varList.add("ErrorCode", nErrorCode);
			varList.add("ErrorMsg", strErrorMsg);
			
			pData.setVariableList(varList);
					
			// send the result data(XML) to Client
			HttpPlatformResponse res 
			    = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML,"UTF-8");
			res.setData(pData); 
			res.sendData();		// Send Data
	
	}

}
