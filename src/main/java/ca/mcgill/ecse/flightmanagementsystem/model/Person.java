/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.flightmanagementsystem.model;
import java.util.*;

// line 28 "../../../../../FMSPersistence.ump"
// line 23 "../../../../../FlightManagementSystem.ump"
public class Person
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Person> personsByName = new HashMap<String, Person>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;

  //Person Associations
  private FMS fMS;
  private Flight boardingFlight;
  private Flight workingFlight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, FMS aFMS)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddFMS = setFMS(aFMS);
    if (!didAddFMS)
    {
      throw new RuntimeException("Unable to create person due to fMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      personsByName.remove(anOldName);
    }
    personsByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Person getWithName(String aName)
  {
    return personsByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }
  /* Code from template association_GetOne */
  public FMS getFMS()
  {
    return fMS;
  }
  /* Code from template association_GetOne */
  public Flight getBoardingFlight()
  {
    return boardingFlight;
  }

  public boolean hasBoardingFlight()
  {
    boolean has = boardingFlight != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Flight getWorkingFlight()
  {
    return workingFlight;
  }

  public boolean hasWorkingFlight()
  {
    boolean has = workingFlight != null;
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
      existingFMS.removePerson(this);
    }
    fMS.addPerson(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setBoardingFlight(Flight aBoardingFlight)
  {
    boolean wasSet = false;
    Flight existingBoardingFlight = boardingFlight;
    boardingFlight = aBoardingFlight;
    if (existingBoardingFlight != null && !existingBoardingFlight.equals(aBoardingFlight))
    {
      existingBoardingFlight.removePassenger(this);
    }
    if (aBoardingFlight != null)
    {
      aBoardingFlight.addPassenger(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setWorkingFlight(Flight aNewWorkingFlight)
  {
    boolean wasSet = false;
    if (aNewWorkingFlight == null)
    {
      Flight existingWorkingFlight = workingFlight;
      workingFlight = null;
      
      if (existingWorkingFlight != null && existingWorkingFlight.getPilot() != null)
      {
        existingWorkingFlight.setPilot(null);
      }
      wasSet = true;
      return wasSet;
    }

    Flight currentWorkingFlight = getWorkingFlight();
    if (currentWorkingFlight != null && !currentWorkingFlight.equals(aNewWorkingFlight))
    {
      currentWorkingFlight.setPilot(null);
    }

    workingFlight = aNewWorkingFlight;
    Person existingPilot = aNewWorkingFlight.getPilot();

    if (!equals(existingPilot))
    {
      aNewWorkingFlight.setPilot(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    personsByName.remove(getName());
    FMS placeholderFMS = fMS;
    this.fMS = null;
    if(placeholderFMS != null)
    {
      placeholderFMS.removePerson(this);
    }
    if (boardingFlight != null)
    {
      Flight placeholderBoardingFlight = boardingFlight;
      this.boardingFlight = null;
      placeholderBoardingFlight.removePassenger(this);
    }
    if (workingFlight != null)
    {
      workingFlight.setPilot(null);
    }
  }

  // line 30 "../../../../../FMSPersistence.ump"
   public static  void reinitializeUniqueName(List<Person> persons){
    personsByName.clear();
    for (Person p : persons) {
      personsByName.put(p.getName(), p);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fMS = "+(getFMS()!=null?Integer.toHexString(System.identityHashCode(getFMS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "boardingFlight = "+(getBoardingFlight()!=null?Integer.toHexString(System.identityHashCode(getBoardingFlight())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "workingFlight = "+(getWorkingFlight()!=null?Integer.toHexString(System.identityHashCode(getWorkingFlight())):"null");
  }
}