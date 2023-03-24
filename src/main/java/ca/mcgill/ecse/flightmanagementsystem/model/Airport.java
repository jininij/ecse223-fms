/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.flightmanagementsystem.model;
import java.util.*;
import java.sql.Date;

// line 19 "../../../../../FMSPersistence.ump"
// line 18 "../../../../../FlightManagementSystem.ump"
public class Airport
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Airport> airportsByCode = new HashMap<String, Airport>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Airport Attributes
  private String address;
  private String code;

  //Airport Associations
  private FMS fMS;
  private List<Flight> departingFlights;
  private List<Flight> arrivingFlights;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Airport(String aAddress, String aCode, FMS aFMS)
  {
    address = aAddress;
    if (!setCode(aCode))
    {
      throw new RuntimeException("Cannot create due to duplicate code. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddFMS = setFMS(aFMS);
    if (!didAddFMS)
    {
      throw new RuntimeException("Unable to create airport due to fMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    departingFlights = new ArrayList<Flight>();
    arrivingFlights = new ArrayList<Flight>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setCode(String aCode)
  {
    boolean wasSet = false;
    String anOldCode = getCode();
    if (anOldCode != null && anOldCode.equals(aCode)) {
      return true;
    }
    if (hasWithCode(aCode)) {
      return wasSet;
    }
    code = aCode;
    wasSet = true;
    if (anOldCode != null) {
      airportsByCode.remove(anOldCode);
    }
    airportsByCode.put(aCode, this);
    return wasSet;
  }

  public String getAddress()
  {
    return address;
  }

  public String getCode()
  {
    return code;
  }
  /* Code from template attribute_GetUnique */
  public static Airport getWithCode(String aCode)
  {
    return airportsByCode.get(aCode);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithCode(String aCode)
  {
    return getWithCode(aCode) != null;
  }
  /* Code from template association_GetOne */
  public FMS getFMS()
  {
    return fMS;
  }
  /* Code from template association_GetMany */
  public Flight getDepartingFlight(int index)
  {
    Flight aDepartingFlight = departingFlights.get(index);
    return aDepartingFlight;
  }

  public List<Flight> getDepartingFlights()
  {
    List<Flight> newDepartingFlights = Collections.unmodifiableList(departingFlights);
    return newDepartingFlights;
  }

  public int numberOfDepartingFlights()
  {
    int number = departingFlights.size();
    return number;
  }

  public boolean hasDepartingFlights()
  {
    boolean has = departingFlights.size() > 0;
    return has;
  }

  public int indexOfDepartingFlight(Flight aDepartingFlight)
  {
    int index = departingFlights.indexOf(aDepartingFlight);
    return index;
  }
  /* Code from template association_GetMany */
  public Flight getArrivingFlight(int index)
  {
    Flight aArrivingFlight = arrivingFlights.get(index);
    return aArrivingFlight;
  }

  public List<Flight> getArrivingFlights()
  {
    List<Flight> newArrivingFlights = Collections.unmodifiableList(arrivingFlights);
    return newArrivingFlights;
  }

  public int numberOfArrivingFlights()
  {
    int number = arrivingFlights.size();
    return number;
  }

  public boolean hasArrivingFlights()
  {
    boolean has = arrivingFlights.size() > 0;
    return has;
  }

  public int indexOfArrivingFlight(Flight aArrivingFlight)
  {
    int index = arrivingFlights.indexOf(aArrivingFlight);
    return index;
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
      existingFMS.removeAirport(this);
    }
    fMS.addAirport(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDepartingFlights()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Flight addDepartingFlight(String aFlightNumber, Date aDate, FMS aFMS, Airport aToAirport)
  {
    return new Flight(aFlightNumber, aDate, aFMS, this, aToAirport);
  }

  public boolean addDepartingFlight(Flight aDepartingFlight)
  {
    boolean wasAdded = false;
    if (departingFlights.contains(aDepartingFlight)) { return false; }
    Airport existingFromAirport = aDepartingFlight.getFromAirport();
    boolean isNewFromAirport = existingFromAirport != null && !this.equals(existingFromAirport);
    if (isNewFromAirport)
    {
      aDepartingFlight.setFromAirport(this);
    }
    else
    {
      departingFlights.add(aDepartingFlight);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDepartingFlight(Flight aDepartingFlight)
  {
    boolean wasRemoved = false;
    //Unable to remove aDepartingFlight, as it must always have a fromAirport
    if (!this.equals(aDepartingFlight.getFromAirport()))
    {
      departingFlights.remove(aDepartingFlight);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDepartingFlightAt(Flight aDepartingFlight, int index)
  {  
    boolean wasAdded = false;
    if(addDepartingFlight(aDepartingFlight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDepartingFlights()) { index = numberOfDepartingFlights() - 1; }
      departingFlights.remove(aDepartingFlight);
      departingFlights.add(index, aDepartingFlight);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDepartingFlightAt(Flight aDepartingFlight, int index)
  {
    boolean wasAdded = false;
    if(departingFlights.contains(aDepartingFlight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDepartingFlights()) { index = numberOfDepartingFlights() - 1; }
      departingFlights.remove(aDepartingFlight);
      departingFlights.add(index, aDepartingFlight);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDepartingFlightAt(aDepartingFlight, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfArrivingFlights()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Flight addArrivingFlight(String aFlightNumber, Date aDate, FMS aFMS, Airport aFromAirport)
  {
    return new Flight(aFlightNumber, aDate, aFMS, aFromAirport, this);
  }

  public boolean addArrivingFlight(Flight aArrivingFlight)
  {
    boolean wasAdded = false;
    if (arrivingFlights.contains(aArrivingFlight)) { return false; }
    Airport existingToAirport = aArrivingFlight.getToAirport();
    boolean isNewToAirport = existingToAirport != null && !this.equals(existingToAirport);
    if (isNewToAirport)
    {
      aArrivingFlight.setToAirport(this);
    }
    else
    {
      arrivingFlights.add(aArrivingFlight);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArrivingFlight(Flight aArrivingFlight)
  {
    boolean wasRemoved = false;
    //Unable to remove aArrivingFlight, as it must always have a toAirport
    if (!this.equals(aArrivingFlight.getToAirport()))
    {
      arrivingFlights.remove(aArrivingFlight);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addArrivingFlightAt(Flight aArrivingFlight, int index)
  {  
    boolean wasAdded = false;
    if(addArrivingFlight(aArrivingFlight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArrivingFlights()) { index = numberOfArrivingFlights() - 1; }
      arrivingFlights.remove(aArrivingFlight);
      arrivingFlights.add(index, aArrivingFlight);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveArrivingFlightAt(Flight aArrivingFlight, int index)
  {
    boolean wasAdded = false;
    if(arrivingFlights.contains(aArrivingFlight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfArrivingFlights()) { index = numberOfArrivingFlights() - 1; }
      arrivingFlights.remove(aArrivingFlight);
      arrivingFlights.add(index, aArrivingFlight);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addArrivingFlightAt(aArrivingFlight, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    airportsByCode.remove(getCode());
    FMS placeholderFMS = fMS;
    this.fMS = null;
    if(placeholderFMS != null)
    {
      placeholderFMS.removeAirport(this);
    }
    for(int i=departingFlights.size(); i > 0; i--)
    {
      Flight aDepartingFlight = departingFlights.get(i - 1);
      aDepartingFlight.delete();
    }
    for(int i=arrivingFlights.size(); i > 0; i--)
    {
      Flight aArrivingFlight = arrivingFlights.get(i - 1);
      aArrivingFlight.delete();
    }
  }

  // line 21 "../../../../../FMSPersistence.ump"
   public static  void reinitializeUniqueCode(List<Airport> airports){
    airportsByCode.clear();
    for (var a : airports) {
      airportsByCode.put(a.getCode(), a);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "address" + ":" + getAddress()+ "," +
            "code" + ":" + getCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fMS = "+(getFMS()!=null?Integer.toHexString(System.identityHashCode(getFMS())):"null");
  }
}