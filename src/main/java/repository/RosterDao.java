package repository;

import entity.Driver;
import entity.Roster;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class RosterDao {
    public List<Roster> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(" select r from Roster r ").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Roster> getRostersByDriverId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Roster> query = session.createQuery("select s from Roster s where s.driver.id = :p_driver_id");
            query.setParameter("p_driver_id", id);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Driver> getAllDriver() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(" select distinct r.driver from Roster r ").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Roster findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Roster> query = session.createQuery("select s from Roster s where s.id = :p_roster_id");
            query.setParameter("p_roster_id", id);
            return query.getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    public List<Roster> findByDriverId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Roster> query = session.createQuery("select s from Roster s where s.driver.id = :p_driver_id");
            query.setParameter("p_driver_id", id);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return null;
    }

    public boolean insert(Roster roster) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(roster);
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
            Roster student = session.load(Roster.class, id);
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

