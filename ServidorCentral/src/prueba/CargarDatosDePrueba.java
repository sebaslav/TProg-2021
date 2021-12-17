package prueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import excepciones.ActividadRepetidaException;
import excepciones.CategoriaRepetidaException;
import excepciones.ClaseLlenaException;
import excepciones.ClaseRepetidaException;
import excepciones.CuponeraRepetidaException;
import excepciones.CuponeraYaCompradaException;
import excepciones.InstitucionRepetidaException;
import excepciones.SocioYaRegistradoException;
import excepciones.UsuarioRepetidoException;
import logica.DtClase;
import logica.DtProfesor;
import logica.DtSocio;
import logica.DtUsuario;
import logica.IControladorCuponeras;
import logica.IControladorInstituciones;
import logica.IControladorUsuarios;

@SuppressWarnings("deprecation")
public class CargarDatosDePrueba {
	// Datos de un socio
	// (String nick, [0]
	// String nombre, [1]
	// String apellido, [2]
	// String email, [3]
	// Date fechaNacimiento, [4]
	// Set<String> clase) [5]

	String rutaImagenes = "imagenes/";

	// Socios
	String socioEL = "Emi71";
	String socioCO = "caro";
	String socioEW = "euge";
	String socioGH = "guille";
	String socioSP = "sergiop";
	String socioAR = "andy";
	String socioAP = "tonyp";
	String socioML = "m1k4";
	String socioCB = "charly";
	private Object[][] datosSoc = {
//			{"Nickname","Nombre","Apellido","Email",25,4,1990,contraseña,foto},
//				[0]		[1]			[2]		[3]		[4]	[5][6]	[7]	[8]
//			socioEL
			{ "Emi71", "Emiliano", "Lucas", "emi71@gmail.com", 31, 12, 1971, "asdfg456", "emiliano.jpeg" },

//			socioCO
			{ "caro", "Carolina", "Omega", "caro@gmail.com", 15, 11, 1983, "123rtgfdv", "carolina_omega.jpeg" },

			{ "euge", "Eugenia", "Williams", "e.will@gmail.com", 15, 4, 1990, "poiuy086", "eugenia_williams.jpeg" },
//			socioGH
			{ "guille", "Guillermo", "Hector", "ghector@gmail.com", 15, 5, 1959, "GTO468", "guillermo_hector.jpg" },

//			socioSP
			{ "sergiop", "Sergio", "Perez", "sergi@gmail.com.uy", 28, 01, 1950, "HGF135", "sergio_perez.jpeg" },

//			socioAR
			{ "andy", "Andrés", "Roman", "chino@gmail.org.uy", 17, 03, 1976, "lkj65D", "andres_romano.jpg" },

//			socioAP
			{ "tonyp", "Antonio", "Paz", "eltony@gmail.org.uy", 14, 02, 1955, "jhvf395", "antonio_pacheco_paz.jpg" },

//			socioML
			{ "m1k4", "Micaela", "Lopez", "mika@gmail.com.ar", 23, 2, 1987, "ijngr024", "micaela_lopez.jpeg" },

//			socioCB
			{ "charly", "Carlos", "Boston", "charly@gmail.com.uy", 8, 5, 1937, "987mnbgh", "carlos_boston.jpg" }

	};

	// Profesores
	String profeVP = "viktor";
	String profeDM = "denis";
	String profeCL = "clazar";
	String profeBS = "TheBoss";
	String profeTN = "Nelson";
	String profeLL = "lale";
	String profePI = "prisc";
	String profeDY = "dagost";
	String profeAL = "aldo";
	private Object[][] datosProf = {
//			{"",foto},
//			   [0] 		   [1]	    [2]		  [3]	 [4][5][6]	  [7]		 	[8]	    	[9]   [10]		[11]		12

//			profeVP
			{ "viktor", "vperez@fuerza.com", "Victor", "Perez", 1, 1, 1977,

					"Victor es un apasionado de los músculos. Sus " + "clases son organizadas en función de distintos "
							+ "aparatos y pesas con el objetivo de desarrollar " + "músculos",

					"Fuerza Bruta", "www.vikgym.com",

					"Victor nació en Moscow en 1977. En el año " + "2005 emigró a Uruguay luego de quedar "
							+ "encantado con el país en un viaje turístico.",
					"lkj34df", "victor_perez.jpg" },

//			profeDM
			{ "denis", "den80@fuerza.com", "Denis", "Miguel", 14, 6, 1980,

					"A Denis le interesan los deportes con pelota, " + "principalmente el voleibol y el handball",

					"Telón", "www.depecho.com",

					"Denis fue un jugador de voleibol profesional.", "poke579", "denis_miguel.jpg" },

//			profeCL
			{ "clazar", "claz4r0@hotmail.com", "Carlos", "Lazaro", 22, 6, 1953,

					"Carlos es un profesor muy divertido cuyas clases " + "de aeróbica están cargadas de energía.",

					"Instituto Natural", "www.enforma.com",

					"El interés por la actividad física llevo a " + "Carlos a dejar su trabajo en un estudio "
							+ "contable y abrir su propio gimnasio.",
					"mkji648", "carlos_lazaro.jpg" },
//			profeBS
			{ "TheBoss", "bruceTheBoss@gmail.com", "Bruno", "Sosa", 23, 9, 1949,

					"Bruno es un ex-boxeardor que busca entrenar a " + "futuros campeones.",

					"Fuerza Bruta", "www.bruce.net",

					"Bruno, mejor conocido como Bruce en el " + "ring, compitió como boxeador entre los "
							+ "años 60s y 70s.",
					"fcku0123", "bruno_sosa.jpeg" },

//			profeTN
			{ "Nelson", "nelson@hotmail.com", "Luis", "Nelson", 1, 1, 1998,

					"Profesor de natación. Especializado en braza y mariposa.",

					"Telón", "www.nelson.uy", "", "vbmn4r", "luis_nelson.JPG" },

//			profeLL
			{ "lale", "la_le@outlook.com", "Laura", "Leyes", 14, 2, 1987,

					"Luego de una exitosa carrera como jugadora de " + "futbol profesional. Laura dedica sus clases a "
							+ "enseñar tácticas de futbol",

					"Telón", "www.laley.com",

					"Jugadora profesional de futbol desde 2010 a 2020.", "ncnl123", "laura_leyes.jpg" },

//			profePI
			{ "prisc", "pripa@gmail.com", "Priscila", "Pappo", 13, 8, 1981,

					"Laura tiene un gran interés por los deportes olímpicos.",

					"Olympic", "www.pi314.net", "", "mny101", "" },

//			profeDY
			{ "dagost", "d_1940_ago@gmail.com", "Daiana", "Agostini", 5, 3, 1940,

					"Profesora dedicada y exigente. No acepta un \"no puedo\" como respuesta.",

					"Olympic", "www.dygym.com", "", "1o1vbm", "daiana_agostini.jpeg" },
//			profeAL
			{ "aldo", "aldo@ outlook.com", "Aldo", "Vivaldi", 17, 7, 1952,

					"Dada su gran estatura Aldo siempre jugó al " + "basquetbol, hoy se dedica a enseñarlo.",

					"Telón", "www.sportsaldo.net", "", "ultraton01", "aldo_vivaldi.jpg" }

	};

