import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//SPLASH

class SplashClass
{
	JFrame frm;
	JLabel l1,l2,l3,l4,l5,l6;
	JProgressBar jb;
	public SplashClass()
	{
		frm=new JFrame("PLACEMENT OFFICE AUTOMATION SYSTEM");
		frm.setDefaultCloseOperation(frm.EXIT_ON_CLOSE);
		frm.setLayout(null);
		l1=new JLabel("PLACEMENT");
		l2=new JLabel("OFFICE AUTOMATION");
		l3=new JLabel("SYSTEM");
		l4=new JLabel("WELCOME");
		l5=new JLabel("Developed by --------");
		
		 l6=new JLabel(new ImageIcon("home.png"));
				 
		
		
		
		jb=new JProgressBar();
		
		Font f=new Font("Times New Roman",Font.BOLD,25);
		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		l3.setForeground(Color.blue);
		
		
		
		

		l1.setBounds(100,10,450,30);
		frm.add(l1);
		l2.setBounds(100,50,450,30);
		frm.add(l2);
		l3.setBounds(100,90,450,30);
		frm.add(l3);
		
		l4.setForeground(Color.red);
		l4.setBounds(100,150,100,30);
		frm.add(l4);

		l5.setForeground(Color.black);
		l5.setBounds(100,200,150,30);
		frm.add(l5);
		
		
		frm.add(l6);
		l6.setBounds(20,350,630,150);
		
		

		jb.setBackground(Color.pink);
		jb.setBounds(100,300,270,20);
		frm.add(jb);

		frm.setBounds(450,200,700,600);
		frm.show();

		jb.setMinimum(0);
		jb.setMaximum(100);
		jb.setStringPainted(true);
		for(int i=0;i<=100;i++)
		{
			jb.setValue(i);
			try
			{
				Thread.sleep(20);
			}
			catch(Exception tt)
			{}
		}
		frm.dispose();
		new login();
			

	
	}
}








////////////////////////////////////////////////////////////////
public class login extends JFrame implements ActionListener{
 JPanel butpan=new JPanel();
 JPanel logpan=new JPanel();
 JPanel selpan=new JPanel();
 JLabel un=new JLabel("Login-ID");
 JTextField usr=new JTextField(10);
 JLabel pd=new JLabel("Password");
 JTextField pwd=new JTextField(10);
 JButton reset=new JButton("Reset");
 JButton log=new JButton("Login");
 JButton join=new JButton("Join");
 JRadioButton app=new JRadioButton("Applicants");
 JRadioButton emp=new JRadioButton("Employers");
 ButtonGroup bg=new ButtonGroup();
 Connection clog;
 Statement st;  
 int flag=0;
 int i=0; 
 

 public static void main(String args[]){
 // new login();
  SplashClass obj=new SplashClass();
 }

 public login(){
  getContentPane().add(butpan,BorderLayout.SOUTH);
  getContentPane().add(logpan,BorderLayout.WEST);
  getContentPane().add(selpan,BorderLayout.EAST);

  logpan.add(un);
  logpan.add(usr);
  logpan.add(pd);
  logpan.add(pwd);

  butpan.add(reset);
  butpan.add(log);
  butpan.add(join);

  selpan.add(app);
  selpan.add(emp);

  bg.add(app);
  bg.add(emp);
  setVisible(true);
  setSize(400,400);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  pack();
  reset.addActionListener(this);
  log.addActionListener(this);
  join.addActionListener(this);



 }

 public void actionPerformed(ActionEvent ae){
  Object source=ae.getSource();
  if(source==reset){
   usr.setText("");
   pwd.setText("");
  }
  else if(source==join){
   if(app.isSelected()) {  new app(); setVisible(false);}
   
   else if(emp.isSelected()) { new emp(); setVisible(false);}
   
  }
  else if(source==log){

/*Log in of Applicants start from here*/

   if(app.isSelected()){
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     clog=DriverManager.getConnection("jdbc:odbc:go");
     st=clog.createStatement();
     ResultSet rsLog1=st.executeQuery("select * from Applicant");

     while(rsLog1.next()){
      boolean b1=rsLog1.getString(8).equals((String)usr.getText());
      boolean b2=rsLog1.getString(9).equals((String)pwd.getText());
      if( b1 && b2 ){
JOptionPane.showMessageDialog(null,"done");			//Remove this line
       new empdetail(rsLog1.getInt(7));
       flag=1; 	
       break;
      }
     }
     if(flag!=1 && i<=3){
      ++i;
      JOptionPane.showMessageDialog(null,"Invalid Entry");
     }
     else if(flag!=1 && i>3){
      System.exit(0);
     }
     else if(flag==1){
      setVisible(false);
     }
    }
    catch(Exception excp){
     JOptionPane.showMessageDialog(null,excp);
     System.exit(0);
    }
   }

/*log in of Employers Start from here*/

   else if(emp.isSelected()){
    try{
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     clog=DriverManager.getConnection("jdbc:odbc:go");
     st=clog.createStatement();
     ResultSet rsLog2=st.executeQuery("select * from Company");

     while(rsLog2.next()){
      boolean b1=rsLog2.getString(5).equals((String)usr.getText());
      boolean b2=rsLog2.getString(6).equals((String)pwd.getText());
      if( b1 && b2 ){
JOptionPane.showMessageDialog(null,"done");			//Remove this line
       new appdetail(rsLog2.getInt(4));
       flag=1; 	
       break;
      }
     }
     if(flag!=1 && i<=3){
      ++i;
      JOptionPane.showMessageDialog(null,"Invalid Entry");
     }
     else if(flag!=1 && i>3){
      System.exit(0);
     }
     else if(flag==1){
      setVisible(false);
     }
    }
    catch(Exception excp){
     JOptionPane.showMessageDialog(null,excp);
     System.exit(0);
    }
   }
  }
 }
}


