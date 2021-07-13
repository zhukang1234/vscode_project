public class HiStudent extends Student{ //大学生类继承自学生类
	protected String xy;//所在学院或大学名称
	protected String xi;//所在系的名称
	public static void main(String args[]){
		Student s1 = new Student();
		s1.setData("李四",12321) ;
		s1.print();
		HiStudent s2 = new HiStudent() ;
		s2.setData("张三",12345); //调用父类的成员方法
		s2.xy="XX大学"; //访问本类的成员变量
		s2.xi="计算机系"; //访问本类的成员变量
		s2.print();//调用父类的方法，就象调用它自己的一样
		System.out.print(s2.xm+", "+s2.xy+", "+s2.xi);
	}
} 
