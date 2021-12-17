package presentacion;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import excepciones.UsuarioNoExisteException;
import logica.DtProfesor;
import logica.DtUsuario;
import logica.IControladorUsuarios;
import logica.Profesor;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ModificarUsuario extends JInternalFrame {
	private IControladorUsuarios controlUsr;
	
	private JTextField emailTextField;
	private JTextField nicknameTextField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField urlTextField;
	private JComboBox<String> comboBoxUsuario;
	private JTextArea bioTextArea;
	private JTextArea descTextArea;
	private DtUsuario du;
	private JDateChooser fechaNacDateChooser;
	private JButton btnAceptar;
	private JButton btnElegirUsuario;
	
	
	public ModificarUsuario(IControladorUsuarios icu) {
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		
		controlUsr = icu;
		
		setTitle("Modificar Usuario");
		setBounds(100, 100, 604, 540);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {21, 147, 200, 30};
		gridBagLayout.rowHeights = new int[] {46, 37, 45, 43, 47, 19, 32, 54, 48, 40, 0, 16};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		btnElegirUsuario = new JButton("Elegir usuario");
		btnElegirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdElegirUsuario();
			}
		});
		GridBagConstraints gbc_btnElegirUsuario = new GridBagConstraints();
		gbc_btnElegirUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnElegirUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_btnElegirUsuario.gridx = 1;
		gbc_btnElegirUsuario.gridy = 0;
		getContentPane().add(btnElegirUsuario, gbc_btnElegirUsuario);
		
		comboBoxUsuario = new JComboBox();
		comboBoxUsuario.setEnabled(true);

		GridBagConstraints gbc_comboBoxUsuario = new GridBagConstraints();
		gbc_comboBoxUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsuario.gridx = 2;
		gbc_comboBoxUsuario.gridy = 0;
		getContentPane().add(comboBoxUsuario, gbc_comboBoxUsuario);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 1;
		getContentPane().add(lblEmail, gbc_lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setEditable(false);
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 2;
		gbc_emailTextField.gridy = 1;
		getContentPane().add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		
		JLabel lblNickname = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.WEST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 1;
		gbc_lblNickname.gridy = 2;
		getContentPane().add(lblNickname, gbc_lblNickname);
		
		nicknameTextField = new JTextField();
		nicknameTextField.setEditable(false);
		nicknameTextField.setColumns(10);
		GridBagConstraints gbc_nicknameTextField = new GridBagConstraints();
		gbc_nicknameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nicknameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nicknameTextField.gridx = 2;
		gbc_nicknameTextField.gridy = 2;
		getContentPane().add(nicknameTextField, gbc_nicknameTextField);
		
		JLabel lblEmail_1_1 = new JLabel("Nombre");
		GridBagConstraints gbc_lblEmail_1_1 = new GridBagConstraints();
		gbc_lblEmail_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblEmail_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_1_1.gridx = 1;
		gbc_lblEmail_1_1.gridy = 3;
		getContentPane().add(lblEmail_1_1, gbc_lblEmail_1_1);
		
		nombreTextField = new JTextField();
		nombreTextField.setColumns(10);
		GridBagConstraints gbc_nombreTextField = new GridBagConstraints();
		gbc_nombreTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTextField.gridx = 2;
		gbc_nombreTextField.gridy = 3;
		getContentPane().add(nombreTextField, gbc_nombreTextField);
		
		JLabel lblEmail_1_1_1 = new JLabel("Apellido");
		GridBagConstraints gbc_lblEmail_1_1_1 = new GridBagConstraints();
		gbc_lblEmail_1_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblEmail_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_1_1_1.gridx = 1;
		gbc_lblEmail_1_1_1.gridy = 4;
		getContentPane().add(lblEmail_1_1_1, gbc_lblEmail_1_1_1);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setColumns(10);
		GridBagConstraints gbc_apellidoTextField = new GridBagConstraints();
		gbc_apellidoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_apellidoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellidoTextField.gridx = 2;
		gbc_apellidoTextField.gridy = 4;
		getContentPane().add(apellidoTextField, gbc_apellidoTextField);
		
		JLabel lblEmail_1_1_1_1 = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_lblEmail_1_1_1_1 = new GridBagConstraints();
		gbc_lblEmail_1_1_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblEmail_1_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_1_1_1_1.gridx = 1;
		gbc_lblEmail_1_1_1_1.gridy = 5;
		getContentPane().add(lblEmail_1_1_1_1, gbc_lblEmail_1_1_1_1);
		
		fechaNacDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaNacDateChooser = new GridBagConstraints();
		gbc_fechaNacDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaNacDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaNacDateChooser.gridx = 2;
		gbc_fechaNacDateChooser.gridy = 5;
		getContentPane().add(fechaNacDateChooser, gbc_fechaNacDateChooser);
		
		JLabel lblExclusivoParaProfesor = new JLabel("Exclusivo para profesor");
		GridBagConstraints gbc_lblExclusivoParaProfesor = new GridBagConstraints();
		gbc_lblExclusivoParaProfesor.insets = new Insets(0, 0, 5, 5);
		gbc_lblExclusivoParaProfesor.gridx = 2;
		gbc_lblExclusivoParaProfesor.gridy = 6;
		getContentPane().add(lblExclusivoParaProfesor, gbc_lblExclusivoParaProfesor);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 7;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 7;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		descTextArea = new JTextArea();
		scrollPane.setViewportView(descTextArea);
		
		JLabel lblBiografia = new JLabel("Biografia");
		GridBagConstraints gbc_lblBiografia = new GridBagConstraints();
		gbc_lblBiografia.anchor = GridBagConstraints.WEST;
		gbc_lblBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiografia.gridx = 1;
		gbc_lblBiografia.gridy = 8;
		getContentPane().add(lblBiografia, gbc_lblBiografia);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 8;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		bioTextArea = new JTextArea();
		scrollPane_1.setViewportView(bioTextArea);
		
		JLabel lblUrl = new JLabel("URL");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.anchor = GridBagConstraints.WEST;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 1;
		gbc_lblUrl.gridy = 9;
		getContentPane().add(lblUrl, gbc_lblUrl);
		
		urlTextField = new JTextField();
		urlTextField.setColumns(10);
		GridBagConstraints gbc_urlTextField = new GridBagConstraints();
		gbc_urlTextField.insets = new Insets(0, 0, 5, 5);
		gbc_urlTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlTextField.gridx = 2;
		gbc_urlTextField.gridy = 9;
		getContentPane().add(urlTextField, gbc_urlTextField);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 10;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdEditarUsuario(e);
			}
		});
		panel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		panel.add(btnCancelar);

	}
	
	protected void cmdElegirUsuario() {
		cargarDatos();
		nombreTextField.setEditable(true);
		apellidoTextField.setEditable(true);
		fechaNacDateChooser.setEnabled(true);
		if (du instanceof DtProfesor) {
			urlTextField.setEditable(true);
			bioTextArea.setEditable(true);
			descTextArea.setEditable(true);
		} else {
			urlTextField.setEditable(false);
			bioTextArea.setEditable(false);
			descTextArea.setEditable(false);
		}
		btnAceptar.setEnabled(true);
	}
	
	public void cargarDatos() {
		du = controlUsr.getDatosUsuario((String) comboBoxUsuario.getSelectedItem());
		emailTextField.setText(du.getEmail());
		nicknameTextField.setText(du.getNickname());
		nombreTextField.setText(du.getNombre());
		apellidoTextField.setText(du.getApellido());
		fechaNacDateChooser.setDate(du.getFechaDeNacimiento());
		if (du instanceof DtProfesor) {
			DtProfesor dp = (DtProfesor) du;
			urlTextField.setText(dp.getUrl());
			bioTextArea.setText(dp.getBiografia());
			descTextArea.setText(dp.getUrl());
		} else {
			urlTextField.setText("");
			bioTextArea.setText("");
			descTextArea.setText("");
		}
	}
	
	public void editarUsuario() {
		du.setNombre(nombreTextField.getText());
		du.setApellido(apellidoTextField.getText());
		du.setFechaDeNacimiento(fechaNacDateChooser.getDate());
		if (du instanceof DtProfesor) {
			DtProfesor dp = (DtProfesor) du;
			dp.setDescripcion(descTextArea.getText());
			dp.setBiografia(bioTextArea.getText());
			dp.setUrl(urlTextField.getText());
		}
		controlUsr.editarDatosUsuario(du);
		JOptionPane.showMessageDialog(this, "El Usuario se ha editado con éxito", "Editar Usuario",
                JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void limpiarFormulario() {
		emailTextField.setText("");
		nicknameTextField.setText("");
		nombreTextField.setText("");
		apellidoTextField.setText("");
		fechaNacDateChooser.setDate(new Date());
		urlTextField.setText("");
		bioTextArea.setText("");
		descTextArea.setText("");
		comboBoxUsuario.removeAllItems();
		btnAceptar.setEnabled(false);
		btnElegirUsuario.setEnabled(false);
		
		nombreTextField.setEditable(false);
		apellidoTextField.setEditable(false);
		urlTextField.setEditable(false);
		bioTextArea.setEditable(false);
		descTextArea.setEditable(false);
		fechaNacDateChooser.setEnabled(false);
	}
	
	protected void cmdEditarUsuario(ActionEvent e) {
		if (checkFormulario()) {
			editarUsuario();
			limpiarFormulario();
			setVisible(false);
		}
	}
	
	protected boolean checkFormulario() {
		String nombre = nombreTextField.getText();
		String apellido = apellidoTextField.getText();
		if (nombre.equals("") || apellido.equals("")) {
			JOptionPane.showMessageDialog(this, "Los campos Nombre y Apellido no pueden estar vacios", "Editar Usuario",
	                JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (du instanceof DtProfesor) {
			String descripcion = descTextArea.getText();
			if (descripcion.equals("")) {
				JOptionPane.showMessageDialog(this, "El campo Descripcion no puede estar vacio", "Editar Usuario",
		                JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if (fechaNacDateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(this, "La fecha de nacimiento no es válida", "Editar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void arranque() { 
		limpiarFormulario();
		cargarComboUsuarios();
		if (comboBoxUsuario.getItemCount() > 0) {
			btnElegirUsuario.setEnabled(true);
		}
		show();
	}
	
	public void cargarComboUsuarios() {
		Set<String> users = controlUsr.getListaUsuarios();
		for(String n : users)
			comboBoxUsuario.addItem(n);
	}
	
}
