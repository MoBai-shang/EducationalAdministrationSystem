import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DataBaseConnect {
	public static ResultSet SQL(String sql)
	{
		Connection con = null;
        String DbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String URL = "jdbc:sqlserver://192.168.1.1:1433;databaseName=mobai";
        ResultSet rs=null;
        try 
        {
        	Class.forName(DbDriver);
            con = DriverManager.getConnection(URL,"loginName","password");
            if (con != null) 
            {
            	Statement st = con.createStatement(); 
            	boolean result1=st.execute(sql);
            	if(result1)
            	{
            		rs=st.getResultSet();
            	}
            }
        }
        catch(Exception ex)
        {
        	JOptionPane.showMessageDialog(null,"系统繁忙，请稍后重试");
        }
        return rs;
	}
}
