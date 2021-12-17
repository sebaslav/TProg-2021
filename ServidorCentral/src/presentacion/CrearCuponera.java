package presentacion;

import logica.*;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import excepciones.CuponeraRepetidaException;

import java.util.Base64;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;
import javax.swing.JFrame;

import java.awt.TextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CrearCuponera extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
	private IControladorCuponeras controlCup;
	private JTextField txtNombre;
	private JTextArea txtDescripcion;
	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFin;
	private JTextField txtDescuento;
	private JDateChooser dateChooserFechaAlta;
	private TextField txtRutaImagen;
	private JLabel lblImagenSeleccionada;

	private JButton botonAceptar;
	private JButton botonCancelar;
	private JButton btnFileChooser;
	private byte[] imagenCodificada;
	private JButton btnCancelar;

	public CrearCuponera(IControladorCuponeras ctrl) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		controlCup = ctrl;
		setIconifiable(true);
		setClosable(true);
		setTitle("Crear Cuponera");
		getContentPane().setEnabled(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 37, 104, 0, 179, 110, 46, 3 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 84, 0, 4, 30, 33, 0, 32, 33, 30, 80, 31, 0, 0, 8 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0,
				0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("Ingresar nombre");
		txtNombre.setColumns(10);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		getContentPane().add(txtNombre, gbc_txtNombre);

		JLabel lblBiografiaopcional = new JLabel("Descripcion");
		GridBagConstraints gbc_lblBiografiaopcional = new GridBagConstraints();
		gbc_lblBiografiaopcional.anchor = GridBagConstraints.EAST;
		gbc_lblBiografiaopcional.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiografiaopcional.gridx = 1;
		gbc_lblBiografiaopcional.gridy = 2;
		getContentPane().add(lblBiografiaopcional, gbc_lblBiografiaopcional);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);

		txtDescripcion = new JTextArea();
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setLineWrap(true);
		scrollPane.setViewportView(txtDescripcion);

		JLabel lblPerodoDeValidez = new JLabel("Período de Validez");
		GridBagConstraints gbc_lblPerodoDeValidez = new GridBagConstraints();
		gbc_lblPerodoDeValidez.anchor = GridBagConstraints.SOUTH;
		gbc_lblPerodoDeValidez.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerodoDeValidez.gridx = 3;
		gbc_lblPerodoDeValidez.gridy = 4;
		getContentPane().add(lblPerodoDeValidez, gbc_lblPerodoDeValidez);

		JLabel lblApellido = new JLabel("Inicio");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 1;
		gbc_lblApellido.gridy = 5;
		getContentPane().add(lblApellido, gbc_lblApellido);

		dateChooserInicio = new JDateChooser();
		GridBagConstraints gbc_dateChooserInicio = new GridBagConstraints();
		gbc_dateChooserInicio.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserInicio.gridx = 3;
		gbc_dateChooserInicio.gridy = 5;
		getContentPane().add(dateChooserInicio, gbc_dateChooserInicio);

		JLabel lblEmail_1 = new JLabel("Fin");
		GridBagConstraints gbc_lblEmail_1 = new GridBagConstraints();
		gbc_lblEmail_1.anchor = GridBagConstraints.EAST;
		gbc_lblEmail_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_1.gridx = 1;
		gbc_lblEmail_1.gridy = 6;
		getContentPane().add(lblEmail_1, gbc_lblEmail_1);

		dateChooserFin = new JDateChooser();
		GridBagConstraints gbc_dateChooserFin = new GridBagConstraints();
		gbc_dateChooserFin.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserFin.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserFin.gridx = 3;
		gbc_dateChooserFin.gridy = 6;
		getContentPane().add(dateChooserFin, gbc_dateChooserFin);

		JLabel lblDescuento = new JLabel("Descuento");
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.anchor = GridBagConstraints.EAST;
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 1;
		gbc_lblDescuento.gridy = 8;
		getContentPane().add(lblDescuento, gbc_lblDescuento);

		txtDescuento = new JTextField();
		txtDescuento.setColumns(10);
		txtDescuento.setToolTipText("Ingresar un número entro 0 y 100");
		GridBagConstraints gbc_txtDescuento = new GridBagConstraints();
		gbc_txtDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescuento.gridx = 3;
		gbc_txtDescuento.gridy = 8;
		getContentPane().add(txtDescuento, gbc_txtDescuento);

		JLabel label = new JLabel("%");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 8;
		getContentPane().add(label, gbc_label);

		JLabel lblFechaAlta = new JLabel("(opcional) Fecha alta");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 9;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		dateChooserFechaAlta = new JDateChooser();
		GridBagConstraints gbc_dateChooserFechaAlta = new GridBagConstraints();
		gbc_dateChooserFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserFechaAlta.gridx = 3;
		gbc_dateChooserFechaAlta.gridy = 9;
		getContentPane().add(dateChooserFechaAlta, gbc_dateChooserFechaAlta);

		lblImagenSeleccionada = new JLabel("[Imagen seleccionada]");
		lblImagenSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblImagenSeleccionada = new GridBagConstraints();
		gbc_lblImagenSeleccionada.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblImagenSeleccionada.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagenSeleccionada.gridx = 3;
		gbc_lblImagenSeleccionada.gridy = 11;
		getContentPane().add(lblImagenSeleccionada, gbc_lblImagenSeleccionada);

		JLabel lblImagen = new JLabel("(opcional) Imagen");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.anchor = GridBagConstraints.EAST;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 1;
		gbc_lblImagen.gridy = 12;
		getContentPane().add(lblImagen, gbc_lblImagen);

		txtRutaImagen = new TextField();
		txtRutaImagen.setEditable(false);
		txtRutaImagen.setText("No se selecciono imagen");
		GridBagConstraints gbc_txtRutaImagen = new GridBagConstraints();
		gbc_txtRutaImagen.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRutaImagen.insets = new Insets(0, 0, 5, 5);
		gbc_txtRutaImagen.gridx = 3;
		gbc_txtRutaImagen.gridy = 12;
		getContentPane().add(txtRutaImagen, gbc_txtRutaImagen);

		btnFileChooser = new JButton("Elegir");
		btnFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				elegirImagen();
			}
		});
		GridBagConstraints gbc_btnFileChooser = new GridBagConstraints();
		gbc_btnFileChooser.anchor = GridBagConstraints.WEST;
		gbc_btnFileChooser.insets = new Insets(0, 0, 5, 5);
		gbc_btnFileChooser.gridx = 4;
		gbc_btnFileChooser.gridy = 12;
		getContentPane().add(btnFileChooser, gbc_btnFileChooser);

		JPanel panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 0, 5);
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 3;
		gbc_panelBotones.gridy = 14;
		getContentPane().add(panelBotones, gbc_panelBotones);
		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCuponera();
			}
		});
		panelBotones.add(botonAceptar);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarCrearCuponera();
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarCrearCuponera();
			}
		});
		panelBotones.add(btnCancelar);
		panelBotones.add(botonCancelar);
		setBounds(100, 100, 570, 605);

	}

	/**
	 * Crea cuponera con los datos del formulario.
	 */
	private void crearCuponera() {
		// Primero checkeo el formulario
		if (checkFormulario()) {
			String nombre;
			String descripcion;
			Date inicio;
			Date fin;
			float descuento;
			Date fechaRegistro;
			try {
				nombre = txtNombre.getText();
				inicio = dateChooserInicio.getDate();
				fin = dateChooserFin.getDate();
				descuento = 1 - Float.parseFloat(txtDescuento.getText()) / 100;
				descripcion = txtDescripcion.getText();
				if (dateChooserFechaAlta.getDate() == null) {
					fechaRegistro = new Date(); // hora actual del sistema
				} else {
					fechaRegistro = dateChooserFechaAlta.getDate();
				}
				
				controlCup.registrarCuponera(nombre, descripcion, inicio, fin, descuento, fechaRegistro, this.imagenCodificada);
				JOptionPane.showMessageDialog(this, "Cuponera registrada exitosamente", "Crear Cuponera",
						JOptionPane.INFORMATION_MESSAGE);
				hide();
				limpiarFormulario();
			} catch (CuponeraRepetidaException e) {
				JOptionPane.showMessageDialog(this, "Error ya existe una Cuponera con ese nombre en el sistema",
						"Crear Cuponera", JOptionPane.ERROR_MESSAGE);
			}
		}
		//limpiarFormulario();
	}

	public void arranque() {
		limpiarFormulario();
	}
	

	private boolean checkFormulario() {
		boolean esCorrecto = txtNombre.getText() != "";
		esCorrecto = esCorrecto && dateChooserInicio.getDate() != null;
		esCorrecto = esCorrecto && dateChooserFin.getDate() != null;
		try {
			esCorrecto = esCorrecto && Float.parseFloat(txtDescuento.getText()) >= 0;
			esCorrecto = esCorrecto && Float.parseFloat(txtDescuento.getText()) <= 100;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error: El descuento debe ser un número entre 0 y 100",
					"Crear Cuponera", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		esCorrecto = esCorrecto && txtDescripcion.getText() != "";
//		esCorrecto = esCorrecto && dateChooserFechaAlta.getDate() != null;
		if (!esCorrecto) {
			JOptionPane.showMessageDialog(this, "Error: se detectó un campo inválido", "Crear Cuponera",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (dateChooserInicio.getDate().after(dateChooserFin.getDate())) {
			JOptionPane.showMessageDialog(this, "Error: la fecha de inicio debe ser anterior a la fecha de fin",
					"Crear Cuponera", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return esCorrecto;
	}

	private void limpiarFormulario() {
		this.imagenCodificada = null;
		txtNombre.setText("");
		txtDescripcion.setText("");
		dateChooserInicio.setDate(null);
		dateChooserFin.setDate(null);
		txtDescuento.setText("");
		txtRutaImagen.setText("");
		dateChooserFechaAlta.setDate(null);
		lblImagenSeleccionada.setIcon(null);
		lblImagenSeleccionada.setText("[Imagen seleccionada]");
	}

	/**
	 * Cierra la ventana del caso de uso.
	 * Limpia el formulario y esconde el JInternalFrame.
	 */
	private void cancelarCrearCuponera() {
		limpiarFormulario();
		hide();
	}

	/**
	 * Crea un FileChooser que permite la seleccion de archivo
	 * de imagen.
	 */
	private void elegirImagen() {

		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("jpeg", "jpg", "png", "tiff", "gif");
		jfc.addChoosableFileFilter(filtro);
		
		// Si usuario selecciono algo, leer archivo y coso
		if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) {

			File archivo = jfc.getSelectedFile();
			txtRutaImagen.setText(archivo.getAbsolutePath());

//			String archivoCodificado = null;
			try {
				/*
				FileInputStream archivoISReader = new FileInputStream(archivo);
				byte[] bytes = new byte[(int) archivo.length()];
				archivoISReader.read(bytes);
				archivoCodificado = Base64.getEncoder().encodeToString(bytes);
				archivoISReader.close();
				*/
				byte[] bytes = Files.readAllBytes(archivo.toPath());
				// Recordar imagen codificada
				this.imagenCodificada = bytes;

				// Mostrar version reescalada imagen seleccionada al usuario
				// Se toma el file y se lo intenta tratar como imagen
				BufferedImage bfi = ImageIO.read(archivo);
				float aspectRatio = bfi.getWidth() / bfi.getHeight();
				Image imagenChiquita = bfi.getScaledInstance( (int) (64*aspectRatio), 64, Image.SCALE_DEFAULT);
				lblImagenSeleccionada.setIcon(new ImageIcon(imagenChiquita));
				lblImagenSeleccionada.setText(null);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				this.imagenCodificada = null;
				lblImagenSeleccionada.setIcon(null);
				lblImagenSeleccionada.setText("[Imagen seleccionada]");
			} catch (IOException e) {
				e.printStackTrace();
				this.imagenCodificada = null;
				lblImagenSeleccionada.setIcon(null);
				lblImagenSeleccionada.setText("[Imagen seleccionada]");
			}


		}

	}
}
