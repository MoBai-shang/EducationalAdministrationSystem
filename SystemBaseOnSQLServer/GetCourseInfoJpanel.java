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

public class GetCourseInfoJpanel extends JFrame implements ActionListener{
	public JLabel[] labels;
	public JTextField textField[];
	public JButton button,btn;
	private CourseJPanel obj;
	public GetCourseInfoJpanel(CourseJPanel obj) {
		// TODO Auto-generated constructor stub
		super("�γ���Ϣ����");
		this.setLocationRelativeTo(null);
		this.setLocation(550, 130);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(9,1));
		this.setSize(350,450);
		this.obj=obj;
		labels=new JLabel[8];
		textField=new JTextField[8];
		Font font=new Font("����",Font.PLAIN,20);
		String s[]= {"�ڿν�ʦ","�γ�����","�γ�ѧ��","������","�Ͽεص�","��������","����","ʱ��"};
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
		textField[0].setText(LandingJPanel.name);
		textField[0].setEditable(false);
		JPanel teJPanel=new JPanel();
		this.getContentPane().add(teJPanel);
		teJPanel.add(button=new JButton("�ύ"));
		teJPanel.add(btn=new JButton("����"));
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
				String ss[]=new String[8];
				boolean istextvalid=true;
				for(int k=0;k<8;k++)
				{
					ss[k]=textField[k].getText();
					if(ss[k].length()==0)
						istextvalid=false;
				}
				
				
				if(istextvalid)
				{
					String sqlString="insert into course(course,teacher,credit,value,place,[begin],data,time) values('"+ss[1]+"','"+ss[0]+"','"+ss[2]+"','"+ss[3]+"','"+ss[4]+"','"+ss[5]+"','"+ss[6]+"','"+ss[7]+"')";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this, "�γ���Ϣ�����ɹ���");
					this.setVisible(false);
					obj.getScheduCard(CourseJPanel.mysql);
				}
				else {
					JOptionPane.showMessageDialog(this,"������Ϣ����Ϊ�գ�");
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
