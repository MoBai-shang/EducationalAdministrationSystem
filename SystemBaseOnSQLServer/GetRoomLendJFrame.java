import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GetRoomLendJFrame extends JFrame implements ActionListener{
	public JLabel[] labels;
	public JTextField textField[];
	public JButton button,btn;
	private String roomid;
	private RoomJPanel obj;
	public GetRoomLendJFrame(String roomid,RoomJPanel obj) {
		// TODO Auto-generated constructor stub
		super("借用信息登记");
		this.setLocationRelativeTo(null);
		this.setLocation(550, 130);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(7,1));
		this.setSize(350,450);
		this.obj=obj;
		this.roomid=roomid;
		labels=new JLabel[6];
		textField=new JTextField[6];
		Font font=new Font("楷体",Font.PLAIN,20);
		String s[]= {"教室编号","借用时间","归还时间","借者编号","联系方式","借用原因"};
		for(int i=0;i<labels.length;i++)
		{
			labels[i]=new JLabel(s[i]);
			labels[i].setFont(font);
			textField[i]=new JTextField(18);
			textField[i].setFont(font);
			JPanel temoJPanel=new JPanel();
			temoJPanel.add(labels[i]);
			temoJPanel.add(textField[i]);
			this.getContentPane().add(temoJPanel);
		}
		textField[0].setText(roomid);
		textField[0].setEditable(false);
		textField[3].setText(LandingJPanel.name);
		textField[3].setEditable(false);
		JPanel teJPanel=new JPanel();
		this.getContentPane().add(teJPanel);
		teJPanel.add(button=new JButton("提交"));
		teJPanel.add(btn=new JButton("返回"));
		button.setFont(font);
		btn.setFont(font);
		button.addActionListener(this);
		btn.addActionListener(this);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button)
		{
			try 
			{
				String time1=textField[1].getText();
				String time2=textField[2].getText();
				String whoString=textField[3].getText();
				String photo=textField[4].getText();
				String using=textField[5].getText();
				if(time1.length()>0&&time2.length()>0&&whoString.length()>0&&photo.length()>0&&using.length()>0)
				{
					String sqlString="update room set state='借用',start='"+time1+"',endtime='"+time2+"',who='"+LandingJPanel.name+"',photo='"+photo+"',using='"+using+"' where roomid='"+roomid+"'";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this, "教室"+roomid+"借用成功，请及时归还！");
					this.setVisible(false);
					obj.getScheduCard(RoomJPanel.mysql);
				}
				else {
					JOptionPane.showMessageDialog(this,"所有信息不能为空！");
				}
			} 
			catch (Exception e2) {
				// TODO: handle exception
			}
		}
		else if(e.getSource()==btn)
		{
			this.setVisible(false);
		}
	}
}
