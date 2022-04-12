import java.awt.Image;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Random;
import java.util.Date;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.ImageIcon;
public class Puzzle {
	int gridSizeX;
	int gridSizeY;
	LinkedList<Cars> cars;
	int lBoard[][];
	
	public Puzzle(Puzzle p) {
		this.gridSizeX = p.gridSizeX;
		this.gridSizeY = p.gridSizeY;
		LinkedList<Cars> cars = new LinkedList<>();
		this.cars = cars;
		for(int i = 0; i < p.cars.size(); i++) {
			this.cars.add(new Cars(p.cars.get(i)));
		}
	}
	
	public Puzzle(int gridSizeX, int gridSizeY, LinkedList<Cars> cars) {
		this.gridSizeX = gridSizeX;
		this.gridSizeY = gridSizeY;
		this.cars = cars;
		lBoard = new int[gridSizeY][gridSizeX];
		
		for(int k = 0; k < gridSizeY; k++) {
			for(int kk = 0; kk < gridSizeX; kk++) {
				lBoard[k][kk] = -1;
			}
		}
		
		for(int i = 0; i<cars.size();i++) { 
			if(cars.get(i).direction == 0) {
					for(int j = 0; j < cars.get(i).size; j++) {
						cars.get(i).index = i;
						lBoard[cars.get(i).y + j][cars.get(i).x] = (cars.get(i).index);
					}
				}
				
				else {
					for(int j = 0; j < cars.get(i).size; j++) {
						cars.get(i).index = i;
						lBoard[cars.get(i).y][cars.get(i).x + j] = (cars.get(i).index);
					}
					}
			}
		//System.out.println(numOfMoves(150));
		solve();
	}
		
	public Puzzle(int gridSizeX, int gridSizeY, int numOfmoves) {
		this.gridSizeX = gridSizeX;
		this.gridSizeY = gridSizeY;
		LinkedList<Cars> cars = new LinkedList<>();
		this.cars = cars;
		lBoard = new int[gridSizeY][gridSizeX];
		for(int i = 0; i < gridSizeY; i++) {
			for(int j = 0; j < gridSizeX; j++)
				lBoard[i][j] = -1;
		}

		int numOfCars = 1;
		while(1==1) {
			for(int i = 0; i < 10; i++) {
				this.cars.clear();
				this.cars = carsList(numOfCars);
				if(numOfMoves(numOfmoves) == numOfmoves)
					return;
			}
			if(numOfCars > 12) numOfCars = 2;
			numOfCars++;
		}
	}

	public LinkedList<Cars> carsList(int numOfCars){
		for(int i = 0; i < gridSizeY; i++) {
			for(int j = 0; j < gridSizeX; j++)
				lBoard[i][j] = -1;
		}
		Random red = new Random();
		Cars redCar = new Cars(gridSizeY/2 - 1, red.nextInt(gridSizeX-2), 2, 1, "Red");
		cars.add(redCar);
		lBoard[redCar.y][redCar.x] = lBoard[redCar.y][redCar.x + 1] = 0;
		int limit = (gridSizeX*gridSizeY);
		int Carscounter = numOfCars;
		while(Carscounter > 0) {
			limit--;
			if(limit == 0) {
				Carscounter = numOfCars;
				limit = (gridSizeX*gridSizeY);
				cars.clear();
				cars.add(redCar);
				for(int i = 0; i < gridSizeY; i++) {
					for(int j = 0; j < gridSizeX; j++)
						lBoard[i][j] = -1;
				}
				lBoard[redCar.y][redCar.x] = lBoard[redCar.y][redCar.x + 1] = 0;
			}
			Cars temp = addCar(Carscounter);
			if(temp != null) {
				cars.add(temp);	
				Carscounter--;
				limit = (gridSizeX*gridSizeY);
			}
		}
		return cars;
	}

	public Cars addCar(int index) {
		String colors[] = {"Yellow", "DarkGreen", "DarkGray", "Orange", "Green", "Gray", "Blue", "Aqua"};
		Random y = new Random();
		Random x = new Random();
		Random d = new Random();
		Random s = new Random();
		int size, i, j;
		if(s.nextInt(3) == 0) size = 3;
		else size = 2;
		Cars temp = new Cars(index, y.nextInt(gridSizeY), x.nextInt(gridSizeX), size, d.nextInt(2), colors[d.nextInt(8)]);
		if(temp.y == gridSizeY/2 - 1 && temp.direction == 1) return null;
		switch(temp.direction) {
		case 0:
			if(temp.y + temp.size > gridSizeY) return null;
			for(i = 0; i < temp.size; i++) {
				if(lBoard[temp.y + i][temp.x] != -1)
					return null;
			}
			for(j = 0; j < temp.size; j++)
				lBoard[temp.y +j][temp.x] = index;
			break;
		case 1:
			if(temp.x + temp.size > gridSizeX) return null;
			for(i = 0; i < temp.size; i++) {
				if(lBoard[temp.y][temp.x + i] != -1)
					return null;
			}
			for(j = 0; j < temp.size; j++)
				lBoard[temp.y][temp.x+j] = index;
			break;
		}
			return temp;
	}

