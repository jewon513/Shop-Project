package com.biz.jena.service;

import java.io.ByteArrayOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class JenaService {

	public final String endPoint = "http://data.visitkorea.or.kr/sparql";
	
	public final String prefix = "" + 
			"PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
			"PREFIX dc: <http://purl.org/dc/elements/1.1/> " + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#> " + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " + 
			"PREFIX vi: <http://www.saltlux.com/transformer/views#> " + 
			"PREFIX kto: <http://data.visitkorea.or.kr/ontology/> " + 
			"PREFIX ktop: <http://data.visitkorea.or.kr/property/> " + 
			"PREFIX ids: <http://data.visitkorea.or.kr/resource/> " + 
			"PREFIX wgs: <http://www.w3.org/2003/01/geo/wgs84_pos#> " + 
			"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " + 
			"PREFIX geo: <http://www.saltlux.com/geo/property#> " + 
			"PREFIX pf: <http://www.saltlux.com/DARQ/property#> ";	
	
	public String getNearAccommodation(String contentId) {
		
		String queryString = prefix;
		
		return null;
		
	}
	
	public String test() {
		
		String queryString = prefix;
		queryString += 
				"SELECT * " + 
				"WHERE {" + 
				" ?resource a kto:Event ; " + 
				"      rdfs:label ?name . " +  
				"} limit 100";
		
	
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(endPoint, query);
		
		ResultSet resultSet = null;
		
		resultSet = qexec.execSelect();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsJSON(outputStream, resultSet);
		
		String json = new String(outputStream.toByteArray());
		
		return json;
		
		
	}
	
}
