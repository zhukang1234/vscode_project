interface Age{ //定义年龄接口
	int month = 3;//int前面自动隐含了public static final
	String ageInformation();//void前面自动隐含了public abstract
	//String ageInformation(){}//编译错，接口中的方法不能有{}主体
}
class TV implements Age{//电视机有年龄，TV要实现Age接口
	public String ageInformation(){//实现接口中的抽象方法pringAge()，这也是覆盖
		return "这台电视机的年龄是5岁"+month+"个月";//可访问接口中的常量month
	}
}
class Person implements Age{//人有年龄，Person要实现Age接口
	public String ageInformation(){//实现接口中的抽象方法pringAge()，这也是覆盖
		return "这个人的年龄是20岁"+month+"个月";//可访问接口中的常量month
	}
}
public class Test{
	static void print(Age x){//Age是接口，虽然无法用new来建立对象，但它可作为类型，相当于是TV和Person的父类
		System.out.println(x.ageInformation());
	}
	public static void main (String args[]){
		print(new TV());
		print(new Person());
	}
}