	// Instituciones
	String instIN = "Instituto Natural";
	String instFB = "Fuerza Bruta";
	String instTL = "Telón";
	String instYT = "Olympic";
	private Object[][] datosIns = {
//			{"NombreInstituto","Descripción","URL"}

//			instIN
			{ "Instituto Natural", "Clases de gimnasia, aeróbica, spinning y yoga.", "https://www.inatural.com" },

//			instFB
			{ "Fuerza Bruta", "Gimnasio especializado en el desarrollo de la musculatura.",
					"https://www.musculos.com/" },

//			instTL
			{ "Telón", "Actividades deportivas para todas las edades.", "https://telon.com.uy" },

//			instYT
			{ "Olympic", "Gimnasia y Aparatos", "https://www.olympic21.com" } };

	// Categorias
	String catC1 = "Al aire libre";
	String catC2 = "Deportes";
	String catC3 = "Fitness";
	String catC4 = "Gimnasia";
	private String[] datosCat = { catC1, catC2, catC3, catC4 };

	// Actividades
	String actA1 = "Aparatos y pesas";
	String actA2 = "Voleibol";
	String actA3 = "Aeróbica";
	String actA4 = "Kickboxing";
	String actA5 = "Atletismo";
	String actA6 = "Basquetbol";
	String actA7 = "Aparatos II";
	String actA8 = "Pilates";
	String actA9 = "Voleibol II";
	String actA10 = "Basquetbol II";
	private Object[][] datosAct = {
//			{"",Aceptada, Imagen(byte[])}
//				0						1				2			3				4		5  6  7						8			9	     10

//			actA1
			{ "Fuerza Bruta", actA1, "Clases de aparatos, pesas y calistenia.", 90, (float) 550, 31, 03, 2021, profeVP,
					true, "act_aparatos_y_pesas.jpg" },

//			actA2
			{ "Telón", actA2, "Voleibol en todas sus formas", 120, (float) 750, 20, 04, 2021, profeDM, true,
					"act_voleibol.jpg" },

//			actA3
			{ "Instituto Natural", actA3, "Para cuidar el aparato cardiovascular", 110, (float) 800, 30, 05, 2021,
					profeCL, true, "act_aerobica.jpg" },

//			actA4
			{ "Fuerza Bruta", actA4, "En busca del nuevo campeón de boxeo.", 100, (float) 980, 7, 6, 2021, profeBS,
					true, "act_kickboxing.jpg" },

//			actA5
			{ "Telón", actA5, "100m , 200m, postas y carreras con obstaculos.", 150, (float) 500, 8, 7, 2021, profeDM,
					true, "act_atletismo.jpeg" },

//			actA6
			{ "Telón", actA6, "Espectáculo conmemorando los 30 años de Violeta.", 80, (float) 450, 31, 7, 2021, profeTN,
					true, "" },

//			actA7	   
			{ instFB, actA7, "Clases de aparatos avanzadas", 60, (float) 1500, 15, 8, 2021, null, false, "" },

			// actA8
			{ instIN, actA8, "El Método Pilates combina diferentes capacidades físicas", 45, (float) 600, 30, 8, 2021,
					profeCL, null, "act_pilates.jpg" },

			// actA9
			{ instTL, actA9, "Voleibol avanzado", 120, (float) 1000, 1, 9, 2021, profeDM, false, "act_voleibol_2.jpg" },

			// actA10
			{ instTL, actA10, "Basquetbol avanzado", 80, (float) 600, 7, 9, 2021, profeDM, null, "" } };

