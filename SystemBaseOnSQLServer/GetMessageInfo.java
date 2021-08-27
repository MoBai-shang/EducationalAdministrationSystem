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

public class GetMessageInfo extends JFrame implements ActionListener{
	public JLabel[] labels;
	public JTextField textField[];
	public JButton button,btn;
	private TableJPanel obj;
	private String tablename;
	public GetMessageInfo(TableJPanel obj,String table) {
		// TODO Auto-generated constructor stub
		super("注册信息登记");
		this.setLocationRelativeTo(null);
		this.setLocation(550, 130);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(9,1));
		this.setSize(350,450);
		this.tablename=table;
		this.obj=obj;
		labels=new JLabel[8];
		textField=new JTextField[8];
		Font font=new Font("楷体",Font.PLAIN,20);
		String s[]= {"编号","密码","姓名","性别","学院","专业","班级","电话"};
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
				String ss[]=new String[9];
				boolean istextvalid=true;
				for(int k=0;k<8;k++)
				{
					ss[k]=textField[k].getText();
					if(ss[k].length()==0)
						istextvalid=false;
				}
				
				
				if(istextvalid)
				{
					ResultSet rs=DataBaseConnect.SQL("select * from "+tablename+" where ID='"+ss[0]+"'");
					if(rs!=null&&rs.next())
						JOptionPane.showMessageDialog(this, "用户已存在！");
		            else
		            	{
			            	String sqlString="insert into "+tablename+"(ID,password,name,sex,college,profession,class,photo) values('"+ss[0]+"','"+ss[1]+"','"+ss[2]+"','"+ss[3]+"','"+ss[4]+"','"+ss[5]+"','"+ss[6]+"','"+ss[7]+"')";
							rs=DataBaseConnect.SQL(sqlString);
							JOptionPane.showMessageDialog(this, "成员注册成功！");
							this.setVisible(false);
							if(tablename.equals("teacher"))
								obj.getScheduCard(ManageTeacherJPanel.mysql);
							else 
								obj.getScheduCard(ManageStudentJPanel.mysql);
		            	}
		            	rs.close();
					
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
