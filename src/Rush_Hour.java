import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;

public class Rush_Hour extends JFrame {
    Buttons[][] gBoard; // graphic board
    Puzzle p;
    int numOfMoves;

    public Rush_Hour(int numOfMoves) {
        int rows = 6; // min 3
        int cols = 6; // min 2
        this.numOfMoves = numOfMoves;
        // int numOfCars = 7; // min 1
        // int numOfMoves = 3;
        // this.p = menu();
        this.p = new Puzzle(cols, rows, numOfMoves);
        initBoard();
        setTitle("Rush Hour");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int ratio = (int) (((double) rows / (double) cols) * 800);
        setSize(800, ratio);
        setLocationRelativeTo(null);
        setVisible(true);
        // for(int i = 1; i < 23; i++) {
        // if(p.solveable(p.lBoard, p.cars, i, 0) == true) System.out.println(i+"true");
        // else System.out.println(i+"false");
        // }
    }

    public Puzzle menu() {
        String[] options = { "Level-1", "Level-2", "Level-3", "Exit" };
        int response = JOptionPane.showOptionDialog(null, "Choose Level",
                "Starting Game Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        LinkedList<Cars> cars = new LinkedList<>();
        String colors[] = { "Red", "Yellow", "Purple", "Pink", "Orange", "Green", "Gray", "Blue", "Aqua" };
        switch (response) {
            case -1:
                System.exit(0);
            case 0:
                Puzzle p3 = new Puzzle(6, 6, 12, 3);
                return p3;
            case 1:
                Cars a1 = new Cars(2, 1, 2, 1, colors[0]);
                Cars a2 = new Cars(0, 0, 3, 0, colors[1]);
                Cars a3 = new Cars(3, 0, 2, 0, colors[2]);
                Cars a4 = new Cars(0, 1, 2, 1, colors[3]);
                Cars a5 = new Cars(4, 1, 2, 1, colors[4]);
                Cars a6 = new Cars(5, 2, 3, 1, colors[5]);
                Cars a7 = new Cars(1, 3, 3, 0, colors[6]);
                Cars a8 = new Cars(3, 5, 3, 0, colors[7]);
                cars.add(a1);
                cars.add(a2);
                cars.add(a3);
                cars.add(a4);
                cars.add(a5);
                cars.add(a6);
                cars.add(a7);
                cars.add(a8);
                Puzzle p4 = new Puzzle(6, 6, cars);
                return p4;
            case 2:
                Cars a = new Cars(0, 2, 0, 2, 1, colors[0]);
                Cars b = new Cars(1, 3, 0, 3, 0, colors[1]);
                Cars c = new Cars(2, 3, 2, 3, 0, colors[2]);
                Cars d = new Cars(3, 0, 3, 2, 0, colors[3]);
                Cars e = new Cars(4, 2, 3, 2, 0, colors[4]);
                Cars f = new Cars(5, 4, 3, 2, 1, colors[5]);
                Cars g = new Cars(6, 4, 5, 2, 0, colors[6]);
                Cars h = new Cars(7, 1, 4, 2, 1, colors[7]);
                Cars i = new Cars(8, 3, 4, 2, 1, colors[8]);
                cars.add(a);
                cars.add(b);
                cars.add(c);
                cars.add(d);
                cars.add(e);
                cars.add(f);
                cars.add(g);
                cars.add(h);
                cars.add(i);
                Puzzle p1 = new Puzzle(6, 6, cars);
                return p1;
            case 3:
                System.exit(0);

            default:
                Puzzle p5 = new Puzzle(6, 6, cars);
                return p5;
        }

    }

    public void initBoard() {
        gBoard = new Buttons[p.gridSizeY][p.gridSizeX];
        p.lBoard = new int[p.gridSizeY][p.gridSizeX];
        ImageIcon board = new ImageIcon("Board.png");
        Image Board = board.getImage();

        ImageIcon board_select = new ImageIcon("Board_Select.png");
        Image Board_Select = board_select.getImage();

        setLayout(new GridLayout(p.gridSizeY, p.gridSizeX));
        Border emptyBorder = BorderFactory.createEmptyBorder();
        for (int i = 0; i < p.gridSizeY; i++) {
            for (int j = 0; j < p.gridSizeX; j++) {
                p.lBoard[i][j] = -1;
                gBoard[i][j] = new Buttons(Board);
                gBoard[i][j].setBorder(emptyBorder);
                gBoard[i][j].addActionListener(new AL(i, j));
                int a = i, b = j;
                gBoard[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if (gBoard[a][b].getImg() == Board)
                            gBoard[a][b].setImg(Board_Select);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        if (gBoard[a][b].getImg() == Board_Select)
                            gBoard[a][b].setImg(Board);
                    }
                });

                add(gBoard[i][j]);
            }
        }

        for (int i = 0; i < p.cars.size(); i++)

