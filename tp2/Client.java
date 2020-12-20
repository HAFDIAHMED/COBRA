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
      String refFile = "Bank.ref";
      java.io.BufferedReader in = new java.io.BufferedReader(new FileReader(refFile));
      ref = in.readLine();
    } catch (java.io.IOException ex) {
      ex.printStackTrace();
      System.exit(1);
    }
    org.omg.CORBA.Object obj = orb.string_to_object(ref);
    Bank bankinst = BankHelper.narrow(obj);
    //
    char ch = lire_choix() ;
    while (ch !='0') {
      switch(ch) {
        case '0': break;
        case '1': {
          float montant = bankinst.montant();
          System.out.println("Votre Montant : "+montant);
          break;
        }
        case '2': {
          System.out.println("Entrer Montant a crediter  : ");
          float montant = lire_float();

          bankinst.crediter(montant);
          break;
        }
        case '3': {
          System.out.println("Entrer Montant a debiter  : ");
          float montant = lire_float();

          bankinst.debiter(montant);
          break;

        }
      }
      ch=lire_choix();
    }
  }
  static char lire_choix() {
    System.out.println( "+------------------+");
    System.out.println( "| Service bancaire |");
    System.out.println( "+------------------+\n");
    System.out.println( "1 : Lecture du montant du compte");
    System.out.println( "2 : Credit du compte" );
    System.out.println( "3 : Debit du compte\n" );
    System.out.println( "0 : Quitter\n");
    System.out.println( "Taper le code de l'operation a effectuer : ");
    return(lire_char());
  }
  static char lire_char() {
    String chaine;
    try {
      java.io.DataInputStream dataIn = new java.io.DataInputStream(System.in);
      BufferedReader in = new BufferedReader(new InputStreamReader(dataIn));
      chaine = in.readLine();
    }
    catch(java.io.IOException ex) {
      System.err.println(ex.getMessage());
      ex.printStackTrace();
      return(' ');
    }
    return(chaine.charAt(0));
  }
  static float lire_float() {
    String chaine;
    try {
      java.io.DataInputStream dataIn = new java.io.DataInputStream(System.in);
      BufferedReader in = new BufferedReader(new InputStreamReader(dataIn));
      chaine = in.readLine();
    }
    catch(java.io.IOException ex) {
      System.err.println(ex.getMessage());
      ex.printStackTrace();
      return(0);
    }
    return(Float.parseFloat(chaine));
  }
}
