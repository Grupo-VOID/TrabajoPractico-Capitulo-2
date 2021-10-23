package dao;

import java.sql.ResultSet;

import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	public int updateDineroDisponible(Usuario usuario);
	public int updateTiempoDisponible(Usuario usuario);
	public Usuario buscarPorNombre(String nombre);
	public Usuario buscarPorId(int id);

}