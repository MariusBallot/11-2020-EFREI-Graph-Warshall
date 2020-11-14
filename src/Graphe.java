import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graphe {
	
	private int nb_sommets; // test
	
	private int nb_arretes;
	
	private double[][] matrice;
	
	private String nom;
	
	private int pli; //plus grand item (utile pour l'affichage)
	
	
	public Graphe(final String nom_fichier) {
		try {
            Scanner scanner = new Scanner(new File(nom_fichier)); 
            
            this.nom = nom_fichier;
            
            // convertion et affectation des valeurs du nb de colonnes/arretes
            String nb_sommets_s = scanner.next();
            String nb_arretes_s = scanner.next();
            this.nb_sommets = Integer.parseInt(nb_sommets_s);
            this.nb_arretes = Integer.parseInt(nb_arretes_s); 

            
            // creation du graphe (par une matrice d'adjacente contenant les valeurs des arcs)
            this.matrice = new double [nb_sommets][nb_sommets];
           
            
            String[] tab_s = new String[3*nb_arretes]; // va nous servir pour améliorer l'affichage
            double[] tab = new double[3*nb_arretes]; //initialisation du tableau contenant les valeurs du graphes.
            int i = 0;
            while(scanner.hasNext()) { //Scan mot par mot
            
            	String x = scanner.next(); 
            	tab_s[i] = x; // remplissage de tab_s
            	int v_x = Integer.parseInt(x);
            	tab[i]=v_x; // remplissage de tab
            	i++;
            }
            
            // donne la longueur de l'item le plus "long" de la matrice pour adapter l'affichage en fonction de lui
            String max =tab_s[0];
            for(String x : tab_s) {
            	if(x.length()>max.length()) {
            		max=x;
            	}
            }
            this.pli=max.length();
            
            
            
            // remplissage de la matrice
            for (int n = 0 ; n<tab.length; n=n+3) {
            	int a = (int)tab[n]; // sommet a 
            	int b = (int)tab[n+1]; // sommet b
            	double val = tab[n+2]; // valeur de l'arrete (a,b)
            	matrice[a][b]=val;
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
		

	double inf = Double.POSITIVE_INFINITY;
	
	public String Floyd_Warshall() {
		String[] t = new String[matrice.length*matrice[0].length];
		double[][] m = this.getMatrice();
		int h =0;
		// remplacement des 0 par des +oo
		for(int i = 0 ; i < this.getNb_sommets(); i++) {
			for(int j = 0 ; j < this.getNb_sommets(); j++) {
				if(m[i][j] == 0.0) {
					m[i][j] = inf;
				}
			}
		}
		
		
		// début de l'algo
		for(int k = 0; k < this.getNb_sommets(); k++) {
			for(int i = 0; i < this.getNb_sommets(); i++) {
				for(int j = 0; j < this.getNb_sommets(); j++) {
					m[i][j]=Math.min(m[i][j],m[i][k]+m[k][j]);
					
					//remplissage du tableau de String
					if (String.valueOf(Math.min(m[i][j],m[i][k]+m[k][j]))==String.valueOf(inf)) {
						t[h]="+oo";
					}
					else {
						t[h]=String.valueOf(Math.min(m[i][j],m[i][k]+m[k][j]));
					}
				}h++;
		
			}
		}					
	   
		String max = t[0];
		for(String x : t) { 
			if( x.length() > max.length()) {
					max=x;
			}
		}
		int p = max.length();
	    
		String s = "\n\nHere is the Floyd Warshall matrix :\n";
		
        // numérotation des colonnes
        s+="\n      ";
        for(int b = 0; b < this.nb_sommets; b++){
        	s+=b+"";
        	for(int c = 0; c < p ; c++) {
        		s+=" ";
        	}
        }
        s+="\n";
        
        
        // le problème vient des espaces qui ne sont pas ajustés après les +oo
        
        
        // numérotation des lignes plus affichage de la matrice 
        for (int k = 0 ; k < this.nb_sommets; k++){
        	s+="\n" + k + "   |";
        	for( int j = 0 ; j < this.nb_sommets; j++){
        		
        		String s_val = String.valueOf((int)(m[k][j])); 
        		
        		int difference = p-s_val.length();// Différence entre la longueur de val et la longueur de la pli pour ajuster les espaces
        		
        		if(m[k][j]!=Double.POSITIVE_INFINITY) {
        			s+=" "+(int)m[k][j];
        		}
        		else{
        			s+=" +oo";
        		}
        		
        		// on ajuste les espaces
        		for(int a=0 ; a<difference; a++) {
        			s+=" ";
        		}	
        	}
		
        	s+=" |";
        }

		return s;	
	}
	
	
	public int getNb_sommets() {
		return nb_sommets;
	}



	public void setNb_sommets(int nb_sommets) {
		this.nb_sommets = nb_sommets;
	}



	public int getNb_arretes() {
		return nb_arretes;
	}



	public void setNb_arretes(int nb_arretes) {
		this.nb_arretes = nb_arretes;
	}



	public double[][] getMatrice() {
		return matrice;
	}



	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}
	
	
	
	public int getPli() {
		return pli;
	}



	public void setPli(int pli) {
		this.pli = pli;
	}



	@Override
	public String toString() {
		
        String s = "";
        
        // numérotation des colonnes
        s+="\n      ";
        for(int b = 0; b < nb_sommets; b++){
        	s+=b+"";
        	for(int c = 0; c < pli ; c++) {
        		s+=" ";
        	}
        }
        s+="\n";
        
        // numérotation des lignes plus affichage de la matrice 
        for (int k = 0 ; k < nb_sommets; k++){
        	s+="\n" + k + "   |";
        	for( int j = 0 ; j < nb_sommets; j++){
      
        		String s_val = String.valueOf((int)matrice[k][j]); 
        		int difference = this.pli-s_val.length(); // Différence entre la longueur de val et la longueur de la pli pour ajuster les espaces
        		s+=" "+(int)matrice[k][j];
        		
        		// on ajuste les espaces
        		for(int a=0 ; a<difference; a++) {
        			s+=" ";
        		}	
        	}
        	s+=" |";
        }
        return s;
        
	}
}