package presentacion;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import logica.IControladorCuponeras;
import logica.IControladorInstituciones;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AgregarActividadACuponera extends JInternalFrame {
	private JTextField txtCantidad;
	private JComboBox<String> comboBoxInst;
	private JComboBox<String> comboBoxAct;
	private JComboBox<String> comboBoxCuponeras;
	private IControladorCuponeras controlCuponera;
	private IControladorInstituciones controlInst;
	private JButton btnCuponera;
	private JButton btnInstitucion;
	private JButton btnActividad;
	private JButton btnConfirmar;
	private JLabel label;

	public AgregarActividadACuponera(IControladorCuponeras controlCup, IControladorInstituciones controlInstitu) {
		setResizable(true);
		getContentPane().setEnabled(false);
		setClosable(true);
		setTitle("Agregar Actividad a Cuponera");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		controlCuponera = controlCup;
		controlInst = controlInstitu;

		setBounds(100, 100, 756, 373);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1, 205, 284, 147, 134, 0};
		gridBagLayout.rowHeights = new int[]{1, 38, 25, 25, 25, 19, 35, 25, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
						
								label = new JLabel("");
								GridBagConstraints gbc_label = new GridBagConstraints();
								gbc_label.anchor = GridBagConstraints.NORTHWEST;
								gbc_label.insets = new Insets(0, 0, 5, 5);
								gbc_label.gridx = 0;
								gbc_label.gridy = 0;
								getContentPane().add(label, gbc_label);
						
								JLabel lblCuponeras_1_1 = new JLabel("Cuponeras");
								GridBagConstraints gbc_lblCuponeras_1_1 = new GridBagConstraints();
								gbc_lblCuponeras_1_1.fill = GridBagConstraints.HORIZONTAL;
								gbc_lblCuponeras_1_1.insets = new Insets(0, 0, 5, 5);
								gbc_lblCuponeras_1_1.gridx = 1;
								gbc_lblCuponeras_1_1.gridy = 2;
								getContentPane().add(lblCuponeras_1_1, gbc_lblCuponeras_1_1);
				
						comboBoxCuponeras = new JComboBox<String>();
						GridBagConstraints gbc_comboBoxCuponeras = new GridBagConstraints();
						gbc_comboBoxCuponeras.anchor = GridBagConstraints.SOUTH;
						gbc_comboBoxCuponeras.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboBoxCuponeras.insets = new Insets(0, 0, 5, 5);
						gbc_comboBoxCuponeras.gridx = 2;
						gbc_comboBoxCuponeras.gridy = 2;
						getContentPane().add(comboBoxCuponeras, gbc_comboBoxCuponeras);
		
				btnCuponera = new JButton("Seleccionar");
				GridBagConstraints gbc_btnCuponera = new GridBagConstraints();
				gbc_btnCuponera.gridwidth = 2;
				gbc_btnCuponera.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnCuponera.insets = new Insets(0, 0, 5, 0);
				gbc_btnCuponera.gridx = 3;
				gbc_btnCuponera.gridy = 2;
				getContentPane().add(btnCuponera, gbc_btnCuponera);
				btnCuponera.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cmdSeleccionarCuponera();
					}
				});
												
														JLabel lblInstitucin_1_1 = new JLabel("Institución");
														GridBagConstraints gbc_lblInstitucin_1_1 = new GridBagConstraints();
														gbc_lblInstitucin_1_1.fill = GridBagConstraints.HORIZONTAL;
														gbc_lblInstitucin_1_1.insets = new Insets(0, 0, 5, 5);
														gbc_lblInstitucin_1_1.gridx = 1;
														gbc_lblInstitucin_1_1.gridy = 3;
														getContentPane().add(lblInstitucin_1_1, gbc_lblInstitucin_1_1);
										
												comboBoxInst = new JComboBox<String>();
												GridBagConstraints gbc_comboBoxInst = new GridBagConstraints();
												gbc_comboBoxInst.anchor = GridBagConstraints.NORTH;
												gbc_comboBoxInst.fill = GridBagConstraints.HORIZONTAL;
												gbc_comboBoxInst.insets = new Insets(0, 0, 5, 5);
												gbc_comboBoxInst.gridx = 2;
												gbc_comboBoxInst.gridy = 3;
												getContentPane().add(comboBoxInst, gbc_comboBoxInst);
												comboBoxInst.setEditable(false);
										
												btnInstitucion = new JButton("Seleccionar");
												GridBagConstraints gbc_btnInstitucion = new GridBagConstraints();
												gbc_btnInstitucion.gridwidth = 2;
												gbc_btnInstitucion.anchor = GridBagConstraints.NORTHWEST;
												gbc_btnInstitucion.insets = new Insets(0, 0, 5, 0);
												gbc_btnInstitucion.gridx = 3;
												gbc_btnInstitucion.gridy = 3;
												getContentPane().add(btnInstitucion, gbc_btnInstitucion);
												btnInstitucion.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														cmdSeleccionarInstitucion();
													}
												});
								
										JLabel lblActividadDeportiva_1_1 = new JLabel("Actividad Deportiva");
										GridBagConstraints gbc_lblActividadDeportiva_1_1 = new GridBagConstraints();
										gbc_lblActividadDeportiva_1_1.anchor = GridBagConstraints.WEST;
										gbc_lblActividadDeportiva_1_1.insets = new Insets(0, 0, 5, 5);
										gbc_lblActividadDeportiva_1_1.gridx = 1;
										gbc_lblActividadDeportiva_1_1.gridy = 4;
										getContentPane().add(lblActividadDeportiva_1_1, gbc_lblActividadDeportiva_1_1);
						
								comboBoxAct = new JComboBox<String>();
								GridBagConstraints gbc_comboBoxAct = new GridBagConstraints();
								gbc_comboBoxAct.anchor = GridBagConstraints.NORTH;
								gbc_comboBoxAct.fill = GridBagConstraints.HORIZONTAL;
								gbc_comboBoxAct.insets = new Insets(0, 0, 5, 5);
								gbc_comboBoxAct.gridx = 2;
								gbc_comboBoxAct.gridy = 4;
								getContentPane().add(comboBoxAct, gbc_comboBoxAct);
						
								btnActividad = new JButton("Seleccionar");
								GridBagConstraints gbc_btnActividad = new GridBagConstraints();
								gbc_btnActividad.gridwidth = 2;
								gbc_btnActividad.anchor = GridBagConstraints.NORTHWEST;
								gbc_btnActividad.insets = new Insets(0, 0, 5, 0);
								gbc_btnActividad.gridx = 3;
								gbc_btnActividad.gridy = 4;
								getContentPane().add(btnActividad, gbc_btnActividad);
								btnActividad.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										cmdSeleccionarActividad();
									}
								});
				
						JLabel lblCantidadDeClases_1_1 = new JLabel("Cantidad de Clases");
						GridBagConstraints gbc_lblCantidadDeClases_1_1 = new GridBagConstraints();
						gbc_lblCantidadDeClases_1_1.fill = GridBagConstraints.HORIZONTAL;
						gbc_lblCantidadDeClases_1_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblCantidadDeClases_1_1.gridx = 1;
						gbc_lblCantidadDeClases_1_1.gridy = 5;
						getContentPane().add(lblCantidadDeClases_1_1, gbc_lblCantidadDeClases_1_1);
				
						txtCantidad = new JTextField();
						GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
						gbc_txtCantidad.anchor = GridBagConstraints.NORTH;
						gbc_txtCantidad.fill = GridBagConstraints.HORIZONTAL;
						gbc_txtCantidad.insets = new Insets(0, 0, 5, 5);
						gbc_txtCantidad.gridx = 2;
						gbc_txtCantidad.gridy = 5;
						getContentPane().add(txtCantidad, gbc_txtCantidad);
						txtCantidad.setEnabled(false);
						txtCantidad.setColumns(10);
						
								btnConfirmar = new JButton("Confirmar");
								GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
								gbc_btnConfirmar.anchor = GridBagConstraints.NORTHEAST;
								gbc_btnConfirmar.insets = new Insets(0, 0, 0, 5);
								gbc_btnConfirmar.gridx = 2;
								gbc_btnConfirmar.gridy = 7;
								getContentPane().add(btnConfirmar, gbc_btnConfirmar);
								btnConfirmar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										cmdConfirmar();
									}
								});
				
						JButton btnCancelar = new JButton("Cancelar");
						GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
						gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
						gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
						gbc_btnCancelar.gridx = 3;
						gbc_btnCancelar.gridy = 7;
						getContentPane().add(btnCancelar, gbc_btnCancelar);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cmdCancelar();
					}
				});

	}

	public void arranque() {
		limpiarFormulario();
		cargarCuponeras();
		comboBoxCuponeras.setEnabled(true);
		if (comboBoxCuponeras.getItemCount() > 0) {
			btnCuponera.setEnabled(true);
		}
	}

	public void cargarCuponeras() {
		Set<String> cupo = controlCuponera.getEspCuponerasNoCompradas();
		for (String n : cupo)
			comboBoxCuponeras.addItem(n);
	}

	public void cargarInstituciones() {
		Set<String> inst = controlInst.getInstituciones();
		for (String n : inst)
			comboBoxInst.addItem(n);
	}

	public void cargarActividades() {
		Set<String> act = controlCuponera.getActividadesFaltantes((String) comboBoxInst.getSelectedItem(), (String) comboBoxCuponeras.getSelectedItem());
		for (String n : act)
			comboBoxAct.addItem(n);
	}

	protected void cmdSeleccionarCuponera() {
		btnCuponera.setEnabled(false);
		comboBoxCuponeras.setEnabled(false);
		btnInstitucion.setEnabled(true);
		comboBoxInst.setEnabled(true);
		cargarInstituciones();
	}

	protected void cmdSeleccionarInstitucion() {
		btnInstitucion.setEnabled(false);
		comboBoxInst.setEnabled(false);
		btnActividad.setEnabled(true);
		comboBoxAct.setEnabled(true);
		cargarActividades();
	}

	public void cmdSeleccionarActividad() {
		btnActividad.setEnabled(false);
		comboBoxAct.setEnabled(false);
		txtCantidad.setEnabled(true);
		btnConfirmar.setEnabled(true);
	}

	protected void cmdConfirmar() {
		int cantidad;
		try {
			cantidad = Integer.parseInt(txtCantidad.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de clases debe ser un numero entero",
					"Agregar Actividad A Cuponera", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (comboBoxInst.getSelectedItem() == null ||
			comboBoxAct.getSelectedItem() == null ||
			comboBoxCuponeras.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Campo no valido",
					"Agregar Actividad A Cuponera", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (cantidad <= 0) {
			JOptionPane.showMessageDialog(this, "La cantidad de clases debe ser mayor a 0",
					"Agregar Actividad A Cuponera", JOptionPane.ERROR_MESSAGE);
		} else {
			String nomInstitucion = (String) comboBoxInst.getSelectedItem();
			String nomActividad = (String) comboBoxAct.getSelectedItem();
			String nomCuponera = (String) comboBoxCuponeras.getSelectedItem();
			controlCuponera.agregarActividadACuponera(nomInstitucion, nomActividad, nomCuponera, cantidad);
			JOptionPane.showMessageDialog(this, "Actividad agregada con exito", "Agregar Actividad A Cuponera",
					JOptionPane.INFORMATION_MESSAGE);
			limpiarFormulario();
			setVisible(false);
		}
	}

	protected void cmdCancelar() {
		limpiarFormulario();
		setVisible(false);
	}

	public void limpiarFormulario() {
		btnCuponera.setEnabled(false);
		btnInstitucion.setEnabled(false);
		btnActividad.setEnabled(false);
		txtCantidad.setText("");
		txtCantidad.setEnabled(false);
		comboBoxAct.removeAllItems();
		comboBoxAct.setEnabled(false);
		comboBoxCuponeras.removeAllItems();
		comboBoxCuponeras.setEnabled(false);
		comboBoxInst.removeAllItems();
		comboBoxInst.setEnabled(false);
		btnConfirmar.setEnabled(false);
	}
}
