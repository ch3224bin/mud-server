package com.jeff.mud.state.firststep;

public enum FirstStep {
	cc0,
	cc1,
	cc2,
	cc3,
	cc4,
	cc5,
	cc6
	;
	
	public static boolean contains(String str) {
	    for (FirstStep c : FirstStep.values()) {
	        if (c.name().equals(str)) {
	            return true;
	        }
	    }
	    return false;
	}
}
