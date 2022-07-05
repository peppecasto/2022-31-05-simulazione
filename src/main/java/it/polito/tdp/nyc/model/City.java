package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;

public class City {
	public String name;
	private LatLng posizione;
	private int nHotSpot;
	



	public City(String name, LatLng posizione, int nHotSpot) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
		this.posizione=posizione;
		this.nHotSpot = nHotSpot;
	}

	/**
	 * @return the posizione
	 */
	public LatLng getPosizione() {
		return posizione;
	}

	/**
	 * @param posizione the posizione to set
	 */
	public void setPosizione(LatLng posizione) {
		this.posizione = posizione;
	}

	/**
	 * @return the nHotSpot
	 */
	public int getnHotSpot() {
		return nHotSpot;
	}

	/**
	 * @param nHotSpot the nHotSpot to set
	 */
	public void setnHotSpot(int nHotSpot) {
		this.nHotSpot = nHotSpot;
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

	@Override
	public String toString() {
		return name;
}
}
