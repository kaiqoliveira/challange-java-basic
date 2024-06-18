package projedata_teste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class main {

	public static void main(String[] args) {
		   List<Funcionario> funcionarios = new ArrayList<>();

		   	//3.1 Inserir todos os funcionários
	        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
	        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
	        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
	        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("191119.88"), "Diretor"));
	        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
	        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
	        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
	        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 9), new BigDecimal("3017.45"), "Gerente"));
	        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
	        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
	        
	        //3.2 Remover o funcionário "João"da lista
	        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
	        
	        for (Funcionario funcionario : funcionarios) {
	            System.out.println("Nome: " + funcionario.getNome());
	            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento());
	            System.out.println("Salário: " + funcionario.getSalario());
	            System.out.println("Função: " + funcionario.getFuncao());
	            System.out.println("Idade: " + funcionario.getIdade());
	            System.out.println();
	        }
	        //3.3 Exibindo todos os funcionários com as informações formatadas
	        for (Funcionario funcionario : funcionarios) {
	            String dataFormatada = funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.getDefault());
	            symbols.setDecimalSeparator(',');
	            symbols.setGroupingSeparator('.');
	            DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
	            String salarioFormatado = df.format(funcionario.getSalario());

	            System.out.println("Nome: " + funcionario.getNome());
	            System.out.println("Data de Nascimento: " + dataFormatada);
	            System.out.println("Salário: " + salarioFormatado);
	            System.out.println("Função: " + funcionario.getFuncao());
	            System.out.println("Idade: " + funcionario.getIdade());
	            System.out.println();
	        }
	        //3.4 Os funcionários receberam 10% de aumento, atualizar a lista
	        for (Funcionario funcionario : funcionarios) {
	            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10")); // Aumento de 10%
	            funcionario.setSalario(novoSalario);
	        }
	        //3.5 Agrupar os funcionários por função em um Map
	        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

	        for (Funcionario funcionario : funcionarios) {
	            String funcao = funcionario.getFuncao();
	            funcionariosPorFuncao.computeIfAbsent(funcao, k -> new ArrayList<>()).add(funcionario);
	        }
	        //3.6 Exibindo funcionários agrupados em função
	        for (String funcao : funcionariosPorFuncao.keySet()) {
	            System.out.println("Funcionários da função: " + funcao);
	            List<Funcionario> listaFuncionarios = funcionariosPorFuncao.get(funcao);
	            for (Funcionario funcionario : listaFuncionarios) {
	                System.out.println("Nome: " + funcionario.getNome() +
	                                   ", Data de Nascimento: " + funcionario.getDataNascimento() +
	                                   ", Salário: " + funcionario.getSalario() +
	                                   ", Idade: " + funcionario.getIdade());
	            }
	            System.out.println();
	        }
	        //3.8 Imprimindo os funcionários que fazem aniversário no mês 10
	        System.out.println("Funcionários que fazem aniversário no mês 10:");
	        for (Funcionario funcionario : funcionarios) {
	            if (funcionario.getDataNascimento().getMonthValue() == 10) {
	                System.out.println("Nome: " + funcionario.getNome() +
	                                   ", Data de Nascimento: " + funcionario.getDataNascimento() +
	                                   ", Salário: " + funcionario.getSalario() +
	                                   ", Função: " + funcionario.getFuncao());
	            }
	        }
	        System.out.println();

	        //3.8 Imprimindo os funcionários que fazem aniversário no mês 12
	        System.out.println("Funcionários que fazem aniversário no mês 12:");
	        for (Funcionario funcionario : funcionarios) {
	            if (funcionario.getDataNascimento().getMonthValue() == 12) {
	                System.out.println("Nome: " + funcionario.getNome() +
	                                   ", Data de Nascimento: " + funcionario.getDataNascimento() +
	                                   ", Salário: " + funcionario.getSalario() +
	                                   ", Função: " + funcionario.getFuncao());
	            }
	        }
	        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade
	        Funcionario funcionarioMaisVelho = null;
	        int maiorIdade = Integer.MIN_VALUE;

	        for (Funcionario funcionario : funcionarios) {
	            int idade = LocalDate.now().getYear() - funcionario.getDataNascimento().getYear();
	            if (idade > maiorIdade) {
	                maiorIdade = idade;
	                funcionarioMaisVelho = funcionario;
	            }
	        }

	        if (funcionarioMaisVelho != null) {
	            System.out.println("Funcionário mais velho:");
	            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
	            System.out.println("Idade: " + maiorIdade);
	        } else {
	            System.out.println("Não há funcionários na lista.");
	        }
	        //3.10 Imprimir a lista de funcionários por ordem alfabética.
	        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));
	        System.out.println("Funcionários em ordem alfabética:");
	        for (Funcionario funcionario : funcionarios) {
	            System.out.println("Nome: " + funcionario.getNome() +
	                               ", Data de Nascimento: " + funcionario.getDataNascimento() +
	                               ", Salário: " + funcionario.getSalario() +
	                               ", Função: " + funcionario.getFuncao());
	        }
	        
	        //3.11 Imprimir total de salários
	        BigDecimal totalSalarios = BigDecimal.ZERO;
	        for (Funcionario funcionario : funcionarios) {
	            totalSalarios = totalSalarios.add(funcionario.getSalario());
	        }

	        
	        System.out.println("Total dos salários dos funcionários: " + totalSalarios);
	    
	        //3.12 Imprimir quantos salários mínimos cada um ganha, considerar o salário min R$1212.00
	        System.out.println("Quantidade de salários mínimos que cada funcionário ganha:");
	        BigDecimal salarioMinimo = new BigDecimal("1212.00");
	        for (Funcionario funcionario : funcionarios) {
	            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
	            System.out.println(funcionario.getNome() + ": " + quantidadeSalariosMinimos + " salários mínimos");
	        }
	    }
	
	


	}


