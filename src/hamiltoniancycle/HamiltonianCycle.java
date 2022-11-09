/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hamiltoniancycle;

/* Program Java untuk solusi masalah Hamiltonian Cycle
menggunakan backtracking */
class HamiltonianCycle 
{ 
	final int V = 5; 
	int path[]; 

	/* Fungsi utilitas untuk memeriksa apakah vertex v bisa
ditambahkan pada indeks 'pos'in dalam Hamiltonian Cycle
dibangun sejauh ini (disimpan di 'jalur []') */

	boolean isSafe(int v, int graph[][], int path[], int pos) 
	{ 
		/* Periksa apakah simpul ini adalah simpul yang berdekatan dari vertex yang sebelumnya ditambahkan. */
		if (graph[path[pos - 1]][v] == 0) 
			return false; 

		/* Periksa apakah vertex sudah termasuk.
Langkah ini dapat dioptimalkan dengan membuat array
dari ukuran V */
		for (int i = 0; i < pos; i++) 
			if (path[i] == v) 
				return false; 

		return true; 
	} 

	/* Fungsi utilitas rekursif untuk menyelesaikan hamiltonian
masalah siklus */
	boolean hamCycleUtil(int graph[][], int path[], int pos) 
	{ 
		/* kasus dasar: Jika semua simpul termasuk dalam
Siklus Hamiltonian */
		if (pos == V) 
		{ 
			// Dan jika ada keunggulan dari yang terakhir termasuk
// vertex ke vertex pertama
			if (graph[path[pos - 1]][path[0]] == 1) 
				return true; 
			else
				return false; 
		} 

		// Coba berbagai simpul sebagai kandidat berikutnya
// Siklus Hamilton. Kami tidak mencoba untuk 0 seperti kami
// termasuk 0 sebagai titik awal di hamCycle ()
		for (int v = 1; v < V; v++) 
		{ 
			/* Periksa apakah simpul ini dapat ditambahkan ke Hamiltonian
Siklus */
			if (isSafe(v, graph, path, pos)) 
			{ 
				path[pos] = v; 

				/* berulang untuk membangun sisa jalan */
				if (hamCycleUtil(graph, path, pos + 1) == true) 
					return true; 

				/* Jika menambahkan vertex v tidak mengarah ke solusi,
lalu hapus */
				path[pos] = -1; 
			} 
		} 

		/* Jika tidak ada titik dapat ditambahkan ke Hamiltonian Cycle
dibangun sejauh ini, lalu mengembalikan false */
		return false; 
	} 

	/* Fungsi ini memecahkan masalah Hamiltonian Cycle menggunakan
Mundur. Ini terutama menggunakan hamCycleUtil () untuk menyelesaikan
masalah. Ini mengembalikan false jika tidak ada Siklus Hamiltonian
mungkin, jika tidak, kembalikan true dan cetak path.
Harap perhatikan bahwa mungkin ada lebih dari satu solusi,
fungsi ini mencetak salah satu solusi yang layak. */
	int hamCycle(int graph[][]) 
	{ 
		path = new int[V]; 
		for (int i = 0; i < V; i++) 
			path[i] = -1; 

		/* Mari kita beri titik 0 sebagai titik pertama di jalan.
Jika ada Siklus Hamilton, maka jalurnya bisa
dimulai dari titik siklus apa pun sebagaimana grafiknya
tidak diarahkan */
		path[0] = 0; 
		if (hamCycleUtil(graph, path, 1) == false) 
		{ 
			System.out.println("\nSolution does not exist"); 
			return 0; 
		} 

		printSolution(path); 
		return 1; 
	} 

	/* Fungsi utilitas untuk mencetak solusi */
	void printSolution(int path[]) 
	{ 
		System.out.println("Ini adalah hasil dari Implementasi Sirkuit Hamilton :\n"+
"           (0)--(1)--(2) \n" +
"            |   / \\   | \n" +
"            |  /   \\  | \n" +
"            | /     \\ | \n" +
"           (3)-------(4) "); 
		for (int i = 0; i < V; i++) 
			System.out.print(" " + path[i] + " "); 

		// Mari kita cetak simpul pertama lagi untuk menunjukkan
// siklus lengkap
		System.out.println(" " + path[0] + " "); 
	} 

	// program driver untuk menguji fungsi di atas
	public static void main(String args[]) 
	{ 
		HamiltonianCycle hamiltonian = 
		new HamiltonianCycle(); 
		int graph1[][] = {{0, 1, 0, 1, 0}, 
			{1, 0, 1, 1, 1}, 
			{0, 1, 0, 0, 1}, 
			{1, 1, 0, 0, 1}, 
			{0, 1, 1, 1, 0}, 
		}; 

		// Cetak solusinya 
		hamiltonian.hamCycle(graph1); 

				int graph2[][] = {{0, 1, 0, 1, 0}, 
			{1, 0, 1, 1, 1}, 
			{0, 1, 0, 0, 1}, 
			{1, 1, 0, 0, 0}, 
			{0, 1, 1, 0, 0}, 
		}; 

		// Print the solution 
		hamiltonian.hamCycle(graph2); 
	} 
} 
