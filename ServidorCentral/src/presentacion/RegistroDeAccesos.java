package presentacion;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.DtRegistro;
import logica.IControladorRegistros;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class RegistroDeAccesos extends JInternalFrame {

	private IControladorRegistros controlReg;
	private JTable tablaRegistros;
	
	public RegistroDeAccesos(IControladorRegistros icr) {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		controlReg = icr;
		
		setTitle("Registros de acceso al sitio");
		setBounds(100, 100, 595, 498);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		tablaRegistros = new JTable(new DefaultTableModel(new Object[] { "#", "IP", "URL", "Browser", "SO" }, 0));
		scrollPane.setViewportView(tablaRegistros);
		

	}
	
	public void arranque() {
		llenarTabla();
	}
	
	
	private void llenarTabla() {
		limpiarTabla();
		List<DtRegistro> registros = controlReg.listarRegistros();

		DefaultTableModel model = (DefaultTableModel) tablaRegistros.getModel();

		for (int i=0; i<registros.size(); i++) {
			String ip = registros.get(i).getIp();
			String url = registros.get(i).getUrl();
			String browser = registros.get(i).getBrowser();
			String sisOp = registros.get(i).getSisOp();

			model.addRow(new String[] { String.valueOf(i+1), ip, url, browser, sisOp });
		}
		//tablaRegistros.setRowSelectionAllowed(true);
	}
	
	private void limpiarTabla() {
		DefaultTableModel model = (DefaultTableModel) tablaRegistros.getModel();
		while (model.getRowCount() > 0) {
			model.removeRow(0); // vaciar filas
		}
	}

}
