import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;


public class HibernateUtil {

  private static final SessionFactory sessionFactory;
  private static final Configuration cfg;


  private static final String ENTITIES_PACKAGE = "fr.larbotech.entite";
  private static final String ANNOTATED_PACKAGE = "fr";
  private static final String HBM_DIALECT = "org.hibernate.dialect.HSQLDialect";

  private static Configuration getConfiguration() {
    Configuration conf = new Configuration();

    final Reflections reflections = new Reflections(ENTITIES_PACKAGE);
    reflections.getTypesAnnotatedWith(MappedSuperclass.class).forEach(conf::addAnnotatedClass);
    reflections.getTypesAnnotatedWith(Entity.class).forEach(conf::addAnnotatedClass);
    conf.addPackage(ANNOTATED_PACKAGE).setProperty(AvailableSettings.DIALECT, HBM_DIALECT);

    return conf;
  }

  static {
    try {

      cfg = getConfiguration();
     /* getCfg().addAnnotatedClass(Utilisateur.class);
      getCfg().addAnnotatedClass(Client.class);
      getCfg().addAnnotatedClass(EntiteCommerciale.class);
      getCfg().addAnnotatedClass(Fournisseur.class);
      getCfg().addAnnotatedClass(Langue.class);
      getCfg().addAnnotatedClass(Profil.class);
      getCfg().addAnnotatedClass(Role.class);
      getCfg().addAnnotatedClass(Site.class); */
      sessionFactory = getCfg().configure().buildSessionFactory();


    } catch (Throwable ex) {
      // Log the exception.
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  /**
   * @return the cfg
   */
  public static Configuration getCfg() {
    return cfg;
  }
}