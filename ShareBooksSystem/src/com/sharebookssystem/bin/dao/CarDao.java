package com.sharebookssystem.bin.dao;

import org.hibernate.SessionFactory;

public class CarDao {
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public CarDao(){

    }

}
