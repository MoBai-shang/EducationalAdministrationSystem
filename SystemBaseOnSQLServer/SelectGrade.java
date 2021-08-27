import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SelectGrade extends TableJPanel{
	private static String s[]= {"","学生编号","年级学期","课程名称","授课教师","课程学分","课程分数","通过否"};
	public static String mysql="select * from grade where ID='"+LandingJPanel.name+"'";
	public SelectGrade() {
		super(s,mysql);
		// TODO Auto-generated constructor stub
		button.setText("申请复审");
	}

	public void deal(ActionEvent e, int[] rows) {
		// TODO Auto-generated method stub
		for(int i=0;i<rows.length;i++)
		{
			String idString=(String) jTable.getValueAt(rows[i], 1);
			String courseS=(String) jTable.getValueAt(rows[i], 3);
			String mgrade=(String) jTable.getValueAt(rows[i], 6);
			if(mgrade.indexOf('?')==-1)
			{
				try {
					String sqlString="update grade set gradescore='"+mgrade+"?' where ID='"+idString+"' and course='"+courseS+"'";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this,"课程"+courseS+"成绩"+mgrade+"复审申请提交成功！");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			else {
				JOptionPane.showMessageDialog(this,"课程"+courseS+"成绩"+mgrade+"复审申请提交成功！");
			}
		}
	}
}
