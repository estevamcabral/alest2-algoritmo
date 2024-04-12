import java.util.LinkedList;
import java.util.Scanner;

public class App {

    public static String mudarDirection(String directionAtual, String caracterDeCurva) {
        if (caracterDeCurva == "/"){
            switch(directionAtual){
                //validar a direção atual e quando faz uma curva vai para qual,
                //exemplo ta na esquerda tem curva qual vai ser a direção?
                // a direção varia dependendo se é \ ou /
                // dai retorna a nova direção, que vai ser atribuida aquela variavel principal
            }
        }
        if(caracterDeCurva == "\""){
            switch(directionAtual){
                //aqui a mesma coisa
            }
        }

        return "";
    }

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

        //Direção com valor default como direita.
        String direction = "direita";


        LinkedList<String> listaDinheiroCapturado = new LinkedList<>();


        while(true){
            try {
                caracterPercorrido = mapa[percorrerLinhas][percorrerColunas];
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }

            if(caracterPercorrido == "#") break;

            if(caracterPercorrido == "/" || caracterPercorrido == "\""){
                direction = mudarDirection(direction, caracterPercorrido);
            } 

            
            // por enquanto podemos pegar o dinheiro e aplicar um separador, a lista ficaria assim
            // Exemplo: --80----10---23--10--98-78
            // Depois fica facil de pegar a ordem e transformar para inteiro, depois podemos discutir melhor a forma mais ideal de fazer isso
            if (caracterPercorrido.matches("[0-9.]+") && Integer.parseInt(caracterPercorrido) >= 0)
                listaDinheiroCapturado.add(caracterPercorrido);
                else listaDinheiroCapturado.add("-");

            switch (direction) {
                case "direita":
                    percorrerColunas++;
                    break;
                case "esquerda":
                    percorrerColunas--;
                    break;
                case "cima":
                    percorrerLinhas--;
                case "baixo":
                    percorrerLinhas++;
                default:
                    break;
            }
        }


        for (String dinheiro : listaDinheiroCapturado) {
            System.out.println(dinheiro);
        }
        sc.close();
    }
}
