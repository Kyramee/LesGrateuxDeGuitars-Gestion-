import controler.controlerSysteme;
import vue.vueJFrame;

public class MainTemp {

	public static void main(String[] args) {
		controlerSysteme gA = new controlerSysteme();
		
		if(gA.checkUser("Groot", "root")) {
			System.out.println("ok");
			vueJFrame v = new vueJFrame();
			v.init();
		} else {
			System.out.println("not ok");
		}
	}
}
