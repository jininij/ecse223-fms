package ca.mcgill.ecse.flightmanagementsystem.persistence;

import ca.mcgill.ecse.flightmanagementsystem.application.FMSApplication;
import ca.mcgill.ecse.flightmanagementsystem.model.FMS;

public class FmsPersistence {


	  private static String filename = "data.json";
	  private static JsonSerializer serializer = new JsonSerializer("ca.mcgill.ecse.flightmanagementsystem");

	  public static void setFilename(String filename) {
	    FmsPersistence.filename = filename;
	  }

	  public static void save() {
	    save(FMSApplication.getFMS());
	  }

	  public static void save(FMS fms) {
	    serializer.serialize(fms, filename);
	  }

	  public static FMS load() {
	    var fms = (FMS) serializer.deserialize(filename);
	    // model cannot be loaded - create empty BTMS
	    if (fms == null) {
	      fms = new FMS();
	    } else {
	      fms.reinitialize();
	    }
	    return fms;
	  }

}
