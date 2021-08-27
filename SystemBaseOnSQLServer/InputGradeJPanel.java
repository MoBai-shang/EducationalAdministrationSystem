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

public class InputGradeJPanel extends JFrame implements ActionListener{
	public JLabel[] labels;
	public JTextField textField[];
	public JButton button,btn;
	private JudgeGrade obj;
	public InputGradeJPanel(JudgeGrade obj) {
		// TODO Auto-generated constructor stub
		super("ѧ����Ϣ¼��");
		this.setLocationRelativeTo(null);
		this.setLocation(550, 130);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(8,1));
		this.setSize(350,450);
		this.obj=obj;
		labels=new JLabel[7];
		textField=new JTextField[7];
		Font font=new Font("����",Font.PLAIN,20);
		String s[]= {"�ڿν�ʦ","ѧ�����","�꼶ѧ��","�γ�����","�γ�ѧ��","�γ̷���","ͨ����"};
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
				String ss[]=new String[7];
				boolean istextvalid=true;
				for(int k=0;k<7;k++)
				{
					ss[k]=textField[k].getText();
					if(ss[k].length()==0)
						istextvalid=false;
				}
				
				
				if(istextvalid)
				{
					String sqlString="insert into grade(ID,schoolyear,course,teacher,credit,gradescore,passed) values('"+ss[1]+"','"+ss[2]+"','"+ss[3]+"','"+ss[0]+"','"+ss[4]+"','"+ss[5]+"','"+ss[6]+"')";
					ResultSet rs=DataBaseConnect.SQL(sqlString);
					JOptionPane.showMessageDialog(this, "�γ̳ɼ��Ǽǳɹ���");
					this.setVisible(false);
					obj.getScheduCard(JudgeGrade.mysql);
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
