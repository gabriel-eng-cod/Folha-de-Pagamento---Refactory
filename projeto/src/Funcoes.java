import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

import java.util.LinkedList;

public class Funcoes implements Cloneable{
    
    LinkedList<Horista> horista_list = new LinkedList<>();

    LinkedList<Assalariado> assalariado_list = new LinkedList<>();

    Agenda_Pagamento agenda = new Agenda_Pagamento();

    Historico auxiliares = new Historico();

    int id = 1;
    int id_sindicato = 2;

    Scanner input = new Scanner(System.in);

    void add_empregado()
    {

        while(true)
        {
            System.out.println("Selecione o tipo de empregado que deseja adicionar");
            System.out.println("==================================================\n");
            System.out.println("1) Horista");
            System.out.println("2) Assalariado\n");

            System.out.printf("-> ");
            int chave = input.nextInt();
            input.nextLine();

            System.out.println();

            auxiliares.adicionado = chave;

            if(chave != 1 && chave != 2)
            {
                System.out.println("Escolha uma opção válida!");
            }
            else
            {
                add(chave);
                break;
            }
        }
    }

    void add(int chave)
    {
        String name;
        String endereco;
        double salario, taxa_mensal_sindicato = 0;
        int sindicate;
        boolean is_sindicate = false;

        System.out.println("O funcionário faz parte do sindicato?\n\n1) Sim\n2) Não\n");

        System.out.printf("-> ");
        sindicate = input.nextInt();
        input.nextLine();
        System.out.println();

        if(sindicate == 1)
        {
            is_sindicate = true;
            System.out.println("Qual o valor da taxa mensal do sindicato?\n");

            System.out.printf("-> ");
            taxa_mensal_sindicato = input.nextDouble();
            input.nextLine();
            System.out.println();
        }

        System.out.printf("Digite o nome: ");

        name = input.nextLine();

        System.out.println();

        System.out.printf("Digite o endereço: ");

        endereco = input.nextLine();

        System.out.println();

        System.out.printf("Digite o salário do funcionário: ");

        salario = input.nextDouble();
        input.nextLine();
        System.out.println();

        String metodo_pag;

        System.out.printf("Qual o forma de pagamento que o funcionário irá receber?\n\n");

        agenda.listar();

        System.out.println("\n-> ");

        int escolha_agenda = input.nextInt();
        input.nextLine();

        int i;

        for(i = 0; i < agenda.formas_pagamento.size(); i++)
        {
            if(escolha_agenda == i+1)
            {
                break;
            }
        }

        metodo_pag = agenda.formas_pagamento.get(i);

        System.out.printf("Qual o tipo de pagamento?\n\n1) Cheque pelos correios\n2) Cheque em mãos\n3) Depósito em conta bancária\n\n-> ");
        
        int escolha_pag = input.nextInt();

        if(chave == 1)
        {
            add_horista(sindicate, is_sindicate, taxa_mensal_sindicato, name, endereco, salario, metodo_pag, escolha_pag);
        }
        else
        {
            add_assalariado(sindicate, is_sindicate, taxa_mensal_sindicato, name, endereco, salario, metodo_pag, escolha_pag);
        }
    }

    void add_horista(int sindicate, boolean is_sindicate, double taxa_mensal_sindicato, String name, String endereco, double salario_hora, String metodo_pag, int escolha_pag)
    {
        System.out.printf("\nO ID do funcionário é %d\n", id);

        Horista horis = new Horista(name, endereco, salario_hora, id, is_sindicate, escolha_pag, metodo_pag);

        if(sindicate == 1)
        {
            horis.sindicate(id_sindicato, taxa_mensal_sindicato);
        }

        id++;
        id_sindicato++;

        horista_list.add(horis);
        auxiliares.horis = horis; //armazenar para undo/redo
    }

