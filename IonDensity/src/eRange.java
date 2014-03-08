

public class eRange {
    static double a = 5.37E-4;;
    static double b = 0.9815;
    static double c = 3.123E-3;
    static double w = 21.7; //KeV
    
    public static void main(String args[]) {
 
        // the following from Ref: Energy Deposition by Electron Beams and 6 Rays*
         double rr = range(a, b, c, w);
         System.out.printf("%10.6f", rr );
         System.out.println("     gm/cm2 \n");
    }      
         public static double  range(double a, double b, double c, double w) {
         double r;
         r = a * w;
         r = r * (1 - b/(1 + c * w));
         return r;
    }
     
   
}