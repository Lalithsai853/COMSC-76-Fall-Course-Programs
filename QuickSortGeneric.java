
import java.util.Comparator;

class Circle extends GeometricObject {
  private double radius;

  public Circle() {
  }

  public Circle(double radius) {
    this.radius = radius;
  }

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  @Override /** Return area */
  public double getArea() {
    return radius * radius * Math.PI;
  }

  /** Return diameter */
  public double getDiameter() {
    return 2 * radius;
  }

  @Override /** Return perimeter */
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  /* Print the circle info */
  public void printCircle() {
    System.out.println("The circle is created " + getDateCreated() +
      " and the radius is " + radius);
  }
}

abstract class GeometricObject {
  private String color = "white";
  private boolean filled;
  private java.util.Date dateCreated;

  /** Construct a default geometric object */
  protected GeometricObject() {
    dateCreated = new java.util.Date();
  }

  /** Construct a geometric object with color and filled value */
  protected GeometricObject(String color, boolean filled) {
    dateCreated = new java.util.Date();
    this.color = color;
    this.filled = filled;
  }

  /** Return color */
  public String getColor() {
    return color;
  }

  /** Set a new color */
  public void setColor(String color) {
    this.color = color;
  }

  /** Return filled. Since filled is boolean,
   *  the get method is named isFilled */
  public boolean isFilled() {
    return filled;
  }

  /** Set a new filled */
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /** Get dateCreated */
  public java.util.Date getDateCreated() {
    return dateCreated;
  }

  /** Return a string representation of this object */
  public String toString() {
    return "created on " + dateCreated + "\ncolor: " + color +
      " and filled: " + filled;
  }

  /** Abstract method getArea */
  public abstract double getArea();

  /** Abstract method getPerimeter */
  public abstract double getPerimeter();
}

public class Lalithsai_Posam_Test2 {

    public static <E extends Comparable<E>> void quickSort(E[ ] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

    public static <E extends Comparable<E>> int partition(E[] list, int first, int last) {
        E pivot = list[first]; 
        int low = first + 1;
        int high = last;

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low].compareTo(pivot) <= 0)
                low++;
                                                                                                
            // Search backward from right
            while (low <= high && list[high].compareTo(pivot) > 0)
                high--;

            // Swap two elements in the list
            if (high > low) {
                E temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high].compareTo(pivot) >= 0) {
            high--;
        }
        // Swap pivot with list[high]
        if (pivot.compareTo(list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }

        else {
            return first;
        }
        
    }

    

    public static <E> void quickSort(E[ ] list,  Comparator<? super E> comparator) {
        quickSort(list, 0, list.length - 1, comparator);

    }

    public static <E> void quickSort(E[ ] list, int first, int last, Comparator<? super E> comparator) {
        if (last > first) {
            int pivotIndex = partition(list, first, last, comparator);
            quickSort(list, first, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, last, comparator);
        }
    }

    public static <E> int partition(E[] list, int first, int last, Comparator<? super E> comparator) {
        E pivot = list[first]; 
        int low = first + 1;
        int high = last;

        while (high > low) {
            // Search forward from left
            while (low <= high && comparator.compare(list[low], pivot) <= 0) {
                low++;
            }                                                                                    
            // Search backward from right
            while (low <= high && comparator.compare(list[high], pivot) > 0) {
                high--;
            }
            // Swap two elements in the list
            if (high > low) {
                E temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && comparator.compare(list[high], pivot) >= 0) {
            high--;
        }
        // Swap pivot with list[high]
        if (comparator.compare(list[high], pivot) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }

        else {
            return first;
        }
        
    }

    public static void main(String[ ] args) {

        Integer[ ] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};

        quickSort(list);

        for (int i = 0; i < list.length; i++) {

            System.out.print(list[i] + " ");

        }

        System.out.println();

        Circle[ ] list1 = {new Circle(2), new Circle(3), new Circle(2),

                     new Circle(5), new Circle(6), new Circle(1), new Circle(2),

                     new Circle(3), new Circle(14), new Circle(12)};

        quickSort(list1, new GeometricObjectComparator());

        for (int i = 0; i < list1.length; i++) {

            System.out.print(list1[i] + " ");

        }

    }
}
 
