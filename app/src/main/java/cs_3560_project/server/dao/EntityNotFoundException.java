package cs_3560_project.server.dao;

public class EntityNotFoundException extends Exception {
  public EntityNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