            switch (p.cars.get(i).direction) {

                case 0:
                    for (int j = 0; j < p.cars.get(i).size; j++) {

                        ImageIcon down_icon = new ImageIcon(
                                "Cars_Images/" + p.cars.get(i).color + "/" + p.cars.get(i).color + "_Down.png");
                        Image Down = down_icon.getImage();
                        ImageIcon middle_up_icon = new ImageIcon(
                                "Cars_Images/" + p.cars.get(i).color + "/" + p.cars.get(i).color + "_Middle_Up.png");
                        Image Middle_Up = middle_up_icon.getImage();
                        ImageIcon up_icon = new ImageIcon(
                                "Cars_Images/" + p.cars.get(i).color + "/" + p.cars.get(i).color + "_Up.png");
                        Image Up = up_icon.getImage();

                        p.cars.get(i).index = i;
                        p.lBoard[p.cars.get(i).y + j][p.cars.get(i).x] = i;
                        if (j == 0)
                            gBoard[p.cars.get(i).y][p.cars.get(i).x].setImg(Up);
                        else if (j == p.cars.get(i).size - 1)
                            gBoard[p.cars.get(i).y + (p.cars.get(i).size - 1)][p.cars.get(i).x].setImg(Down);
                        else
                            gBoard[p.cars.get(i).y + j][p.cars.get(i).x].setImg(Middle_Up);
                    }
                    break;

                case 1:
                    for (int j = 0; j < p.cars.get(i).size; j++) {
                        ImageIcon left_icon = new ImageIcon(
                                "Cars_Images/" + p.cars.get(i).color + "/" + p.cars.get(i).color + "_Left.png");
                        Image Left = left_icon.getImage();
                        ImageIcon middle_side_icon = new ImageIcon(
                                "Cars_Images/" + p.cars.get(i).color + "/" + p.cars.get(i).color + "_Middle_Side.png");
                        Image Middle_Side = middle_side_icon.getImage();
                        ImageIcon right_icon = new ImageIcon(
                                "Cars_Images/" + p.cars.get(i).color + "/" + p.cars.get(i).color + "_Right.png");
                        Image Right = right_icon.getImage();

                        p.cars.get(i).index = i;
                        p.lBoard[p.cars.get(i).y][p.cars.get(i).x + j] = i;
                        if (j == 0)
                            gBoard[p.cars.get(i).y][p.cars.get(i).x].setImg(Left);
                        else if (j == p.cars.get(i).size - 1)
                            gBoard[p.cars.get(i).y][p.cars.get(i).x + (p.cars.get(i).size - 1)].setImg(Right);
                        else
                            gBoard[p.cars.get(i).y][p.cars.get(i).x + j].setImg(Middle_Side);
                    }
            }

    }

    public void update_board(Cars c, int direction) {
        ImageIcon board = new ImageIcon("Board.png");
        Image Board = board.getImage();

        ImageIcon down_icon = new ImageIcon("Cars_Images/" + c.color + "/" + c.color + "_Down.png");
        Image Down = down_icon.getImage();
        ImageIcon middle_up_icon = new ImageIcon("Cars_Images/" + c.color + "/" + c.color + "_Middle_Up.png");
        Image Middle_Up = middle_up_icon.getImage();
        ImageIcon up_icon = new ImageIcon("Cars_Images/" + c.color + "/" + c.color + "_Up.png");
        Image Up = up_icon.getImage();

        ImageIcon left_icon = new ImageIcon("Cars_Images/" + c.color + "/" + c.color + "_Left.png");
        Image Left = left_icon.getImage();
        ImageIcon middle_side_icon = new ImageIcon("Cars_Images/" + c.color + "/" + c.color + "_Middle_Side.png");
        Image Middle_Side = middle_side_icon.getImage();
        ImageIcon right_icon = new ImageIcon("Cars_Images/" + c.color + "/" + c.color + "_Right.png");
        Image Right = right_icon.getImage();

        switch (direction) {

            case 0:
                p.lBoard[c.y + c.size][c.x] = -1;
                gBoard[c.y + c.size][c.x].setImg(Board);
                gBoard[c.y + c.size][c.x].repaint();
                p.lBoard[c.y + c.size - 1][c.x] = c.index;
                gBoard[c.y + c.size - 1][c.x].setImg(Down);
                gBoard[c.y + c.size - 1][c.x].repaint();

                for (int j = c.y + 1; j < c.y + c.size - 1; j++) {
                    gBoard[j][c.x].setImg(Middle_Up);
                    gBoard[j][c.x].repaint();
                    p.lBoard[j][c.x] = c.index;
                }
                p.lBoard[c.y][c.x] = c.index;
                gBoard[c.y][c.x].setImg(Up);
                gBoard[c.y][c.x].repaint();
                break;

            case 1:
                p.lBoard[c.y - 1][c.x] = -1;
                gBoard[c.y - 1][c.x].setImg(Board);
                gBoard[c.y - 1][c.x].repaint();

                p.lBoard[c.y][c.x] = c.index;
                gBoard[c.y][c.x].setImg(Up);
                gBoard[c.y][c.x].repaint();
                for (int j = 1; j < c.size - 1; j++) {
                    p.lBoard[c.y + j][c.x] = c.index;
                    gBoard[c.y + j][c.x].setImg(Middle_Up);
                    gBoard[c.y + j][c.x].repaint();
                }
                p.lBoard[c.y + c.size - 1][c.x] = c.index;
                gBoard[c.y + c.size - 1][c.x].setImg(Down);
                gBoard[c.y + c.size - 1][c.x].repaint();
                break;

            case 2:
                p.lBoard[c.y][c.x + c.size] = -1;
                gBoard[c.y][c.x + c.size].setImg(Board);
                gBoard[c.y][c.x + c.size].repaint();
                p.lBoard[c.y][c.x + c.size - 1] = c.index;
                gBoard[c.y][c.x + c.size - 1].setImg(Right);
                gBoard[c.y][c.x + c.size - 1].repaint();
                for (int j = c.x + 1; j < c.x + c.size - 1; j++) {
                    gBoard[c.y][j].setImg(Middle_Side);
                    gBoard[c.y][j].repaint();
                    p.lBoard[c.y][j] = c.index;
                }
                p.lBoard[c.y][c.x] = c.index;
                gBoard[c.y][c.x].setImg(Left);
                gBoard[c.y][c.x].repaint();
                break;

            case 3:
                p.lBoard[c.y][c.x - 1] = -1;
                gBoard[c.y][c.x - 1].setImg(Board);
                gBoard[c.y][c.x - 1].repaint();

                p.lBoard[c.y][c.x] = c.index;
                gBoard[c.y][c.x].setImg(Left);
                gBoard[c.y][c.x].repaint();
                for (int j = 1; j < c.size - 1; j++) {
                    p.lBoard[c.y][c.x + j] = c.index;
                    gBoard[c.y][c.x + j].setImg(Middle_Side);
                    gBoard[c.y][c.x + j].repaint();
                }
                p.lBoard[c.y][c.x + c.size - 1] = c.index;
                gBoard[c.y][c.x + c.size - 1].setImg(Right);
                gBoard[c.y][c.x + c.size - 1].repaint();
        }

    }

    class AL implements ActionListener {
        private int row;
        private int col;

        public AL(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {

            if (p.lBoard[row][col] != -1) {
                Buttons.state = p.lBoard[row][col];
            } else if (Buttons.state != -1) {
                int steps;
                if (p.cars.get(Buttons.state).direction == 0) {
                    if (p.cars.get(Buttons.state).x == col) {
                        if (p.cars.get(Buttons.state).y < row) {
                            steps = row - (p.cars.get(Buttons.state).y + p.cars.get(Buttons.state).size - 1);
                            for (int i = p.cars.get(Buttons.state).y + p.cars.get(Buttons.state).size; i < row; i++)
                                if (p.lBoard[i][col] != -1)
                                    steps = 0;
                            for (int j = 0; j < steps; j++) {
                                p.cars.get(Buttons.state).y++;
                                update_board(p.cars.get(Buttons.state), 1);
                            }
                        } else {
                            steps = p.cars.get(Buttons.state).y - row;
                            for (int i = p.cars.get(Buttons.state).y - 1; i > row; i--)
                                if (p.lBoard[i][col] != -1)
                                    steps = 0;
                            for (int j = 0; j < steps; j++) {
                                p.cars.get(Buttons.state).y--;
                                update_board(p.cars.get(Buttons.state), 0);
                            }
                        }
                    }

                } else {
                    if (p.cars.get(Buttons.state).y == row) {
                        if (p.cars.get(Buttons.state).x < col) {
                            steps = col - (p.cars.get(Buttons.state).x + p.cars.get(Buttons.state).size - 1);
                            for (int i = p.cars.get(Buttons.state).x + p.cars.get(Buttons.state).size; i < col; i++)
                                if (p.lBoard[row][i] != -1)
                                    steps = 0;
                            for (int j = 0; j < steps; j++) {
                                p.cars.get(Buttons.state).x++;
                                update_board(p.cars.get(Buttons.state), 3);
                            }
                        } else {
                            steps = p.cars.get(Buttons.state).x - col;
                            for (int i = p.cars.get(Buttons.state).x - 1; i > col; i--)
                                if (p.lBoard[row][i] != -1)
                                    steps = 0;
                            for (int j = 0; j < steps; j++) {
                                p.cars.get(Buttons.state).x--;
                                update_board(p.cars.get(Buttons.state), 2);
                            }

                        }
                    }
                }
                if (p.lBoard[(p.gridSizeY / 2) - 1][p.gridSizeX - 1] == 0) {
                    JOptionPane.showMessageDialog(Rush_Hour.this, "You Won!");
                    dispose();
                    System.out.println(numOfMoves + 1);
                    new Rush_Hour(++numOfMoves);
                }

                Buttons.state = -1;
            }

        }
    }

    public static void main(String[] args) {
        new Rush_Hour(1);
        // new Menu();
    }
}
