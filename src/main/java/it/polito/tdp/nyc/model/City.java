package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class City {
String name;
Double coordX;
Double coordY;
LatLng posizione;
/**
 * @return the posizione
 */
public Double getCoordX() {
	return coordX;
}

/**
 * @param posizione the posizione to set
 */
public void setCoordX(Double coordX) {
	this.coordX = coordX;
}

/**
 * @return the coordY
 */
public Double getCoordY() {
	return coordY;
}

/**
 * @param coordY the coordY to set
 */
public void setCoordY(double coordY) {
	this.coordY = coordY;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	City other = (City) obj;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}

@Override
public String toString() {
	return "City: " + name;
}

public City(String name) {
	super();
	this.name = name;
	
}
public City(String name, Double coordX, Double coordY) {
	super();
	this.name = name;
this.coordX=coordX;
this.coordY=coordY;
this.posizione = new LatLng(coordX, coordY);
}

/**
 * @return the posizone
 */
public LatLng getPosizione() {
	return posizione;
}

/**
 * @param posizone the posizone to set
 */
public void setPosizione(LatLng posizione) {
	this.posizione = posizione;
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
}
