package principal.log;

public class Log {
	private static String log = "";
	private static volatile String buffer = "";
	
	
	public Log() {
		
	}
	
	public void actualizar(){
		if (!buffer.equals("")){
			System.out.println(buffer);
			log += buffer;
			buffer = "";
		}
	}
	
	public static void addMensaje(final String msg){
		buffer += msg + "\n";
	}

}
