/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "FlightStates.ump"
public class Participant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Participant State Machines
  public enum Status { NotReady, Ready, Flying, Landed, Cancelled }
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Participant()
  {
    setStatus(Status.NotReady);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public  fly()
  {
    switch(status)
    {
      case NotReady:
        rejectFlyNotReady();
      default:
        return null;
    }
  }

  public  cancel()
  {
    switch(status)
    {
      case NotReady:
        doCancel();
      case Flying:
        rejectCancel();
      case Landed:
        rejectCancel();
      default:
        return null;
    }
  }

  private boolean __autotransition7__()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotReady:
        setStatus(Status.NotReady);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean unassign(Plane plane)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Ready:
        if (planeAssignedHere(plane))
        {
        // line 23 "FlightStates.ump"
          
          setStatus(Status.NotReady);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition8__()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Flying:
        setStatus(Status.Flying);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition9__()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Landed:
        setStatus(Status.Landed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Cancelled:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case NotReady:
        __autotransition7__();
        break;
      case Flying:
        __autotransition8__();
        break;
      case Landed:
        __autotransition9__();
        break;
    }
  }

  public void delete()
  {}

  // line 58 "FlightStates.ump"
   private boolean canBeAssigned(Plane plane){
    return !plane.hasNextFlight();
  }

  // line 62 "FlightStates.ump"
   private void doAssignPlane(Plane plane){
    setPlane(plane);
  }

  // line 66 "FlightStates.ump"
   private void rejectPlaneAssignment(){
    throw new RuntimeException("Cannot assign this plane to this flight.");
  }

  // line 70 "FlightStates.ump"
   private void rejectFly(){
    throw new RuntimeException("Not time to fly yet!");
  }

  // line 74 "FlightStates.ump"
   private void rejectFlyNotReady(){
    throw new RuntimeException("Cannot fly. There is no plane.");
  }

  // line 77 "FlightStates.ump"
   private void doCancel(){
    setPlane(null);
  }

  // line 80 "FlightStates.ump"
   private void rejectCancel(String state){
    throw new RuntimeException("Cannot cancel flight due to " + state + ".");
  }

}