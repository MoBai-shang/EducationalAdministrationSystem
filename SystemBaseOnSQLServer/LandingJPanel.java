import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LandingJPanel extends FaceJPanel implements ActionListener{
	public static String name;
	public LandingJPanel() {
		super("����ϵͳ��½");
		// TODO Auto-generated constructor stub
		this.button[0].addActionListener(this);
		this.button[1].addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button[0])
		{
			this.setVisible(false);
			new RegisterJPanel(this);
		}
		else if(e.getSource()==button[1])
		{
			String s1=text.getText();
			String s2=new String(this.password.getPassword());
			if(s1.length()>0&&s2.length()>0)
			{
				if(s1.indexOf(' ')==-1&&s2.indexOf(' ')==-1)
				{
					try 
					{
						ResultSet rs=DataBaseConnect.SQL("select password from "+WelcomeJPanel.identity+" where ID='"+s1+"'");
						
						if(rs!=null)
						{
							String tem="";
			            	while(rs.next())
			            		tem=rs.getString(1);
			            	tem=tem.trim();
			            	if(tem.length()!=0)
			            	{
				            		if(tem.equals(s2))
				            	{
				            		this.setVisible(false);
				            		name=s1;
				            		new SystemJPanel();
				            		
				            	}
				            	else
				            	{
				            		JOptionPane.showMessageDialog(this, "�������");
				            	}
			            	}
			            	else
			            		JOptionPane.showMessageDialog(this,"�û�������");
			            	rs.close();
			            }
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null,"ϵͳ��æ�����Ժ�����");
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(this, "��������ո�ȷǷ��ַ���");
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "������Ϣ����Ϊ��!");
			}
		}
	}
}
