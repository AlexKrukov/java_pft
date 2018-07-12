package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("John", "Travolta", "18887779090", "johntravolta777@gmail.com", "test1"));
    }
    ArrayList<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactEdit(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "John", "Travolta", "18887779090", "johntravolta777@gmail.com", null);
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().updateContactInfo();
    app.getContactHelper().returnToHomePage();
    ArrayList<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
