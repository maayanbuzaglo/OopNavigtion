package GUI;

import java.util.ArrayList;
import javax.swing.JFrame;
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Pacman;

public class Main {

	@SuppressWarnings("null")
	public static void main(String[] args) {

		//		Pacman p = new Pacman();
		//		p.setRadius(1);
		//		p.setSpeed(2);
		//		p.setID(2);
		//		ArrayList<Pacman> Pacman_list = new ArrayList<>();
		//		ArrayList<Fruit> Fruit_list = new ArrayList<>();
		//		Pacman_list.add(p);
		//		Game g = new Game(Pacman_list, Fruit_list);
		//
		//		g.createCSV("C:\\Users\\�����\\Desktop\\data\\try.csv");
		//		
		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.background.getWidth(), window.background.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//		Pacman p = new Pacman();
		//		ArrayList<Pacman> Csv = p.ReadCsvFile("C:\\Users\\�����\\Desktop\\data\\game_1543684662657.csv");
		//		for (Pacman it: Csv) {
		//			System.out.println(it.toString());
		//		}

		//		Fruit f = new Fruit();
		//		ArrayList<Fruit> Csv = f.ReadCsvFile("C:\\Users\\�����\\Desktop\\data\\game_1543684662657.csv");
		//		for (Fruit it: Csv) {
		//			System.out.println(it.toString());
		//		}
		//		r.createKML(Csv,"C:\\Users\\�����\\Desktop\\Ex2\\data\\f.kml");

		//				Game g = new Game("C:\\Users\\�����\\Desktop\\Ex3\\data\\game_1543684662657.csv");
		//				for (Pacman it1: g.Pacman_list) {
		//					System.out.println(it1.toString());
		//				}
		//				for (Fruit it: g.Fruit_list) {
		//					System.out.println(it.toString());
		//	}
		//		g.readCsv(g);
		//		g.createKML(g, "C:\\Users\\�����\\Desktop\\data\\try2.kml");
		//		g.Game2Csv(g);
	}

}
