package repository;

import entity.Driver;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DriverDao {
    public List<Driver> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Driver").list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Driver findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Driver> query = session.createQuery("select s from Driver s where s.id = :p_driver_id");
            query.setParameter("p_driver_id", id);
            return query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    public boolean insert(Driver student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {

        }
        return false;
    }

    public boolean removeDriver(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Driver student = session.load(Driver.class, id);
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

