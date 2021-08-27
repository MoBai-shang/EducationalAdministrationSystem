import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FaceJPanel extends JFrame
{
	public JTextField text;
	public JPasswordField password;
	public JButton[] button;
	public JLabel label[];
	public JPanel pane,p[];
	public Font font;
	public FaceJPanel(String title)
	{
		super(title);
		this.setSize(480,250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pane=new JPanel();
		font=new Font("¿¬Ìå",Font.PLAIN,30);
		pane.setFont(font);
		this.add(pane);
		this.label=new JLabel[2];
		this.button=new JButton[2];
		pane.setLayout(new GridLayout(4,1));
		p=new JPanel[3];
		String ss[]= {"   ÓÃ»§","   ÃÜÂë"};
		String bs[]= {"×¢²á","µÇÂ½"};
		p[2]=new JPanel();
		for(int i=0;i<2;i++)
		{
			this.label[i]=new JLabel(ss[i]);
			this.button[i]=new JButton(bs[i]); 
			p[i]=new JPanel();
			this.label[i].setFont(font);
			this.button[i].setFont(font);
			pane.add(p[i]);
			p[i].add(this.label[i]);
			p[2].add(button[i]);
		}
		
		this.text=new JTextField(20);
		this.password=new JPasswordField(20);
		this.text.setFont(font);
		this.password.setFont(font);
		this.password.setEchoChar('*');
		p[0].add(this.text);
		p[1].add(this.password);
		pane.add(p[2]);
		this.setVisible(true);
	}
}
