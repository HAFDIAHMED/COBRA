import java.io.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
public class Client {
  public static void main(String args[]) {
    org.omg.CORBA.ORB orb = null;
    try {
      orb = ORB.init(args, null);
    } catch(SystemException ex) {
      ex.printStackTrace();
      return;
    }
    String ref = null;
    try {
      String refFile = "Hello.ref";
      java.io.BufferedReader in = new java.io.BufferedReader(new FileReader(refFile));
      ref = in.readLine();
    } catch (java.io.IOException ex) {
      ex.printStackTrace();
      System.exit(1);
    }
    org.omg.CORBA.Object obj = orb.string_to_object(ref);
    Hello helloInst = HelloHelper.narrow(obj);
    helloInst.hello();
  }
}
