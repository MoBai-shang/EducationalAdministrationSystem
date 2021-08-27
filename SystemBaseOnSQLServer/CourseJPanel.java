import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class CourseJPanel extends TableJPanel{
	private static String s[]= {"","课程","教师","学分","课余量","教室","开课日期","星期","时间"};
	public static String mysql="select * from course where value>0";
	public JButton btn;
	public CourseJPanel() {
		// TODO Auto-generated constructor stub
		super(s,mysql);
		button.setText("选课");
		btn=new JButton("添课");
		btn.setFont(new Font("楷体",Font.PLAIN,30));
		this.add(btn);
		btn.addActionListener(this);
		if(WelcomeJPanel.identity.equals("student"))
			btn.setVisible(false);
	}
	
	public void deal(ActionEvent e,int[] rows) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			for(int i=0;i<rows.length;i++)
			{
				String course=(String) jTable.getValueAt(rows[i], 1);
				try 
				{
					String sqlString="select * from schedu where ID='"+LandingJPanel.name+"' and course='"+course+"'";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					if(rs==null||!rs.next())
					{
						String teacher=(String)jTable.getValueAt(rows[i], 2);
						String credit=(String) jTable.getValueAt(rows[i], 3);
						String room=(String) jTable.getValueAt(rows[i], 5);
						String begin=(String)jTable.getValueAt(rows[i], 6);
						String date=(String) jTable.getValueAt(rows[i], 7);
						String time=(String) jTable.getValueAt(rows[i], 8);
						try 
						{
							sqlString="insert into schedu(ID,course,credit,teacher,room,[begin],date,time)values('"+LandingJPanel.name+"','"+course+"',"+credit+",'"+teacher+"','"+room+"','"+begin+"','"+date+"','"+time+"')";
							rs=DataBaseConnect.SQL(sqlString);
							JOptionPane.showMessageDialog(this, "课程"+course+"选课成功！");
							sqlString="update course set value=value-1 where course='"+course+"'";
							rs=DataBaseConnect.SQL(sqlString);
						} 
						catch (Exception e2) {
							// TODO: handle exception
						}
					}
					else {
						JOptionPane.showMessageDialog(this, "你的课表中已经存在课程"+course+"!");
					}
				} 
				catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		else if(e.getSource()==btn)
		{
			new GetCourseInfoJpanel(this);
		}
	}
}
