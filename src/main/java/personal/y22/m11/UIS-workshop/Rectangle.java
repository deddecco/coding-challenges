package personal.y22.m11.UISWorkshop;

public class Rectangle {
     static final int NUMBER_OF_SIDES = 4;
     private double width;
     private double height;
     private double x;
     private double y;
     private String color;

     public Rectangle() {
     }

     public Rectangle(double x, double y, double height, double width, String color) {
          this.x = x;
          this.y = y;
          this.height = height;
          this.width = width;
          this.color = color;
     }

     public double getWidth() {
          return width;
     }

     public void setWidth(double width) {
          if (width > 0) {
               this.width = width;
          }
     }

     public double getHeight() {
          return height;
     }

     public void setHeight(double height) {
          if (height > 0) {
               this.height = height;
          }
     }

     public double getX() {
          return x;
     }

     public void setX(double x) {
          this.x = x;
     }

     public double getY() {
          return y;
     }

     public void setY(double y) {
          this.y = y;
     }

     public String getColor() {
          return color;
     }

     public void setColor(String color) {
          this.color = color;
     }

     public double getArea() {
          return getWidth() * getHeight();
     }

     public static double getArea(double width, double height) {
          return width * height;
     }

     public double getPerimeter() {
          return 2 * (getWidth() + getHeight());
     }

     public static double getPerimeter(double width, double height) {
          return 2 * (width + height);
     }
}