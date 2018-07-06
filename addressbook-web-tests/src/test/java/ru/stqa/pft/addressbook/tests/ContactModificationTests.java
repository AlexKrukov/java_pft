package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("John", "Travolta", "18887779090", "johntravolta777@gmail.com", "test1"));
    }
    app.getContactHelper().initContactEdit();
    app.getContactHelper().fillContactForm(new ContactData("John", "Travolta", "18887779090", "johntravolta777@gmail.com", null));
    app.getContactHelper().updateContactInfo();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
