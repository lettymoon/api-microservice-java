package ibm.javer.javer.domain.repository;

import ibm.javer.javer.domain.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Usuarios {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Usuario save(Usuario usuario){
        entityManager.persist(usuario);
        return usuario;
    }

    @Transactional
    public Usuario update(Usuario usuario){
        entityManager.merge(usuario);
        return usuario;
    }

    @Transactional
    public void delete(Usuario usuario){
        if(!entityManager.contains(usuario)){
            usuario = entityManager.merge(usuario);
        }
        entityManager.remove(usuario);
    }

    @Transactional
    public void delete(Integer id){
        Usuario usuario = entityManager.find(Usuario.class, id);
        delete(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> searchToName(String nome){
        String jpql = "select c from Usuario c where c.nome like :nome";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("nome", "%" + nome +"%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Usuario> getAll(){
        return entityManager.createQuery("from Cliente", Usuario.class).getResultList();
    }

}
