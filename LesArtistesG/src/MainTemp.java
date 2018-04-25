import controler.controlerArtisteAlbum;
import vue.vueJFrame;

public class MainTemp {

	public static void main(String[] args) {
		controlerArtisteAlbum gA = new controlerArtisteAlbum();
		
		if(gA.checkUser("Groot", "root")) {
			System.out.println("ok");
			vueJFrame v = new vueJFrame();
			v.init();
		} else {
			System.out.println("not ok");
		}
	}
}
