package br.com.conversor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil extends ClassePadraoAndiie{

	private final static Logger LOGGER = Logger.getLogger(ConverteMain.class.getName());
	protected JsonUtil() throws JSONException, IOException {}

	public String leTodasAsLinhas(BufferedReader buferedReader) throws IOException{
		
		StringBuilder stringBuilder = new StringBuilder();
		
		int cp;
		
		while ((cp = buferedReader.read()) != -1) {
			stringBuilder.append((char) cp);
		}
		
		return stringBuilder.toString();
	}
	
	public  JSONObject lerJSONdaURL(String url) throws IOException, JSONException {
		
		InputStream inputStream = null;
		
		try {
			
			inputStream = new URL(url).openStream();
			
			BufferedReader buferedReader = 
					new BufferedReader(
							new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			
			String resultadoJson = leTodasAsLinhas(buferedReader);
			JSONObject jsonObject = new JSONObject(resultadoJson);
			
			return jsonObject;
			
		} catch (MalformedURLException e) {
			logarErro( "Erro de URL incorreta nessa desgraça!", e);					
		} catch (JSONException e) {			
			logarErro("Erro na estrutura do json nessa desgraça!",e);			
		} catch (IOException e) {
			logarErro("Erro input output nessa desgraça!",e);			
		}finally {
			logarInfo("Input Stream fechado!");
			inputStream.close();
		}
		
		return null;
	}

	@Override
	protected Logger getLogger() {
		// TODO Auto-generated method stub
		return LOGGER;
	}
	
}
