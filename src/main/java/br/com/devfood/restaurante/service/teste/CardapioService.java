package br.com.devfood.restaurante.service.teste;

import br.com.devfood.restaurante.dao.CardapioDao;
import br.com.devfood.restaurante.util.CargaDeDadosUtil;
import br.com.devfood.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class CardapioService {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManagerdevFood();
        entityManager.getTransaction().begin();
        CargaDeDadosUtil.cadastrarCategoria(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);
        System.out.println("O produto pesquisado foi: " + cardapioDao.consultarPorNome("moqueca"));
        entityManager.close();

    }
}
