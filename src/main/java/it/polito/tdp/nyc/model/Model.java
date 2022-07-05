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

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
private NYCDao dao;
	
	private Graph<City,DefaultWeightedEdge> grafo;
	private List<String> providers;
	private List<City> cities;
	
	public List<String> getProviders(){
		if(this.providers==null) {
			this.dao = new NYCDao();
			this.providers = dao.getAllProviders();
		}
		return this.providers;
	}
	
	public String creaGrafo(String prov) {
		//istanzio oggetti che mi serviranno
		this.dao = new NYCDao();
		this.cities = dao.getAllCities(prov);
		//creo il grafo
		this.grafo = 
				new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.cities);
		
		//aggiungo gli archi
		for(City c1 : this.cities) {
			for(City c2 : this.cities) {
				if(!c1.equals(c2)) {
					double peso = LatLngTool.distance(c1.getPosizione(), c2.getPosizione(), LengthUnit.KILOMETER);
					Graphs.addEdge(this.grafo, c1, c2, peso);
				}
			}
		}
		
		return "Grafo creato con " + String.format("Numero di Vertici pari a: %d", 
				this.grafo.vertexSet().size()) + " e " + String.format("Numero di Archi pari a: %d", 
						this.grafo.edgeSet().size()) + "\n" ;
	}
	
	public List<City> getCities(){
		return cities;
	}
	
	public List<CityDistance> getCityDistances(City scelto){
		List<CityDistance> result= new ArrayList<>();
		List<City> verticiVicini = Graphs.neighborListOf(this.grafo, scelto);
		for(City v : verticiVicini) {
			result.add(new CityDistance(v.getName(), this.grafo.getEdgeWeight(this.grafo.getEdge(scelto, v))));
		}
		Collections.sort(result, new Comparator<CityDistance>() {

			@Override
			public int compare(CityDistance o1, CityDistance o2) {
				// TODO Auto-generated method stub
				return o1.getDistanza().compareTo(o2.getDistanza());
			}
		});
		return result;
		}
	
	public boolean grafoCreato() {
		if(this.grafo == null)
			return false;
		else 
			return true;
	}
	
	
	
	
	

	
	
}
