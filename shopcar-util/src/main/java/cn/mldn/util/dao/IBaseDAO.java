package cn.mldn.util.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 定义数据表的数据层抽象标准
 * @author mldn
 * @param <K> 表的主键类型，常见的主键类型为String、Long
 * @param <V> 要操作的VO类型
 */
public interface IBaseDAO<K,V> {
	/**
	 * 进行数据的增加操作，调用INSERT语法进行SQL执行
	 * @param vo 要进行增加的数据的VO类对象（该对象不允许为null）
 	 * @return 增加成功返回true，否则返回false
	 * @throws SQLException 数据库的操作异常
	 */
	public boolean doCreate(V vo) throws SQLException ;
	/**
	 * 进行数据的修改处理操作，调用UPDATE语法执行，是以ID为修改条件
	 * @param vo 要修改的新数据
	 * @return 如果修改的数据成功返回true，如果修改数据不存在则返回false
	 * @throws SQLException 数据库操作异常
	 */
	public boolean doEdit(V vo) throws SQLException ;
	/**
	 * 使用指定的ID进行数据行的删除处理。
	 * @param id 要删除的数据编号
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException 数据库操作异常
	 */
	public boolean doRemove(K id) throws SQLException ;
	/**
	 * 是进行数据的批量删除，可以直接在删除时利用IN限定符处理
	 * @param ids 包含了所有要删除的数据的ID，不能重复，不能为空（not null），必须有内容（size()&gt;0）
	 * @return 全部删除成功返回true，如果返回false表示删除失败
	 * @throws SQLException  数据库操作异常
	 */
	public boolean doRemove(Set<K> ids) throws SQLException ;
	/**
	 * 根据ID查询该主键对应的完整的数据行信息
	 * @param id 要查询的编号
	 * @return 如果指定的id存在则将数据行以VO的形式返回，如果不存在返回null
	 * @throws SQLException 数据库操作异常
	 */
	public V findById(K id) throws SQLException ;
	/**
	 * 执行数据表的全部数据查询操作
	 * @return 如果有数据则数据以List集合返回，如果没有数据则集合长度为0（size()==0，不是null）
	 * @throws SQLException 数据库操作异常
	 */
	public List<V> findAll() throws SQLException ;
	/**
	 * 进行数据的分页查询
	 * @param currentPage 表示当前所在页
	 * @param lineSize 每页显示的数据行
	 * @return 如果有数据则数据以List集合返回，如果没有数据则集合长度为0（size()==0，不是null）
	 * @throws Exception 数据库操作异常
	 */
	public List<V> findAll(Long currentPage,Integer lineSize) throws Exception ;
	/**
	 * 进行数据的指定字段的模糊查询操作，结果也需要进行分页显示
	 * @param column 模糊查询的数据列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 表示当前所在页
	 * @param lineSize 每页显示的数据行
	 * @return 如果有数据则数据以List集合返回，如果没有数据则集合长度为0（size()==0，不是null）
	 * @throws Exception 数据库操作异常
	 */
	public List<V> findSplit(String column,String keyWord,Long currentPage,Integer lineSize) throws Exception ;
	/**
	 * 统计数据表中的所有数据行数，使用COUNT()函数完成，该操作不允许返回null
	 * @return 返回统计结果，如果没有数据行返回0
	 * @throws SQLException 数据库操作异常
	 */
	public Long getAllCount() throws SQLException ;
	/**
	 * 模糊查询的返回的记录行数
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @return 返回统计结果，如果没有数据行返回0 
	 * @throws SQLException 数据库操作异常
	 */
	public Long getSplitCount(String column,String keyWord) throws SQLException ;
}
