package CodigosTeste;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.*;

public class API_GET_Request {

		public static void main(String[] args) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("CRM/CRO:");
			String tipo = sc.next();
			
			System.out.print("UF:");
			String uf = sc.next();
			
			System.out.print("Nome do médico:");
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
					retorno+=output;
				}
				
				System.out.println(retorno);
				
				conector.disconnect();

			} 
			catch (Exception e) {
				e.printStackTrace();
			}

		}

}
