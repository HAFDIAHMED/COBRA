import java.io.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;
public class Server {
  public static void main(String args[]) {
    int status = 0;
    org.omg.CORBA.ORB orb = null;
    try {
      orb = ORB.init(args, null);
      System.out.println( "+------------------+");
      System.out.println( "| Service bancaire |");
      System.out.println( "+------------------+\n");
      status = run(orb);
    } catch(UserException ex) {
      ex.printStackTrace();
      System.exit(1);
    }
    if(orb!=null) {
      try {
        orb.shutdown(true);
      } catch(Exception ex) {
        ex.printStackTrace();
        System.exit(1);
      }
    }
  }
  static int run(org.omg.CORBA.ORB orb)  throws org.omg.CORBA.UserException {
    org.omg.PortableServer.POA rootPOA = org.omg.PortableServer.POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
    org.omg.PortableServer.POAManager gerant = rootPOA.the_POAManager();
    Bank_impl bankimlp = new Bank_impl(0);
    Bank bank = bankimlp._this(orb);
    try {
      String ref = orb.object_to_string(bank);
      String refFile = "Bank.ref";
      java.io.PrintWriter out = new PrintWriter(new java.io.FileOutputStream(refFile));
      out.println(ref);
      out.flush();
    } catch(java.io.IOException ex) {
      ex.printStackTrace();
      return 1;
    }
    gerant.activate();
    orb.run();
    return 0;
  }
}
