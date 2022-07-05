package it.polito.tdp.nyc.model;

public class CityDistance {
	private String name;
	
	private Double distanza;

	public CityDistance(String name, double distanza) {
		super();
		this.name = name;
		this.distanza = distanza;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the distanza
	 */
	public Double getDistanza() {
		return distanza;
	}

	/**
	 * @param distanza the distanza to set
	 */
	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	
	

	

}
