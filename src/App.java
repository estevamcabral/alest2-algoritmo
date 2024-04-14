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

        while (true) {
            try {
                caracterPercorrido = mapa[percorrerLinhas][percorrerColunas];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("error " + e.getMessage());
                break;
            }

            if (caracterPercorrido.equals("#"))
                break;
                
            if ((caracterPercorrido.equals("/") && (percorrerLinhas-1 >= 0 || direction.equals("esquerda")))|| caracterPercorrido.equals("\\")) {
                direction = mudarDirection(direction, caracterPercorrido);
            }

            if (caracterPercorrido.matches("[0-9.]+") && Integer.parseInt(caracterPercorrido) >= 0)
                listaDinheiroCapturado.add(caracterPercorrido);
            else
                listaDinheiroCapturado.add("-");

            switch (direction) {
                case "direita":
                    percorrerColunas++;
                    break;
                case "esquerda":
                    percorrerColunas--;
                    break;
                case "cima":
                    percorrerLinhas--;
                    break;
                case "baixo":
                    percorrerLinhas++;
                    break;
                default:
                    System.out.println("curva não permitida!");
                    break;
            }
        }

        for (String dinheiro : listaDinheiroCapturado) {
            System.out.print(dinheiro + " ");
        }
    }
}
