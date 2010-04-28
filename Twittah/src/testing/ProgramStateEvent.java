package testing;

import java.util.EventObject;

public class ProgramStateEvent extends EventObject {
    private ProgramState _state;
    
    public ProgramStateEvent( Object source, ProgramState state ) {
        super( source );
        _state = state;
    }
    public ProgramState state() {
        return _state;
    }
}
