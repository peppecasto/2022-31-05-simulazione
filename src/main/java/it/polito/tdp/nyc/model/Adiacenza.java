package it.polito.tdp.nyc.model;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Adiacenza {
private City city1;
private City city2;

private Double peso;
public Adiacenza(City c1, City c2, double peso) {
	// TODO Auto-generated constructor stub
	if(!c1.equals(c2)) {
	this.city1 = c1;
	this.city2 = c2;
	
	this.peso = peso;
	}
}
/**
 * @return the city1
 */
public City getCity1() {
	return city1;
}
/**
 * @param city1 the city1 to set
 */
public void setCity1(City city1) {
	this.city1 = city1;
}
/**
 * @return the city2
 */
public City getCity2() {
	return city2;
}
/**
 * @param city2 the city2 to set
 */
public void setCity2(City city2) {
	this.city2 = city2;
}
/**
 * @return the peso
 */
public Double getPeso() {
	return peso;
}
/**
 * @param peso the peso to set
 */
public void setPeso(Double peso) {
	this.peso = peso;
}
@Override
public String toString() {
	return "Adiacenza [city1=" + city1 + ", city2=" + city2 + ", peso=" + peso + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((city1 == null) ? 0 : city1.hashCode());
	result = prime * result + ((city2 == null) ? 0 : city2.hashCode());
	result = prime * result + ((peso == null) ? 0 : peso.hashCode());
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
	Adiacenza other = (Adiacenza) obj;
	if (city1 == null) {
		if (other.city1 != null)
			return false;
	} else if (!city1.equals(other.city1))
		return false;
	if (city2 == null) {
		if (other.city2 != null)
			return false;
	} else if (!city2.equals(other.city2))
		return false;
	if (peso == null) {
		if (other.peso != null)
			return false;
	} else if (!peso.equals(other.peso))
		return false;
	return true;
}

}
