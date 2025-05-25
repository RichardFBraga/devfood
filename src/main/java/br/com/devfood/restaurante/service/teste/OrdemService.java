package br.com.devfood.restaurante.service.teste;

import br.com.devfood.restaurante.dao.ClienteDao;
import br.com.devfood.restaurante.dao.EnderecoDao;
import br.com.devfood.restaurante.util.CargaDeDadosUtil;
import br.com.devfood.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerdevFood();
        entityManager.getTransaction().begin();
        CargaDeDadosUtil.cadastrarCategoria(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CargaDeDadosUtil.cadastrarClientes(entityManager);
        CargaDeDadosUtil.cadastrarOrdensClientes(entityManager);
        EnderecoDao enderecoDao = new EnderecoDao(entityManager);
        System.out.println(enderecoDao.consultarClientes("SP", "São Paulo", null));

        ClienteDao clienteDao = new ClienteDao(entityManager);
        System.out.println(clienteDao.consultarPorNome("Ana"));
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
