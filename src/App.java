import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static String mudarDirection(String directionAtual, String caracterDeCurva) {
        if (caracterDeCurva.equals("/")) {
            switch (directionAtual) {
                case "cima":
                    return "direita";
                case "baixo":
                    return "esquerda";
                case "esquerda":
                    return "baixo";
                case "direita":
                    return "cima";

                default:
                    break;
            }
        }
        if (caracterDeCurva.equals("\\")) {
            switch (directionAtual) {
                case "direita":
                    return "baixo";
                case "esquerda":
                    return "cima";
                case "baixo":
                    return "direita";
                case "cima":
                    return "esquerda";

                default:
                    break;
            }
        }

        return directionAtual;
    }

    public static void main(String[] args) throws Exception {

        File file = new File("src/casoD50.txt");
        Scanner scanner = new Scanner(file);

        String[][] mapa = new String[50][50];

        for (int i = 0; i < 50 && scanner.hasNextLine(); i++) {
            String[] linha = scanner.nextLine().split("");
            for (int j = 0; j < linha.length && j < 50; j++) {
                mapa[i][j] = linha[j];
            }
        }
        
        scanner.close();

        int percorrerLinhas = 0, percorrerColunas = 0;
        String caracterPercorrido;

        String direction = "direita";

        LinkedList<String> listaDinheiroCapturado = new LinkedList<>();

        //começo do mapa
        for (int i = 0; i < mapa.length; i++) {
            if (mapa[i][0] != null && mapa[i][0].equals("-")) {
                percorrerLinhas = i;
                break; // Assim que encontrarmos o "-", podemos sair do loop
            }
        }

        while (true) {
            try {
                caracterPercorrido = mapa[percorrerLinhas][percorrerColunas];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("error " + e.getMessage());
                break;
            }

            if (caracterPercorrido.equals("#"))
                break;
                
            if (caracterPercorrido.equals("/") || caracterPercorrido.equals("\\")) {
                direction = mudarDirection(direction, caracterPercorrido);
            }

            if (caracterPercorrido.matches("[0-9.]+") && Integer.parseInt(caracterPercorrido) >= 0)
            {
                listaDinheiroCapturado.add(caracterPercorrido);
            }

            String prox = "";
            switch (direction) {
                case "direita":
                    percorrerColunas++;
                    prox = mapa[percorrerLinhas][percorrerColunas];
                    break;
                case "esquerda":
                    percorrerColunas--;
                    prox = mapa[percorrerLinhas][percorrerColunas];
                    break;
                case "cima":
                    percorrerLinhas--;
                    prox = mapa[percorrerLinhas][percorrerColunas];
                    break;
                case "baixo":
                    percorrerLinhas++;
                    prox = mapa[percorrerLinhas][percorrerColunas];
                    break;
                default:
                    System.out.println("curva não permitida!");
                    break;
            }
            if (!prox.matches("[0-9.]+") && caracterPercorrido.matches("[0-9.]+")){
                listaDinheiroCapturado.add("-");
            }
        }
        for (String dinheiro : listaDinheiroCapturado) {
            System.out.print(dinheiro + " ");
        }
    }
}
