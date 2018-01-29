class Pathname {
	private int depth;
	private String[] directories;
	private String name;
	private String type;
	public Pathname(String name) {
		this(name, "");
	}
	public Pathname(String name, String type) {
		this.depth = 0;
		this.directories = new String[10];
		this.type = type;
		this.name = name;
	}
	public void addDirectory(String directory) {
		if (this.depth >= 10) return;
		this.directories[this.depth++] = directory;
	}
	public boolean equals(Pathname other) {
		if (this.depth != other.depth) return false;
		for (int i = 0; i < this.depth; i += 1) {
			if (this.directories[i] != other.directories[i]) return false;
		}
		return true;
	}
	public String toString() {
		String result = "";
		for (int i = 0; i < this.depth; i += 1) {
			result += "/" + this.directories[i];
		}
		result += "/" + this.name;
		if (this.type != "") {
			result += "." + this.type;
		}
		return result;
	}
}

class Pathfinder  
{  
  public static void main(String [] args)  
  {  
    Pathname p0 = new Pathname("coffee", "java");  
    p0.addDirectory("home");  
    p0.addDirectory("Desktop");  
    p0.addDirectory("labs");  
    System.out.println(p0);  //  Prints /home/Desktop/labs/coffee.java  
  
    Pathname p1 = new Pathname("cola");  
    p1.addDirectory("home");  
    p1.addDirectory("hax");  
    System.out.println(p1);  //  Prints /home/hax/cola  
  
    Pathname p2 = new Pathname("tea");  
    System.out.println(p2);  //  Prints /tea  
  
    System.out.println(p0.equals(p0));                //  Prints true  
    System.out.println(p0.equals(p1));                //  Prints false  
    System.out.println(p1.equals(p2));                //  Prints false  
    System.out.println(p0.equals("Not a pathname"));  //  Prints false  
  }  
}