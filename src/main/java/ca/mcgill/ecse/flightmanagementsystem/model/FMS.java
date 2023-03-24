/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.flightmanagementsystem.model;
import java.util.*;
import java.sql.Date;

// line 5 "../../../../../FlightManagementSystem.ump"
public class FMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FMS Associations
  private List<Flight> flights;
  private List<Person> persons;
  private List<Airport> airports;
  private List<Plane> planes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FMS()
  {
    flights = new ArrayList<Flight>();
    persons = new ArrayList<Person>();
    airports = new ArrayList<Airport>();
    planes = new ArrayList<Plane>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Flight getFlight(int index)
  {
    Flight aFlight = flights.get(index);
    return aFlight;
  }

  public List<Flight> getFlights()
  {
    List<Flight> newFlights = Collections.unmodifiableList(flights);
    return newFlights;
  }

  public int numberOfFlights()
  {
    int number = flights.size();
    return number;
  }

  public boolean hasFlights()
  {
    boolean has = flights.size() > 0;
    return has;
  }

  public int indexOfFlight(Flight aFlight)
  {
    int index = flights.indexOf(aFlight);
    return index;
  }
  /* Code from template association_GetMany */
  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
  }
  /* Code from template association_GetMany */
  public Airport getAirport(int index)
  {
    Airport aAirport = airports.get(index);
    return aAirport;
  }

  public List<Airport> getAirports()
  {
    List<Airport> newAirports = Collections.unmodifiableList(airports);
    return newAirports;
  }

  public int numberOfAirports()
  {
    int number = airports.size();
    return number;
  }

  public boolean hasAirports()
  {
    boolean has = airports.size() > 0;
    return has;
  }

  public int indexOfAirport(Airport aAirport)
  {
    int index = airports.indexOf(aAirport);
    return index;
  }
  /* Code from template association_GetMany */
  public Plane getPlane(int index)
  {
    Plane aPlane = planes.get(index);
    return aPlane;
  }

  public List<Plane> getPlanes()
  {
    List<Plane> newPlanes = Collections.unmodifiableList(planes);
    return newPlanes;
  }

  public int numberOfPlanes()
  {
    int number = planes.size();
    return number;
  }

  public boolean hasPlanes()
  {
    boolean has = planes.size() > 0;
    return has;
  }

  public int indexOfPlane(Plane aPlane)
  {
    int index = planes.indexOf(aPlane);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFlights()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Flight addFlight(String aFlightNumber, Date aDate, Airport aFromAirport, Airport aToAirport)
  {
    return new Flight(aFlightNumber, aDate, this, aFromAirport, aToAirport);
  }

  public boolean addFlight(Flight aFlight)
  {
    boolean wasAdded = false;
    if (flights.contains(aFlight)) { return false; }
    FMS existingFMS = aFlight.getFMS();
    boolean isNewFMS = existingFMS != null && !this.equals(existingFMS);
    if (isNewFMS)
    {
      aFlight.setFMS(this);
    }
    else
    {
      flights.add(aFlight);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFlight(Flight aFlight)
  {
    boolean wasRemoved = false;
    //Unable to remove aFlight, as it must always have a fMS
    if (!this.equals(aFlight.getFMS()))
    {
      flights.remove(aFlight);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFlightAt(Flight aFlight, int index)
  {  
    boolean wasAdded = false;
    if(addFlight(aFlight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFlights()) { index = numberOfFlights() - 1; }
      flights.remove(aFlight);
      flights.add(index, aFlight);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFlightAt(Flight aFlight, int index)
  {
    boolean wasAdded = false;
    if(flights.contains(aFlight))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFlights()) { index = numberOfFlights() - 1; }
      flights.remove(aFlight);
      flights.add(index, aFlight);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFlightAt(aFlight, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Person addPerson(String aName)
  {
    return new Person(aName, this);
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    FMS existingFMS = aPerson.getFMS();
    boolean isNewFMS = existingFMS != null && !this.equals(existingFMS);
    if (isNewFMS)
    {
      aPerson.setFMS(this);
    }
    else
    {
      persons.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a fMS
    if (!this.equals(aPerson.getFMS()))
    {
      persons.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAirports()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Airport addAirport(String aAddress, String aCode)
  {
    return new Airport(aAddress, aCode, this);
  }

  public boolean addAirport(Airport aAirport)
  {
    boolean wasAdded = false;
    if (airports.contains(aAirport)) { return false; }
    FMS existingFMS = aAirport.getFMS();
    boolean isNewFMS = existingFMS != null && !this.equals(existingFMS);
    if (isNewFMS)
    {
      aAirport.setFMS(this);
    }
    else
    {
      airports.add(aAirport);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAirport(Airport aAirport)
  {
    boolean wasRemoved = false;
    //Unable to remove aAirport, as it must always have a fMS
    if (!this.equals(aAirport.getFMS()))
    {
      airports.remove(aAirport);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAirportAt(Airport aAirport, int index)
  {  
    boolean wasAdded = false;
    if(addAirport(aAirport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAirports()) { index = numberOfAirports() - 1; }
      airports.remove(aAirport);
      airports.add(index, aAirport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAirportAt(Airport aAirport, int index)
  {
    boolean wasAdded = false;
    if(airports.contains(aAirport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAirports()) { index = numberOfAirports() - 1; }
      airports.remove(aAirport);
      airports.add(index, aAirport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAirportAt(aAirport, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlanes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Plane addPlane(String aModel, String aStatus)
  {
    return new Plane(aModel, aStatus, this);
  }

  public boolean addPlane(Plane aPlane)
  {
    boolean wasAdded = false;
    if (planes.contains(aPlane)) { return false; }
    FMS existingFMS = aPlane.getFMS();
    boolean isNewFMS = existingFMS != null && !this.equals(existingFMS);
    if (isNewFMS)
    {
      aPlane.setFMS(this);
    }
    else
    {
      planes.add(aPlane);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlane(Plane aPlane)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlane, as it must always have a fMS
    if (!this.equals(aPlane.getFMS()))
    {
      planes.remove(aPlane);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlaneAt(Plane aPlane, int index)
  {  
    boolean wasAdded = false;
    if(addPlane(aPlane))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlanes()) { index = numberOfPlanes() - 1; }
      planes.remove(aPlane);
      planes.add(index, aPlane);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlaneAt(Plane aPlane, int index)
  {
    boolean wasAdded = false;
    if(planes.contains(aPlane))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlanes()) { index = numberOfPlanes() - 1; }
      planes.remove(aPlane);
      planes.add(index, aPlane);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlaneAt(aPlane, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (flights.size() > 0)
    {
      Flight aFlight = flights.get(flights.size() - 1);
      aFlight.delete();
      flights.remove(aFlight);
    }
    
    while (persons.size() > 0)
    {
      Person aPerson = persons.get(persons.size() - 1);
      aPerson.delete();
      persons.remove(aPerson);
    }
    
    while (airports.size() > 0)
    {
      Airport aAirport = airports.get(airports.size() - 1);
      aAirport.delete();
      airports.remove(aAirport);
    }
    
    while (planes.size() > 0)
    {
      Plane aPlane = planes.get(planes.size() - 1);
      aPlane.delete();
      planes.remove(aPlane);
    }
    
  }

}