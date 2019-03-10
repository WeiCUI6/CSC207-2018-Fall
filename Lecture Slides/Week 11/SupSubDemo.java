public class SupSubDemo {
  public static void main(String[] args) {
      Sup sup = new Sup();
      sup.m();
      Sub sub = new Sub();
      sub.m();
      Sup both = new Sub();
      both.m();
  }
}

class Sup {
    public void m() {
        System.out.println("Sup's m");
    }
}

class Sub extends Sup {
    public void m() {
        System.out.println("Sub's m");
    }
}