package org.example;

import Dao.*;
import Entidades.Cliente;
import Entidades.Motorista;

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

    private static void criarPedido() {
    }

    private static void atribuirPedidoAMotorista() {
    }

    private static void registrarEventoEntrega() {
    }

    private static void atualizarStatusEntrega() {
    }

    private static void listarTodasEntregasClientesMotoristas() {
    }

    private static void relatorioTotalEntregasPorMotorista() {
    }

    private static void relatorioClientesMaiorVolumeEntregue() {
    }

    private static void relatoriosPedidosPendentesPorEstado() {
    }

    private static void relatorioEntregasAtrasadasPorCidade() {
    }

    private static void buscarPedidoPorCPFCNPJCliente() {
    }

    private static void cancelarPedido() {
    }

    private static void excluirEntrega() {
    }

    private static void excluirCliente() {
    }

    private static void excluirMotorista() {
    }
}