    void add_assalariado(int sindicate, boolean is_sindicate, double taxa_mensal_sindicato, String name, String endereco, double salario_assalariado, String metodo_pag, int escolha_pag)
    {
        int comissionado;
        double comissao = -1;

        System.out.println("O funcionário em questão recebe comissão?\n\n1) Sim\n2) Não\n");

        System.out.printf("-> ");
        comissionado = input.nextInt();
        input.nextLine();
        System.out.println();

        if(comissionado == 1)
        {
            System.out.printf("Digite a comissão do funcionário assalariado: ");
            comissao = input.nextDouble();
            input.nextLine();
            System.out.println();
        }

        System.out.printf("\nO ID do funcionário é %d\n", id);

        Assalariado assal = new Assalariado(name, endereco, salario_assalariado, comissionado, comissao, id, is_sindicate, escolha_pag, metodo_pag);

        id++;

        if(sindicate == 1)
        {
            assal.sindicate(id_sindicato, taxa_mensal_sindicato);
        }

        id_sindicato++;

        assalariado_list.add(assal);
        auxiliares.assal = assal; //armazenar para undo/redo
    }

    void listar_funcionarios()
    {
        System.out.println("*** Funcionários que trabalham como horistas: ***\n");

        if(horista_list.size() == 0)
        {
            System.out.println("Vazio\n");
        }
        else
        {
            for (int i = 0; i < horista_list.size(); i++) 
            {
                Horista funcionario = horista_list.get(i);

                System.out.printf("Nome: %s\n", funcionario.name);
                System.out.printf("Endereço: %s\n", funcionario.endereco);
                System.out.printf("Salário: %.2f\n", funcionario.salario);
                System.out.printf("ID do funcionário: %s\n", funcionario.id);
                System.out.printf("Tipo de pagamento: ");
                if(funcionario.escolha_pag == 1)
                {
                    System.out.printf("Cheque pelos correios\n");
                }
                else if(funcionario.escolha_pag == 2)
                {
                    System.out.printf("Cheque em mãos\n");
                }
                else
                {
                    System.out.printf("Depósito em conta bancária\n");
                }
                System.out.println();

                if(funcionario.is_sindicate)
                {
                    System.out.printf("O funcionário faz parte do sindicato\n");
                    System.out.printf("Taxa mensal do sindicato: %.2f\n", funcionario.taxa_mensal_sindicato);
                    System.out.printf("ID do funcionário no sindicato: %d\n\n", funcionario.id_sindicato);
                    System.out.printf("Taxas de serviço do funcionário:\n\n");

                    for(Taxa_de_servico taxa : funcionario.taxa_list)
                    {
                        System.out.printf("> Descrição da taxa: %s\n", taxa.descricao);
                        System.out.printf("> Valor da taxa: %.2f\n", taxa.valor);
                        System.out.println();
                    }
                }
                else
                {
                    System.out.printf("O funcionário não faz parte do sindicato\n\n");
                }

                System.out.printf("Cartões de pontos do funcionário %s:\n\n", funcionario.name);
                
                for(Cartao_de_ponto card : funcionario.cartao_list)
                {
                    System.out.printf("> Data: %s\n", card.data);
                    System.out.printf("> Hora de entrada: %dh\n", card.in);
                    System.out.printf("> Hora de saída: %dh\n", card.out);
                    System.out.printf("> Horas trabalhadas no dia: %d horas\n", card.horas_trabalhadas);
                    System.out.println();
                }

                System.out.println("===========================\n");
            }
        }

        System.out.println("*** Funcionários que trabalham como assalariados: ***\n");

        if(assalariado_list.size() == 0)
        {
            System.out.println("Vazio");
        }
        else
        {
            for (int i = 0; i < assalariado_list.size(); i++) 
            {
                Assalariado funcionario = assalariado_list.get(i);

                System.out.printf("Nome: %s\n", funcionario.name);
                System.out.printf("Endereço: %s\n", funcionario.endereco);
                System.out.printf("Salário: %.2f\n", funcionario.salario);
                if(funcionario.comissionado == 1)
                {
                    System.out.printf("Comissao: %.2f\n", funcionario.comissao);
                }
                else
                {
                    System.out.printf("O funcionário não possui comissão\n");
                }
                System.out.printf("ID do funcionário: %s\n", funcionario.id);
                System.out.printf("Tipo de pagamento: ");
                if(funcionario.escolha_pag == 1)
                {
                    System.out.printf("Cheque pelos correios\n");
                }
                else if(funcionario.escolha_pag == 2)
                {
                    System.out.printf("Cheque em mãos\n");
                }
                else
                {
                    System.out.printf("Depósito em conta bancária\n");
                }

                System.out.println();

                if(funcionario.is_sindicate)
                {
                    System.out.printf("O funcionário faz parte do sindicato\n");
                    System.out.printf("Taxa mensal do sindicato: %.2f\n", funcionario.taxa_mensal_sindicato);
                    System.out.printf("ID do funcionário no sindicato: %d\n\n", funcionario.id_sindicato);
                    System.out.printf("Taxas de serviço do funcionário:\n\n");

                    for(Taxa_de_servico taxa : funcionario.taxa_list)
                    {
                        System.out.printf("> Descrição da taxa: %s\n", taxa.descricao);
                        System.out.printf("> Valor da taxa: %.2f\n", taxa.valor);
                        System.out.println();
                    }
                }
                else
                {
                    System.out.printf("O funcionário não faz parte do sindicato\n\n");
                }

                System.out.printf("Vendas do funcionário %s:\n\n", funcionario.name);

                for(Resultado_venda vendas : funcionario.venda_list)
                {
                    System.out.printf("> Data: %s\n", vendas.date);
                    System.out.printf("> Produto vendido: %s\n", vendas.produto);
                    System.out.printf("> Valor do produto: %s\n", vendas.valor_do_produto);
                    System.out.println();
                }

                System.out.println("===========================\n");
            }
        }

        System.out.println();
    }