	public void show_solution(Node node) {
		while(node != null) {
			for (int i = 0; i < node.board.length; i++) {
				for (int j = 0; j < node.board[0].length; j++) {
					if(node.board[i][j] == -1)
						System.out.print("X ");
					else
					System.out.print(node.board[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			node = node.father;
		}
	}
	
	public boolean win(Node node) {
		for(int i = node.cars.get(0).x + 2; i < gridSizeX; i++)
			if(node.board[node.cars.get(0).y][i] != -1) 
				return false;
		return true;
	}

	public LinkedList<Node> solveable(Node node) {
		int steps;
		Cars car;
		Node temp;
		LinkedList<Node> list = new LinkedList<Node>();
		for(int car_index = 0; car_index < node.cars.size(); car_index++) {
			car = node.cars.get(car_index);
			if(car.direction == 0) {
				//Down
				temp = new Node(node);
				for(steps = car.y + car.size; steps < gridSizeY; steps++) {
					temp = new Node(temp, node);
					car = temp.cars.get(car_index);
					if(temp.board[steps][car.x] != -1) break;
					temp.board[steps-car.size][car.x] = -1;
					temp.board[steps][car.x] = car.index;
					car.y++;
					list.add(temp);
					}
			car = node.cars.get(car_index);
				//Up
				temp = new Node(node);
				for(steps = car.y - 1; steps >= 0; steps--) {
					temp = new Node(temp, node);
					car = temp.cars.get(car_index);
					if(temp.board[steps][car.x] != -1) break;
					temp.board[steps + car.size][car.x] = -1;
					temp.board[steps][car.x] = car.index;
					car.y--;
					list.add(temp);
					}
			}
			else {
				car = node.cars.get(car_index);
				//Right
				temp = new Node(node);
				for(steps = car.x + car.size; steps < gridSizeX; steps++) {
					temp = new Node(temp, node);
					car = temp.cars.get(car_index);
					if(temp.board[car.y][steps] != -1) break;
					temp.board[car.y][steps-car.size] = -1;
					temp.board[car.y][steps] = car.index;
					car.x++;
					list.add(temp);
				}
				car = node.cars.get(car_index);
				//Left
				temp = new Node(node);
				for(steps = car.x - 1; steps >= 0; steps--) {
					temp = new Node(temp, node);
					car = temp.cars.get(car_index);
					if(temp.board[car.y][steps] != -1) break;
					temp.board[car.y][steps + car.size] = -1;
					temp.board[car.y][steps] = car.index;
					car.x--;
					list.add(temp);
				}
			}
		}
		return list;
	}

	public void solve() {
		Instant time = Instant.now();
		LinkedList<Node> boards = new LinkedList<Node>();
		LinkedList<Node> new_boards;
		Iterator<Node> p1;
		Iterator<Node> p2;
		Node temp;
		boolean flag;
		//int max = 0;
		boards.add(new Node(lBoard, cars));
		Node current;
		for(int i = 0; i < boards.size(); i++){
			current = boards.get(i);
			//if(boards.get(i).depth > max) {
			//	max = boards.get(i).depth;
			//System.out.println(boards.size());
			//}
			if(win(current)) {
				//show_solution(boards.get(i));
				System.out.println(Duration.between(time, Instant.now()));
				return;
			}
			new_boards = solveable(current);
			//boards.removeFirst();
			p1 = new_boards.iterator();
			while(p1.hasNext()) {
				flag = true;
				temp = p1.next();
				p2 = boards.iterator();
				while(p2.hasNext()) {
					if(Arrays.deepEquals(p2.next().board, temp.board)) {
							flag = false;
							break;
					}
				}
				if(flag == true)
					boards.addLast(temp);
			}
		}
	}
	
	public int numOfMoves(int num_of_moves) {
		Instant time = Instant.now();
		LinkedList<Node> boards = new LinkedList<Node>();
		LinkedList<Node> new_boards;
		Node start = new Node(lBoard, cars);
		boards.add(start);
		for(int i = 0; i < boards.size(); i++){
			if(boards.get(i).depth > num_of_moves) return -1;
			new_boards = solveable(boards.get(i));
			if(win(boards.get(i))) {
				return boards.get(i).depth;
			}
			//boards.removeFirst();
			Iterator<Node> p1 = new_boards.iterator();
			Iterator<Node> p2;
			Node temp;
			boolean flag;
			while(p1.hasNext()) {
				flag = true;
				temp = p1.next();
				p2 = boards.iterator();
				while(p2.hasNext()) {
					if(Arrays.deepEquals(p2.next().board, temp.board))
							flag = false;
				}
				if(flag == true)
					boards.addLast(temp);
			}
		}
		return -1;
	}
}

