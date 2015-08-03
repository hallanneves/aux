/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andressa Freitas
 */
public class Dijkstra {

    public int anterior[];
    private long best[];

    public void Dijkstra() // Added a start point.
    {
        synchronized (TrabRot.tabela) {
            // Dijkstra's Algorithm
            best = new long[TrabRot.comp];
            boolean[] visited = new boolean[TrabRot.comp];
            anterior = new int[TrabRot.comp];

            for (int i = 0; i < TrabRot.comp; i++) {
                best[i] = Integer.MAX_VALUE;
                visited[i] = false;
            }

            best[0] = 0; // Changed the 0 to variable start.
            anterior[0] = -1;

            while (true) {
                int v = -1;
                for (int j = 0; j < TrabRot.comp; j++) {
                    if (!visited[j] && (v < 0 || best[j] < best[v])) {
                        v = j;
                    }
                }
                if (v < 0 || best[v] == Integer.MAX_VALUE) {
                    break;
                }
                visited[v] = true;
                for (int i = 0; i < TrabRot.comp; i++) {
                    long w = TrabRot.tabela[v][i][0];

                    if (best[i] > best[v] + w) {
                        best[i] = best[v] + w;
                        anterior[i] = v;
                    }
                }

            }
        }
        System.out.print("Vetor Anterior [");
        for (int cont = 0; cont < anterior.length; cont++) {
            if (cont != 0) {
                System.out.print("[");
                System.out.print(anterior[cont]);
                System.out.print("],");
            } else {
                System.out.print(anterior[cont]);
                System.out.print("],");
            }
        }
    }

}
