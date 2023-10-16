import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class App {

    static Scanner scanner=new Scanner(System.in);
    static String arrayLocais[]=new String[100];
    static int qtLocais=0;
    static String []subArrayLocais=new String [100];
    static int qtSubLocais=0;
    static String nomeUsuario;
    //Método para exibir o menu inicial
    public static int menu(){
        int op; 
        do {
            System.out.println("Boas vindas! Quem ira decidir a saida de hoje? ");
            nomeUsuario=scanner.nextLine();
            try {
                Process processo = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
                processo.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String abertura="Ola "+nomeUsuario+" :)";
            System.out.println(abertura);
            System.out.print("\n---------MENU 1--------");
            System.out.println("\n0 - Sair\n1 - Cadastrar local\n2 - Marcar local como visitado\n3 - Escolher um local para ir\n4 - Deletar um local");
            System.out.println("-------------------------");
            System.out.println("");
            System.out.print("Digite sua opcao: ");
            op=scanner.nextInt();
            try {
                Process processo = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
                processo.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            System.out.println("");
            System.out.println("---------MENU 2--------");
            System.out.println("0 - Restaurante");
            System.out.println("1 - Barzinho");
            System.out.println("2 - Balada");
            System.out.println("3 - Cafe");
            System.out.println("4 - Ar livre");
            System.out.println("-------------------------");
            System.out.println("");
            System.out.print("Digite o tipo do local de acordo com os codigos: ");
            scanner.nextLine();
            tipo=scanner.nextLine();
            System.out.println("");
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
            System.out.println("");
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

    public static String leStringPreferencia(){
        String tipo;
        String preco;
        String inedito;
        String turno;
        String total="\n";
        
        //Leitura do tipo
        int tipoI;
        do{
            System.out.println("");
            System.out.println("---------MENU 2--------");
            System.out.println("0 - Restaurante");
            System.out.println("1 - Barzinho");
            System.out.println("2 - Balada");
            System.out.println("3 - Cafe");
            System.out.println("4 - Ar livre");
            System.out.println("-------------------------");
            System.out.println("");
            System.out.print("Digite o tipo do local que voce quer visitar de acordo com os codigos: ");
            scanner.nextLine();
            System.out.println("");
            tipo=scanner.nextLine();
            tipoI=Integer.parseInt(tipo);
            if(tipoI>4||tipoI<0)System.out.println("\nOpcao invalida!");
        }while(tipoI>4||tipoI<0);
        total=total+tipo+",";
        total=total+"."+",";

        //Leitura do preço
        int precoI;
        do{
            System.out.println("");
            System.out.print("Qual seu orcamento [1-3] sendo 1 barato e 3 caro? ");
            System.out.println("");
            preco = scanner.nextLine();
            precoI=Integer.parseInt(preco);
            if(precoI>3||precoI<0)System.out.println("\nOpcao invalida! ");
        }while(precoI>3||precoI<0);
        total=total+preco+",";
        
        //Leitura se o local é inedito ou nao
        String ineditoU;
        do{
            System.out.println("");
            System.out.print("O local precisa ser inédito [SIM/NAO]? ");
            System.out.println("");
            inedito=scanner.nextLine();
            ineditoU=inedito.toUpperCase();
            if(!ineditoU.equals("SIM")&&!ineditoU.equals("NAO"))System.out.println("Opcao invalida! ");
        }while(!ineditoU.equals("SIM")&&!ineditoU.equals("NAO"));
        total=total+ineditoU+",";

        //Leitura do turno
        String turnoU;
        do{
            System.out.println("");
            System.out.print("O local é para dia, noite ou ambos? ");
            System.out.println("");
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
                sc2.close();
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
    
    //!- Métodos para escolher um local -!

    //Método para criar um array contendo os locais salvos no arquivo
    public static void criaArray(String preferencia){
        try (BufferedReader leitor = new BufferedReader(new FileReader("locais.txt"))) {
            String linha;
            //cria o array de locais
            while ((linha = leitor.readLine()) != null) {
                arrayLocais[qtLocais]=linha;
                qtLocais++;
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contaPontos(preferencia);
    }

    //Método para contabilizar as correspondencias entre os locais disponiveis e o desejado
    public static void contaPontos(String preferencia){
        int pontuacaoCadaLinha []=new int[100];
        String[] partesPreferencia = preferencia.split(",");
        String tipo=partesPreferencia[0].trim();

        //Guarda em um subarray apenas os locais da mesma categoria
        for(int i=0;i<qtLocais;i++){
            String[] partesArrayLocais = arrayLocais[i].split(",");
            int a=Integer.parseInt(partesArrayLocais[0]);
            int b=Integer.parseInt(tipo);
            if(a==b){
                subArrayLocais[qtSubLocais]=arrayLocais[i];
                qtSubLocais++;
            }
        }
        //Contabiliza os pontos dos atributos restantes    
        for(int i=0;i<qtSubLocais;i++){
            String[] partesArrayLocais = subArrayLocais[i].split(",");
            for(int j=1;j<5;j++){
                if(partesPreferencia[j].equals(partesArrayLocais[j]))pontuacaoCadaLinha[i]++;
            }
        }
        try {
            Process processo = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
            processo.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(qtSubLocais==0){
            System.out.println("Sem opcoes disponíveis !"); 
        }
        else if(qtSubLocais==1){
            String partes[]=subArrayLocais[0].split(",");
            System.out.println("");
            System.out.println("O local selecionado foi: "+partes[1]);
            if(nomeUsuario.equals("Laura")){
                System.out.println("Divirta-se com Lucas! <3");
            }
            else if(nomeUsuario.equals("Lucas")){
                System.out.println("Divirta-se com Laura! <3");
            }
            else{
                System.out.println("Divirta-se! ");
            }
        }  
        //Em caso de mais de um local disponivel na categoria buscada, a funcao é chamada para optar pelo que possuir maior correspondencia
        else selecionaLocal(pontuacaoCadaLinha);
    }

    public static void selecionaLocal(int[] pontuacao){
        int locaisCandidatos=0;
        int atributosIguais=3;
        int posicoes[]=new int[100];
        int cPosicoes=0;
        while(locaisCandidatos==0&&atributosIguais>0){
            for(int i=0;i<qtSubLocais;i++){
                if(pontuacao[i]==atributosIguais){
                    locaisCandidatos++;
                    posicoes[cPosicoes]=i;
                    int c=i+1;
                    while(pontuacao[c]==atributosIguais){
                        locaisCandidatos++;
                        posicoes[cPosicoes]=i;
                        c++;
                    }
                }
            }
            if(locaisCandidatos==0)atributosIguais--;
        }
        try {
            Process processo = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
            processo.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(locaisCandidatos>1){
            Random gerador = new Random();
            gerador.setSeed(System.nanoTime());
            int a,b;
            a=(((Math.abs(gerador.nextInt()) %locaisCandidatos)));
            b=posicoes[a];

            String partes[]=subArrayLocais[b].split(",");
            System.out.println("");
            System.out.println("O local selecionado foi: "+partes[1]);
            if(nomeUsuario.equals("Laura")){
                System.out.println("Divirta-se com Lucas! <3");
            }
            else if(nomeUsuario.equals("Lucas")){
                System.out.println("Divirta-se com Laura! <3");
            }
            else{
                System.out.println("Divirta-se! ");
            }
        }
        else {
            int b=posicoes[0];
            String partes[]=subArrayLocais[b].split(",");
            System.out.println("");
            System.out.println("O local selecionado foi: "+partes[1]);
            if(nomeUsuario.equals("Laura")){
                System.out.println("Divirta-se com Lucas! <3");
            }
            else if(nomeUsuario.equals("Lucas")){
                System.out.println("Divirta-se com Laura! <3");
            }
            else{
                System.out.println("Divirta-se! ");
            }
        }
        
    }
    
    public static void main(String[] args) {
    
    int opcao;
    String nome;
    String op="";
    do{
        try {
            Process processo = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
            processo.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        String preferencia=leStringPreferencia();
                        criaArray(preferencia);
                            break;
                    case 4:
                        System.out.print("Digite o nome do local que voce deseja excluir: ");
                        scanner.nextLine();
                        nome=scanner.nextLine();
                        String nomeM=nome.toUpperCase();
                        exclui(nomeM);
                            break;  
                }
                System.out.println("\n\n\n");
                System.out.println("Deseja recomeçar?[S/N] ");
                op=scanner.nextLine();
                System.out.println("\n\n\n");
            }   
        } while (op.equals("S")); 
    }
}
