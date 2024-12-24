package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.ForumstoreEntity;
import com.cl.entity.view.ForumstoreView;

import com.cl.service.ForumstoreService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 我的收藏
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-18 12:00:28
 */
@RestController
@RequestMapping("/forumstore")
public class ForumstoreController {
    @Autowired
    private ForumstoreService forumstoreService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ForumstoreEntity forumstore,
		HttpServletRequest request){
        EntityWrapper<ForumstoreEntity> ew = new EntityWrapper<ForumstoreEntity>();

		PageUtils page = forumstoreService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumstore), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ForumstoreEntity forumstore, 
		HttpServletRequest request){
        EntityWrapper<ForumstoreEntity> ew = new EntityWrapper<ForumstoreEntity>();

		PageUtils page = forumstoreService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, forumstore), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ForumstoreEntity forumstore){
       	EntityWrapper<ForumstoreEntity> ew = new EntityWrapper<ForumstoreEntity>();
      	ew.allEq(MPUtil.allEQMapPre( forumstore, "forumstore")); 
        return R.ok().put("data", forumstoreService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ForumstoreEntity forumstore){
        EntityWrapper< ForumstoreEntity> ew = new EntityWrapper< ForumstoreEntity>();
 		ew.allEq(MPUtil.allEQMapPre( forumstore, "forumstore")); 
		ForumstoreView forumstoreView =  forumstoreService.selectView(ew);
		return R.ok("查询我的收藏成功").put("data", forumstoreView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ForumstoreEntity forumstore = forumstoreService.selectById(id);
		forumstore = forumstoreService.selectView(new EntityWrapper<ForumstoreEntity>().eq("id", id));
        return R.ok().put("data", forumstore);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ForumstoreEntity forumstore = forumstoreService.selectById(id);
		forumstore = forumstoreService.selectView(new EntityWrapper<ForumstoreEntity>().eq("id", id));
        return R.ok().put("data", forumstore);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ForumstoreEntity forumstore, HttpServletRequest request){
    	forumstore.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(forumstore);
        forumstoreService.insert(forumstore);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ForumstoreEntity forumstore, HttpServletRequest request){
    	forumstore.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(forumstore);
        forumstoreService.insert(forumstore);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ForumstoreEntity forumstore, HttpServletRequest request){
        //ValidatorUtils.validateEntity(forumstore);
        forumstoreService.updateById(forumstore);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        forumstoreService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
