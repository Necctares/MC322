package com.unicamp.mc322.lab08.Tweet;

public enum Extensao {
	avi, wmv, flv, png, jpg, gif;

	public static boolean checarExtensaoPermitidaDeVideo(Extensao extensao) {
		switch (extensao) {
		case avi:
			return true;
		case wmv:
			return true;
		case flv:
			return true;
		default:
			return false;
		}
	}

	public static boolean checarExtensaoPermitidaDeImagem(Extensao extensao) {
		switch (extensao) {
		case png:
			return true;
		case jpg:
			return true;
		case gif:
			return true;
		default:
			return false;
		}
	}
}
