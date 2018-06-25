package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.gotoContactPage();
        app.getContactHelper().fillContactForm(new ContactData("John", "Travolta", "18887779090", "johntravolta777@gmail.com", "test1"), true);
        app.getContactHelper().enterNewContact();
        app.getContactHelper().returnToHomePage();
    }

}
