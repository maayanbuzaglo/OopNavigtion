package Packman_Game;

import java.io.IOException;
import java.util.ArrayList;
import Coords.Coords;
import GUI.MyFrame;
import Packman_Game.Game;

/*
 * This class represents an efficient algorithm which computes the path of every pacman.
 */
public class ShortestPathAlgo {

	public static ArrayList<Path> closestFruit(Game g) throws IOException {

		double time;
		Fruit fruitEaten = null;
		Coords c = new Coords();
		Packman_Game.Line l = null;
		ArrayList<Fruit> fruitLeft = new ArrayList<Fruit>(); //list of the fruits left. 
		ArrayList<Path> path_List = new ArrayList<Path>();
		ArrayList<SaveData> data_List;
		for(Fruit it: g.getFruit_list()) {
			fruitLeft.add(it);
		}
		for (int i = 0; i < g.Pacman_list.size(); i++) {
			Path path = new Path(g.getPacman_list().get(i));
			path_List.add(path);
		}
		while (!fruitLeft.isEmpty()) { //until there are no more fruits to eat.
			int indexFruit = 0;
			int indexPacman = 0;
			data_List = new ArrayList<SaveData>();
			for(int i = 0; i < g.Pacman_list.size(); i++) {
				double minTime = Double.MAX_VALUE;
				for(int j = 0; j < fruitLeft.size(); j++) {
					double speedP = g.Pacman_list.get(i).getSpeed(); 
					time = c.distance2d(fruitLeft.get(j).getLocation(), g.Pacman_list.get(i).getLocation()) / speedP; //computes the time.
					if((time + g.Pacman_list.get(i).getTime()) < minTime) {
						fruitEaten = new Fruit(fruitLeft.get(j));
						minTime = time + g.getPacman_list().get(i).getTime();
						indexFruit = j;
					}
				}
				data_List.add(new SaveData(g.getPacman_list().get(i), fruitEaten, minTime, indexFruit)); //the closest fruit to pacman i.
			}

			SaveData eatData = new SaveData(data_List.get(0));

			//checks what pacman should eat.
			for (int i = 0; i < data_List.size(); i++) {
				if (eatData.getTime() >= data_List.get(i).getTime()) {
					eatData.setPacman(data_List.get(i).getPacman());
					eatData.setFruit(data_List.get(i).getFruit());
					eatData.setTime(data_List.get(i).getTime());
					eatData.setIndexFruit(data_List.get(i).getIndexFruit());
					indexPacman = i;
				}
			}
			l = new Packman_Game.Line(eatData.getPacman().getLocation(), fruitLeft.get(eatData.getIndexFruit()).getLocation());
			g.Line_list.add(l);
			g.Pacman_list.get(indexPacman).setTime(eatData.getTime()); //sets the time of the pacman that ate.
			g.Pacman_list.get(indexPacman).setLocation(fruitLeft.get(eatData.getIndexFruit()).getLocation()); //moves the pacman to the location of the fruit.
			g.getFruit_list().remove(eatData.getIndexFruit());
			fruitLeft.remove(eatData.getIndexFruit()); // removes the fruit.
			MyFrame frame = new MyFrame(g);
			System.out.println(eatData.toString());
		}
		return null;
	}

//	/*
//	 * Example.
//	 */
//	public static void main(String[] args) throws IOException {
//
//		Game g = new Game();
//		g.readCsv("C:\\Users\\�����\\eclipse-workspace\\OopNavigtion\\data\\game_1543693822377.csv");
//		for (Pacman it1: g.Pacman_list) {
//			System.out.println(it1.toString());
//		}
//		for (Fruit it: g.Fruit_list) {
//			System.out.println(it.toString());
//		}
//		g.Line_list = new ArrayList<Line>();
//		closestFruit(g);
//	}
	
}

