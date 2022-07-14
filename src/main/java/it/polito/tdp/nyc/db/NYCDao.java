package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.nyc.model.Adiacenza;
import it.polito.tdp.nyc.model.City;
import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.Provider;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}

public List<Provider> getAllProviders(){
	String sql = "SELECT DISTINCT Provider  FROM nyc_wifi_hotspot_locations";
	List<Provider> result = new ArrayList<>();
	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet res = st.executeQuery();

		while (res.next()) {
			result.add(new Provider(res.getString("Provider")));
		}
		
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("SQL Error");
	}

	return result;
}

public List<City> getAllCitiesWithThatProvider(Provider p){
	String sql = "SELECT DISTINCT City " + "FROM nyc_wifi_hotspot_locations " + "WHERE Provider = ?";
	List<City> result = new ArrayList<>();
	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, p.getName());
		ResultSet res = st.executeQuery();

		while (res.next()) {
			result.add(new City(res.getString("City")));
		}
		
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("SQL Error");
	}
	return result;
	
}

public List<City> getCitiesCoordinates(Provider p){
	String sql = "SELECT DISTINCT City, AVG(Latitude), AVG(Longitude) " + "FROM nyc_wifi_hotspot_locations " + "WHERE Provider = ? " +  "GROUP BY City";
	List<City> result = new ArrayList<>();
	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, p.getName());
		ResultSet res = st.executeQuery();

		while (res.next()) {
			result.add(new City(res.getString("City"), res.getDouble("AVG(Latitude)"), res.getDouble("AVG(Longitude)")));
		}
		
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("SQL Error");
	}
	return result;
	
}


	
	
}
