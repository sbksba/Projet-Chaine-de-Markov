//import java.math.BigDecimal;
import java.math.*;

public class Projet_main {

    public static void main (String[] args)
    {
	int K = Integer.parseInt(args[0]);
	double p = Double.parseDouble(args[1]);
	double q = Double.parseDouble(args[2]);
	int c = Integer.parseInt(args[3]);
	double epsilon = Math.pow(10,-5);
	/*double p = (2.0/5);
	  double q = (1.0/6);*/
	
	CMTD cmtd = new CMTD(K,p,q,c);
	double[][] mat = cmtd.get_matrice();
		
	System.out.println("\n## Matrice de transition ##");
	CMTD.affichage(mat);
		
	/*=================*/
	/*RESOLUTION EXACTE*/
	/*=================*/
	System.out.println("\n## Resolution exacte de Pi ##");
	double[] Pi = cmtd.resolution_exacte();
	System.out.print("\n[");
	double sum = 0.0;
	for (int i=0; i<=K; i++){
	    System.out.print("   " + Pi[i]);   
	}
	System.out.print("   ]\n");

	/*======================*/
	/*METHODE DES PUISSANCES*/
	/*======================*/
	System.out.println("\n## Resolution de Pi : methode des puissances ##");
	double[] PiP = cmtd.resolution_puissance(10000,epsilon);
	System.out.print("\n[");
	for (int i=0; i<=K; i++)
	    System.out.print("   " + PiP[i]);   
	System.out.print("   ]\n");
		    
	/*=======================*/
	/*METHODE DE GAUSS-SEIDEL*/
	/*=======================*/
	System.out.println("\n## Resolution de Pi : methode de gauss-seidel ##");
	double[] PiGS = cmtd.resolution_gauss_seidel(10000,epsilon);
	System.out.print("\n[");
	for (int i=0; i<=K; i++)
	    System.out.print("   " + PiGS[i]);   
	System.out.print("   ]\n");
		    	
	/*==============*/
	/*     TEST     */
	/*==============*/
	System.out.println("\n## Ecart entre les deux methodes et la methode exacte ##");
	double res1=0;
	double res2=0;
	int taille=0;
	
	for (int i=0; i<=K; i++)
	    {
		res1 += Math.abs(PiP[i]-Pi[i]);
		res2 += Math.abs(PiGS[i]-Pi[i]);
		taille++;
	    }
	
	res1 = (double)(res1/taille);
	res2 = (double)(res2/taille);
	if (res1 < res2)
	    System.out.println("Methode des puissance   : "+res1+" -> Methode la plus performante\nMethode de Gauss-Seidel : "+res2);
	else
	    System.out.println("Methode des puissance   : "+res1+"\nMethode de Gauss-Seidel : "+res2+" -> Methode la plus performante");

	/*double[] test = {3.,1., 2.};
	test = CMTD.normalize(test);
	for (int i=0; i<3; i++)
	    System.out.print("   " + test[i]);   
	    System.out.print("   ]\n");*/
	
	Test.performance_Puissance(p,q);
	//Test.NombreItEnFonctionDeK();
	//Test.ValeursProches();
	//Test.ValeursEloignees();
	//Test.InverseValeursProches();
	//Test.InverseValeursEloignees();
    }
}
