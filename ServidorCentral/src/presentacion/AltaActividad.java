package presentacion;

import logica.*;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.Set;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.awt.FlowLayout;
import com.toedter.calendar.JDateChooser;

import excepciones.ActividadRepetidaException;
import excepciones.UsuarioNoExisteException;

//import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.List;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AltaActividad extends JInternalFrame {

	// Controlador de usuarios que se utilizará para las acciones del JFrame
//    private IControladorUsuarios controlUsr;
	private IControladorInstituciones controlInst;
	private JTextField txtNombre;
	private JTextField txtDuracion;
	private JTextField txtCosto;
	private JTextArea txtDescripcion;
	private JDateChooser dateChooserActividad;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JComboBox<String> comboInstituciones;
	private List listCategoria;

	public AltaActividad(IControladorInstituciones ctrl) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		controlInst = ctrl;

		setIconifiable(true);
		setClosable(true);
		setTitle("Alta Actividad");
		getContentPane().setEnabled(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 86, 200, 200, 200, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 24, 0, 0, 26, 0, 19, 35, 56, 0, 19, 0, 19, 35, 19, 35, 35, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblInstitucion = new JLabel("Institucion");
		GridBagConstraints gbc_lblInstitucion = new GridBagConstraints();
		gbc_lblInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstitucion.gridx = 1;
		gbc_lblInstitucion.gridy = 1;
		getContentPane().add(lblInstitucion, gbc_lblInstitucion);

		comboInstituciones = new JComboBox<>();
		GridBagConstraints gbc_comboInstituciones = new GridBagConstraints();
		gbc_comboInstituciones.gridwidth = 3;
		gbc_comboInstituciones.anchor = GridBagConstraints.NORTH;
		gbc_comboInstituciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboInstituciones.insets = new Insets(0, 0, 5, 5);
		gbc_comboInstituciones.gridx = 2;
		gbc_comboInstituciones.gridy = 1;
		getContentPane().add(comboInstituciones, gbc_comboInstituciones);
		
		JLabel lblCategoria = new JLabel("Categorias ");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 1;
		gbc_lblCategoria.gridy = 3;
		getContentPane().add(lblCategoria, gbc_lblCategoria);
		
		 listCategoria = new List();
		 listCategoria.setMultipleSelections(true);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridwidth = 3;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridx = 2;
		gbc_list.gridy = 3;
		getContentPane().add(listCategoria, gbc_list);

		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 6;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("Ingresar nombre");
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 3;
		gbc_txtNombre.fill = GridBagConstraints.BOTH;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 6;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		JLabel lblBiografiaopcional = new JLabel("Descripcion");
		GridBagConstraints gbc_lblBiografiaopcional = new GridBagConstraints();
		gbc_lblBiografiaopcional.anchor = GridBagConstraints.EAST;
		gbc_lblBiografiaopcional.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiografiaopcional.gridx = 1;
		gbc_lblBiografiaopcional.gridy = 8;
		getContentPane().add(lblBiografiaopcional, gbc_lblBiografiaopcional);

		JScrollPane scrollDescripcion = new JScrollPane();
		GridBagConstraints gbc_scrollDescripcion = new GridBagConstraints();
		gbc_scrollDescripcion.gridwidth = 3;
		gbc_scrollDescripcion.fill = GridBagConstraints.BOTH;
		gbc_scrollDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_scrollDescripcion.gridx = 2;
		gbc_scrollDescripcion.gridy = 8;
		getContentPane().add(scrollDescripcion, gbc_scrollDescripcion);

		txtDescripcion = new JTextArea();
		scrollDescripcion.setViewportView(txtDescripcion);

		JLabel lblFechaReg = new JLabel("Fecha registro");
		GridBagConstraints gbc_lblFechaReg = new GridBagConstraints();
		gbc_lblFechaReg.anchor = GridBagConstraints.NORTH;
		gbc_lblFechaReg.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFechaReg.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaReg.gridx = 1;
		gbc_lblFechaReg.gridy = 10;
		getContentPane().add(lblFechaReg, gbc_lblFechaReg);

		dateChooserActividad = new JDateChooser();
		GridBagConstraints gbc_dateChooserActividad = new GridBagConstraints();
		gbc_dateChooserActividad.gridwidth = 2;
		gbc_dateChooserActividad.anchor = GridBagConstraints.NORTH;
		gbc_dateChooserActividad.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserActividad.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserActividad.gridx = 2;
		gbc_dateChooserActividad.gridy = 10;
		getContentPane().add(dateChooserActividad, gbc_dateChooserActividad);

		JLabel lblDuracion = new JLabel("Duración");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 1;
		gbc_lblDuracion.gridy = 12;
		getContentPane().add(lblDuracion, gbc_lblDuracion);

		txtDuracion = new JTextField();
		GridBagConstraints gbc_txtDuracion = new GridBagConstraints();
		gbc_txtDuracion.fill = GridBagConstraints.BOTH;
		gbc_txtDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_txtDuracion.gridx = 2;
		gbc_txtDuracion.gridy = 12;
		getContentPane().add(txtDuracion, gbc_txtDuracion);
		txtDuracion.setColumns(10);

		JLabel lblCosto = new JLabel("Costo");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 1;
		gbc_lblCosto.gridy = 14;
		getContentPane().add(lblCosto, gbc_lblCosto);

		txtCosto = new JTextField();
		GridBagConstraints gbc_txtCosto = new GridBagConstraints();
		gbc_txtCosto.fill = GridBagConstraints.BOTH;
		gbc_txtCosto.insets = new Insets(0, 0, 5, 5);
		gbc_txtCosto.gridx = 2;
		gbc_txtCosto.gridy = 14;
		getContentPane().add(txtCosto, gbc_txtCosto);
		txtCosto.setColumns(10);

		JPanel panelBotones = new JPanel();

		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 0, 5);
		gbc_panelBotones.gridwidth = 3;
		gbc_panelBotones.anchor = GridBagConstraints.WEST;
		gbc_panelBotones.gridx = 2;
		gbc_panelBotones.gridy = 16;
		getContentPane().add(panelBotones, gbc_panelBotones);
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarActividad();
			}
		});
		panelBotones.add(botonAceptar);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarAltaActividad();
			}
		});
		panelBotones.add(botonCancelar);
		setBounds(100, 100, 461, 650);
	}

	public void cargarInstituciones() {
		Set<String> inst = controlInst.getInstituciones();
		comboInstituciones.addItem(null);
		for (String n : inst)
			comboInstituciones.addItem(n);
		comboInstituciones.setSelectedIndex(-1);
	}
	
	private void cargarCategoria() {
		Set<String> cate = controlInst.getCategorias();
		for (String n : cate)
			this.listCategoria.addItem(n);
	}

	private void cancelarAltaActividad() {
		limpiarFormulario();
		this.hide();
	}

	private void registrarActividad() {

		try {

			// Chequeo que formulario no tenga fruta
			if (checkFormulario()) {

				// Obtencion de datos
				String nomInstAct = (String) this.comboInstituciones.getSelectedItem(); // nombre de institucion
				String nombreAct = this.txtNombre.getText(); // nombre de la actividad
				String descripcionAct = this.txtDescripcion.getText(); // descripcion actividad
				Date fechaAct = this.dateChooserActividad.getDate(); // fecha registro actividad

				int duracionAct = Integer.parseInt(txtDuracion.getText()); // duracion
				float costoAct = Float.parseFloat(txtCosto.getText()); // costo
				

				// Llamo al Controlador para registrar actividad en capa logica.
				Set<String> cates = new HashSet<String>();
				String cateArray[]= this.listCategoria.getSelectedItems();
				int cantidad = cateArray.length;
				for(int i = 0;i<cantidad;i++) {
					cates.add(cateArray[i]);
				}
				controlInst.registrarActividad(nomInstAct,cates, nombreAct,null, descripcionAct, duracionAct, costoAct, fechaAct,null);

				// Mensaje de exito
				JOptionPane.showMessageDialog(this, "La actividad se registro con exito", "Alta actividad",
						JOptionPane.INFORMATION_MESSAGE);
				hide();
				limpiarFormulario();
			}
		} catch (NumberFormatException ea) {
			JOptionPane.showMessageDialog(this, "Duracion o costo invalidos", "Datos erroneos",
					JOptionPane.ERROR_MESSAGE);
		} catch (ActividadRepetidaException eb) {
			// WIP
			JOptionPane.showMessageDialog(this, eb.getMessage(), "Datos erroneos", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void arranque() {
		limpiarFormulario();
		this.show();
		cargarInstituciones();
		cargarCategoria();
	}

	private void limpiarFormulario() {

		// limpiar formulario
		// setear campos vacios
		this.comboInstituciones.setSelectedIndex(-1); // -1 = sin seleccion
		this.comboInstituciones.removeAllItems();

		this.txtNombre.setText(""); // usar "" ?
		this.txtDescripcion.setText("");
		this.txtDuracion.setText("");
		this.txtCosto.setText("");
		this.listCategoria.removeAll();
		this.dateChooserActividad.setDate(null);
	}

	private boolean checkFormulario() {
		//Corroboro campos vacios
		if(txtNombre.getText().isEmpty()||txtCosto.getText().isEmpty()||
				txtDescripcion.getText().isEmpty()||txtDuracion.getText().isEmpty()||dateChooserActividad.getDate() == null)
		{
			JOptionPane.showMessageDialog(this, "No pueden haber campos vacios", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Corroboro que se haya seleccionado una institucion
		if(comboInstituciones.getSelectedItem() == null)
		{
			JOptionPane.showMessageDialog(this, "Debe seleccionar una institucion", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Categorias seleccionadas
		if(listCategoria.getSelectedItems().length == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una categoria", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//Costo y duracion positivas
		int duracion = Integer.parseInt(txtDuracion.getText());
		float costo = Float.parseFloat(txtCosto.getText());
		if(duracion <=0||costo<=0) {
			JOptionPane.showMessageDialog(this, "El costo y la duracion deber ser mayores que cero", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		return true;
	}
	
	
}
