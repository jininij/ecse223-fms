/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.flightmanagementsystem.model;
import java.util.*;
import java.sql.Date;

// line 1 "../../../../../FlightStates.ump"
// line 12 "../../../../../FlightManagementSystem.ump"
public class Flight
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Flight> flightsByFlightNumber = new HashMap<String, Flight>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Flight Attributes
  private String flightNumber;
  private Date date;

  //Flight State Machines
  public enum Status { NotReady, Ready, Flying, Landed, Cancelled }
  private Status status;

  //Flight Associations
  private FMS fMS;
  private Airport fromAirport;
  private Airport toAirport;
  private Plane plane;
  private List<Person> passengers;
  private Person pilot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Flight(String aFlightNumber, Date aDate, FMS aFMS, Airport aFromAirport, Airport aToAirport)
  {
    date = aDate;
    if (!setFlightNumber(aFlightNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate flightNumber. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddFMS = setFMS(aFMS);
    if (!didAddFMS)
    {
      throw new RuntimeException("Unable to create flight due to fMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFromAirport = setFromAirport(aFromAirport);
    if (!didAddFromAirport)
    {
      throw new RuntimeException("Unable to create departingFlight due to fromAirport. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddToAirport = setToAirport(aToAirport);
    if (!didAddToAirport)
    {
      throw new RuntimeException("Unable to create arrivingFlight due to toAirport. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    passengers = new ArrayList<Person>();
    setStatus(Status.NotReady);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFlightNumber(String aFlightNumber)
  {
    boolean wasSet = false;
    String anOldFlightNumber = getFlightNumber();
    if (anOldFlightNumber != null && anOldFlightNumber.equals(aFlightNumber)) {
      return true;
    }
    if (hasWithFlightNumber(aFlightNumber)) {
      return wasSet;
    }
    flightNumber = aFlightNumber;
    wasSet = true;
    if (anOldFlightNumber != null) {
      flightsByFlightNumber.remove(anOldFlightNumber);
    }
    flightsByFlightNumber.put(aFlightNumber, this);
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getFlightNumber()
  {
    return flightNumber;
  }
  /* Code from template attribute_GetUnique */
  public static Flight getWithFlightNumber(String aFlightNumber)
  {
    return flightsByFlightNumber.get(aFlightNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithFlightNumber(String aFlightNumber)
  {
    return getWithFlightNumber(aFlightNumber) != null;
  }

  public Date getDate()
  {
    return date;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean assignPlane(Plane plane)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotReady:
        if (canBeAssigned(plane))
        {
        // line 5 "../../../../../FlightStates.ump"
          doAssignPlane(plane);
          setStatus(Status.Ready);
          wasEventProcessed = true;
          break;
        }
        if (!(canBeAssigned(plane)))
        {
        // line 9 "../../../../../FlightStates.ump"
          rejectPlaneAssignment();
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

  public boolean fly()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotReady:
        // line 13 "../../../../../FlightStates.ump"
        rejectFly("");
        setStatus(Status.NotReady);
        wasEventProcessed = true;
        break;
      case Ready:
        if (isTimeToFly())
        {
        // line 26 "../../../../../FlightStates.ump"
          
          setStatus(Status.Flying);
          wasEventProcessed = true;
          break;
        }
        if (!(isTimeToFly()))
        {
        // line 28 "../../../../../FlightStates.ump"
          rejectFly("");
          setStatus(Status.Ready);
          wasEventProcessed = true;
          break;
        }
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
      case NotReady:
        // line 17 "../../../../../FlightStates.ump"
        doCancel();
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Ready:
        // line 32 "../../../../../FlightStates.ump"
        doCancel();
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Flying:
        // line 38 "../../../../../FlightStates.ump"
        rejectCancel("");
        setStatus(Status.Flying);
        wasEventProcessed = true;
        break;
      case Landed:
        // line 46 "../../../../../FlightStates.ump"
        rejectCancel("");
        setStatus(Status.Landed);
        wasEventProcessed = true;
        break;
      case Cancelled:
        setStatus(Status.Cancelled);
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
        // line 23 "../../../../../FlightStates.ump"
          
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

  public boolean land()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Flying:
        setStatus(Status.Landed);
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
  }
  /* Code from template association_GetOne */
  public FMS getFMS()
  {
    return fMS;
  }
  /* Code from template association_GetOne */
  public Airport getFromAirport()
  {
    return fromAirport;
  }
  /* Code from template association_GetOne */
  public Airport getToAirport()
  {
    return toAirport;
  }
  /* Code from template association_GetOne */
  public Plane getPlane()
  {
    return plane;
  }

  public boolean hasPlane()
  {
    boolean has = plane != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Person getPassenger(int index)
  {
    Person aPassenger = passengers.get(index);
    return aPassenger;
  }

  public List<Person> getPassengers()
  {
    List<Person> newPassengers = Collections.unmodifiableList(passengers);
    return newPassengers;
  }

  public int numberOfPassengers()
  {
    int number = passengers.size();
    return number;
  }

  public boolean hasPassengers()
  {
    boolean has = passengers.size() > 0;
    return has;
  }

  public int indexOfPassenger(Person aPassenger)
  {
    int index = passengers.indexOf(aPassenger);
    return index;
  }
  /* Code from template association_GetOne */
  public Person getPilot()
  {
    return pilot;
  }

  public boolean hasPilot()
  {
    boolean has = pilot != null;
    return has;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFMS(FMS aFMS)
  {
    boolean wasSet = false;
    if (aFMS == null)
    {
      return wasSet;
    }

    FMS existingFMS = fMS;
    fMS = aFMS;
    if (existingFMS != null && !existingFMS.equals(aFMS))
    {
      existingFMS.removeFlight(this);
    }
    fMS.addFlight(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFromAirport(Airport aFromAirport)
  {
    boolean wasSet = false;
    if (aFromAirport == null)
    {
      return wasSet;
    }

    Airport existingFromAirport = fromAirport;
    fromAirport = aFromAirport;
    if (existingFromAirport != null && !existingFromAirport.equals(aFromAirport))
    {
      existingFromAirport.removeDepartingFlight(this);
    }
    fromAirport.addDepartingFlight(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setToAirport(Airport aToAirport)
  {
    boolean wasSet = false;
    if (aToAirport == null)
    {
      return wasSet;
    }

    Airport existingToAirport = toAirport;
    toAirport = aToAirport;
    if (existingToAirport != null && !existingToAirport.equals(aToAirport))
    {
      existingToAirport.removeArrivingFlight(this);
    }
    toAirport.addArrivingFlight(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setPlane(Plane aNewPlane)
  {
    boolean wasSet = false;
    if (aNewPlane == null)
    {
      Plane existingPlane = plane;
      plane = null;
      
      if (existingPlane != null && existingPlane.getNextFlight() != null)
      {
        existingPlane.setNextFlight(null);
      }
      wasSet = true;
      return wasSet;
    }

    Plane currentPlane = getPlane();
    if (currentPlane != null && !currentPlane.equals(aNewPlane))
    {
      currentPlane.setNextFlight(null);
    }

    plane = aNewPlane;
    Flight existingNextFlight = aNewPlane.getNextFlight();

    if (!equals(existingNextFlight))
    {
      aNewPlane.setNextFlight(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPassengers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addPassenger(Person aPassenger)
  {
    boolean wasAdded = false;
    if (passengers.contains(aPassenger)) { return false; }
    Flight existingBoardingFlight = aPassenger.getBoardingFlight();
    if (existingBoardingFlight == null)
    {
      aPassenger.setBoardingFlight(this);
    }
    else if (!this.equals(existingBoardingFlight))
    {
      existingBoardingFlight.removePassenger(aPassenger);
      addPassenger(aPassenger);
    }
    else
    {
      passengers.add(aPassenger);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePassenger(Person aPassenger)
  {
    boolean wasRemoved = false;
    if (passengers.contains(aPassenger))
    {
      passengers.remove(aPassenger);
      aPassenger.setBoardingFlight(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPassengerAt(Person aPassenger, int index)
  {  
    boolean wasAdded = false;
    if(addPassenger(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePassengerAt(Person aPassenger, int index)
  {
    boolean wasAdded = false;
    if(passengers.contains(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPassengerAt(aPassenger, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setPilot(Person aNewPilot)
  {
    boolean wasSet = false;
    if (aNewPilot == null)
    {
      Person existingPilot = pilot;
      pilot = null;
      
      if (existingPilot != null && existingPilot.getWorkingFlight() != null)
      {
        existingPilot.setWorkingFlight(null);
      }
      wasSet = true;
      return wasSet;
    }

    Person currentPilot = getPilot();
    if (currentPilot != null && !currentPilot.equals(aNewPilot))
    {
      currentPilot.setWorkingFlight(null);
    }

    pilot = aNewPilot;
    Flight existingWorkingFlight = aNewPilot.getWorkingFlight();

    if (!equals(existingWorkingFlight))
    {
      aNewPilot.setWorkingFlight(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    flightsByFlightNumber.remove(getFlightNumber());
    FMS placeholderFMS = fMS;
    this.fMS = null;
    if(placeholderFMS != null)
    {
      placeholderFMS.removeFlight(this);
    }
    Airport placeholderFromAirport = fromAirport;
    this.fromAirport = null;
    if(placeholderFromAirport != null)
    {
      placeholderFromAirport.removeDepartingFlight(this);
    }
    Airport placeholderToAirport = toAirport;
    this.toAirport = null;
    if(placeholderToAirport != null)
    {
      placeholderToAirport.removeArrivingFlight(this);
    }
    if (plane != null)
    {
      plane.setNextFlight(null);
    }
    while( !passengers.isEmpty() )
    {
      passengers.get(0).setBoardingFlight(null);
    }
    if (pilot != null)
    {
      pilot.setWorkingFlight(null);
    }
  }

  // line 57 "../../../../../FlightStates.ump"
   private boolean canBeAssigned(Plane plane){
    return !plane.hasNextFlight();
  }

  // line 61 "../../../../../FlightStates.ump"
   private boolean planeAssignedHere(Plane plane){
    return getPlane().getNextFlight().equals(this);
  }

  // line 65 "../../../../../FlightStates.ump"
   private void doAssignPlane(Plane plane){
    setPlane(plane);
  }

  // line 69 "../../../../../FlightStates.ump"
   private void rejectPlaneAssignment(){
    throw new RuntimeException("Cannot assign this plane to this flight.");
  }

  // line 72 "../../../../../FlightStates.ump"
   private boolean isTimeToFly(){
    java.util.Date d = new java.util.Date();    
		return d.equals(date);
  }

  // line 76 "../../../../../FlightStates.ump"
   private void rejectFly(String error){
    throw new RuntimeException(error);
  }

  // line 80 "../../../../../FlightStates.ump"
   private void doCancel(){
    setPlane(null);
  }

  // line 83 "../../../../../FlightStates.ump"
   private void rejectCancel(String error){
    throw new RuntimeException(error);
  }


  public String toString()
  {
    return super.toString() + "["+
            "flightNumber" + ":" + getFlightNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "fMS = "+(getFMS()!=null?Integer.toHexString(System.identityHashCode(getFMS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "fromAirport = "+(getFromAirport()!=null?Integer.toHexString(System.identityHashCode(getFromAirport())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "toAirport = "+(getToAirport()!=null?Integer.toHexString(System.identityHashCode(getToAirport())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "plane = "+(getPlane()!=null?Integer.toHexString(System.identityHashCode(getPlane())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "pilot = "+(getPilot()!=null?Integer.toHexString(System.identityHashCode(getPilot())):"null");
  }
}