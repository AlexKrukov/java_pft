package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification() {
        app.getContactHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("John", "Travolta", "18887779090", "johntravolta777@gmail.com", null), false);
        app.getContactHelper().updateContactInfo();
        app.getContactHelper().returnToHomePage();
    }
}
