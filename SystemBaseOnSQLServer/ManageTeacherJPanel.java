import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ManageTeacherJPanel extends TableJPanel
{
	private static String s[]= {"","���","����","����","�Ա�","ѧԺ","רҵ","�༶","�绰"};
	public static String mysql="select * from teacher";
	private JButton btn;
	public ManageTeacherJPanel() {
		// TODO Auto-generated constructor stub
		super(s, mysql);
		button.setText("ע��");
		btn=new JButton("���");
		this.add(btn);
		btn.setFont(new Font("����",Font.PLAIN,30));
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
						JOptionPane.showMessageDialog(this, "ע���ɹ���");
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, "ϵͳ��æ��");
					}
			}
			
		}
		else if(e.getSource()==btn)
		{
			new GetMessageInfo(this,"teacher");
		}
	}
}
