class Zillion {
	int[] digits;
	public Zillion(int size) {
		this.digits = new int[size];
	}
	public void increment() {
		int position = this.digits.length;
		while (true) {
			position -= 1;
			this.digits[position] += 1;
			if (this.digits[position] == 10) {
				this.digits[position] = 0;
				if (position == 0) {
					break;
				}
			} else {
				break;
			}
		}
	}
	@Override
	public String toString() {
		String result = "";
		for (int digit : this.digits) {
			result += digit;
		}
		return result;
	}
}

public class Main {
	public static void main(String[] args) {
		Zillion z = new Zillion(3);
		for (int i = 0; i < 999; i += 1) {
			z.increment();
		}
		System.out.println(z);
		z.increment();
		System.out.println(z);
		/*
		Output:
		999
		000
		*/
	}
}