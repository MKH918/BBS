package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ForumstoreEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumstoreView;


/**
 * 我的收藏
 *
 * @author 
 * @email 
 * @date 2024-04-18 12:00:28
 */
public interface ForumstoreService extends IService<ForumstoreEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ForumstoreView> selectListView(Wrapper<ForumstoreEntity> wrapper);
   	
   	ForumstoreView selectView(@Param("ew") Wrapper<ForumstoreEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ForumstoreEntity> wrapper);
   	

}

