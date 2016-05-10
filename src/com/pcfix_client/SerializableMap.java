package com.pcfix_client;

import java.io.Serializable;
import java.util.Map;

public class SerializableMap implements Serializable {
	 private Map<String,Object> map;
	 
	    public SerializableMap(Map<String, Object> map2) {
		// TODO Auto-generated constructor stub
	    	map = map2;
	    }

		public SerializableMap() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Map<String, Object> getMap() {
	        return map;
	    }
	 
	    public void setMap(Map<String, Object> map) {
	        this.map = map;
	    }
}
