import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SelectGrade extends TableJPanel{
	private static String s[]= {"","ѧ�����","�꼶ѧ��","�γ�����","�ڿν�ʦ","�γ�ѧ��","�γ̷���","ͨ����"};
	public static String mysql="select * from grade where ID='"+LandingJPanel.name+"'";
	public SelectGrade() {
		super(s,mysql);
		// TODO Auto-generated constructor stub
		button.setText("���븴��");
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
					JOptionPane.showMessageDialog(this,"�γ�"+courseS+"�ɼ�"+mgrade+"���������ύ�ɹ���");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			else {
				JOptionPane.showMessageDialog(this,"�γ�"+courseS+"�ɼ�"+mgrade+"���������ύ�ɹ���");
			}
		}
	}
}
