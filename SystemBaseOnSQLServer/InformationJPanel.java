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
		String labelString[]= {"ѧ��","����","����","�Ա�","ѧԺ","רҵ","�༶","�绰"};
		getInformation();
		label=new JLabel[8];
		tPanel.setLayout(new GridLayout(8,1));
		Font font =new Font("����",Font.PLAIN,20);
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
		button1=new JButton("�޸�");
		button2=new JButton("�޸�");
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
			JOptionPane.showMessageDialog(null,"��Ϣ��ȡʧ�ܣ�");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String string=null;
		
		if(e.getSource()==button1)
		{
			string=JOptionPane.showInputDialog(this, "�����µ����룺");
			if(string!=null)
			{
				informationString[1]=string;
				label[1].setText("���룺"+string);
				String sqlString="update "+WelcomeJPanel.identity+" set password='"+string+"' where ID='"+LandingJPanel.name+"'";
				ResultSet rs=DataBaseConnect.SQL(sqlString);
				JOptionPane.showMessageDialog(this, "�����޸ĳɹ���");
			}
		}
		else if(e.getSource()==button2){
			string=JOptionPane.showInputDialog(this, "�����µ���ϵ��ʽ��");
			if(string!=null)
			{
				informationString[7]=string;
				label[7].setText("�绰��"+string);
				try {
					String sqlString="update "+WelcomeJPanel.identity+" set photo='"+string+"' where ID='"+LandingJPanel.name+"'";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this, "��ϵ��ʽ�޸ĳɹ���");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
}
