package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import presentacion.*;
import prueba.CargarDatosDePrueba;
import servidor.Publicador;

import javax.swing.JDesktopPane;
import logica.Fabrica;
import logica.IControladorCuponeras;
import logica.IControladorInstituciones;
import logica.IControladorRegistros;
import logica.IControladorUsuarios;

public class Principal {

	private JFrame principalFrame;

	// Declaracion de JInternalFrames, para uso en eventos
	private AgregarActividadACuponera agregarActividadACuponeraFrame;
	private AltaActividad altaActividadFrame;
	private AltaClase altaClaseFrame;
	private AltaUsuario altaUsuarioFrame;
	private ConsultaActividad consultaActividadFrame;
	private ConsultaClase consultaClaseFrame;
	private ConsultaCuponera consultaCuponeraFrame;
	private ConsultaDeUsuario consultaUsuarioFrame;
	private CrearCuponera crearCuponeraFrame;
	private ModificarUsuario modificarUsuarioFrame;
	private RegistroAClase registroAClaseFrame;
	private IControladorUsuarios iUsuarios;
	private IControladorInstituciones iInstituciones;
	private CargarDatosDePrueba datosDePrueba;
	private JMenuItem botonDatosPrueba;
	private IControladorCuponeras iCuponeras;
	private AltaCategoria altaCategoriaFrame;
	private AceptarRechazarActividad aceptarRechazarActividadFrame;
	private RegistroDeAccesos registroDeAccesosFrame;
	private IControladorRegistros iRegistros;
	/**
	 * Launch the application.
	 */
	// prueba.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Publicador p = new Publicador();
					p.publicar();
					Principal window = new Principal();
					window.principalFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	// NO IMP
	private void mostrarAgregarActividadACuponeraFrame() {
		this.agregarActividadACuponeraFrame.show();
		agregarActividadACuponeraFrame.arranque();
	}

	private void mostrarAltaActividadFrame() {
		this.altaActividadFrame.arranque();
	}
	
	private void mostrarAltaCategoriaFrame() {
		this.altaCategoriaFrame.show();
		altaCategoriaFrame.arranque();
	}

	private void mostrarAltaClaseFrame() {
		this.altaClaseFrame.show();
		altaClaseFrame.arranque();
	}

	private void mostrarAltaUsuarioFrame() {
		this.altaUsuarioFrame.show(); // no es lo mismo que setVisible(true)
		altaUsuarioFrame.arranque();
	}

	private void mostrarConsultaActividadFrame() {
		consultaActividadFrame.arranque();
	}

	private void mostrarConsultaClaseFrame() {
		consultaClaseFrame.arranque();
	}

	// NO IMP
	private void mostrarConsultaCuponeraFrame() {
		this.consultaCuponeraFrame.arranque();
	}

	private void mostrarConsultaUsuarioFrame() {
		consultaUsuarioFrame.arranque();
	}

	// NO IMP
	private void mostrarCrearCuponeraFrame() {
		this.crearCuponeraFrame.show();
	}

	// NO IMP
	private void mostrarModificarUsuarioFrame() {
		this.modificarUsuarioFrame.arranque();
	}

	private void mostrarRegistroAClaseFrame() {
		this.registroAClaseFrame.arranque();
	}
	
	private void mostrarAceptarRechazar() {
		this.aceptarRechazarActividadFrame.arranque();
		this.aceptarRechazarActividadFrame.show();
	}
	
	private void mostrarRegistroDeAccesosFrame() {
		this.registroDeAccesosFrame.arranque();
		this.registroDeAccesosFrame.show();
	}
	
	private void cargarLosDatosDePrueba() {
		
		try {
			botonDatosPrueba.setEnabled(false);
			datosDePrueba = new CargarDatosDePrueba(iUsuarios, iInstituciones, iCuponeras);
			JOptionPane.showMessageDialog(null,"Datos cargados exitosamente","Cargar datos de prueba", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			botonDatosPrueba.setEnabled(true);
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		principalFrame = new JFrame();
		JDesktopPane jdp = new JDesktopPane();
		principalFrame.setContentPane(jdp);
		principalFrame.setTitle("Tarea 1");
		principalFrame.setBounds(100, 100, 878, 810);
		principalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		principalFrame.setJMenuBar(menuBar);

		JMenu mnRegistro = new JMenu("Usuarios");
		menuBar.add(mnRegistro);

		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo usuario");
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAltaUsuarioFrame();
			}
		});

