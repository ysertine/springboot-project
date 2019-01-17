package com.ysertine.common.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * @Title BaseService.java
 * @Description 通用的Service接口
 * @author DengJinbo
 * @date 2019年1月15日
 */
public interface BaseService<T> {

	/**
	 * @Title save 
	 * @Description 保存一个实体，null的属性也会保存，不会使用数据库默认值
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	int save(T record);
	
	/**
	 * @Title saveSelective 
	 * @Description 保存一个实体，null的属性也不会保存，会使用数据库默认值
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	int saveSelective(T record);
	
	/**
	 * @Title delete 
	 * @Description 根据实体属性作为条件进行删除，查询条件使用等号
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	int delete(T record);
	
	/**
	 * @Title deleteByPrimaryKey 
	 * @Description 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param key
	 * @return
	 */
	int deleteByPrimaryKey(Object key);
	
	/**
	 * @Title updateByPrimaryKey 
	 * @Description 根据主键更新实体全部字段，null值会被更新
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);
	
	/**
	 * @Title updateByPrimaryKeySelective 
	 * @Description 根据主键更新属性不为null的值
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);
	
	/**
	 * @Title getByPrimaryKey 
	 * @Description 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param key
	 * @return
	 */
	T getByPrimaryKey(Object key);
	
	/**
	 * @Title getByCriteria 
	 * @Description 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	T getByCriteria(T record);
	
	/**
	 * @Title listByCriteria 
	 * @Description 根据实体中的属性值进行查询，查询条件使用等号
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	List<T> listByCriteria(T record);
	
	/**
	 * @Title listAll 
	 * @Description 查询全部结果，select(null)方法能达到同样的效果
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @return
	 */
	List<T> listAll();
	
	/**
	 * @Title countByCriteria 
	 * @Description 根据实体中的属性查询总数，查询条件使用等号
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param record
	 * @return
	 */
	int countByCriteria(T record);
	
	/**
	 * @Title getPageInfo 
	 * @Description 查询分页
	 * @author DengJinbo
	 * @date 2019年1月15日
	 * @version 1.0
	 * @param pageNum 页码
	 * @param pageSize 每页显示数量
	 * @param orderBy 排序
	 * @return
	 */
	PageInfo<T> getPageInfo(int pageNum, int pageSize, String orderBy);
	
	/**
	 * @Title getPageInfo 
	 * @Description 根据条件查询分页
	 * @author DengJinbo
	 * @date 2019年1月17日
	 * @version 1.0
	 * @param pageNum 页码
	 * @param pageSize 每页显示数量
	 * @param orderBy 排序
	 * @param record
	 * @return
	 */
	PageInfo<T> getPageInfo(int pageNum, int pageSize, String orderBy, T record);
}
