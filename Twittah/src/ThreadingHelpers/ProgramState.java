//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Project              : IST240 - Twitter Application
//
// enum Name            : ProgramState
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// enum Attributes

// The type of program states
// 
public enum ProgramState {
TIMELINE_PARSED, TIMELINE_MODIFIED, SUBSCRIPTION_ADDED, TIMELINE_REFRESHED
}
