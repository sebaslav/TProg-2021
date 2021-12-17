package presentacion;

import javax.swing.JInternalFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import logica.DtCuponera;
import logica.IControladorCuponeras;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ConsultaCuponera extends JInternalFrame {

	private IControladorCuponeras icc; // ControladorCuponeras
	private ConsultaActividad cact; // redundante? (des)comentar en constructor

	// Elementos propios de GUI
	private JTextField txtFieldNombre;
	private JTextField txtFieldDescripcion;
	private JTextField txtFieldValidoDesde;
	private JTextField txtFieldValidoHasta;
	private JTextField txtFieldDescuento;
	private JTextField txtFieldFechaRegistro;
	private JTable tablaActividades;
	JLabel lblImagenCuponera;

	private JComboBox<String> comboCuponeras;
	private JComboBox<String> comboBoxCategorias;
	private JButton btnConsultarActividad;

	public ConsultaCuponera(IControladorCuponeras icc, ConsultaActividad cact) {
		setIconifiable(true);

		this.icc = icc;
		this.cact = cact;

		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 643, 546);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 555, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 60, 136, 20, 0, 0, 109, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblCuponeras = new JLabel("Cuponeras");
		GridBagConstraints gbc_lblCuponeras = new GridBagConstraints();
		gbc_lblCuponeras.insets = new Insets(0, 0, 0, 5);
		gbc_lblCuponeras.anchor = GridBagConstraints.EAST;
		gbc_lblCuponeras.gridx = 0;
		gbc_lblCuponeras.gridy = 0;
		panel_1.add(lblCuponeras, gbc_lblCuponeras);

		comboCuponeras = new JComboBox<>();
//		comboCuponeras.setSelectedIndex(-1);
//		comboCuponeras.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				seleccionarCuponera();
//			}
//		});
		GridBagConstraints gbc_comboCuponeras = new GridBagConstraints();
		gbc_comboCuponeras.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboCuponeras.gridx = 1;
		gbc_comboCuponeras.gridy = 0;
		panel_1.add(comboCuponeras, gbc_comboCuponeras);

		lblImagenCuponera = new JLabel("[Seleccionar cuponera]");
		GridBagConstraints gbc_lblImagenCuponera = new GridBagConstraints();
		gbc_lblImagenCuponera.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagenCuponera.gridx = 1;
		gbc_lblImagenCuponera.gridy = 3;
		getContentPane().add(lblImagenCuponera, gbc_lblImagenCuponera);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNombre = new JLabel("nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panel.add(lblNombre, gbc_lblNombre);

		txtFieldNombre = new JTextField();
		txtFieldNombre.setEditable(false);
		GridBagConstraints gbc_txtFieldNombre = new GridBagConstraints();
		gbc_txtFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldNombre.gridx = 1;
		gbc_txtFieldNombre.gridy = 0;
		panel.add(txtFieldNombre, gbc_txtFieldNombre);
		txtFieldNombre.setColumns(10);

		JLabel lblDescripcion = new JLabel("descripción");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 1;
		panel.add(lblDescripcion, gbc_lblDescripcion);

		txtFieldDescripcion = new JTextField();
		txtFieldDescripcion.setEditable(false);
		txtFieldDescripcion.setColumns(10);
		GridBagConstraints gbc_txtFieldDescripcion = new GridBagConstraints();
		gbc_txtFieldDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldDescripcion.gridx = 1;
		gbc_txtFieldDescripcion.gridy = 1;
		panel.add(txtFieldDescripcion, gbc_txtFieldDescripcion);

		JLabel lblValidoDesde = new JLabel("válido desde");
		GridBagConstraints gbc_lblValidoDesde = new GridBagConstraints();
		gbc_lblValidoDesde.anchor = GridBagConstraints.WEST;
		gbc_lblValidoDesde.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidoDesde.gridx = 0;
		gbc_lblValidoDesde.gridy = 2;
		panel.add(lblValidoDesde, gbc_lblValidoDesde);

		txtFieldValidoDesde = new JTextField();
		txtFieldValidoDesde.setEditable(false);
		txtFieldValidoDesde.setColumns(10);
		GridBagConstraints gbc_txtFieldValidoDesde = new GridBagConstraints();
		gbc_txtFieldValidoDesde.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldValidoDesde.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldValidoDesde.gridx = 1;
		gbc_txtFieldValidoDesde.gridy = 2;
		panel.add(txtFieldValidoDesde, gbc_txtFieldValidoDesde);

		JLabel lblValidoHasta = new JLabel("válido hasta");
		GridBagConstraints gbc_lblValidoHasta = new GridBagConstraints();
		gbc_lblValidoHasta.anchor = GridBagConstraints.WEST;
		gbc_lblValidoHasta.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidoHasta.gridx = 0;
		gbc_lblValidoHasta.gridy = 3;
		panel.add(lblValidoHasta, gbc_lblValidoHasta);

		txtFieldValidoHasta = new JTextField();
		txtFieldValidoHasta.setEditable(false);
		txtFieldValidoHasta.setColumns(10);
		GridBagConstraints gbc_txtFieldValidoHasta = new GridBagConstraints();
		gbc_txtFieldValidoHasta.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldValidoHasta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldValidoHasta.gridx = 1;
		gbc_txtFieldValidoHasta.gridy = 3;
		panel.add(txtFieldValidoHasta, gbc_txtFieldValidoHasta);

		JLabel lblDescuento = new JLabel("descuento");
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.anchor = GridBagConstraints.WEST;
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 0;
		gbc_lblDescuento.gridy = 4;
		panel.add(lblDescuento, gbc_lblDescuento);

		txtFieldDescuento = new JTextField();
		txtFieldDescuento.setEditable(false);
		txtFieldDescuento.setColumns(10);
		GridBagConstraints gbc_txtFieldDescuento = new GridBagConstraints();
		gbc_txtFieldDescuento.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldDescuento.gridx = 1;
		gbc_txtFieldDescuento.gridy = 4;
		panel.add(txtFieldDescuento, gbc_txtFieldDescuento);

		JLabel lblFechaRegistro = new JLabel("fecha registro");
		GridBagConstraints gbc_lblFechaRegistro = new GridBagConstraints();
		gbc_lblFechaRegistro.anchor = GridBagConstraints.WEST;
		gbc_lblFechaRegistro.insets = new Insets(0, 0, 0, 5);
		gbc_lblFechaRegistro.gridx = 0;
		gbc_lblFechaRegistro.gridy = 5;
		panel.add(lblFechaRegistro, gbc_lblFechaRegistro);

		txtFieldFechaRegistro = new JTextField();
		txtFieldFechaRegistro.setEditable(false);
		txtFieldFechaRegistro.setColumns(10);
		GridBagConstraints gbc_txtFieldFechaRegistro = new GridBagConstraints();
		gbc_txtFieldFechaRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldFechaRegistro.gridx = 1;
		gbc_txtFieldFechaRegistro.gridy = 5;
		panel.add(txtFieldFechaRegistro, gbc_txtFieldFechaRegistro);

		JLabel lblCategorias = new JLabel("Categorias");
		GridBagConstraints gbc_lblCategorias = new GridBagConstraints();
		gbc_lblCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategorias.gridx = 1;
		gbc_lblCategorias.gridy = 5;
		getContentPane().add(lblCategorias, gbc_lblCategorias);

		comboBoxCategorias = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxCategorias = new GridBagConstraints();
		gbc_comboBoxCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCategorias.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCategorias.gridx = 1;
		gbc_comboBoxCategorias.gridy = 6;
		getContentPane().add(comboBoxCategorias, gbc_comboBoxCategorias);

		JLabel lblActividadesDeportivas_1 = new JLabel("Actividades Deportivas");
		GridBagConstraints gbc_lblActividadesDeportivas_1 = new GridBagConstraints();
		gbc_lblActividadesDeportivas_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividadesDeportivas_1.gridx = 1;
		gbc_lblActividadesDeportivas_1.gridy = 7;
		getContentPane().add(lblActividadesDeportivas_1, gbc_lblActividadesDeportivas_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 8;
		getContentPane().add(scrollPane, gbc_scrollPane);

		tablaActividades = new JTable(new DefaultTableModel(new Object[] { "Nombre", "Cantidad de Clases" }, 0));

		// una unica seleccion
		tablaActividades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		// nuevo evento: seleccionar algo en la tabla
		tablaActividades.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			// activar o desactivar boton segun haya alguna fila seleccionada en la tabla
			btnConsultarActividad.setEnabled(!tablaActividades.getSelectionModel().isSelectionEmpty());
		});

		tablaActividades.setDefaultEditor(Object.class, null);
		tablaActividades.setPreferredScrollableViewportSize(new Dimension(500, 70));
		scrollPane.setViewportView(tablaActividades);

		btnConsultarActividad = new JButton("Consultar actividad");
		btnConsultarActividad.setEnabled(false);
		btnConsultarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = tablaActividades.getSelectedRow();
				String nombreActividad = tablaActividades.getModel().getValueAt(filaSeleccionada, 0).toString();
//				System.out.println(nombreActividad);
				cact.arranqueConActividad(nombreActividad); // se va para CU Consultar Actividad
			}
		});
		GridBagConstraints gbc_btnConsultarActividad = new GridBagConstraints();
		gbc_btnConsultarActividad.insets = new Insets(0, 0, 0, 5);
		gbc_btnConsultarActividad.gridx = 1;
		gbc_btnConsultarActividad.gridy = 9;
		getContentPane().add(btnConsultarActividad, gbc_btnConsultarActividad);

	}

	/**
	 * Inicializacion estandar de la ventana, con formulario vacio para consultar
	 * cualquier cuponera.
	 */
	public void arranque() {
		this.show();
		btnConsultarActividad.setVisible(true);
		limpiarFormulario();
		cargarCuponeras();
	}

	/**
	 * Inicializacion para consultar una cuponera en particular.
	 * 
	 * Se usa desde otro caso de uso.
	 */
	public void arranqueAux(String nomCuponera) {
		this.show();
		DtCuponera dtcupo = icc.getDatosCuponera(nomCuponera);

		cargarCuponera(nomCuponera); // cargar al combobox
		llenarFormulario(dtcupo); // cargar a los campos del formulario
		llenarTabla(dtcupo); // cargar la tabla con actividades
		btnConsultarActividad.setVisible(false); // no recursividad entre casos de uso
	}

	/**
	 * Carga todas las cuponeras del sistema al combobox
	 */
	private void cargarCuponeras() {

		// Primero cargamos un nuevo modelo para el JComboBox
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		Set<String> nombresCuponeras = icc.getEspCuponeras();
		for (String nom : nombresCuponeras)
			model.addElement(nom);
		
		// Le seteamos el nuevo modelo
		comboCuponeras.setModel(model);
		
		// Le seteamos indice -1 para que no haya opcion
		// seleccionada por defecto
		comboCuponeras.setSelectedIndex(-1);
		
		// Se agrega actionListener despues para que el
		// setSelectedIndex(-1) no dispare el evento
		comboCuponeras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarCuponera();
			}
		});
		
	}

	/**
	 * Vacía el combobox de cuponeras y después mete 1 sola cuponera.
	 * 
	 * Se usa con arranqueAux.
	 */
	private void cargarCuponera(String nomCuponera) {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(nomCuponera);
		comboCuponeras.setModel(model);
	}

	private void seleccionarCuponera() {
		if (this.comboCuponeras.getSelectedItem() != null ) {
//			this.combo
		try {
			String nombreCuponera = this.comboCuponeras.getSelectedItem().toString();
			DtCuponera datos = icc.getDatosCuponera(nombreCuponera);

			llenarFormulario(datos); // lo hago en metodo aparte para legibilidad
			llenarTabla(datos);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Se produjo un error", "Error", JOptionPane.ERROR_MESSAGE);
			cancelarConsultaCuponera();
		} catch (ClassCastException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Se produjo un error", "Error", JOptionPane.ERROR_MESSAGE);
			cancelarConsultaCuponera();
		} catch (NullPointerException e) {
			e.printStackTrace();
			cancelarConsultaCuponera();
		}
		} else {
			System.out.println("country roads");
			limpiarFormulario(); // reseteamos todo
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
		Image versionChica = imagen.getScaledInstance( (int) (64 * aspectRatio), 64, BufferedImage.SCALE_SMOOTH);

		return new ImageIcon(versionChica);
	}

	private void llenarFormulario(DtCuponera datos) {

		if (datos.getImagen() != null && datos.getImagen().length > 0) {
			try {
				lblImagenCuponera.setIcon(decodificarImagen(datos.getImagen()));
				lblImagenCuponera.setText(null);
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
				lblImagenCuponera.setIcon(null);
				lblImagenCuponera.setText("[No hay imagen que mostrar]");
			}
		} else {
			lblImagenCuponera.setText("[No hay imagen que mostrar]");
		}

		txtFieldNombre.setText(datos.getNombre());
		txtFieldDescripcion.setText(datos.getDescripcion());

		java.text.DateFormat df = new SimpleDateFormat("dd/MM/YYYY");

		txtFieldValidoDesde.setText(df.format(datos.getValidoDesde()));
		txtFieldValidoHasta.setText(df.format(datos.getValidoHasta()));

		txtFieldDescuento.setText(String.format("%.0f", 100*(1 - datos.getDescuento())) + "%");
		txtFieldFechaRegistro.setText(df.format(datos.getFechaRegistro()));

		comboBoxCategorias.removeAllItems();
		Set<String> categorias = datos.getCategorias();
		for (String n : categorias) {
			comboBoxCategorias.addItem(n);
		}
	}

	@SuppressWarnings("unchecked")
	private void llenarTabla(DtCuponera dt) {

		limpiarTabla();

		DefaultTableModel model = (DefaultTableModel) tablaActividades.getModel();

		Object[] actividadNumero = dt.getActividades().entrySet().toArray(); // arreglo de Entry<String, Integer>

		for (Object actnum : actividadNumero) {
			// se atrapan excepciones de casteo afuera de este metodo
			String nombreActividad = ((java.util.Map.Entry<String, Integer>) actnum).getKey();
			int cantidadClases = ((java.util.Map.Entry<String, Integer>) actnum).getValue();

			model.addRow(new String[] { nombreActividad, String.valueOf(cantidadClases) });
		}
		tablaActividades.setRowSelectionAllowed(true);
	}

	/**
	 * Ya se llama esta operacion desde limpiarFormulario().
	 * Limpia la JTable con las actividades asociadas
	 * a la cuponera.
	 */
	private void limpiarTabla() {
		DefaultTableModel model = (DefaultTableModel) tablaActividades.getModel();
		while (model.getRowCount() > 0) {
			model.removeRow(0); // vaciar filas
		}

		btnConsultarActividad.setEnabled(false); // deseleccionar boton (porque dispara ListSelectionEvent)
	}

	/**
	 * Limpia el formulario con los datos de la cuponera
	 * asi como la tabla con las actividades asociadas,
	 * y resetea todo a como estaba al inicio.
	 */
	private void limpiarFormulario() {
		
		// Eliminar todos los actionListeners de ComboCuponeras
		// Esto es porque cargarCuponera() le agrega un actionListener
		// cada vez que se le cargan los datos al JComboBox
		while (comboCuponeras.getActionListeners().length > 0) {
			comboCuponeras.removeActionListener(comboCuponeras.getActionListeners()[0]);
		}
		lblImagenCuponera.setText("[Seleccionar cuponera]");
		lblImagenCuponera.setIcon(null);
		txtFieldNombre.setText(null);
		txtFieldDescripcion.setText(null);
		txtFieldValidoDesde.setText(null);
		txtFieldValidoHasta.setText(null);
		txtFieldDescuento.setText(null);
		txtFieldFechaRegistro.setText(null);
		comboBoxCategorias.removeAllItems();
		limpiarTabla();
	}

	private void cancelarConsultaCuponera() {
		limpiarFormulario(); // redundante?
		this.hide();
	}
}
