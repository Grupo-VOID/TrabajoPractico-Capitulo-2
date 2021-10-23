package dao;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}

	public static AtraccionDAO getAtraccionesDAO() {
		return new AtraccionDAOImpl();
	}

	public static PromocionDAO getPromocionesDAO() {
		return new PromocionDAOImpl();
	}
	
	public static TipoAtraccionDAO getTipoAtraccionDAO() {
		return new TipoAtraccionDAOImpl();
	}

}