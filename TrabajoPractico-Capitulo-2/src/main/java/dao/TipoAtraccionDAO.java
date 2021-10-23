package dao;

public interface TipoAtraccionDAO extends GenericDAO<String> {

	public String buscarPorId(int id);
}
