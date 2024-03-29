package com.xaas.central.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.xaas.central.event.OrderCreatedEvent;

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
import org.apache.http.client.methods.HttpPost;

import java.sql.Timestamp;

import java.io.IOException;

@Component
public class ProdutoUpdatedListener {

    @RabbitListener(queues = "produtos.v1.produto-updated.generate-verificacao")
    public void onOrderCreated(OrderCreatedEvent event) throws IOException {
        System.out.println("Id recebido " + event.getId());
        System.out.println("Nome recebido " + event.getQtd());

        verificaMudança();
    }


	/*public static String postCompra(String produto, String qtd, String preco, String dataHora) throws IOException{
		String result = "";
        HttpPost post = new HttpPost("http://localhost:9193/compras/");

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append(produto+",");
		json.append(qtd+",");
        json.append("\"preco\":"+preco+",");
        json.append("\"dataHora\":\""+dataHora+"\"");
        json.append("}");

        // send a JSON data
        post.setEntity(new StringEntity(json.toString()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
        }

        return result;
	}*/
	
	public static String putCoisas(String[] novoProd) throws IOException{
		//String putUrl = "http://service-1:9191/produtos/";
		String putUrl = "http://localhost:9191/produtos/";
		/*String[] aux = novoProd[0].split(":");
		aux[1] = aux[1].replace("\"","");*/
		HttpPut httpPut = new HttpPut(putUrl);

		httpPut.setHeader("Accept", "application/json");
		httpPut.setHeader("Content-type", "application/json");

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

				//Assim que é a saída
				//[{"produtoId":"14139131","nome":"coca","qtd":100,"preco":7.0},{"produtoId":"ca73ee6e","nome":"leite","qtd":299,"preco":4.5}]

				return result_produto;
			}

		}
		return "";
    }

	public static String[] trataString(String[] str){
		//String[]
		for(int i=0;i<str.length;i++){
			str[i]=str[i].replace("[","");
			str[i]=str[i].replace("}","");
			str[i]=str[i].replace("]","");
			str[i]=str[i].replace("{","");

		}

		return str;
	}
	
	public static void verificaMudança() throws IOException{
		String reserva;
		String produto;
		
        reserva = getCoisas("http://localhost:9192/reservas/");
        produto = getCoisas("http://localhost:9191/produtos/");

		//reserva = getCoisas("http://service-2:9192/reservas/");
        //produto = getCoisas("http://service-1:9191/produtos/");

        String[] reservaSplit = trataString(reserva.split("},"));
        String[] produtoSplit =trataString(produto.split("},"));
        //funciona mesmo quando o caractere não é presente
        //System.out.println(produtoSplit[0].replace("}",""));
        for(int i=0;i<reservaSplit.length;i++){
            String[] reservaAux = reservaSplit[i].split(",");
            String[] reservaProduto = reservaAux[1].split(":");
            for(int j=0;j<produtoSplit.length;j++){
                String[] produtoAux = produtoSplit[i].split(",");
                String[] produtoNome = produtoAux[1].split(":");
                if(produtoNome[1].equals(reservaProduto[1])){
                    String[] limite = reservaAux[2].split(":");
                    String[] qtd = produtoAux[2].split(":");

                    int limiteToint = Integer.parseInt(limite[1]);
                    int qtdToint = Integer.parseInt(qtd[1]);
                    if(qtdToint<limiteToint){
                        String[] qtd_reservada = reservaAux[3].split(":");
                        int qtdReservaToint = Integer.parseInt(qtd_reservada[1]);
                        String[] preco = produtoAux[3].split(":");
                        float precoTofloat = Float.parseFloat(preco[1]);
                        precoTofloat *= qtdReservaToint;
                        qtdToint +=qtdReservaToint;

                        String novaQtd = qtd[0]+":"+Integer.toString(qtdToint);

                        produtoAux[2] = novaQtd;

                        System.out.println(putCoisas(produtoAux));

                        //Timestamp timestamp = new Timestamp(System.currentTimeMillis() );
                        
                        
                        //System.out.println(postCompra(reservaAux[1], reservaAux[2], Float.toString(precoTofloat), timestamp.toString() ) );


                    }
                }
            }
        }
		
	}
}
