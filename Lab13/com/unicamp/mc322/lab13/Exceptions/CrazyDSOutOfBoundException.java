package com.unicamp.mc322.lab13.Exceptions;

public class CrazyDSOutOfBoundException extends IndexOutOfBoundsException {

	private static final long serialVersionUID = -1876592231727993189L;

	public CrazyDSOutOfBoundException() {
	}

	public CrazyDSOutOfBoundException(String s) {
		super(s);
	}

	public CrazyDSOutOfBoundException(int index) {
		super(index);
	}

}
