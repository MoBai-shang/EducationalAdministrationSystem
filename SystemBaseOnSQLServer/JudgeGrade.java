import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class JudgeGrade extends TableJPanel{
	private static String s[]= {"","学生编号","年级学期","课程名称","授课教师","课程学分","课程分数","通过否"};
	public static String mysql="select * from grade where teacher='"+LandingJPanel.name+"'";
	private JButton btn;
	public JudgeGrade() {
		super(s,mysql);
		// TODO Auto-generated constructor stub
		button.setText("修改成绩");
		btn=new JButton("发布成绩");
		btn.setFont(new Font("楷体",Font.PLAIN,30));
		btn.addActionListener(this);
		this.add(btn);
		if(WelcomeJPanel.identity.equals("student"))
			btn.setVisible(false);
	}

	public void deal(ActionEvent e, int[] rows) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			for(int i=0;i<rows.length;i++)
			{
				String idString=(String) jTable.getValueAt(rows[i], 1);
				String courseS=(String) jTable.getValueAt(rows[i], 3);
				String string=JOptionPane.showInputDialog(this,"将学生"+idString+"的课程"+courseS+"成绩更改为");
				if(string!=null)
				{
					try {
						int mgrade=Integer.parseInt(string);
						String ispass;
						if(mgrade>=60)
							ispass="通过";
						else 
							ispass="未通过";
						String sqlString="update grade set gradescore='"+mgrade+"',passed='"+ispass+"' where ID='"+idString+"' and course='"+courseS+"'";
						ResultSet rs=DataBaseConnect.SQL(sqlString);
						JOptionPane.showMessageDialog(this, "将学生"+idString+"的课程"+courseS+"成绩更改为"+mgrade+"成功！");
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, "输入数据不合法");
					}
				}
			}
		}
		else if(e.getSource()==btn)
		{
			new InputGradeJPanel(this);
		}
	}

}
