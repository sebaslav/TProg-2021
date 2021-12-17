package presentacion;

import logica.*;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import excepciones.UsuarioRepetidoException;
import javax.swing.filechooser.FileNameExtensionFilter;

import logica.DtUsuario;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.util.Set;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private IControladorUsuarios controlUsr;
	private IControladorInstituciones controlInst;
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextArea txtBiografia;
	private JTextField txtLink;
	private JRadioButton radioSocio;
	private JRadioButton radioProfesor;
	private JTextArea txtDescripcion;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JComboBox<String> comboInstituciones;
	private JDateChooser dateChooserAU;
	private JScrollPane scrollDescripcion;
	private JLabel lblDescripcion;
	private JLabel lblBiografia;
	private JLabel lblLinkopcional;
	private JLabel lblInstitucion;
	private JLabel lblExclusivoParaProfesor;
	private JLabel lblContrasea;
	private JLabel lblConfirmarContrasea;
	private JPasswordField textContra;
	private JPasswordField txtContraConfirm;
	private JLabel lblFotoDePerfil;
	private JLabel lblImagen;
	private JButton btnFileChooser;
	private JTextField txtRutaImagen;
	private byte[] imagenCodificada; // imagen de usuario
	private JScrollPane scrollBiografia;

	public AltaUsuario(IControladorUsuarios ctrl, IControladorInstituciones ctrl2) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		controlInst = ctrl2;
		controlUsr = ctrl;
		setIconifiable(true);
		setClosable(true);
		setTitle("Alta Usuario");
		getContentPane().setEnabled(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 138, 21, 31, 83, 77, 62, 85, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 19, 19, 19, 25, 70, 25, 21, 21, 15, 24, 38, 38, 19, 35, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("Ingresar nombre");
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridwidth = 3;
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 0;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 5;
		gbc_lblApellido.gridy = 0;
		getContentPane().add(lblApellido, gbc_lblApellido);

		txtApellido = new JTextField();
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.gridwidth = 2;
		gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellido.gridx = 6;
		gbc_txtApellido.gridy = 0;
		getContentPane().add(txtApellido, gbc_txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNombre = new JLabel("Nickname");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		getContentPane().add(lblNombre, gbc_lblNombre);

		txtNickname = new JTextField();
		GridBagConstraints gbc_txtNickname = new GridBagConstraints();
		gbc_txtNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNickname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNickname.gridwidth = 3;
		gbc_txtNickname.gridx = 2;
		gbc_txtNickname.gridy = 1;
		getContentPane().add(txtNickname, gbc_txtNickname);
		txtNickname.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 5;
		gbc_lblEmail.gridy = 1;
		getContentPane().add(lblEmail, gbc_lblEmail);

		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.gridwidth = 2;
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.gridx = 6;
		gbc_txtEmail.gridy = 1;
		getContentPane().add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		lblContrasea = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 1;
		gbc_lblContrasea.gridy = 2;
		getContentPane().add(lblContrasea, gbc_lblContrasea);

		radioSocio = new JRadioButton("");
		radioSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarFormularioProfesor(false);
				;
			}
		});

		textContra = new JPasswordField();
		GridBagConstraints gbc_textContra = new GridBagConstraints();
		gbc_textContra.fill = GridBagConstraints.HORIZONTAL;
		gbc_textContra.insets = new Insets(0, 0, 5, 5);
		gbc_textContra.gridwidth = 3;
		gbc_textContra.gridx = 2;
		gbc_textContra.gridy = 2;
		getContentPane().add(textContra, gbc_textContra);
		textContra.setColumns(10);

		lblConfirmarContrasea = new JLabel("<html>Confirmar<br>contraseña</html>");
		GridBagConstraints gbc_lblConfirmarContrasea = new GridBagConstraints();
		gbc_lblConfirmarContrasea.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblConfirmarContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmarContrasea.gridx = 5;
		gbc_lblConfirmarContrasea.gridy = 2;
		getContentPane().add(lblConfirmarContrasea, gbc_lblConfirmarContrasea);

		txtContraConfirm = new JPasswordField();
		GridBagConstraints gbc_txtContraConfirm = new GridBagConstraints();
		gbc_txtContraConfirm.gridwidth = 2;
		gbc_txtContraConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContraConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_txtContraConfirm.gridx = 6;
		gbc_txtContraConfirm.gridy = 2;
		getContentPane().add(txtContraConfirm, gbc_txtContraConfirm);
		txtContraConfirm.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 1;
		gbc_lblFecha.gridy = 3;
		getContentPane().add(lblFecha, gbc_lblFecha);

		dateChooserAU = new JDateChooser();
		GridBagConstraints gbc_dateChooserAU = new GridBagConstraints();
		gbc_dateChooserAU.fill = GridBagConstraints.BOTH;
		gbc_dateChooserAU.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserAU.gridwidth = 3;
		gbc_dateChooserAU.gridx = 2;
		gbc_dateChooserAU.gridy = 3;
		getContentPane().add(dateChooserAU, gbc_dateChooserAU);

		lblImagen = new JLabel("[No hay imagen seleccionada]");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.fill = GridBagConstraints.BOTH;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridwidth = 5;
		gbc_lblImagen.gridx = 2;
		gbc_lblImagen.gridy = 4;
		getContentPane().add(lblImagen, gbc_lblImagen);

		lblFotoDePerfil = new JLabel("Foto de perfil");
		GridBagConstraints gbc_lblFotoDePerfil = new GridBagConstraints();
		gbc_lblFotoDePerfil.anchor = GridBagConstraints.EAST;
		gbc_lblFotoDePerfil.insets = new Insets(0, 0, 5, 5);
		gbc_lblFotoDePerfil.gridx = 1;
		gbc_lblFotoDePerfil.gridy = 5;
		getContentPane().add(lblFotoDePerfil, gbc_lblFotoDePerfil);

		txtRutaImagen = new JTextField();
		txtRutaImagen.setEditable(false);
		GridBagConstraints gbc_txtRutaImagen = new GridBagConstraints();
		gbc_txtRutaImagen.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRutaImagen.insets = new Insets(0, 0, 5, 5);
		gbc_txtRutaImagen.gridwidth = 4;
		gbc_txtRutaImagen.gridx = 2;
		gbc_txtRutaImagen.gridy = 5;
		getContentPane().add(txtRutaImagen, gbc_txtRutaImagen);
		txtRutaImagen.setColumns(10);

		btnFileChooser = new JButton("Elegir");
		btnFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				elegirImagen();
			}
		});
		GridBagConstraints gbc_btnFileChooser = new GridBagConstraints();
		gbc_btnFileChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFileChooser.insets = new Insets(0, 0, 5, 5);
		gbc_btnFileChooser.gridx = 6;
		gbc_btnFileChooser.gridy = 5;
		getContentPane().add(btnFileChooser, gbc_btnFileChooser);

		JLabel lblSocio = new JLabel("Socio");
		GridBagConstraints gbc_lblSocio = new GridBagConstraints();
		gbc_lblSocio.anchor = GridBagConstraints.EAST;
		gbc_lblSocio.insets = new Insets(0, 0, 5, 5);
		gbc_lblSocio.gridx = 1;
		gbc_lblSocio.gridy = 6;
		getContentPane().add(lblSocio, gbc_lblSocio);
		GridBagConstraints gbc_radioSocio = new GridBagConstraints();
		gbc_radioSocio.anchor = GridBagConstraints.WEST;
		gbc_radioSocio.insets = new Insets(0, 0, 5, 5);
		gbc_radioSocio.gridx = 2;
		gbc_radioSocio.gridy = 6;
		getContentPane().add(radioSocio, gbc_radioSocio);

		radioProfesor = new JRadioButton("");
		radioProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarFormularioProfesor(true);
				;
			}
		});

		JLabel lblProfesor = new JLabel("Profesor");
		GridBagConstraints gbc_lblProfesor = new GridBagConstraints();
		gbc_lblProfesor.anchor = GridBagConstraints.EAST;
		gbc_lblProfesor.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfesor.gridx = 1;
		gbc_lblProfesor.gridy = 7;
		getContentPane().add(lblProfesor, gbc_lblProfesor);
		GridBagConstraints gbc_radioProfesor = new GridBagConstraints();
		gbc_radioProfesor.anchor = GridBagConstraints.WEST;
		gbc_radioProfesor.insets = new Insets(0, 0, 5, 5);
		gbc_radioProfesor.gridx = 2;
		gbc_radioProfesor.gridy = 7;
		getContentPane().add(radioProfesor, gbc_radioProfesor);

		lblExclusivoParaProfesor = new JLabel("Exclusivo para profesor");
		GridBagConstraints gbc_lblExclusivoParaProfesor = new GridBagConstraints();
		gbc_lblExclusivoParaProfesor.insets = new Insets(0, 0, 5, 5);
		gbc_lblExclusivoParaProfesor.gridwidth = 2;
		gbc_lblExclusivoParaProfesor.gridx = 4;
		gbc_lblExclusivoParaProfesor.gridy = 8;
		getContentPane().add(lblExclusivoParaProfesor, gbc_lblExclusivoParaProfesor);

		lblInstitucion = new JLabel("Institucion");
		GridBagConstraints gbc_lblInstitucion = new GridBagConstraints();
		gbc_lblInstitucion.anchor = GridBagConstraints.EAST;
		gbc_lblInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstitucion.gridx = 1;
		gbc_lblInstitucion.gridy = 9;
		getContentPane().add(lblInstitucion, gbc_lblInstitucion);

		comboInstituciones = new JComboBox();
		GridBagConstraints gbc_comboInstituciones = new GridBagConstraints();
		gbc_comboInstituciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboInstituciones.insets = new Insets(0, 0, 5, 5);
		gbc_comboInstituciones.gridwidth = 5;
		gbc_comboInstituciones.gridx = 2;
		gbc_comboInstituciones.gridy = 9;
		getContentPane().add(comboInstituciones, gbc_comboInstituciones);

		lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 10;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		scrollDescripcion = new JScrollPane();
		GridBagConstraints gbc_scrollDescripcion = new GridBagConstraints();
		gbc_scrollDescripcion.fill = GridBagConstraints.BOTH;
		gbc_scrollDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_scrollDescripcion.gridwidth = 5;
		gbc_scrollDescripcion.gridx = 2;
		gbc_scrollDescripcion.gridy = 10;
		getContentPane().add(scrollDescripcion, gbc_scrollDescripcion);

		txtDescripcion = new JTextArea();
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setLineWrap(true);
		scrollDescripcion.setViewportView(txtDescripcion);

		lblBiografia = new JLabel("<html>Biografia<br>(opcional)</html>");
		GridBagConstraints gbc_lblBiografia = new GridBagConstraints();
		gbc_lblBiografia.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiografia.gridx = 1;
		gbc_lblBiografia.gridy = 11;
		getContentPane().add(lblBiografia, gbc_lblBiografia);

		scrollBiografia = new JScrollPane();
		GridBagConstraints gbc_scrollBiografia = new GridBagConstraints();
		gbc_scrollBiografia.fill = GridBagConstraints.BOTH;
		gbc_scrollBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_scrollBiografia.gridwidth = 5;
		gbc_scrollBiografia.gridx = 2;
		gbc_scrollBiografia.gridy = 11;
		getContentPane().add(scrollBiografia, gbc_scrollBiografia);

		txtBiografia = new JTextArea();
		scrollBiografia.setViewportView(txtBiografia);
		txtBiografia.setWrapStyleWord(true);
		txtBiografia.setLineWrap(true);
		txtBiografia.setColumns(10);

		lblLinkopcional = new JLabel("Link (opcional)");
		GridBagConstraints gbc_lblLinkopcional = new GridBagConstraints();
		gbc_lblLinkopcional.anchor = GridBagConstraints.EAST;
		gbc_lblLinkopcional.insets = new Insets(0, 0, 5, 5);
		gbc_lblLinkopcional.gridx = 1;
		gbc_lblLinkopcional.gridy = 12;
		getContentPane().add(lblLinkopcional, gbc_lblLinkopcional);

		txtLink = new JTextField();
		GridBagConstraints gbc_txtLink = new GridBagConstraints();
		gbc_txtLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLink.insets = new Insets(0, 0, 5, 5);
		gbc_txtLink.gridwidth = 5;
		gbc_txtLink.gridx = 2;
		gbc_txtLink.gridy = 12;
		getContentPane().add(txtLink, gbc_txtLink);
		txtLink.setColumns(10);

		JPanel panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 0, 5);
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridwidth = 5;
		gbc_panelBotones.gridx = 2;
		gbc_panelBotones.gridy = 13;
		getContentPane().add(panelBotones, gbc_panelBotones);

		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarUsuario();
			}
		});
		panelBotones.add(botonAceptar);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarAltaUsuario();
			}
		});
		panelBotones.add(botonCancelar);
		setBounds(100, 100, 579, 678);

		habilitarFormularioProfesor(false);
		;
	}

	/**
	 * Vacía (si es que habia) y carga desde cero los nombres de todas las
	 * instituciones en el sistema en el JComboBox de Instituciones
	 */
	public void cargarInstituciones() {

		// Nuevo modelo
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();

		Set<String> nombreInstituciones = controlInst.getInstituciones(); // instituciones del sistema
		for (String nomInst : nombreInstituciones)
			modelo.addElement(nomInst); // agregar a modelo

		comboInstituciones.setModel(modelo); // setear modelo

		comboInstituciones.setSelectedIndex(-1); // sin institucion elegida por defecto
	}

	private void registrarUsuario() {
		// Primero checkeo el formulario
		if (checkFormulario()) {
			try {
				// fecha provisional
				Date fecha = dateChooserAU.getDate();
				DtUsuario user;
				if (radioSocio.isSelected()) {
					user = new DtSocio(txtNickname.getText(), txtNombre.getText(), txtApellido.getText(),
							txtEmail.getText(), fecha, textContra.getText(), this.imagenCodificada, null, null, null,
							null, null, null);

				} else {
					user = new DtProfesor(txtNickname.getText(), txtNombre.getText(), txtApellido.getText(),
							txtEmail.getText(), fecha, textContra.getText(), this.imagenCodificada, null, null,
							txtDescripcion.getText(), (String) comboInstituciones.getSelectedItem(), txtLink.getText(),
							txtBiografia.getText(), null, null, null);
				}
				controlUsr.registrarUsuario(user);
				JOptionPane.showMessageDialog(null, "Usuario dado de alta exitosamente");
				hide();
				limpiarFormulario();
			} catch (UsuarioRepetidoException e) {
				JOptionPane.showMessageDialog(this, "Error : El Usuario ya existe en el sistema", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean checkFormulario() {

		boolean esCorrecto = false;
		try {
			esCorrecto = dateChooserAU.getDate() != null;
			boolean botonSocioApretado = radioSocio.isSelected();
			boolean botonProfesorApretado = radioProfesor.isSelected();
			boolean botonApretado = botonSocioApretado ^ botonProfesorApretado; // XOR

			String nombreU = this.txtNombre.getText();
			String apellidoU = this.txtApellido.getText();
			String emailU = this.txtEmail.getText();
			String nicknameU = this.txtNickname.getText();
			String descripcionU = this.txtDescripcion.getText();
			String institucionU = (String) this.comboInstituciones.getSelectedItem();
			String contra = this.textContra.getText();
			String contraConfirm = this.txtContraConfirm.getText();

			esCorrecto = esCorrecto && botonApretado && !nombreU.isEmpty() && !apellidoU.isEmpty() && !emailU.isEmpty()
					&& !nicknameU.isEmpty() && !contra.isEmpty() && !contraConfirm.isEmpty();

			if (botonProfesorApretado) {
				esCorrecto = esCorrecto && !descripcionU.isEmpty() && institucionU != null && !institucionU.isEmpty();
			}

			// no es Correcto sii no hay boton apretado o campos basicos de usuario son
			// vacios
			if (esCorrecto == false) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
			}

			if (!contra.equals(contraConfirm)) {
				esCorrecto = false;
				JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales", "Registrar Usuario",
						JOptionPane.ERROR_MESSAGE);
			}

			return esCorrecto;
		} catch (NullPointerException e) { // .isEmpty() puede causar NullPointerException
			esCorrecto = false;
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Se produjo un error", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);

			return esCorrecto;
		}
	}

	/**
	 * Muestra (u oculta) formulario de Alta de Profesor
	 * 
	 * @param mostrar True si y solo si se quiere mostrar formulario de profe
	 */
	private void habilitarFormularioProfesor(boolean mostrar) {
		radioSocio.setSelected(!mostrar);
		radioProfesor.setSelected(mostrar);

		txtDescripcion.setEditable(mostrar);
		comboInstituciones.setEnabled(mostrar);
		// Cargar instituciones solo cuando el profe esta habilitado
		if (mostrar) {
			cargarInstituciones();
		}

		txtDescripcion.setEditable(mostrar);
		txtDescripcion.setEnabled(mostrar);
		scrollDescripcion.setVisible(mostrar);
		txtBiografia.setVisible(mostrar);
		txtLink.setVisible(mostrar);
		comboInstituciones.setEnabled(mostrar);
		comboInstituciones.setVisible(mostrar);
		lblDescripcion.setVisible(mostrar);
		scrollBiografia.setVisible(mostrar);
		lblBiografia.setVisible(mostrar);
		lblLinkopcional.setVisible(mostrar);
		lblInstitucion.setVisible(mostrar);
		lblExclusivoParaProfesor.setVisible(mostrar);
		this.pack();
	}

	private void limpiarFormulario() {

		this.imagenCodificada = null;

		radioSocio.setSelected(false);
		radioProfesor.setSelected(false);

		txtNickname.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");

		dateChooserAU.setDate(null);

		txtDescripcion.setText("");
		txtBiografia.setText("");
		txtLink.setText("");

		txtContraConfirm.setText("");
		textContra.setText("");

	}

	/**
	 * Cierra la ventana del caso de uso. Limpia el formulario y esconde el
	 * JInternalFrame.
	 */
	private void cancelarAltaUsuario() {
		limpiarFormulario();
		hide();
	}

	public void arranque() {
		limpiarFormulario();
		show();
		this.pack();
		habilitarFormularioProfesor(false);
		;
	}

	/**
	 * Crea un FileChooser que permite la seleccion de archivo de imagen.
	 */
	private void elegirImagen() {

		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("jpeg", "jpeg", "png", "tiff", "gif");
		jfc.addChoosableFileFilter(filtro);

		// Si usuario selecciono algo, leer archivo y coso
		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

			File archivo = jfc.getSelectedFile();
			txtRutaImagen.setText(archivo.getAbsolutePath());

			try {
				byte[] bytes = Files.readAllBytes(archivo.toPath());

				// Recordar imagen codificada
				this.imagenCodificada = bytes;

				// Mostrar version reescalada imagen seleccionada al usuario
				// Se toma el file y se lo intenta tratar como imagen
				BufferedImage bfi = ImageIO.read(archivo);
				float aspectRatio = bfi.getWidth() / bfi.getHeight();
				Image imagenChiquita = bfi.getScaledInstance((int) (64 * aspectRatio), 64, Image.SCALE_DEFAULT);
				lblImagen.setIcon(new ImageIcon(imagenChiquita));
				lblImagen.setText(null);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				this.imagenCodificada = null;
				lblImagen.setIcon(null);
				lblImagen.setText("[Imagen seleccionada]");
			} catch (IOException e) {
				e.printStackTrace();
				this.imagenCodificada = null;
				lblImagen.setIcon(null);
				lblImagen.setText("[Imagen seleccionada]");
			}

		}
	}
}
