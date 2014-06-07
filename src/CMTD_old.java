import java.lang.Math;
import java.math.BigDecimal;

public class CMTD
{
    private int dK;
    private double dp,dq;
    private BigDecimal[][] P;
    private BigDecimal K,p,q;


    public CMTD (int K, double p, double q) {
	this.dp = p;
	this.dq = q;
	this.dK = K;
	this.K = new BigDecimal(Double.toString (K));
	this.p = new BigDecimal(Double.toString (p));
	this.q = new BigDecimal(Double.toString (q));
	P = matrice();
    }

    public BigDecimal[][] get_matrice () {
	return P;
    } 

    /* Rend la matrice de transition */
    public BigDecimal[][] matrice ()
    {
	BigDecimal[][] resultat = new BigDecimal[dK+1][dK+1];
	BigDecimal one = new BigDecimal("1.0");
	if (dK == 0) {resultat[0][0] = one; return resultat;}

       
	for (int i=0; i<=dK; i++)
	    for (int j=0; j<=dK; j++)
		resultat[i][j] = new BigDecimal("0.0");

	resultat[0][0] = p;
	resultat[dK][dK] = one.subtract(q);
	resultat[0][1] = p;
	resultat[dK][dK-1] = q;

	for (int i=1; i<dK; i++) 
	    {
		resultat[i][i] = ((one.subtract(p)).subtract(q)).add((p.multiply(q)).multiply(new BigDecimal("2.0")));
		//resultat[i][i] = (1-dp)-dq+(2*dp*dq);
		resultat[i][i+1] = p.multiply(one.subtract(q));
		//resultat[i][i+1] = dp*(1-dq);
		resultat[i][i-1] = q.multiply(one.subtract(p));
		//resultat[i][i-1] = dq*(1-dp);
	    }

	return resultat;
    }
    
    public BigDecimal[] resolution_exacte() {

	BigDecimal K = new BigDecimal(Double.toString(dK));
	BigDecimal p = new BigDecimal(Double.toString(dp));
	BigDecimal q = new BigDecimal(Double.toString(dq));
	BigDecimal one = new BigDecimal("1.0");
	
	// Pi (n) = Pi (0) * P^n 
	BigDecimal[] Pi = new BigDecimal [dK+1];

	// Pi[0] + Pi[1] + .... + Pi[K] = 1 
	BigDecimal sum2k = new BigDecimal("0.0"); 
	
	if (dK == 0) { Pi[0]= one; return Pi;}
	else {
	    BigDecimal tmp = p.divide(q.multiply(one.subtract(p)));
	    BigDecimal tmp2;
 
	    if (dK==1) {
	        tmp2 = one.add(tmp);
	    }
	    else {
		if (dK ==2) {
		    tmp2 = one.add(tmp.multiply(one.add((p.multiply(one.subtract(q))).divide(q))));
		}
		else {
		    for (int i=2; i<dK; i++) {
			Pi[i] = ((p.multiply(one.subtract(q))).divide((q.multiply(one.subtract(p))))).pow (i-1);
			//Pi[i] = Math.pow( ((p * (1-q)) / (q*(1-p))), (double) (i-1) );

			//System.out.println("Pi " + i + " = " + Pi[i]);
			sum2k = sum2k.add(Pi[i]);
			//System.out.println("sum2k = " +sum2k);
		    }
	
	
		    //Pi[0] = 1 / (1 + p/((1-q)*q) * ( 1 + sum2k + (p * (1-q) /q) * Pi[K-1]));
		    tmp2 = (sum2k.add(one)).add(((p.multiply(one.subtract(q))).divide(q)).multiply(Pi[dK-1]));
		    //System.out.println(tmp2);
		    tmp2 = one.add(tmp.multiply(tmp2));
		}
	    }

	    Pi[0] = one.divide(tmp2, 30, BigDecimal.ROUND_DOWN);
        
	    //System.out.println("Pi[0] = " + Pi[0]);

	    //System.out.println("Pi[1] = ("+p +"/ ("+(1-p)+" * "+q+")) *Pi[0]") ;

	    Pi[1] = Pi[0].multiply(tmp);
	    //Pi[1] = (p / ((1-p)*q)) *Pi[0] ;

	    //avant la boucle Pi[i] contient [p(1-q)/q(1-p)]^i-1
	    for (int i = 2; i<dK; i++) {
		Pi[i] = Pi[1].multiply(Pi[i]); 
	    }
	    tmp = (p.divide(q)).multiply(one.subtract(q));
	    if (dK>1) Pi[dK] = Pi[dK-1].multiply(tmp);
	
	    return Pi;
	}
    }


    /* Rend le produit scalaire de deux vecteurs */
    public double produit_scalaire (double [][] matrice, double[] tabPi,int K)
    {
	double somme = 0;
	for (int i=0; i<K; i++)
	    {
		for (int j=0; j<K; j++)
		    {
			somme += matrice[i][j]*tabPi[j];
		    }
	    }
	
	return somme;
    }
}