	private String[][] datosCatAct = {
			// 1
			{ catC3 },
			// 2
			{ catC2 },
			// 3
			{ catC4, catC1 },
			// 4
			{ catC2 },
			// 5
			{ catC2 },
			// 6
			{ catC2 },
			// 7
			{ catC3 },
			// 8
			{ catC4 },
			// 9
			{ catC1, catC2 },
			// 10
			{ catC4 } };

	private Object[][] datosSeguidores = {
//			{"NicknameSeguidor","NicknameAQuienSigue"}
//					0					1
//			
			{ socioEL, socioGH }, { socioCO, socioEW }, { socioCO, socioGH }, { socioEW, socioEL },
			{ socioEW, socioCO }, { socioEW, socioML }, { socioGH, socioEL }, { socioGH, socioCO },
			{ socioGH, socioEW }, { socioGH, profeBS }, { socioSP, socioEW }, { socioSP, socioAR },
			{ socioSP, profeCL }, { socioAR, socioCO }, { socioAR, socioAP }, { socioAR, profeCL },
			{ socioAP, socioCO }, { socioAP, socioML }, { socioAP, socioCB }, { socioML, socioSP },
			{ socioML, socioAP }, { socioCB, socioAP }, { socioCB, profeTN }, { profeVP, socioAP },
			{ profeVP, socioML }, { profeVP, profeCL }, { profeVP, profeLL }, { profeVP, profePI },
			{ profeDM, socioEL }, { profeDM, socioCO }, { profeDM, socioEW }, { profeDM, socioGH },
			{ profeDM, socioSP }, { profeDM, socioAR }, { profeDM, socioAP }, { profeDM, socioML },
			{ profeDM, socioCB }, { profeCL, socioCO }, { profeCL, socioEW }, { profeCL, socioGH },
			{ profeCL, profeBS }, { profeBS, socioGH }, { profeBS, socioAR }, { profeBS, socioML },
			{ profeTN, socioEL }, { profeTN, socioAR }, { profeTN, socioAP }, { profeTN, profeLL },
			{ profeTN, profePI }, { profeTN, profeDY }, { profeLL, socioCB }, { profeLL, profeTN },
			{ profePI, socioCB }, { profePI, profeTN }, { profeDY, socioAP }, { profeDY, profeTN },
			{ profeAL, socioAR }, { profeAL, socioAP }, { profeAL, socioCB }, { profeAL, profeLL },
			{ profeAL, profePI }, { profeAL, profeDY } };

