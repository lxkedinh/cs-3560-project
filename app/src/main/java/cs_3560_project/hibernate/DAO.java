package cs_3560_project.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import cs_3560_project.server.model.*;

public class DAO {
  private static SessionFactory factory =
      new Configuration().configure().addAnnotatedClass(Author.class).addAnnotatedClass(Book.class)
          .addAnnotatedClass(Creator.class).addAnnotatedClass(Director.class)
          .addAnnotatedClass(Documentary.class).addAnnotatedClass(Item.class)
          .addAnnotatedClass(Loan.class).addAnnotatedClass(Person.class)
          .addAnnotatedClass(Student.class).buildSessionFactory();

  public static SessionFactory getFactory() {
    return factory;
  }

  /**
   * @param newEntity - entity to add to the database
   */
  public static <T> void create(T newEntity) {
    factory.inTransaction(session -> {
      session.persist(newEntity);
    });
  }

  /**
   * @param entityType - class type of entity to be searched
   * @param id - primary key to search database with for desired entity instance
   * @return entity object if found in database
   * @throws NullPointerException if object could not be found in database
   */
  public static <T> T read(Class<T> entityType, Object id) throws NullPointerException {
    var entityWrapper = new Object() {
      T entity;
    };
    factory.inTransaction(session -> {
      entityWrapper.entity = session.get(entityType, id);

      if (entityWrapper.entity == null) {
        throw new NullPointerException("Entity could not be found in the database.");
      }
    });

    return entityWrapper.entity;
  }

  /**
   * @param entity to update properties inside database
   */
  public static <T> void update(T entity) {
    factory.inTransaction(session -> {
      session.merge(entity);
    });
  }

  /**
   * @param entity to delete from database
   */
  public static <T> void delete(T entity) {
    factory.inTransaction(session -> {
      session.remove(entity);
    });
  }

  public static void closeFactory() {
    factory.close();
  }

}
