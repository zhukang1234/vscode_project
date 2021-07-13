public class TestCar{
    public static void main(String[] args){
      Car car = new Car("成龙",200f,25f);
      System.out.println("车主姓名: "+car.getOwnerName());
      System.out.println("当前车速: "+car.getCurSpeed());
      System.out.println("当前转向角度: "+car.getCurDirInDegree());
      car.changeSpeed(80);
      System.out.println("在调用changeSpeed(80)后,车速变为: " + car.getCurSpeed());
      car.stop();
      System.out.println("在调用stop()后, 车速变为: "+car.getCurSpeed());
    }
  }
  