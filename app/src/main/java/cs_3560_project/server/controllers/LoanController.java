package cs_3560_project.server.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.hibernate.SessionFactory;
import cs_3560_project.server.dao.DAO;
import cs_3560_project.server.dao.EntityNotFoundException;
import cs_3560_project.server.model.Item;
import cs_3560_project.server.model.ItemStatus;
import cs_3560_project.server.model.Loan;
import cs_3560_project.server.model.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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

  public static List<Loan> fetchOverdueLoans() throws EntityNotFoundException {
    List<Loan> allLoans = fetchAllLoans();
    List<Loan> overdueLoans = new ArrayList<>();

    for (Loan l : allLoans) {
      if (l.getDueDate().compareTo(LocalDate.now()) > 0) {
        overdueLoans.add(l);
      }
    }

    if (overdueLoans.size() == 0) {
      throw new EntityNotFoundException("There are no overdue loans.");
    }

    return overdueLoans;
  }

  public static List<Loan> fetchAllLoans() throws EntityNotFoundException {
    var wrapper = new Object() {
      List<Loan> loans = null;
    };

    SessionFactory factory = DAO.getFactory();
    factory.inTransaction(session -> {
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
      Root<Loan> root = cr.from(Loan.class);
      cr.select(root);
      wrapper.loans = session.createQuery(cr).getResultList();
    });

    if (wrapper.loans.size() == 0) {
      throw new EntityNotFoundException("There are currently no loans in the system.");
    }

    return wrapper.loans;
  }

  public static List<Loan> fetchReturnedLoans() throws EntityNotFoundException {
    var wrapper = new Object() {
      List<Loan> loans = null;
    };

    SessionFactory factory = DAO.getFactory();
    factory.inTransaction(session -> {
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
      Root<Loan> root = cr.from(Loan.class);
      cr.select(root).where(root.get("return_date").isNotNull());
      wrapper.loans = session.createQuery(cr).getResultList();
    });

    if (wrapper.loans.size() == 0) {
      throw new EntityNotFoundException("No loans have been returned yet.");
    }

    return wrapper.loans;
  }

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

  public static List<LoanReportEntry> generateFinancialReport() throws EntityNotFoundException {
    List<Loan> loans = fetchReturnedLoans();
    loans.sort(new LoanComparator());
    List<LoanReportEntry> report = new ArrayList<>();

    for (Loan l : loans) {
      Student s = l.getStudent();
      Item i = l.getItem();
      LoanReportEntry entry = new LoanReportEntry(l.getNumber(), l.getReturnDate(), s.getName(),
          s.getBroncoId(), l.getTotalPrice(), i.getTitle(), i.getCode());
      report.add(entry);
    }

    return report;
  }

}


// utility class to sort loans from most recent to oldest
class LoanComparator implements Comparator<Loan> {
  @Override
  public int compare(Loan a, Loan b) {
    LocalDate aDate = a.getLoanDate();
    LocalDate bDate = b.getLoanDate();

    // a is older than b
    if (aDate.compareTo(bDate) < 0) {
      return 1;
      // a is more recent than b
    } else if (aDate.compareTo(bDate) > 0) {
      return -1;
    } else {
      return 0;
    }
  }
}
