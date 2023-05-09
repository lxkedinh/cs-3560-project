package cs_3560_project.server.controllers;

import cs_3560_project.server.dao.DAO;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Student;

public class StudentController {

  public static void insertStudent(Student student) {
    DAO.create(student);
  }

  public static Student fetchStudent(int broncoId) throws EntityNotFoundException {
    return DAO.read(Student.class, broncoId);
  }

  public static void updateStudent(Student updatedStudent) {
    DAO.update(updatedStudent);
  }

  public static void deleteStudent(int broncoId) throws EntityNotFoundException {
    Student studentToDelete = DAO.read(Student.class, broncoId);
    DAO.delete(studentToDelete);
  }

  public static boolean hasCurrentLoans(int broncoId) throws EntityNotFoundException {
    Student studentToCheck = DAO.read(Student.class, broncoId);
    return studentToCheck.getLoan() != null;
  }

  public static boolean hasOverdueLoans(int broncoId) throws EntityNotFoundException {
    Student studentToCheck = DAO.read(Student.class, broncoId);
    return studentToCheck.getLoan() != null && studentToCheck.getLoan().isOverDue();
  }
}
