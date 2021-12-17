package presentacion;
import logica.IControladorInstituciones;
import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AceptarRechazarActividad extends JInternalFrame {

	private JComboBox<String> comboActividades;
	private JButton btnAceptar;
	private JButton btnRechazar;
	private JButton btnCancelar;
	private IControladorInstituciones ctrlInst;
	public AceptarRechazarActividad(IControladorInstituciones inst) {
		ctrlInst = inst;
		setTitle("Aceptar o rechazar actividad deportiva");
		setResizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 442, 167);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 145, 137, 90, 0};
		gridBagLayout.rowHeights = new int[]{0, 23, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblActividades = new JLabel("Actividades");
		GridBagConstraints gbc_lblActividades = new GridBagConstraints();
		gbc_lblActividades.gridwidth = 3;
		gbc_lblActividades.anchor = GridBagConstraints.SOUTH;
		gbc_lblActividades.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividades.gridx = 1;
		gbc_lblActividades.gridy = 1;
		getContentPane().add(lblActividades, gbc_lblActividades);
		
		comboActividades = new JComboBox<String>();
		GridBagConstraints gbc_comboActividades = new GridBagConstraints();
		gbc_comboActividades.gridwidth = 3;
		gbc_comboActividades.insets = new Insets(0, 0, 5, 5);
		gbc_comboActividades.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboActividades.gridx = 1;
		gbc_comboActividades.gridy = 2;
		getContentPane().add(comboActividades, gbc_comboActividades);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boton(true);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 4;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		
		btnRechazar = new JButton("Rechazar");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boton(false);
			}
		});
		GridBagConstraints gbc_btnRechazar = new GridBagConstraints();
		gbc_btnRechazar.anchor = GridBagConstraints.WEST;
		gbc_btnRechazar.insets = new Insets(0, 0, 5, 5);
		gbc_btnRechazar.gridx = 2;
		gbc_btnRechazar.gridy = 4;
		getContentPane().add(btnRechazar, gbc_btnRechazar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}
	
	public void arranque() {
		comboActividades.removeAllItems();
		Set<String> act = ctrlInst.getActividadesIngresadas();
		comboActividades.addItem(null);
		for (String n : act)
			comboActividades.addItem(n);
		comboActividades.setSelectedIndex(-1);
	}
	
	public void boton(boolean acepta) {
		if (comboActividades.getSelectedItem()!=null) {
			String nomActividad = comboActividades.getSelectedItem().toString();
			ctrlInst.aceptarActividad(nomActividad, acepta);
			if (acepta)
				JOptionPane.showMessageDialog(this, "La actividad "+nomActividad+" se acepto con exito!", "Aceptar Actividad",
						JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "La actividad "+nomActividad+" se rechazo con exito", "Rechazar Actividad",
						JOptionPane.INFORMATION_MESSAGE);
		} else {
			// Ninguna actividad seleccionada
			JOptionPane.showMessageDialog(this, "No se selecciono ninguna actividad.", "Error", JOptionPane.ERROR_MESSAGE);
		}
//		cancelar();
		arranque();
	}
	
	
	public void cancelar() {
		this.hide();
	}
}
