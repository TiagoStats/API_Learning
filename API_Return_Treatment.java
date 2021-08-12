package CodigosTeste;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

//Definindo as classes que irão receber o JSON e transforma-lo em objeto Java

class Dados_Retorno {

    private final String url;
    private final String total;
    private final String status;
    private final String mensagem;
    private final String api_limite;
    private final String api_consultas;
    private final List<Item> item;

    public Dados_Retorno(String url, String total, String status, String mensagem, String api_limite, String api_consultas, List<Item> item) {
        this.url = url;
        this.total = total;
        this.status = status;
        this.mensagem = mensagem;
        this.api_limite = api_limite;
        this.api_consultas = api_consultas;
        this.item = item;
    }
        
    @Override
    public String toString() {
        return new StringBuilder().append("Dados_Retorno{").append("\nURL: ")
                .append(url).append("\nNumero de Registros Encontrados: ")
                .append(total).append("\nStatus: ")
                .append(status).append("\nMensagem: ")
                .append(mensagem).append("\nLimite de Consultas: ")
                .append(api_limite).append("\nNúmero de Consultas Realizadas: ")
                .append(api_consultas).append("\nItens: ")
                .append(item).append("\n}").toString();
    }
    
    public List<Item> getNumeroDeConsultas(){
    	return item;
    }
    
    public String getSituacaoCadastral() {
    	
    	String[] itens = item.stream().map(String::valueOf).toArray(String[]::new);
    	
    	return itens[0];
    }
    
}


class Item {

    private final String tipo;
    private final String nome;
    private final String numero;
    private final String profissao;
    private final String uf;
    private final String situacao;

    public Item(String tipo, String nome, String numero, String profissao, String uf, String situacao) {
        this.tipo = tipo;
        this.nome = nome;
        this.numero = numero;
        this.profissao = profissao;
        this.uf = uf;
        this.situacao = situacao;
    }
        
    @Override
    public String toString() {
        return situacao;
        		
        		/*new StringBuilder().append("\n	Item\n	{").append("\n	Tipo: ")
                .append(tipo).append("\n	Nome: ")
                .append(nome).append("\n	Número: ")
                .append(numero).append("\n	Profissao: ")
                .append(profissao).append("\n	UF: ")
                .append(uf).append("\n	Situação: ")
                .append(situacao).append("\n	}").toString();*/
    }
    
    public String getSituacao(){
    	
    	return situacao;
    }
    
}

public class API_Return_Treatment {

		public static void main(String[] args) {
			
			Gson gson = new Gson();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("TIPO: ");
			String tipo = sc.next();
			
			System.out.print("UF: ");
			String uf = sc.next();
			
			System.out.print("CADASTRO: ");
			String nome = sc.next();

			try {

				URL url = new URL("https://www.consultacrm.com.br/api/index.php?tipo="+tipo+"&uf="+uf+"&q="+nome+"&chave=1185342101&destino=json");
				HttpURLConnection conector = (HttpURLConnection) url.openConnection();
				conector.setDoOutput(true);
				conector.setRequestMethod("GET");
				
				if (conector.getResponseCode() != 200) {
					System.out.print("ERROR... HTTP error code : " + conector.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conector.getInputStream())));

				String output, retorno=""; 
				 
				while ((output = br.readLine()) != null) {
					retorno+=output; // Neste While é adicionado todo o retorno da API dentro da variável 'retorno'
				}
				
				
				Dados_Retorno dados_retorno = gson.fromJson(retorno, Dados_Retorno.class);//Pega o JSON que veio da API e coloca dentro da Classe que criei no começo
				
				System.out.println(dados_retorno.getNumeroDeConsultas());
				
				//Abaixo printa a situação do médico 
				/*
				if (dados_retorno.getSituacaoCadastral().equals("Transferido")){
					System.out.println("CRM Inválido. Motivo: Médico Trasferido.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Aposentado")) {
					System.out.println("CRM Inválido. Motivo: Médico Aposentado.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Cancelado")) {
					System.out.println("CRM Inválido. Motivo: CRM Cancelado.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Cassado")) {
					System.out.println("CRM Inválido. Motivo: Médico Cassado.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Falecido")) {
					System.out.println("CRM Inválido. Motivo: Médico Falecido.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Interdição cautelar - total")) {
					System.out.println("CRM Inválido. Motivo: Médico Interditado como Medida Cautelar.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Suspensão temporária")) {
					System.out.println("CRM Inválido. Motivo: CRM Suspenso Temporariamente.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Suspenso - total")) {
					System.out.println("CRM Inválido. Motivo: CRM Suspenso.");
				} else if (dados_retorno.getSituacaoCadastral().equals("Suspenso por ordem judicial - total")) {
					System.out.println("CRM Inválido. Motivo: CRM Suspenso por Ordem Judicial.");
				} else {
					System.out.println("Não de adequa.");
				}
				*/
				conector.disconnect();

			} 
			catch (Exception e) {
				e.printStackTrace();
			}

			
		}

}