    void remove_empregado()
    {
        System.out.println("Que tipo de funcionário será removido?\n\n1) Horista\n2) Assalariado\n");

        System.out.printf("-> ");
        int escolha = input.nextInt();
        input.nextLine();
        System.out.println();

        auxiliares.removido = escolha;

        System.out.println("Digite o id do funcionário que deseja remover\n");

        System.out.printf("-> ");
        int chave = input.nextInt();
        System.out.println();
        //input.nextLine();

        if(escolha == 1)
        {
            for (Horista funcionario : horista_list)
            {
                if(funcionario.id == chave)
                {
                    auxiliares.horis = funcionario;
                    horista_list.remove(funcionario);
                    break;
                }
            }
        }
        else
        {
            for (Assalariado funcionario : assalariado_list) 
            {
                if(funcionario.id == chave)
                {
                    auxiliares.assal = funcionario;
                    assalariado_list.remove(funcionario);
                    break;
                }
            }
        }
    }

    void add_cartao_de_ponto()
    {
        System.out.printf("Digite o id do funcionário ao qual deseja adicionar um cartão: ");

        int id = input.nextInt();
        input.nextLine();

        auxiliares.id_card = id;

        System.out.printf("Digite a data do dia trabalhado (DD/MM/AA): ");

        String date = input.nextLine();

        System.out.printf("Digite a hora de entrada (24h): ");
        
        int in = input.nextInt();

        System.out.printf("Digite a hora de saída (24h): ");
        
        int out = input.nextInt();

        System.out.println();

        Cartao_de_ponto card = new Cartao_de_ponto(in, out, date);

        for (Horista horista : horista_list) 
        {
            if(horista.id == id)
            {
                horista.cartao_list.add(card);
                break;
            }    
        }

        auxiliares.card = card;
    }
    
    void add_venda()
    {
        System.out.printf("Digite o id do funcionário ao qual deseja adicionar uma venda: ");

        int id = input.nextInt();
        input.nextLine();

        auxiliares.id_venda = id;

        System.out.printf("Digite a data da venda (DD/MM/AA): ");

        String date = input.nextLine();

        System.out.printf("Digite o nome do produto vendido: ");
        
        String produto = input.nextLine();

        System.out.printf("Digite o valor do produto vendido: ");
        
        double valor = input.nextDouble();

        System.out.println();

        Resultado_venda venda = new Resultado_venda(produto, valor, date);

        for (Assalariado assalariado : assalariado_list) 
        {
            if(assalariado.id == id)
            {
                assalariado.venda_list.add(venda);
                break;
            }    
        }

        auxiliares.sale = venda;
    }

    void add_taxa_de_servico()
    {
        System.out.printf("Digite o id do funcionário ao qual deseja adicionar uma taxa de serviço: ");

        int id = input.nextInt();
        input.nextLine();

        auxiliares.id_taxa = id;

        System.out.println();

        System.out.printf("Descrição para a taxa de serviço:\n\n");

        System.out.printf("-> ");

        String descricao = input.nextLine();
        //input.nextLine();
        System.out.println();

        System.out.printf("Qual o valor da taxa? ");

        double taxa = input.nextDouble();
        input.nextLine();

        System.out.println();

        Taxa_de_servico taxa_de_servico = new Taxa_de_servico(taxa, descricao);

        for (Horista horista : horista_list) 
        {
            if(horista.id == id)
            {
                horista.taxa_list.add(taxa_de_servico);
                auxiliares.taxa_assalariado = taxa_de_servico;
                return;
            }    
        }

        for (Assalariado assalariado : assalariado_list) 
        {
            if(assalariado.id == id)
            {
                assalariado.taxa_list.add(taxa_de_servico);
                auxiliares.taxa_horista = taxa_de_servico;
                return;
            }    
        }
    }

