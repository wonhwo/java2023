import java.sql.*;
import java.util.Scanner;

import com.mysql.cj.xdevapi.PreparableStatement;

public class phonManager {
	static Connection con=DBconnect.makecon();
	static PreparedStatement ps=null;

	public static void main(String[] args) {
		if(con!=null) {
			//Delete();
			Select();
			Insert();
			Select();	
		}
		

}
	public static void update() {
		Scanner s=new Scanner(System.in);
		String key=s.nextLine();
		ResultSet rs=null;
		String sql="select * from person where phone=?";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, key);
			rs=ps.executeQuery();
			
		if(rs.next()) {
			String e=s.nextLine();
			int a = s.nextInt();
			sql="update person set email = ?,age=? where phone = ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, e);
			ps.setInt(2, a);
			ps.setString(3, key);
			ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void Delete() {
		ResultSet rs=null;
		Scanner s=new Scanner(System.in);
		String sql="select phone from person where phone=?";
		
		try {
			String a=s.nextLine();
			ps=con.prepareStatement(sql);
			ps.setString(1, a);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				String sql2="delete from person where phone=?";
				ps=con.prepareStatement(sql2);
				ps.setString(1, a);
				ps.executeUpdate();
				
			}
			else {System.out.println("동일한 번호가 없습니다.");}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	public static void Select() {
		ResultSet rs=null;
		String sql="select * from person";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			System.out.println("==================Phone List====================");
			System.out.println("이름"+"\t"+"번호"+"\t"+"이메일"+"\t"+"나이");
			
			while (rs.next()) {
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4));
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
	}
//	public static void Insert() {
//
//		String sql="insert into person values(?,?,?,?)";
//		String name=s.next();
//		String phone=s.next();
//		String email=s.next();
//		int age=s.nextInt();
//		try {
//			ps=con.prepareStatement(sql);
//			ps.setString(1, name);
//			ps.setString(2, phone);
//			ps.setString(3, email);
//			ps.setInt(4, age);
//			ps.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
//		
//	}
	public static void Insert() {
		
		//String sql="insert into person values(?,?,?,?)";
		try {
			String slq = "select * from person where phone=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, in_tf2);
//			ps.setString(1, name);
//			ps.setString(2, phone);
//			ps.setString(3, email);
//			ps.setInt(4, age);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
