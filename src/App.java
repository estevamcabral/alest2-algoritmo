import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
 
    public static String mudarDirection(String directionAtual, char caracterDeCurva) {
        if (caracterDeCurva=='/') {
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
        if (caracterDeCurva=='\\') {
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

        long tempoInicial = System.currentTimeMillis();
        File file = new File("src/casoD50.txt");
        Scanner scanner = new Scanner(file);
        String[] tamanho = scanner.nextLine().split(" ");
        int tamLinhas = Integer.parseInt(tamanho[0]);
        int tamColunas = Integer.parseInt(tamanho[1]);
        char[][] mapa = new char[tamLinhas][tamColunas];

        for (int i = 0; i < tamLinhas && scanner.hasNextLine(); i++) {
            String[] linha = scanner.nextLine().split("");
            for (int j = 0; j < linha.length && j < tamColunas; j++) {
                mapa[i][j] = linha[j].charAt(0);
            }
        }
        
        scanner.close();
        char caracterPercorrido;

        int percorrerLinhas = 0, percorrerColunas = 0;
        String direction = "direita";

        LinkedList<String> listaDinheiroCapturado = new LinkedList<>();

        //começo do mapa
        for (int i = 0; i < mapa.length; i++) {
            if (mapa[i][0] != '\0' && mapa[i][0]=='-') {
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

            if (caracterPercorrido=='#')
                break;
                
            if (caracterPercorrido=='/' || caracterPercorrido=='\\') {
                direction = mudarDirection(direction, caracterPercorrido);
            }

            if (Character.isDigit(caracterPercorrido)) {
                listaDinheiroCapturado.add(String.valueOf(caracterPercorrido));
            }

            char prox = '\0';
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
            prox = mapa[percorrerLinhas][percorrerColunas];

            if (Character.isDigit(caracterPercorrido)&& !Character.isDigit(prox)) {
                listaDinheiroCapturado.add("-");
            }
        }

        String listAsString = String.join("", listaDinheiroCapturado);
        String numeros[] = listAsString.split("-");

        //System.out.println("Ordem do dinheiro encontrado:");
        long soma = 0;

        for(String numero : numeros){
            if (Long.parseLong(numero) > 0){
              //  System.out.println(Long.parseLong(numero)); < Descomentar para ordem
                soma += Long.parseLong(numero);
            }
        }
        System.out.println("Total de dinheiro encontrado: " + soma);
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo de execução para o caso D50: " + (tempoFinal - tempoInicial)+" milissegundos.");
    }
}
