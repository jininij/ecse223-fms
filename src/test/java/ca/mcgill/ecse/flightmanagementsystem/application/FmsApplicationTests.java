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

import ca.mcgill.ecse.flightmanagementsystem.model.FMS;
import ca.mcgill.ecse.flightmanagementsystem.model.Plane;
import ca.mcgill.ecse.flightmanagementsystem.persistence.FmsPersistence;

public class FmsApplicationTests {

  private static FMS fms = FMSApplication.getFMS();

  private static int nextDriverID = 1;
  private static String filename = "testdata.btms";

  @BeforeAll
  public static void setUpOnce() {
    FmsPersistence.setFilename(filename);
    // This is a small trick to initialize nextDriverID variable before running the test suite
    Plane dummyPlane = new Plane("b777", "available", new FMS());
    nextDriverID = dummyPlane.getId() + 1;
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
//    Date date = Date.valueOf(LocalDate.now());
//    String licencePlate = "XYZ123";
//    BusVehicle bus = fms.addVehicle(licencePlate);
//    int routeNumber = 1;
//    Route route = fms.addRoute(routeNumber);
//    RouteAssignment assignment = fms.addAssignment(date, bus, route);
//    String name = "driver";
//    nextDriverID++;
//    Driver driver = fms.addDriver(name);
//    Shift shift = Shift.Afternoon;
//    fms.addSchedule(shift, driver, assignment);
//    BtmsPersistence.save();
//
//    // load model again and check it
//    BTMS btms2 = BtmsPersistence.load();
//    checkResultSchedule(driver.getName(), driver.getSickStatus(), driver.getId(), assignment.getDate(),
//        assignment.getRoute().getNumber(), assignment.getBus().getLicencePlate(), assignment.getBus().getRepairStatus(),
//        shift, btms2, 1, 1, 1, 1, 1);
  }

  @Test
  public void testPersistenceReinitialization() {
//    Date date = Date.valueOf(LocalDate.now());
//    String licencePlate = "XYZ123";
//    BusVehicle bus = fms.addVehicle(licencePlate);
//    int routeNumber = 1;
//    Route route = fms.addRoute(routeNumber);
//    RouteAssignment assignment = fms.addAssignment(date, bus, route);
//    String name = "driver";
//    int id = nextDriverID++;
//    Driver driver = fms.addDriver(name);
//    Shift shift = Shift.Afternoon;
//    fms.addSchedule(shift, driver, assignment);
//    BtmsPersistence.save();
//
//    // simulate shutting down the application
//    fms.delete();
//    fms.reinitialize();
//    checkResultSchedule(name, SickStatus.Available, id, date, routeNumber, licencePlate, RepairStatus.Available, shift,
//        fms, 0, 0, 0, 0, 0);
//
//    // load model again and add further model elements
//    fms = BtmsPersistence.load();
//    checkResultSchedule(name, SickStatus.Available, id, date, routeNumber, licencePlate, RepairStatus.Available, shift,
//        fms, 1, 1, 1, 1, 1);
//
//    // check errors
//    assertFailure(() -> fms.addVehicle(licencePlate), "Cannot create due to duplicate licencePlate.");
//    assertFailure(() -> fms.addRoute(routeNumber), "Cannot create due to duplicate number.");
//
//    fms.addDriver(name + name);
//    fms.getDriver(1).toggleSickStatus();
//    BtmsPersistence.save(fms); // need to pass in btms here because it was reassigned
//
//    // load model again and check it
//    fms = BtmsPersistence.load();
//    checkResultScheduleTwoDrivers(name, SickStatus.Available, id, date, routeNumber, licencePlate,
//        RepairStatus.Available, shift, fms, 1, 1, 1, 1, 2, name + name, SickStatus.Sick, id + 1);
  }

  private void checkResultScheduleTwoDrivers() {
//    checkResultSchedule(driverName, driverOnSickLeave, driverId, assignmentDate, assignmentRouteNumber,
//        assignmentBusLicencePlate, assignmentBusInRepairShop, shift, btms, numberSchedules, numberAssignments,
//        numberRoutes, numberBuses, numberDrivers);
//    if (numberDrivers > 1) {
//      assertEquals(driver2Name, btms.getDriver(1).getName());
//      assertEquals(driver2OnSickLeave, btms.getDriver(1).getSickStatus());
//      assertEquals(driver2Id, btms.getDriver(1).getId());
//      assertTrue(btms.getDriver(1).getDriverSchedules().isEmpty());
//      assertEquals(btms, btms.getDriver(1).getBTMS());
//    }
  }

  private void checkResultSchedule() {
//    assertEquals(numberSchedules, btms.getSchedules().size());
//    if (numberSchedules > 0) {
//      assertEquals(shift, btms.getSchedule(0).getShift());
//      assertNotNull(btms.getSchedule(0).getAssignment());
//      assertNotNull(btms.getSchedule(0).getDriver());
//      assertEquals(btms, btms.getSchedule(0).getBTMS());
//    }
//    assertEquals(numberAssignments, btms.getAssignments().size());
//    if (numberAssignments > 0) {
//      assertEquals(assignmentDate, btms.getAssignment(0).getDate());
//      assertNotNull(btms.getAssignment(0).getBus());
//      assertNotNull(btms.getAssignment(0).getRoute());
//      assertEquals(numberSchedules, btms.getAssignment(0).getDriverSchedules().size());
//      assertEquals(btms, btms.getAssignment(0).getBTMS());
//    }
//    assertEquals(numberRoutes, btms.getRoutes().size());
//    if (numberRoutes > 0) {
//      assertEquals(assignmentRouteNumber, btms.getRoute(0).getNumber());
//      assertEquals(numberAssignments, btms.getRoute(0).getRouteAssignments().size());
//      assertEquals(btms, btms.getRoute(0).getBTMS());
//    }
//    assertEquals(numberDrivers, btms.getDrivers().size());
//    if (numberDrivers > 0) {
//      assertEquals(driverName, btms.getDriver(0).getName());
//      assertEquals(driverOnSickLeave, btms.getDriver(0).getSickStatus());
//      assertEquals(driverId, btms.getDriver(0).getId());
//      assertEquals(numberSchedules, btms.getDriver(0).getDriverSchedules().size());
//      assertEquals(btms, btms.getDriver(0).getBTMS());
//    }
//    assertEquals(numberBuses, btms.getVehicles().size());
//    if (numberBuses > 0) {
//      assertEquals(assignmentBusLicencePlate, btms.getVehicle(0).getLicencePlate());
//      assertEquals(assignmentBusInRepairShop, btms.getVehicle(0).getRepairStatus());
//      assertEquals(numberAssignments, btms.getVehicle(0).getRouteAssignments().size());
//      assertEquals(btms, btms.getVehicle(0).getBTMS());
//    }
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
