import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ScheduJPanel extends TableJPanel{
	private static String[] s= {"选中","ID","课程","学分","教师","授课地点","开课日期","星期","时间"};
	public static String mysql="select * from schedu where ID='"+LandingJPanel.name+"'";
	public JButton btn;
	public JTextField jTextField;
	public ScheduJPanel() {
		// TODO Auto-generated constructor stub
		super(s,mysql);
		button.setText("退课");
		btn=new JButton("评教");
		btn.setFont(new Font("楷体",Font.PLAIN,30));
		jTextField=new JTextField(40);
		jTextField.setFont(new Font("楷体",Font.PLAIN,25));
		this.add(jTextField);
		this.add(btn);
		btn.addActionListener(this);
	}
	
	public void deal(ActionEvent e, int[] rows) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			for(int i=0;i<rows.length;i++)
			{
				String course=(String) jTable.getValueAt(rows[i], 2);
				try {
					String sqlString="delete from schedu where ID='"+LandingJPanel.name+"' and course='"+course+"'";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this, "课程"+course+"退课成功！");
					sqlString="update course set value=value+1 where course='"+course+"'";
					rs=DataBaseConnect.SQL(sqlString);
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		else if(e.getSource()==btn)
		{
			String eva=jTextField.getText().trim();
			if(eva.length()>5)
			{
				for(int i=0;i<rows.length;i++)
				{
					String teach=(String) jTable.getValueAt(rows[i], 1);
					String course=(String) jTable.getValueAt(rows[i], 2);
					try {
						String sqlString="insert into evalu(teacher,course,evaluation) values('"+teach+"','"+course+"','"+eva+"')";
						ResultSet rs=DataBaseConnect.SQL(sqlString);
						JOptionPane.showMessageDialog(this, "课程"+course+"评教成功！");
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this, "评教内容无效或长度不够（至少6个字）！");
		}
	}
}
