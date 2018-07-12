package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        ArrayList<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("John", "Travolta", null, null, null);
        app.getContactHelper().gotoAddContactPage();
        app.getContactHelper().createContact(contact);
        ArrayList<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
