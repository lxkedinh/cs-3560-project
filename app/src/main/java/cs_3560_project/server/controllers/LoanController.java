package cs_3560_project.server.controllers;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.SessionFactory;
import cs_3560_project.server.dao.DAO;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Item;
import cs_3560_project.server.model.ItemStatus;
import cs_3560_project.server.model.Loan;
import jakarta.persistence.criteria.CriteriaQuery;

public class LoanController {

  public static void insertLoan(Loan loan) {
    DAO.create(loan);
  }

  public static Loan fetchLoan(int loanNumber) throws EntityNotFoundException {
    return DAO.read(Loan.class, loanNumber);
  }

  public static void updateLoan(Loan updatedLoan) {
    DAO.update(updatedLoan);
  }

  public static void deleteLoan(int loanNumber) throws EntityNotFoundException {
    Loan loanToDelete = DAO.read(Loan.class, loanNumber);
    DAO.delete(loanToDelete);
  }

  public static List<Loan> fetchOverdueLoans() {
    var loansWrapper = new Object() {
      List<Loan> overdueLoans = null;
    };

    SessionFactory factory = DAO.getFactory();
    factory.inTransaction(session -> {
      session.enableFilter("overdueFilter");
      CriteriaQuery<Loan> criteria = session.getCriteriaBuilder().createQuery(Loan.class);
      loansWrapper.overdueLoans = session.createQuery(criteria).getResultList();
    });

    return loansWrapper.overdueLoans;
  }

  public static List<Loan> fetchAllLoans() {
    var loansWrapper = new Object() {
      List<Loan> overdueLoans = null;
    };

    SessionFactory factory = DAO.getFactory();
    factory.inTransaction(session -> {
      CriteriaQuery<Loan> criteria = session.getCriteriaBuilder().createQuery(Loan.class);
      loansWrapper.overdueLoans = session.createQuery(criteria).getResultList();
    });

    return loansWrapper.overdueLoans;
  }

  // TODO: handle returning item
  public static void returnItem(int loanNumber) throws EntityNotFoundException {
    Loan loanToReturnItem = DAO.read(Loan.class, loanNumber);
    Item itemToReturn = loanToReturnItem.getItem();
    if (itemToReturn == null) {
      throw new EntityNotFoundException("This loan does not have an associated item.");
    }

    loanToReturnItem.calculateTotalPrice();
    loanToReturnItem.setReturnDate(LocalDate.now());
    itemToReturn.setStatus(ItemStatus.Available);
    DAO.update(loanToReturnItem);
    DAO.update(itemToReturn);
  }

  // TODO: generate financial reports
}
