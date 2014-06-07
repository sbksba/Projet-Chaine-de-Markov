import java.lang.Math;
import java.math.*;

public class CMTD
{
    private int K;
    private double p,q;
    private double[][] P;

    public CMTD (int K, double p, double q,int choix)
    {
	this.p = p;
	this.q = q;
	this.K = K;
	if (choix == 1)
	    P = matrice();
	else if (choix == 2)
	    P = matriceInfinie();
	else
	    System.out.println("Probleme");
    }
    
    public double[][] get_matrice () {return P;} 
    
    public static void affichage (double[][] matrice)
    {
	for (int i=0; i<matrice.length; i++)
	    {
		for (int j=0; j<matrice.length; j++)
		    System.out.print(matrice[i][j]+" ");
		System.out.println("");
	    }
    }

    /* Rend la matrice de transition */
    public double[][] matrice()
    {
	double[][] P = new double [K+1][K+1];
	for (int i=0; i<=K; i++){
	    for (int j=0; j<=K; j++){
		
		if(i==j && i==K){//cas de la case(k,k)
		    P[i][j]=1-q;
		}
		else if(i==j && i==0){//cas de la case(0,0)
		    P[i][j]=1-p;
		}
		else if(i==j){//cas de la case (i,i)
		    P[i][j]=1-p-q;
		}
		else if(i>0 && j==i-1){ //cas de la case (i,i)
		    P[i][j]=q;
		}
		else if(i<K && j==i+1){
		    P[i][j]=p;
		}
		else{
		    P[i][j]=0;
		}
	    }
	}
	return P;
    }
    
    /* Rend la matrice de transition Infinie (Exercice 3) */
    public double[][] matriceInfinie()
    {
	double[][] P = new double [K+1][K+1];
	
	for (int i=0; i<=K; i++){
	    for (int j=0; j<=K; j++){
		if (i==0 && i==j)
			P[i][j] = (1-q)*(1-p)+q;
		else if (i==K && j==i)
		    P[i][j] = (1-q)*(1-p)+(1-q)*p;
		else if (j==i && i!=0)
		    P[i][j] = (1-q)*(1-p);
		else if (j==i+1)
		    P[i][j] = (1-q)*p;
		else if (i!=0)
		    P[i][0] = q;
	    }
	}
		
	return P;
    }
    
    /* Rend la resolution exacte */
    public double[] resolution_exacte()
    {
	double[] pi = new double[K+1];
	pi[0] = 1;
	double somme = pi[0];
	int cpt=0;
        
	for (int i = 1; i<=K; i++)
	    {
		pi[i] = (pi[i-1] * p) / q;
		somme += pi[i];
		cpt++;
	    }
	for (int i = 0; i<=K; i++)
	    {
		pi[i] = pi[i] / somme;
		cpt++;
	    }
	System.out.println("Nombre d'iterations : "+cpt);
	return pi;
    }

    /* Rend le produit d'un vecteur et d'une matrice */
    public double[] produitVectMat (double [][] matrice, double[] tabPi)
    {
	double[] resultat = new double[K+1];
	for (int i=0; i<tabPi.length; i++)
	    resultat[i] = 0;

	for (int i=0; i<tabPi.length; i++)
	    for (int j=0; j<tabPi.length; j++)
		resultat[i] += tabPi[j]*matrice[j][i];
		
	return resultat;
    }

    /* Rend la resolution par la methode des puissances*/
    public double[] resolution_puissance (int n, double epsilon)
    {
	double[] Pi = new double [K+1];
	double[] vecteur = new double [K+1];
	double res;
	int limit = n;
	int cpt=0;

	for (int i=0; i<=K; i++)
	    Pi[i] = (double)1/(double)Math.abs(K+1);
	
	long debut = System.currentTimeMillis();
	while (limit > 0)
	    {
		cpt++;
		//System.out.println((System.currentTimeMillis()-debut)*Math.pow(10,-3)+" "+cpt);
		vecteur = produitVectMat(P,Pi);
		/* Ecart entre Pi et vecteur*/
		res = ecart(Pi,vecteur);
		
		/* Si notre ecart est inferieur a EPSILON alors on s arrete*/
		if (res < epsilon)
		    {
			System.out.print(cpt);
			return vecteur;
		    }
		
		Pi = vecteur;
		limit--;
	    }
	System.out.print(cpt);
	//System.out.println("puissances = "+ limit);
	return Pi;
    }

    /* Rend l ecart entre deux vecteurs*/
    public double ecart (double[] vect1, double[] vect2)
    {
	double somme = 0;
	
	for (int i=0; i<vect1.length;i++)
	    somme += Math.abs(vect1[i] - vect2[i]);
	return somme;
    }

    public void affiche (double[] v)
    {
	System.out.print("\n[");
	for (int i=0; i<v.length; i++)
	    System.out.print("   " + v[i]);   
	System.out.print("   ]\n");
    }

    public double[] resolution_gauss_seidel(int n, double epsilon)
    {
	int limit = n;
	double[] Pi = new double [K+1];
	int cpt=0;
	
	for (int i=0; i<=K; i++)
	    Pi[i] = (double)1/(double)Math.abs(K+1);

	double []temp=gauss_seidel(Pi);
	long debut = System.currentTimeMillis();
	while (limit >0)
	{
	    cpt++;
	    //System.out.println((System.currentTimeMillis()-debut)*Math.pow(10,-3)+" "+cpt);
	    if (ecart(Pi,temp)<epsilon) {System.out.print(cpt);return Pi;}
	    temp = Pi;
	    Pi = gauss_seidel(temp);
	    limit--;
	}
	System.out.print(cpt);
	return Pi;
    }
    
    public double[] gauss_seidel(double[] v)
    {
	double[] Pi = new double [K+1];
	double[] vecteur = new double [K+1] ;
	double res; 
	double somme;

	for (int i=0; i<=K; i++)
	    Pi[i] = (double)1/(double)Math.abs(K+1);
	
	double somme_pi_k=0;

	for (int j=0; j<vecteur.length; j++)
	{
	    somme = 0;
	    
	    for (int i=0; i<j; i++)
		somme += vecteur[i]*P[i][j];
	    
	    for (int i=j+1; i<vecteur.length; i++)
		somme += v[i] * P[i][j];
	    
	    vecteur[j] = somme*(1.00/(1.00-P[j][j]));
	    somme_pi_k=somme_pi_k+vecteur[j];
	}

	for(int i=0;i<P.length;i++)
	    Pi[i]=(double)vecteur[i]/(double)somme_pi_k;
       
	return Pi;
    }
}
