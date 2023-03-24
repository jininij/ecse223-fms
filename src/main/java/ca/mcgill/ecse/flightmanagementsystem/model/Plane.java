/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.flightmanagementsystem.model;

import java.util.List;

// line 37 "../../../../../FMSPersistence.ump"
// line 27 "../../../../../FlightManagementSystem.ump"
public class Plane
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Plane Attributes
  private String model;

  //Autounique Attributes
  private int id;

  //Plane Associations
  private FMS fMS;
  private Flight nextFlight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Plane(String aModel, FMS aFMS)
  {
    model = aModel;
    id = nextId++;
    boolean didAddFMS = setFMS(aFMS);
    if (!didAddFMS)
    {
      throw new RuntimeException("Unable to create plane due to fMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setModel(String aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public String getModel()
  {
    return model;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public FMS getFMS()
  {
    return fMS;
  }
  /* Code from template association_GetOne */
  public Flight getNextFlight()
  {
    return nextFlight;
  }

  public boolean hasNextFlight()
  {
    boolean has = nextFlight != null;
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
      existingFMS.removePlane(this);
    }
    fMS.addPlane(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setNextFlight(Flight aNewNextFlight)
  {
    boolean wasSet = false;
    if (aNewNextFlight == null)
    {
      Flight existingNextFlight = nextFlight;
      nextFlight = null;
      
      if (existingNextFlight != null && existingNextFlight.getPlane() != null)
      {
        existingNextFlight.setPlane(null);
      }
      wasSet = true;
      return wasSet;
    }

    Flight currentNextFlight = getNextFlight();
    if (currentNextFlight != null && !currentNextFlight.equals(aNewNextFlight))
    {
      currentNextFlight.setPlane(null);
    }

    nextFlight = aNewNextFlight;
    Plane existingPlane = aNewNextFlight.getPlane();

    if (!equals(existingPlane))
    {
      aNewNextFlight.setPlane(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    FMS placeholderFMS = fMS;
    this.fMS = null;
    if(placeholderFMS != null)
    {
      placeholderFMS.removePlane(this);
    }
    if (nextFlight != null)
    {
      nextFlight.setPlane(null);
    }
  }

  // line 39 "../../../../../FMSPersistence.ump"
   public static  void reinitializeAutouniqueID(List<Plane> planes){
    nextId = 0; 
    for (Plane plane : planes) {
      if (plane.getId() > nextId) {
        nextId = plane.getId();
      }
    }
    nextId++;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "model" + ":" + getModel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fMS = "+(getFMS()!=null?Integer.toHexString(System.identityHashCode(getFMS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "nextFlight = "+(getNextFlight()!=null?Integer.toHexString(System.identityHashCode(getNextFlight())):"null");
  }
}