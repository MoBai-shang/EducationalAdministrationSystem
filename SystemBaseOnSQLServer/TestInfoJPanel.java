import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TestInfoJPanel extends TableJPanel
{
	private static String s[]= {"","ѧ��","�γ�","��ʦ","ѧ��","�ص�","����","ʱ��","�ο��༶"};
	public static String mysql="select * from testinfo";
	private JButton btn;
	public TestInfoJPanel() {
		// TODO Auto-generated constructor stub
		super(s, mysql);
		button.setText("ȡ������");
		btn=new JButton("��ӿ���");
		this.add(btn);
		btn.setFont(new Font("����",Font.PLAIN,30));
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
						JOptionPane.showMessageDialog(this, "������Ϣɾ���ɹ���");
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, "�������ݲ��Ϸ�");
					}
				}
				else
					JOptionPane.showMessageDialog(this,"��û��Ȩ��ɾ���Ǳ��˷����Ŀ��ԣ�");
					
				
			}
		}
		else if(e.getSource()==btn)
		{
			new GetTestInfo(this);
		}
	}
}
