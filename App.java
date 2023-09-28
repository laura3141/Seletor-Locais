import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class App {
    static Scanner scanner=new Scanner(System.in);
    public static int menu(){
        int op; 
        do {
            System.out.println("\n0-Sair\n1-Cadastrar local\n2-Marcar local como visitado\n3-Escolher um local para ir\n4-Deletar um local\nDigite sua opcao: ");
            op=scanner.nextInt();
            if(op > 4 || op < 0)System.out.println("Opcao invalida!");
        } while (op > 4 || op < 0);
        return op;
    }
    
    public static String le(){
        String tipo;
        String nome;
        String preco;
        String inedito;
        String turno;
        String total="";
        
        int tipoI;
        do{
            System.out.println("0 - restaurante");
            System.out.println("1 - barzinho");
            System.out.println("2 - balada");
            System.out.println("3 - cafe");
            System.out.println("4- ar livre");
            System.out.print("Digite o tipo do local de acordo com os codigos: ");
            scanner.nextLine();
            tipo=scanner.nextLine();
            tipoI=Integer.parseInt(tipo);
            if(tipoI>4||tipoI<0)System.out.println("Opcao invalida");
        }while(tipoI>4||tipoI<0);
        total=total+tipo+",";

        int precoI;
        System.out.println("Digite o nome do local:");
        nome=scanner.nextLine();
        total=total+nome+",";

        do{
            System.out.print("Qual o preço[1-3] sendo 1 barato e 3 caro?");
            preco = scanner.nextLine();
            precoI=Integer.parseInt(preco);
            if(precoI>3||precoI<0)System.out.println("Opcao invalida");
        }while(precoI>3||precoI<0);
        total=total+preco+",";
        String ineditoU;

        do{
            System.out.print("O local é inédito?[SIM/NAO]");
            inedito=scanner.nextLine();
            ineditoU=inedito.toUpperCase();
            if(!ineditoU.equals("SIM")&&!ineditoU.equals("NAO"))System.out.println("Opcao invalida");
        }while(!ineditoU.equals("SIM")&&!ineditoU.equals("NAO"));
        total=total+ineditoU+",";

        String turnoU;
        do{
            System.out.println("O local é para dia, noite ou ambos?");
            turno=scanner.nextLine();
            turnoU=turno.toUpperCase();
            if(!turnoU.equals("AMBOS")&&!turnoU.equals("DIA")&&!turnoU.equals("NOITE"))System.out.println("Opcao invalida");
        }while(!turnoU.equals("AMBOS")&&!turnoU.equals("DIA")&&!turnoU.equals("NOITE"));
        total=total+turnoU +",";
        return total;
    }
    

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
            }
            String novoArquivo=arquivo.replace(linhaUt, novaLinha);
    
            try (FileWriter fileWriter = new FileWriter("locais.txt")) {
                fileWriter.write(novoArquivo);
                fileWriter.write("\n");
                System.out.println("Dados foram atualizados com sucesso no arquivo.");
            } catch (IOException e) {   
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
                System.out.println("Local excluido com sucesso.");
            } catch (IOException e) {   
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escreveArquivo(String str){
        String nomeArq = "locais.txt";
        
        try (FileWriter fileWriter = new FileWriter(nomeArq,true)) {
            fileWriter.write(str);
            fileWriter.write("\n");
            System.out.println("Dados foram escritos com sucesso no arquivo.");
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
                        System.out.println("Até a próxima! \2 ");
                            break;
                    case 1:
                        escreveArquivo(le());
                            break;

                    case 2:
                        System.out.print("Digite o nome do local que voce quer marcar como visitado: ");
                        scanner.nextLine();
                        nome=scanner.nextLine();
                        marcaVisita(nome);
                            break;
                    case 3:
                        System.out.print("Digite o nome do local que voce quer marcar como visitado: ");
                        scanner.nextLine();
                        nome=scanner.nextLine();
                        exclui(nome);

                             break;
                        
                }
            }
            
        } while (opcao != 0); 
    }
}
