import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
public class RoomJPanel extends TableJPanel{
	private static String s[]= {"","���Һ�","״̬","�������","�黹����","������","��ϵ��ʽ","����ԭ��"};
	public static String mysql="select * from room";
	private JButton btn;
	public RoomJPanel() {
		// TODO Auto-generated constructor stub
		super(s, mysql);
		button.setText("����");
		btn=new JButton("�黹");
		btn.setFont(new Font("����",Font.PLAIN,30));
		this.add(btn);
		btn.addActionListener(this);
	}
	
	public void deal(ActionEvent e, int[] rows) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			for(int i=0;i<rows.length;i++)
			{
				String mystate=(String) jTable.getValueAt(rows[i], 2);
				String roomid=(String) jTable.getValueAt(rows[i], 1);
				if(mystate.equals("����"))
				{
					new GetRoomLendJFrame(roomid,this);

				}
				else {
					JOptionPane.showMessageDialog(this, "����"+roomid+"�Ѿ������ã�Ŀǰ�в��ɽ裡");
				}
								
			}
		}
		else if(e.getSource()==btn)
		{
			for(int i=0;i<rows.length;i++)
			{
				String mystate=(String) jTable.getValueAt(rows[i], 2);
				String roomid=(String) jTable.getValueAt(rows[i], 1);
				if(!mystate.equals("����"))
				{
					String slendid=(String) jTable.getValueAt(rows[i], 5);
					if(LandingJPanel.name.equals(slendid))
					{
						try 
						{
							String sqlString="update room set state='����',start=' ',endtime=' ',who=' ',photo=' ',using=' ' where roomid='"+roomid+"'";
							ResultSet rs=DataBaseConnect.SQL(sqlString);
							JOptionPane.showMessageDialog(this, "����"+roomid+"�黹�ɹ���");
						} 
						catch (Exception e2) {
							// TODO: handle exception
						}

					}
					else {
						JOptionPane.showMessageDialog(this, "��û��Ȩ�޹黹�Ǳ���������ң�");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "����"+roomid+"��δ�����ã�Ŀǰ�в��ɻ���");
				}
								
			}
		}

	}
}
