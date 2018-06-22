package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDelitionTests extends TestBase {

    @Test

    public void testContactDelition() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeletingContact();
    }
}
