import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InformationJPanel extends JPanel implements ActionListener{
	String informationString[];
	public JButton button1;
	public JButton button2;
	private JLabel label[];
	public InformationJPanel() {
		// TODO Auto-generated constructor stub
		JPanel tPanel=new JPanel();
		this.add(tPanel);
		this.setLayout(null);
		tPanel.setBounds(400, 100, 300, 360);
		informationString=new String[8];
		String labelString[]= {"学号","密码","姓名","性别","学院","专业","班级","电话"};
		getInformation();
		label=new JLabel[8];
		tPanel.setLayout(new GridLayout(8,1));
		Font font =new Font("楷体",Font.PLAIN,20);
		JPanel temPanel[]=new JPanel[label.length];
		for(int i=0;i<label.length;i++)
		{
			temPanel[i]=new JPanel();
			temPanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			label[i]=new JLabel(labelString[i]+":"+informationString[i]);
			label[i].setFont(font);
			temPanel[i].add(label[i]);
			tPanel.add(temPanel[i]);
		}
		button1=new JButton("修改");
		button2=new JButton("修改");
		button1.addActionListener(this);
		button2.addActionListener(this);
		temPanel[1].add(button1);
		temPanel[7].add(button2);
	}
	private void getInformation()
	{
		String s1=LandingJPanel.name;
		try 
		{
			ResultSet rs=DataBaseConnect.SQL("select * from "+WelcomeJPanel.identity+" where ID='"+s1+"'");
			if(rs!=null)
			{
				String tem="";
				int columnCount=rs.getMetaData().getColumnCount();
            	while(rs.next())
            	{
            		for(int i=1;i<=columnCount;i++)
            		{
            			informationString[i-1]=rs.getString(i).trim();
            		}
            	}
            	rs.close();
            }
		} 
		catch (Exception e2) 
		{
			JOptionPane.showMessageDialog(null,"信息获取失败！");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String string=null;
		
		if(e.getSource()==button1)
		{
			string=JOptionPane.showInputDialog(this, "输入新的密码：");
			if(string!=null)
			{
				informationString[1]=string;
				label[1].setText("密码："+string);
				String sqlString="update "+WelcomeJPanel.identity+" set password='"+string+"' where ID='"+LandingJPanel.name+"'";
				ResultSet rs=DataBaseConnect.SQL(sqlString);
				JOptionPane.showMessageDialog(this, "密码修改成功！");
			}
		}
		else if(e.getSource()==button2){
			string=JOptionPane.showInputDialog(this, "输入新的联系方式：");
			if(string!=null)
			{
				informationString[7]=string;
				label[7].setText("电话："+string);
				try {
					String sqlString="update "+WelcomeJPanel.identity+" set photo='"+string+"' where ID='"+LandingJPanel.name+"'";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this, "联系方式修改成功！");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
}