		mnRegistro.add(mntmNuevoUsuario);

		JMenuItem mntmConsultarUsuario = new JMenuItem("Consultar usuario");
		mntmConsultarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarConsultaUsuarioFrame();
			}
		});
		mnRegistro.add(mntmConsultarUsuario);

		JMenuItem mntmModificarUsuario = new JMenuItem("Modificar usuario");
		mntmModificarUsuario.setEnabled(true);
		mntmModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarModificarUsuarioFrame();
			}
		});
		mnRegistro.add(mntmModificarUsuario);

		JMenu mnActividades = new JMenu("Actividades");
		menuBar.add(mnActividades);

		JMenuItem mntmNuevaActividad = new JMenuItem("Nueva actividad");
		mntmNuevaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAltaActividadFrame();
			}
		});
		mnActividades.add(mntmNuevaActividad);

		JMenuItem mntmConsultarActividad = new JMenuItem("Consultar actividad");
		mntmConsultarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarConsultaActividadFrame();
			}
		});
		
		JMenuItem mntmAceptarrechazarActividad = new JMenuItem("Aceptar/Rechazar actividad");
		mntmAceptarrechazarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAceptarRechazar();
			}
		});
		mnActividades.add(mntmAceptarrechazarActividad);
		mnActividades.add(mntmConsultarActividad);

		JMenu mnConsulta = new JMenu("Clases");
		menuBar.add(mnConsulta);

		JMenuItem mntmNuevaClase = new JMenuItem("Nueva clase");
		mntmNuevaClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAltaClaseFrame();
			}
		});
		mnConsulta.add(mntmNuevaClase);

		JMenuItem mntmConsultarClase = new JMenuItem("Consultar clase");
		mntmConsultarClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarConsultaClaseFrame();
			}
		});
		mnConsulta.add(mntmConsultarClase);

		JMenuItem mntmRegistrarAClase = new JMenuItem("Registrar socio a clase");
		mntmRegistrarAClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarRegistroAClaseFrame();
			}
		});
		mnConsulta.add(mntmRegistrarAClase);

		JMenu mnCuponeras = new JMenu("Cuponeras");
		menuBar.add(mnCuponeras);

		JMenuItem mntmCrearCuponera = new JMenuItem("Crear cuponera");
//		mntmCrearCuponera.setEnabled(false);
		mntmCrearCuponera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarCrearCuponeraFrame();
			}
		});
		mnCuponeras.add(mntmCrearCuponera);

		JMenuItem mntmConsultarCuponera = new JMenuItem("Consultar cuponera");
		mntmConsultarCuponera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarConsultaCuponeraFrame();
			}
		});
		mnCuponeras.add(mntmConsultarCuponera);

		JMenuItem mntmAgregarActividadA = new JMenuItem("Agregar actividad a cuponera");
		mntmAgregarActividadA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAgregarActividadACuponeraFrame();
			}
		});
		mnCuponeras.add(mntmAgregarActividadA);
		
		JMenu mnCategorias = new JMenu("Categorias");
		menuBar.add(mnCategorias);
		
		JMenuItem mntmAltacategoria = new JMenuItem("Nueva categoria");
		mntmAltacategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAltaCategoriaFrame();
			}
		});
		mnCategorias.add(mntmAltacategoria);

		JMenu mnOtros = new JMenu("Otros");
		menuBar.add(mnOtros);

		botonDatosPrueba = new JMenuItem("Cargar datos prueba");
		botonDatosPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarLosDatosDePrueba();
			}
		});
		mnOtros.add(botonDatosPrueba);
		
		JMenuItem mntmRegistrosDeAcceso = new JMenuItem("Registros de acceso al sitio");
		mntmRegistrosDeAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarRegistroDeAccesosFrame();
			}
		});
		mnOtros.add(mntmRegistrosDeAcceso);

		// Fabrica
		Fabrica fab = Fabrica.getInstance();
		iUsuarios = fab.getControladorUsuario();
		iInstituciones = fab.getControladorInstituciones();
		iCuponeras = fab.getControladorCuponeras();
		iRegistros = fab.getControladorRegistros();

		// JInternalFrame: CU Alta Usuario
		this.altaUsuarioFrame = new AltaUsuario(iUsuarios, iInstituciones);
