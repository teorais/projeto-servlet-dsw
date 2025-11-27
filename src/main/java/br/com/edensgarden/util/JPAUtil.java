package br.com.edensgarden.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    // O nome "projeto-dsw-pu" DEVE ser igual ao que colocamos no persistence.xml
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("projeto-dsw-pu");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}