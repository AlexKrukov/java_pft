package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void enterNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {

    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());

    //if (creation) {
    //  new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    //} else {
    //  Assert.assertFalse(isElementPresent(By.name("new_group")));
    //}
  }

  public void initContactEditById(int index) {
    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContactInfo() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void confirmDeletingContact() {
    wd.switchTo().alert().accept();
  }

  public void gotoAddContactPage() {
    click(By.linkText("add new"));
  }

  public void create(ContactData group) {
    gotoAddContactPage();
    fillContactForm(group);
    enterNewContact();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactEditById(contact.getId());
    fillContactForm(contact);
    updateContactInfo();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmDeletingContact();
    contactCache = null;
  }

  public boolean isThereAContact() {
return isElementPresent(By.name("selected[]"));
  }

    public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
    }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("[name = entry]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String first_name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String last_name = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(first_name).withLastName(last_name));
    }
    return new Contacts(contactCache);
  }

}
