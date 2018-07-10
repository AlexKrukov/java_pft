package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

  public void initContactEdit(int index) {
    wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img")).get(index).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContactInfo() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void confirmDeletingContact() {
    wd.switchTo().alert().accept();
  }

  public void gotoAddContactPage() {
    click(By.linkText("add new"));
  }

  public void createContact(ContactData group) {
    gotoAddContactPage();
    fillContactForm(group);
    enterNewContact();
    returnToHomePage();
  }

  public boolean isThereAContact() {
return isElementPresent(By.name("selected[]"));
  }

    public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
    }

  public ArrayList<ContactData> getContactList() {
    ArrayList<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("[name = entry]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String first_name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String last_name = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      ContactData contact = new ContactData(id, first_name, last_name, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
