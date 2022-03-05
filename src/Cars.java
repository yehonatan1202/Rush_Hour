
public class Cars {
    int index;
    int x;
    int y;
    int size;
    int direction; // 0=up, 1=right
    String color;

    public Cars(int y, int x, int size, int dir, String color) {
        this.y = y;
        this.x = x;
        this.size = size;
        this.direction = dir;
        this.color = color;
    }

    public Cars(int index, int y, int x, int size, int dir, String color) {
        this.index = index;
        this.y = y;
        this.x = x;
        this.size = size;
        this.direction = dir;
        this.color = color;
    }

    public Cars(Cars car) {
        this.index = car.index;
        this.x = car.x;
        this.y = car.y;
        this.size = car.size;
        this.direction = car.direction;
        this.color = car.color;
    }
}
