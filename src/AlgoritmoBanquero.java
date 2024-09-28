import java.util.Scanner;

public class AlgoritmoBanquero {
    private int n; // Número de procesos
    private int m; // Número de recursos
    private int[][] max; // Matriz máxima
    private int[][] asignado; // Matriz asignada
    private int[][] necesidad; // Matriz de necesidades
    private int[] disponible; // Recursos disponibles

    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        // Ingreso de datos
        System.out.print("Ingresa el número de procesos: ");
        n = sc.nextInt();

        System.out.print("Ingresa el número de recursos: ");
        m = sc.nextInt();

        max = new int[n][m];
        asignado = new int[n][m];
        necesidad = new int[n][m];
        disponible = new int[m];

        // Ingresar la matriz máxima
        System.out.println("Ingresa la matriz máxima:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        // Ingresar la matriz asignada
        System.out.println("Ingresa la matriz asignada:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                asignado[i][j] = sc.nextInt();
            }
        }

        // Ingresar recursos disponibles
        System.out.println("Ingresa los recursos disponibles:");
        for (int j = 0; j < m; j++) {
            disponible[j] = sc.nextInt();
        }

        // Calcular la matriz de necesidades
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                necesidad[i][j] = max[i][j] - asignado[i][j];
            }
        }

        // Verificar si hay un estado seguro
        if (estadoSeguro()) {
            System.out.println("El sistema está en un estado seguro.");
        } else {
            System.out.println("El sistema no está en un estado seguro.");
        }
    }

    private boolean estadoSeguro() {
        int[] trabajo = new int[m];
        boolean[] finalizado = new boolean[n];

        // Inicializar trabajo con los recursos disponibles
        for (int i = 0; i < m; i++) {
            trabajo[i] = disponible[i];
        }

        // Comprobar si todos los procesos pueden finalizar
        while (true) {
            boolean asignacionEncontrada = false;

            for (int i = 0; i < n; i++) {
                if (!finalizado[i] && puedeFinalizar(i, trabajo)) {
                    // Simular la finalización del proceso
                    for (int j = 0; j < m; j++) {
                        trabajo[j] += asignado[i][j];
                    }

                    finalizado[i] = true;
                    asignacionEncontrada = true;
                    System.out.println("El proceso " + i + " puede finalizar.");
                }
            }

            // Si no se pudo encontrar una asignación, termina el ciclo
            if (!asignacionEncontrada) {
                break;
            }
        }

        // Verificar si todos los procesos finalizaron
        for (boolean b : finalizado) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    // Verifica si el proceso 'i' puede finalizar con los recursos actuales
    private boolean puedeFinalizar(int i, int[] trabajo) {
        for (int j = 0; j < m; j++) {
            if (necesidad[i][j] > trabajo[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AlgoritmoBanquero ab = new AlgoritmoBanquero();
        ab.iniciar();
    }
}
