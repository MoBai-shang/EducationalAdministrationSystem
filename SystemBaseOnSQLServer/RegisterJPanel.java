import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class RegisterJPanel extends FaceJPanel implements ActionListener
{
	public JPasswordField passwordagain;
	public FaceJPanel obj=null;
	public JLabel labelpassword;
	public RegisterJPanel() {
		super("教务系统注册");
		// TODO Auto-generated constructor stub
		this.passwordagain=new JPasswordField(20);
		this.passwordagain.setFont(font);
		this.passwordagain.setEchoChar('*');
		labelpassword=new JLabel("确认密码");
		labelpassword.setFont(font);
		JPanel temPanel=new JPanel();
		temPanel.add(labelpassword);
		temPanel.add(passwordagain);
		pane.remove(p[2]);
		pane.add(temPanel);
		pane.add(p[2]);
		button[1].setText("返回");
		this.button[0].addActionListener(this);
		this.button[1].addActionListener(this);
	}
	public RegisterJPanel(FaceJPanel obj)
	{
		this();
		this.obj=obj;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button[1])
		{
			this.setVisible(false);
			if(obj!=null)
				obj.setVisible(true);
		}
		else if(e.getSource()==button[0])
		{
			String s1=text.getText();
			String s2=new String(this.password.getPassword());
			String s3=new String(this.passwordagain.getPassword());
			if(s1.length()>0&&s2.length()>0&&s3.length()>0)
			{
				if(s1.indexOf(' ')==-1&&s3.indexOf(' ')==-1&&s2.indexOf(' ')==-1)
					
				{
					if(s2.equals(s3))
					{
						try 
						{
							ResultSet rs=DataBaseConnect.SQL("select * from "+WelcomeJPanel.identity+" where ID='"+s1+"'");
							if(rs!=null)
							{
								String tem="";
				            	while(rs.next())
				            		tem=rs.getString(1);
				            	tem=tem.trim();
				            	if(tem.length()!=0)
				            		JOptionPane.showMessageDialog(this, "用户已存在！");
				            	else
				            	{
				            		DataBaseConnect.SQL("insert into "+WelcomeJPanel.identity+"(ID,password,name,sex,college,profession,class,photo) values('"+s1+"','"+s2+"','游客','不详','游客','游客','游客','游客')");
						            JOptionPane.showMessageDialog(this, "注册成功！");
				            	}
				            	rs.close();
							}
						} catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null,"系统繁忙，请稍后重试");
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(this, "密码前后不一致!");
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(this, "不能输入空格等非法字符！");
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "输入信息不能为空!");
			}
		}
	}
}