    void alterar_info()
    {
        System.out.printf("Qual dado você deseja alterar?\n\n");
        System.out.printf("1) Nome\n");
        System.out.printf("2) Endereço\n");
        System.out.printf("3) Tipo de empregado\n");
        System.out.printf("4) Método de pagamento\n");
        System.out.printf("5) Presença no sindicato\n");
        System.out.printf("6) ID no sindicato\n");
        System.out.printf("7) Taxa sindical\n\n");

        System.out.printf("-> ");
        int escolha = input.nextInt();

        auxiliares.escolha_alteracao = escolha;
        System.out.println();

        System.out.printf("Qual o id do empregado que você deseja alterar os dados? ");

        System.out.printf("-> ");
        int id = input.nextInt();
        
        auxiliares.id_alteracao = id;
        int tipo_empregado = 0, i, j;

        System.out.println();

        Horista horista;
        Assalariado assalariado;

        for (i = 0; i < horista_list.size(); i++) 
        {
            horista = horista_list.get(i);

            if(horista.id == id)
            {
                tipo_empregado = 1;
                auxiliares.horis = horista;
                auxiliares.horis_ou_assal = 1;
                System.out.printf("O ID informado pertence ao funcionário %s.\n\n", horista.name);
                alterar(escolha, horista, tipo_empregado, id);
                break;
            }    
        }

        for (j = 0; j < assalariado_list.size(); j++)
        {
            assalariado = assalariado_list.get(j);

            if(tipo_empregado == 1)
            {
                break;
            }

            if(assalariado.id == id)
            {
                tipo_empregado = 2;
                auxiliares.assal = assalariado;
                auxiliares.horis_ou_assal = 2;
                System.out.printf("O ID informado pertence ao funcionário %s.\n\n", assalariado.name);
                alterar(escolha, assalariado, tipo_empregado, id);
                break;
            }    
        }

        id++;
        return;
    }

