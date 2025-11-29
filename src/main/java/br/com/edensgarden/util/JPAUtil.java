package br.com.edensgarden.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("projeto-dsw-pu");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}