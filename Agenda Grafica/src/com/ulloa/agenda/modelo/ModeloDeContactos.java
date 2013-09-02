package com.ulloa.agenda.modelo;

import java.io.File;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ulloa.agenda.entidad.Contacto;
import com.ulloa.agenda.serializacion.Serializadora;

public class ModeloDeContactos extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<Contacto> contactos;
	private Serializadora serializadora;
	private String titulos[] = {"Nombre","Apellido","Telefono"};
	private static ModeloDeContactos instancia;
	
	public static synchronized ModeloDeContactos getIntancia(){
		
		if (instancia == null){
			instancia = new  ModeloDeContactos();
		}
		return instancia;
	} 
	
	@SuppressWarnings("unchecked")
	private ModeloDeContactos(){
		
		File archivo = new File("agenda.dat");
		serializadora = new Serializadora();
		
		if(archivo.exists()){
			contactos = (ArrayList<Contacto>) serializadora.deserializar("agenda.dat");
		
		}else{
			contactos = new ArrayList<Contacto>();
		}
		
	}
	public void agregarContacto(String nombre, String apellido, String telefono){
		
		Contacto persona = new Contacto(nombre, apellido, telefono);
		contactos.add(persona);
		serializadora.serializar(contactos);
		fireTableDataChanged();
		
	}
	public void eliminarContacto(int fila){
		
		contactos.remove(fila);
		serializadora.serializar(contactos);
		fireTableDataChanged();
	}
	
	public void modificarContacto(int fila, String nombre, String apellido, String telefono){
		
		Contacto persona = new Contacto(nombre, apellido, telefono);
		contactos.set(fila, persona);
		serializadora.serializar(contactos);
		fireTableDataChanged();
	}
	public String getColumnName(int columna){
		
		return titulos[columna];
	}

	public int getColumnCount() {
	
		return titulos.length;
	}

	public int getRowCount() {
	
		return contactos.size();
	}

	public Object getValueAt(int fila, int columna) {
		
		Contacto persona = contactos.get(fila);
		String retorno = "";
		
		switch(columna){
		case 0:
			retorno = persona.getNombre();
			break;
			
		case 1:
			retorno = persona.getApellido();
			break;
			
		case 2:
			retorno = persona.getTelefono();
			break;
		}
		return retorno;
	}
	
	public void setValueAt(Object valor, int fila, int columna) {
		
		Contacto contacto = contactos.get(fila);
		
		switch(columna){
		case 0:
			contacto.setNombre((String)valor);
			break;
			
		case 1:
			contacto.setApellido((String)valor);
			break;
			
		case 2:
			contacto.setTelefono((String)valor);
			break;
		}
		serializadora.serializar(contactos);
	}
	
	public boolean isCellEditable(int fila, int columna) {
		
		return true;
	}

	
}
