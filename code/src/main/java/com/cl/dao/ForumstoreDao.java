package com.cl.dao;

import com.cl.entity.ForumstoreEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumstoreView;


/**
 * 我的收藏
 * 
 * @author 
 * @email 
 * @date 2024-04-18 12:00:28
 */
public interface ForumstoreDao extends BaseMapper<ForumstoreEntity> {
	
	List<ForumstoreView> selectListView(@Param("ew") Wrapper<ForumstoreEntity> wrapper);

	List<ForumstoreView> selectListView(Pagination page,@Param("ew") Wrapper<ForumstoreEntity> wrapper);
	
	ForumstoreView selectView(@Param("ew") Wrapper<ForumstoreEntity> wrapper);
	

}
