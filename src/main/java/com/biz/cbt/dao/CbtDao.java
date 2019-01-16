package com.biz.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.cbt.sql.CbtSQL;
import com.biz.cbt.vo.CbtVO;

//CRUD
public interface CbtDao {
	
	
	@Select(CbtSQL.CB_ALL)
	public List<CbtVO> selectAll();
	
	@Select(CbtSQL.CB_FIND_NUM)
	public CbtVO findByNum(String cb_num);
	
	@Insert(CbtSQL.CB_INSERT)
	public int insert(CbtVO vo);
	
	@Update(CbtSQL.CB_UPDATE)
	public int update(CbtVO vo);
	
	@Delete(CbtSQL.CB_DELETE)
	public int delete(String cb_num);
	
	

}
