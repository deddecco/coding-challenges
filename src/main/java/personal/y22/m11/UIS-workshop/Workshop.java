package personal.y22.m11.UISWorkshop;

import static personal.y22.m11.UISWorkshop.Rectangle.NUMBER_OF_SIDES;

public class Workshop {
     public static void main(String[] args) {
          Rectangle rectangle = new Rectangle();
          System.out.println(rectangle.getWidth());
          System.out.println(NUMBER_OF_SIDES);

          System.out.println(rectangle.getPerimeter());
          System.out.println(rectangle.getArea());
          System.out.println(Rectangle.getArea(10, 25));
          System.out.println(Rectangle.getPerimeter(10, 25));

          Rectangle newRectangle = new Rectangle(4, 3, 5.6, 9.5, "purple");

          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println(newRectangle.getArea());
     }
}
