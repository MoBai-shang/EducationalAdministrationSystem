import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ScheduJPanel extends TableJPanel{
	private static String[] s= {"ѡ��","ID","�γ�","ѧ��","��ʦ","�ڿεص�","��������","����","ʱ��"};
	public static String mysql="select * from schedu where ID='"+LandingJPanel.name+"'";
	public JButton btn;
	public JTextField jTextField;
	public ScheduJPanel() {
		// TODO Auto-generated constructor stub
		super(s,mysql);
		button.setText("�˿�");
		btn=new JButton("����");
		btn.setFont(new Font("����",Font.PLAIN,30));
		jTextField=new JTextField(40);
		jTextField.setFont(new Font("����",Font.PLAIN,25));
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
					JOptionPane.showMessageDialog(this, "�γ�"+course+"�˿γɹ���");
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
						JOptionPane.showMessageDialog(this, "�γ�"+course+"���̳ɹ���");
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this, "����������Ч�򳤶Ȳ���������6���֣���");
		}
	}
}
