package br.com.conversor;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ClassePadraoAndiie {

	//public abstract  Logger getLoggerInfo(String s);
	
	//public abstract Logger getLoggerError(String s);
	
	protected abstract Logger getLogger();
	
	protected void logarErro(String msg, Exception e) {
		getLogger().log(Level.SEVERE, msg);
	}
	
	protected void logarInfo(String msg) {
		getLogger().log(Level.INFO, msg);
	}
	
}
