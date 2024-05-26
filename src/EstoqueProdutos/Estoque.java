package EstoqueProdutos;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
class Produto {
    public int codigoProduto;
    public String nomeProduto;
    public String marcaProduto;

    public double precoProduto;
    public int qtdDisponivel;

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public void setMarcaProduto(java.lang.String marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public void setNomeProduto(java.lang.String nomeProduto) {this.nomeProduto = nomeProduto;}

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
public class Estoque {
    static Scanner input = new Scanner(System.in);
    static int codigoAutoIncrement = 0;
    public static void main(String[] args) {
        Produto[] produtos = new Produto[10];
        init(produtos);
        int choice = 0;
        OrdemAlfabetica(produtos);

        do {
            ExibirMenu();
            choice = input.nextInt();

            while (choice < 0 || choice > 6) {
                System.out.print(" ERRO: Insira uma opção válida: ");
                choice = input.nextInt();
            }

            input.nextLine();
            switch (choice) {
                case 1:
                    if (VerificarEspaco(produtos)) {
                        Produto NovoProduto = new Produto();
                        System.out.println(" ---------------------------------------");
                        System.out.print(" Insira o nome do produto: ");
                        String nome  = input.nextLine();

                        while (VerificarNome(nome, produtos)) {
                            System.out.println(" ");
                            System.out.println(" ERRO: Nome já existe na base de dados.");
                            System.out.print(" Insira outro nome para o produto: ");
                            nome = input.nextLine();

                        }

                        NovoProduto.setNomeProduto(nome);

                        System.out.print(" Insira a marca do produto: ");
                        NovoProduto.setMarcaProduto(input.nextLine());
                        System.out.print(" Insira o preço do produto: ");
                        double precoNovoProduto = input.nextDouble();
                        System.out.print(" Insira a quantidade disponível no estoque: ");
                        int qtdNovoProduto = input.nextInt();

                        NovoProduto.setQtdDisponivel(qtdNovoProduto);
                        NovoProduto.setPrecoProduto(precoNovoProduto);

                        InserirProduto(NovoProduto, produtos);
                        MostarProdutos(produtos);
                    } else {
                        System.out.println(" Ops. O vetor está cheio.");
                    }
                    break;

                case 2:
                    System.out.println(" ");
                    MostarProdutos(produtos);
                    System.out.println(" Insira o código do produto que deseja excluir: ");
                    int codProdutoExcluir = input.nextInt();
                    ExcluirProduto(produtos, codProdutoExcluir);
                    break;

                case 3:
                    System.out.println(" ----------------------------------------------");

                    MostarProdutos(produtos);

                    System.out.println(" ");
                    System.out.print(" Insira o código do produto que deseja alterar: ");
                    int codigoProduto = input.nextInt();

                    System.out.println(" Escolha um atributo do produto para alterar: ");
                    ExibirSubMenu();
                    int atributoEscolhido = input.nextInt();

                    java.lang.String novoNome = " ";
                    double novoPreco = 0.0;
                    java.lang.String novaMarca = " ";
                    int novaQtd = 0;

                    input.nextLine();

                        if (atributoEscolhido == 1) {

                            System.out.print(" Insira o novo nome do produto: ");
                            novoNome = input.nextLine();

                            while(VerificarNome(novoNome,produtos)){
                                System.out.print(" Nome já existe na base de dados. Escolha outro: ");
                                novoNome = input.nextLine();
                            }

                        } else if (atributoEscolhido == 2) {
                            System.out.print(" Insira o novo preço do produto: ");
                            novoPreco = input.nextDouble();
                            while(novoPreco<0){
                                System.out.print(" Insira um novo preço válido: ");
                                novoPreco = input.nextDouble();
                            }

                        } else if (atributoEscolhido == 3) {
                            System.out.print(" Insira a nova marca do produto: ");
                            novaMarca = input.next();

                        } else if (atributoEscolhido == 4) {
                            System.out.print(" Insira a nova quantidade do produto: ");
                            novaQtd = input.nextInt();
                            while(novaQtd<0){
                                System.out.print(" Insira uma quantidade válida: ");
                                novaQtd = input.nextInt();
                            }
                        }else{
                            System.out.println(" Insira um atributo válido.");
                        }


                    for (Produto produto : produtos) {
                        if (produto!= null && produto.codigoProduto == codigoProduto) {

                            switch (atributoEscolhido) {
                                case 1:
                                    produto.setNomeProduto(novoNome);
                                break;
                                case 2:
                                    produto.setPrecoProduto(novoPreco);
                                break;
                                case 3:
                                    produto.setMarcaProduto(novaMarca);
                                break;
                                case 4:
                                    produto.setQtdDisponivel(novaQtd);
                                break;
                            }

                            System.out.println(" Atributo alterado com sucesso.");
                             MostarProdutos(produtos);
                        }
                    }
                    break;
                case 4:
                    System.out.print(" Insira o código do produto: ");
                    int codigoProdutoADetalhar = input.nextInt();
                    DetalharProduto(codigoProdutoADetalhar, produtos);
                    break;
                case 5:
                    MostarProdutos(produtos);
                break;

                case 6:
                    MostarProdutos(produtos);
                break;
           }
        } while (choice != 0);
        System.out.println(" *** Programa Finalizado Com Sucesso.");
    }
    private static boolean VerificarEspaco(Produto[] produtos) {
        boolean espaco = false;
        int tam = produtos.length - 1;

        if (produtos[tam] == null) {
            espaco = true;
        }
        return espaco;

    }
    private static void DetalharProduto(int codigoProdutoADetalhar, Produto[] produtos) {

        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i] != null && produtos[i].codigoProduto == codigoProdutoADetalhar) {
                System.out.println(" - - - - - - - - - - - - - - - - - - - - - ");
                System.out.println(" CÓDIGO: " + produtos[i].codigoProduto);
                System.out.println(" NOME: " + produtos[i].nomeProduto);
                System.out.println(" MARCA: " + produtos[i].marcaProduto);
                System.out.println(" PREÇO: " + produtos[i].precoProduto);
                System.out.println(" QTD DISPONÍVEL: " + produtos[i].qtdDisponivel);
                System.out.println(" - - - - - - - - - - - - - - - - - - - - - ");
            }
        }
    }
    public static void init(Produto produtos[]) {
        Produto Monitor = new Produto();
        Monitor.setNomeProduto("Monitor Acer");
        Monitor.setMarcaProduto("Acer");
        Monitor.setPrecoProduto(459.32);
        Monitor.setQtdDisponivel(100);
        InserirProduto(Monitor, produtos);

        Produto Geladeira = new Produto();
        Geladeira.setNomeProduto("Geladeira");
        Geladeira.setPrecoProduto(15.012);
        Geladeira.setMarcaProduto("Electrolux");
        Geladeira.setQtdDisponivel(4);
        InserirProduto(Geladeira, produtos);

        Produto Mesa = new Produto();
        Mesa.setNomeProduto("Mesa Industrial");
        Mesa.setMarcaProduto("Madeira Madeira");
        Mesa.setPrecoProduto(324.4);
        InserirProduto(Mesa, produtos);

        Produto Caderno = new Produto();
        Caderno.setNomeProduto("Caderno 96 Folhas");
        Caderno.setMarcaProduto("Tilibra");
        Caderno.setPrecoProduto(19.90);
        Caderno.setQtdDisponivel(175);
        InserirProduto(Caderno, produtos);

        Produto GarrafaCafé = new Produto();
        GarrafaCafé.setNomeProduto("Garrafa de Café");
        GarrafaCafé.setMarcaProduto("Tuppaware");
        GarrafaCafé.setPrecoProduto(53.56);
        GarrafaCafé.setQtdDisponivel(92);
        InserirProduto(GarrafaCafé, produtos);
    }
    public static void ExibirMenu() {
        System.out.println(" - - -");
        System.out.println(" [1] Inserir produto");
        System.out.println(" [2] Excluir produto");
        System.out.println(" [3] Alterar dados de um produto");
        System.out.println(" [4] Detalhar um produto");
        System.out.println(" [5] Mostrar produtos pela ordem de cadastro");
        System.out.println(" [6] Mostrar produtos pela ordem alfabética");
        System.out.println(" [0] Finalizar programa.");
    }

    public static void InserirProduto(Produto NovoProduto, Produto[] produtos) {
        Random rand = new Random();
        int preenchido = 0;
        int i = 0;

        int codigo = rand.nextInt(100);

        while(EncontrarCodigo(codigo,produtos)){
            codigo = rand.nextInt(100);
        }

        while (i < produtos.length && preenchido == 0) {
            if (produtos[i] == null) {
                produtos[i] = NovoProduto;
                produtos[i].setCodigoProduto(codigo);
                preenchido = 1;
            } else {
                i++;
            }
        }
    }
    static public boolean EncontrarCodigo(int codigo,Produto [] produtos){
        boolean found = false;
        for (Produto produto : produtos) {
            if (produto != null && produto.codigoProduto == codigo) {
                found = true;
            }
        }

        return found;
    }
    static public void ExcluirProduto(Produto[] produtos, int codProdutoExcluir){
        for (int i = 0; i < produtos.length; i++) {
            if(produtos[i] != null && produtos[i].codigoProduto == codProdutoExcluir){
                produtos[i] = null;
                ReordenarVetor(produtos);
            }
        }
    }
    public static void MostarProdutos(Produto[] produtos){
        System.out.println(" ");
        System.out.println(" CÓDIGO   |       NOME    |    PREÇO    | QTD DISPONÍVEL");
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i] != null){
                System.out.println(" " + produtos[i].codigoProduto + "           " +  produtos[i].nomeProduto + "           " + produtos[i].precoProduto + "           "  + produtos[i].qtdDisponivel);
            }
        }
    }

    public static void ExibirSubMenu (){
        System.out.println(" [1] Alterar nome do produto");
        System.out.println(" [2] Alterar preço do produto");
        System.out.println(" [3] Alterar marca do produto ");
        System.out.println(" [4] Alterar quantidade disponível do produto ");
    }
    public static boolean VerificarNome(String nome, Produto[] produtos){
        boolean encontrado = false;

        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i] != null && Objects.equals(produtos[i].nomeProduto, nome)){
                encontrado = true;
            }
        }
        return encontrado;
    }
    static public void ReordenarVetor(Produto[] produtos){

        for (int i = 0; i < produtos.length-1; i++) {
            if(produtos[i] == null){
                produtos[i] = produtos[i+1];
                produtos[i+1] = null;
            }
        }
        MostarProdutos(produtos);
    }

    static public void OrdemAlfabetica(Produto[]produtos){
        for(int i = 0; i< 4; i++){
            for (int j = 0; j< 4; j++) {
                if (produtos[i] != null && produtos[i].nomeProduto.length() > produtos[i + 1].nomeProduto.length()) {
                    Produto aux = produtos[i];
                    produtos[i] = produtos[i + 1];
                    produtos[i + 1] = aux;
                }
            }
        }
        MostarProdutos(produtos);

    }
}