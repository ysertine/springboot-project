package com.ysertine.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysertine.common.service.BaseService;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * @Title BaseServiceImpl.java
 * @Description 通用的Service接口实现类
 * @author DengJinbo
 * @date 2019年1月15日
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseMapper<T> baseMapper;

	@Override
	public int save(T record) {
		return baseMapper.insert(record);
	}

	@Override
	public int saveSelective(T record) {
		return baseMapper.insertSelective(record);
	}

	@Override
	public int delete(T record) {
		return baseMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return baseMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public T getByPrimaryKey(Object key) {
		return baseMapper.selectByPrimaryKey(key);
	}

	@Override
	public T getByCriteria(T record) {
		return baseMapper.selectOne(record);
	}

	@Override
	public List<T> listByCriteria(T record) {
		return baseMapper.select(record);
	}

	@Override
	public List<T> listAll() {
		return baseMapper.selectAll();
	}

	@Override
	public int countByCriteria(T record) {
		return baseMapper.selectCount(record);
	}

	@Override
	public PageInfo<T> getPageInfo(int pageNum, int pageSize, String orderBy) {
		return PageHelper.startPage(pageNum, pageSize).setOrderBy(orderBy)
				.doSelectPageInfo(() -> baseMapper.selectAll());
	}
}