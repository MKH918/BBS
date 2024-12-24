package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 我的收藏
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2024-04-18 12:00:28
 */
@TableName("forumstore")
public class ForumstoreEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ForumstoreEntity() {
		
	}
	
	public ForumstoreEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 帖子id
	 */
					
	private String fid;
	
	/**
	 * 帖子标题
	 */
					
	private String title;
	
	/**
	 * 发布人
	 */
					
	private String username;
	
	/**
	 * 头像
	 */
					
	private String avatarurl;
	
	/**
	 * 收藏用户id
	 */
					
	private String uid;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：帖子id
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}
	/**
	 * 获取：帖子id
	 */
	public String getFid() {
		return fid;
	}
	/**
	 * 设置：帖子标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：帖子标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：发布人
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：发布人
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：头像
	 */
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	/**
	 * 获取：头像
	 */
	public String getAvatarurl() {
		return avatarurl;
	}
	/**
	 * 设置：收藏用户id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：收藏用户id
	 */
	public String getUid() {
		return uid;
	}

}
