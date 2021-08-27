import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
public class RoomJPanel extends TableJPanel{
	private static String s[]= {"","教室号","状态","借出日期","归还日期","借用人","联系方式","借用原因"};
	public static String mysql="select * from room";
	private JButton btn;
	public RoomJPanel() {
		// TODO Auto-generated constructor stub
		super(s, mysql);
		button.setText("借用");
		btn=new JButton("归还");
		btn.setFont(new Font("楷体",Font.PLAIN,30));
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
				if(mystate.equals("空闲"))
				{
					new GetRoomLendJFrame(roomid,this);

				}
				else {
					JOptionPane.showMessageDialog(this, "教室"+roomid+"已经被借用，目前尚不可借！");
				}
								
			}
		}
		else if(e.getSource()==btn)
		{
			for(int i=0;i<rows.length;i++)
			{
				String mystate=(String) jTable.getValueAt(rows[i], 2);
				String roomid=(String) jTable.getValueAt(rows[i], 1);
				if(!mystate.equals("空闲"))
				{
					String slendid=(String) jTable.getValueAt(rows[i], 5);
					if(LandingJPanel.name.equals(slendid))
					{
						try 
						{
							String sqlString="update room set state='空闲',start=' ',endtime=' ',who=' ',photo=' ',using=' ' where roomid='"+roomid+"'";
							ResultSet rs=DataBaseConnect.SQL(sqlString);
							JOptionPane.showMessageDialog(this, "教室"+roomid+"归还成功！");
						} 
						catch (Exception e2) {
							// TODO: handle exception
						}

					}
					else {
						JOptionPane.showMessageDialog(this, "你没有权限归还非本人所借教室！");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "教室"+roomid+"尚未被借用，目前尚不可还！");
				}
								
			}
		}

	}
}
