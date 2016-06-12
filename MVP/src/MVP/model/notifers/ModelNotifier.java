package MVP.model.notifers;

public abstract class ModelNotifier {
	protected String message ;
	protected Object object ;
	
	public ModelNotifier(String message,Object obj) {
		this.message = message;
		this.object = obj;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return this.object;
	}
	public void setObj(Object obj) {
		this.object= obj;
	}
	
}
