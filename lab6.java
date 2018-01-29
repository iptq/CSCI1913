class Polygon {
	private int[] sideLengths;

	public Polygon(int sides, int...lengths) {
		int index = 0;
		sideLengths = new int[sides];
		for (int length: lengths) {
			sideLengths[index] = length;
			index += 1;
		}
	}

	public int side(int number) {
		return sideLengths[number];
	}

	public int perimeter() {
		int total = 0;
		for (int index = 0; index < sideLengths.length; index += 1) {
			total += side(index);
		}
		return total;
	}
}

class Rectangle extends Polygon {
	private int width, height;
	public Rectangle(int width, int height) {
		super(4, new int[] { width, height, width, height });
		this.width = width;
		this.height = height;
	}
	public int area() {
		return this.width * this.height;
	}
}

class Square extends Polygon {
	private int sideLength;
	public Square(int sideLength) {
		super(4, new int[] { sideLength, sideLength, sideLength, sideLength });
		this.sideLength = sideLength;
	}
	public int area() {
		return this.sideLength * this.sideLength;
	}
}

class Shapes {
	public static void main(String[] args) {
		Rectangle wrecked = new Rectangle(3, 5); //  Make a 3 × 5 rectangle.  
		System.out.println(wrecked.area()); //  Print its area, 15.  
		System.out.println(wrecked.perimeter()); //  Print its perimeter, 16.  

		Square nerd = new Square(7); //  Make a 7 × 7 square.  
		System.out.println(nerd.area()); //  Print its area, 49.  
		System.out.println(nerd.perimeter()); //  Print its perimeter, 28.  
	}
}