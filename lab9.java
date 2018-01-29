class RunnyStack<Base> {
	class Run {
		public Base object;
		public int length;
		public Run next;
		public Run(Base object, Run next) {
			this.object = object;
			this.length = 1;
			this.next = next;
		}
		public Run(Base object) {
			this(object, null);
		}
		public boolean is(Base other) {
			if (other == null || this.object == null) {
				return this.object == other;
			} else {
				return this.object.equals(other);
			}
		}
	}
	private int runs, depth;
	private Run top;
	public RunnyStack() {
		this.runs = 0;
		this.depth = 0;
		this.top = null;
	}
	public boolean isEmpty() {
		return this.top == null;
	}
	public int depth() {
		return this.depth;
	}
	public int runs() {
		return this.runs;
	}
	public void push(Base object) {
		Run run = new Run(object, top);
		if (this.top == null) {
			this.runs = 1;
			this.depth = 1;
			this.top = run;
		} else {
			if (this.top.is(object)) {
				this.depth += 1;
				this.top.length += 1;
			} else {
				this.runs += 1;
				this.depth += 1;
				Run top = this.top;
				run.next = top;
				this.top = run;
			}
		}
	}
	public void pop() {
		if (this.isEmpty()) {
			throw new IllegalStateException("The stack is empty.");
		}
		this.depth -= 1;
		this.top.length -= 1;
		if (this.top.length == 0) {
			this.runs -= 1;
			this.top = this.top.next;
		}
	}
	public Base peek() {
		if (this.isEmpty()) {
			throw new IllegalStateException("The stack is empty.");
		}
		return this.top.object;
	}
}

class RunnyDriver {  
	public static void main(String[] args) {  
		RunnyStack<String> s = new RunnyStack<String>();  
		
		s.push("A");  
		System.out.println(s.peek() + " " + s.depth() + " " + s.runs());  //  A 1 1  
		
		s.push("B");  
		System.out.println(s.peek() + " " + s.depth() + " " + s.runs());  //  B 2 2  
		
		s.push("B");  
		System.out.println(s.peek() + " " + s.depth() + " " + s.runs());  //  B 3 2  
		
		s.pop();  
		System.out.println(s.peek() + " " + s.depth() + " " + s.runs());  //  B 2 2  
		
		s.pop();  
		System.out.println(s.peek() + " " + s.depth() + " " + s.runs());  //  A 1 1  
	}
}