    void alterar(int escolha, Empregado empregado, int tipo_empregado, int id) //último parâmetro verifica se o funcio. é horista ou assalariado
    {
        if(escolha == 1)
        {
            System.out.printf("O nome do funcionário é %s.\n", empregado.name);

            auxiliares.nome = empregado.name;

            empregado.alterar_nome(empregado);

            auxiliares.nome_alterado = empregado.name;

            System.out.printf("\nAlteração realizada com sucesso\n\n");
        }
        else if(escolha == 2)
        {
            System.out.printf("O endereço do funcionário é %s.\n", empregado.endereco);

            auxiliares.endereco = empregado.endereco;

            empregado.alterar_endereco(empregado);

            auxiliares.endereco_alterado = empregado.endereco;

            System.out.printf("\nAlteração realizada com sucesso\n\n");
        }
        else if(escolha == 3)
        {
            if(tipo_empregado == 1)
            {
                System.out.printf("O funcionário é horista e passará a ser assalariado.\n\n");
                System.out.printf("O funcionário receberá comissão?\n1) Sim\n2) Não\n\n-> ");

                int comi = input.nextInt();

                double valor_comissao = 0;

                if(comi == 1)
                {
                    System.out.printf("Qual o valor da comissão?\n\n-> ");

                    valor_comissao = input.nextDouble();
                }
                            
                System.out.printf("Digite o salário do funcionário assalariado\n\n-> ");

                double salario_assalariado = input.nextDouble();

                Assalariado novo = new Assalariado(empregado.name, empregado.endereco, salario_assalariado, comi, valor_comissao, empregado.id, empregado.is_sindicate, empregado.escolha_pag, empregado.metodo_pag);
                            
                if(empregado.is_sindicate)
                {
                    novo.sindicate(empregado.id_sindicato, empregado.taxa_mensal_sindicato);
                }

                id_sindicato++;

                assalariado_list.add(novo);
                            

                for(int k = 0; k < horista_list.size(); k++)
                {
                    Assalariado assalariado = assalariado_list.get(k);

                    if(assalariado.id == novo.id)
                    {
                        for(Taxa_de_servico taxa : empregado.taxa_list)
                        {
                            novo.taxa_list.add(taxa);
                        }
                    }
                }

                auxiliares.assal = novo;

                for (Horista horista : horista_list)
                {
                    if(horista.id == id)
                    {
                        horista_list.remove(horista);
                        break;
                    }
                }
            }
            else
            {
                System.out.printf("O funcionário é assalariado e passará a ser horista.\n");

                System.out.printf("Digite o salário do funcionário horista\n\n-> ");

                double salario_horista = input.nextDouble();

                Horista novo = new Horista(empregado.name, empregado.endereco, salario_horista, empregado.id, empregado.is_sindicate, empregado.escolha_pag, empregado.metodo_pag);
                        
                if(empregado.is_sindicate)
                {
                    novo.sindicate(empregado.id_sindicato, empregado.taxa_mensal_sindicato);
                }

                id_sindicato++;

                horista_list.add(novo);
                        
                for(int k = 0; k < horista_list.size(); k++)
                {
                    Horista horista = horista_list.get(k);

                    if(horista.id == novo.id)
                    {
                        for(Taxa_de_servico taxa : empregado.taxa_list)
                        {
                            novo.taxa_list.add(taxa);
                        }
                        break;
                    }
                }

                auxiliares.horis = novo;

                for (Assalariado funcionario : assalariado_list)
                {
                    if(funcionario.id == id)
                    {
                        assalariado_list.remove(funcionario);
                        break;
                    }
                }
            }

            System.out.printf("\nAlteração realizada com sucesso\n\n");

        }
        else if(escolha == 4)
        {
            System.out.printf("O método de pagamento do funcionário é ");
            auxiliares.escolha_pag = empregado.escolha_pag;
            if(empregado.escolha_pag == 1)
            {                            
                System.out.printf("Cheque pelos correios\n\n");
                System.out.printf("Qual o novo método de pagamento?\n2) Cheque em mãos\n3) Depósito em conta bancária\n");
            }
            else if(empregado.escolha_pag == 2)
            {
                System.out.printf("Cheque em mãos\n\n");
                System.out.printf("Qual o novo método de pagamento?\n1) Cheque pelos correios\n3) Depósito em conta bancária\n");
            }
            else
            {
                System.out.printf("Depósito pelos correios\n\n");
                System.out.printf("Qual o novo método de pagamento?\n1) Cheque pelos correios\n3) Cheque em mãos\n");
            }

            empregado.alterar_meth_pag(empregado);

            auxiliares.escolha_pag_alterada = empregado.escolha_pag;

            System.out.printf("\nAlteração realizada com sucesso\n\n");
        }
        else if(escolha == 5)
        {
            auxiliares.is_sindicate = empregado.is_sindicate;

            if(empregado.is_sindicate)
            {
                System.out.printf("O funcionário pertencia ao sindicato e agora não pertence mais.\n");
                empregado.alterar_is_sind(empregado);
            }
            else
            {
                System.out.printf("O funcionário não pertencia ao sindicato e agora pertence.\n");
                empregado.alterar_is_sind(empregado);
                id_sindicato++;
            }

            auxiliares.is_sindicate_alterado = empregado.is_sindicate;

            System.out.printf("\nAlteração realizada com sucesso\n\n");
        }
        else if(escolha == 6)
        {
            auxiliares.id_sindicato = empregado.id_sindicato;
            System.out.printf("O ID do funcionário no sindicato é %d.\n", empregado.id_sindicato);
            empregado.alterar_id_sind(empregado);
            auxiliares.id_sindicato_alterado = empregado.id_sindicato;
            System.out.printf("\nAlteração realizada com sucesso\n\n");
        }
        else
        {
            auxiliares.taxa_sindical = empregado.taxa_mensal_sindicato;
            System.out.printf("A taxa mensal do funcionário no sindicato é %.2f.\n", empregado.taxa_mensal_sindicato);
            empregado.alterar_taxa(empregado);
            auxiliares.taxa_sindical_alterada = empregado.taxa_mensal_sindicato;
            System.out.printf("\nAlteração realizada com sucesso\n\n");
        }
    }

    void criar_nova_agenda()
    {
        agenda.criar_nova_agenda();
    }

