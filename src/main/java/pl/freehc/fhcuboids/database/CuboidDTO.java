package pl.freehc.fhcuboids.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.freehc.fhcuboids.App;

import java.util.List;

public class CuboidDTO {
    private static final SessionFactory factory;
    static {
        HibernateFactory hibernateFactory = new HibernateFactory();
        factory = hibernateFactory.getSessionFactory();
    }
    private Session getSession() {
        return factory.openSession();
    }

    public List<CuboidModel> GetAll() {
        if(App.getInst().cuboidCache.size() != 1) {
            Session session = getSession();
            try {
                Query query = session.createQuery("FROM CuboidModel");
                List<CuboidModel> response = query.list();
                App.getInst().cuboidCache.put("cuboids", response);
                return response;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            } finally {
                session.close();
            }
        }
        return App.getInst().cuboidCache.get("cuboids");
    }

    public void Add(CuboidModel cuboidModel) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(cuboidModel);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public void Update(CuboidModel cuboidModel) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(cuboidModel);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public void Delete(CuboidModel cuboidModel) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(cuboidModel);
            session.getTransaction().commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }


}
