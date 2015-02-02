package modelo;

import hibernate.HibernateUtil;
import hibernate.Inmueble;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ModeloInmueble {

    public static List<Inmueble> get() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String hql = "from Inmueble";
        Query q = session.createQuery(hql);
        List<Inmueble> pruebas = q.list();
        
        session.getTransaction().commit();
        session.close();
        return pruebas;
    }
    
    public static Inmueble getParametro(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Inmueble p = (Inmueble) session.get(Inmueble.class,Integer.parseInt(id));
        
        session.getTransaction().commit();
        session.close();
        return p;
    }

    public static void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Inmueble p = (Inmueble) session.load(Inmueble.class,Integer.parseInt(id));
        session.delete(p);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Inmueble p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(p);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void update(Inmueble p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(p);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
