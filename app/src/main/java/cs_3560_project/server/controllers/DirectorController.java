package cs_3560_project.server.controllers;

import cs_3560_project.server.dao.DAO;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Director;

public class DirectorController {

  public static void insertDirector(Director director) {
    DAO.create(director);
  }

  public static Director fetchDirector(int id) throws EntityNotFoundException {
    return DAO.read(Director.class, id);
  }

  public static void updateDirector(Director updatedDirector) {
    DAO.update(updatedDirector);
  }

  public static void deleteDirector(int id) throws EntityNotFoundException {
    Director directorToDelete = DAO.read(Director.class, id);
    DAO.delete(directorToDelete);
  }
}
