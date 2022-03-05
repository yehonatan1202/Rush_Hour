import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Puzzle {
    int gridSizeX;
    int gridSizeY;
    LinkedList<Cars> cars;
    int lBoard[][];

    public Puzzle(int gridSizeX, int gridSizeY, LinkedList<Cars> cars) {
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.cars = cars;
        lBoard = new int[gridSizeY][gridSizeX];

        for (int k = 0; k < gridSizeY; k++) {
            for (int kk = 0; kk < gridSizeX; kk++) {
                lBoard[k][kk] = -1;
            }
        }

        for (int i = 0; i < cars.size(); i++) {
            switch (cars.get(i).direction) {
                case 0:
                    for (int j = 0; j < cars.get(i).size; j++) {
                        cars.get(i).index = i;
                        lBoard[cars.get(i).y + j][cars.get(i).x] = (cars.get(i).index);
                    }
                    break;

                case 1:
                    for (int j = 0; j < cars.get(i).size; j++) {
                        cars.get(i).index = i;
                        lBoard[cars.get(i).y][cars.get(i).x + j] = (cars.get(i).index);
                    }
            }
        }
    }

    public Puzzle(int gridSizeX, int gridSizeY, int numOfCars, int numOfmoves) {
        if ((gridSizeX * gridSizeY) / 2 < numOfCars + 1) {
            System.out.println("too many cars");
            System.exit(1);
        }
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        LinkedList<Cars> cars = new LinkedList<>();
        lBoard = new int[gridSizeY][gridSizeX];
        for (int i = 0; i < gridSizeY; i++) {
            for (int j = 0; j < gridSizeX; j++)
                lBoard[i][j] = -1;
        }
        boolean ready = false;
        while (!ready) {
            cars.clear();
            System.out.println();
            ready = true;
            this.cars = cars;
            this.cars = carsList(numOfCars);

            for (int i = 0; i < numOfmoves; i++) {
                if (solveable(lBoard, cars, i, 0) == true) {
                    ready = false;
                    break;
                }

                if (solveable(lBoard, cars, numOfmoves, 0) == false) {
                    ready = false;
                    continue;
                }
            }
        }
    }

    public Puzzle(int gridSizeX, int gridSizeY, int numOfmoves) {
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        LinkedList<Cars> cars = new LinkedList<>();
        this.cars = cars;
        lBoard = new int[gridSizeY][gridSizeX];
        for (int i = 0; i < gridSizeY; i++) {
            for (int j = 0; j < gridSizeX; j++)
                lBoard[i][j] = -1;
        }

        int numOfCars = numOfmoves;
        while (1 == 1) {
            for (int i = 0; i < 10; i++) {
                this.cars.clear();
                this.cars = carsList(numOfCars);
                if ((solveable(lBoard, cars, numOfmoves - 2, 0) == false)
                        && (solveable(lBoard, cars, numOfmoves - 1, 0) == false)
                        && (solveable(lBoard, cars, numOfmoves, 0) == true)) {
                    return;
                }
            }
            if (numOfCars > 12)
                numOfCars = numOfmoves;
            numOfCars++;
        }
    }

    public LinkedList<Cars> carsList(int numOfCars) {
        for (int i = 0; i < gridSizeY; i++) {
            for (int j = 0; j < gridSizeX; j++)
                lBoard[i][j] = -1;
        }
        Random red = new Random();
        Cars redCar = new Cars(gridSizeY / 2 - 1, red.nextInt(gridSizeX - 2), 2, 1, "Red");
        cars.add(redCar);
        lBoard[redCar.y][redCar.x] = lBoard[redCar.y][redCar.x + 1] = 0;
        int limit = (gridSizeX * gridSizeY);
        int Carscounter = numOfCars;
        while (Carscounter > 0) {
            limit--;
            if (limit == 0) {
                Carscounter = numOfCars;
                limit = (gridSizeX * gridSizeY);
                cars.clear();
                cars.add(redCar);
                for (int i = 0; i < gridSizeY; i++) {
                    for (int j = 0; j < gridSizeX; j++)
                        lBoard[i][j] = -1;
                }
                lBoard[redCar.y][redCar.x] = lBoard[redCar.y][redCar.x + 1] = 0;
            }
            Cars temp = addCar();
            if (temp != null) {
                cars.add(temp);
                Carscounter--;
                limit = (gridSizeX * gridSizeY);
            }
        }
        return cars;
    }

    public Cars addCar() {
        String colors[] = { "Yellow", "Purple", "Pink", "Orange", "Green", "Gray", "Blue", "Aqua" };
        Random y = new Random();
        Random x = new Random();
        Random d = new Random();
        Random s = new Random();
        int size, i, j;
        if (s.nextInt(3) == 0)
            size = 3;
        else
            size = 2;
        Cars temp = new Cars(y.nextInt(gridSizeY), x.nextInt(gridSizeX), size, d.nextInt(2), colors[d.nextInt(8)]);
        if (temp.y == gridSizeY / 2 - 1 && temp.direction == 1)
            return null;
        switch (temp.direction) {
            case 0:
                if (temp.y + temp.size > gridSizeY)
                    return null;
                for (i = 0; i < temp.size; i++) {
                    if (lBoard[temp.y + i][temp.x] != -1)
                        return null;
                }
                for (j = 0; j < temp.size; j++)
                    lBoard[temp.y + j][temp.x] = 0;
                break;
            case 1:
                if (temp.x + temp.size > gridSizeX)
                    return null;
                for (i = 0; i < temp.size; i++) {
                    if (lBoard[temp.y][temp.x + i] != -1)
                        return null;
                }
                for (j = 0; j < temp.size; j++)
                    lBoard[temp.y][temp.x + j] = 1;
                break;
        }
        return temp;
    }

    long arrToLong(int arr[][]) {
        long num = 0;
        for (int i = 0; i < gridSizeY; i++) {
            for (int j = 0; j < gridSizeX; j++) {
                num += arr[i][j] * Math.pow(2, (i * 6 + j));
            }
        }
        return num;
    }

    int getplace(int arr, int y, int x) {
        return (((arr >> (5 * 6 - x)) >> 5 - 3) & y);
    }

    public boolean win(int tempBoard[][], LinkedList<Cars> tempCars) {
        for (int i = tempCars.get(0).x + 2; i < gridSizeX; i++)
            if (tempBoard[tempCars.get(0).y][i] != -1)
                return false;
        return true;
    }

    public boolean solveable(int Board[][], LinkedList<Cars> CarsList, int moves, int sum) { // first solution O(n ^ m)
                                                                                             // n - legal moves, m -
                                                                                             // number of moves
        int steps, i;
        Cars car;
        // Cars
        LinkedList<Cars> tempCars = new LinkedList<Cars>();
        for (i = 0; i < CarsList.size(); i++)
            tempCars.add(new Cars(CarsList.get(i)));
        // Matrix
        int[][] tempBoard = new int[gridSizeY][];
        for (i = 0; i < gridSizeY; i++)
            tempBoard[i] = Board[i].clone();
        if (moves <= 0) {
            if (win(tempBoard, tempCars) == true) {
                return true;
            } else
                return false;
        }
        for (int car_index = 0; car_index < tempCars.size(); car_index++) {
            car = tempCars.get(car_index);
            if (car.direction == 0) {
                // Down
                for (steps = car.y + car.size; steps < gridSizeY; steps++) {
                    if (tempBoard[steps][car.x] != -1)
                        break;
                    sum++;
                    tempBoard[steps - car.size][car.x] = -1;
                    tempBoard[steps][car.x] = car.direction;
                    car.y++;
                    if (solveable(tempBoard, tempCars, moves - 1, sum))
                        return true;
                }
                // Clear
                tempCars.clear();
                for (i = 0; i < CarsList.size(); i++)
                    tempCars.add(new Cars(CarsList.get(i)));
                car = tempCars.get(car_index);
                for (i = 0; i < gridSizeY; i++)
                    tempBoard[i] = Board[i].clone();
                // Up
                for (steps = car.y - 1; steps >= 0; steps--) {
                    if (tempBoard[steps][car.x] != -1)
                        break;
                    sum++;
                    tempBoard[steps + car.size][car.x] = -1;
                    tempBoard[steps][car.x] = car.direction;
                    car.y--;
                    if (solveable(tempBoard, tempCars, moves - 1, sum))
                        return true;
                }
            } else {
                // Right
                for (steps = car.x + car.size; steps < gridSizeX; steps++) {
                    if (tempBoard[car.y][steps] != -1)
                        break;
                    sum++;
                    tempBoard[car.y][steps - car.size] = -1;
                    tempBoard[car.y][steps] = car.direction;
                    car.x++;
                    if (solveable(tempBoard, tempCars, moves - 1, sum))
                        return true;
                }
                // Clear
                tempCars.clear();
                for (i = 0; i < CarsList.size(); i++)
                    tempCars.add(new Cars(CarsList.get(i)));
                car = tempCars.get(car_index);
                for (i = 0; i < gridSizeY; i++)
                    tempBoard[i] = Board[i].clone();
                // Left
                for (steps = car.x - 1; steps >= 0; steps--) {
                    if (tempBoard[car.y][steps] != -1)
                        break;
                    sum++;
                    tempBoard[car.y][steps + car.size] = -1;
                    tempBoard[car.y][steps] = car.direction;
                    car.x--;
                    if (solveable(tempBoard, tempCars, moves - 1, sum))
                        return true;
                }
            }
            // Clear
            tempCars.clear();
            for (i = 0; i < CarsList.size(); i++)
                tempCars.add(new Cars(CarsList.get(i)));
            car = tempCars.get(car_index);
            for (i = 0; i < gridSizeY; i++)
                tempBoard[i] = Board[i].clone();
        }
        return false;
    }

}
