import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public abstract class TableJPanel extends JPanel implements ActionListener
{
	private DefaultTableModel tableModel;
	public JTable jTable;
	public String sql;
	public JButton button;
	public String titles[]= {""};
	public TableJPanel(String[] titles,String sql) {
		
		this.tableModel=new DefaultTableModel(titles,0);
		jTable=new JTable(this.tableModel);
		this.add(new JScrollPane(jTable));
		jTable.setPreferredScrollableViewportSize(new Dimension(925, 500));
		jTable.setFont(new Font("����",Font.PLAIN,20));
		jTable.getTableHeader().setFont(new Font("����",Font.PLAIN,25));
		jTable.setRowHeight(30);
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        jTable.setDefaultRenderer(Object.class,r);
        
		//jTable.setGridColor(Color.blue);
		JCheckBox box = new JCheckBox();
		/*getColumn()���������Ӧ�ĵڼ�����Ӹ�ѡ��*/
		jTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
			
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus,int row, int column) {
				
				
				/*���õ���ѡ��ѡ�����б���Ⱦ*/
				box.setSelected(isSelected);
				
				/*���ø�ѡ���ڵ�Ԫ���о���*/
				box.setHorizontalAlignment((int) 0.5f);
				return box;
			}
		});
		this.titles=titles;
		this.sql=sql;
		button=new JButton("");
		button.setFont(new Font("����",Font.PLAIN,30));
		this.add(button,"South");
		getScheduCard(sql);
		FitTableColumns(jTable);
		button.addActionListener(this);
	}
	public void getScheduCard(String sql)
	{
		tableModel.setRowCount(0);
		try 
		{
			ResultSet rs=DataBaseConnect.SQL(sql);
			if(rs!=null)
			{
				int j=0;
            	while(rs.next())
            	{
            		j++;
            		this.tableModel.setRowCount(j);
            		for(int i=1;i<titles.length;i++)
            		{
            			try {
            				this.tableModel.setValueAt(rs.getString(i).trim(), j-1, i);
						} catch (Exception e) {
							// TODO: handle exception
						}
            			
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
	public void FitTableColumns(JTable myTable)
	{
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements())
		{
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(
					column.getIdentifier());
			int width = (int) myTable
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(myTable,
							column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++)
			{
				int preferedWidth = (int) myTable
						.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable,
								myTable.getValueAt(row, col), false, false,
								row, col).getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // ���к���Ҫ
			column.setWidth(width + myTable.getIntercellSpacing().width + 25);
		}
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int rows[]=jTable.getSelectedRows();
		if(rows.length>0)
		{
			int option=JOptionPane.showConfirmDialog(this,"��ȷ��ִ�д˲�����");
			if(option==0)
			{
				deal(e,rows);
				getScheduCard(sql);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "����δѡ���κ����ݣ�");
		}
	}
	public abstract void deal(ActionEvent e,int[] rows) ;
}
