//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// Interface Name       : ProgramStateListener
//    
// Authors              : Scott Smiesko, Rick Humes
// Date                 : 2010-30-04
//
//
// DESCRIPTION
// This is an interface for classes to use to listen for ProgramStateEvents.
//
// KNOWN LIMITATIONS
// None.
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package ThreadingHelpers;


// No need to look here
// Interface for classes to listen with
//
public interface ProgramStateListener {
    public void stateReceived( ProgramStateEvent event );
}
