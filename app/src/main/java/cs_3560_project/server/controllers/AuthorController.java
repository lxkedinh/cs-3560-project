package cs_3560_project.server.controllers;

import cs_3560_project.server.dao.DAO;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Author;

public class AuthorController {

  public static void insertAuthor(Author author) {
    DAO.create(author);
  }

  public static Author fetchAuthor(int id) throws EntityNotFoundException {
    return DAO.read(Author.class, id);
  }

  public static void updateAuthor(Author updatedAuthor) {
    DAO.update(updatedAuthor);
  }

  public static void deleteAuthor(int id) throws EntityNotFoundException {
    Author authorToDelete = DAO.read(Author.class, id);
    DAO.delete(authorToDelete);
  }
}
