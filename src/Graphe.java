/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graphe {
	
	private int nb_sommets;
	
	private int nb_arretes;
	
	private Matrice matrice;
	
	private String nom;
	
	
	
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
            this.matrice = new Matrice(nb_sommets,nb_sommets);
           
            
            matrice.setTab_s(new String[3*nb_arretes]) ;// va nous servir pour améliorer l'affichage
            int[] tab = new int[3*nb_arretes]; //initialisation du tableau contenant les valeurs du graphes.
            int i = 0;
            while(scanner.hasNext()) { //Scan mot par mot
            
            	String x = scanner.next(); 
            	matrice.setTab_sI(i,x); // remplissage de tab_s
            	int v_x = Integer.parseInt(x);
            	tab[i] = v_x; // remplissage de tab
            	i++;
            }
            
            // donne la longueur de l'item le plus "long" de la matrice pour adapter l'affichage en fonction de lui
            String max = matrice.getTab_sI(0);
            for(String x : matrice.getTab_s()) {
            	if(x.length() > max.length()) {
            		max=x;
            	}
            }
            matrice.setPli(max.length());
            
            
            
            // remplissage de la matrice
            for (int n = 0 ; n<tab.length; n=n+3) {
            	int a = tab[n]; // sommet a 
            	int b = tab[n+1]; // sommet b
            	int val = tab[n+2]; // valeur de l'arrete (a,b)
            	matrice.setTab_ij(a,b,val);
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
		

	
	public void Floyd_Warshall() {
		Matrice m = this.matrice;
		String t[] = new String[3*matrice.getN()];
		for(int k = 0; k < this.getNb_sommets(); k++) {
			for(int i = 0; i < this.getNb_sommets(); i++) {
				for(int j = 0; j < this.getNb_sommets(); j++) {
					m.setTab_ij(i,j,Math.min(m.getTab_ij(i,j),m.getTab_ij(i,k)+m.getTab_ij(k,j)));
				}
			}
		}
		String s = "\nLa matrice obtenue après l'algorihme de Floyd-Warshall est la suivante :\n";
		s+=m.toString();
		System.out.println(s);
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
	
	

	public Matrice getMatrice() {
		return matrice;
	}



	public void setMatrice(Matrice matrice) {
		this.matrice = matrice;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	@Override
	public String toString() {
        String s = "\nLa matrice représentant le graphe " + nom + " est :\n";
        return s+= matrice.toString();
        
	}
}
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graphe {
	
	private int nb_sommets;
	
	private int nb_arretes;
	
	private int[][] matrice;
	
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
            this.matrice = new int [nb_sommets][nb_sommets];
           
            
            String[] tab_s = new String[3*nb_arretes]; // va nous servir pour améliorer l'affichage
            int[] tab = new int[3*nb_arretes]; //initialisation du tableau contenant les valeurs du graphes.
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
            	int a = tab[n]; // sommet a 
            	int b = tab[n+1]; // sommet b
            	int val = tab[n+2]; // valeur de l'arrete (a,b)
            	matrice[a][b]=val;
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
		

	
	public void Floyd_Warshall() {
		String[] t = new String[matrice.length*matrice[0].length];
		int[][] m = this.getMatrice();
		int h =0;
		for(int k = 0; k < this.getNb_sommets(); k++) {
			for(int i = 0; i < this.getNb_sommets(); i++) {
				for(int j = 0; j < this.getNb_sommets(); j++) {
					m[i][j]=Math.min(m[i][j],m[i][k]+m[k][j]);
					t[h]=String. valueOf(Math.min(m[i][j],m[i][k]+m[k][j]));// reste à trouver le pli de t
				}h++;
		
			}
		}					
	    //int p = this.pli; // le problème viens d'ici c'est pas le pli de la matrice du graphe mais celui de la matrice de Floyd
		String max =t[0];
		for(String x : t) {
		    if(x.length()>max.length()) {
		   		max=x;
		    }
		}
		int p = max.length();
			
	    
		String s = "\nLa matrice obtenue après l'algorihme de Floyd-Warshall est la suivante :\n";
		
        // numérotation des colonnes
        s+="\n      ";
        for(int b = 0; b < this.nb_sommets; b++){
        	s+=b+"";
        	for(int c = 0; c < p ; c++) {
        		s+=" ";
        	}
        }
        s+="\n";
        
        // numérotation des lignes plus affichage de la matrice 
        for (int k = 0 ; k < this.nb_sommets; k++){
        	s+="\n" + k + "   |";
        	for( int j = 0 ; j < this.nb_sommets; j++){
        		
        		String s_val = Integer.toString(m[k][j]); 
        		int difference = this.pli-s_val.length(); // Différence entre la longueur de val et la longueur de la pli pour ajuster les espaces
        		s+=" "+m[k][j];
        		
        		// on ajuste les espaces
        		for(int a=0 ; a<difference; a++) {
        			s+=" ";
        		}	
        	}
        	s+=" |";
        }

		System.out.println(s);	
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



	public int[][] getMatrice() {
		return matrice;
	}



	public void setMatrice(int[][] matrice) {
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
        String s = "\nLa matrice représentant le graphe " + nom + " est :\n";
        
        // numérotation des colonnes
        s+="\n      ";
        for(int b = 0; b < nb_sommets; b++){
        	s+=b+"";
        	for(int c = 0; c < this.pli ; c++) {
        		s+=" ";
        	}
        }
        s+="\n";
        
        // numérotation des lignes plus affichage de la matrice 
        for (int k = 0 ; k < nb_sommets; k++){
        	s+="\n" + k + "   |";
        	for( int j = 0 ; j < nb_sommets; j++){
        		
        		String s_val = Integer.toString(matrice[k][j]); 
        		int difference = this.pli-s_val.length(); // Différence entre la longueur de val et la longueur de la pli pour ajuster les espaces
        		s+=" "+matrice[k][j];
        		
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
