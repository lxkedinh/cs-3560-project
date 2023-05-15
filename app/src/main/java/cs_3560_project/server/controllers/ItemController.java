package cs_3560_project.server.controllers;

import cs_3560_project.server.dao.DAO;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Book;
import cs_3560_project.server.model.Documentary;
import cs_3560_project.server.model.Item;
import cs_3560_project.server.model.ItemStatus;

public class ItemController {

  public static void insertBook(Book book) {
    DAO.create(book);
  }

  public static void insertDocumentary(Documentary documentary) {
    DAO.create(documentary);
  }

  public static Book fetchBook(int code) throws EntityNotFoundException {
    return DAO.read(Book.class, code);
  }

  public static Documentary fetchDocumentary(int code) throws EntityNotFoundException {
    return DAO.read(Documentary.class, code);
  }

  public static void updateBook(Book book) {
    DAO.update(book);
  }

  public static void updateDocumentary(Documentary documentary) {
    DAO.update(documentary);
  }

  public static void deleteBook(int code) throws EntityNotFoundException {
    Book bookToDelete = DAO.read(Book.class, code);
    DAO.delete(bookToDelete);
  }

  public static void deleteDocumentary(int code) throws EntityNotFoundException {
    Documentary documentaryToDelete = DAO.read(Documentary.class, code);
    DAO.delete(documentaryToDelete);
  }

  public static <T extends Item> ItemStatus checkAvailability(Class<T> itemType, int code)
      throws EntityNotFoundException {
    T itemToCheck = DAO.read(itemType, code);
    return itemToCheck.getStatus();
  }

  public static void updateAvailability(int code, ItemStatus newStatus)
      throws EntityNotFoundException {
    Item itemToUpdate = DAO.read(Item.class, code);
    itemToUpdate.setStatus(newStatus);
    DAO.update(itemToUpdate);
  }
}
