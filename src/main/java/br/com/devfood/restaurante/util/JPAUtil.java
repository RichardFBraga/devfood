package br.com.devfood.restaurante.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory DEVFOOD = Persistence.createEntityManagerFactory("devFood");

    public static EntityManager getEntityManagerdevFood(){
        return DEVFOOD.createEntityManager();
    }
}
