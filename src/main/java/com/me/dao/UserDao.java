///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.me.dao;
//
//import com.me.pojo.User;
//import java.util.ArrayList;
//import java.util.List;
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
///**
// *
// * @author 18572
// */
//@Repository
//public class UserDao {
//
//    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//    private Session session = null;
//
//    private Session getSession() {
//        if (session == null || !session.isOpen()) {
//            session = sf.openSession();
//        }
//        return session;
//    }
//
//    private void beginTransaction() {
//        getSession().beginTransaction();
//    }
//
//    private void commit() {
//        getSession().getTransaction().commit();
//    }
//
//    private void close() {
//        getSession().close();
//    }
//
//    private void rollbackTransaction() {
//        getSession().getTransaction().rollback();
//    }
//
//    public List<User> getUsers() {
//        List<User> messages = new ArrayList<>();
//        try {
//            beginTransaction();
//            //Query q = getSession().createQuery("from Message where userName= :username");
//            Query q = getSession().createQuery("from user");
//            messages = q.list();
//            commit();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            rollbackTransaction();
//        } finally {
//            close();
//        }
//        return messages;
//    }
//
//    public User getUserByName(String username){
//        try{
//            beginTransaction();
//            Query q = getSession().createQuery("from User where username= :username");
//            q.setString("username", username);
//            User user = (User) q.uniqueResult();
//            return user;
//        } catch(HibernateException ex){
//            ex.printStackTrace();
//            rollbackTransaction();
//        } finally{
//            close();
//        }
//        return null;
//    }
//
//    public int addUser(User user) {
//        int result = 0;
//        try {
//            beginTransaction();
//            getSession().save(user);
//            commit();
//            result = 1;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            rollbackTransaction();
//        } finally {
//            close();
//        }
//        return result;
//    }
//
//    public int updateUser(long id, double price, String ISBN, String author, String title){
//        int result = 0;
//        try{
//            beginTransaction();
//            Query q = getSession().createQuery("from Book where id= :id");
//            q.setLong("id", id);
//            User book = (User) q.uniqueResult();
//
//            getSession().save(book);
//            commit();
//            result = 1;
//        } catch(HibernateException ex){
//            ex.printStackTrace();
//            rollbackTransaction();
//        } finally{
//            close();
//        }
//        return result;
//    }
//
//    public int deleteUserById(long id) {
//        int result = 0;
//        try {
//            beginTransaction();
//            Query q = getSession().createQuery("from Book where id= :id");
//            q.setLong("id", id);
//            User bookToDelete = (User) q.uniqueResult();
//            getSession().delete(bookToDelete);
//            commit();
//            result = 1;
//        } catch (HibernateException e) {
//            e.printStackTrace();
//            rollbackTransaction();
//        } finally {
//            close();
//        }
//        return result;
//    }
//}
