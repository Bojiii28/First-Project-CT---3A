import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

class Frame1 extends JFrame implements ActionListener {

       // initialize the lbl with caption name is employee information.
       JLabel lbl=new JLabel("Employee Information");

       Font f=new Font("Serif",Font.BOLD,35);
       Font f1=new Font("Serif",Font.BOLD,15);

       
       JLabel lblid,lblname,lblcity,lblsex,lblsal,lblsubmit;
       JTextField txtid,txtname,txtcity,txtsal;
       JRadioButton rbmale,rbfemale;
       JButton btnadd,btnsave,btnupdate,btndelete,btnexit,btnref;
       JButton btnnext,btnprev,btnlast,btnfirst, btny, btnn;
       String gen;
       ResultSet rs=null;
       Connection con=null;
       Statement stmt=null;
       
       Frame1()
       {
       // this is display in a Frame titlebar.
       super("Developed by group 1");
       addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we)
                {
                System.exit(0);
                }
       });
       // set layout to null
       //setLayout(null);
       
       
       lblid=new JLabel("ID");
       lblname=new JLabel("NAME");
       lblcity=new JLabel("CITY");
       lblsal=new JLabel("SALARY");
       lblsex=new JLabel("SEX");
       
       lbl.setFont(f);
       lblid.setFont(f1);
       lblname.setFont(f1);
       lblcity.setFont(f1);
       lblsal.setFont(f1);
       lblsex.setFont(f1);
       
       /*lbl.setHorizontalAlignment(JLabel.RIGHT);
       lblid.setHorizontalAlignment(JLabel.RIGHT);
       lblname.setHorizontalAlignment(JLabel.RIGHT);
       lblcity.setHorizontalAlignment(JLabel.RIGHT);
       lblsal.setHorizontalAlignment(JLabel.RIGHT);
       lblsex.setHorizontalAlignment(JLabel.RIGHT);*/
       
       txtid=new JTextField(15);
       txtname=new JTextField(15);
       txtcity=new JTextField(15);
       txtsal=new JTextField(15);
       rbmale=new JRadioButton("Male");
       rbfemale=new JRadioButton("Female");
       
       btnadd=new JButton("Add");
       btnsave=new JButton("Save");
       btnupdate=new JButton("Update");
       btndelete=new JButton("Delete");
       btnfirst=new JButton("First");
       btnnext=new JButton("Next");
       btnprev=new JButton("Previous");
       btnlast=new JButton("Last");
       btnexit=new JButton("Exit");
       btnref=new JButton("Refresh");
       
       btnadd.setFont(f1);
       btnsave.setFont(f1);
       btnupdate.setFont(f1);
       btndelete.setFont(f1);
       btnfirst.setFont(f1);
       btnnext.setFont(f1);
       btnprev.setFont(f1);
       btnlast.setFont(f1);
       btnexit.setFont(f1);
       btnref.setFont(f1);
       
       btnadd.setPreferredSize(new Dimension(150, 50));
       //btnadd.setMinimumSize(new Dimension(130, 30));
       //btnadd.setMaximumSize(new Dimension(260, 60));
       
       
       //add the title into a panel
       JPanel titlePanel = new JPanel(new GridBagLayout());
       titlePanel.add(lbl);
       titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
       
       
       //group the two radio buttons together
       ButtonGroup bg = new ButtonGroup();

       //add button to group
       bg.add(rbmale);
       bg.add(rbfemale);
       
       //add two radio buttons into panel
       JPanel rbtn = new JPanel(new GridLayout(1,2, 10, 10));
       rbtn.add(rbmale);
       rbtn.add(rbfemale);
       
       //add components into lblPanel
       JPanel lblPanel = new JPanel(new GridLayout(5,4, 20, 20));
       lblPanel.add(lblid);
       lblPanel.add(txtid);
       lblPanel.add(btnadd);
       lblPanel.add(btnsave);
       lblPanel.add(lblname);
       lblPanel.add(txtname);
       lblPanel.add(btndelete);
       lblPanel.add(btnupdate);
       lblPanel.add(lblcity);
       lblPanel.add(txtcity);
       lblPanel.add(btnprev);
       lblPanel.add(btnnext);
       lblPanel.add(lblsal);
       lblPanel.add(txtsal);
       lblPanel.add(btnfirst);
       lblPanel.add(btnlast);
       lblPanel.add(lblsex);
       lblPanel.add(rbtn); 
       lblPanel.add(btnexit);
       lblPanel.add(btnref);
       
       //submit label
       lblsubmit=new JLabel("Developed By: GROUP 1");
       lblsubmit.setHorizontalAlignment(JLabel.CENTER);
       lblsubmit.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
       
       //add the label submit panel into another panel
       JPanel lblContainer = new JPanel(new GridBagLayout());
       lblContainer.add(lblPanel);
       lblContainer.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
      
       //add the panels into frame
       add(titlePanel, BorderLayout.NORTH);
       add(lblContainer, BorderLayout.CENTER);
       add(lblsubmit, BorderLayout.SOUTH);


       // register radio button
       rbmale.addActionListener(this);
       rbfemale.addActionListener(this);

       // register all the button
       btnadd.addActionListener(this);
       btnsave.addActionListener(this);
       btnupdate.addActionListener(this);
       btndelete.addActionListener(this);
  
       // register all the button 
       btnfirst.addActionListener(this);
       btnnext.addActionListener(this);
       btnprev.addActionListener(this);
       btnlast.addActionListener(this);           
       btnexit.addActionListener(this);
       btnref.addActionListener(this);
       
       btny=new JButton("Yes");
       btnn=new JButton("No");
   
       // open database connection
       // here we call a dbopen() method
       dbOpen();
       }

       
       public void actionPerformed(ActionEvent ae)
	{
		try
		{
			
	 		if(ae.getActionCommand()=="Add")
			{
				txtid.setText("");
				txtname.setText("");
				txtcity.setText("");
				txtsal.setText("");
				
			}
			if(ae.getActionCommand()=="Update")
			{
				stmt.executeUpdate("UPDATE Emp SET name='" + txtname.getText() + "',city='" + txtcity.getText() + "',sal=" + txtsal.getText() + ",sex='"+gen+"' WHERE id=" + txtid.getText() + "");
				dbClose();
				dbOpen();
					JOptionPane.showMessageDialog(null, "Record successfully updated. Refresh.", "Message", JOptionPane.PLAIN_MESSAGE);
			}
			if(ae.getActionCommand()=="Delete")
			{
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Message",JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					stmt.executeUpdate("DELETE FROM Emp WHERE id=" + txtid.getText() + "");			
					dbClose();
					dbOpen();
					JOptionPane.showMessageDialog(null, "Record deleted. Refresh.", "Message", JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					dbClose();
					dbOpen();
				}
			}	
			if(ae.getActionCommand()=="Save")
			{
				stmt.executeUpdate("INSERT INTO Emp VALUES('" + txtid.getText() + "','" + txtname.getText() + "','" + txtcity.getText() + "'," + txtsal.getText() + ",'"+gen+"')");
				dbClose();
				dbOpen();
				JOptionPane.showMessageDialog(null, "Record successfully saved. Refresh.", "Message", JOptionPane.PLAIN_MESSAGE);
			}
			if(ae.getActionCommand()=="Next")
			{
				if(rs.next())
				{
					setText();
					setText();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You are At Already Last Record", "Message", JOptionPane.ERROR_MESSAGE);	
                  		}
			}
			if(ae.getActionCommand()=="Previous")
			{
				if(rs.previous())
				{
                  	setText();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You Are At Already First Record", "Message", JOptionPane.ERROR_MESSAGE);
                  		}
			}
			if (ae.getActionCommand()=="First")
			{
				if(rs.first())
				{
					setText();
				}
			}
			if (ae.getActionCommand()=="Last")
			{
				if(rs.last())
				{
					setText();
				}
			}
			if(ae.getActionCommand()=="Exit")
			{
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Message",JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					System.exit(0);	
				}
				else
				{
					dbClose();
					dbOpen();
				}
				
			}
			if(ae.getActionCommand()=="Female")
			 {
				gen="Female";
			 }
			 else
			 {
				gen="Male";
			 }
			 if(ae.getActionCommand()=="Refresh")
			 {
			 	dbClose();
			 	dbOpen();
			 }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void dbOpen()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=prog1.mdb;";
			//Connection con = DriverManager.getConnection(database, "", "");
            con=DriverManager.getConnection("jdbc:odbc:LoginTutorial");
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("Select * from Emp");
			if(rs.next())
			setText();
		}catch(Exception e){}
	}
	
	public void dbClose()
	{
		try{stmt.close();
		rs.close();
		con.close();
		}catch(Exception e){}
	}
	
	public void setText(){
		try{
			txtid.setText(rs.getString(1));
			txtname.setText(rs.getString(2));
			txtcity.setText(rs.getString(3));
			txtsal.setText(rs.getString(4));
			if(rs.getString(5).equals("Male")) 
			{	
				rbmale.setSelected(true);
			}	
			else
			{
				rbfemale.setSelected(true);
			}
	    	}catch(Exception ex){}		
	    	}
}

public class EmpData
{
        public static void main(String ar[])throws Exception
        {

        // create a object of Frame1 class in main method
        Frame1 f1=new Frame1();

        // set frame size
        f1.setSize(800,600);
		f1.setLocationRelativeTo(null);
        // set frame visible true
        f1.setVisible(true);
        //set look and feel for frame
	    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	    SwingUtilities.updateComponentTreeUI(f1);
        }
}
/* Group 1
Alabastro, Karen R.
Angeles, Kyla Marie G.
Garcia, Erlinda L.
Guevarra, Tanya Nicole C.
Ilag, Jazreen Dhane C.
Arellano, Jose Mari R.
Cabantog, Peter James M.
Cabantog, Peter John M.	 
Coronel, Russel John B.
Enriquez, John Mark M.
Inocencio, Aaron James D.
Racho, Jericko Jireh C.
 
 */
