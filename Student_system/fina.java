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
		frame.setTitle("��Ϣ����ϵͳ");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container  = frame.getContentPane();
		container.setLayout(new FlowLayout());
  JLabel lb=new JLabel("¼�����������¼����ѯ��ɾ����������ѧ�ţ��޸��ǶԲ�ѯ" +
          "���ݸĺ�ı��棡");
  final JTextField ѧ��;
final JTextField ����;
final JTextField רҵ;
final JTextField �꼶;
final JTextField ����;
  final JRadioButton ��;
final JRadioButton Ů;
  ButtonGroup group=null;
  JButton ¼��,��ѯ,ɾ��,�޸�,��ʾ;
  JPanel p1,p2,p3,p4,p5,p6,pv,ph;
    ѧ��=new JTextField(10); 
    ����=new JTextField(10);
    רҵ=new JTextField(10);
    �꼶=new JTextField(10);
    ����=new JTextField(10);
    group=new ButtonGroup();
    ��=new JRadioButton("��");
    Ů=new JRadioButton("Ů");
    group.add(��);
    group.add(Ů);
    ¼��=new JButton("¼��");
    ��ѯ=new JButton("��ѯ");
    ɾ��=new JButton("ɾ��");
    �޸�=new JButton("�޸�");
    ��ʾ=new JButton("��ʾ");
    �޸�.setEnabled(false);
    
    //���������ı���
    p1=new JPanel();
    p1.add(new JLabel("ѧ��:",JLabel.CENTER));
    p1.add(ѧ��);
    p2=new JPanel();
    p2.add(new JLabel("����:",JLabel.CENTER));
    p2.add(����);
    p3=new JPanel();
    p3.add(new JLabel("�Ա�:",JLabel.CENTER));
    p3.add(��);
    p3.add(Ů);
    p4=new JPanel();
    p4.add(new JLabel("רҵ:",JLabel.CENTER));
    p4.add(רҵ);
    p5=new JPanel();
    p5.add(new JLabel("�꼶:",JLabel.CENTER));
    p5.add(�꼶);
    p6=new JPanel();
    p6.add(new JLabel("����:",JLabel.CENTER));
    p6.add(����);
    pv=new JPanel();
    pv.setLayout(new GridLayout(6,1));
    pv.add(p1);
    pv.add(p2);
    pv.add(p3);
    pv.add(p4);
    pv.add(p5);
    pv.add(p6);
    ph=new JPanel();
    ph.add(¼��);
    ph.add(��ѯ);
    ph.add(�޸�);
    ph.add(ɾ��);
    ph.add(��ʾ);
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
    		
    		
    		//�ж�ѡ��������Ů
    		 /*if(e.getSource().equals(��)){
    			 if(��.isSelected()) {
    				 System.out.println("�б�ѡ����");
    			 } else {
    				 System.out.println("�б�ȡ��ѡ����");
    			 }
    		 }
    		 
    		 if(e.getSource().equals(Ů)){
    			 if(Ů.isSelected()) {
    				 System.out.println("Ů��ѡ����");
    			 } else {
    				 System.out.println("Ů��ȡ��ѡ����");
    			 }
    		 }*/
    		
    		//¼�� �Ĺ���
    		if(e.getActionCommand() == "¼��") {
    			
    			String text1 = ѧ��.getText().trim();
    			String text2 = ����.getText();
    			String text3 = רҵ.getText();
    			String text4 = �꼶.getText();
    			String text5 = ����.getText();
    			String text6 = null;
    			String text7 = "- - - - -���Ƿָ��� - - - - -";
    			if(��.isSelected()) {
    				text6 = "��";
    			}
    			
    			if(Ů.isSelected()) {
    				text6 = "Ů";
    			}
    			
    			
    			//��texts�����˴�����¼����Ϣ
    			String texts = "\n\nѧ�ţ�" + text1 + "\n\n" + "������" + text2 + "\n\n" + "רҵ��" + text3 + "\n\n" + "�꼶��"  + text4 + "\n\n" + "������" + text5 + "\n\n" + "�Ա�" + text6 + "\n\n" ;
    			
    			//��ʾ��
    			int m = JOptionPane.showConfirmDialog(null, "�Ƿ�¼�������¼��" + texts , "¼��" , JOptionPane.YES_NO_OPTION);//n = 1/0;
    			if(m==0) {
    				insert_Student(text1,text2,text3,text4,text5);
    				
    			} else {
    				JOptionPane.showMessageDialog(null, "��ȡ���ô�¼�룡��");
    			}
    		}
    		
    		
    		
    		
    		
    		//��ʾ  �Ĺ���
    		if(e.getActionCommand() == "��ʾ") {
    			
    			
    			int n = JOptionPane.showConfirmDialog(null, "�Ƿ���ʾ���м�¼" , "��ʾ" , JOptionPane.YES_NO_OPTION);//n = 1/0;
    			
    			if(n ==0 ) {
    			try {
    				
    				File file = new File("D:/file.txt");
    				InputStreamReader reader = new InputStreamReader(
    						new FileInputStream(file));//����һ������������
    				
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
    				//JOptionPane.showMessageDialog(null, lines , "��ʾ",JOptionPane.INFORMATION_MESSAGE);
    				
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    			} else {
    				JOptionPane.showMessageDialog(null, "��ȡ���ôβ�ѯ");
    			}
    			
    		}
    		
    		//��ѯ  �Ĺ���
    		if(e.getActionCommand() == "��ѯ") {
    			File file = new File("D:/file.txt");
    			BufferedReader reader = null;
    			String text7 = "- - - - -���Ƿָ��� - - - - -";
    			try {
    				
    				//InputStreamReader reader = new InputStreamReader(
    					//	new FileInputStream(file));//����һ������������
    				
    				 reader = new BufferedReader(new FileReader(file));
    				
    				//��ʱ�������ѯѧ��
    				String text1 = ѧ��.getText();
    				int n = JOptionPane.showConfirmDialog(null, "��ѯ��ѧ��Ϊ��" + text1 , "��ѯ" , JOptionPane.YES_NO_OPTION);//n = 1/0;
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
    				JOptionPane.showMessageDialog(null, lines , "��ѯ",JOptionPane.INFORMATION_MESSAGE);
    				reader.close();
    				} else {
    					JOptionPane.showMessageDialog(null, "��ȡ����ѯ����");
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
    		
    		
    		//ɾ�� �Ĺ���
    		if(e.getActionCommand() == "ɾ��") {
    			String text1 = ѧ��.getText().trim();
    			delete_Student(text1);
    		}
    		
    		
    		
    		
    	}	
    }
  
    //------------ע�����-------------
    
    MyListener listener  = new MyListener();
    MyListener listen  = new MyListener();
    ��ʾ.addActionListener(listener);
    ¼��.addActionListener(listener);
    ��ѯ.addActionListener(listener);
    ɾ��.addActionListener(listener);
    ��.addActionListener(listen);
    Ů.addActionListener(listen);
    frame.setVisible(true);
  }
	
	
	
	
	/*
	 * ---------------------- ���ܺ��� ---------------------
	 */
	
	
	//��ȡ���е�����
	//
    public static void  getAllMessage(){
	try {
		//���JDBC����
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/test";
		String user = "root";
		String password = "root";
		Connection connect = DriverManager.getConnection(url,user,password);
		Statement stmt = connect.createStatement();
		System.out.println("success to connect ");
		
		//��ȡ���е�����
		
		
		
		String sql1 = "select * from StudentSystem";
		ResultSet rs = stmt.executeQuery(sql1);
		System.out.println("ѧ��\t����\tרҵ\t�꼶\t����");
		String lines = null;
		while(rs.next()) {
			System.out.print(rs.getString(1)+ "\t");
			System.out.print(rs.getString(2)+ "\t");
			System.out.print(rs.getString(3)+ "\t");
			System.out.print(rs.getString(4)+ "\t");
			System.out.print(rs.getString(5)+ "\t");
			System.out.println();
			if(lines != null){
			lines =  lines +  "ѧ��__����__רҵ__�꼶__����\n" + rs.getString(1)+ " 		 "+ rs.getString(2)+ " 		 "+ rs.getString(3)
					+ "  "+ rs.getString(4)+ "		  "+ rs.getString(5)+ " 		 " + "\n\n";
			} else {
				lines ="���ݿ����������ݣ�\n\n" + "ѧ��__����__רҵ__�꼶__����\n" + rs.getString(1)+ ""+ rs.getString(2)+ "  "+ rs.getString(3)
						+ "		  "+ rs.getString(4)+ " 		 "+ rs.getString(5)+ "  " + "\n";
			}
		}
		JOptionPane.showMessageDialog(null, lines , "��ʾ",JOptionPane.INFORMATION_MESSAGE);	
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
 }
    
    /**
     * ���빦��
     */
    
    	public static void insert_Student(String str1,String str2,String str3,String str4,String str5){
    		try {
    			//���JDBC����
    			Class.forName("com.mysql.jdbc.Driver");
    			String url = "jdbc:mysql://localhost/test";
    			String user = "root";
    			String password = "root";
    			//�������ݿ�
    			Connection connect = DriverManager.getConnection(url,user,password);
    			Statement stmt = connect.createStatement();
    			System.out.println("success to connect ");
    			
    			
    			
    			
    			
    			/**
    			 * 
    			 */
    			
    			String lines = null;
    			String sql = "select * from StudentSystem";//Ҫִ�е�SQL
                String sql2 = "delete from  StudentSystem where id =?";
                String sql3 = "insert into StudentSystem(id,name,study,grade,birthplace)VALUES(?,?,?,?,?)";//SQL����
                
                
                PreparedStatement pst = (PreparedStatement)connect.prepareStatement(sql3);
                pst = connect.prepareStatement(sql3);
                
                	
                	pst.setNString(1,str1);//1,2,3,Ϊ��Ӧ����Ĳ������мǣ������������12�������˵�һ���ʺŴ���� ID
                	pst.setString(2,str2);
                	pst.setString(3,str3);
                	pst.setString(4,str4);
                	pst.setString(5,str5);
                	
               
                
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "�ô�¼��ɹ�����");
                
                ResultSet rs = stmt.executeQuery(sql);
        		System.out.println("ѧ��\t����\tרҵ\t�꼶\t����");
        		while(rs.next()) {
        			System.out.print(rs.getString(1)+ "\t");
        			System.out.print(rs.getString(2)+ "\t");
        			System.out.print(rs.getString(3)+ "\t");
        			System.out.print(rs.getString(4)+ "\t");
        			System.out.print(rs.getString(5)+ "\t");
        			System.out.println();
        			if(lines != null){
        			lines =  lines +  "ѧ��__����__רҵ__�꼶__����\n" + rs.getString(1)+ " 		 "+ rs.getString(2)+ " 		 "+ rs.getString(3)
        					+ "  "+ rs.getString(4)+ "		  "+ rs.getString(5)+ " 		 " + "\n\n";
        			} else {
        				lines ="���ݿ����������ݣ�\n\n" + "ѧ��__����__רҵ__�꼶__����\n" + rs.getString(1)+ ""+ rs.getString(2)+ "  "+ rs.getString(3)
        						+ "		  "+ rs.getString(4)+ " 		 "+ rs.getString(5)+ "  " + "\n";
        			}
        		}
                
                JOptionPane.showMessageDialog(null, lines , "��ʾ",JOptionPane.INFORMATION_MESSAGE);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	
    	/**
         *  ------------ɾ������------------
         */
    	public static void delete_Student(String str){
    		try {
    			//���JDBC����
    			Class.forName("com.mysql.jdbc.Driver");
    			String url = "jdbc:mysql://localhost/test";
    			String user = "root";
    			String password = "root";
    			//�������ݿ�
    			Connection connect = DriverManager.getConnection(url,user,password);
    			Statement stmt = connect.createStatement();
    			System.out.println("success to connect ");
    			
    			
    			
    			
    			
    			//**
    			String lines = null;
    			String sql = "select * from StudentSystem";//Ҫִ�е�SQL
                String sql2 = "delete from  StudentSystem where id =?";
                PreparedStatement pst = (PreparedStatement)connect.prepareStatement(sql2);
                pst = connect.prepareStatement(sql2);
                pst.setString(1,str);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "��ɾ��������¼", "ɾ��" ,JOptionPane.INFORMATION_MESSAGE);
                
                ResultSet rs = stmt.executeQuery(sql);
        		System.out.println("ѧ��\t����\tרҵ\t�꼶\t����");
        		while(rs.next()) {
        			System.out.print(rs.getString(1)+ "\t");
        			System.out.print(rs.getString(2)+ "\t");
        			System.out.print(rs.getString(3)+ "\t");
        			System.out.print(rs.getString(4)+ "\t");
        			System.out.print(rs.getString(5)+ "\t");
        			System.out.println();
        			if(lines != null){
        			lines =  lines +  "ѧ��__����__רҵ__�꼶__����\n" + rs.getString(1)+ " 		 "+ rs.getString(2)+ " 		 "+ rs.getString(3)
        					+ "  "+ rs.getString(4)+ "		  "+ rs.getString(5)+ " 		 " + "\n\n";
        			} else {
        				lines ="���ݿ����������ݣ�\n\n" + "ѧ��__����__רҵ__�꼶__����\n" + rs.getString(1)+ ""+ rs.getString(2)+ "  "+ rs.getString(3)
        						+ "		  "+ rs.getString(4)+ " 		 "+ rs.getString(5)+ "  " + "\n";
        			}
        		}
                
                JOptionPane.showMessageDialog(null, lines , "��ʾ",JOptionPane.INFORMATION_MESSAGE);
    		
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
 
}
	
 
 
 
 
 

