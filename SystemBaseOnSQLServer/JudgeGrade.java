import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class JudgeGrade extends TableJPanel{
	private static String s[]= {"","ѧ�����","�꼶ѧ��","�γ�����","�ڿν�ʦ","�γ�ѧ��","�γ̷���","ͨ����"};
	public static String mysql="select * from grade where teacher='"+LandingJPanel.name+"'";
	private JButton btn;
	public JudgeGrade() {
		super(s,mysql);
		// TODO Auto-generated constructor stub
		button.setText("�޸ĳɼ�");
		btn=new JButton("�����ɼ�");
		btn.setFont(new Font("����",Font.PLAIN,30));
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
				String string=JOptionPane.showInputDialog(this,"��ѧ��"+idString+"�Ŀγ�"+courseS+"�ɼ�����Ϊ");
				if(string!=null)
				{
					try {
						int mgrade=Integer.parseInt(string);
						String ispass;
						if(mgrade>=60)
							ispass="ͨ��";
						else 
							ispass="δͨ��";
						String sqlString="update grade set gradescore='"+mgrade+"',passed='"+ispass+"' where ID='"+idString+"' and course='"+courseS+"'";
						ResultSet rs=DataBaseConnect.SQL(sqlString);
						JOptionPane.showMessageDialog(this, "��ѧ��"+idString+"�Ŀγ�"+courseS+"�ɼ�����Ϊ"+mgrade+"�ɹ���");
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(this, "�������ݲ��Ϸ�");
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
