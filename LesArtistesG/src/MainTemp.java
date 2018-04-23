
public class MainTemp {

	public static void main(String[] args) {
		gestionArtistes gA = new gestionArtistes();
		
		if(gA.checkUser("Groot", "root")) {
			System.out.println("ok");
			vueJFrame v = new vueJFrame();
			v.setVisible(true);
			
		} else {
			System.out.println("not ok");
		}
	}
}
