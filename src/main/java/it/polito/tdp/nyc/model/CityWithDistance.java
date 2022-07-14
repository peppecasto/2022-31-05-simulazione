package it.polito.tdp.nyc.model;

public class CityWithDistance {
String name;
Double distanza;
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
public void setDistanza(Double distanza) {
	this.distanza = distanza;
}
@Override
public String toString() {
	return "CityWithDistance [name=" + name + ", distanza=" + distanza + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((distanza == null) ? 0 : distanza.hashCode());
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
	CityWithDistance other = (CityWithDistance) obj;
	if (distanza == null) {
		if (other.distanza != null)
			return false;
	} else if (!distanza.equals(other.distanza))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}
public CityWithDistance(String name, Double distanza) {
	super();
	this.name = name;
	this.distanza = distanza;
}

}
