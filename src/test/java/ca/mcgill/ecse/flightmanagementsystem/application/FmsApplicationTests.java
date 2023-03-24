package ca.mcgill.ecse.flightmanagementsystem.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ca.mcgill.ecse.flightmanagementsystem.model.Airport;
import ca.mcgill.ecse.flightmanagementsystem.model.FMS;
import ca.mcgill.ecse.flightmanagementsystem.model.Flight;
import ca.mcgill.ecse.flightmanagementsystem.model.Plane;
import ca.mcgill.ecse.flightmanagementsystem.persistence.FmsPersistence;

public class FmsApplicationTests {

  private static FMS fms = FMSApplication.getFMS();

  private static int nextPlaneID = 1;
  private static String filename = "testdata.fms";

  @BeforeAll
  public static void setUpOnce() {
    FmsPersistence.setFilename(filename);
    // This is a small trick to initialize nextDriverID variable before running the test suite
    Plane dummyPlane = new Plane("b777", new FMS());
    nextPlaneID = dummyPlane.getId() + 1;
  }

  @BeforeEach
  public void setUp() {
    // remove test file
    new File(filename).delete();
    // clear all data
    fms.delete();
  }

  @Test
  public void testPersistence() {
    Date date = Date.valueOf(LocalDate.now());
    Airport a1 = fms.addAirport("montreal", "yul");
    Airport a2 = fms.addAirport("toronto", "yyt");
    Airport a3 = fms.addAirport("vancouver", "code");
    Flight f1 = fms.addFlight("AC001", date, a1, a2);
	FmsPersistence.save();

	FMS fms2 = FmsPersistence.load();
	assertEquals(fms2.getAirports().size(),3);
	assertEquals(0, Airport.getWithCode("code").getArrivingFlights().size());
	assertEquals(1, Airport.getWithCode("yul").getDepartingFlights().size());
	assertEquals(0, Airport.getWithCode("yyt").getDepartingFlights().size());
	assertEquals(0, Airport.getWithCode("yul").getArrivingFlights().size());
	assertEquals(1, Airport.getWithCode("yyt").getArrivingFlights().size());
  }

  @Test
  public void testPersistenceReinitialization() {
    Date date = Date.valueOf(LocalDate.now());
    
    int planeId = nextPlaneID++;
    String planeModel1 = "a380";
    Plane p1 = fms.addPlane(planeModel1);
    
    planeId = nextPlaneID++;
    String planeModel2 = "b777";
    Plane p2 = fms.addPlane(planeModel2);
    
    String ac1 = "yul";
    String ac2 = "yyt";
    Airport a1 = fms.addAirport("montreal", ac1);
    Airport a2 = fms.addAirport("toronto", ac2);
    Flight f1 = fms.addFlight("AC001", date, a1, a2);
    
    FmsPersistence.save();
    
    fms.delete();
    assertTrue(fms.getPlanes().isEmpty());
    
    fms.reinitialize();
    
    assertEquals(0, fms.numberOfPlanes());
    
    fms = FmsPersistence.load();
    assertEquals(2, fms.numberOfPlanes());
    assertEquals(p1.getId(), fms.getPlane(0).getId());
    assertEquals(p1.getModel(), fms.getPlane(0).getModel());
    assertEquals(p2.getId(), fms.getPlane(1).getId());
    assertEquals(p2.getModel(), fms.getPlane(1).getModel());
    
    assertEquals(2, fms.numberOfAirports());
    assertEquals(ac1,fms.getFlight(0).getFromAirport().getCode());
    assertFailure(() -> fms.addAirport("montreal", ac1), "Cannot create due to duplicate code");
    assertFailure(() -> fms.addFlight("AC001",date, a2, a1), "Cannot create due to duplicate flightNumber.");
  }

  /**
   * Tests failure case by making sure RuntimeException is thrown with the given error message.
   *
   * @param executable a method call that could throw an exception preceded by "() -> ", eg,<br>
   */
  private static void assertFailure(Executable executable, String expectedError) {
    var exception = assertThrows(RuntimeException.class, executable);
    assertTrue(exception.getMessage().contains(expectedError));
  }

}
