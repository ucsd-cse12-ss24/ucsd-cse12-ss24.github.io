package LinkedList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestStringList {

  @Test
  public void testAdd() {
    StringList slist = new LinkedStringList();
    slist.prepend("banana");
    slist.prepend("apple");

    assertEquals("banana", slist.get(1));
    assertEquals("apple", slist.get(0));
  }

  @Test
  public void testAddThenSize() {
    StringList slist = new LinkedStringList();
    slist.prepend("banana");
    slist.prepend("apple");
    slist.prepend("c");
    slist.prepend("d");
    slist.prepend("e");

    assertEquals(5, slist.size());
  }


  @Test
  public void testAddMany() {
    StringList slist = new LinkedStringList();
    slist.add("a");
    slist.add("b");
    slist.add("c");
    slist.add("d");
    slist.add("e");

    assertEquals(null, slist.get(100));
    assertEquals("e", slist.get(4));
    assertEquals("d", slist.get(3));
    assertEquals("c", slist.get(2));
    assertEquals("b", slist.get(1));
    assertEquals("a", slist.get(0));

  }

}

