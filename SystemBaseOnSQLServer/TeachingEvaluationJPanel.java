import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class TeachingEvaluationJPanel extends TableJPanel{
	private static String[] s= {"","��ʦ����","�γ�","����"};
	public static String mysql="select * from evalu";
	public TeachingEvaluationJPanel() {
		// TODO Auto-generated constructor stub
		super(s,mysql);
		button.setText("ɾ��");
	}
	public void deal(ActionEvent e, int[] rows) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			for(int i=0;i<rows.length;i++)
			{
				String teach=(String) jTable.getValueAt(rows[i], 1);
				String course=(String) jTable.getValueAt(rows[i], 2);
				try {
					String sqlString="delete from evalu where teacher='"+teach+"' and course='"+course+"'";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this, "�γ�"+course+"����ɾ���ɹ���");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			getScheduCard(TeachingEvaluationJPanel.mysql);
		}
	}
}
