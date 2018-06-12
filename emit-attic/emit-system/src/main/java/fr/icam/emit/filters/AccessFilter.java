package fr.icam.emit.filters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.github.jeromerocheteau.JdbcFilter;

import fr.icam.emit.entities.Access;
import fr.icam.emit.entities.Feature;
import fr.icam.emit.entities.Instrument;
import fr.icam.emit.entities.Measure;
import fr.icam.emit.entities.Token;

public class AccessFilter extends JdbcFilter {

	@Override
	protected void doFill(PreparedStatement statement, ServletRequest request) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uuid = httpRequest.getHeader("X-Access-Token");
		statement.setString(1, uuid);
	}
	
	@Override
	protected void doMap(ServletRequest request, ResultSet resultSet) throws Exception {
		if (resultSet.next()) {
			Long id = resultSet.getLong("id");
			Timestamp issued = resultSet.getTimestamp("issued");

			Long tokenId = resultSet.getLong("tokenId");
			String tokenUuid = resultSet.getString("tokenUuid");
			String tokenUsername = resultSet.getString("tokenUsername");
			
			Long featureId = resultSet.getLong("featureId");
			String featureName = resultSet.getString("featureName");
			Integer featureFactor = resultSet.getInt("featureFactor");

			Long measureId = resultSet.getLong("measureId");
			String measureName = resultSet.getString("measureName");
			String measureUnit = resultSet.getString("measureUnit");

			Long instrumentId = resultSet.getLong("instrumentId");
			String instrumentUri = resultSet.getString("instrumentUri");
			String instrumentName = resultSet.getString("instrumentName");
			
			Instrument instrument = new Instrument(instrumentId, null, instrumentUri, instrumentName); 
			Measure measure = new Measure(measureId, null, measureName, measureUnit);
			Feature feature = new Feature(featureId, null, featureName, featureFactor, measure, instrument);
			Token token = new Token(tokenId, null, tokenUuid, tokenUsername, feature);
			Access access = new Access(id, issued.getTime(), token);
			
			request.setAttribute("access", access);
		} else {
			throw new ServletException("token-access");
		}
	}
	
}
