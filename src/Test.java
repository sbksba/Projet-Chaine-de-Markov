import java.math.*;

public class Test
{
    /*Performance du systeme en fonction de K*/
    public static void performance_K(double p, double q)
    {
	for (int i=0; i<15000; i=i+1000)
	    {
		CMTD cmtd = new CMTD(i,p,q,1);
		long debut = System.nanoTime();
		double[] Pi = cmtd.resolution_exacte();
		System.out.println((System.nanoTime()-debut)+" "+i);
	    }
    }
    
    /*Nombre d iterations variables*/
    public static void performance_Puissance(double p, double q)
    {
	for (int i=1; i<=1000; i++)
	    {
		CMTD cmtd = new CMTD(i,p,q,1);
		System.out.print(i+" ");
		/*long debut = System.currentTimeMillis();*/
		double[] Pi = cmtd.resolution_puissance(i,Math.pow(10,-5));
		System.out.println("");
		/*System.out.println(((System.currentTimeMillis()-debut)*0.001)+" "+i);*/
	    }
    }
    
    /* */
    public static void performance_GaussSeidel(double p, double q)
    {
	for (int i=0; i<=500; i++)
	    {
		CMTD cmtd = new CMTD(i,p,q,1);
		long debut = System.currentTimeMillis();
		double[] Pi = cmtd.resolution_gauss_seidel(i,Math.pow(10,-5));
		System.out.println(((System.currentTimeMillis()-debut)*0.001)+" "+i);
	    }
    }

    /*Variation d'epsilon*/
    public static void performance_EPSILON(int K, double p, double q)
    {
	CMTD cmtd = new CMTD(K,p,q,1);
	for (int i=0; i>-100; i--)
	    {
		long debut = System.nanoTime();
		double[] Pi = cmtd.resolution_puissance(1000,Math.pow(10,i));
		System.out.println(i+" :  "+((System.nanoTime()-debut)));
	    } 
    }

    /* Nombre d'iterations des methodes de gauss-seidel et des puissances en fonction de K*/
    public static void NombreItEnFonctionDeK ()
    {
	double p = 0.02;
	double q = 0.01;
	System.out.println("\n#NombreItEnFonctionDeK.dat\n#K puissance gauss-seidel");
	for (int i=6; i<=100; i++)
	    {
		CMTD cmtd = new CMTD(i,p,q,1);
		System.out.print(i+" ");
		double[] PiP = cmtd.resolution_puissance(10000,Math.pow(10,-5));
		System.out.print(" ");
		double[] PiGS = cmtd.resolution_gauss_seidel(10000,Math.pow(10,-5));
		System.out.println("");
	    }
    }

    /* Nombre d'iterations pour des valeurs proches*/
    public static void ValeursProches()
    {
	double i=0.0001;
	System.out.println("\n#ValeursProches.dat\n#QP puissance gauss-seidel");
	while(i<0.615)
	{
	    double p=i;
	    double q=p+0.0001;
	    CMTD cmtd = new CMTD(6,p,q,1);
	    System.out.print(i+" ");
	    double[] PiP = cmtd.resolution_puissance(10000,Math.pow(10,-5));
	    System.out.print(" ");
	    double[] PiGS = cmtd.resolution_gauss_seidel(10000,Math.pow(10,-5));
	    System.out.println("");
	    i=i+0.0001;
	}
    }

    /* Nombre d'iterations pour des valeurs eloignees*/
    public static void ValeursEloignees()
    {
	double i=0.0001;
	System.out.println("\n#ValeursEloignees.dat\n#QP puissance gauss-seidel");
	while(i<1)
	{
	    double p=i;
	    double q=p/100;
	    CMTD cmtd = new CMTD(6,p,q,1);
	    System.out.print(i+" ");
	    double[] PiP = cmtd.resolution_puissance(10000,Math.pow(10,-5));
	    System.out.print(" ");
	    double[] PiGS = cmtd.resolution_gauss_seidel(10000,Math.pow(10,-5));
	    System.out.println("");
	    i=i+0.0001;
	}
    }
    
    /* Nombre d'iterations pour des valeurs proches*/
    public static void InverseValeursProches()
    {
	double i=0.0001;
	System.out.println("\n#InverseValeursProches.dat\n#QP puissance gauss-seidel");
	while(i<0.615)
	{
	    double q=i;
	    double p=q+0.0001;
	    CMTD cmtd = new CMTD(6,p,q,1);
	    System.out.print(i+" ");
	    double[] PiP = cmtd.resolution_puissance(10000,Math.pow(10,-5));
	    System.out.print(" ");
	    double[] PiGS = cmtd.resolution_gauss_seidel(10000,Math.pow(10,-5));
	    System.out.println("");
	    i=i+0.0001;
	}
    }

    /* Nombre d'iterations pour des valeurs eloignees*/
    public static void InverseValeursEloignees()
    {
	double i=0.0001;
	System.out.println("\n#InverseValeursEloignees.dat\n#QP puissance gauss-seidel");
	while(i<1)
	{
	    double q=i;
	    double p=q/100;
	    CMTD cmtd = new CMTD(6,p,q,1);
	    System.out.print(i+" ");
	    double[] PiP = cmtd.resolution_puissance(10000,Math.pow(10,-5));
	    System.out.print(" ");
	    double[] PiGS = cmtd.resolution_gauss_seidel(10000,Math.pow(10,-5));
	    System.out.println("");
	    i=i+0.0001;
	}
    }
}
