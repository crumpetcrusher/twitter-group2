package ThreadingHelpers;

//No need to look here
//Allows for object to be passed in that called the state.
public class ProgramStateEvent extends java.util.EventObject {
    private ProgramState _state;
    
    public ProgramStateEvent( Object source, ProgramState state ) {
        super( source );
        _state = state;
    }
    public ProgramState state() {
        return _state;
    }
}