    void rodar_folha()
    {
        LocalDate data = LocalDate.now();
        Calendar calendario = Calendar.getInstance();

        String dia_atual = data.getDayOfWeek().name();

        if(dia_atual == "MONDAY")
        {
            dia_atual = "Segunda";
        }
        else if(dia_atual == "TUESDAY")
        {
            dia_atual = "Terça";
        }
        else if(dia_atual == "WEDNESDAY")
        {
            dia_atual = "Quarta";
        }
        else if(dia_atual == "THURSDAY")
        {
            dia_atual = "Terça";
        }
        else if(dia_atual == "FRIDAY")
        {
            dia_atual = "Sexta";
        }

        int semana_atual = calendario.get(Calendar.WEEK_OF_MONTH);
        int dia = data.getDayOfMonth();

        System.out.printf("Funcionários Horistas que devem ser pagos hoje:\n\n");

        String pagamento;
        double salario;

        for (Horista horista : horista_list) 
        {
            pagamento = horista.metodo_pag;

            salario = 0;

            for (Cartao_de_ponto card : horista.cartao_list) 
            {
                if(card.horas_trabalhadas > 8)
                {
                    salario += 8 * horista.salario;
                    salario += (card.horas_trabalhadas - 8) * (0.0015*horista.salario + horista.salario);
                }
                else
                {
                    salario += (card.horas_trabalhadas)*horista.salario;
                }
            }

            if(horista.is_sindicate)
            {
                salario -= horista.taxa_mensal_sindicato;

                for (Taxa_de_servico taxas : horista.taxa_list) 
                {
                    salario -= taxas.valor;
                }
            }

            if(pagamento.contains(dia_atual))
            {
                if(pagamento.contains("1"))
                {
                    System.out.printf("Nome: %s\n", horista.name);

                    System.out.printf("A receber: %.2f\n\n", salario);
                }
                else if(pagamento.contains("2"))
                {
                    if(semana_atual == 2 || semana_atual == 4)
                    {
                        System.out.printf("Nome: %s\n", horista.name);

                        System.out.printf("A receber: %.2f\n\n", salario);
                    }
                }
            }
        }

        System.out.printf("Funcionários Assalariados que devem ser pagos hoje:\n\n");

        for (Assalariado assalariado : assalariado_list) 
        {
            pagamento = assalariado.metodo_pag;

            salario = 0;
            double comissao;

            salario += assalariado.salario;

            if(assalariado.comissionado == 1)
            {
                comissao = (assalariado.comissao)/100;

                for (Resultado_venda vendas : assalariado.venda_list)
                {
                    salario += (vendas.valor_do_produto)*comissao;
                }
            }

            if(assalariado.is_sindicate)
            {
                salario -= assalariado.taxa_mensal_sindicato;

                for (Taxa_de_servico taxas : assalariado.taxa_list) 
                {
                    salario -= taxas.valor;
                }
            }

            if(pagamento.contains("Semanal"))
            {
                if(pagamento.contains(dia_atual))
                {
                    if(pagamento.contains("1"))
                    {
                        System.out.printf("Nome: %s\n", assalariado.name);

                        System.out.printf("A receber: %.2f\n\n", salario);
                    }
                    else if(pagamento.contains("2"))
                    {
                        if(semana_atual == 2 || semana_atual == 4)
                        {
                            System.out.printf("Nome: %s\n", assalariado.name);

                            System.out.printf("A receber: %.2f\n\n", salario);
                        }
                    }
                }
            }
            else
            {
                if(pagamento.contains("Mensal"))
                {
                    if(pagamento.contains("$"))
                    {
                        if(dia == 30)
                        {
                            System.out.printf("Nome: %s\n", assalariado.name);

                            System.out.printf("A receber: %.2f\n\n", salario);
                        }
                    }
                    else
                    {
                        String numero = "";
                        for(int i = 7; i < pagamento.length(); i++)
                        {
                            numero += pagamento.charAt(i);
                        }
                        //char numero = pagamento.charAt(7);
                        //int aux = Character.getNumericValue(numero);
                        int num = Integer.parseInt(numero);

                        if(dia == num)
                        {
                            System.out.printf("Nome: %s\n", assalariado.name);

                            System.out.printf("A receber: %.2f\n\n", salario);
                        }
                    }
                }
            }
        }
    }
}