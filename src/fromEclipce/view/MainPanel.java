package view;
//JWindowをマウスで移動 
//JWindowなどのタイトルバーのないフレームをマウスで移動します。

// http://terai.xrea.jp/Swing/DragWindow.html


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
public class MainPanel{
 public JTextField textCount;
  public void invoke() {
    EventQueue.invokeLater(new Runnable() {
      @Override public void run() {
        createAndShowGUI();
      }
    });
  }
  public void countSet(String cnt){
	  textCount.setText("1000");
  }
  public void countDown() {
	  System.out.println("---schedule()---");
	  Timer timer1 = new Timer();
	  timer1.schedule(new Task1(this), 0, 100); // タスクの実行間隔は1000ミリ秒
	  try {
	  Thread.sleep(50000);
	  } catch (InterruptedException ignore) {
	  }
	  timer1.cancel();
	  System.out.println("---scheduleAtFixedRate()---");
	  Timer timer2 = new Timer();
	  timer2.scheduleAtFixedRate(new Task1(this), 0, 100); // タスクの実行間隔は1000ミリ秒
	  try {
	  Thread.sleep(50000);
	  } catch (InterruptedException ignore) {
	  }
	  timer2.cancel();
	  }
	  
  /**
   * @wbp.parser.entryPoint
   */
  public  void createAndShowGUI() {
    DragWindowListener dwl = new DragWindowListener();
    final JFrame frame = new JFrame();
    frame.setUndecorated(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//　現在、全終了なので他のフォームと連動する場合は変更すること
    frame.addMouseListener(dwl);
    frame.addMouseMotionListener(dwl);
    frame.setSize(220, 40);
    frame.setLocationRelativeTo(null);
    frame.getContentPane().setLayout(null);
    
    textCount = new JTextField();
    textCount.setBounds(12, 10, 96, 19);
    frame.getContentPane().add(textCount);
    textCount.setColumns(10);
    
    JButton btnNewButton = new JButton("Fork");
    btnNewButton.setBounds(112, 9, 47, 21);
    frame.getContentPane().add(btnNewButton);
    
    JButton btnPass = new JButton("PASS");
    btnPass.setBounds(164, 9, 47, 21);
    frame.getContentPane().add(btnPass);
    frame.setVisible(true);
    

    //countStart();
    //countDown();

  }
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
class DragWindowListener extends MouseAdapter {
  private final Point startPt = new Point();
  private Window window;
  @Override public void mousePressed(MouseEvent me) {
    if(window==null) {
      Object o = me.getSource();
      if(o instanceof Window) {
        window = (Window)o;
      } else if(o instanceof JComponent) {
        window = SwingUtilities.windowForComponent(me.getComponent());
      }
    }
    startPt.setLocation(me.getPoint());
  }
  @Override public void mouseDragged(MouseEvent me) {
    if(window!=null) {
      Point eventLocationOnScreen = me.getLocationOnScreen();
      window.setLocation(eventLocationOnScreen.x - startPt.x,
                         eventLocationOnScreen.y - startPt.y);
    }
  }
    
}

class Task1 extends TimerTask {
	MainPanel mp;
private volatile int taskNum = 1;
public Task1(MainPanel mp){
	this.mp = mp;
	
}
public void run() {
System.out.println(taskNum + " " + System.currentTimeMillis());
taskNum++;
System.out.println(mp.textCount.getText());
if (Integer.parseInt((mp.textCount.getText()).toString())>0){
	Integer i = (Integer.parseInt(mp.textCount.getText()))-1;
	System.out.println("i  " +i);
	--i;
	mp.textCount.setText(i.toString());
	
}
}

}



		
		
		