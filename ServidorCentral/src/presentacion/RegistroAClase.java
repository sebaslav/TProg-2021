package presentacion;



import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import excepciones.ClaseLlenaException;
import excepciones.SocioYaRegistradoException;
import logica.IControladorUsuarios;
import logica.DtClase;
import logica.IControladorCuponeras;
import logica.IControladorInstituciones;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class RegistroAClase extends JInternalFrame {
	private JComboBox<String> comboBoxInstituciones;
	private JComboBox<String> comboBoxActividades;
	private JComboBox<String> comboBoxSocios;
	private JComboBox<String> comboBoxClases;
	private JComboBox<String> comboBoxCuponeras;

	private JLabel lblFechitaClase;
	private JLabel lblFechaClaseNO;

	private JDateChooser fechaRegistroDateChooser;

	private JButton btnBotonAceptar;

	private IControladorInstituciones ICI;
	private IControladorUsuarios ICU;
	private IControladorCuponeras ICC; // sacar cuando haya Compra de Cuponera

	private String instSeleccionada;
	private String actSeleccionada;
	private String socioSeleccionado;
	private String claseSeleccionada;
	private String cuponeraSeleccionada;

	private boolean socioEscogido;
	private boolean claseEscogida;

	
	private void cmdSeleccionarInstitucion(ActionEvent e) {

		comboBoxInstituciones.setEnabled(false); // SI quiere corregir que cancele y a llorar al cuartito

		comboBoxActividades.setEnabled(true);

		this.instSeleccionada = comboBoxInstituciones.getSelectedItem().toString();
		cargarActividades();

	}

	private void cmdSeleccionarActividad(ActionEvent e) {

		comboBoxActividades.setEnabled(false); // SI quiere corregir que cancele y a llorar al cuartito
		comboBoxActividades.setEditable(false);

		comboBoxSocios.setEnabled(true);
		comboBoxClases.setEnabled(true);

		// Cargo los socios
		cargarSocios();

		// Cargo las clases
		cargarClases();
	}

	private void cmdSeleccionarSocio(ActionEvent e) {
		socioEscogido = comboBoxSocios.getSelectedIndex() != -1;
		if (socioEscogido) {
			comboBoxSocios.setEnabled(false); 
			this.socioSeleccionado = comboBoxSocios.getSelectedItem().toString();
			permisoSeleccionarCuponeraFecha();
		}
	}

	
	private void cmdSeleccionarClase(ActionEvent e) {
		this.claseEscogida = comboBoxClases.getSelectedIndex() != -1;
		if (claseEscogida) {
			comboBoxClases.setEnabled(false); 
			this.claseSeleccionada = comboBoxClases.getSelectedItem().toString();

			// Fecha de clase
			lblFechaClaseNO.setVisible(true);
			lblFechitaClase.setVisible(true);
			DtClase dtc = ICI.getDatosDeClase(claseSeleccionada);
			Date fechitaClase = dtc.getFechaHora();
			Date fechitaRegistro = dtc.getFechaDeRegistro();
			java.text.DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
			this.lblFechitaClase.setText(df.format(fechitaClase));
			fechaRegistroDateChooser.setDate(fechitaRegistro);
			fechaRegistroDateChooser.setMinSelectableDate(fechitaRegistro);
			fechaRegistroDateChooser.setMaxSelectableDate(fechitaClase);
			
			permisoSeleccionarCuponeraFecha();
		}
	}

	private void permisoSeleccionarCuponeraFecha() {
		/*
		 * VERSION CON CUPONERAS
		 */
		if (this.socioEscogido && this.claseEscogida) {
			comboBoxCuponeras.setEnabled(true);

			// Cargo las cuponeras
			cargarCuponeras();
		}

		/*
		 * VERSION SIN CUPONERAS
		 */

		btnBotonAceptar.setEnabled(this.socioEscogido && this.claseEscogida);
	}

	private void cargarCuponeras() {
		/*
		// Se crea un nuevo modelo updateado de cuponeras
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

		/*
		 * NO BORRAR!!!
		 * GUARDAR PARA CUANDO PONGAN  CU Comprar cuponera 
		 */
		/*
		String nomSocio = comboBoxSocios.getSelectedItem().toString(); // dueño cuponera
		String nomActividad = comboBoxActividades.getSelectedItem().toString(); 
		Set<String> nomCuponeras = ICU.getCuponerasDisponibles(nomSocio, nomActividad);
		Object[] nomCuponerasObj = nomCuponeras.toArray();
		// Agregamos las cuponeras que haya comprado el socio
		for (Object object : nomCuponerasObj) {
			model.addElement((String) object);
		}
		*/
		
		/*
		 * VERSION SIN COMPRA DE CUPONERA
		 * (Se precisa ControladorCuponeras en este JInternalFrame)
		 */
		//Set<String> nombresCuponeras = ICC.getEspCuponeras();
		//model.addAll(nombresCuponeras); // se puede esto?

		
		// Se le setea el modelo que se la haya creado
		//comboBoxCuponeras.setModel(model);
		
		String nomSocio = comboBoxSocios.getSelectedItem().toString(); // dueño cuponera
		String nomActividad = comboBoxActividades.getSelectedItem().toString(); 
		Set<String> nomCuponeras = ICU.getCuponerasDisponibles(nomSocio, nomActividad);
		if(nomCuponeras.size()!=0) {
			this.comboBoxCuponeras.setEnabled(true);
			this.comboBoxCuponeras.removeAllItems();
			for(String n : nomCuponeras)
				this.comboBoxCuponeras.addItem(n);
		}
		
	}

	private void cmdSeleccionarCuponera(ActionEvent e) {

		// Si esto explota la Logica de cuponeras, es porque capaz que esta
		// agarrando string null en lugar del string vacío ""
		if(comboBoxCuponeras.getSelectedItem()!=null)
			this.cuponeraSeleccionada = comboBoxCuponeras.getSelectedItem().toString();
		else 
			this.cuponeraSeleccionada=null;
	}

	private void registrarSocioAClase() {

		try {
			if (checkCampos()) {
				ICU.registrarSocioAClase(socioSeleccionado, cuponeraSeleccionada, fechaRegistroDateChooser.getDate(),
						claseSeleccionada, actSeleccionada, instSeleccionada);
				JOptionPane.showMessageDialog(this, "Socio registrado a clase exitosamente.", "Registrar socio a clase",
						JOptionPane.INFORMATION_MESSAGE);
				cancelarRegistroAClase();
			} else {
				JOptionPane.showMessageDialog(this, "Se produjo un error. Intentar nuevamente.", "Error",
						JOptionPane.ERROR_MESSAGE);
				arranque();
			}
		} catch (ClaseLlenaException | SocioYaRegistradoException a) {
			JOptionPane.showMessageDialog(this, a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			arranque();
		} 

	}

	private void cancelarRegistroAClase() {
		limpiarCampos();
		this.hide();
	}

	private void cmdAceptar(ActionEvent e) {
		registrarSocioAClase();
	}

	private void cmdCancelar(ActionEvent e) {
		cancelarRegistroAClase();
	}

	/**
	 * @return true sii lo ingresado es correcto
	 */
	private boolean checkCampos() {
		boolean correcto = !this.instSeleccionada.isEmpty() && this.socioEscogido && this.claseEscogida
				&& fechaRegistroDateChooser.getDate() != null;
		return correcto;
	}

	private void limpiarCampos() {

		comboBoxInstituciones.setModel(new DefaultComboBoxModel<String>());

		comboBoxActividades.setEnabled(false);
		comboBoxActividades.setEditable(false);
		comboBoxActividades.setModel(new DefaultComboBoxModel<String>());

		comboBoxSocios.setEnabled(false);
		comboBoxSocios.setEditable(false);
		comboBoxSocios.removeAllItems();
		comboBoxClases.setModel(new DefaultComboBoxModel<String>());

		comboBoxClases.setEnabled(false);
		comboBoxClases.setEditable(false);
		comboBoxCuponeras.setModel(new DefaultComboBoxModel<String>());

		comboBoxCuponeras.setEnabled(false);
		comboBoxCuponeras.setEditable(false);

		btnBotonAceptar.setEnabled(false);

		lblFechaClaseNO.setVisible(false);
		lblFechitaClase.setVisible(false);

		instSeleccionada = null;
		actSeleccionada = null;
		socioSeleccionado = null;
		claseSeleccionada = null;
		socioEscogido = false;
		claseEscogida = false;
		cuponeraSeleccionada = null;
		fechaRegistroDateChooser.setDate(null);

	}

	public RegistroAClase(IControladorInstituciones ICI, IControladorUsuarios ICU, IControladorCuponeras ICC) {
		setTitle("Registrar socio a clase");
		setIconifiable(true);
		setClosable(true);
		this.ICI = ICI;
		this.ICU = ICU;
		this.ICC = ICC;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(true);
		setBounds(100, 100, 619, 472);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 67, 0, 15, 14 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 40, 19, 0, 0, 0, 17, 37, 35, 0, 37, 28, 52, 41, -12, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0,
				1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblSeleccioneInstitucion = new JLabel("Seleccione una Institucion");
		GridBagConstraints gbc_lblSeleccioneInstitucion = new GridBagConstraints();
		gbc_lblSeleccioneInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccioneInstitucion.gridx = 1;
		gbc_lblSeleccioneInstitucion.gridy = 1;
		getContentPane().add(lblSeleccioneInstitucion, gbc_lblSeleccioneInstitucion);

		comboBoxInstituciones = new JComboBox<String>();
		comboBoxInstituciones.setSelectedIndex(-1);
		comboBoxInstituciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdSeleccionarInstitucion(e);
			}
		});

		comboBoxActividades = new JComboBox<String>();
		comboBoxActividades.setSelectedIndex(-1);
		comboBoxActividades.setEnabled(false);
		comboBoxActividades.setEditable(false);

		GridBagConstraints gbc_comboBoxInstituciones = new GridBagConstraints();
		gbc_comboBoxInstituciones.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInstituciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInstituciones.gridx = 1;
		gbc_comboBoxInstituciones.gridy = 2;
		getContentPane().add(comboBoxInstituciones, gbc_comboBoxInstituciones);

		JLabel lblSeleccioneActividad = new JLabel("Seleccione la actividad deseada");
		GridBagConstraints gbc_lblSeleccioneActividad = new GridBagConstraints();
		gbc_lblSeleccioneActividad.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccioneActividad.gridx = 1;
		gbc_lblSeleccioneActividad.gridy = 4;
		getContentPane().add(lblSeleccioneActividad, gbc_lblSeleccioneActividad);

		comboBoxActividades = new JComboBox<>();
		comboBoxActividades.setEnabled(false);
		comboBoxActividades.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
		gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividades.gridx = 1;
		gbc_comboBoxActividades.gridy = 5;
		getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);

		comboBoxActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdSeleccionarActividad(e);
			}
		});

		JPanel panelLabels1 = new JPanel();
		GridBagConstraints gbc_panelLabels1 = new GridBagConstraints();
		gbc_panelLabels1.insets = new Insets(0, 0, 5, 5);
		gbc_panelLabels1.fill = GridBagConstraints.BOTH;
		gbc_panelLabels1.gridx = 1;
		gbc_panelLabels1.gridy = 7;
		getContentPane().add(panelLabels1, gbc_panelLabels1);
		GridBagLayout gbl_panelLabels1 = new GridBagLayout();
		gbl_panelLabels1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelLabels1.rowHeights = new int[] { 0, 0 };
		gbl_panelLabels1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelLabels1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelLabels1.setLayout(gbl_panelLabels1);

		JLabel lblNewLabel = new JLabel("Elija el Socio");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 9;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelLabels1.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Elija la Clase");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 11;
		gbc_lblNewLabel_1.gridx = 10;
		gbc_lblNewLabel_1.gridy = 0;
		panelLabels1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JPanel panel1 = new JPanel();
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.insets = new Insets(0, 0, 5, 5);
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 1;
		gbc_panel1.gridy = 8;
		getContentPane().add(panel1, gbc_panel1);

		comboBoxSocios = new JComboBox<>();
		comboBoxSocios.setSelectedIndex(-1);
		comboBoxSocios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdSeleccionarSocio(e);
			}
		});
		panel1.setLayout(new GridLayout(0, 2, 5, 0));
		comboBoxSocios.setEnabled(false);
		panel1.add(comboBoxSocios);

		comboBoxClases = new JComboBox<>();
		comboBoxClases.setSelectedIndex(-1);
		comboBoxClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdSeleccionarClase(e);
			}
		});
		comboBoxClases.setEnabled(false);
		panel1.add(comboBoxClases);

		JPanel panelLabels2 = new JPanel();
		GridBagConstraints gbc_panelLabels2 = new GridBagConstraints();
		gbc_panelLabels2.insets = new Insets(0, 0, 5, 5);
		gbc_panelLabels2.fill = GridBagConstraints.BOTH;
		gbc_panelLabels2.gridx = 1;
		gbc_panelLabels2.gridy = 10;
		getContentPane().add(panelLabels2, gbc_panelLabels2);
		panelLabels2.setLayout(new GridLayout(0, 4, 4, 0));

		JLabel lblWhitespace1 = new JLabel("");
		panelLabels2.add(lblWhitespace1);

		JLabel lblWhitespace2 = new JLabel("");
		panelLabels2.add(lblWhitespace2);

		lblFechaClaseNO = new JLabel("Fecha de clase: ");
		lblFechaClaseNO.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabels2.add(lblFechaClaseNO);

		lblFechitaClase = new JLabel("");
		panelLabels2.add(lblFechitaClase);

		JPanel panel2 = new JPanel();
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.insets = new Insets(0, 0, 5, 5);
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 1;
		gbc_panel2.gridy = 11;
		getContentPane().add(panel2, gbc_panel2);

		comboBoxCuponeras = new JComboBox<>();
		comboBoxCuponeras.setSelectedIndex(-1);
		comboBoxCuponeras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdSeleccionarCuponera(e);
			}
		});
		panel2.setLayout(new GridLayout(0, 4, 5, 0));

		JLabel lblCuponera = new JLabel("Cuponera");
		panel2.add(lblCuponera);
		comboBoxCuponeras.setEnabled(false);
		panel2.add(comboBoxCuponeras);

		JLabel lblFechaRegistro = new JLabel("Fecha registro:");
		panel2.add(lblFechaRegistro);

		fechaRegistroDateChooser = new JDateChooser();
		panel2.add(fechaRegistroDateChooser);
		
		JTextFieldDateEditor fechaRegEditor = (JTextFieldDateEditor) fechaRegistroDateChooser.getDateEditor();
		fechaRegEditor.setEditable(false);

		JPanel panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.gridx = 1;
		gbc_panelBotones.gridy = 14;
		getContentPane().add(panelBotones, gbc_panelBotones);

		btnBotonAceptar = new JButton("Aceptar");
		btnBotonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAceptar(e);
			}
		});
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBotones.add(btnBotonAceptar);

		JButton btnBotonCancelar = new JButton("Cancelar");
		btnBotonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdCancelar(e);
			}
		});
		panelBotones.add(btnBotonCancelar);

	}

	public void arranque() {
		this.show();

		limpiarCampos();
		cargarInstituciones();
		this.comboBoxInstituciones.setEnabled(true);
	}

	public void cargarInstituciones() {

		// Se crea un nuevo modelo updateado de instituciones
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

		Set<String> instis = ICI.getInstituciones();
		Object[] instisObject = instis.toArray();

		for (Object object : instisObject) {
			model.addElement((String) object);
//			model.addElement(object.toString()); // alternativa
		}
		comboBoxInstituciones.setModel(model);
	}

	private void cargarActividades() {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

		Set<String> setActividades = ICI.getActividadesAceptadasDeInstitucion(instSeleccionada);
		Object[] activObject = setActividades.toArray();

		for (Object object : activObject) {
			model.addElement((String) object);
		}
		comboBoxActividades.setModel(model);

		if (model.getSize() == 0) {
			JOptionPane.showMessageDialog(this, "No hay actividades para esta institucion.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			arranque();
		} else {
			comboBoxActividades.requestFocus();
		}

	}

	private void cargarSocios() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		Object[] socios = ICU.getSocios().toArray();

		for (Object soc : socios) {
			model.addElement((String) soc);
		}
		comboBoxSocios.setModel(model);

		comboBoxSocios.requestFocus();
	}

	private void cargarClases() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

		actSeleccionada = comboBoxActividades.getSelectedItem().toString();
		Object[] clasesVig = ICI.getClasesVigentes(this.instSeleccionada, this.actSeleccionada).toArray();

		for (Object cla : clasesVig) {
			DtClase claDt = (DtClase) cla;
			model.addElement((String) claDt.getNombre());
		}
		comboBoxClases.setModel(model);
		
		if (model.getSize() == 0) {
			JOptionPane.showMessageDialog(this, "No hay clases para esta actividad.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			arranque();
		}
		
	}
}
