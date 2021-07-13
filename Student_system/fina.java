package Student_system;
 
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
/*class Stu implements java.io.Serializable{
  String number,name,specialty,grade,borth,sex;
  public Stu(){};
  public void setNumber(String number){ this.number=number;}
  public String getNumber(){ return number;}
  public void setName(String name){ this.name=name;}
  public String getName(){ return name;}
  public void setSex(String sex){ this.sex=sex;}
  public String getSex(){ return sex;}
  public void setSpecialty(String specialty){ this.specialty=specialty;}
  public String getSpecialty(){ return specialty;}
  public void setGrade(String grade){ this.grade=grade;}
  public String getGrade(){ return grade;}
  public void setBorth(String borth){ this.borth=borth;}
  public String getBorth(){ return borth;}
}*/
 
public class StudentSystem extends JFrame{
	public static void main(String[] args){
		JFrame frame = new JFrame(); 
		frame.setTitle("信息管理系统");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container  = frame.getContentPane();
		container.setLayout(new FlowLayout());
  JLabel lb=new JLabel("录入请先输入记录，查询、删除请先输入学号，修改是对查询" +
          "内容改后的保存！");
  final JTextField 学号;
final JTextField 姓名;
final JTextField 专业;
final JTextField 年级;
final JTextField 出生;
  final JRadioButton 男;
final JRadioButton 女;
  ButtonGroup group=null;
  JButton 录入,查询,删除,修改,显示;
  JPanel p1,p2,p3,p4,p5,p6,pv,ph;
    学号=new JTextField(10); 
    姓名=new JTextField(10);
    专业=new JTextField(10);
    年级=new JTextField(10);
    出生=new JTextField(10);
    group=new ButtonGroup();
    男=new JRadioButton("男");
    女=new JRadioButton("女");
    group.add(男);
    group.add(女);
    录入=new JButton("录入");
    查询=new JButton("查询");
    删除=new JButton("删除");
    修改=new JButton("修改");
    显示=new JButton("显示");
    修改.setEnabled(false);
    
    //添加输入框及文本框
    p1=new JPanel();
    p1.add(new JLabel("学号:",JLabel.CENTER));
    p1.add(学号);
    p2=new JPanel();
    p2.add(new JLabel("姓名:",JLabel.CENTER));
    p2.add(姓名);
    p3=new JPanel();
    p3.add(new JLabel("性别:",JLabel.CENTER));
    p3.add(男);
    p3.add(女);
    p4=new JPanel();
    p4.add(new JLabel("专业:",JLabel.CENTER));
    p4.add(专业);
    p5=new JPanel();
    p5.add(new JLabel("年级:",JLabel.CENTER));
    p5.add(年级);
    p6=new JPanel();
    p6.add(new JLabel("出生:",JLabel.CENTER));
    p6.add(出生);
    pv=new JPanel();
    pv.setLayout(new GridLayout(6,1));
    pv.add(p1);
    pv.add(p2);
    pv.add(p3);
    pv.add(p4);
    pv.add(p5);
    pv.add(p6);
    ph=new JPanel();
    ph.add(录入);
    ph.add(查询);
    ph.add(修改);
    ph.add(删除);
    ph.add(显示);
    frame.add(lb);
    frame.add(ph);
    frame.add(p1);
    frame.add(p2);
    frame.add(p3);
    frame.add(p4);
    frame.add(p5);
    frame.add(p6);
    frame.add(pv);
    frame.add(ph);
 
    
    class MyListener implements ActionListener {
    	public void actionPerformed (ActionEvent e) {
    		
    		
    		//判断选中是男是女
    		 /*if(e.getSource().equals(男)){
    			 if(男.isSelected()) {
    				 System.out.println("男被选中了");
    			 } else {
    				 System.out.println("男被取消选中了");
    			 }
    		 }
    		 
    		 if(e.getSource().equals(女)){
    			 if(女.isSelected()) {
    				 System.out.println("女被选中了");
    			 } else {
    				 System.out.println("女被取消选中了");
    			 }
    		 }*/
    		
    		//录入 的功能
    		if(e.getActionCommand() == "录入") {
    			
    			String text1 = 学号.getText().trim();
    			String text2 = 姓名.getText();
    			String text3 = 专业.getText();
    			String text4 = 年级.getText();
    			String text5 = 出生.getText();
    			String text6 = null;
    			String text7 = "- - - - -我是分割线 - - - - -";
    			if(男.isSelected()) {
    				text6 = "男";
    			}
    			
    			if(女.isSelected()) {
    				text6 = "女";
    			}
    			
    			
    			//用texts包含此次所有录入信息
    			String texts = "\n\n学号：" + text1 + "\n\n" + "姓名：" + text2 + "\n\n" + "专业：" + text3 + "\n\n" + "年级："  + text4 + "\n\n" + "出生：" + text5 + "\n\n" + "性别：" + text6 + "\n\n" ;
    			
    			//显示框
    			int m = JOptionPane.showConfirmDialog(null, "是否录入该条记录：" + texts , "录入" , JOptionPane.YES_NO_OPTION);//n = 1/0;
    			if(m==0) {
    				insert_Student(text1,text2,text3,text4,text5);
    				
    			} else {
    				JOptionPane.showMessageDialog(null, "已取消该次录入！！");
    			}
    		}
    		
    		
    		
    		
    		
    		//显示  的功能
    		if(e.getActionCommand() == "显示") {
    			
    			
    			int n = JOptionPane.showConfirmDialog(null, "是否显示所有记录" , "显示" , JOptionPane.YES_NO_OPTION);//n = 1/0;
    			
    			if(n ==0 ) {
    			try {
    				
    				File file = new File("D:/file.txt");
    				InputStreamReader reader = new InputStreamReader(
    						new FileInputStream(file));//创建一个输入流对象
    				
    				BufferedReader bufferReader = new BufferedReader(reader);
    				
    				String line = "";
    				String lines = null;
    				line = bufferReader.readLine();
    				while(line != null) {
    					
    					System.out.println(line);
    					if(lines != null) {
    					lines = lines + "\n" + line + "\n";
    					} else {
    						lines = line + "\n" ;
    					}
    					line = bufferReader.readLine();
    				}
    				//JOptionPane.showMessageDialog(null, lines , "显示",JOptionPane.INFORMATION_MESSAGE);
    				
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    			} else {
    				JOptionPane.showMessageDialog(null, "已取消该次查询");
    			}
    			
    		}
    		
    		//查询  的功能
    		if(e.getActionCommand() == "查询") {
    			File file = new File("D:/file.txt");
    			BufferedReader reader = null;
    			String text7 = "- - - - -我是分割线 - - - - -";
    			try {
    				
    				//InputStreamReader reader = new InputStreamReader(
    					//	new FileInputStream(file));//创建一个输入流对象
    				
    				 reader = new BufferedReader(new FileReader(file));
    				
    				//暂时仅允许查询学号
    				String text1 = 学号.getText();
    				int n = JOptionPane.showConfirmDialog(null, "查询的学号为：" + text1 , "查询" , JOptionPane.YES_NO_OPTION);//n = 1/0;
    				if(n == 0){
    				String line = null;
    				String lines = null;
    				
    				while((line = reader.readLine())!= null) {
    					 if(line.equals(text1)) {
    						System.out.println(line);
    						while(!(line.equals(text7))){
    							line = reader.readLine();
    							if(lines == null) {
    								lines = line + "\n";
    							} else {
    								lines = lines + line + "\n";
    							}
    						System.out.println(line);
    						}
    						
    					 } else {
    						 continue;
    					 }
    						
    					 
    				} 
    				JOptionPane.showMessageDialog(null, lines , "查询",JOptionPane.INFORMATION_MESSAGE);
    				reader.close();
    				} else {
    					JOptionPane.showMessageDialog(null, "已取消查询功能");
    				}
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			} finally {
    				if(reader != null) {
    					try {
    						reader.close();
    					}catch (IOException e1){
    						
    					}
    				}
    			}
    		}
    		
    		
    		//删除 的功能
    		if(e.getActionCommand() == "删除") {
    			String text1 = 学号.getText().trim();
    			delete_Student(text1);
    		}
    		
    		
    		
    		
    	}	
    }
  
    //------------注册监听-------------
    
    MyListener listener  = new MyListener();
    MyListener listen  = new MyListener();
    显示.addActionListener(listener);
    录入.addActionListener(listener);
    查询.addActionListener(listener);
    删除.addActionListener(listener);
    男.addActionListener(listen);
    女.addActionListener(listen);
    frame.setVisible(true);
  }
	
	
	
	
	/*
	 * ---------------------- 功能函数 ---------------------
	 */
	
	
	//获取所有的数据
	//
    public static void  getAllMessage(){
	try {
		//添加JDBC驱动
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/test";
		String user = "root";
		String password = "root";
		Connection connect = DriverManager.getConnection(url,user,password);
		Statement stmt = connect.createStatement();
		System.out.println("success to connect ");
		
		//读取所有的数据
		
		
		
		String sql1 = "select * from StudentSystem";
		ResultSet rs = stmt.executeQuery(sql1);
		System.out.println("学号\t姓名\t专业\t年级\t出生");
		String lines = null;
		while(rs.next()) {
			System.out.print(rs.getString(1)+ "\t");
			System.out.print(rs.getString(2)+ "\t");
			System.out.print(rs.getString(3)+ "\t");
			System.out.print(rs.getString(4)+ "\t");
			System.out.print(rs.getString(5)+ "\t");
			System.out.println();
			if(lines != null){
			lines =  lines +  "学号__姓名__专业__年级__出生\n" + rs.getString(1)+ " 		 "+ rs.getString(2)+ " 		 "+ rs.getString(3)
					+ "  "+ rs.getString(4)+ "		  "+ rs.getString(5)+ " 		 " + "\n\n";
			} else {
				lines ="数据库中所有数据：\n\n" + "学号__姓名__专业__年级__出生\n" + rs.getString(1)+ ""+ rs.getString(2)+ "  "+ rs.getString(3)
						+ "		  "+ rs.getString(4)+ " 		 "+ rs.getString(5)+ "  " + "\n";
			}
		}
		JOptionPane.showMessageDialog(null, lines , "显示",JOptionPane.INFORMATION_MESSAGE);	
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
 }
    
    /**
     * 插入功能
     */
    
    	public static void insert_Student(String str1,String str2,String str3,String str4,String str5){
    		try {
    			//添加JDBC驱动
    			Class.forName("com.mysql.jdbc.Driver");
    			String url = "jdbc:mysql://localhost/test";
    			String user = "root";
    			String password = "root";
    			//连接数据库
    			Connection connect = DriverManager.getConnection(url,user,password);
    			Statement stmt = connect.createStatement();
    			System.out.println("success to connect ");
    			
    			
    			
    			
    			
    			/**
    			 * 
    			 */
    			
    			String lines = null;
    			String sql = "select * from StudentSystem";//要执行的SQL
                String sql2 = "delete from  StudentSystem where id =?";
                String sql3 = "insert into StudentSystem(id,name,study,grade,birthplace)VALUES(?,?,?,?,?)";//SQL命令
                
                
                PreparedStatement pst = (PreparedStatement)connect.prepareStatement(sql3);
                pst = connect.prepareStatement(sql3);
                
                	
                	pst.setNString(1,str1);//1,2,3,为对应上面的参数，切记！！！！！这里”12“传给了第一个问号代表的 ID
                	pst.setString(2,str2);
                	pst.setString(3,str3);
                	pst.setString(4,str4);
                	pst.setString(5,str5);
                	
               
                
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "该次录入成功！！");
                
                ResultSet rs = stmt.executeQuery(sql);
        		System.out.println("学号\t姓名\t专业\t年级\t出生");
        		while(rs.next()) {
        			System.out.print(rs.getString(1)+ "\t");
        			System.out.print(rs.getString(2)+ "\t");
        			System.out.print(rs.getString(3)+ "\t");
        			System.out.print(rs.getString(4)+ "\t");
        			System.out.print(rs.getString(5)+ "\t");
        			System.out.println();
        			if(lines != null){
        			lines =  lines +  "学号__姓名__专业__年级__出生\n" + rs.getString(1)+ " 		 "+ rs.getString(2)+ " 		 "+ rs.getString(3)
        					+ "  "+ rs.getString(4)+ "		  "+ rs.getString(5)+ " 		 " + "\n\n";
        			} else {
        				lines ="数据库中所有数据：\n\n" + "学号__姓名__专业__年级__出生\n" + rs.getString(1)+ ""+ rs.getString(2)+ "  "+ rs.getString(3)
        						+ "		  "+ rs.getString(4)+ " 		 "+ rs.getString(5)+ "  " + "\n";
        			}
        		}
                
                JOptionPane.showMessageDialog(null, lines , "显示",JOptionPane.INFORMATION_MESSAGE);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	
    	/**
         *  ------------删除功能------------
         */
    	public static void delete_Student(String str){
    		try {
    			//添加JDBC驱动
    			Class.forName("com.mysql.jdbc.Driver");
    			String url = "jdbc:mysql://localhost/test";
    			String user = "root";
    			String password = "root";
    			//连接数据库
    			Connection connect = DriverManager.getConnection(url,user,password);
    			Statement stmt = connect.createStatement();
    			System.out.println("success to connect ");
    			
    			
    			
    			
    			
    			//**
    			String lines = null;
    			String sql = "select * from StudentSystem";//要执行的SQL
                String sql2 = "delete from  StudentSystem where id =?";
                PreparedStatement pst = (PreparedStatement)connect.prepareStatement(sql2);
                pst = connect.prepareStatement(sql2);
                pst.setString(1,str);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "已删除该条记录", "删除" ,JOptionPane.INFORMATION_MESSAGE);
                
                ResultSet rs = stmt.executeQuery(sql);
        		System.out.println("学号\t姓名\t专业\t年级\t出生");
        		while(rs.next()) {
        			System.out.print(rs.getString(1)+ "\t");
        			System.out.print(rs.getString(2)+ "\t");
        			System.out.print(rs.getString(3)+ "\t");
        			System.out.print(rs.getString(4)+ "\t");
        			System.out.print(rs.getString(5)+ "\t");
        			System.out.println();
        			if(lines != null){
        			lines =  lines +  "学号__姓名__专业__年级__出生\n" + rs.getString(1)+ " 		 "+ rs.getString(2)+ " 		 "+ rs.getString(3)
        					+ "  "+ rs.getString(4)+ "		  "+ rs.getString(5)+ " 		 " + "\n\n";
        			} else {
        				lines ="数据库中所有数据：\n\n" + "学号__姓名__专业__年级__出生\n" + rs.getString(1)+ ""+ rs.getString(2)+ "  "+ rs.getString(3)
        						+ "		  "+ rs.getString(4)+ " 		 "+ rs.getString(5)+ "  " + "\n";
        			}
        		}
                
                JOptionPane.showMessageDialog(null, lines , "显示",JOptionPane.INFORMATION_MESSAGE);
    		
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
 
}
	
 
 
 
 
 

