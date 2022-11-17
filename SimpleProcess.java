/** 
	Esta clase representa la definicion abstracta de un proceso simple, el
        cual solo se compone de un id o numero de identificacion. Todas las clases 
        que representen tipos de procesos mas complejos deben heredar de esta clase

	@author Ing. Andrea Quan
**/
public abstract class SimpleProcess {
	/** El campo id es un entero que identifica al proceso, los ids son correlativos por politica
        **/
	protected int id;
	///////
	protected int time;

	protected String nombre;

	public   boolean isFree;
	
	public static int  AritmethicTime;
	public static int  ConditionalTime;
	public static int IOTime;
	public static int IterativoTime;


	/**
		Inicializa el SimpleProcess con un id especifico.
		@param id representa el id que se le asigna al SimpleProcess
	**/	
	public SimpleProcess(int id, int time){
		this.id = id;
		this.time= time;
		this.isFree= true;
		
	}
        /**
		Devuelve el id del SimpleProcess
		@return devuelve el entero que representa el id del proceso
	**/	
	public final int getId() {
		return this.id;
	}
        /**
		Formato imprimible para objetos SimpleProcess
		@return devuelve un String de la forma [id:id_del_proceso]
	**/	
	public String toString() {
		return "[id:"+ this.id+"]";
	}

	/*
	 * Proceso que devolvera si esta atendido un metodo
	 */

	 public boolean  isFinished(){
		if (time>0) {
			return false;
		}
		else{
			return true;	
		}  
	 }



}