//		this.altaUsuarioFrame.setSize(365, 240);
//		this.altaUsuarioFrame.setLocation(82, 29);
		this.principalFrame.getContentPane().add(altaUsuarioFrame);
		this.altaUsuarioFrame.hide();

		// JInternalFrame: CU Agregar Actividad a Cuponera
		this.agregarActividadACuponeraFrame = new AgregarActividadACuponera(iCuponeras,iInstituciones);
		this.agregarActividadACuponeraFrame.setSize(774, 285);
		this.agregarActividadACuponeraFrame.setLocation(32, 23);
		this.principalFrame.getContentPane().add(agregarActividadACuponeraFrame);
		this.agregarActividadACuponeraFrame.hide();

		// JInternalFrame: CU Alta Actividad
		altaActividadFrame = new AltaActividad(iInstituciones);
		this.principalFrame.getContentPane().add(altaActividadFrame);
		altaActividadFrame.hide();

		// JInternalFrame: CU Alta Clase
		altaClaseFrame = new AltaClase(iInstituciones);
		altaClaseFrame.setLocation(32, 102);
		this.principalFrame.getContentPane().add(altaClaseFrame);
		altaClaseFrame.hide();
		// JInternalFrame: CU Alta Actividad
		//Lo cambie de lugar a ver si asi si funca
		consultaClaseFrame = new ConsultaClase(iInstituciones);
		this.principalFrame.getContentPane().add(consultaClaseFrame);
		consultaClaseFrame.hide();
		
		
		// JInternalFrame: CU Consultar clase
		consultaActividadFrame = new ConsultaActividad(iInstituciones,consultaClaseFrame,consultaCuponeraFrame);
		this.principalFrame.getContentPane().add(consultaActividadFrame);
		consultaActividadFrame.hide();
		
		
		// JInternalFrame: CU Consultar Cuponera
		consultaCuponeraFrame = new ConsultaCuponera(iCuponeras, consultaActividadFrame);
		this.principalFrame.getContentPane().add(consultaCuponeraFrame);
		consultaCuponeraFrame.hide();
		
		consultaActividadFrame.setConsultaCuponeraFrame(consultaCuponeraFrame);
	
		
		

		
		

		

		// JInternalFrame: CU Consultar usuario
		consultaUsuarioFrame = new ConsultaDeUsuario(iUsuarios, iInstituciones, (ConsultaClase)consultaClaseFrame,
														(ConsultaActividad)consultaActividadFrame);
		consultaUsuarioFrame.setLocation(12, 96);
		this.principalFrame.getContentPane().add(consultaUsuarioFrame);
		consultaUsuarioFrame.hide();

		// JInternalFrame: CU Crear Cuponera
		crearCuponeraFrame = new CrearCuponera(iCuponeras);
		this.principalFrame.getContentPane().add(crearCuponeraFrame);
		crearCuponeraFrame.hide();

		// JInternalFrame: CU Modificar Usuarios
		modificarUsuarioFrame = new ModificarUsuario(fab.getControladorUsuario());
		this.principalFrame.getContentPane().add(modificarUsuarioFrame);
		modificarUsuarioFrame.hide();

		// JInternalFrame: CU Registro a clase
		registroAClaseFrame = new RegistroAClase(iInstituciones, iUsuarios,iCuponeras);
		this.principalFrame.getContentPane().add(registroAClaseFrame);
		registroAClaseFrame.hide();

		// JInternalFrame: CU Registro a clase
		altaCategoriaFrame = new AltaCategoria(iInstituciones);
		this.principalFrame.getContentPane().add(altaCategoriaFrame);
		altaCategoriaFrame.hide();
		
		aceptarRechazarActividadFrame = new AceptarRechazarActividad(iInstituciones);
		this.principalFrame.getContentPane().add(aceptarRechazarActividadFrame);
		aceptarRechazarActividadFrame.hide();
		
		// JInternalFrame: CU Registros de accesos al sitio
		this.registroDeAccesosFrame = new RegistroDeAccesos(iRegistros);
		this.principalFrame.getContentPane().add(registroDeAccesosFrame);
		registroDeAccesosFrame.hide();
	}
}
