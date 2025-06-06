package br.com.devfood.restaurante.dao;

import br.com.devfood.restaurante.entity.Endereco;
import br.com.devfood.restaurante.vo.ClienteVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class EnderecoDao {
    private EntityManager entityManager;
    public EnderecoDao(EntityManager entityManager){this.entityManager = entityManager;}

    public void cadastrar(final Endereco endereco){
        this.entityManager.persist(endereco);
    }

    public Endereco consultarPorId(final  Integer id){
        return this.entityManager.find(Endereco.class,id);
    }
    public List<Endereco> consultarTodos(){
        String sql = "SELECT c FROM Endereco c";
        return this.entityManager.createQuery(sql,Endereco.class).getResultList();
    }
    public List<ClienteVo> consultarClientes(final String estado, final String cidade, final String rua){
        String jpql = "SELECT new br.com.devfood.restaurante.vo.ClienteVo(e.cliente.clienteId.cpf,e.cliente.nome) " +
                " FROM Endereco e WHERE 1=1";
        if(Objects.nonNull(estado)){
            jpql = jpql.concat("AND UPPER (e.estado) = UPPER (:estado) ");
        }
        if(Objects.nonNull(cidade)){
            jpql = jpql.concat("AND UPPER (e.cidade) = UPPER (:cidade) ");
        }
        if(Objects.nonNull(rua)){
            jpql = jpql.concat("AND UPPER (e.rua) = UPPER (:rua) ");
        }

        TypedQuery typedQuery =  this.entityManager.createQuery(jpql,ClienteVo.class);
        if(Objects.nonNull(estado)){
            typedQuery.setParameter("estado",estado);
        }
        if(Objects.nonNull(cidade)){
            typedQuery.setParameter("cidade",cidade);
        }
        if(Objects.nonNull(rua)){
            typedQuery.setParameter("rua",rua);
        }
        return typedQuery.getResultList();
    }


    public void atualizar(final Endereco endereco){
        this.entityManager.merge(endereco);
    }

    public void excluir(final Endereco endereco){
        this.entityManager.remove(endereco);
    }



}

