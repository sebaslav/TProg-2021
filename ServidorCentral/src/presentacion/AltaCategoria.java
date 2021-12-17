package presentacion;

import java.awt.EventQueue;
import excepciones.CategoriaRepetidaException;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import logica.IControladorInstituciones;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaCategoria extends JInternalFrame {
	private final JLabel lblNombreCategoria = new JLabel("Nombre Categoria");
	private JTextField textCategoria;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private IControladorInstituciones ctrlInst;
	
	
	
	public AltaCategoria(IControladorInstituciones inst) {
		ctrlInst = inst;
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta Categoria");
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 90, 97, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 56, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		GridBagConstraints gbc_lblNombreCategoria = new GridBagConstraints();
		gbc_lblNombreCategoria.anchor = GridBagConstraints.EAST;
		gbc_lblNombreCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreCategoria.gridx = 1;
		gbc_lblNombreCategoria.gridy = 2;
		getContentPane().add(lblNombreCategoria, gbc_lblNombreCategoria);
		
		textCategoria = new JTextField();
		GridBagConstraints gbc_textCategoria = new GridBagConstraints();
		gbc_textCategoria.gridwidth = 2;
		gbc_textCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_textCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCategoria.gridx = 2;
		gbc_textCategoria.gridy = 2;
		getContentPane().add(textCategoria, gbc_textCategoria);
		textCategoria.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 4;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				textCategoria.setText("");
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}
	
	public void arranque() {
		textCategoria.setText(null);
	}
	
	private boolean checkFormulario() {
		return !textCategoria.getText().isEmpty();
	}
	private void aceptar() {
		if (checkFormulario()) {
			try {
				String categoria = textCategoria.getText();
				ctrlInst.registrarCategoria(categoria);
				textCategoria.setText("");
				JOptionPane.showMessageDialog(this, "Categoria registrada con exito", "Alta Categoria",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (CategoriaRepetidaException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta Clase", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			// Muestro error de registro
			JOptionPane.showMessageDialog(this,"Debes ingresar una categoria", "Alta Clase", JOptionPane.ERROR_MESSAGE);
		}
	}
}





