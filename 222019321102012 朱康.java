/********************************
2019计科1-2班  实验考核
完成以后改名为 学号姓名.java 提交到服务器实验考核文件夹。
五子棋单机二人对战游戏，双方都用鼠标，交替下棋。目前有的功能：鼠标下棋；判定胜负；点击菜单-->重新开始，用JOptionPane.showConfirmDialog弹出确认对话框，可以新开一局。
你需要完善的地方：
（1）点击菜单 游戏-->退出，或按Esc键(KeyEvent.VK_ESCAPE)，会用JOptionPane.showConfirmDialog弹出确认对话框，点“是”，
则退出程序。
由于默认由窗口监听按键，故这2个监听器的注册应放在窗口类GameWindow的构造方法里。由于2个监听器做相同的事情，可以定义一个函数，2个监听器调用这个函数。
用到了KeyEvent和KeyAdapter这样的类，自行添加import语句。
（2）这是快棋游戏，限定每次落子时间为10秒。例如，黑子落子后，白子在10秒内没有下子，则判定黑棋胜，用JOptionPane.showMessageDialog输出胜负信息，并重开一局。
remainingTime变量记录剩余秒数，从10开始，每一秒减1，减到0则超时，判定胜负。有一方落子，则秒数又从10开始递减。
在面板的paintComponent方法的最后，已经用drawString()函数输出剩余时间信息，但它是固定的。
你需要在面板添加一个定时器类型的成员变量（javax.swing.Timer），在面板的构造方法中注册，每一秒触发一次，让秒数递减，减到0则判胜负。
【提示】
Timer的使用方法是
class TimerListener implements ActionListener{
	public void actionPerformed(ActionEvent e){ }//改变剩余秒数，面板重绘
}
Timer timer=new Timer(1000,new TimerListener());//给定时器建立监听器
timer.start();//启动定时器  timer.stop则暂停

********************************/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
//---------------------------------------------------------------------------------------------

class GamePanel extends JPanel{
	/**
	 * 静态常量数据属性
	 */
	private static final int BOARD_SIZE = 15; // 棋盘大小(15行 * 15列)
	private static final int ROW_WIDTH = 36; // 行间距列、间距（像素）
	private static final int SPACE = ROW_WIDTH / 2; // 上下边间距
	/**
	 * 实例变量数据属性
	 */
	private boolean player; // true黑,false白
	private char[][] board; // 后台虚拟棋盘15*15，每个位置'+'表示未落子，'b'已落黑棋，'w'已落白棋
	private int remainingTime=10;//剩余时间（秒数，从10到0）
    private Timer time;

