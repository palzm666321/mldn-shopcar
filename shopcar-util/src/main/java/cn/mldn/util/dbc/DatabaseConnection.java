package cn.mldn.util.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/mldn";
	private static final String USER = "root";
	private static final String PASSWORD = "mysqladmin";
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	public static Connection getConnection() {
		Connection conn = threadLocal.get();
		if (conn == null) {
			conn = buildConnection() ;
			threadLocal.set(conn);
		}
		return conn ;
	}
	public static void close() {
		Connection conn = threadLocal.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			threadLocal.remove(); 
		}
	}

	private static Connection buildConnection() {
		try {
			Class.forName(DBDRIVER);
			return DriverManager.getConnection(DBURL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
}
