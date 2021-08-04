package com.unicamp.mc322.lab08.Tweet;

public enum Resolucao {
	res240p(426*240),
	res360p(640*360),
	res420p(854*460),
	res720p(1280*720),
	res1080p(1080*1920),
	res2k(2048*1080),
	res4k(3840*2160);
	
	private int numeroPixels;
	
	private Resolucao(int numeroPixels) {
		this.numeroPixels = numeroPixels;
	}
	
	public int retornarNumPixels() {
		return numeroPixels;
	}
}