 	/************************************************
	  清空棋子（虚拟棋盘）
	 ***********************************************/
	private void clearMap() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = '+';
			}
		}
	}
	/************************************************
	  重新开始一局游戏
	***********************************************/
	public void reStart(){
		clearMap(); // 清空虚拟棋盘
		repaint(); // 重绘棋盘（面板）
		player = true; // 每一局，黑棋先下
	}
	/************************************************
	  面板构造方法，建立虚拟棋盘board，清空棋子
	 ***********************************************/
	public GamePanel() {
		board = new char[BOARD_SIZE][BOARD_SIZE]; // 建立后台虚拟棋盘

		setPreferredSize(new Dimension(ROW_WIDTH * (BOARD_SIZE - 1) + SPACE
				* 2, ROW_WIDTH * (BOARD_SIZE - 1) + SPACE * 2+ROW_WIDTH) );//增加36像素高度(最后那个ROW_WIDTH)，为显示剩余时间
		reStart();
        time = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime=remainingTime-1;
                repaint();
                if(remainingTime==0){
                    String str = !player ? "黑方胜利" : "白方胜利";
					JOptionPane.showMessageDialog(null, str, "游戏结束",JOptionPane.PLAIN_MESSAGE); // 弹出消息框
					reStart();
                    remainingTime=10;
                }
            }
        });


        
		//为面板注册监听器，点击则落子，并判断是否出现胜者
		addMouseListener(new MouseAdapter() {
            
			public void mouseClicked(MouseEvent e) {

                remainingTime=10;
                time.restart();//启动定时器  timer.stop则暂停
				// 将用户鼠标事件的像素坐标转换成棋子数组的坐标。
				int xPos = (int) (e.getX() / ROW_WIDTH);
				int yPos = (int) (e.getY() / ROW_WIDTH);
				if (board[xPos][yPos] == '+') {// 如果此处没有下过棋
					board[xPos][yPos] = player ? 'b' : 'w'; // 给虚拟棋盘赋值，要么黑，要么白
					repaint(); // 通过读取board数组绘图
					if (someoneIsWinner(xPos, yPos)) {// 如果有玩家获胜
						String str = player ? "黑方胜利" : "白方胜利";
						JOptionPane.showMessageDialog(null, str, "游戏结束",JOptionPane.PLAIN_MESSAGE); // 弹出消息框
						reStart();
                        
					} else { //如果没有人胜利，则切换玩家
						player = !player;
					}

				}
			}
		});
        
	}//面板构造方法结束

	/************************************************
	  面板的绘图方法
	***********************************************/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 棋盘
		g.setColor(new Color(200, 100, 50)); // 设为桔黄色
		g.fillRect(0, 0, ROW_WIDTH * (BOARD_SIZE + 1), ROW_WIDTH
				* (BOARD_SIZE + 1)); // 填充棋盘
		g.setColor(Color.black); // 设为黑色
		for (int i = 0; i < BOARD_SIZE; i++) {// 画竖线
			g.drawLine(SPACE + ROW_WIDTH * i, SPACE, SPACE + ROW_WIDTH * i,
					SPACE + ROW_WIDTH * (BOARD_SIZE - 1));
		}
		for (int i = 0; i < BOARD_SIZE; i++) {// 画横线
			g.drawLine(SPACE, SPACE + ROW_WIDTH * i, SPACE + ROW_WIDTH
					* (BOARD_SIZE - 1), SPACE + ROW_WIDTH * i);
		}
		// 画点
		g.fillOval(SPACE + 3 * ROW_WIDTH - 3, SPACE + 3 * ROW_WIDTH - 3, 7,
				7);
		g.fillOval(SPACE + 11 * ROW_WIDTH - 3, SPACE + 3 * ROW_WIDTH - 3,
				7, 7);
		g.fillOval(SPACE + 3 * ROW_WIDTH - 3, SPACE + 11 * ROW_WIDTH - 3,
				7, 7);
		g.fillOval(SPACE + 7 * ROW_WIDTH - 3, SPACE + 7 * ROW_WIDTH - 3, 7,
				7);
		g.fillOval(SPACE + 11 * ROW_WIDTH - 3, SPACE + 11 * ROW_WIDTH - 3,
				7, 7);

		// 遍历数组，绘制黑白棋子
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == 'b') {// 绘制黑棋
					g.setColor(Color.black);
					g.fillOval(SPACE + i * ROW_WIDTH - ROW_WIDTH / 2, SPACE
							+ j * ROW_WIDTH - ROW_WIDTH / 2, ROW_WIDTH,
							ROW_WIDTH);
				}
				else if (board[i][j] == 'w') {// 绘制白棋
					g.setColor(Color.white);
					g.fillOval(SPACE + i * ROW_WIDTH - ROW_WIDTH / 2, SPACE
							+ j * ROW_WIDTH - ROW_WIDTH / 2, ROW_WIDTH,
							ROW_WIDTH);
				}
			}
		}
		g.setColor(Color.black);
        
        
		g.drawString("剩余时间为 "+remainingTime+" 秒",getWidth()/2-50, getHeight()-20);
        
	}// 面板绘图方法结束
	
	/************************************************
	  横向找：在当前落子处(x,y)，横向查找，判断是否有连续5个相同颜色的棋子
	 ***********************************************/
	private boolean find(int x, int y) {
		int i, num = 1;
		char temp = player ? 'b' : 'w';
		for (i = x + 1; i < 15; i++) {// 向右找：统计(x,y)以右相同棋子的数量
			if (board[i][y] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		for (i = x - 1; i >= 0; i--) {// 向左找：再加上左边的棋子
			if (board[i][y] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}
 
	/************************************************
	  纵向找
	 ***********************************************/
	private boolean find2(int x, int y) {
		int i, num = 1;
		char temp = player ? 'b' : 'w';
		for (i = y + 1; i < 15; i++) { // 向下找
			if (board[x][i] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		for (i = y - 1; i >= 0; i--) {// 向上找
			if (board[x][i] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}
 
	/************************************************
	 \ 方向
	 ***********************************************/
	private boolean find3(int x, int y) {
		int i, j, num = 1;
		char temp = player ? 'b' : 'w';
		for (i = x + 1, j = y + 1; i < 15 && j < 15; i++, j++) {// 向右下方
			if (board[i][j] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {// 向左上方
			if (board[i][j] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}
 
	/************************************************
	  / 方向
	************************************************/
	private boolean find4(int x, int y) {
		int i, j, num = 1;
		char temp = player ? 'b' : 'w';
		for (i = x + 1, j = y - 1; i < 15 && j >= 0; i++, j--) {// 向右上
			if (board[i][j] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		for (i = x - 1, j = y + 1; i >= 0 && j < 15; i--, j++) { // 向左下
			if (board[i][j] == temp) {
				num++;
				if (num == 5) {
					return true;
				}
			} else {
				break;
			}
		}
		return false;
	}
 
	/************************************************
	 判断是否出现胜负，在落子处(x,y)四个方向查找，判断是否有连续5个颜色相同的棋子
	 ***********************************************/
	private boolean someoneIsWinner(int x, int y) {
		return (find(x, y) || find2(x, y) || find3(x, y) || find4(x, y));
	}
}//面板类GamePanel结束


//---------------------------------------------------------------------------------------------
class GameWindow extends JFrame{
	/**
	 * 控件属性
	 */
	private GamePanel panel=new GamePanel(); // 五子棋游戏的面板容器
	private JMenuBar bar; // 菜单栏
	private JMenu game; // 菜单（"游戏"）
	private JMenuItem[] items; // 菜单项（"重新开始"    "退出"）
	/************************************************
	   窗口构造方法：面板、菜单
	 ***********************************************/
	public GameWindow() {this("五子棋");}
	public GameWindow(String s) {
		setTitle(s);
		panel = new GamePanel(); // 面板对象
		setContentPane(panel);

		bar = new JMenuBar(); // 建立菜单栏
		game = new JMenu("游戏"); // 建立名为“游戏”的菜单
		bar.add(game);

		items = new JMenuItem[2]; // game菜单下创建2个菜单项
		game.add(items[0] = new JMenuItem("重新开始"));// 第一个菜单项为“重新开始”
		game.add(items[1] = new JMenuItem("退出")); // 第二个菜单项为“退出”
		setJMenuBar(bar);
        
		setLocation(250, 50);
		setResizable(false);
		pack();//自动调整窗口大小，往往根据面板setPreferredSize设置的最佳大小
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//发生windowClosing时，什么也不做
		setVisible(true);
        
		items[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(JOptionPane.showConfirmDialog(null, "重新开始?")==JOptionPane.YES_OPTION)
					panel.reStart();
			}
		});

        items[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(JOptionPane.showConfirmDialog(null, "退出?")==JOptionPane.YES_OPTION)
					System.exit(1);
			}
		});

	}//窗口构造方法  结束
}//窗口类GameWindow结束

//---------------------------------------------------------------------------------------------
public class FiveChess {
	/***********************************************
	程序入口
	***********************************************/
	public static void main(String[] args) {
		new GameWindow();
	}
}