package cn.mldn.util.dao.abs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import cn.mldn.util.dbc.DatabaseConnection;

public abstract class AbstractDAO {
	protected PreparedStatement pstmt ;
	protected Connection conn ;
	public AbstractDAO() {
		this.conn = DatabaseConnection.getConnection() ;
	}
	public Long handleCount(String tableName) throws SQLException {
		String sql = "SELECT COUNT(*) FROM " + tableName ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getLong(1) ;
		}
		return 0L ;
	} 
	/**
	 * 定义一个获取当前Session的最后一次增长ID内容
	 * @return last_insert_id()执行结果
	 * @throws SQLException SQL
	 */
	public Long getLastId() throws SQLException {
		String sql = "SELECT last_insert_id()" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getLong(1) ;
		}
		return 0L ;
	}
	/**
	 * 处理集合的批量转换
	 * @param ids 要处理的集合
	 * @return 转为要处理的in的范围定义
	 */
	public String handleBatchLong(Set<Long> ids) {
		StringBuffer buf = new StringBuffer() ;
		Iterator<Long> iter = ids.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()) ;
		return buf.toString() ;
	}
}
