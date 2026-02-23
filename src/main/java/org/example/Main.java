package org.example;

import Dao.*;
import Entidades.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final Scanner SC = new Scanner(System.in);
    static final ClienteDao CLIENTE_DAO = new ClienteDao();
    static final EntregaDao ENTREGA_DAO = new EntregaDao();
    static final HistoricoEntregaDao HISTORICO_ENTREGA_DAO = new HistoricoEntregaDao();
    static final MotoristaDao MOTORISTA_DAO = new MotoristaDao();
    static final PedidoDao PEDIDO_DAO = new PedidoDao();

    public static void main(String[] args) {
        inicio();
    }
    public static void inicio() {
        while (true) {
            System.out.println("""
                    1 - Cadastrar Cliente
                    2 - Cadastrar Motorista
                    3 - Criar Pedido
                    4 - Atribuir Pedido a Motorista (Gerar Entrega)
                    5 - Registrar Evento de Entrega (Histórico)
                    6 - Atualizar Status da Entrega
                    7 - Listar Todas as Entregas com Cliente e Motorista
                    8 - Relatório: Total de Entregas por Motorista
                    9 - Relatório: Clientes com Maior Volume Entregue
                    10 - Relatório: Pedidos Pendentes por Estado
                    11 - Relatório: Entregas Atrasadas por Cidade
                    12 - Buscar Pedido por CPF/CNPJ do Cliente
                    13 - Cancelar Pedido
                    14 - Excluir Entrega (com validação)
                    15 - Excluir Cliente (com verificação de dependência)
                    16 - Excluir Motorista (com verificação de dependência)
                    0 - Sair
                    Escolha Opção: 
                    """);
            int opcao = SC.nextInt();
            switch (opcao){
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarMotorista();
                    break;
                case 3:
                    criarPedido();
                    break;
                case 4:
                    atribuirPedidoAMotorista();
                    break;
                case 5:
                    registrarEventoEntrega();
                    break;
                case 6:
                    atualizarStatusEntrega();
                    break;
                case 7:
                    listarTodasEntregasClientesMotoristas();
                    break;
                case 8:
                    relatorioTotalEntregasPorMotorista();
                    break;
                case 9:
                    relatorioClientesMaiorVolumeEntregue();
                    break;
                case 10:
                    relatoriosPedidosPendentesPorEstado();
                    break;
                case 11:
                    relatorioEntregasAtrasadasPorCidade();
                    break;
                case 12:
                    buscarPedidoPorCPFCNPJCliente();
                    break;
                case 13:
                    cancelarPedido();
                    break;
                case 14:
                    excluirEntrega();
                    break;
                case 15:
                    excluirCliente();
                    break;
                case 16:
                    excluirMotorista();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
 //1
    private static void cadastrarCliente() {
        SC.nextLine();
        System.out.println("Insira o nome do Cliente: ");
        String nome = SC.nextLine();
        System.out.println("Insira seu CPF/CNPJ: ");
        String cpfCnpj = SC.nextLine();
        System.out.println("Insira seu Endereço: ");
        String endereco = SC.nextLine();
        System.out.println("Insira sua Cidade: ");
        String cidade = SC.nextLine();
        System.out.println("Insira seu Estado: ");
        String estado = SC.nextLine();
        CLIENTE_DAO.inserirCliente(new Cliente(nome,cpfCnpj,endereco,cidade,estado));
    }
//2
    private static void cadastrarMotorista() {
        SC.nextLine();
        System.out.println("Insira o Nome do seu Motorista: ");
        String nome = SC.nextLine();
        System.out.println("Insira a CNH do seu Motorista: ");
        String cnh = SC.nextLine();
        System.out.println("Insira o Veiculo de seu Motorista: ");
        String veiculo = SC.nextLine();
        System.out.println("Insira a Cidade Base de seu Motorista: ");
        String cidadeBase = SC.nextLine();
        MOTORISTA_DAO.inserirMotorista(new Motorista(nome,cnh,veiculo,cidadeBase));
    }
//3
    private static void criarPedido() {
        ArrayList<Cliente> listaCliente = CLIENTE_DAO.mostrarTodosClientes();
        System.out.println("Lista Clientes: ");
        for(Cliente cliente : listaCliente){
            System.out.println(cliente);
        }
        System.out.println("Insira o ID do Cliente: ");
        int idCliente = SC.nextInt();
        System.out.println("A data do pedido é a atual!");
        LocalDate dataAtual = LocalDate.now();
        System.out.println(dataAtual);
        System.out.println("Insira o volume da carga: ");
        double volume = SC.nextDouble();
        System.out.println("Insira o peso da carga: ");
        double peso = SC.nextDouble();
        System.out.println("Qual o status do pedido: *Digite tudo maiusculo, vai virar enum alguma hora mas nao hj");
        SC.nextLine();
        String status = SC.nextLine();
        PEDIDO_DAO.inserirPedido(new Pedido(idCliente, dataAtual, volume, peso, status));
    }
    //4
    private static void atribuirPedidoAMotorista() {
        ArrayList<Motorista> listaMotorista = MOTORISTA_DAO.mostrarTodosMotoristas();
        System.out.println("Lista Motorista: ");
        for(Motorista motorista : listaMotorista){
            System.out.println(motorista);
        }
        System.out.println("Insira o ID do Motorista: ");
        int idMotorista = SC.nextInt();
        ArrayList<Pedido> listaPedidos = PEDIDO_DAO.mostrarTodosPedidos();
        System.out.println("Lista Pedidos: ");
        for(Pedido pedido : listaPedidos){
            System.out.println(pedido);
        }
        System.out.println("Insira o ID do Pedido: ");
        int idPedido = SC.nextInt();
        System.out.println("Insira a data de Saida (dd/MM/yyyy): ");
        SC.nextLine();
        String dataSaidaTexto = SC.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataSaida = LocalDate.parse(dataSaidaTexto, formatter);
        System.out.println("Data convertida : " + dataSaida);
        System.out.println("Insira a data de Saida (dd/MM/yyyy): ");
        String dataEntradaTexto = SC.nextLine();
        LocalDate dataEntrada = LocalDate.parse(dataEntradaTexto, formatter);
        System.out.println("Data convertida : " + dataEntrada);
        System.out.println("Insira o Status da entrega - Vai virar enum alguma hora tbm");
        String status = SC.nextLine();
        ENTREGA_DAO.inserirEntrega(new Entrega(idPedido, idMotorista, dataSaida, dataEntrada, status));
    }
    //5
    private static void registrarEventoEntrega() {
        System.out.println("lista de Entregas");
        ArrayList<Entrega> listaEntrega = ENTREGA_DAO.mostrarTodasEntregas();
        for(Entrega entrega : listaEntrega){
            System.out.println(entrega);
        }
        System.out.println("Insira o ID da entrega");
        int idEntrega = SC.nextInt();
        System.out.println("Insira a data da Entrega (dd/MM/yyyy): ");
        SC.nextLine();
        String dataEventoTexto = SC.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataEvento = LocalDate.parse(dataEventoTexto, formatter);
        System.out.println("Insira a descrição do evento: ");
        String descricao = SC.nextLine();
        HISTORICO_ENTREGA_DAO.inserirHistoricoEntrega(new HistoricoEntrega(idEntrega, dataEvento, descricao));
    }
//6
    private static void atualizarStatusEntrega() {
        System.out.println("Lista de Entregas: ");
        ArrayList<Entrega> listaEntregas = ENTREGA_DAO.mostrarTodasEntregas();
        for(Entrega entrega : listaEntregas){
            System.out.println(entrega);
        }
        System.out.println("Insira o id da entrega: ");
        int id = SC.nextInt();
        System.out.println("Insira o novo Status: ");
        SC.nextLine();
        String status = SC.nextLine();
         ENTREGA_DAO.atualizarStatusEntrega(status, id);
    }
//7
        private static void listarTodasEntregasClientesMotoristas() {
            System.out.println("\n=== RELATÓRIO GERAL DE ENTREGAS ===");
            ArrayList<EntregaClienteMotorista> lista = ENTREGA_DAO.listarTodasEntregasClienteMotorista();

            if (lista.isEmpty()) {
                System.out.println("Nenhuma entrega registrada com motorista e cliente.");
            } else {
                for (EntregaClienteMotorista item : lista) {
                    System.out.println(item);
                }
            }
        }
//8
    private static void relatorioTotalEntregasPorMotorista() {
        ArrayList<MotoristaTotalEntregas> listaMotora = MOTORISTA_DAO.contagemMotorista();
        for(MotoristaTotalEntregas motoristaTotalEntregas : listaMotora){
            System.out.println(motoristaTotalEntregas);
        }
    }
//9
    private static void relatorioClientesMaiorVolumeEntregue() {
        ArrayList<ClienteMaiorVolume> listaCliente = CLIENTE_DAO.listarTodosClientesMaiorEntrega();
        for(ClienteMaiorVolume cliente : listaCliente){
            System.out.println(cliente);
        }
    }
//10
    private static void relatoriosPedidosPendentesPorEstado() {
        ArrayList<QuantidadeEstado> listaQuantidade = PEDIDO_DAO.mostrarEstadoPedidosPendente();
        for (QuantidadeEstado qtd : listaQuantidade){
            System.out.println(qtd);
        }
    }
//11
    private static void relatorioEntregasAtrasadasPorCidade() {
        ArrayList<CidadeAtrasada> listaCidadeAtrasa = ENTREGA_DAO.listarCidadesAtrasadas();
        for(CidadeAtrasada cidade : listaCidadeAtrasa){
            System.out.println(listaCidadeAtrasa);
        }
    }
//12
    private static void buscarPedidoPorCPFCNPJCliente() {
        System.out.println("Insira o CPF/CNPJ do cliente: ");
        SC.nextLine();
        String cnpjCPF = SC.nextLine();
        ArrayList<Pedido> listaPedidos = PEDIDO_DAO.buscarPorCpfCnpj(cnpjCPF);
        for(Pedido pedido : listaPedidos){
            System.out.println(pedido);
        }
    }
//13
    private static void cancelarPedido() {
        ArrayList<Pedido> listaPedido = PEDIDO_DAO.mostrarTodosPedidos();
        for(Pedido pedido : listaPedido){
            System.out.println(pedido);
        }
        System.out.println("Insira o ID do Pedido que você deseja cancelar: ");
        int id = SC.nextInt();
        PEDIDO_DAO.cancelarPedido(id);
    }
//14
    private static void excluirEntrega() {
        ArrayList<Entrega> listaEntrega = ENTREGA_DAO.mostrarTodasEntregas();
        for(Entrega entrega : listaEntrega){
            System.out.println(entrega);
        }
        System.out.println("Insira o ID da entrega que você deseja excluir: ");
        int id = SC.nextInt();
        System.out.println("Digite - 'EXCLUSAO' para excluir a entrega: ");
        SC.nextLine();
        String exclud = SC.nextLine();
        if(!exclud.equals("EXCLUSAO")){
            return;
        }
        ENTREGA_DAO.deletarEntrega(id);
    }
//15
    private static void excluirCliente() {

    }
//16
    private static void excluirMotorista() {
    }
}