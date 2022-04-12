import java.util.Iterator;
import java.util.LinkedList;

public class Node {
int board[][];
LinkedList<Cars> cars;
Node father;
int depth;

public Node(int board[][], LinkedList<Cars> cars) {
	this.board = new int[board.length][board[0].length];
	for (int i = 0; i < board.length; i++) {
		for (int j = 0; j < board[0].length; j++) {
			this.board[i][j] = board[i][j];
		}
	}
	
	this.cars = new LinkedList<Cars>();
	Iterator<Cars> p = cars.iterator();
	while(p.hasNext()) {
		this.cars.add(new Cars(p.next()));
	}
	this.father = null;
	this.depth = 0;
}

public Node(Node node, Node father) {
	this.board = new int[node.board.length][node.board[0].length];
	for (int i = 0; i < node.board.length; i++) {
		for (int j = 0; j < node.board[0].length; j++) {
			this.board[i][j] = node.board[i][j];
		}
	}
	
	this.cars = new LinkedList<Cars>();
	Iterator<Cars> p = node.cars.iterator();
	while(p.hasNext()) {
		this.cars.add(new Cars(p.next()));
	}
	this.father = father;
	this.depth = node.depth;
}
public Node(Node node) {
	this.board = new int[node.board.length][node.board[0].length];
	for (int i = 0; i < node.board.length; i++) {
		for (int j = 0; j < node.board[0].length; j++) {
			this.board[i][j] = node.board[i][j];
		}
	}
	
	this.cars = new LinkedList<Cars>();
	Iterator<Cars> p = node.cars.iterator();
	while(p.hasNext()) {
		this.cars.add(new Cars(p.next()));
	}
	this.father = node;
	this.depth = node.depth + 1;
}
}
