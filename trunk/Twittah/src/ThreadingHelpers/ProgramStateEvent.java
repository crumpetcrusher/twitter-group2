//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// Class Name           : ProgramStateEvent
//    
// Authors              : Scott Smiesko, Rick Humes
// Date                 : 2010-30-04
//
//
// DESCRIPTION
// This is a class that allows for an object to be passed in that called the ProgramStateEvent
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package ThreadingHelpers;

public class ProgramStateEvent extends java.util.EventObject {
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Attributes
    //
    
    // This class holds one attribute for dealing with the state of the program:
    //
    // _state           :  Holds the type of state that the program is in
    //
    //
    private static final long serialVersionUID = 2529082869427374443L;
    private ProgramState _state;
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Constructors
    //
    
    // This constructor is created by passing it a source object and the state of the program
    // This allows for this event object to be made dealing with just what types of events this program can have.
    //
    public ProgramStateEvent( Object source, ProgramState state ) {
        super( source );
        _state = state;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Class Methods
    //
    
    // This returns the state of the program passed into the object
    //
    public ProgramState state() {
        return _state;
    }
}
