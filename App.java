import java.io.*;
import java.util.Scanner;

public class App {
    static Scanner scanner=new Scanner(System.in);

    //Método para exibir o menu inicial
    public static int menu(){
        int op; 
        do {
            System.out.print("---------MENU 1--------");
            System.out.println("\n0 - Sair\n1 - Cadastrar local\n2 - Marcar local como visitado\n3 - Escolher um local para ir\n4 - Deletar um local");
            System.out.println("-------------------------");
            System.out.print("Digite sua opcao: ");
            op=scanner.nextInt();
            if(op > 4 || op < 0)System.out.println("Opcao invalida!");
        } while (op > 4 || op < 0);
        return op;
    }
    
    //Método para ler um local a ser cadastrado
    public static String le(){
        String tipo;
        String nome;
        String preco;
        String inedito;
        String turno;
        String total="\n";
        
        //Leitura do tipo
        int tipoI;
        do{
            System.out.println("---------MENU 2--------");
            System.out.println("0 - Restaurante");
            System.out.println("1 - Barzinho");
            System.out.println("2 - Balada");
            System.out.println("3 - Cafe");
            System.out.println("4 - Ar livre");
            System.out.println("-------------------------");
            System.out.print("Digite o tipo do local de acordo com os codigos: ");
            scanner.nextLine();
            tipo=scanner.nextLine();
            tipoI=Integer.parseInt(tipo);
            if(tipoI>4||tipoI<0)System.out.println("\nOpcao invalida!");
        }while(tipoI>4||tipoI<0);
        total=total+tipo+",";

        //Leitura do nome
        String nomeU;
        do{
            System.out.print("Digite o nome do local: ");
            nome=scanner.nextLine();
            if(nome.length()>20)System.out.println("\nErro! Maximo 20 caracteres");
        }while(nome.length()>20);
        nomeU=nome.toUpperCase();
        total=total+nomeU+",";

        //Leitura do preço
        int precoI;
        do{
            System.out.print("Qual o preço [1-3] sendo 1 barato e 3 caro? ");
            preco = scanner.nextLine();
            precoI=Integer.parseInt(preco);
            if(precoI>3||precoI<0)System.out.println("\nOpcao invalida! ");
        }while(precoI>3||precoI<0);
        total=total+preco+",";
        
        //Leitura se o local é inedito ou nao
        String ineditoU;
        do{
            System.out.print("O local é inédito [SIM/NAO]? ");
            inedito=scanner.nextLine();
            ineditoU=inedito.toUpperCase();
            if(!ineditoU.equals("SIM")&&!ineditoU.equals("NAO"))System.out.println("Opcao invalida! ");
        }while(!ineditoU.equals("SIM")&&!ineditoU.equals("NAO"));
        total=total+ineditoU+",";

        //Leitura do turno
        String turnoU;
        do{
            System.out.print("O local é para dia, noite ou ambos? ");
            turno=scanner.nextLine();
            turnoU=turno.toUpperCase();
            if(!turnoU.equals("AMBOS")&&!turnoU.equals("DIA")&&!turnoU.equals("NOITE"))System.out.println("Opcao invalida!");
        }while(!turnoU.equals("AMBOS")&&!turnoU.equals("DIA")&&!turnoU.equals("NOITE"));
        total=total+turnoU;
        return total;
    }
    
    //Método para alterar o status inédito
    public static void marcaVisita(String nome){
        try (BufferedReader leitor = new BufferedReader(new FileReader("locais.txt"))) {
            String linha;//guarda o conteudo da linha atual
            String linhaUt="";//guarda a linha do local procurado
            String arquivo="";//concatena todas as linhas 
            String novaLinha="";//replace da linha com o sim
            while ((linha = leitor.readLine()) != null) {
                arquivo+=linha+"\n";//armazena todas as linhas
                Scanner sc2=new Scanner(linha);
                sc2.useDelimiter(",");
                int c=0;
                String nm="";
                while(sc2.hasNext()){
                    String aux=sc2.next();//parte a linha entre as virgulas
                    if(c==1){// se for o atributo nome
                        nm=aux;
                        break;
                    }
                    c++;
                }
                if (nm.equals(nome)){
                    linhaUt=linha;
                    novaLinha=linha.replace("SIM","NAO");
                }
                sc2.close();
            }
            String novoArquivo=arquivo.replace(linhaUt, novaLinha);
    
            try (FileWriter fileWriter = new FileWriter("locais.txt")) {
                fileWriter.write(novoArquivo);
                System.out.println("\nAtualizacao realizada com sucesso!\n");
            } catch (IOException e) {   
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método para excluir um local
    public static void exclui(String nome){
        try (BufferedReader leitor = new BufferedReader(new FileReader("locais.txt"))) {
            String linha;//guarda o conteudo da linha atual
            String arquivo="";//concatena todas as linhas 
            while ((linha = leitor.readLine()) != null) {

                Scanner sc2=new Scanner(linha);
                sc2.useDelimiter(",");
                int c=0;
                String nm="";
                while(sc2.hasNext()){
                    String aux=sc2.next();//parte a linha entre as virgulas
                    if(c==1){// se for o atributo nome
                        nm=aux;
                        break;
                    }
                    c++;
                }
                if (!nm.equals(nome)){
                    arquivo+=linha+"\n";
                }
            }
           
            try (FileWriter fileWriter = new FileWriter("locais.txt")) {
                fileWriter.write(arquivo);
                System.out.println("\nLocal excluido com sucesso!\n ");
            } catch (IOException e) {   
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método para cadastrar um local no arquivo
    public static void escreveArquivo(String str){
        String nomeArq = "locais.txt";
        
        try (FileWriter fileWriter = new FileWriter(nomeArq,true)) {
            fileWriter.write(str);
            System.out.println("\nLocal cadastrado com sucesso!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void contaPontos(String preferencia){
          try (BufferedReader leitor = new BufferedReader(new FileReader("locais.txt"))) {
            String linha;
            String[]atributos=new String[5];
            int[]pontuacao=new int[20];
            
            int qtLinha=0;
            while ((linha = leitor.readLine()) != null) {
                int pontos=0;
                Scanner sc2=new Scanner(linha);
                Scanner sc3=new Scanner(preferencia);
                sc2.useDelimiter(",");
                qtLinha++;
                while(sc2.hasNext()){
                    String aux=sc2.next();//parte a linha entre as virgulas
                    String aux2=sc3.next();
                    if(aux.equals(aux2))pontos++;
                }
                pontuacao[qtLinha]=pontos;
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    

    public static void main(String[] args) {
        
        int opcao;
        String nome;
        do{
            opcao = menu();
            if(opcao!=0){
                switch (opcao){
                    case 0:
                        System.out.println("\nAté a próxima!\n");
                            break;
                    case 1:
                        escreveArquivo(le());
                            break;

                    case 2:
                        System.out.print("Digite o nome do local que voce deseja marcar como visitado: ");
                        scanner.nextLine();
                        nome=scanner.nextLine();
                        String nomeU=nome.toUpperCase();
                        marcaVisita(nomeU);
                            break;
                    case 3:
                        
                             break;
                    case 4:
                        System.out.print("Digite o nome do local que voce deseja excluir: ");
                        scanner.nextLine();
                        nome=scanner.nextLine();
                        String nomeM=nome.toUpperCase();
                        exclui(nomeM);
                            break;
                        
                }
            }
            
        } while (opcao != 0); 
    }
}
