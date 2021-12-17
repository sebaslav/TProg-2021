package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import excepciones.ClaseRepetidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.DtClase;
import logica.IControladorInstituciones;
import logica.IControladorUsuarios;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Set;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JFrame;
import com.toedter.components.JLocaleChooser;

@SuppressWarnings("serial")
public class AltaClase extends JInternalFrame {
	
	private IControladorInstituciones controlInst;
	
	private JTextField nombreTextField;
	private JTextField socMinTextField;
	private JTextField socMaxTextField;
	private JTextField urlTextField;
	private JComboBox<String> comboBoxProf;
	private JComboBox<String> comboBoxInst;
	private JComboBox<String> comboBoxAct;
	private JDateChooser fechaAltadateChooser;
	private JDateChooser fechaDictadoDateChooser;
	private JTextField txtHora;
	private JTextField txtMinuto;
	private JButton btnElegirInstitucion;
	private JButton btnAceptar;

	public AltaClase(IControladorInstituciones ici) {
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Clase");
		
		controlInst = ici;
		
		setBounds(100, 100, 687, 593);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {175, 104, 103, 69, 132};
		gridBagLayout.rowHeights = new int[] {46, 49, 49, 31, 46, 56, 50, 41, 43, 31, 100};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		btnElegirInstitucion = new JButton("Elegir Institucion");
		btnElegirInstitucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnElegirInstitucion.setEnabled(false);
				comboBoxInst.setEnabled(false);
				boolean esCorrecto = cargarActividades();
				if(esCorrecto) {
					cargarProfesores();
					btnAceptar.setEnabled(true);
				}
			}
		});
		GridBagConstraints gbc_btnElegirInstitucion = new GridBagConstraints();
		gbc_btnElegirInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_btnElegirInstitucion.gridx = 0;
		gbc_btnElegirInstitucion.gridy = 0;
		getContentPane().add(btnElegirInstitucion, gbc_btnElegirInstitucion);
		
		comboBoxInst = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxInst = new GridBagConstraints();
		gbc_comboBoxInst.gridwidth = 3;
		gbc_comboBoxInst.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInst.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInst.gridx = 1;
		gbc_comboBoxInst.gridy = 0;
		getContentPane().add(comboBoxInst, gbc_comboBoxInst);
		
		JLabel lblActividad = new JLabel("Actividad");
		GridBagConstraints gbc_lblActividad = new GridBagConstraints();
		gbc_lblActividad.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividad.gridx = 0;
		gbc_lblActividad.gridy = 1;
		getContentPane().add(lblActividad, gbc_lblActividad);
		
		comboBoxAct = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxAct = new GridBagConstraints();
		gbc_comboBoxAct.gridwidth = 3;
		gbc_comboBoxAct.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAct.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAct.gridx = 1;
		gbc_comboBoxAct.gridy = 1;
		getContentPane().add(comboBoxAct, gbc_comboBoxAct);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		nombreTextField = new JTextField();
		GridBagConstraints gbc_nombreTextField = new GridBagConstraints();
		gbc_nombreTextField.gridwidth = 3;
		gbc_nombreTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTextField.fill = GridBagConstraints.BOTH;
		gbc_nombreTextField.gridx = 1;
		gbc_nombreTextField.gridy = 2;
		getContentPane().add(nombreTextField, gbc_nombreTextField);
		nombreTextField.setColumns(10);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de dictado");
		GridBagConstraints gbc_lblFechaDeInicio = new GridBagConstraints();
		gbc_lblFechaDeInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeInicio.gridx = 0;
		gbc_lblFechaDeInicio.gridy = 3;
		getContentPane().add(lblFechaDeInicio, gbc_lblFechaDeInicio);
		
		fechaDictadoDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaDictadoDateChooser = new GridBagConstraints();
		gbc_fechaDictadoDateChooser.gridwidth = 3;
		gbc_fechaDictadoDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDictadoDateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaDictadoDateChooser.gridx = 1;
		gbc_fechaDictadoDateChooser.gridy = 3;
		getContentPane().add(fechaDictadoDateChooser, gbc_fechaDictadoDateChooser);
		JTextFieldDateEditor editorFechaDictado = (JTextFieldDateEditor) fechaDictadoDateChooser.getDateEditor();
		editorFechaDictado.setEditable(false);
		
		JLabel lblHoraDeInicio = new JLabel("Hora");
		GridBagConstraints gbc_lblHoraDeInicio = new GridBagConstraints();
		gbc_lblHoraDeInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoraDeInicio.gridx = 0;
		gbc_lblHoraDeInicio.gridy = 4;
		getContentPane().add(lblHoraDeInicio, gbc_lblHoraDeInicio);
		
		txtHora = new JTextField();
		GridBagConstraints gbc_txtHora = new GridBagConstraints();
		gbc_txtHora.insets = new Insets(0, 0, 5, 5);
		gbc_txtHora.gridx = 1;
		gbc_txtHora.gridy = 4;
		getContentPane().add(txtHora, gbc_txtHora);
		txtHora.setColumns(10);
		
		JLabel lblMinuto = new JLabel("Minuto");
		GridBagConstraints gbc_lblMinuto = new GridBagConstraints();
		gbc_lblMinuto.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinuto.gridx = 2;
		gbc_lblMinuto.gridy = 4;
		getContentPane().add(lblMinuto, gbc_lblMinuto);
		
		txtMinuto = new JTextField();
		GridBagConstraints gbc_txtMinuto = new GridBagConstraints();
		gbc_txtMinuto.insets = new Insets(0, 0, 5, 5);
		gbc_txtMinuto.gridx = 3;
		gbc_txtMinuto.gridy = 4;
		getContentPane().add(txtMinuto, gbc_txtMinuto);
		txtMinuto.setColumns(10);
		
		JLabel lblProfesor = new JLabel("Profesor");
		GridBagConstraints gbc_lblProfesor = new GridBagConstraints();
		gbc_lblProfesor.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfesor.gridx = 0;
		gbc_lblProfesor.gridy = 5;
		getContentPane().add(lblProfesor, gbc_lblProfesor);
		
		comboBoxProf = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxProf = new GridBagConstraints();
		gbc_comboBoxProf.gridwidth = 3;
		gbc_comboBoxProf.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxProf.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxProf.gridx = 1;
		gbc_comboBoxProf.gridy = 5;
		getContentPane().add(comboBoxProf, gbc_comboBoxProf);
		
		JLabel lblSociosMinimos = new JLabel("Socios minimos");
		GridBagConstraints gbc_lblSociosMinimos = new GridBagConstraints();
		gbc_lblSociosMinimos.insets = new Insets(0, 0, 5, 5);
		gbc_lblSociosMinimos.gridx = 0;
		gbc_lblSociosMinimos.gridy = 6;
		getContentPane().add(lblSociosMinimos, gbc_lblSociosMinimos);
		
		socMinTextField = new JTextField();
		GridBagConstraints gbc_socMinTextField = new GridBagConstraints();
		gbc_socMinTextField.gridwidth = 3;
		gbc_socMinTextField.insets = new Insets(0, 0, 5, 5);
		gbc_socMinTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_socMinTextField.gridx = 1;
		gbc_socMinTextField.gridy = 6;
		getContentPane().add(socMinTextField, gbc_socMinTextField);
		socMinTextField.setColumns(10);
		
		JLabel lblSociosMaximos = new JLabel("Socios maximos");
		GridBagConstraints gbc_lblSociosMaximos = new GridBagConstraints();
		gbc_lblSociosMaximos.insets = new Insets(0, 0, 5, 5);
		gbc_lblSociosMaximos.gridx = 0;
		gbc_lblSociosMaximos.gridy = 7;
		getContentPane().add(lblSociosMaximos, gbc_lblSociosMaximos);
		
		socMaxTextField = new JTextField();
		socMaxTextField.setColumns(10);
		GridBagConstraints gbc_socMaxTextField = new GridBagConstraints();
		gbc_socMaxTextField.gridwidth = 3;
		gbc_socMaxTextField.insets = new Insets(0, 0, 5, 5);
		gbc_socMaxTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_socMaxTextField.gridx = 1;
		gbc_socMaxTextField.gridy = 7;
		getContentPane().add(socMaxTextField, gbc_socMaxTextField);
		
		JLabel lblUrl = new JLabel("URL");
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 0;
		gbc_lblUrl.gridy = 8;
		getContentPane().add(lblUrl, gbc_lblUrl);
		
		urlTextField = new JTextField();
		urlTextField.setColumns(10);
		GridBagConstraints gbc_urlTextField = new GridBagConstraints();
		gbc_urlTextField.gridwidth = 3;
		gbc_urlTextField.insets = new Insets(0, 0, 5, 5);
		gbc_urlTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlTextField.gridx = 1;
		gbc_urlTextField.gridy = 8;
		getContentPane().add(urlTextField, gbc_urlTextField);
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de alta");
		GridBagConstraints gbc_lblFechaDeAlta = new GridBagConstraints();
		gbc_lblFechaDeAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeAlta.gridx = 0;
		gbc_lblFechaDeAlta.gridy = 9;
		getContentPane().add(lblFechaDeAlta, gbc_lblFechaDeAlta);
		
		fechaAltadateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaAltadateChooser = new GridBagConstraints();
		gbc_fechaAltadateChooser.gridwidth = 3;
		gbc_fechaAltadateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaAltadateChooser.fill = GridBagConstraints.BOTH;
		gbc_fechaAltadateChooser.gridx = 1;
		gbc_fechaAltadateChooser.gridy = 9;
		getContentPane().add(fechaAltadateChooser, gbc_fechaAltadateChooser);
		JTextFieldDateEditor editorFechaAlta = (JTextFieldDateEditor) fechaAltadateChooser.getDateEditor();
		editorFechaAlta.setEditable(false);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 10;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaClase();
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
	

	public void arranque() {
		limpiarFormulario();
		cargarInstituciones();
		if (comboBoxInst.getItemCount() > 0) {
			btnElegirInstitucion.setEnabled(true);
		}
	}
	public void cargarInstituciones() {
		Set<String> inst = controlInst.getInstituciones();
		for(String n : inst)
			comboBoxInst.addItem(n);
	}

	public boolean cargarActividades() {
		Set<String> act = controlInst.getActividadesAceptadasDeInstitucion((String )comboBoxInst.getSelectedItem());
		for(String n : act)
			comboBoxAct.addItem(n);
		if(act.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay actividades asociadas a esta institucion", "Alta Clase",
                    JOptionPane.ERROR_MESSAGE);
			hide();
			limpiarFormulario();
			return false;
		}
		return true;
	}

	public void cargarProfesores() {
		Set<String> prof = controlInst.getProfesoresDeInstitucion((String)comboBoxInst.getSelectedItem());
		for(String n : prof)
			comboBoxProf.addItem(n);
	}
	
	public void altaClase() {
		String activ = (String) comboBoxAct.getSelectedItem();
		String inst = (String) comboBoxInst.getSelectedItem();
		String prof = (String) comboBoxProf.getSelectedItem();
		String nom = nombreTextField.getText();
		String url = urlTextField.getText();
		Date fechaDictado = fechaDictadoDateChooser.getDate();
		Date fechaAlta = fechaAltadateChooser.getDate();
		if (checkFormulario()) {
			try {
				String hora = txtHora.getText();
				String minuto = txtMinuto.getText();
				int socMin = Integer.parseInt(socMinTextField.getText());
				int socMax = Integer.parseInt(socMaxTextField.getText());
				int intHora = Integer.parseInt(hora);
				int intMinuto = Integer.parseInt(minuto);
				fechaDictado.setHours(intHora);
				fechaDictado.setMinutes(intMinuto);
				DtClase dt = new DtClase(nom, fechaDictado, socMax, socMin, fechaAlta, url, null, null, null, null, null, 0, null, 0, null, false);
                controlInst.registrarClase(activ, inst, prof, dt);
                // Muestro éxito de la operación
                JOptionPane.showMessageDialog(this, "La clase se ha creado con éxito", "Alta Clase",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                setVisible(false);
            } catch (ClaseRepetidaException e) {
                // Muestro error de registro
                JOptionPane.showMessageDialog(this, e.getMessage(), "Alta Clase", JOptionPane.ERROR_MESSAGE);
                limpiarFormulario();
                setVisible(false);
            }
		}
	}

	public void limpiarFormulario() {
		nombreTextField.setText("");
		socMinTextField.setText("");
		socMaxTextField.setText("");
		txtHora.setText("");
		txtMinuto.setText("");
		urlTextField.setText("");
		comboBoxProf.removeAllItems();
		comboBoxInst.removeAllItems();
		comboBoxAct.removeAllItems();
		comboBoxInst.setEnabled(true);
		fechaDictadoDateChooser.setDate(null);
		fechaAltadateChooser.setDate(null);
		btnElegirInstitucion.setEnabled(false);
		btnAceptar.setEnabled(false);
	}
	
	public boolean checkFormulario() {
		String nom = nombreTextField.getText();
		String socMin = socMinTextField.getText();
		String socMax = socMaxTextField.getText();
		String url = urlTextField.getText();
		String hora = txtHora.getText();
		String minuto = txtMinuto.getText();
		JTextFieldDateEditor fechaDictado = (JTextFieldDateEditor) fechaDictadoDateChooser.getDateEditor();
		String fechaDictadoTexto = fechaDictado.getText();
		JTextFieldDateEditor fechaAlta = (JTextFieldDateEditor) fechaAltadateChooser.getDateEditor();
		String fechaAltaTexto = fechaAlta.getText();
		if (nom.isEmpty() || socMin.isEmpty() || socMax.isEmpty() || url.isEmpty() || hora.isEmpty() || minuto.isEmpty() || fechaDictadoTexto.isEmpty() || fechaAltaTexto.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta Clase", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
            Integer.parseInt(socMin);
            Integer.parseInt(socMax);
            Integer.parseInt(hora);
            Integer.parseInt(minuto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Socios maximos, socios minimos, Horas y minutos deben ser numeros enteros", "Alta Clase",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
		if(Integer.parseInt(socMin) > Integer.parseInt(socMax)) {
			JOptionPane.showMessageDialog(this, "Socios minimos no puede ser mayor a socios maximos ", "Alta Clase", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(Integer.parseInt(socMin) < 1) {
			JOptionPane.showMessageDialog(this, "Socios minimos debe ser mayor que 0 ", "Alta Clase", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		Integer intHora = Integer.parseInt(hora);
		Integer intMinuto = Integer.parseInt(minuto);
		if (intHora >= 24 || intHora < 0 || intMinuto < 0 || intMinuto >= 60 ) {
			JOptionPane.showMessageDialog(this, "Hora debe ser menor a 24 y mayor o igual a cero y minuto debe ser menor a 60 y mayor igual a cero ", "Alta Clase", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
