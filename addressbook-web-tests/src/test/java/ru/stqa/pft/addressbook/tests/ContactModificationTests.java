package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("John", "Travolta", "18887779090", "johntravolta777@gmail.com", "test1"));
    }
    ArrayList<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactEdit(0);
    app.getContactHelper().fillContactForm(new ContactData("John", "Travolta", "18887779090", "johntravolta777@gmail.com", null));
    app.getContactHelper().updateContactInfo();
    app.getContactHelper().returnToHomePage();
    ArrayList<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
