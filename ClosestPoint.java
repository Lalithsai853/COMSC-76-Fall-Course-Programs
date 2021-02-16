import java.util.*;

public class ClosestPoint {
	public static void main (String[] args) {
		// Creates array of 10 points --- TEST
		Point[] array_points = new Point[10];
		for (int i = 0; i < array_points.length; i++) {
				array_points[i] = new Point();
				array_points[i].x = Math.random() * 100;
				array_points[i].y = Math.random() * 100;
		}

		// Prints points
		System.out.println("Test with 1 dimentional array: ");
		for(Point a: array_points) {
			System.out.printf("[ %.2f, %.2f ]\n", a.x, a.y);

		}
		// Calls getClosestPair method and returns closest pair and distance
		System.out.println("The closet pair: ");
		Pair pair = Pair.getClosestPair(array_points);
		System.out.printf("[ %.2f, %.2f ] - [ %.2f, %.2f ]\n", pair.p1.x, pair.p1.y, pair.p2.x, pair.p2.y );
		System.out.printf("Distance: %.2f" + " units\n", pair.getDistance());
	}
}

class Point implements Comparable<Point> {
	public double x;
	public double y;

	public Point () { }
	public Point(double x, double y)
	{
		this.x=x;
		this.y=y;
	}
	
	@Override
	// Compares x coordinates. If equal, compares y coordinates
	public int compareTo (Point rand_point) {
		if (x > rand_point.x) 
			return 1;
		
		else if (x < rand_point.x) 
			return -1;
		
		else if (y > rand_point.y) 
			return 1;
		
		else if (y < rand_point.y)
			return -1;
			
		else
			return 0;
	}
}


class CompareY implements Comparator<Point> {
	@Override
	// Compares y coordinates. If equal, compares x coordinates
	public int compare(Point point1, Point point2) {
		if (point1.y > point2.y) 
			return 1;
		
		else if (point1.y < point2.y) 
			return -1;
		
		else if (point1.x > point2.x) 
			return 1;
		
		else if (point1.x < point2.x) 
			return -1;
		else 
			return 0;
	}
}

class Pair {

    public Point p1;
	public Point p2;

	public Pair(Point p1, Point p2) {
		this.p1=p1;
		this.p2=p2;
	}
	public double getDistance()
	{
		return distance(p1,p2);
	} 

    //  Returns closest pair for multi-dimensional array
    public static Pair getClosestPair(double [ ] [ ]  points) {
        Point[] singlePoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            singlePoints[i] = new Point();
        }
        return getClosestPair(singlePoints);
    }

	// Returns distance for single dimensional array
	public static Pair getClosestPair(Point [ ]  points) {
        Arrays.sort(points); // pointsOrderedOnX
		Point[] pointsOrderedOnY = points.clone(); //pointsOrderedOnY
		Arrays.sort(pointsOrderedOnY, new CompareY());
		return distance(points, 0, points.length - 1, pointsOrderedOnY);

    }

	

    // Returns the distance between two points p1 and p2 

    public static double distance(Point p1, Point p2) {
        return distance(p1.x, p1.y, p2.x, p2.y);
    }



    // Compute the distance between points (x1, y1) and (x2, y2) 

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt( ((x2 -x1)*(x2-x1)) + ((y2-y1)*(y2-y1)));
    }


	// Returns the distance given the sorted array on the x coordinate and the sorted array on the y coordinates as well as the lowest and highest indices
    public static Pair distance(Point [ ] pointsOrderedOnX, int low, int high, Point [ ] pointsOrderedOnY) {
		
		if (low >= high) {
			return null;
		}
        if (low + 1 == high) {
            return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);
        }

		// Splits the points into two sides
        int midPoint = (low + high) / 2;

        // Return the closet pair on the left side
		Pair leftPair = distance(pointsOrderedOnX, low, midPoint, pointsOrderedOnY);
		// Return the closet pair on the right side
		Pair rightPair = distance(pointsOrderedOnX, midPoint + 1, high, pointsOrderedOnY);
		
		double compareDistance = 0;
		Pair closestPair = null;

		// No pairs on both s1 and s2
		if (leftPair == null && rightPair == null)  {
			compareDistance = 0;
			closestPair = null;
		} 

		// Closest pair on right side
		else if (leftPair == null) {
			compareDistance = rightPair.getDistance(); 
			closestPair = rightPair;
		} 

		// Closest pair on left side
		else if (rightPair == null) {
			compareDistance = leftPair.getDistance(); 
			closestPair = leftPair;
		} 
		else {
			// Get the minimum distance between both sides
			compareDistance = Math.min(leftPair.getDistance(), rightPair.getDistance());
			
			// Store the point that has the minimum distance
			if (leftPair.getDistance() <= rightPair.getDistance()) {
				closestPair = leftPair;
			}

			else {
				closestPair = rightPair;
			}
			
		}
		// Creates both strips
		ArrayList<Point> leftStrip = new ArrayList<Point>();
		ArrayList<Point> rightStrip = new ArrayList<Point>();

		// Adds points into both strips if they fit within the d by 2d rectangle
		for (int i = 0; i < pointsOrderedOnY.length; i++) 
		{
			if ((pointsOrderedOnY[i].x <= pointsOrderedOnX[midPoint].x)&&
				(pointsOrderedOnX[midPoint].x - pointsOrderedOnY[i].x <= compareDistance)) {
				leftStrip.add(pointsOrderedOnY[i]);
			}
			else if ((pointsOrderedOnY[i].x > pointsOrderedOnX[midPoint].x &&
					(pointsOrderedOnY[i].x - pointsOrderedOnX[midPoint].x <= compareDistance ))) {
				rightStrip.add(pointsOrderedOnY[i]);
			}
		}
		
		// Compares points from the left and right strips -- if less than the compareDistance, that pair becomes closest pair
		int rightIndex = 0;
		for (int i = 0; i < leftStrip.size(); i++) 
		{
			while (rightIndex < rightStrip.size() && rightStrip.get(rightIndex).y <= leftStrip.get(i).y - compareDistance) {
				rightIndex++;
			}
			int r1 = rightIndex;
			while (r1 < rightStrip.size() && Math.abs(rightStrip.get(r1).y - leftStrip.get(i).y) <=  compareDistance) {
				
				if (distance(leftStrip.get(i), rightStrip.get(r1)) < compareDistance) {
					compareDistance = distance(leftStrip.get(i), rightStrip.get(r1));
					closestPair.p1 = leftStrip.get(i);
					closestPair.p2 = rightStrip.get(r1);
				}

				r1++;
			}
		}

		return closestPair;
	}


}
