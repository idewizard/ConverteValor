package br.com.conversor;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ConverteMain extends ClassePadraoAndiie{
	
	// o seu logger tem que ser da sua classe principal
	// logo o LOGGER tem que ser criado aqui mesmo
	// porem quem vai executar os metodos da classe lOGGER sera a abstract
	// com isso devemos ensinar ela a recuperar o logger da sua classe filha
	private final static Logger LOGGER = Logger.getLogger(ConverteMain.class.getName());
	private static JSONObject jsonObj ;
	private static double porcetagemDesvalorizacao;
	private static DecimalFormat decimalFormat ;
	private static JsonUtil jsonUtil;
	

	public ConverteMain()throws JSONException, IOException{
		
		jsonUtil = new JsonUtil();
		decimalFormat = new DecimalFormat("###.###");			
		jsonObj = jsonUtil.lerJSONdaURL("http://data.fixer.io/api/latest?access_key=e7fb2d46df6b6a2d895c4deb3c236842");
		double realCapim = jsonObj.getJSONObject("rates").getDouble("BRL");		
		porcetagemDesvalorizacao = (100 - (1 / realCapim))-99;
		
	}

	
	public String getValor(String codDinheiro, double valorReais) {
		
		double valorMoedaEstrangeira = jsonObj.getJSONObject("rates").getDouble(codDinheiro);
		valorMoedaEstrangeira =  valorMoedaEstrangeira - (valorMoedaEstrangeira*porcetagemDesvalorizacao);
		valorReais = valorMoedaEstrangeira * valorReais;
		
		return String.valueOf(decimalFormat.format(valorReais));
	}
	
	
	
public String[] populaLista() {
		
		List<String> keyList = new ArrayList<String>();		
		JSONObject jobj =  jsonObj.getJSONObject("rates");
		Iterator<String> iterator = jobj.keys();
		
		while(iterator.hasNext()) {
			
			String key = iterator.next().toString();
			keyList.add(key);
		}

		ComparaCodDinheiro compara = new ComparaCodDinheiro();
		keyList.sort(compara);		
		
		
		return (String[]) keyList.toArray(new String[keyList.size()]);
		
	}


	@Override
	public Logger getLogger() {
		return LOGGER;
	}


}


class ComparaCodDinheiro implements Comparator<String>{

	public int compare(String cod1, String cod2) {
		
		return cod1.compareTo(cod2);
	}
	
}