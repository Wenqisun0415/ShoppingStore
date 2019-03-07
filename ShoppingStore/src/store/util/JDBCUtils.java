package store.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl=new ThreadLocal<>();
	
	/**
	 * get Connection from connection pool
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = tl.get();
		if(conn==null){
			conn=ds.getConnection();
			//和当前线程绑定
			tl.set(conn);
		}
		return conn;
	}

	// 获取数据源
	public static DataSource getDataSource() {
		return ds;
	}

	// 释放资源
	public static void closeResource( Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);
	}
	
	// 释放资源
	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeResource(st, rs);
		closeConn(conn);
	}

	// close connection
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//和线程解绑
				tl.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	// close statement 
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st = null;
		}
	}

	// close result set
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
	
	
	// start transaction
	public static void startTransaction() throws SQLException{
		getConnection().setAutoCommit(false);
	}
	
	/**
	 * 事务提交且释放连接
	 */
	public static void commitAndClose(){
		Connection conn = null;
		try {
			conn=getConnection();
			//事务提交
			conn.commit();
			//关闭资源
			conn.close();
			//解除版定
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 事务回滚且释放资源
	 */
	public static void rollbackAndClose(){
		Connection conn = null;
		try {
			conn=getConnection();
			//事务回滚
			conn.rollback();
			//关闭资源
			conn.close();
			//解除版定
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
