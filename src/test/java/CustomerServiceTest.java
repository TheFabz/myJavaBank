import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.CheckingAccount;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.fail;

public class CustomerServiceTest {

    @Test
    public void testsUserServicesAndActions(){
        CustomerService customerService = new CustomerServiceImpl();
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        c1.setName("Rui");
        c2.setName("Sergio");
        Account account = new CheckingAccount();
        account.credit(100);
        c1.addAccount(account);
        c2.addAccount(account);

        customerService.add(c1);
        customerService.add(c2);

        // should add ids to model
        if (c1.getId() == null || c2.getId() == null) {
            fail();
        }

        // should get customer balance
        if (customerService.getBalance(c1.getId()) != account.getBalance() ||
                customerService.getBalance(c2.getId()) != account.getBalance()) {
            fail();
        }

        // should get customer account ids
        Set<Integer> c1aids = customerService.listCustomerAccountIds(c1.getId());
        Set<Integer> c2aids = customerService.listCustomerAccountIds(c2.getId());
        if (c1aids.size() != 1 || c2aids.size() != 1 ||
                !c1aids.contains(account.getId()) || !c2aids.contains(account.getId())) {
            fail();

        }

    }


}
