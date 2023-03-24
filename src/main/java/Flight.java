/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "FlightStates.ump"
public class Flight
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Flight State Machines
  public enum Status { NotReady, Ready, Flying, Landed, Cancelled }
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Flight()
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
        rejectFly();
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
      case Ready:
        doCancel();
      case Flying:
        rejectCancel();
      case Landed:
        rejectCancel();
      default:
        return null;
    }
  }

  private boolean __autotransition25__()
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

  private boolean __autotransition26__()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Ready:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean __autotransition27__()
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

  private boolean __autotransition28__()
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
        __autotransition25__();
        break;
      case Ready:
        __autotransition26__();
        break;
      case Flying:
        __autotransition27__();
        break;
      case Landed:
        __autotransition28__();
        break;
    }
  }

  public void delete()
  {}

  // line 59 "FlightStates.ump"
   private boolean canBeAssigned(Plane plane){
    return !plane.hasNextFlight();
  }

  // line 63 "FlightStates.ump"
   private boolean planeAssignedHere(Plane plane){
    return getPlane().getNextFlight().equals(this);
  }

  // line 67 "FlightStates.ump"
   private void doAssignPlane(Plane plane){
    setPlane(plane);
  }

  // line 71 "FlightStates.ump"
   private void rejectPlaneAssignment(){
    throw new RuntimeException("Cannot assign this plane to this flight.");
  }

  // line 75 "FlightStates.ump"
   private void rejectFly(String error){
    throw new RuntimeException(error);
  }

  // line 79 "FlightStates.ump"
   private void doCancel(){
    setPlane(null);
  }

  // line 82 "FlightStates.ump"
   private void rejectCancel(String error){
    throw new RuntimeException(error);
  }

}