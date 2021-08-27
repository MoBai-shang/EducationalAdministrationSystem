import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SystemJPanel extends JFrame implements ActionListener,ChangeListener
{
	private JTabbedPane tab;
	public static Font font;
	private ScheduJPanel scheduJPanel;
	private CourseJPanel courseJPanel;
	private RoomJPanel roomJPanel;
	private TableJPanel grade;
	private TestInfoJPanel testInfoJPanel;
	private ManageTeacherJPanel manageTeacherJPanel;
	private ManageStudentJPanel manageStudentJPanel;
	private TeachingEvaluationJPanel teachingEvaluationJPanel;
	public SystemJPanel() {
		// TODO Auto-generated constructor stub
		super("教务系统");
		this.setSize(1150,650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		font=new Font("楷体",Font.PLAIN,30);
		tab=new JTabbedPane(JTabbedPane.LEFT);
		this.getContentPane().add(tab);
		tab.addChangeListener(this);
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setOneTouchExpandable(true);
		split.add(new JScrollPane(new InformationJPanel()));
		
		split.setDividerLocation(200);
		//split.add(new JSplitPane(new ));
		tab.setFont(font);
		tab.addTab("基本信息", split);
		scheduJPanel=new ScheduJPanel();
		courseJPanel=new CourseJPanel();
		roomJPanel=new RoomJPanel();
		tab.addTab("学期课表", scheduJPanel);
		tab.addTab("选课",courseJPanel);
		tab.addTab("教室资源", roomJPanel);
		if(WelcomeJPanel.identity.equals("student"))
			{
				grade=new SelectGrade();
				tab.addTab("成绩查询", grade);
			}
		else 
			{
				grade=new JudgeGrade();
				tab.addTab("成绩管理", grade);
			}
		tab.addTab("考试信息",testInfoJPanel=new TestInfoJPanel());
		if(WelcomeJPanel.identity.equals("admin"))
		{
			manageTeacherJPanel =new ManageTeacherJPanel();
			manageStudentJPanel=new ManageStudentJPanel();
			teachingEvaluationJPanel=new TeachingEvaluationJPanel();
			tab.addTab("学生管理", manageStudentJPanel);
			tab.addTab("教师管理", manageTeacherJPanel);
			tab.addTab("教学评估", teachingEvaluationJPanel);
		}
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		int i=tab.getSelectedIndex();
		if(i==1)
		{
			scheduJPanel.getScheduCard(ScheduJPanel.mysql);
		}
		else if(i==2)
		{
			courseJPanel.getScheduCard(CourseJPanel.mysql);
		}
		else if(i==3)
		{
			roomJPanel.getScheduCard(RoomJPanel.mysql);
		}
		else if(i==4)
		{
			if(WelcomeJPanel.identity.equals("student"))
				grade.getScheduCard(SelectGrade.mysql);
			else 
				grade.getScheduCard(JudgeGrade.mysql);
		}
		else if(i==5)
		{
			testInfoJPanel.getScheduCard(TestInfoJPanel.mysql);
		}
		else if(i==6)
		{
			manageStudentJPanel.getScheduCard(ManageStudentJPanel.mysql);
		}
		else if (i==7) {
			manageTeacherJPanel.getScheduCard(ManageTeacherJPanel.mysql);
		}
		else if(i==8)
		{
			teachingEvaluationJPanel.getScheduCard(TeachingEvaluationJPanel.mysql);
		}
	}
}
