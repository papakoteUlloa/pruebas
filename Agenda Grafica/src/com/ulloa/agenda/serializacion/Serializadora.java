package com.ulloa.agenda.serializacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializadora {

	private ObjectInputStream lector;
	private ObjectOutputStream escritor;
	
	public void serializar(Object objecto){
		
		try {
			escritor = new ObjectOutputStream(new FileOutputStream("agenda.dat"));
			
			escritor.writeObject(objecto);
			escritor.flush();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	public Object deserializar(String nombreArchivo){
		
		Object retorno = null;
		try {
			lector = new ObjectInputStream(new FileInputStream(nombreArchivo));
			retorno = lector.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		return retorno;
	}
	
}
