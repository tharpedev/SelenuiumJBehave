package org.qas.jbehave.example.steps;

import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.OutcomesTable;
import org.qas.jbehave.example.PhoneStore;
import org.qas.jbehave.example.model.PhoneInfo;

import java.util.ArrayList;
import java.util.Map;


public class PhoneStoreSteps {
    private PhoneStore _phoneStore = new PhoneStore();
    private ArrayList<PhoneInfo> _context = new ArrayList<PhoneInfo>();

    @Given("product list: $phoneTable")
    public void productList(ExamplesTable phoneTable ) {
        _phoneStore.emptyStore();
        for (Map<String, String> row : phoneTable.getRows()) {
            String name = row.get("name");
            double price = Double.parseDouble(row.get("price"));
            _phoneStore.addPhone(new PhoneInfo(name, price));
        }
    }
    @When("search product by price greater or equal $price")
    public void searchProductByPriceGreaterOrEqual(@Named("$price")double price) {
        _context = _phoneStore.searchByPriceGreaterOrEqual(price);
    }


    @Then("result are: $phoneTable")
    public void compareResultByPrice(ExamplesTable phoneTable) {
        int index = 0;
        for (Map<String, String> row : phoneTable.getRows()) {
            String name = row.get("name");
            double price = Double.parseDouble(row.get("price"));
            PhoneInfo phone = _context.get(index);
            assert (0 == phone.getName().compareToIgnoreCase(name) && 0 == Double.compare(phone.getPrice(), price));
            index ++;
        }
    }


    @When("search product by name $name")
    public void searchProductByName(String name) {
        _context = _phoneStore.searchByName(name);
    }

    @Then("name of filtered products must contain $name")
    public void nameOfFilteredProductsMustContain(String name) {
        OutcomesTable outComes = new OutcomesTable();
        for (PhoneInfo phone : _context) {
            outComes.addOutcome(phone.getName(), phone.getName(), Matchers.containsString(name));
        }
        outComes.verify();
    }

}
