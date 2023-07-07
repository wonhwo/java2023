import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
public class DBconnect {
	
	public static Connection makecon() {
		
		String url = "jdbc:mysql://localhost:3306/app?serverTimezone=Asia/Seoul";
		String user="root";
		String pass="1234";
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
			
		}catch (Exception e) {
			
			e.printStackTrace();return con;
			
		}
		return con;
		
	}

}
