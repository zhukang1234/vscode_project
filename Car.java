public class Car {
  private String ownerName; //车主姓名
  private float curSpeed; //当前车速
  private float curDirInDegree; //当前方向盘转向角度
  public Car(String ownerName){
    this.ownerName=ownerName;
  }
  public Car(String ownerName, float speed, float dirInDegree){
   this(ownerName);
   this.curSpeed=speed;
   this.curDirInDegree=dirInDegree;
 }
  public String getOwnerName() { //提供对车主姓名的访问
    return ownerName;
  }
  public float getCurDirInDegree() { //提供对当前方向盘转向角度的访问
    return curDirInDegree;
  }
  public float getCurSpeed() { //提供对当前车速的访问
    return curSpeed;
  }
  public void changeSpeed(float curSpeed) { //提供改变当前的车速
    this.curSpeed = curSpeed;
  }
  public void stop(){ //提供停车
    this.curSpeed=0;
  }
  public static void main(String[] args) {}
}

