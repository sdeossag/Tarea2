import java.util.*;

class Arista {
    int origen, destino, peso;

    Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}


public class BellmanFord {
    int V, E;
    List<Arista> aristas;

    BellmanFord(int V, int E) {
        this.V = V;
        this.E = E;
        aristas = new ArrayList<>();
    }

    void agregarArista(int origen, int destino, int peso) {
        aristas.add(new Arista(origen, destino, peso));
    }

    void ejecutarBellmanFord(int origen) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[origen] = 0;

        for (int i = 1; i < V; i++) {
            for (Arista arista : aristas) {
                int u = arista.origen;
                int v = arista.destino;
                int peso = arista.peso;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                }
            }
        }

        for (Arista arista : aristas) {
            int u = arista.origen;
            int v = arista.destino;
            int peso = arista.peso;

            if (dist[u] != Integer.MAX_VALUE && dist[u] + peso < dist[v]) {
                System.out.println("El grafo contiene un ciclo negativo.");
                return;
            }
        }

        imprimirSolucion(dist, origen);
    }

    void imprimirSolucion(int[] dist, int origen) {
        System.out.println("Distancias desde el vértice fuente " + origen + ":");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Vértice " + i + ": no alcanzable");
            } else {
                System.out.println("Vértice " + i + ": " + dist[i]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 8;
        BellmanFord grafo = new BellmanFord(V, E);

        grafo.agregarArista(0, 1, -1);
        grafo.agregarArista(0, 2, 4);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(1, 3, 2);
        grafo.agregarArista(1, 4, 2);
        grafo.agregarArista(3, 2, 5);
        grafo.agregarArista(3, 1, 1);
        grafo.agregarArista(4, 3, -3);

        grafo.ejecutarBellmanFord(0);
    }
}
