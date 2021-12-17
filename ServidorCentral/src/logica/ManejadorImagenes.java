package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class ManejadorImagenes {

	public ManejadorImagenes() {}
	
	public String obtenerRutaBase() {
		Properties defaultProps = new Properties();
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream("/home/" + System.getProperty("user.name") + "/.entrenamosUy/propiedades.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			defaultProps.load(fileIn);
			fileIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ruta = defaultProps.getProperty("rutaBaseImagenes");
		File fileDir = new File(ruta);     
		fileDir.mkdirs();
		return defaultProps.getProperty("rutaBaseImagenes");
	}
	
	public void ingresarImagen(String rutaImagen, byte[] datosImagen) {
		String rutaBase = obtenerRutaBase();
		String ruta = rutaBase + rutaImagen;
		try {
            FileOutputStream imageOutFile = new FileOutputStream(ruta);
            imageOutFile.write(datosImagen);
            imageOutFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] obtenerImagen(String rutaImagen) {
		String rutaBase = obtenerRutaBase();
		String ruta = rutaBase + rutaImagen;
		byte[] res = new byte[]{};
		try {
			res = Files.readAllBytes(Paths.get(ruta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}
