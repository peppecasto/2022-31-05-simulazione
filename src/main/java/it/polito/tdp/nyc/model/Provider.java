package it.polito.tdp.nyc.model;

public class Provider {
public String name;

public Provider(String name) {
	super();
	this.name = name;
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
	return name + "\n";
}

}
