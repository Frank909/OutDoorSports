package outdoorapp.services;

public abstract class AbstractService implements Service{

	private static boolean flag = false;
	
	protected boolean getFlag(){
		return flag;
	}
	
	protected void setFlag(boolean flag){
		AbstractService.flag = flag;
	}

}