	private Object[][] datosCla = {
//			{"", imagen, cantPremios, descPremio, urlVideo}	
//				0					1							2							3		 4   5  6  			  7  8 				9			10			11	 12 13				   14       15         16          17         18

//			C1

			{ "Aparatos y pesas", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"viktor", // Nickname Socio
					"Calistenia", // Nombre Clase
					15, 04, 2021, 15, 30, 1, 5, 31, 03, 2021, "https://www.musculos.com/Calistenia",
					"cla_calistenia.jpg", 0, "No hay", "https://www.youtube.com/watch?v=_IMil1Lj-Z8" },

//			C2
			{ "Aparatos y pesas", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"viktor", // Nickname Socio
					"Peso libre", // Nombre Clase
					01, 05, 2021, 17, 00, 1, 5, 31, 03, 2021, "https://www.musculos.com/pesolibre", "", 0, "No hay",
					"https://www.youtube.com/watch?v=KHFHi6ci9Fg" },

//			C3
			{ "Aparatos y pesas", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"viktor", // Nickname Socio
					"Aparatos", // Nombre Clase
					01, 06, 2021, 18, 00, 1, 7, 31, 03, 2021, "https://www.musculos.com/aparatos", "", 3,
					"Rueda de abdominales", "https://www.youtube.com/watch?v=6YTVPjjm3e8" },

//			C4
			{ "Voleibol", // Nombre Actividad
					"Telón", // Nombre Intituto
					"denis", // Nickname Socio
					"Voleibol", // Nombre Clase
					10, 06, 2021, 19, 00, 10, 21, 20, 04, 2021, "https://telon.com.uy/voley", "cla_voleibol.jpg", 0,
					"No hay", "https://www.youtube.com/watch?v=PwBsJxHFz98" },

//			C5
			{ "Voleibol", // Nombre Actividad
					"Telón", // Nombre Intituto
					"Nelson", // Nickname Socio
					"Braza", // Nombre Clase
					10, 07, 2021, 20, 00, 2, 6, 20, 04, 2021, "https://telon.com.uy/natacionB", "cla_braza.jpg", 0,
					"No hay", "" },

//			C6
			{ "Voleibol", // Nombre Actividad
					"Telón", // Nombre Intituto
					"Nelson", // Nickname Socio
					"Mariposa", // Nombre Clase
					10, 8, 2021, 17, 45, 2, 6, 20, 04, 2021, "https://telon.com.uy/natacionM", "cla_mariposa.jpeg", 2,
					"Lentes natacion", "" },

//			C7
			{ "Aeróbica", // Nombre Actividad
					"Instituto Natural", // Nombre Intituto
					"clazar", // Nickname Socio
					"Aeróbica niños", // Nombre Clase
					15, 8, 2021, 16, 30, 5, 10, 30, 05, 2021, "https://www.inatural.com/aeroni", "", 3,
					"Caramañola infantil", "" },

//			C8
			{ "Aeróbica", // Nombre Actividad
					"Instituto Natural", // Nombre Intituto
					"clazar", // Nickname Socio
					"Aeróbico adulto mayor", // Nombre Clase
					31, 8, 2021, 19, 30, 5, 12, 30, 05, 2021, "https://www.inatural.com/aeroam", "", 0, "No hay", "" },

//			C9
			{ "Aeróbica", // Nombre Actividad
					"Instituto Natural", // Nombre Intituto
					"clazar", // Nickname Socio
					"Aeróbica", // Nombre Clase
					30, 9, 2021, 20, 00, 5, 20, 30, 05, 2021, "https://www.inatural.com/aerogral", "", 2, "Caramañola",
					"" },

//			C10
			{ "Kickboxing", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"TheBoss", // Nickname Socio
					"Boxeo I", // Nombre Clase
					01, 9, 2021, 19, 30, 1, 4, 07, 06, 2021, "https://www.musculos.com/boxeo1", "cla_boxeo_1.jpg", 0,
					"No hay", "https://www.youtube.com/watch?v=-gGx_WqKAh8" },

//			C11
			{ "Kickboxing", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"TheBoss", // Nickname Socio
					"Boxeo II", // Nombre Clase
					30, 9, 2021, 17, 00, 2, 2, 07, 06, 2021, "https://www.musculos.com/boxeo2", "cla_boxeo_2.jpg", 2,
					"Guantillas", "https://www.youtube.com/watch?v=-gGx_WqKAh8" },

//			C12
			{ "Kickboxing", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"viktor", // Nickname Socio
					"Músculos para boxeo", // Nombre Clase
					15, 10, 2021, 20, 00, 1, 5, 07, 06, 2021, "https://www.musculos.com/muscbox",
					"cla_musculos_para_boxeo.jpg", 0, "No hay", "" },

//			C13
			{ "Atletismo", // Nombre Actividad
					"Telón", // Nombre Intituto
					"lale", // Nickname Socio
					"100 M", // Nombre Clase
					25, 9, 2021, 19, 00, 3, 10, 8, 07, 2021, "https://telon.com.uy/100m", "cla_100M.jpg", 0, "No hay",
					"" },

//			C14
			{ "Atletismo", // Nombre Actividad
					"Telón", // Nombre Intituto
					"lale", // Nickname Socio
					"200 M", // Nombre Clase
					5, 11, 2021, 18, 30, 3, 10, 8, 07, 2021, "https://telon.com.uy/200m", "cla_200M.jpg", 0, "No hay",
					"" },

//			C15
			{ "Atletismo", // Nombre Actividad
					"Telón", // Nombre Intituto
					"lale", // Nickname Socio
					"Posta", // Nombre Clase
					29, 11, 2021, 17, 45, 8, 16, 8, 07, 2021, "https://telon.com.uy/posta", "cla_posta.jpg", 0,
					"No hay", "https://www.youtube.com/watch?v=OGs4c0C8jx8" },

//			C16
			{ "Basquetbol", // Nombre Actividad
					"Telón", // Nombre Intituto
					"aldo", // Nickname Socio
					"Basquet I", // Nombre Clase
					03, 11, 2021, 21, 00, 10, 15, 31, 07, 2021, "https://telon.com.uy/bball1", "", 0, "No hay",
					"https://www.youtube.com/watch?v=VMeQ4yRvGrc" },

//			C17
			{ "Basquetbol", // Nombre Actividad
					"Telón", // Nombre Intituto
					"aldo", // Nickname Socio
					"Basquet II", // Nombre Clase
					20, 11, 2021, 21, 00, 10, 10, 31, 07, 2021, "https://telon.com.uy/bball2", "", 5,
					"Juego de muñequeras", "https://www.youtube.com/watch?v=VMeQ4yRvGrc" },

//			C18
			{ "Aparatos y pesas", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"viktor", // Nickname Socio
					"Aparatos II", // Nombre Clase
					30, 11, 2021, 20, 00, 1, 5, 15, 11, 2021, "https://www.musculos.com/aparatos", "", 2,
					"Pesa rusa 5K", "https://www.youtube.com/watch?v=6YTVPjjm3e8" },

//			C19
			{ "Kickboxing", // Nombre Actividad
					"Fuerza Bruta", // Nombre Intituto
					"TheBoss", // Nickname Socio
					"Boxeo III", // Nombre Clase
					01, 12, 2021, 19, 00, 2, 6, 10, 11, 2021, "https://www.musculos.com/boxeo2", "cla_boxeo_3.jpg", 2,
					"Guantillas", "" }

	};
	String cupP1 = "Pelota";
	String cupP2 = "Gimnasia";
	String cupP3 = "Músculos";
	String cupP4 = "Pista";
	private Object[][] datosCup = {
//			{"NombreCuponera","Descripción",(DateInicio)20,2,2020,(DateFin)20,3,2020,descuento(float),(fechaReg)30,1,2020, imagen}
//					0				1					2	3   4  			5	6  7	8						 9	10 11    12

//		cupP1
			{ "Pelota", "Deportes con pelota.", 01, 05, 2021, 31, 07, 2021, (float) 0.8, // 20%
					30, 04, 2021, "cup_pelota.jpeg" },

//		cupP2
			{ "Gimnasia", "Aeróbica y aparatos.", 01, 8, 2021, 30, 9, 2021, (float) 0.7, // 30%
					15, 07, 2021, "cup_gimnasia.jpg" },

//		cupP3
			{ "Músculos", "Pesas.", 15, 8, 2021, 15, 12, 2021, (float) 0.9, // 10%
					18, 07, 2021, "cup_musculos.jpg" },

//		cupP4
			{ "Pista", "Entrenamiento de Atletismo", 01, 10, 2021, 31, 12, 2021, (float) 0.85, // 15%
					01, 9, 2021, "cup_pista.jpg" }

	};

