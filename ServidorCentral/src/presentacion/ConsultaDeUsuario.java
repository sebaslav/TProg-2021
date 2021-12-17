package presentacion;

import javax.swing.JInternalFrame;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import logica.IControladorUsuarios;
import logica.DtUsuario;
import logica.IControladorInstituciones;
import logica.DtSocio;
import logica.DtActividad;
import logica.DtClase;
import logica.DtProfesor;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ConsultaDeUsuario extends JInternalFrame {
	private JTextField txtNickname;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JDateChooser dateFecha;
	private JComboBox<String> comboUsuarios;
	private JComboBox<String> comboClasesSocio;
	private JComboBox<String> comboClasesProf;
	private JComboBox<String> comboActividades;
	JLabel lblClases;
	JLabel lblSocio;
	JLabel lblProfesor;
	JLabel lblClases_1;
	JLabel lblActividades;
	JLabel lblDescripcion;
	JScrollPane scrollPane_1;
	JScrollPane scrollPane;
	JLabel lblBiografia;
	JLabel lblInstitucion;
	JLabel lblUrl;
	private IControladorUsuarios ctrlUsuario;
	private IControladorInstituciones ctrlInstituciones;
	private ConsultaClase frameClase;
	private ConsultaActividad frameActividad;
	private DtUsuario user;
	private JTextField txtInstitucion;
	private JTextField txtUrl;
	private JTextArea txtBio;
	private JTextArea txtDescripcion;
	private JButton clasesSociosbtnNewButton;
	private JButton clasesProfeBtnNewButton;
	private JButton actBtnNewButton;

	private Map<String, String> clases; // Usado para obtener actividad de una clase en O(1)
	private JLabel lblImagen;

	public ConsultaDeUsuario(IControladorUsuarios ctrl, IControladorInstituciones ctrlIns, ConsultaClase FClase,
			ConsultaActividad FAct) {
		setIconifiable(true);
		ctrlUsuario = ctrl;
		ctrlInstituciones = ctrlIns;
		frameClase = FClase;
		frameActividad = FAct;
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setEnabled(true);
		setTitle("Consulta Usuario");
		setBounds(100, 100, 861, 454);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 16, 0, 23, 159, 110, 88, 192, 99, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 21, 38, 34, 34, 34, 34, 34, 35, 35, 35, 35, 35, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblUsuarios = new JLabel("Usuarios");
		GridBagConstraints gbc_lblUsuarios = new GridBagConstraints();
		gbc_lblUsuarios.anchor = GridBagConstraints.EAST;
		gbc_lblUsuarios.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarios.gridx = 1;
		gbc_lblUsuarios.gridy = 1;
		getContentPane().add(lblUsuarios, gbc_lblUsuarios);

		comboUsuarios = new JComboBox<String>();
		comboUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarUsuario();
			}
		});
		GridBagConstraints gbc_comboUsuarios = new GridBagConstraints();
		gbc_comboUsuarios.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboUsuarios.insets = new Insets(0, 0, 5, 5);
		gbc_comboUsuarios.gridx = 3;
		gbc_comboUsuarios.gridy = 1;
		getContentPane().add(comboUsuarios, gbc_comboUsuarios);
		
		lblImagen = new JLabel("[No tiene imagen]");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.fill = GridBagConstraints.BOTH;
		gbc_lblImagen.gridheight = 2;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 6;
		gbc_lblImagen.gridy = 1;
		getContentPane().add(lblImagen, gbc_lblImagen);

		JLabel lblNickname = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 1;
		gbc_lblNickname.gridy = 2;
		getContentPane().add(lblNickname, gbc_lblNickname);

		txtNickname = new JTextField();
		txtNickname.setEditable(false);
		GridBagConstraints gbc_txtNickname = new GridBagConstraints();
		gbc_txtNickname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNickname.gridx = 3;
		gbc_txtNickname.gridy = 2;
		getContentPane().add(txtNickname, gbc_txtNickname);

		txtNickname.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 3;
		getContentPane().add(txtNombre, gbc_txtNombre);

		txtNombre.setColumns(10);
		
				lblSocio = new JLabel("Socio");
				lblSocio.setVisible(false);
				GridBagConstraints gbc_lblSocio = new GridBagConstraints();
				gbc_lblSocio.insets = new Insets(0, 0, 5, 5);
				gbc_lblSocio.gridx = 6;
				gbc_lblSocio.gridy = 3;
				getContentPane().add(lblSocio, gbc_lblSocio);
		JLabel lblApellido = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 1;
		gbc_lblApellido.gridy = 4;
		getContentPane().add(lblApellido, gbc_lblApellido);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		GridBagConstraints gbc_txtApellido = new GridBagConstraints();
		gbc_txtApellido.insets = new Insets(0, 0, 5, 5);
		gbc_txtApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApellido.gridx = 3;
		gbc_txtApellido.gridy = 4;
		getContentPane().add(txtApellido, gbc_txtApellido);

		txtApellido.setColumns(10);
		lblClases = new JLabel("Clases");
		lblClases.setVisible(false);
		GridBagConstraints gbc_lblClases = new GridBagConstraints();
		gbc_lblClases.insets = new Insets(0, 0, 5, 5);
		gbc_lblClases.gridx = 5;
		gbc_lblClases.gridy = 4;
		getContentPane().add(lblClases, gbc_lblClases);
		
				comboClasesSocio = new JComboBox<String>();
				comboClasesSocio.setVisible(false);
				
						GridBagConstraints gbc_comboClasesSocio = new GridBagConstraints();
						gbc_comboClasesSocio.insets = new Insets(0, 0, 5, 5);
						gbc_comboClasesSocio.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboClasesSocio.gridx = 6;
						gbc_comboClasesSocio.gridy = 4;
						getContentPane().add(comboClasesSocio, gbc_comboClasesSocio);
		
				clasesSociosbtnNewButton = new JButton("Consultar");
				clasesSociosbtnNewButton.setVisible(false);
				clasesSociosbtnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verClase((String) comboClasesSocio.getSelectedItem());
					}
				});
				
						GridBagConstraints gbc_clasesSociosbtnNewButton = new GridBagConstraints();
						gbc_clasesSociosbtnNewButton.insets = new Insets(0, 0, 5, 5);
						gbc_clasesSociosbtnNewButton.gridx = 7;
						gbc_clasesSociosbtnNewButton.gridy = 4;
						getContentPane().add(clasesSociosbtnNewButton, gbc_clasesSociosbtnNewButton);

		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 5;
		getContentPane().add(lblEmail, gbc_lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 3;
		gbc_txtEmail.gridy = 5;
		getContentPane().add(txtEmail, gbc_txtEmail);

		txtEmail.setColumns(10);
		lblProfesor = new JLabel("Profesor");
		lblProfesor.setVisible(false);
		GridBagConstraints gbc_lblProfesor = new GridBagConstraints();
		gbc_lblProfesor.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfesor.gridx = 6;
		gbc_lblProfesor.gridy = 5;
		getContentPane().add(lblProfesor, gbc_lblProfesor);

		JLabel lblFecha = new JLabel("Fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 1;
		gbc_lblFecha.gridy = 6;
		getContentPane().add(lblFecha, gbc_lblFecha);

		dateFecha = new JDateChooser();

		dateFecha.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_dateFecha = new GridBagConstraints();
		gbc_dateFecha.insets = new Insets(0, 0, 5, 5);
		gbc_dateFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateFecha.gridx = 3;
		gbc_dateFecha.gridy = 6;
		getContentPane().add(dateFecha, gbc_dateFecha);

		lblBiografia = new JLabel("Biografia");
		lblBiografia.setVisible(false);
		
				clasesProfeBtnNewButton = new JButton("Consultar");
				clasesProfeBtnNewButton.setVisible(false);
				clasesProfeBtnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verClase((String) comboClasesProf.getSelectedItem());
					}
				});
				
						comboClasesProf = new JComboBox<String>();
						comboClasesProf.setVisible(false);
						lblClases_1 = new JLabel("Clases");
						lblClases_1.setVisible(false);
						GridBagConstraints gbc_lblClases_1 = new GridBagConstraints();
						gbc_lblClases_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblClases_1.gridx = 5;
						gbc_lblClases_1.gridy = 6;
						getContentPane().add(lblClases_1, gbc_lblClases_1);
						GridBagConstraints gbc_comboClasesProf = new GridBagConstraints();
						gbc_comboClasesProf.insets = new Insets(0, 0, 5, 5);
						gbc_comboClasesProf.fill = GridBagConstraints.HORIZONTAL;
						gbc_comboClasesProf.gridx = 6;
						gbc_comboClasesProf.gridy = 6;
						getContentPane().add(comboClasesProf, gbc_comboClasesProf);
				GridBagConstraints gbc_clasesProfeBtnNewButton = new GridBagConstraints();
				gbc_clasesProfeBtnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_clasesProfeBtnNewButton.gridx = 7;
				gbc_clasesProfeBtnNewButton.gridy = 6;
				getContentPane().add(clasesProfeBtnNewButton, gbc_clasesProfeBtnNewButton);
		GridBagConstraints gbc_lblBiografia = new GridBagConstraints();
		gbc_lblBiografia.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblBiografia.gridheight = 2;
		gbc_lblBiografia.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiografia.gridx = 1;
		gbc_lblBiografia.gridy = 7;
		getContentPane().add(lblBiografia, gbc_lblBiografia);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 3;
		gbc_scrollPane.gridy = 7;
		getContentPane().add(scrollPane, gbc_scrollPane);

		txtBio = new JTextArea();
		txtBio.setWrapStyleWord(true);
		txtBio.setLineWrap(true);
		txtBio.setEditable(false);
		scrollPane.setViewportView(txtBio);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setVisible(false);
		
				lblActividades = new JLabel("Actividades");
				lblActividades.setVisible(false);
				GridBagConstraints gbc_lblActividades = new GridBagConstraints();
				gbc_lblActividades.insets = new Insets(0, 0, 5, 5);
				gbc_lblActividades.anchor = GridBagConstraints.EAST;
				gbc_lblActividades.gridx = 5;
				gbc_lblActividades.gridy = 7;
				getContentPane().add(lblActividades, gbc_lblActividades);
		
				comboActividades = new JComboBox<String>();
				comboActividades.setVisible(false);
				GridBagConstraints gbc_comboActividades = new GridBagConstraints();
				gbc_comboActividades.insets = new Insets(0, 0, 5, 5);
				gbc_comboActividades.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboActividades.gridx = 6;
				gbc_comboActividades.gridy = 7;
				getContentPane().add(comboActividades, gbc_comboActividades);
		
				actBtnNewButton = new JButton("Consultar");
				actBtnNewButton.setVisible(false);
				actBtnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verActividad();
					}
				});
				GridBagConstraints gbc_actBtnNewButton = new GridBagConstraints();
				gbc_actBtnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_actBtnNewButton.gridx = 7;
				gbc_actBtnNewButton.gridy = 7;
				getContentPane().add(actBtnNewButton, gbc_actBtnNewButton);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 5;
		gbc_lblDescripcion.gridy = 8;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVisible(false);

		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 6;
		gbc_scrollPane_1.gridy = 8;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);

		txtDescripcion = new JTextArea();
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setEditable(false);

		scrollPane_1.setViewportView(txtDescripcion);

		lblInstitucion = new JLabel("Institucion");
		lblInstitucion.setVisible(false);
		GridBagConstraints gbc_lblInstitucion = new GridBagConstraints();
		gbc_lblInstitucion.anchor = GridBagConstraints.EAST;
		gbc_lblInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstitucion.gridx = 1;
		gbc_lblInstitucion.gridy = 9;
		getContentPane().add(lblInstitucion, gbc_lblInstitucion);

		txtInstitucion = new JTextField();
		txtInstitucion.setVisible(false);
		txtInstitucion.setEditable(false);
		GridBagConstraints gbc_txtInstitucion = new GridBagConstraints();
		gbc_txtInstitucion.insets = new Insets(0, 0, 5, 5);
		gbc_txtInstitucion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInstitucion.gridx = 3;
		gbc_txtInstitucion.gridy = 9;
		getContentPane().add(txtInstitucion, gbc_txtInstitucion);
		txtInstitucion.setColumns(10);

		lblUrl = new JLabel("URL");
		lblUrl.setVisible(false);
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.anchor = GridBagConstraints.EAST;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrl.gridx = 1;
		gbc_lblUrl.gridy = 10;
		getContentPane().add(lblUrl, gbc_lblUrl);

		txtUrl = new JTextField();
		txtUrl.setVisible(false);
		txtUrl.setEditable(false);
		GridBagConstraints gbc_txtUrl = new GridBagConstraints();
		gbc_txtUrl.gridwidth = 2;
		gbc_txtUrl.insets = new Insets(0, 0, 5, 5);
		gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUrl.gridx = 3;
		gbc_txtUrl.gridy = 10;
		getContentPane().add(txtUrl, gbc_txtUrl);
		txtUrl.setColumns(10);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				comboUsuarios.removeAllItems();
				hide();
			}
		});
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrar.gridx = 7;
		gbc_btnCerrar.gridy = 11;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
	}

	public void arranque() {
		limpiarFormulario();
		// Remuevo la basura y actualizo datos
		comboUsuarios.removeAllItems();
		this.cargarComboUsuarios();
		this.show();
	}

	private void cargarComboUsuarios() {
		Set<String> users = ctrlUsuario.getListaUsuarios();
		comboUsuarios.addItem(null);
		for (String n : users)
			comboUsuarios.addItem(n);
		comboUsuarios.setSelectedIndex(-1);
	}

	private void mostrarUsuario() {
		// Me aseguro que no salten los datos al cargar el combo
		if (comboUsuarios.getSelectedItem() != null) {
			limpiarFormulario(); // Para reiniciar el estado de visibilidad de los objetos a default
			cargarUsuario((String) comboUsuarios.getSelectedItem());
			String nickname = (String) comboUsuarios.getSelectedItem();

			if (ctrlUsuario.getDatosUsuario(nickname) instanceof DtSocio) {
				comboClasesSocio.setVisible(true);
				lblClases.setVisible(true);
				lblSocio.setVisible(true);
				clasesSociosbtnNewButton.setVisible(true);
				scrollPane_1.setVisible(false);
				scrollPane.setVisible(false);

			} else if (ctrlUsuario.getDatosUsuario(nickname) instanceof DtProfesor) {
				comboClasesProf.setVisible(true);
				comboActividades.setVisible(true);
				txtDescripcion.setVisible(true);
				lblProfesor.setVisible(true);
				lblClases_1.setVisible(true);
				lblActividades.setVisible(true);
				clasesProfeBtnNewButton.setVisible(true);
				actBtnNewButton.setVisible(true);
				scrollPane_1.setVisible(true);
				scrollPane.setVisible(true);
				lblBiografia.setVisible(true);
				lblInstitucion.setVisible(true);
				txtInstitucion.setVisible(true);
				lblUrl.setVisible(true);
				txtUrl.setVisible(true);
			}
		}
	}

	private void cargarUsuario(String usuario) {
		
		// Obtenemos el DtUsuario
		user = ctrlUsuario.getDatosUsuario(usuario);
		
		// Cargamos la ventana con ese DtUsuario
		
		if (user.getImagen() != null && user.getImagen().length > 0) {
			lblImagen.setVisible(true);
			try {
				lblImagen.setIcon(decodificarImagen(user.getImagen()));
				lblImagen.setText(null);
				
			} catch (IOException e) {
				e.printStackTrace();
				lblImagen.setIcon(null);
				lblImagen.setText("[No hay imagen que mostrar]");
			}
		} else { 
			lblImagen.setIcon(null);
			lblImagen.setText("[No hay imagen que mostrar]");
		}
		
		txtNickname.setText(user.getNickname());
		txtNombre.setText(user.getNombre());
		txtApellido.setText(user.getApellido());
		txtEmail.setText(user.getEmail());
		dateFecha.setDate(user.getFechaDeNacimiento());
		dateFecha.setEnabled(false);

		if (user instanceof DtSocio) {
			DtSocio socio = (DtSocio) user;
			this.clases = null;
			Set<String> clases = socio.getClases();
			for (String n : clases)
				comboClasesSocio.addItem(n);
			if (comboClasesSocio.getItemCount() > 0)
				clasesSociosbtnNewButton.setEnabled(true);
		} else {
			DtProfesor prof = (DtProfesor) user;
			txtBio.setText(prof.getBiografia());
			txtDescripcion.setText(prof.getDescripcion());
			txtInstitucion.setText(prof.getInstitucion());
			txtUrl.setText(prof.getUrl());
			this.clases = prof.getClases();
			Set<String> actividades = new HashSet<String>();
			for (String n : clases.keySet()) {
				comboClasesProf.addItem(n);
				actividades.add(clases.get(n));
			}
			Iterator<String> it = actividades.iterator();
			while (it.hasNext()) {
				comboActividades.addItem(it.next());
			}
			if (clases.size() > 0) {
				clasesProfeBtnNewButton.setEnabled(true);
				actBtnNewButton.setEnabled(true);
			}
		}
	}

	// Solo para profesor
	private void verClase(String nomClase) {

		/*
		 * La invocacion mas prolija de ConsultaClase es la que pide Nombre Institucion,
		 * Nombre Actividad y Nombre Clase Hay otra que usa DtClase, pero es rara y no
		 * carga los ComboBox por falta de informacion. Intentamos usar la invocacion
		 * linda antes de la fea, y para eso precisamos el nombre de la actividad.
		 */

		if (this.clases != null) {

			String nomInstProf = txtInstitucion.getText();
			String nomActProf = null;
//			for (Map.Entry<String, String> n : this.clases) {
			for (String n : clases.keySet()) {
				if (n.equals(nomClase)) {
					nomActProf = clases.get(n); // nombre de la actividad
					break; // salir del for-each
				}
			}

			if (!nomInstProf.isEmpty() && !nomActProf.isEmpty()) {
				frameClase.arranqueCargado(nomClase, nomActProf, nomInstProf);
			} else {
				// este else no deberia ejecutarse nunca, peero... no quiero romper nada
				DtClase datosClase = ctrlInstituciones.getDatosDeClase(nomClase);
				frameClase.arranqueCargado(datosClase); // no carga ComboBoxes
			}
		} else {
			// this.clases == null y es un Socio el elegido
			DtClase datosClase = ctrlInstituciones.getDatosDeClase(nomClase);
			frameClase.arranqueCargado(datosClase); // no carga ComboBoxes
		}
	}

	private void verActividad() {
		frameActividad.arranqueConActividad((String) comboActividades.getSelectedItem());
//		frameActividad.setVisible(true);
//		frameActividad.limpiarFormulario();
//		DtProfesor prof = (DtProfesor) user;
//		frameActividad.cargarDatosActividad((String)comboActividades.getSelectedItem(), prof.getInstitucion());
	}

	private void limpiarFormulario() {
		// deshabilito botones
		clasesSociosbtnNewButton.setEnabled(false);
		clasesProfeBtnNewButton.setEnabled(false);
		actBtnNewButton.setEnabled(false);
		clasesSociosbtnNewButton.setVisible(false);
		clasesProfeBtnNewButton.setVisible(false);
		actBtnNewButton.setVisible(false);

		// Hago las labels opcionales invisibles
		lblImagen.setText("[Seleccionar usuario]"); // texto default
		lblImagen.setIcon(null); // sin icono
		lblImagen.setVisible(false); // invisible
		lblSocio.setVisible(false);
		lblClases.setVisible(false);
		lblClases_1.setVisible(false);
		lblProfesor.setVisible(false);
		lblActividades.setVisible(false);
		lblDescripcion.setVisible(false);
		lblUrl.setVisible(false);
		lblBiografia.setVisible(false);
		lblInstitucion.setVisible(false);

		// pongo en null los textos y escondo los de profesor
		txtNickname.setText(null);
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtEmail.setText(null);
		dateFecha.setDate(null);
		txtBio.setText(null);
		txtBio.setVisible(false);
		txtDescripcion.setText(null);
		txtDescripcion.setVisible(false);
		txtInstitucion.setText(null);
		txtInstitucion.setVisible(false);
		txtUrl.setText(null);
		txtUrl.setVisible(false);

		// vacio los combo de clases y actividades
		// Socio
		comboClasesSocio.removeAllItems();
		comboClasesSocio.setVisible(false);
		// Profesor
		comboClasesProf.removeAllItems();
		comboClasesProf.setVisible(false);
		comboActividades.removeAllItems();
		comboActividades.setVisible(false);
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
