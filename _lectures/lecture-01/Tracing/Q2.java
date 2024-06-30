package Tracing;

public class Q2 {
    public static void f(Coord c) {
      Car car = new Car("blue", c);
      car.location.row = 10;
      car.location.col = 9;
    }
    public static int question() {
      Coord unit = new Coord(1, 1);
      Car blackCar = new Car("black", unit);
      f(unit);
      return blackCar.location.row;
    }
    public static void main(String[] args) {
      System.out.println(question());
    }
  }

