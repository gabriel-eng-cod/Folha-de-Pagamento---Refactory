import java.util.Scanner;
//import java.util.LinkedList;1


public class Main {

    public static void main(String[] args){

        Funcoes empregado = new Funcoes();

        int last_action = 0;

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("==============================");
        System.out.println("Bem vindo à Folha de Pagamento");
        System.out.println("==============================");

        while(true)
        {
            System.out.println(" ____________________________________");
            System.out.println("|                                    |");
            System.out.println("|Escolha uma das opções abaixo:      |");
            System.out.println("|                                    |");
            System.out.println("|1) Adicionar um novo empregado.     |");
            System.out.println("|2) Remover um empregado.            |");
            System.out.println("|3) Lançar um cartão de ponto.       |");
            System.out.println("|4) Lançar um resultado venda.       |");
            System.out.println("|5) Lançar uma taxa de serviço.      |");
            System.out.println("|6) Alterar detalhes de um empregado.|");
            System.out.println("|7) Rodar folha de pagamento.        |");
            System.out.println("|8) Undo.                            |");
            System.out.println("|9) Redo.                            |");
            System.out.println("|10) Criar nova agenda de pagamento. |");
            System.out.println("|11) Sair do sistema.                |");
            System.out.println("|12) Listar funcionários.            |");
            System.out.println("|____________________________________|\n");

            System.out.printf("-> ");
            int chave = input.nextInt();
            input.nextLine();

            System.out.println();

            if(chave != 8 && chave != 9 && chave != 12)
            {
                last_action = chave;
            }

            if(chave < 1 || chave > 12)
            {
                System.out.println("Insira uma opção válida");
            }
            else
            {
                if(chave == 1)
                {
                    empregado.add_empregado();
                    System.out.println("O funcionário foi adicionado com sucesso!\n");
                }
                else if(chave == 2)
                {
                    empregado.remove_empregado();
                    System.out.println("Funcionário removido com sucesso!\n");
                }
                else if(chave == 3)
                {
                    empregado.add_cartao_de_ponto();
                    System.out.println("Cartão de ponto adicionado com sucesso!\n");
                }
                else if(chave == 4)
                {
                    empregado.add_venda();
                    System.out.println("Venda adicionada com sucesso!\n");
                }
                else if(chave == 5)
                {
                    empregado.add_taxa_de_servico();
                    System.out.println("Taxa de serviço adicionada com sucesso!\n");
                }
                else if(chave == 6)
                {
                    empregado.alterar_info();
                }
                else if(chave == 7)
                {
                    empregado.rodar_folha();
                }
                else if(chave == 8)
                {
                    
                    if(last_action == 1)
                    {
                        if(empregado.auxiliares.adicionado == 1)
                        {
                            empregado.horista_list.removeLast();
                        }
                        else
                        {
                            empregado.assalariado_list.removeLast();
                        }
                    }
                    else if(last_action == 2)
                    {
                        if(empregado.auxiliares.removido == 1)
                        {
                            empregado.horista_list.add(empregado.auxiliares.horis);
                        }
                        else
                        {
                            empregado.assalariado_list.add(empregado.auxiliares.assal);
                        }
                    }
                    else if(last_action == 3)
                    {
                        for (Horista horista : empregado.horista_list)
                        {
                            if(horista.id == empregado.auxiliares.id_card)
                            {
                                horista.cartao_list.removeLast();
                                break;
                            }    
                        }
                    }
                    else if(last_action == 4)
                    {
                        for (Assalariado assalariado : empregado.assalariado_list)
                        {
                            if(assalariado.id == empregado.auxiliares.id_venda)
                            {
                                assalariado.venda_list.removeLast();
                                break;
                            }    
                        }
                    }
                    else if(last_action == 5)
                    {
                        boolean verificado = false;
                        for (Horista horista : empregado.horista_list)
                        {
                            if(horista.id == empregado.auxiliares.id_taxa)
                            {
                                verificado = true;
                                horista.taxa_list.removeLast();
                                break;
                            }    
                        }

                        if(!verificado)
                        {
                            for (Assalariado assalariado : empregado.assalariado_list)
                            {
                                if(assalariado.id == empregado.auxiliares.id_taxa)
                                {
                                    verificado = true;
                                    assalariado.taxa_list.removeLast();
                                    break;
                                }    
                            }
                        }
                    }
                    else if(last_action == 6)
                    {
                        int x = empregado.auxiliares.escolha_alteracao;
                        int id = empregado.auxiliares.id_alteracao;
                        int h_ou_a = empregado.auxiliares.horis_ou_assal;
                        Empregado funcionario = null;

                        for (Horista horista : empregado.horista_list)
                        {
                            if(horista.id == id)
                            {
                                funcionario = horista;
                                break;
                            }
                        }

                        for (Assalariado assalariado : empregado.assalariado_list)
                        {
                            if(assalariado.id == id)
                            {
                                funcionario = assalariado;
                                break;
                            }
                        }
                                    
                        if(x == 1)
                        {
                            funcionario.name = empregado.auxiliares.nome;
                            break;
                        }
                        else if(x == 2)
                        {
                            funcionario.endereco = empregado.auxiliares.endereco;
                            break;
                        }
                        else if(x == 4)
                        {
                            funcionario.escolha_pag = empregado.auxiliares.escolha_pag;
                            break;
                        }
                        else if(x == 5)
                        {
                            funcionario.is_sindicate = empregado.auxiliares.is_sindicate;
                            break;
                        }
                        else if(x == 6)
                        {
                            funcionario.id_sindicato = empregado.auxiliares.id_sindicato;
                            break;
                        }
                        else if(x == 7)
                        {
                            funcionario.taxa_mensal_sindicato = empregado.auxiliares.taxa_sindical;
                            break;
                        }
                        else
                        {
                            if(h_ou_a == 1)
                            {
                                empregado.horista_list.add(empregado.auxiliares.horis);
                                empregado.assalariado_list.removeLast();
                            }
                            else
                            {
                                empregado.assalariado_list.add(empregado.auxiliares.assal);
                                empregado.horista_list.removeLast();
                            }
                        }  
                    }

                    System.out.println("\nComando desfeito com sucesso!\n");    
                }
            
                else if(chave == 9)
                {
                    if(last_action == 1)
                    {
                        if(empregado.auxiliares.adicionado == 1)
                        {
                            empregado.horista_list.add(empregado.auxiliares.horis);
                        }
                        else
                        {
                            empregado.assalariado_list.add(empregado.auxiliares.assal);
                        }
                    }
                    else if(last_action == 2)
                    {
                        if(empregado.auxiliares.removido == 1)
                        {
                            empregado.horista_list.removeLast();
                        }
                        else
                        {
                            empregado.assalariado_list.removeLast();
                        }
                    }
                    else if(last_action == 3)
                    {
                        for (Horista horista : empregado.horista_list)
                        {
                            if(horista.id == empregado.auxiliares.id_card)
                            {
                                horista.cartao_list.add(empregado.auxiliares.card);
                                break;
                            }    
                        }
                    }
                    else if(last_action == 4)
                    {
                        for (Assalariado assalariado : empregado.assalariado_list)
                        {
                            if(assalariado.id == empregado.auxiliares.id_venda)
                            {
                                assalariado.venda_list.add(empregado.auxiliares.sale);
                                break;
                            }    
                        }
                    }
                    else if(last_action == 5)
                    {
                        boolean verificado = false;
                        for (Horista horista : empregado.horista_list)
                        {
                            if(horista.id == empregado.auxiliares.id_taxa)
                            {
                                verificado = true;
                                horista.taxa_list.add(empregado.auxiliares.taxa_horista);
                                break;
                            }    
                        }

                        if(!verificado)
                        {
                            for (Assalariado assalariado : empregado.assalariado_list)
                            {
                                if(assalariado.id == empregado.auxiliares.id_taxa)
                                {
                                    verificado = true;
                                    assalariado.taxa_list.add(empregado.auxiliares.taxa_assalariado);
                                    break;
                                }    
                            }
                        }
                    }
                    else if(last_action == 6)
                    {
                        int x = empregado.auxiliares.escolha_alteracao;
                        int id = empregado.auxiliares.id_alteracao;
                        int h_ou_a = empregado.auxiliares.horis_ou_assal;
                        Empregado funcionario = null;

                        for (Horista horista : empregado.horista_list)
                        {
                            if(horista.id == id)
                            {
                                funcionario = horista;
                                break;
                            }
                        }
                        for (Assalariado assalariado : empregado.assalariado_list)
                        {
                            if(assalariado.id == id)
                            {
                                funcionario = assalariado;
                                break;
                            }
                        }

                        if(x == 1)
                        {
                            funcionario.name = empregado.auxiliares.nome_alterado;
                            break;
                        }
                        else if(x == 2)
                        {
                            funcionario.endereco = empregado.auxiliares.endereco_alterado;
                            break;
                        }
                        else if(x == 4)
                        {
                            funcionario.escolha_pag = empregado.auxiliares.escolha_pag_alterada;
                            break;
                        }
                        else if(x == 5)
                        {
                            funcionario.is_sindicate = empregado.auxiliares.is_sindicate_alterado;
                            break;
                        }
                        else if(x == 6)
                        {
                            funcionario.id_sindicato = empregado.auxiliares.id_sindicato_alterado;
                            break;
                        }
                        else if(x == 7)
                        {
                            funcionario.taxa_mensal_sindicato = empregado.auxiliares.taxa_sindical_alterada;
                            break;
                        }
                        else
                        {
                            if(h_ou_a == 1)
                            {
                                empregado.horista_list.removeLast();
                                empregado.assalariado_list.add(empregado.auxiliares.assal);
                            }
                            else
                            {                                        
                                empregado.assalariado_list.removeLast();
                                empregado.horista_list.add(empregado.auxiliares.horis);
                            }
                        }     
                    }

                    System.out.println("\nComando refeito com sucesso!\n");
                }
                else if(chave == 10)
                {
                    empregado.criar_nova_agenda();
                }
                else if(chave == 11)
                {
                    System.out.println("Tem certeza que deseja sair do sistema?\n\n1) Sair\n2) Continuar navegando\n");

                    System.out.printf("-> ");
                    int saida = input.nextInt();
                    System.out.println();

                    if(saida == 1)
                    {
                        System.out.println("Obrigado por utilizar nosso sistema!\n\n");
                        break;
                    }
                }
                else
                {
                    empregado.listar_funcionarios();
                }
            }
        
        }

        input.close();

    }
}
