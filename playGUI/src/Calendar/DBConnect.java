package Calendar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	   public Connection makeCon(){
		      // TODO Auto-generated method stub
		      String url = "jdbc:mysql://localhost:3306/app?serverTimezone=Asia/Seoul";
		      String user = "root";
		      String password = "1234";
		      
		      Connection con = null;
		      try {
		         Class.forName("com.mysql.cj.jdbc.Driver");
		         con = DriverManager.getConnection(url, user, password);
		         return con;
		                  
		      }  catch (Exception e) {
		         e.printStackTrace();
		         return con;
		      }
		   }
	   public ResultSet select(String key) {
		   Connection con = makeCon();
		   PreparedStatement ps;
		   ResultSet rs =null;
		   String sql;
		   sql = "select * from dayday where day_num=?";
		   try {
			ps=con.prepareStatement(sql);
			ps.setString(1, key);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return rs;
		}
	   }
	   public ResultSet select() {
		   Connection con = makeCon();
		   PreparedStatement ps;
		   ResultSet rs =null;
		   String sql;
		   sql = "select distinct day_num from dayday";
		   try {
			ps=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return rs;
		}
	   }
	   public void insert(String dd,String memo) {
		   Connection con = makeCon();
		   PreparedStatement ps;
		   String sql;
		   sql = "insert into dayday values(null,?,?)";
		   try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dd);
			ps.setString(2, memo);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	   }
	   public void delete(int key) {
		   Connection con = makeCon();
		   PreparedStatement ps;
		   String sql;
		   sql = "delete from dayday  where id=?";
		   try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   }

}
