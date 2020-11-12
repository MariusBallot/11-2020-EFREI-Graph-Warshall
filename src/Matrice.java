

public class Matrice {

	private int[][] tab;
	private String[]tab_s;
	private int n;
	private int m;
	private int pli;
	
	public Matrice(final int n,final int m) {
		this.tab = new int[n][m];
		this.tab_s = new String[3*n];
		this.n = n;
		this.m = m;
	}
	
	public Matrice(Graphe g, int pli) {
		this.tab = g.getMatrice();
		this.pli = pli;
		this.n = tab.length;
		this.m = tab[0].length;
		
		
	}

    // donne la longueur de l'item le plus "long" de la matrice pour adapter l'affichage en fonction de lui
	
	public int calcul_pli(){
    String max =tab_s[0];
    for(String x : this.tab_s) {
    	if(x.length()>max.length()) {
    		max=x;
    	}
    }
    return max.length();
	}
	
	
	
	public int[][] getTab() {
		return tab;
	}
	
	public int getTab_ij(int i, int j) {
		return tab[i][j];
	}

	public void setTab(int[][] tab) {
		this.tab = tab;
	}
	
	public void setTab_ij(int i, int j, int x) {
		tab[i][j]=x;
	}

	public String[] getTab_s() {
		return tab_s;
	}

	public void setTab_s(String[] tab_s) {
		this.tab_s = tab_s;
	}
	
	public void setTab_sI(int i, String x) {
		this.tab_s[i] = x;
	}
	
	public String getTab_sI(int i) {
		return this.tab_s[i];
	}


	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}


	public int getM() {
		return m;
	}


	public void setM(int m) {
		this.m = m;
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
        for(int b = 0; b < this.n ; b++){
        	s+=b+"";
        	for(int c = 0; c < this.pli ; c++) {
        		s+=" ";
        	}
        }
        s+="\n";
        
        // numérotation des lignes plus affichage de la matrice 
        for (int k = 0 ; k < this.n; k++){
        	s+="\n" + k + "   |";
        	for( int j = 0 ; j < this.n; j++){
        		
        		String s_val = Integer.toString(this.tab[k][j]); 
        		int difference = this.pli-s_val.length(); // Différence entre la longueur de val et la longueur de la pli pour ajuster les espaces
        		s+=" "+this.tab[k][j];
        		
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
