import java.util.Scanner;
public class VehicleCosmeticManager{
	private float fuelCapacity = 41.0F; //litres
	private float engineSize = 2.0F;// the consumption rate of the fuel
	private float fuelUsed = 14.0F;
	//DistanceTravelled += Vector3.Distance(transform.position, lastPosition);

	public float compute(float distance){
		float r = distance / fuelUsed;
		float g = 100/r; // converting KM/L to L/100KM
		float s = g * 2.825F; //to convert from KM/L to Miles/Gallon

		return s;
	}

	public float fuelUsed(float distance){
		float r = distance / fuelUsed;
		float u = distance * r;
		float g = 100/r; // converting KM/L to L/100KM
		float s = g * 2.825F; //to convert from KM/L to Miles/Gallon

		return s;
	}

	public static void main(String[] args){
		VehicleCosmeticManager vs = new VehicleCosmeticManager();


		Scanner sc = new Scanner(System.in);
		System.out.println("Enter distance driven");
		float r = vs.compute(sc.nextFloat());
		System.out.println(r);
	}
}
