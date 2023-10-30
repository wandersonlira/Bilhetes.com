package controller.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import model.entidades.TabEndereco;

public class ViacepService {
	
	public TabEndereco getEndereco(String cep) throws ClientProtocolException, IOException {
		
		TabEndereco endereco = null;
		
		HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");
		
		try(CloseableHttpClient httpCliente = HttpClientBuilder.create().disableRedirectHandling().build();
				CloseableHttpResponse response = httpCliente.execute(request)) {
			
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				String resultado = EntityUtils.toString(entity);
				
				Gson gson = new Gson();
				
				endereco = gson.fromJson(resultado, TabEndereco.class);
			}
		}
		
		return endereco;
 	}

}
