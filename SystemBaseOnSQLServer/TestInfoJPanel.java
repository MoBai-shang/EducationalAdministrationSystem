import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TestInfoJPanel extends TableJPanel
{
	private static String s[]= {"","学期","课程","教师","学分","地点","日期","时间","参考班级"};
	public static String mysql="select * from testinfo";
	private JButton btn;
	public TestInfoJPanel() {
		// TODO Auto-generated constructor stub
		super(s, mysql);
		button.setText("取消考试");
		btn=new JButton("添加考试");
		this.add(btn);
		btn.setFont(new Font("楷体",Font.PLAIN,30));
		btn.addActionListener(this);
		if(WelcomeJPanel.identity.equals("student"))
		{
			button.setVisible(false);
			btn.setVisible(false);
		}
	}
	public void deal(ActionEvent e, int[] rows) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			for(int i=0;i<rows.length;i++)
			{
				String tea=(String) jTable.getValueAt(rows[i], 3);
				String courseS=(String) jTable.getValueAt(rows[i], 2);
				if(LandingJPanel.name.equals(tea))
				{
					try {
						String sqlString="delete from testinfo where teacher='"+tea+"' and course='"+courseS+"'";
						ResultSet rs=DataBaseConnect.SQL(sqlString);
						JOptionPane.showMessageDialog(this, "考试信息删除成功！");
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, "输入数据不合法");
					}
				}
				else
					JOptionPane.showMessageDialog(this,"您没有权限删除非本人发布的考试！");
					
				
			}
		}
		else if(e.getSource()==btn)
		{
			new GetTestInfo(this);
		}
	}
}
