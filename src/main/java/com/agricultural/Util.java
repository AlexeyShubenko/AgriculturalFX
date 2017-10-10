package com.agricultural;

import com.agricultural.dao.HibernateUtil;

import javax.persistence.EntityManager;

/**
 * Created by Alexey on 10.10.2017.
 */
public class Util implements Runnable {
    @Override
    public void run() {
        EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
        session.close();
    }
}
