package conn.sample;

public class Vehicle {
    private int id;
    private String type;
    private String photo;
    private double chargePerDay;

    public Vehicle(int id, String type, String photo, double chargePerDay) {
        this.id = id;
        this.type = type;
        this.photo = photo;
        this.chargePerDay = chargePerDay;
    }
    
  	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getChargePerDay() {
		return chargePerDay;
	}

	public void setChargePerDay(double chargePerDay) {
		this.chargePerDay = chargePerDay;
	}


    
    
}
