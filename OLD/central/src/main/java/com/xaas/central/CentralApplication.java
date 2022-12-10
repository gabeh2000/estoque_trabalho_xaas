package com.xaas.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
/*imports do http */

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class CentralApplication {
    private static ApplicationContext applicationContext;

	public static void main(String[] args) throws IOException{
		applicationContext=SpringApplication.run(CentralApplication.class, args);
		displayAllBeans();
		verificaMudança();
			
		

	}

	
	public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }


    public static String getCoisas(String url) throws IOException {
		HttpGet g_request_produto = new HttpGet(url);

		// add g_request_produto headers
		g_request_produto.addHeader("custom-key", "mkyong");
		g_request_produto.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
			 CloseableHttpResponse response = httpClient.execute(g_request_produto)) {

			// Get HttpResponse Status
			System.out.println(response.getProtocolVersion());              // HTTP/1.1
			System.out.println(response.getStatusLine().getStatusCode());   // 200
			System.out.println(response.getStatusLine().getReasonPhrase()); // OK
			System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// return it as a String
				String result_produto = EntityUtils.toString(entity);
				//System.out.println(result_produto);
				return result_produto;
			}

		}
		return "";
    }

	public static String putCoisas(String[] novoProd) throws IOException{
		String putUrl = "http://localhost:9191/produtos/";
		String[] aux = novoProd[0].split(":");

		HttpPut httpPut = new HttpPut(putUrl+aux[1]);

		httpPut.setHeader("Accept", "application/json");
		httpPut.setHeader(HttpHeaders.USER_AGENT, "application/json");

		String json = "{\r\n" + 
  			"  "+novoProd[0]+",\r\n" + 
 			"  "+novoProd[1]+",\r\n" + 
  			"  "+novoProd[2]+",\r\n" + 
  			"  "+novoProd[3]+"\r\n" + 
  			"}";
		//System.out.println(json);
		String result = "";
		httpPut.setEntity(new StringEntity(json));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPut)) {

            result = EntityUtils.toString(response.getEntity());
        }
		return result;
	}

	public static String[] trataString(String[] jsons){
		for(int i = 0; i<jsons.length; i++){
			if(jsons[i].contains("[")){
				jsons[i].replace('[','');
				System.out.println("Cheguei");
			}
			if(jsons[i].contains(']')){
				jsons[i].replaceAll(']','');
			}
			if(jsons[i].contains("{")){
				jsons[i].replaceAll('{','');
			}
			if(jsons[i].contains("}")){
				jsons[i].replaceAll('}','');

			}

		}
		return jsons;
	}

	public static void verificaMudança()throws IOException{
		String respostaProd;
		String respostaReserva;
	
		while(true){
			respostaProd = getCoisas("http://localhost:9191/produtos/");
			respostaReserva = getCoisas("http://localhost:9192/reservas/");

			String[] reservaSplit = respostaReserva.split("},");
			String[] produtoSplit = respostaProd.split("},");
			reservaSplit = trataString(reservaSplit);
			System.out.println(reservaSplit[0]);
			produtoSplit = trataString(produtoSplit);
			//System.out.println(reservaSplit[0]);
			for(int i = 0;i<reservaSplit.length;i++){
				String[] reservaI = reservaSplit[i].split(",");
				String[] reservaProduto = reservaI[1].split(":");
				for(int j = 0;j<produtoSplit.length;j++){
					String[] produtoJ = produtoSplit[j].split(",");
					String[] produtoNome = produtoJ[1].split(":");
					System.out.println(reservaProduto[1]);
					if(reservaProduto[1].equals(produtoNome[1])){
						String[] limite = reservaI[2].split(":");
						String[] qtd = produtoJ[2].split(":");

						int limiteToint = Integer.parseInt(limite[1]);
						int qtdToint = Integer.parseInt(qtd[1]);
						if(qtdToint<limiteToint){
							String[] qtd_reservada = reservaI[3].split(":");
							int qtdReservaToint = Integer.parseInt(qtd_reservada[1]);
							qtdToint +=qtdReservaToint;

							String novaQtd = qtd[0]+":"+Integer.toString(qtdToint);

							produtoJ[2] = novaQtd;

							System.out.println(putCoisas(produtoJ));

						}
					}
				}
			}

		}
	}
}
