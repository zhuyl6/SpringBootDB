package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	/**
	 * 基于菜单id获取权限标识
	 * @param menuIds
	 * @return
	 */
	List<String> findPermisssions(
			@Param("menuIds") Integer[] menuIds);
	
	/**
	 *  将对象持久化到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	/**
	  *  将对象持久化到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysMenu entity);
	
	@Select("select id,name,parentId from sys_menus")
	List<Node> findZtreeMenuNodes();
	/**
	  * 删除菜单自身信息
	 * @param id
	 * @return
	 */
	@Delete("delete from sys_menus where id=#{id}")
	int deleteObject(Integer id);
	/**
	  *  基于id统计子菜单数
	 * @param id
	 * @return
	 */
	@Select("select count(*) from sys_menus where parentId=#{id}")
	int getChildCount(Integer id);
	
    /**
     *	 查询所有菜单以及上级菜单信息
     *   @return 所有菜单
     */
	@Select("select c.*,p.name parentName "
			+ " from sys_menus c left join sys_menus p"
			+ " on c.parentId=p.id")
	List<Map<String,Object>> findObjects();
}




