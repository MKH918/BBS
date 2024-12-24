package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.ForumstoreDao;
import com.cl.entity.ForumstoreEntity;
import com.cl.service.ForumstoreService;
import com.cl.entity.view.ForumstoreView;

@Service("forumstoreService")
public class ForumstoreServiceImpl extends ServiceImpl<ForumstoreDao, ForumstoreEntity> implements ForumstoreService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumstoreEntity> page = this.selectPage(
                new Query<ForumstoreEntity>(params).getPage(),
                new EntityWrapper<ForumstoreEntity>()
        );
        return new PageUtils(page);
    }

	@Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ForumstoreEntity> wrapper) {
		Page<ForumstoreView> page = new Query<ForumstoreView>(params).getPage();
		page.setRecords(baseMapper.selectListView(page, wrapper));
		PageUtils pageUtil = new PageUtils(page);
		return pageUtil;
	}
    
	@Override
	public List<ForumstoreView> selectListView(Wrapper<ForumstoreEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ForumstoreView selectView(Wrapper<ForumstoreEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
