package ThreadingHelpers;


//No need to look here
//Interface for classes to listen with
public interface ProgramStateListener {
	public void stateReceived( ProgramStateEvent event );
}
