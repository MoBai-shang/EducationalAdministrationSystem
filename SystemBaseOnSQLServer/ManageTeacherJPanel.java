import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ManageTeacherJPanel extends TableJPanel
{
	private static String s[]= {"","编号","密码","姓名","性别","学院","专业","班级","电话"};
	public static String mysql="select * from teacher";
	private JButton btn;
	public ManageTeacherJPanel() {
		// TODO Auto-generated constructor stub
		super(s, mysql);
		button.setText("注销");
		btn=new JButton("添加");
		this.add(btn);
		btn.setFont(new Font("楷体",Font.PLAIN,30));
		btn.addActionListener(this);
	}
	
	public void deal(ActionEvent e, int[] rows) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			for(int i=0;i<rows.length;i++)
			{
				String id=(String) jTable.getValueAt(rows[i], 1);
				
					try {
						String sqlString="delete from teacher where ID='"+id+"'";
						ResultSet rs=DataBaseConnect.SQL(sqlString);
						JOptionPane.showMessageDialog(this, "注销成功！");
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, "系统繁忙！");
					}
			}
			
		}
		else if(e.getSource()==btn)
		{
			new GetMessageInfo(this,"teacher");
		}
	}
}
