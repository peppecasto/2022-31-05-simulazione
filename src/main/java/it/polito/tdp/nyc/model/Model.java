package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.model.CityWithDistance;
import it.polito.tdp.nyc.db.NYCDao;

public class Model {
private NYCDao dao;
private Graph<City, DefaultWeightedEdge> g;


	
public Model() {
	super();
	this.dao = new NYCDao();
}



public List<Provider> getAllProviders(){
	return this.dao.getAllProviders();
}

public void creaGrafo(Provider p) {
	this.g = new SimpleWeightedGraph<City, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	Graphs.addAllVertices(this.g, dao.getAllCitiesWithThatProvider(p));
	System.out.println(this.g.vertexSet().toString());
	List<City> daCollegare = new ArrayList<City>();
	daCollegare = dao.getCitiesCoordinates(p);
	List<Adiacenza> archi = new ArrayList<Adiacenza>();
	
	for(City c1 : daCollegare) {
		for(City c2 : daCollegare) {
			if(!c1.equals(c2)) {
			Double peso = LatLngTool.distance(c1.getPosizione(), c2.getPosizione(), LengthUnit.KILOMETER);
			Adiacenza a = new Adiacenza(c1, c2, peso);
			archi.add(a);
			Graphs.addEdge(this.g, c1, c2, peso);
		}}
	}
	System.out.println("Numero archi del grafo: " + this.g.edgeSet().size());
}



public int getNVertici() {
	// TODO Auto-generated method stub
	return this.g.vertexSet().size();
}

public int getNArchi() {
	return this.g.edgeSet().size();
}



public List<City> getAllCitiesWithThatProvider() {
	// TODO Auto-generated method stub
	List<City> citiesWithThatP =  new ArrayList<City>();
	citiesWithThatP.addAll(this.g.vertexSet());
	return citiesWithThatP ;
	}







public List<CityWithDistance> getCityVicineWithTheirDistances(City selezionata) {
	List<CityWithDistance> distanze = new ArrayList<CityWithDistance>();
	List<City> vicine = Graphs.neighborListOf(this.g, selezionata);
	for(City v : vicine) {
		distanze.add(new CityWithDistance(v.getName(), this.g.getEdgeWeight(this.g.getEdge(selezionata, v))));
	}
	Collections.sort(distanze, new Comparator<CityWithDistance>() {

		@Override
		public int compare(CityWithDistance o1, CityWithDistance o2) {
			// TODO Auto-generated method stub
			return o1.getDistanza().compareTo(o2.getDistanza());
		}
	}
);
	return distanze;
}
	
	
	

	
	
}
