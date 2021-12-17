package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import logica.DtActividad;
import logica.IControladorInstituciones;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ConsultaActividad extends JInternalFrame {
	private JTextField textNombre;
	private JTextArea textDescripcion;
	private JTextField textDuracion;
	private JTextField txtCosto;
	private JComboBox<String> comboBoxInst;
	private JComboBox<String> comboBoxAct;
	private JComboBox<String> comboBoxClases;
	private JDateChooser dateAct;
	private IControladorInstituciones controlInst;
	private ConsultaClase frameClase;
	private JButton btnConsultarClas;
	private JComboBox<String> comboBoxCuponera;
	private JButton btnConsultarCup;
	private ConsultaCuponera frameCuponera;
	private JLabel lblCategorias;
	private JList<String> listaCategorias;
	private JScrollPane scrollPane;
	private JLabel lblEstado;
	private JTextField estadoTextField;
	private JLabel lblImagen;

	public void setConsultaCuponeraFrame(ConsultaCuponera f) {
		this.frameCuponera = f;
	}

	public ConsultaActividad(IControladorInstituciones ctrlInst, ConsultaClase frameCla, ConsultaCuponera frameCupo) {
		setResizable(true);
		frameCuponera = frameCupo;
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameClase = frameCla;
		controlInst = ctrlInst;
		setTitle("Consulta Actividad Deportiva");
		setBounds(100, 100, 570, 565);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 240, 71, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 35, 105, 35, 27, 29, 35, 50, 35, 35, 35, 35, 35, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblInstituciones = new JLabel("Instituciones");
		GridBagConstraints gbc_lblInstituciones = new GridBagConstraints();
		gbc_lblInstituciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInstituciones.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstituciones.gridx = 1;
		gbc_lblInstituciones.gridy = 1;
		getContentPane().add(lblInstituciones, gbc_lblInstituciones);

		comboBoxInst = new JComboBox<String>();
//		comboBoxInst.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				seleccionarInstitucion();
//			}
//		});
		GridBagConstraints gbc_comboBoxInst = new GridBagConstraints();
		gbc_comboBoxInst.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInst.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInst.gridx = 2;
		gbc_comboBoxInst.gridy = 1;
		getContentPane().add(comboBoxInst, gbc_comboBoxInst);

		lblImagen = new JLabel("[no hay imagen]");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 2;
		gbc_lblImagen.gridy = 2;
		getContentPane().add(lblImagen, gbc_lblImagen);

		JLabel lblActividades = new JLabel("Actividades");
		GridBagConstraints gbc_lblActividades = new GridBagConstraints();
		gbc_lblActividades.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActividades.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividades.gridx = 1;
		gbc_lblActividades.gridy = 3;
		getContentPane().add(lblActividades, gbc_lblActividades);

		comboBoxAct = new JComboBox<String>();
		comboBoxAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarActividad();
			}
		});
		GridBagConstraints gbc_comboBoxAct = new GridBagConstraints();
		gbc_comboBoxAct.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAct.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAct.gridx = 2;
		gbc_comboBoxAct.gridy = 3;
		getContentPane().add(comboBoxAct, gbc_comboBoxAct);

		lblEstado = new JLabel("Estado");
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 1;
		gbc_lblEstado.gridy = 4;
		getContentPane().add(lblEstado, gbc_lblEstado);

		estadoTextField = new JTextField();
		estadoTextField.setEditable(false);
		GridBagConstraints gbc_estadoTextField = new GridBagConstraints();
		gbc_estadoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_estadoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoTextField.gridx = 2;
		gbc_estadoTextField.gridy = 4;
		getContentPane().add(estadoTextField, gbc_estadoTextField);
		estadoTextField.setColumns(10);

		lblCategorias = new JLabel("Categorias");
		GridBagConstraints gbc_lblCategorias = new GridBagConstraints();
		gbc_lblCategorias.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategorias.gridx = 1;
		gbc_lblCategorias.gridy = 5;
		getContentPane().add(lblCategorias, gbc_lblCategorias);

		listaCategorias = new JList<String>();
		GridBagConstraints gbc_listaCategorias = new GridBagConstraints();
		gbc_listaCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_listaCategorias.fill = GridBagConstraints.HORIZONTAL;
		gbc_listaCategorias.gridx = 2;
		gbc_listaCategorias.gridy = 5;
		getContentPane().add(listaCategorias, gbc_listaCategorias);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 6;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.BOTH;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 6;
		getContentPane().add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.NORTH;
		gbc_lblDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 7;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 7;
		getContentPane().add(scrollPane, gbc_scrollPane);

		textDescripcion = new JTextArea();
		textDescripcion.setWrapStyleWord(true);
		textDescripcion.setLineWrap(true);
		scrollPane.setViewportView(textDescripcion);
		textDescripcion.setEditable(false);
		textDescripcion.setColumns(10);

		JLabel lblDuracion = new JLabel("Duracion");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 1;
		gbc_lblDuracion.gridy = 8;
		getContentPane().add(lblDuracion, gbc_lblDuracion);

		textDuracion = new JTextField();
		textDuracion.setEditable(false);
		GridBagConstraints gbc_textDuracion = new GridBagConstraints();
		gbc_textDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_textDuracion.fill = GridBagConstraints.BOTH;
		gbc_textDuracion.gridx = 2;
		gbc_textDuracion.gridy = 8;
		getContentPane().add(textDuracion, gbc_textDuracion);
		textDuracion.setColumns(10);

		JLabel lblCosto = new JLabel("Costo");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 1;
		gbc_lblCosto.gridy = 9;
		getContentPane().add(lblCosto, gbc_lblCosto);

		txtCosto = new JTextField();
		txtCosto.setEditable(false);
		GridBagConstraints gbc_txtCosto = new GridBagConstraints();
		gbc_txtCosto.insets = new Insets(0, 0, 5, 5);
		gbc_txtCosto.fill = GridBagConstraints.BOTH;
		gbc_txtCosto.gridx = 2;
		gbc_txtCosto.gridy = 9;
		getContentPane().add(txtCosto, gbc_txtCosto);
		txtCosto.setColumns(10);

		JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro");
		GridBagConstraints gbc_lblFechaDeRegistro = new GridBagConstraints();
		gbc_lblFechaDeRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFechaDeRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeRegistro.gridx = 1;
		gbc_lblFechaDeRegistro.gridy = 10;
		getContentPane().add(lblFechaDeRegistro, gbc_lblFechaDeRegistro);

		dateAct = new JDateChooser();

		JTextFieldDateEditor coso = (JTextFieldDateEditor) dateAct.getDateEditor();
		coso.setEditable(false);

		dateAct.getCalendarButton().setEnabled(false);
		GridBagConstraints gbc_dateAct = new GridBagConstraints();
		gbc_dateAct.insets = new Insets(0, 0, 5, 5);
		gbc_dateAct.fill = GridBagConstraints.BOTH;
		gbc_dateAct.gridx = 2;
		gbc_dateAct.gridy = 10;
		getContentPane().add(dateAct, gbc_dateAct);

		JLabel lblClases = new JLabel("Clases");
		GridBagConstraints gbc_lblClases = new GridBagConstraints();
		gbc_lblClases.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblClases.insets = new Insets(0, 0, 5, 5);
		gbc_lblClases.gridx = 1;
		gbc_lblClases.gridy = 11;
		getContentPane().add(lblClases, gbc_lblClases);

		comboBoxClases = new JComboBox<>();
		GridBagConstraints gbc_comboBoxClases = new GridBagConstraints();
		gbc_comboBoxClases.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClases.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxClases.gridx = 2;
		gbc_comboBoxClases.gridy = 11;
		getContentPane().add(comboBoxClases, gbc_comboBoxClases);

		btnConsultarClas = new JButton("Consultar");
		btnConsultarClas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarClase();
			}
		});
		GridBagConstraints gbc_btnConsultarClas = new GridBagConstraints();
		gbc_btnConsultarClas.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultarClas.gridx = 3;
		gbc_btnConsultarClas.gridy = 11;
		getContentPane().add(btnConsultarClas, gbc_btnConsultarClas);

		JLabel lblCuponeras = new JLabel("Cuponeras");
		GridBagConstraints gbc_lblCuponeras = new GridBagConstraints();
		gbc_lblCuponeras.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCuponeras.insets = new Insets(0, 0, 5, 5);
		gbc_lblCuponeras.gridx = 1;
		gbc_lblCuponeras.gridy = 12;
		getContentPane().add(lblCuponeras, gbc_lblCuponeras);

		comboBoxCuponera = new JComboBox<>();
		GridBagConstraints gbc_comboBoxCuponera = new GridBagConstraints();
		gbc_comboBoxCuponera.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCuponera.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCuponera.gridx = 2;
		gbc_comboBoxCuponera.gridy = 12;
		getContentPane().add(comboBoxCuponera, gbc_comboBoxCuponera);

		btnConsultarCup = new JButton("Consultar");
		btnConsultarCup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCuponeras();
			}
		});
		GridBagConstraints gbc_btnConsultarCup = new GridBagConstraints();
		gbc_btnConsultarCup.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultarCup.gridx = 3;
		gbc_btnConsultarCup.gridy = 12;
		getContentPane().add(btnConsultarCup, gbc_btnConsultarCup);

	}

	/**
	 * Carga todas las instituciones del sistema en el combobox. Si no las hay, no
	 * carga nada.
	 */
	private void cargarInstituciones() {
		
		// Primero cargamos un nuevo modelo para el JComboBox
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		Set<String> nombresInstituciones = controlInst.getInstituciones();
		for (String nomInst : nombresInstituciones)
			model.addElement(nomInst);
		
		// Le seteamos el nuevo modelo
		comboBoxInst.setModel(model);
		
		// Le seteamos indice -1 para que no haya opcion
		// seleccionada por defecto
		comboBoxInst.setSelectedIndex(-1);
		
		// Se le agrega el actionListener 
		comboBoxInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarInstitucion();
			}
		});
	}

	/**
	 * Si no hay institucion seleccionada no carga ningun string. Carga nombre de
	 * actividades al combobox solo si hay una institucion seleccionada.
	 */
	private void cargarActividades() {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(null);

		if (hayInstitucionSeleccionada()) { // Carga datos solo si hay una institucion seleccionada
			Set<String> acts = controlInst
					.getActividadesDeInstitucion(comboBoxInst.getModel().getSelectedItem().toString());
			for (String n : acts)
				model.addElement(n);
		}
		comboBoxAct.setModel(model);
//		comboBoxAct.setSelectedIndex(-1);
	}

	/**
	 * Precond: hay actividad seleccionada
	 */
	private void cargarClases() {

		comboBoxClases.setEnabled(true);
		String nomActividad = comboBoxAct.getModel().getSelectedItem().toString();

		// No precisa chequear que haya institucion seleccionada, solo actividad
		// porque no puede haber actividad sin institucion
		Set<String> clases = controlInst.getClasesDeActividad(nomActividad,
				comboBoxInst.getModel().getSelectedItem().toString());
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
		modelo.addElement(null);
		for (String n : clases)
			modelo.addElement(n);
		comboBoxClases.setModel(modelo);

	}

	/**
	 * Precond: hay institucion y actividad seleccionadas
	 */
	private void cargarDatosActividadAux() {
		cargarDatosActividad(comboBoxInst.getModel().getSelectedItem().toString(),
				comboBoxAct.getModel().getSelectedItem().toString());
	}

	/**
	 * @param dt datos de una actividad
	 * 
	 *           Recibe los datos de una actividad (contiene nombres de las
	 *           cuponeras) y carga los nombres de las cuponeras al combobox
	 */
	private void cargarCuponeras(DtActividad dt) {
		comboBoxCuponera.setEnabled(true);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(null);
		Set<String> cupo = dt.getCuponeras();
		for (String n : cupo)
			model.addElement(n);
		comboBoxCuponera.setModel(model);
	}

	/**
	 * Si hay cuponeras seleccionada en el combobox, llama al caso de uso Consulta
	 * de Cuponera con sus datos. Si no hay cuponera seleccionada, no pasa nada.
	 */
	private void mostrarCuponeras() {
		if (hayCuponeraSeleccionada()) {
			frameCuponera.arranqueAux(comboBoxCuponera.getModel().getSelectedItem().toString());
		} else {
			JOptionPane.showMessageDialog(this, "Cuponera invalida.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Carga los nombres de las categorias a la lista correspondiente. La gracia es
	 * invocar esta funcion ya teniendo un DtActividad con los nombres de cada
	 * categoria.
	 * 
	 * @param nombreCategorias nombres de las categorias a cargar
	 */
	private void cargarCategorias(Set<String> nombresCategorias) {

		DefaultListModel<String> modelo = new DefaultListModel<String>();
		modelo.addElement(null);
		for (String n : nombresCategorias)
			modelo.addElement(n);
		listaCategorias.setModel(modelo);
	}

	/**
	 * @param nombreInsti nombre de la institucion
	 * @param nombreActiv nombre de una actividad de la institucion
	 * 
	 *                    Rellena los campos del formulario con los datos de la
	 *                    actividad. El nombre de la institucion es proporcionado
	 *                    por mera optimizacion.
	 */
	private void cargarDatosActividad(String nombreInsti, String nombreActiv) {

		DtActividad acti = controlInst.getDtActividad(nombreInsti, nombreActiv);

		// Intentar cargar imagen, si es que la tiene
		if (acti.getImagen() != null && acti.getImagen().length > 0) {
			try {
				lblImagen.setText(null);
				lblImagen.setIcon(decodificarImagen(acti.getImagen()));
			} catch (IOException e) {
				e.printStackTrace();
				lblImagen.setIcon(null);
				lblImagen.setText("[No hay imagen que mostrar]");
			}
		} else {
			lblImagen.setIcon(null);
			lblImagen.setText("[No hay imagen que mostrar]");
		}

		// CARGAR ESTADO DE LA ACTIVIDAD (Aceptada, Rechazada, etc.)
		switch (acti.getEstado().ordinal()) {
		case 0: // Ingresada
			this.estadoTextField.setForeground(Color.ORANGE.darker());
			break;
		case 1: // Aceptada
			this.estadoTextField.setForeground(Color.GREEN.darker());
			break;
		case 2: // Rechazada
			this.estadoTextField.setForeground(Color.RED.darker());
			break;
		}
		this.estadoTextField.setText(acti.getEstado().toString());

		// CARGAR CATEGORIAS
		cargarCategorias(acti.getCategorias());

		textNombre.setText(acti.getNombre());
		textDescripcion.setText(acti.getDescripcion());
		textDuracion.setText(String.valueOf(acti.getDuracion()));
		txtCosto.setText(String.valueOf(acti.getCosto()));
		dateAct.setDate(acti.getFechaRegistro());

		cargarCuponeras(acti);

		if (comboBoxClases.getModel().getSize() > 0) // SI TIRA ERROR, COMPARAR > 1 (POR EL null AL INICIO)
			btnConsultarClas.setEnabled(true);

		if (comboBoxCuponera.getModel().getSize() > 0) // SI TIRA ERROR, COMPARAR > 1 (POR EL null AL INICIO)
			btnConsultarCup.setEnabled(true);
	}

	/**
	 * @return true si y solo si hay string no-vacio en el combobox de institucion
	 */
	private boolean hayInstitucionSeleccionada() {
		return comboBoxInst.getModel().getSelectedItem() != null
				&& !comboBoxInst.getModel().getSelectedItem().toString().isEmpty();
	}

	/**
	 * @return true si y solo si hay string no-vacio en el combobox de actividad
	 */
	private boolean hayActividadSeleccionada() {
		return comboBoxAct.getModel().getSelectedItem() != null
				&& !comboBoxAct.getModel().getSelectedItem().toString().isEmpty();
	}

	/**
	 * @return true si y solo si hay string no-vacio en el combobox de clase
	 */
	private boolean hayClaseSeleccionada() {
		return comboBoxClases.getModel().getSelectedItem() != null
				&& !comboBoxClases.getModel().getSelectedItem().toString().isEmpty();
	}

	/**
	 * @return true si y solo si hay string no-vacio en el combobox de cuponera
	 */
	private boolean hayCuponeraSeleccionada() {
		return comboBoxCuponera.getModel().getSelectedItem() != null
				&& !comboBoxCuponera.getModel().getSelectedItem().toString().isEmpty();
	}

	/**
	 * Si hay institucion, actividad y clase seleccionadas (y de manera cohesiva: la
	 * institucion ofrezca la actividad, y la clase pertenezca a la actividad)
	 * entonces se invoca el caso de uso Consultar Clase con los datos adecuados.
	 */
	private void mostrarClase() {

		if (hayInstitucionSeleccionada() && hayActividadSeleccionada() && hayClaseSeleccionada()) {

			String nomClase = comboBoxClases.getModel().getSelectedItem().toString();
			String nomAct = comboBoxAct.getModel().getSelectedItem().toString();
			String nomInst = comboBoxInst.getModel().getSelectedItem().toString();
			frameClase.arranqueCargado(nomClase, nomAct, nomInst);
		} else {
			JOptionPane.showMessageDialog(this, "Institucion, actividad o clase invalidos.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Resetea todos los comboboxes, listas, campos, todo del formulario y lo como
	 * nuevo.
	 */
	private void limpiarFormulario() {

		limpiarFormularioMenosInstituciones();

		limpiarComboInstituciones();

		comboBoxInst.setEnabled(true);
		comboBoxAct.setEnabled(true);
		comboBoxClases.setEnabled(true);
	}

	/**
	 * limpiarFormulario() pero no limpia ComboBoxInstituciones. Importante para que
	 * re-seleccion de ComboBox de instituciones.
	 */
	private void limpiarFormularioMenosInstituciones() {
		// Limpiar comboboxes y listas

		limpiarComboActividades();
		
		lblImagen.setIcon(null);
		lblImagen.setText("[no hay imagen]");
		limpiarComboCategorias();
		limpiarComboCuponeras();
		limpiarComboClases();

		comboBoxAct.setEnabled(true);
		comboBoxClases.setEnabled(false);
		comboBoxCuponera.setEnabled(false);

		// Limpiar campos
		estadoTextField.setText(null);
		textNombre.setText(null);
		textDescripcion.setText(null);
		textDuracion.setText(null);
		txtCosto.setText(null);
		dateAct.setDate(null);
		btnConsultarClas.setEnabled(false);
		btnConsultarCup.setEnabled(false);
	}

	/**
	 * Vacia el combobox de institucion
	 */
	private void limpiarComboInstituciones() {
		
		// Eliminar todos los actionListeners de ComboBoxInst
		// Esto es porque cargarInstituciones() le agrega un actionListener
		// cada vez que se le cargan los datos al JComboBox
		while (comboBoxInst.getActionListeners().length > 0) {
			comboBoxInst.removeActionListener(comboBoxInst.getActionListeners()[0]);
		}
		
		DefaultComboBoxModel<String> modeloVacio = new DefaultComboBoxModel<String>();
		modeloVacio.addElement(null);
		comboBoxInst.setModel(modeloVacio);
	}

	/**
	 * Vacia el combobox de actividades
	 */
	private void limpiarComboActividades() {
		DefaultComboBoxModel<String> modeloVacio = new DefaultComboBoxModel<String>();
		modeloVacio.addElement(null);
		comboBoxAct.setModel(modeloVacio);
	}

	/**
	 * Vacia la lista de categorias
	 */
	private void limpiarComboCategorias() {
		// Modelo vacio para JList
		DefaultListModel<String> modeloVacio = new DefaultListModel<String>();
		modeloVacio.addElement(null);
		listaCategorias.setModel(modeloVacio);
	}

	/**
	 * Vacia el combobox de clases
	 */
	private void limpiarComboClases() {
		DefaultComboBoxModel<String> modeloVacio = new DefaultComboBoxModel<String>();
		modeloVacio.addElement(null);
		comboBoxClases.setModel(modeloVacio);
	}

	/**
	 * Vacia el combobox de cuponeras
	 */
	private void limpiarComboCuponeras() {
		DefaultComboBoxModel<String> modeloVacio = new DefaultComboBoxModel<String>();
		modeloVacio.addElement(null);
		comboBoxCuponera.setModel(modeloVacio);
	}

	/**
	 * Metodo publico para inicializar la ventana con formulario vacio.
	 */
	public void arranque() {
		this.show();
		limpiarFormulario();
		cargarInstituciones();
	}

	/**
	 * @param nomActividad nombre de una actividad del sistema
	 * 
	 *                     <p>
	 *                     Metodo publico para ser inicializar la ventana y rellenar
	 *                     el formulario con los datos de la actividad
	 *                     proporcionada.
	 *                     </p>
	 * 
	 *                     <p>
	 *                     Se lo usa desde otro caso de uso para consultar cierta
	 *                     actividad pre-seleccionada.
	 *                     </p>
	 */
	public void arranqueConActividad(String nomActividad) {

		// esto es una chanchada NP-compleja pero al
		// menos no acopla Presentacion a Manejador
		arranque(); // inicializar JInternalFrame normalmente

		// buscar en ComboBox de instituciones
		// la institucion con la actividad 'nomActividad'
		boolean encontrado = false;
		String institucionIterador = null;
		int i;
		for (i = 0; !encontrado && i < comboBoxInst.getModel().getSize(); i++) {

			institucionIterador = comboBoxInst.getModel().getElementAt(i);
			if (institucionIterador != null && !institucionIterador.isEmpty()) {
				Set<String> actividadesInst = controlInst.getActividadesDeInstitucion(institucionIterador);
				encontrado = actividadesInst.contains(nomActividad);
			}
		}
		DefaultComboBoxModel<String> modeloConActividad = new DefaultComboBoxModel<String>();
		modeloConActividad.addElement(institucionIterador);
		comboBoxInst.setModel(modeloConActividad);
		comboBoxInst.setEnabled(false); // trancar ComboBoxInst
		seleccionarInstitucion();

		// buscar en ComboBox de actividades
		// la actividad llamada 'nomActividad'
		encontrado = false;
		String actividadIterador = null;
		for (i = 0; !encontrado && i < comboBoxAct.getModel().getSize(); i++) {

			actividadIterador = comboBoxAct.getModel().getElementAt(i);
			if (actividadIterador != null && !actividadIterador.isEmpty()) {
				actividadIterador = comboBoxAct.getModel().getElementAt(i);
				encontrado = nomActividad.equals(actividadIterador);
			}
		}
		DefaultComboBoxModel<String> modeloConClase = new DefaultComboBoxModel<String>();
		modeloConClase.addElement(actividadIterador);
		comboBoxAct.setModel(modeloConClase);
		comboBoxAct.setEnabled(false); // trancar ComboBoxActividad
		seleccionarActividad();
	}

	/**
	 * Codigo que se dispara cuando se selecciona una institucion en la ventana.
	 */
	private void seleccionarInstitucion() {
		// Si cambia la institucion, cambia todo el resto del form
		limpiarFormularioMenosInstituciones();

		cargarActividades();

	}

	/**
	 * Codigo que se dispara cuando se selecciona una actividad en la ventana.
	 */
	private void seleccionarActividad() {

		if (hayActividadSeleccionada()) {
			cargarClases();
			cargarDatosActividadAux();

			if (comboBoxClases.getModel().getSize() > 0) { // O COMPRAR > 1 (ITEM null)
				btnConsultarClas.setEnabled(true);
			}
		}
	}

	/**
	 * @param imagenCodificada imagen codificada en Base64
	 * @return imagen decodificada, lista para verla con un JLabel
	 * @throws IOException
	 * 
	 *                     Decodifica la imagen y un ImageIcon con el grafico.
	 */
	private ImageIcon decodificarImagen(byte[] imagenCodificada) throws IOException {

//		byte[] bytes = Base64.getDecoder().decode(imagenCodificada);
//		String imagenEnString = Base64.getEncoder().encodeToString(imagenCodificada);
		BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(imagenCodificada));

		// Achicamos imagen
		if (imagen == null)
			throw new IOException("Posible mala codificacion o corrupcion de imagen.");
		float aspectRatio = (float) imagen.getWidth() / imagen.getHeight();
		Image versionChica = imagen.getScaledInstance((int) (64 * aspectRatio), 64, BufferedImage.SCALE_SMOOTH);

		return new ImageIcon(versionChica);
	}
}
