package model;

public class TipoAtraccion {

	String tematica;
	
	//DRAMA, COMEDIA, MUSICAL, POLICIAL, ARGENTINA;
//FALTA CREAR LOS TIPO DE ATRACCION
	
	public TipoAtraccion(String tematica) {
		this.tematica = tematica;
	}

	@Override
	public String toString() {
		return "TipoAtraccion [tematica=" + tematica + "]";
	}

}