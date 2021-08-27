import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
public class WelcomeJPanel extends JFrame implements ActionListener
{
	public JRadioButton radiobs[];
	public static String identity="admin";
	public WelcomeJPanel() {
		// TODO Auto-generated constructor stub
		super("教务系统");
		this.setSize(480,250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel pane=new JPanel();
		pane.setLayout(null);
		this.getContentPane().add(pane);
		ButtonGroup bgroup=new ButtonGroup();
		String type[]= {"教师","学生","管理员"};
		this.radiobs=new JRadioButton[type.length];
		Font font=new Font("楷体",Font.PLAIN,30);
		int width=140,height=50;
		int x[]= {30,170,310};
		int y=80;
		for(int i=0;i<this.radiobs.length;i++)
		{
			pane.add(this.radiobs[i]=new JRadioButton(type[i]));
			this.radiobs[i].setFont(font);
			bgroup.add(this.radiobs[i]);
			radiobs[i].setBounds(x[i], y, width, height);
			radiobs[i].addActionListener(this);
		}
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(radiobs[2].isSelected())
			identity="admin";
		else
			identity=radiobs[0].isSelected()?"teacher":"student";
		this.setVisible(false);
		new LandingJPanel();
	}
	public static void main(String arg[])
	{
		new WelcomeJPanel();
	}
}
