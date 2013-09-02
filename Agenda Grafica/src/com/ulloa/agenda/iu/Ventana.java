package com.ulloa.agenda.iu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.ulloa.agenda.modelo.ModeloDeContactos;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JLabel lblNombre, lblApellido, lblTelefono;
	private JTextField txtNombre, txtApellido, txtTelefono;
	private JButton btnAgregar, btnEliminar;
	private JPanel panel;
	private JTable tablaDeContacto;
	
	public Ventana() throws Exception{
		super("Welcome to agenda");
		setSize(440,429);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		SwingUtilities.updateComponentTreeUI(this);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,2,8,8));
		
		lblNombre = new JLabel("Nombre");
		lblTelefono = new JLabel("Telefono");
		lblApellido = new JLabel("Apellido");
		
		txtNombre = new JTextField(12);
		txtApellido = new JTextField(12);
		txtTelefono = new JTextField(12);
		
		btnAgregar = new JButton("Agregar");
		btnEliminar = new JButton("Eliminar");
		
		
		
		btnAgregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				if(txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtTelefono.getText().equals("")){
					JOptionPane.showMessageDialog(null, "No debe dejar los campos vacios","Error al agregar", JOptionPane.ERROR_MESSAGE);
				
				}else{
					
					ModeloDeContactos.getIntancia().agregarContacto(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText());
				}
				txtNombre.setText("");
				txtApellido.setText("");
				txtTelefono.setText("");
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				if(tablaDeContacto.getSelectedRow() != -1){
					
					ModeloDeContactos.getIntancia().eliminarContacto(tablaDeContacto.getSelectedRow());
						
				} else{
					JOptionPane.showMessageDialog(null,"Debe selecionar una fila para borrar","Error al borrar un contacto",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel.add(lblNombre);
		panel.add(txtNombre);
		panel.add(lblApellido);
		panel.add(txtApellido);
		panel.add(lblTelefono);
		panel.add(txtTelefono);
		panel.add(btnAgregar);
		panel.add(btnEliminar);
		add(panel);
		
		tablaDeContacto = new JTable(ModeloDeContactos.getIntancia());
		tablaDeContacto.setPreferredScrollableViewportSize(new Dimension(400,220));
		add(new JScrollPane(tablaDeContacto));
		setVisible(true);
	}
}
