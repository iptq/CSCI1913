class AssociationList<Key, Value> {
  class Node {
    private Key key;
    private Value value;
    private Node next;
    public Node(Key key, Value value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }
  private Node head;
  public AssociationList() {
    head = new Node(null, null, null);
  }
  public Value get(Key key) {
    Node search = head.next;
    while (search != null) {
      if (search.key.equals(key)) {
        return search.value;
      }
      search = search.next;
    }
    throw new IllegalArgumentException("Key not found.");
  }
  public boolean isEmpty() {
    return head.next == null;
  }
  public void put(Key key, Value value) {
    if (key == null)
      throw new IllegalArgumentException("Key is null.");
    Node search = head.next;
    while (search != null) {
      if (search.key.equals(key)) {
        search.value = value;
        return;
      }
      search = search.next;
    }
    Node newNode = new Node(key, value, head.next);
    head.next = newNode;
  }
  public void remove(Key key) {
    Node search = head;
    while (search.next != null) {
      if (search.next.key.equals(key)) {
        search.next = search.next.next;
        return;
      }
      search = search.next;
    }
  }
}

class SmallMediumLarge  
{  
  public static void main(String [] args)  
  {  
    AssociationList<String, Integer> a = new AssociationList<String, Integer>();  
  
    a.put("small",  0);  
    a.put("medium", 1);  
    a.put("large",  2);  
  
    System.out.println(a.get("small"));   //  0  
    System.out.println(a.get("medium"));  //  1  
    System.out.println(a.get("large"));   //  2  
  
    a.put("large", 1000);  
  
    System.out.println(a.get("small"));   //  0  
    System.out.println(a.get("medium"));  //  1  
    System.out.println(a.get("large"));   //  1000  
  
    a.remove("large");  
  
    System.out.println(a.get("small"));   //  0  
    System.out.println(a.get("medium"));  //  1  
    System.out.println(a.get("large"));   //  Throw an exception.  
  }  
}

/*
0
1
2
0
1
1000
0
1
Exception in thread "main" java.lang.IllegalArgumentException: Key not found.
	at AssociationList.get(SmallMediumLarge.java:24)
	at SmallMediumLarge.main(SmallMediumLarge.java:79)
*/