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

import it.polito.tdp.nyc.model.CityDistance;
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

	public List<City> getAllCities(String provider) {
		// TODO Auto-generated method stub
final String sql = "SELECT DISTINCT City, AVG(Latitude) AS Lat, AVG(Longitude) AS Lng, COUNT(*) AS NUM " + "FROM nyc_wifi_hotspot_locations "
		+ "WHERE Provider= ? " + "GROUP BY City " + "ORDER BY City";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet res = st.executeQuery();
			//istanzio la lista e la riempio nel while
			List<City> result= new ArrayList<City>();

			while (res.next()) {
				result.add(new City(res.getString("City"), 
						new LatLng(res.getDouble("Lat"), res.getDouble("Lng")), res.getInt("NUM")));
				}
			conn.close();
			//torno la lista
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	


	
		
	

	public List<String> getAllProviders() {
		// TODO Auto-generated method stub
			final String sql = " select DISTINCT Provider "
					+ "from nyc_wifi_hotspot_locations "
					+ "order by Provider";
			
			
			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();
				//istanzio la lista e la riempio dentro il while coi providers
				List<String> result = new ArrayList<>();
				while (res.next()) {
					result.add(res.getString("Provider"));
				}
				conn.close(); 
				//torno la lista di providers
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("SQL Error");
			}
			
		
	}

	
	
}
