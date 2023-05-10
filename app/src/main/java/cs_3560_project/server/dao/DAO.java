package cs_3560_project.server.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import cs_3560_project.server.model.*;
import jakarta.transaction.Transaction;

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

  public static void closeFactory() {
    factory.close();
  }

  /**
   * @param <T> type of entity to save to database
   * @param newEntity - entity to save to database
   */
  public static <T> void create(T newEntity) {

    Session session = factory.openSession();
    org.hibernate.Transaction transaction = session.beginTransaction();

    session.persist(newEntity);

    transaction.commit();
    session.close();
  }

  /**
   * @param <T> type of entity to search
   * @param entityType - class of entity to be searched
   * @param id - primary key to search database with for desired entity instance
   * @return entity object if found in database
   * @throws EntityNotFoundException if object could not be found in database
   */
  public static <T> T read(Class<T> entityType, Object id) throws EntityNotFoundException {
    var entityWrapper = new Object() {
      T entity;
    };
    factory.inTransaction(session -> {
      entityWrapper.entity = session.get(entityType, id);
    });

    if (entityWrapper.entity == null) {
      throw new EntityNotFoundException("Entity of type \"" + entityType.getSimpleName()
          + "\" with primary key \"" + id + "\" could not be found in the database.");
    }

    return entityWrapper.entity;
  }

  /**
   * @param <T> type of entity to update in database
   * @param entity to update properties inside database
   */
  public static <T> void update(T entity) {
    factory.inTransaction(session -> {
      session.merge(entity);
    });
  }

  /**
   * @param <T> type of entity to delete from database
   * @param entity to delete from database
   */
  public static <T> void delete(T entity) {
    factory.inTransaction(session -> {
      session.remove(entity);
    });
  }
}
