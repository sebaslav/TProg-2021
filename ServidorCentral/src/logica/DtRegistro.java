package logica;

import java.util.Date;

public class DtRegistro {
	
	private String dirIp;
	private String url;
	private String browser;
	private String sisOp;
	private Date fecha;
	
	public DtRegistro() {}
	
	public DtRegistro(String dirIp, String url, String browser, String sisOp, Date fecha) {
		this.dirIp = dirIp;
		this.url = url;
		this.browser = browser;
		this.sisOp = sisOp;
		this.fecha = fecha;
	}
	
	public String getIp() {
		return dirIp;
	}
	
	public void setIp(String dirIp) {
		this.dirIp = dirIp;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getBrowser() {
		return browser;
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public String getSisOp() {
		return sisOp;
	}
	
	public void setSisOp(String sisOp) {
		this.sisOp = sisOp;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
