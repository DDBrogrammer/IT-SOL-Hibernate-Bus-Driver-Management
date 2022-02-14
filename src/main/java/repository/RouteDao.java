package repository;

import entity.Route;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class RouteDao {

    public List<Route> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Route").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Route findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Route> query = session.createQuery("select s from Route s where s.id = :p_route_id");
            query.setParameter("p_route_id", id);
            return query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(Route student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
        }
        return false;
    }

    public boolean removeRoster(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Route student = session.load(Route.class, id);
            session.delete(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

}


