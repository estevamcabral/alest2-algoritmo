import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        int linhas, colunas;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o numero de linhas e colunas desejado para seu mapa.");
        linhas = sc.nextInt();
        colunas = sc.nextInt();
        sc.nextLine();

        //lendo mapa
        String[][] mapa = new String[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            String str = sc.nextLine();
            String[] linha = str.split("");
                for (int j = 0;  j < linha.length; j++) {
                    mapa[i][j] = linha[j];
                }
        }
    
   

        int percorrerLinhas = 0, percorrerColunas = 0;
        String caracterPercorrido;

        LinkedList<Integer> listaDinheiroCapturado = new LinkedList<>();

        while(true){
            try {
                caracterPercorrido = mapa[percorrerLinhas][percorrerColunas];
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }

            if (caracterPercorrido.matches("[0-9.]+") && Integer.parseInt(caracterPercorrido) >= 0)
                listaDinheiroCapturado.add(Integer.parseInt(caracterPercorrido));

            percorrerColunas++;

        }
        sc.close();
    }
}
