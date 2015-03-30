
public class Vehicle(){
	
}

enum 

public class ParkingLot{
	private Level[] levels;
	private final int NUM_LEVELS  = 5;
	public ParkingLot(){}
	public boolean parkVehicle(Vehicle vehicle){}
}

public class Level {
	private int floor;
	private ParkingSpot[] spots;
	private availableSpots = 0;
	private static final int spotsPerRow = 10;
	
	
	public Level(){}
	public int availableSpots(){ return availableSpots; }
	public boolean parkVehicle(Vehicle vehicle){	}
	
	private boolean parkStartingAtSpot(int num, Vehicle vehicle){}
	private int findAvailableSpots( Vehicle vehicle){	}
	
	public void freeSpot(){availableSpots++;}
	
}

public class ParkingSpot{
	private Vehicle vehicle;
	private VehicleSize spotSize;
	private int row;
	private int spotNumber;
	private Level level;
	
	public ParkingSpot(Level lvl, int r, int n, VehicleSize s){ }
	public boolean isAvailable(){}
	public boolean canFitVehicle(){}
	public boolean park(){}
	public int getRow(){}
	public int getSpotNumber(){}
	public void removeVehicle(){}
	
}