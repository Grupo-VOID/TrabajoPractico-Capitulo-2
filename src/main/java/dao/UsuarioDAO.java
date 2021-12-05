package dao;

import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	public int updateUsuario(Usuario usuario);

	public Usuario buscarPorId(int id);

//	public int encontrarIdUsuario(Usuario usuario);

}