	private Object[][] datosActCup = {
//			{\"NombreInstituto","NombreActividad","NombreCuponera",canClases(int)}
//					0					1				2				 3
//		cupP1 actA2 7
			{ "Telón", "Voleibol", "Pelota", 7 },

//		cupP1 actA6 18
			{ "Telón", "Basquetbol", "Pelota", 18 },

//		cupP2 actA3 2
			{ "Instituto Natural", "Aeróbica", "Gimnasia", 2 },

//		cupP2 actA1 8
			{ "Fuerza Bruta", "Aparatos y pesas", "Gimnasia", 8 },

//		cupP3 actA4 11
			{ "Fuerza Bruta", "Kickboxing", "Músculos", 11 },

//		cupP3 actA1 12
			{ "Fuerza Bruta", "Aparatos y pesas", "Músculos", 12 },

//		cupP4 actA5 20
			{ "Telón", "Atletismo", "Pista", 20 } };

	private Object[][] datosRegCla = {
//			{"NicknameSocio",(fechaReg)30,2,2020,"NombreClase",\"NombreActividad",\"NombreInstituto",cuponera}
//				0			            1   2  3       4            5                  6              7

//		R1
			{ "caro", // Socio : socioCO
					9, 04, 2021, "Calistenia", // Clase : catC1
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R2
			{ "sergiop", // Socio : socioSP
					10, 04, 2021, "Calistenia", // Clase : catC1
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R3
			{ "andy", // Socio :socioAR
					12, 04, 2021, "Calistenia", // Clase : catC1
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R4
			{ "andy", // Socio :socioAR
					15, 04, 2021, "Peso libre", // Clase :catC2
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R5
			{ "tonyp", // Socio :socioAP
					20, 04, 2021, "Peso libre", // Clase :catC2
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R6
			{ "caro", // Socio :socioCO
					25, 04, 2021, "Peso libre", // Clase :catC2
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R7
			{ "m1k4", // Socio :socioML
					28, 04, 2021, "Peso libre", // Clase :catC2
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R8
			{ "charly", // Socio : socioCB
					16, 04, 2021, "Aparatos", // Clase :catC3
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R9
			{ "caro", // Socio :socioCO
					15, 05, 2021, "Aparatos", // Clase :catC3
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R10
			{ "m1k4", // socio :socioML
					20, 05, 2021, "Aparatos", // Clase :catC3
					"Aparatos y pesas", "Fuerza Bruta", null },

//		R11
			{ "Emi71", // socio : socioEL
					05, 05, 2021, "Voleibol", // Clase :catC4
					"Voleibol", "Telón", null },

//		R12
			{ "euge", // socio : socioEW
					10, 05, 2021, "Voleibol", // Clase :catC4
					"Voleibol", "Telón", null },

//		R13
			{ "sergiop", // Socio : socioSP
					15, 05, 2021, "Voleibol", // Clase :catC4
					"Voleibol", "Telón", null },

//		R14
			{ "tonyp", // Socio :socioAP
					20, 05, 2021, "Voleibol", // Clase :catC4
					"Voleibol", "Telón", null },

//		R15
			{ "guille", // Socio : socioGH
					8, 06, 2021, "Braza", // Clase :C5
					"Voleibol", "Telón", null },

//		R16
			{ "euge", // socio : socioEW
					13, 06, 2021, "Braza", // Clase :C5
					"Voleibol", "Telón", null },

//		R17
			{ "m1k4", // socio :socioML
					25, 06, 2021, "Braza", // Clase :C5
					"Voleibol", "Telón", null },

//		R18
			{ "charly", // Socio : socioCB
					05, 07, 2021, "Mariposa", // Clase :C6
					"Voleibol", "Telón", null },

//		R19
			{ "sergiop", // Socio : socioSP
					11, 07, 2021, "Mariposa", // Clase :C6
					"Voleibol", "Telón", null },

//		R20
			{ "andy", // Socio :socioAR
					18, 07, 2021, "Mariposa", // Clase :C6
					"Voleibol", "Telón", null },

//		R21
			{ "m1k4", // socio :socioML
					01, 8, 2021, "Aeróbica niños", // Clase :C7
					"Aeróbica", "Instituto Natural", cupP2 },

//		R22
			{ "Emi71", // socio : socioEL
					17, 8, 2021, "Aeróbico adulto mayor", // Clase :C8
					"Aeróbica", "Instituto Natural", null },

//		R23
			{ "guille", // Socio : socioGH
					20, 8, 2021, "Aeróbico adulto mayor", // Clase :C8
					"Aeróbica", "Instituto Natural", null },

//		R24
			{ "andy", // Socio :socioAR
					23, 8, 2021, "Aeróbico adulto mayor", // Clase :C8
					"Aeróbica", "Instituto Natural", null },

//		R25
			{ "caro", // Socio :socioCO
					15, 8, 2021, "Aeróbica", // Clase :C9
					"Aeróbica", "Instituto Natural", cupP2 },

//		R26
			{ "euge", // socio : socioEW
					26, 8, 2021, "Aeróbica", // Clase :C9
					"Aeróbica", "Instituto Natural", null },

//		R27
			{ "andy", // Socio :socioAR
					19, 07, 2021, "Boxeo I", // Clase :C10
					"Kickboxing", "Fuerza Bruta", cupP3 },

//		R28
			{ "tonyp", // Socio :socioAP
					16, 8, 2021, "Boxeo I", // Clase :C10
					"Kickboxing", "Fuerza Bruta", null },

//		R29
			{ "m1k4", // socio :socioML
					24, 8, 2021, "Boxeo I", // Clase :C10
					"Kickboxing", "Fuerza Bruta", null },

//		R30
			{ "sergiop", // Socio : socioSP
					01, 8, 2021, "Boxeo II", // Clase :C11
					"Kickboxing", "Fuerza Bruta", cupP3 },
//		R31
			{ "guille", // Socio : socioGH
					30, 8, 2021, "Boxeo II", // Clase :C11
					"Kickboxing", "Fuerza Bruta", null },

//		R32
			{ "Emi71", // socio : socioEL
					16, 8, 2021, "Músculos para boxeo", // Clase :C12
					"Kickboxing", "Fuerza Bruta", null },

//		R33
			{ "caro", // Socio : socioCO
					16, 8, 2021, "Músculos para boxeo", // Clase :C12
					"Kickboxing", "Fuerza Bruta", null },

//		R34
			{ "euge", // socio : socioEW
					01, 9, 2021, "Músculos para boxeo", // Clase :C12
					"Kickboxing", "Fuerza Bruta", null },

//		R35
			{ "sergiop", // Socio : socioSP
					05, 9, 2021, "Músculos para boxeo", // Clase :C12
					"Kickboxing", "Fuerza Bruta", null },

//		R36
			{ "guille", // Socio : socioGH
					16, 8, 2021, "100 M", // Clase :C13
					"Atletismo", "Telón", null },

//		R37
			{ "charly", // Socio : socioCB
					03, 9, 2021, "100 M", // Clase :C13
					"Atletismo", "Telón", null },

//		R38
			{ "Emi71", // socio : socioEL
					16, 8, 2021, "200 M", // Clase :C14
					"Atletismo", "Telón", null },

//		R39
			{ "charly", // Socio : socioCB
					06, 9, 2021, "200 M", // Clase :C14
					"Atletismo", "Telón", null },

//		R40
			{ "caro", // Socio : socioCO
					01, 9, 2021, "Posta", // Clase :C15
					"Atletismo", "Telón", cupP4 },

//		R41
			{ "sergiop", // Socio : socioSP
					16, 8, 2021, "Basquet I", // Clase :C16
					"Basquetbol", "Telón", null },

//		R42
			{ "Emi71", // socio : socioEL
					20, 8, 2021, "Basquet I", // Clase :C16
					"Basquetbol", "Telón", null },

//		R43
			{ "tonyp", // Socio :socioAP
					31, 8, 2021, "Basquet I", // Clase :C16
					"Basquetbol", "Telón", null },

//		R44
			{ "andy", // Socio :socioAR
					16, 8, 2021, "Basquet II", // Clase :C17
					"Basquetbol", "Telón", null },

//		R45
			{ "tonyp", // Socio :socioAP
					20, 8, 2021, "Basquet II", // Clase :C17
					"Basquetbol", "Telón", null },

//		R46
			{ "caro", // Socio : socioCO
					02, 9, 2021, "Basquet II", // Clase :C17
					"Basquetbol", "Telón", null },

	};

	private Object[][] datosCompra = { { socioGH, cupP1, 12, 12, 2012 }, { socioML, cupP2, 12, 12, 2012 },
			{ socioCO, cupP2, 12, 12, 2012 }, { socioSP, cupP3, 12, 12, 2012 }, { socioAR, cupP3, 12, 12, 2012 },
			{ socioEL, cupP1, 12, 12, 2012 }, { socioCO, cupP4, 12, 12, 2012 } };

	private Object[][] datosValoraciones = { { socioEL, "Voleibol", 4 }, { socioEL, "Aeróbico adulto mayor", 5 },
			{ socioCO, "Calistenia", 4 }, { socioCO, "Peso libre", 5 }, { socioCO, "Aparatos", 4 },
			{ socioGH, "Braza", 5 }, { socioGH, "Aeróbico adulto mayor", 3 }, { socioSP, "Mariposa", 4 },
			{ socioAR, "Mariposa", 2 }, { socioAR, "Boxeo I", 4 }, { socioML, "Aeróbica niños", 5 },
			{ socioML, "Boxeo I", 5 } };

	private Object[][] datosFavoritas = { { socioEL, actA2 }, { socioCO, actA5 }, { socioCO, actA1 },
			{ socioGH, actA4 }, { socioSP, actA1 }, { socioAR, actA2 }, { socioAR, actA4 }, { socioML, actA2 },
			{ socioML, actA4 } };

	private Object[][] datosSorteos = { { "Aparatos", 2, 6, 2021, "charly", "caro", "m1k4" },
			{ "Mariposa", 11, 8, 2021, "charly", "sergiop" }, { "Aeróbica niños", 16, 8, 2021, "m1k4" },
			{ "Aeróbica", 1, 10, 2021, "caro", "euge" }, { "Boxeo II", 1, 10, 2021, "sergiop", "guille" } };

	private IControladorUsuarios ICU;
	private IControladorInstituciones ICI;
	private IControladorCuponeras ICC;

	public CargarDatosDePrueba(IControladorUsuarios ICU, IControladorInstituciones ICI, IControladorCuponeras ICC) {
		// guardar las interfaces en variables locales
		this.ICU = ICU;
		this.ICI = ICI;
		this.ICC = ICC;
		cargarDatosInstituciones();
		cargarDatosSocios();
		cargarDatosProfesores();
		cargarSeguidores();
		cargarCategorias();
		cargarDatosActividades();

		cargarDatosClases();

		cargarDatosCuponeras();

		cargarDatosRegistosClases();

		cargarDatosActividadesDeCuponeras();

		cargarCompraCuponeras();

		cargarValoraciones();

		cargarFavoritas();

		cargarSociosPremiados();

		cargarFinalizadas();

	}

	private void cargarCompraCuponeras() {
		for (Object[] dato : datosCompra) {
			try {
				Date fecha = new Date();
				ICU.comprarCuponera((String) dato[0], (String) dato[1], fecha);
			} catch (CuponeraYaCompradaException e) {
				e.printStackTrace();
			}
		}
	}

	private void cargarDatosSocios() {

		for (Object[] dato : datosSoc) {
			Date fechaNacimiento = new Date((int) dato[6] - 1900, ((int) dato[5]) - 1, (int) dato[4]);

			String nombreFoto = (String) dato[8];
			nombreFoto = this.rutaImagenes + nombreFoto; // concatenar ruta

			try {

				InputStream is = this.getClass().getClassLoader().getResourceAsStream(nombreFoto);

				byte[] fotoByteArray = IOUtils.toByteArray(is);

				is.close();

				/*
				 * byte[] fotoByteArray = Base64.getDecoder().decode(imagenPronta);
				 */
				DtUsuario dtUsuario = new DtSocio((String) dato[0], (String) dato[1], (String) dato[2],
						(String) dato[3], fechaNacimiento, (String) dato[7], fotoByteArray, null, null, null, null,
						null, null);

				ICU.registrarUsuario(dtUsuario);
			} catch (UsuarioRepetidoException e) {

				e.printStackTrace();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	// cargarDatosProfesores después que cargarDatosInstituciones
	private void cargarDatosProfesores() {
		for (Object[] dato : datosProf) {
			Date fechaNacimiento = new Date((int) dato[6] - 1900, (int) dato[5] - 1, (int) dato[4]);

			String nombreFoto = (String) dato[12];
			nombreFoto = this.rutaImagenes + nombreFoto; // concatenar ruta
			try {

				InputStream is = this.getClass().getClassLoader().getResourceAsStream(nombreFoto);

				byte[] fotoByteArray = IOUtils.toByteArray(is);

				is.close();

				/*
				 * byte[] fotoByteArray = Base64.getDecoder().decode((String) dato[12]);
				 */
				DtUsuario dtUsuario = new DtProfesor((String) dato[0], (String) dato[2], (String) dato[3],
						(String) dato[1], fechaNacimiento, (String) dato[11], fotoByteArray, null, null,
						(String) dato[7], (String) dato[8], (String) dato[9], (String) dato[10], null, null, null);

				ICU.registrarUsuario(dtUsuario);
			} catch (UsuarioRepetidoException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void cargarSeguidores() {
		for (Object[] dato : datosSeguidores) {
			ICU.seguirUsuario((String) dato[0], (String) dato[1]);
		}
	}

	private void cargarDatosInstituciones() {
		for (Object[] dato : datosIns) {
			try {
				ICI.registrarInstitucionDeportiva((String) dato[0], (String) dato[1], (String) dato[2]);
			} catch (InstitucionRepetidaException e) {
				e.printStackTrace();
			}
		}
	}

	// cargarDatosActividades después que cargarDatosInstituciones
	private void cargarDatosActividades() {
		int contador = 0;

		for (Object[] dato : datosAct) {

			Date fechaRegistro = new Date((int) dato[7] - 1900, (int) dato[6] - 1, (int) dato[5]);

			String nombreFoto = (String) dato[10];
			nombreFoto = this.rutaImagenes + nombreFoto; // concatenar ruta
			try {

				InputStream is = this.getClass().getClassLoader().getResourceAsStream(nombreFoto);

				byte[] fotoByteArray = IOUtils.toByteArray(is);

				is.close();

				Set<String> cat = new HashSet<String>();
				String[] aIterar = datosCatAct[contador];

				contador++;
				for (String catDato : aIterar) {
					cat.add(catDato);
				}

				/*
				 * byte[] fotoByteArray = Base64.getDecoder().decode((String) dato[10]);
				 */

				if (dato[8] == null) {
					ICI.registrarActividad((String) dato[0], cat, (String) dato[1], null, (String) dato[2],
							(int) dato[3], (float) dato[4], fechaRegistro, fotoByteArray);
				} else {
					ICI.registrarActividad((String) dato[0], cat, (String) dato[1], (String) dato[8], (String) dato[2],
							(int) dato[3], (float) dato[4], fechaRegistro, fotoByteArray);
				}
				if (dato[9] != null)
					ICI.aceptarActividad((String) dato[1], (boolean) dato[9]);
			} catch (ActividadRepetidaException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// cargarDatosClases después que cargarDatosInstituciones
	private void cargarDatosClases() {
		for (Object[] dato : datosCla) {

			Date fechaClase = new Date((int) dato[6] - 1900, (int) dato[5] - 1, (int) dato[4], (int) dato[7],
					(int) dato[8]);
			Date fechaRegistro = new Date((int) dato[13] - 1900, (int) dato[12] - 1, (int) dato[11]);

			String nombreFoto = (String) dato[15];
			nombreFoto = this.rutaImagenes + nombreFoto; // concatenar ruta
			try {

				InputStream is = this.getClass().getClassLoader().getResourceAsStream(nombreFoto);

				byte[] fotoByteArray = IOUtils.toByteArray(is);

				is.close();

				/*
				 * byte[] fotoByteArray = Base64.getDecoder().decode((String) dato[15]);
				 */
				DtClase dtClase = new DtClase((String) dato[3], fechaClase, (int) dato[10], (int) dato[9],
						fechaRegistro, (String) dato[14], fotoByteArray, null, null, (String) dato[18],
						(String) dato[17], (int) dato[16], null, 0, null, false);

				ICI.registrarClase((String) dato[0], (String) dato[1], (String) dato[2], dtClase);
			} catch (ClaseRepetidaException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//			{"Nickname",(fechaReg)2020,2,30,"NombreClase","NombreActividad","NombreInstituto"}
//				0			        1  2  3      4		         5               6
	private void cargarDatosRegistosClases() {
		for (Object[] dato : datosRegCla) {
			Date fechaReg = new Date((int) dato[3] - 1900, (int) dato[2] - 1, (int) dato[1]);
			try {
				ICU.registrarSocioAClase((String) dato[0], (String) dato[7], fechaReg, (String) dato[4],
						(String) dato[5], (String) dato[6]);
			} catch (SocioYaRegistradoException | ClaseLlenaException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * (Carga de datos) Esta operacion se usa para setear participantes como
	 * ganadores de premios, sin que sea aleatorio.
	 */
	private void cargarSociosPremiados() {
		for (Object[] dato : datosSorteos) {
			Set<String> ganadores = new HashSet<>();
			for (int i = 4; i < dato.length; i++) {
				ganadores.add((String) dato[i]);
			}
			Date fechaSorteo = new Date((int) dato[3] - 1900, (int) dato[2] - 1, (int) dato[1]);
			ICI.sortearPremioForzado((String) dato[0], ganadores, fechaSorteo);
		}
	}

	private void cargarDatosCuponeras() {
		for (Object[] dato : datosCup) {
			Date inicio = new Date((int) dato[4] - 1900, (int) dato[3] - 1, (int) dato[2]);
			Date fin = new Date((int) dato[7] - 1900, (int) dato[6] - 1, (int) dato[5]);
			Date fechaRegistro = new Date((int) dato[11] - 1900, (int) dato[10] - 1, (int) dato[9]);

			String nombreFoto = (String) dato[12];
			nombreFoto = this.rutaImagenes + nombreFoto; // concatenar ruta
			try {

				InputStream is = this.getClass().getClassLoader().getResourceAsStream(nombreFoto);

				byte[] fotoByteArray = IOUtils.toByteArray(is);

				is.close();

				/*
				 * byte[] fotoByteArray = Base64.getDecoder().decode((String) dato[12]);
				 */
				ICC.registrarCuponera((String) dato[0], (String) dato[1], inicio, fin, (float) dato[8], fechaRegistro,
						fotoByteArray);
			} catch (CuponeraRepetidaException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void cargarDatosActividadesDeCuponeras() {
		for (Object[] dato : datosActCup) {
			ICC.agregarActividadACuponera((String) dato[0], (String) dato[1], (String) dato[2], (int) dato[3]);
		}
	}

	private void cargarCategorias() {
		try {
			for (String dato : datosCat) {
				ICI.registrarCategoria(dato);
			}
		} catch (CategoriaRepetidaException e) {
			e.printStackTrace();
		}
	}

	private void cargarValoraciones() {
		for (Object[] dato : datosValoraciones) {
			ICU.valorarClase((String) dato[0], (String) dato[1], (int) dato[2]);
		}
	}

	private void cargarFavoritas() {
		for (Object[] dato : datosFavoritas) {
			ICU.cambiarActividadFavorita((String) dato[0], (String) dato[1]);
		}
	}

	private void cargarFinalizadas() {
		ICI.finalizarActividad("Voleibol");
	}
}
