package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import logica.DtClase;
import logica.IControladorInstituciones;

import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ConsultaClase extends JInternalFrame {

	private final int EVERYTHING = 0;
	private final int ACTS_EN_ADELANTE = 1;
	private final int CLASES_EN_ADELANTE = 2;
	private final int CAMPOS_ONLY = 3;

	private JTextField socMinTextField;
	private JTextField socMaxTextField;
	private JTextField urlTextField;
	private JComboBox<String> comboBoxInst;
	private JComboBox<String> comboBoxAct;
	private JComboBox<String> comboBoxClases;
	private JDateChooser fechaDictadoDateChooser;
	private JDateChooser fechaRegDateChooser;
	private IControladorInstituciones controlInst;
	private JTextField horaTextField;
	private JLabel lblInstituto;
	private JLabel lblActividad;
	private JLabel lblClase;

	private JButton btnCerrar;

	public ConsultaClase(IControladorInstituciones ici) {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		controlInst = ici;

		setTitle("Consulta de Clase");
		setBounds(100, 100, 595, 498);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 0, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 41, 43, 39, 20, 35, 34, 43, 33, 32, 36, 0, 0, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		comboBoxInst = new JComboBox<String>();
		comboBoxInst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarInstitucion();
			}
		});

		lblInstituto = new JLabel("Instituto");
		GridBagConstraints gbc_lblInstituto = new GridBagConstraints();
		gbc_lblInstituto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInstituto.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstituto.gridx = 1;
		gbc_lblInstituto.gridy = 1;
		getContentPane().add(lblInstituto, gbc_lblInstituto);
		GridBagConstraints gbc_comboBoxInst = new GridBagConstraints();
		gbc_comboBoxInst.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInst.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInst.gridx = 2;
		gbc_comboBoxInst.gridy = 1;
		getContentPane().add(comboBoxInst, gbc_comboBoxInst);

		comboBoxAct = new JComboBox<String>();
		comboBoxAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarActividad();
			}
		});

		lblActividad = new JLabel("Actividad");
		GridBagConstraints gbc_lblActividad = new GridBagConstraints();
		gbc_lblActividad.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActividad.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividad.gridx = 1;
		gbc_lblActividad.gridy = 2;
		getContentPane().add(lblActividad, gbc_lblActividad);
		GridBagConstraints gbc_comboBoxAct = new GridBagConstraints();
		gbc_comboBoxAct.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAct.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAct.gridx = 2;
		gbc_comboBoxAct.gridy = 2;
		getContentPane().add(comboBoxAct, gbc_comboBoxAct);

		comboBoxClases = new JComboBox<>();
		comboBoxClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarClase();
			}
		});

		lblClase = new JLabel("Clase");
		GridBagConstraints gbc_lblClase = new GridBagConstraints();
		gbc_lblClase.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblClase.insets = new Insets(0, 0, 5, 5);
		gbc_lblClase.gridx = 1;
		gbc_lblClase.gridy = 3;
		getContentPane().add(lblClase, gbc_lblClase);
		GridBagConstraints gbc_comboBoxClases = new GridBagConstraints();
		gbc_comboBoxClases.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClases.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxClases.gridx = 2;
		gbc_comboBoxClases.gridy = 3;
		getContentPane().add(comboBoxClases, gbc_comboBoxClases);

		JLabel lblNewLabel_3 = new JLabel("Fecha de dictado");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		fechaDictadoDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaDictadoDateChooser = new GridBagConstraints();
		gbc_fechaDictadoDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDictadoDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaDictadoDateChooser.gridx = 2;
		gbc_fechaDictadoDateChooser.gridy = 5;
		getContentPane().add(fechaDictadoDateChooser, gbc_fechaDictadoDateChooser);

		fechaDictadoDateChooser.getCalendarButton().setEnabled(false);
		JTextFieldDateEditor editorFechaDictado = (JTextFieldDateEditor) fechaDictadoDateChooser.getDateEditor();
		editorFechaDictado.setEditable(false);

		JLabel lblHora = new JLabel("Hora");
		GridBagConstraints gbc_lblHora = new GridBagConstraints();
		gbc_lblHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHora.insets = new Insets(0, 0, 5, 5);
		gbc_lblHora.gridx = 1;
		gbc_lblHora.gridy = 6;
		getContentPane().add(lblHora, gbc_lblHora);

		horaTextField = new JTextField();
		horaTextField.setEditable(false);
		GridBagConstraints gbc_horaTextField = new GridBagConstraints();
		gbc_horaTextField.insets = new Insets(0, 0, 5, 5);
		gbc_horaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_horaTextField.gridx = 2;
		gbc_horaTextField.gridy = 6;
		getContentPane().add(horaTextField, gbc_horaTextField);
		horaTextField.setColumns(10);

		JLabel lblSociosMinimos = new JLabel("Socios minimos");
		GridBagConstraints gbc_lblSociosMinimos = new GridBagConstraints();
		gbc_lblSociosMinimos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSociosMinimos.insets = new Insets(0, 0, 5, 5);
		gbc_lblSociosMinimos.gridx = 1;
		gbc_lblSociosMinimos.gridy = 7;
		getContentPane().add(lblSociosMinimos, gbc_lblSociosMinimos);

		socMinTextField = new JTextField();
		socMinTextField.setEditable(false);
		GridBagConstraints gbc_socMinTextField = new GridBagConstraints();
		gbc_socMinTextField.insets = new Insets(0, 0, 5, 5);
		gbc_socMinTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_socMinTextField.gridx = 2;
		gbc_socMinTextField.gridy = 7;
		getContentPane().add(socMinTextField, gbc_socMinTextField);
		socMinTextField.setColumns(10);

		JLabel lblSociosMaximos = new JLabel("Socios maximos");
		GridBagConstraints gbc_lblSociosMaximos = new GridBagConstraints();
		gbc_lblSociosMaximos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSociosMaximos.insets = new Insets(0, 0, 5, 5);
		gbc_lblSociosMaximos.gridx = 1;
		gbc_lblSociosMaximos.gridy = 8;
		getContentPane().add(lblSociosMaximos, gbc_lblSociosMaximos);

		socMaxTextField = new JTextField();
		socMaxTextField.setEditable(false);
		socMaxTextField.setColumns(10);
		GridBagConstraints gbc_socMaxTextField = new GridBagConstraints();
		gbc_socMaxTextField.insets = new Insets(0, 0, 5, 5);
		gbc_socMaxTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_socMaxTextField.gridx = 2;
		gbc_socMaxTextField.gridy = 8;
		getContentPane().add(socMaxTextField, gbc_socMaxTextField);

		JLabel lblFechaDeRegistro = new JLabel("Fecha de registro");
		GridBagConstraints gbc_lblFechaDeRegistro = new GridBagConstraints();
		gbc_lblFechaDeRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFechaDeRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeRegistro.gridx = 1;
		gbc_lblFechaDeRegistro.gridy = 9;
		getContentPane().add(lblFechaDeRegistro, gbc_lblFechaDeRegistro);

		fechaRegDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaRegDateChooser = new GridBagConstraints();
		gbc_fechaRegDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaRegDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaRegDateChooser.gridx = 2;
		gbc_fechaRegDateChooser.gridy = 9;
		getContentPane().add(fechaRegDateChooser, gbc_fechaRegDateChooser);

		fechaRegDateChooser.getCalendarButton().setEnabled(false);
		JTextFieldDateEditor editorFechaReg = (JTextFieldDateEditor) fechaRegDateChooser.getDateEditor();
		editorFechaReg.setEditable(false);

		JLabel lblUrl = new JLabel("URL");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 1;
		gbc_lblUrl.gridy = 10;
		getContentPane().add(lblUrl, gbc_lblUrl);

		urlTextField = new JTextField();
		urlTextField.setEditable(false);
		GridBagConstraints gbc_urlTextField = new GridBagConstraints();
		gbc_urlTextField.insets = new Insets(0, 0, 5, 5);
		gbc_urlTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlTextField.gridx = 2;
		gbc_urlTextField.gridy = 10;
		getContentPane().add(urlTextField, gbc_urlTextField);
		urlTextField.setColumns(10);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCerrar.gridx = 2;
		gbc_btnCerrar.gridy = 12;
		getContentPane().add(btnCerrar, gbc_btnCerrar);

	}

	/**
	 * Inicializacion estandar, con comboboxes y campos vacios.
	 */
	public void arranque() {
		this.show();

		// Los habilitamos en caso de que hayan sido
		// deshabilitados al acceder desde arranqueCargado()
		// antes.
		comboBoxInst.setEnabled(true);
		comboBoxAct.setEnabled(true);
		comboBoxClases.setEnabled(true);

		limpiarFormulario();
		cargarInstituciones();
	}
	
	/**
	 * Inicializacion con los campos rellenados de los datos de una clase.
	 * 
	 * @param dt datos de la clase a consultar
	 * 
	 * <p>Metodo publico usado desde otro caso de uso, para la
	 * consulta de una clase en particular.</p>
	 * 
	 */
	public void arranqueCargado(DtClase dt) {
		this.show();
		limpiarCascada(this.EVERYTHING);
		cargarDatosClase(dt);

		// Se le vacian y trancan los Combobox para evitar conflictos
		DefaultComboBoxModel<String> minst = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> mact = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> mclas = new DefaultComboBoxModel<String>();

		comboBoxInst.setModel(minst);
		comboBoxAct.setModel(mact);
		comboBoxClases.setModel(mclas);

		comboBoxInst.setEnabled(false);
		comboBoxAct.setEnabled(false);
		comboBoxClases.setEnabled(false);

		// El focus pasa al boton de cerrar (estetica)
		btnCerrar.requestFocus();
	}

	/**
	 * Inicializacion con los campos rellenados de los datos de una clase.
	 * 
	 * @param nomClase     nombre de la clase de interes
	 * @param nomActividad nombre de la actividad que ofrece la clase
	 * @param nomInstituto nombre del instituto que ofrece la actividad
	 * 
	 * <p>Metodo publico usado desde otro caso de uso, para la
	 * consulta de una clase en particular.</p>
	 */
	public void arranqueCargado(String nomClase, String nomActividad, String nomInstituto) {
		this.show();
		limpiarCascada(this.EVERYTHING);
		cargarDatosClase(nomClase, nomActividad, nomInstituto);

		// Se le trancan los Combobox para evitar conflictos

		DefaultComboBoxModel<String> minst = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> mact = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> mclas = new DefaultComboBoxModel<String>();

		minst.addElement(nomInstituto);
		mact.addElement(nomActividad);
		mclas.addElement(nomClase);

		comboBoxInst.setModel(minst);
		comboBoxAct.setModel(mact);
		comboBoxClases.setModel(mclas);

		comboBoxInst.setEnabled(false);
		comboBoxAct.setEnabled(false);
		comboBoxClases.setEnabled(false);

		// El focus pasa al boton de cerrar (estetica)
		btnCerrar.requestFocus();
	}

	/**
	 * Vacia el combobox de Instituciones y le carga los nombres de todas las
	 * Instituciones en el sistema. En caso de no haberlas, deja el combobox vacio.
	 */
	private void cargarInstituciones() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(null);

		Set<String> inst = controlInst.getInstituciones();
		for (String n : inst)
			model.addElement(n);
		comboBoxInst.setModel(model);
	}

	/**
	 * Vacia el combobox de Actividades y le carga los los nombres de todas las
	 * Actividades asociadas a la Institucion actualmente seleccionada. En caso de
	 * no haberlas, deja el combobox vacio.
	 * 
	 * Precond: hay una institucion seleccionada
	 */
	private void cargarActividades() {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(null);

		Set<String> inst = controlInst
				.getActividadesAceptadasDeInstitucion(comboBoxInst.getModel().getSelectedItem().toString());
		for (String n : inst)
			model.addElement(n);
		comboBoxAct.setModel(model);
	}

	/**
	 * Vacia el combobox de Clases y le carga los los nombres de todas las clases de
	 * la Actividad actualmente seleccionada. En caso de no haberlas, deja el
	 * combobox vacio.
	 * 
	 * Precond: hay una institucion seleccionada Precond: hay una actividad
	 * seleccionada Precond: la actividad esta asociada a la institucion
	 */
	private void cargarClases() {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(null);

		Set<String> inst = controlInst.getClasesDeActividad(comboBoxAct.getModel().getSelectedItem().toString(),
				comboBoxInst.getModel().getSelectedItem().toString());
		for (String n : inst)
			model.addElement(n);
		comboBoxClases.setModel(model);
	}

	/**
	 * @param clase       nombre de la clase seleccionada
	 * @param actividad   nombre de la actividad seleccionada
	 * @param institucion nombre de la institucion seleccionada
	 * 
	 *                    Precond: La institucion ofrece la actividad Precond: La
	 *                    actividad ofrece la clase
	 */
	private void cargarDatosClase(String clase, String actividad, String institucion) {
		DtClase dt = controlInst.getDatosDeClase(clase, actividad, institucion);
		cargarDatosClase(dt);
	}

	/**
	 * @param dt datos de una clase
	 * 
	 * <p>Carga los datos de una clase en los campos del formulario.</p>
	 */
	private void cargarDatosClase(DtClase dt) { // CONSULTA DE USUARIO LO USA. ARREGLAR ESTO

		socMinTextField.setText(String.valueOf(dt.getMinSocios()));
		socMaxTextField.setText(String.valueOf(dt.getMaxSocios()));
		urlTextField.setText(dt.getUrl());
		fechaDictadoDateChooser.setDate(dt.getFechaHora());
		fechaRegDateChooser.setDate(dt.getFechaDeRegistro());
		String hora = String.valueOf(dt.getFechaHora().getHours());
		String minutos = String.valueOf(dt.getFechaHora().getMinutes());
		if (dt.getFechaHora().getMinutes() == 0)
			minutos = minutos + "0";
		horaTextField.setText(hora + ":" + minutos);
	}

	/**
	 * Vacia todos los combobox y todos los campos del formulario.
	 * 
	 * Arrasa con todo sin piedad alguna.
	 */
	private void limpiarFormulario() { // CONSULTA USUARIO LO USA. ARREGLAR ESTO

		comboBoxInst.setEnabled(true);
		comboBoxAct.setEnabled(true);
		comboBoxClases.setEnabled(true);
		comboBoxInst.setEnabled(true);

		limpiarCascada(this.EVERYTHING);
	}

	/**
	 * @param variacion
	 *                  <ol>
	 *                  <li>0 limpia todo</li>
	 *                  <li>1 limpia Actividades en adelante</li>
	 *                  <li>2 limpia Clases en adelante</li>
	 *                  <li>3 limpia campos nomas</li>
	 *                  </ol>
	 */
	private void limpiarCascada(int variacion) {
		if (variacion <= 0)
			limpiarComboInstituciones();
		if (variacion <= 1)
			limpiarComboActividades();
		if (variacion <= 2)
			limpiarComboClases();
		if (variacion <= 3)
			vaciarCampos();
	}

	/**
	 * Vacia los campos de formulario, donde van el nombre y los datos de la clase
	 * consultada.
	 */
	private void vaciarCampos() {

		socMinTextField.setText(null);
		socMaxTextField.setText(null);
		urlTextField.setText(null);

		fechaDictadoDateChooser.setDate(null);
		fechaRegDateChooser.setDate(null);
		horaTextField.setText(null);
	}

	/**
	 * Vacia el combobox de institucion
	 */
	private void limpiarComboInstituciones() {
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
	 * Vacia el combobox de clases
	 */
	private void limpiarComboClases() {
		DefaultComboBoxModel<String> modeloVacio = new DefaultComboBoxModel<String>();
		modeloVacio.addElement(null);
		comboBoxClases.setModel(modeloVacio);
	}

	/**
	 * Codigo que se dispara cuando se selecciona una institucion en el combobox.
	 */
	private void seleccionarInstitucion() {

		limpiarCascada(this.ACTS_EN_ADELANTE);
		if (hayInstitucionSeleccionada()) {
			cargarActividades();
		}
	}

	/**
	 * Codigo que se dispara cuando se selecciona una actividad en el combobox.
	 */
	private void seleccionarActividad() {

		limpiarCascada(this.CLASES_EN_ADELANTE);
		if (hayActividadSeleccionada()) {
			cargarClases();
		}
	}

	/**
	 * Codigo que se dispara cuando se selecciona una clase en el combobox.
	 */
	private void seleccionarClase() {
		limpiarCascada(CAMPOS_ONLY);
		if (hayInstitucionSeleccionada() && hayActividadSeleccionada() && hayClaseSeleccionada()) {
			cargarDatosClase(comboBoxClases.getModel().getSelectedItem().toString(),
					comboBoxAct.getModel().getSelectedItem().toString(),
					comboBoxInst.getModel().getSelectedItem().toString());
		}
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

}
