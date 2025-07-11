package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class FortradeRPage extends BasePage {

    public FortradeRPage(AndroidDriver driver) {
        super(driver);
    }

    @FindBy(id = "FirstName")
    protected WebElement firstName;

    @FindBy(id = "LastName")
    protected WebElement lastName;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-Email lcFieldWrapper']//input[@id='EmailForm']")
    protected WebElement email;

    @FindBy(xpath = "//input[@name='PhoneCountryCode']")
    protected WebElement countryCode;

    @FindBy(xpath = "//div[@class='phoneWrapper']//input[@placeholder='Phone']")
    protected WebElement phoneNumber;

    @FindBy(xpath = /*"//div[@name='Send']"*/"//input[@name='Send']")
    protected WebElement submitButton;

    @FindBy(xpath = "//div[@class='userExistsLabelInner']")
    protected WebElement alrdRegEmailPopUp;

    @FindBy(xpath = "(//div[@class='errorValidationIn'])[last()]")
    public WebElement countryCodeErrorMessage;

    @FindBy(xpath = "//header//div[@class='logo']")
    protected WebElement fortradeRLogo;

    @FindBy(xpath = "//div[@class='alreadyHaveAcc']//a[contains(text(),'Already have an account?')]")
    public WebElement alrHaveAccount;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-Age lcFieldWrapper']//select")
    protected WebElement age;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-EstimatedAnnualIncome lcFieldWrapper']//select")
    protected WebElement annual;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-ValueOfSavingAndInvestments lcFieldWrapper']//select")
    protected WebElement saving;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-KnowledgeOfTrading lcFieldWrapper']//select")
    protected WebElement knowledge;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-PreferredLanguage lcFieldWrapper']//select")
    protected WebElement pLang;

    @FindBy(xpath = /*"//div[@name='ContinueBtn']"*/"//input[@class='ContinueBtn-Submit']")
    protected WebElement continueBtn;

    @FindBy(xpath = "//input[@id='Details-Edit-Btn']")
    public WebElement penBtn;

    @FindBy(xpath = "//input[@name='Token0']")
    protected WebElement tokenField0;

    @FindBy(xpath = "//input[@name='Token1']")
    protected WebElement tokenField1;

    @FindBy(xpath = "//input[@name='Token2']")
    protected WebElement tokenField2;

    @FindBy(xpath = "//input[@name='Token3']")
    protected WebElement tokenField3;

    @FindBy(xpath = "//div[@class='formErrorMessage']")
    public WebElement incorrectTokenMsg;

    @FindBy(xpath = "//input[@class='TokenBack-Button']")
    protected WebElement didNotGetToken;

    @FindBy(xpath = "//label[@name='SentAgainLabel']")
    public WebElement codeIsSent;

    @FindBy(xpath = "//a[text()=' GB21026472']")
    protected WebElement fscRegulationLink;

    private String expTextForPopUp = "Invalid email. Please try another or proceed to log in. If needed, reset your password in case it's forgotten.";


    String[] errorMessages = {"Please enter all your given first name(s)",
            "Please enter your last name in alphabetic characters",
            "Invalid email format.",
            "Invalid phone format."};

    String[] sameNamesErrorMessages = {"Your first name must be different from your last name",
            "Your first name must be different from your last name"};

    // Already have an account link
    public String proAppUrl ="https://authfe.fortrade.com/oauth/account/login?appId=1bad62c5-1460-4369-b58f-fb9aa10ab7b8"; //"https://pro.fortrade.com/";

    // Financial Services Commission, Mauritius (FSC) link
    public String fscURL = "https://opr.fscmauritius.org/ords/opr/r/fsc-opr/fsc-online-public-register-opr";

    public String fbURL = "https://www.facebook.com/Fortrade.International";

    public String insURL = "https://www.instagram.com/fortrade_online_trading/?hl=en";

    public String ytURL = "https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg";

    protected void enterFirstName(String firstNameData) {
        typeText(firstName, firstNameData, "first name");
    }

    protected void enterLastName(String lastNameData) {
        typeText(lastName, lastNameData, "last name");
    }

    protected void enterEmail(String emailData) {
        typeText(email, emailData, "email");
    }

    protected void enterCountryCode(String countryCodeData) {
        typeText(countryCode, countryCodeData, "country code");
    }

    protected void enterPhone(String phoneData) {
        typeText(phoneNumber, phoneData, "phone number");
    }

    protected void clickSubmitBtn() {
        try {
            driver.hideKeyboard();
        } catch (Exception e){
            System.out.println(e);
        }
        clickElement(submitButton, "Submit button");
    }

    protected void selectAge(String ageData) {
        selectFromDropdown(age, ageData, "Age dropdown");
    }

    protected void selectAnnual(String annualData) {
        selectFromDropdown(annual, annualData, "Annual income dropdown");
    }

    protected void selectSaving(String savingData) {
        selectFromDropdown(saving, savingData, "Saving");
    }

    protected void selectKnowledge(String knowledgeData) {
        selectFromDropdown(knowledge, knowledgeData, "Knowledge of trading");
    }

    protected void selectPLang(String pLangData) {
        selectFromDropdown(pLang, pLangData, "PLang of trading");
    }

    protected void closeKeyboard() {
        driver.hideKeyboard();
    }

    protected void clickContinueBtn() {
        try {
            driver.hideKeyboard();
        } catch (Exception e){
            System.out.println(e);
        }
        clickElement(continueBtn, "continue button");
    }

    public void assertUrl(String url) {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void accountRegistration(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                    String phoneData, String ageData, String annualData, String savingData, String knowledgeData, String selectPLangData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneData);
        closeKeyboard();
        clickSubmitBtn();
        selectAge(ageData);
        selectAnnual(annualData);
        selectSaving(savingData);
        selectKnowledge(knowledgeData);
        selectPLang(selectPLangData);
        clickContinueBtn();
    }

    public void ftsQueryParameter(String url, String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                  String phoneNumberData, String ageData, String annualData, String savingData, String knowledgeData, String languageData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        /*clickDenyBtn();*/
        clickSubmitBtn();
        selectAge(ageData);
        selectAnnual(annualData);
        selectSaving(savingData);
        selectKnowledge(knowledgeData);
        if (url.contains("plang:all")){
            selectPLang(languageData);
        }
        clickContinueBtn();
        /*clickDenyBtn();
        clickNotSerbResBtn();
        clickUsePassBtn();
        clickMenuBtn();*/
    }

    public void unsuccessfullyRegistrationWrongData(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                                    String phoneData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneData);
        closeKeyboard();
        clickSubmitBtn();
    }

    public void assertErrorMessages() {
        for (int i = 1; i <= 4; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))), "error message " + errorMessages[i - 1]), errorMessages[i - 1]);
        }
    }

    public void assertSameNameErrorMsg() {
        for (int i = 1; i <= 2; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))), "Error message : " + sameNamesErrorMessages[i - 1]), sameNamesErrorMessages[i - 1]);
        }
    }

    public void assertColor(String color) {
        WebElement[] fields = {firstName, lastName, email, countryCode, phoneNumber};
        for (int i = 0; i < fields.length; i++) {
            /**
             * Ako prosledis color vrednost kao "rgb(123, 123, 132)" onda ukljuci ovaj kod
             */
            /*System.out.println("This is the border color: " + fields[i].getCssValue("border-color"));
            Assert.assertEquals(fields[i].getCssValue("border-color"), color);*/
            /**
             * U suprotnom ako uneses vrednost kao "blue" onda ukljuci ovaj kod
             */
            String borderColor = fields[i].getCssValue("border-color");
            // Split the RGB value
            String[] rgbValues = borderColor.replace("rgb(", "").replace(")", "").split(",");
            int red = Integer.parseInt(rgbValues[0].trim());
            int green = Integer.parseInt(rgbValues[1].trim());
            int blue = Integer.parseInt(rgbValues[2].trim());
            // Assert if it has a 'red' tone (adjust threshold values as needed)
            if (color.equalsIgnoreCase("red")) {
                System.out.println("This is the border color of " + fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(red > 150 && green < 100 && blue < 100, "Border color is not approximately red.");
            } else if (color.equalsIgnoreCase("blue")) {
                System.out.println("This is the border color of " + fields[i].getAttribute("name") + " field: " + borderColor);
                Assert.assertTrue(blue > 200 && green > 100 && red < 50, "Border color is not approximately blue.");
            } else if (color.equalsIgnoreCase("green")) {
                System.out.println("This is the border color of " + fields[i].getAttribute("name") + "field: " + borderColor);
                Assert.assertTrue(green < 200 && red > 50 && red < 120 && blue > 50 && blue < 100, "Border color is not approximately green.");
            }
        }
    }

    public void incorrectToken(String token0, String token1, String token2, String token3) {
        typeText(tokenField0, token0, "first token input field");
        typeText(tokenField1, token1, "second token input field");
        typeText(tokenField2, token2, "third token input field");
        typeText(tokenField3, token3, "fourth token input field");
    }

    public void unsuccessfullyRegistrationWrongSMS(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String ageData, String annualData, String savingData, String knowledgeData, String selectPLangData,String tokenField0Value
            , String tokenField1Value, String tokenField2Value,String tokenField3Value) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectAge(ageData);
        selectAnnual(annualData);
        selectSaving(savingData);
        selectKnowledge(knowledgeData);
        selectPLang(selectPLangData);
        incorrectToken(tokenField0Value,tokenField1Value,tokenField2Value,tokenField3Value);
        closeKeyboard();
        clickContinueBtn();
    }

    public void firstStepWidget(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                String phoneData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneData);
        closeKeyboard();
        clickSubmitBtn();
    }

    public void assertPopUpAlreadyRegisteredAccount() {
        Assert.assertEquals(getText(alrdRegEmailPopUp, "alrdRegEmailPopUp"), expTextForPopUp);
    }

    public void ageParameter(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String ageData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectAge(ageData);
        clickContinueBtn();
    }

    public void annualParameter(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String annualData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectAnnual(annualData);
        clickContinueBtn();
    }

    public void savingParameter(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String savingData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectSaving(savingData);
        clickContinueBtn();
    }

    public void knowledgeParameter(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String knowledgeData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectKnowledge(knowledgeData);
        clickContinueBtn();
    }

    public void pLangParameter(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String pLangData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectPLang(pLangData);
        clickContinueBtn();
    }

    public void clickDidNotGetToken() {
        clickElement(didNotGetToken, "Did not get token text");
    }

    public void clickThePenBtn() {
        clickElement(penBtn, "pen button on the 2nd step widget");
    }

    public void tokenIsNotReceived(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String ageData, String annualData, String savingData, String knowledgeData) throws InterruptedException {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectAge(ageData);
        selectAnnual(annualData);
        selectSaving(savingData);
        selectKnowledge(knowledgeData);
        clickDidNotGetToken();
        Thread.sleep(2000);
    }

    public void returnToThe1stWidget(String firstNameData, String lastNameData, String emailData, String countryCodeData
            , String phoneNumberData) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        clickThePenBtn();
    }

    public void clickLogo(String url) {
        try {
            wait.until(ExpectedConditions.visibilityOf(fortradeRLogo));
            clickElement(fortradeRLogo, "logo");
            System.out.println("Logo is not clickable");
        } catch (Exception e) {
            System.out.println(e + "Logo is clickable");
        }
        assertUrl(url);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertEquals(tabs.size(), 1);
    }

    public void clickAlrHaveAnAcc(){
        scrollToElementBy(By.xpath("//div[@class='alreadyHaveAcc']//a[contains(text(),'Already have an account?')]"));
        clickElement(alrHaveAccount,"An already have an account? link");
    }

    public void clickFscLink(){
        clickElement(fscRegulationLink,"Financial Services Commission, Mauritius FSC GB21026472");
        switchToNewWindow();
    }

    public void secondStepErrorMessage(int numberOfParameters) throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 1; i <= numberOfParameters; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidationIn'])[position()=number]".replace("number", String.valueOf(i))),
                    "error message " + "Please select an option from the dropdown list."), "Please select an option from the dropdown list.");
        }
    }

    public void unsuccessfullyRegistration(String firstNameData, String lastNameData, String emailData, String countryCodeData, String phoneNumberData
            , String ageData, String annualData, String savingData, String knowledgeData, String ageDataSelect, String annualDataSelect, String savingDataSelect, String knowledgeDataSelect) {
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneNumberData);
        closeKeyboard();
        clickSubmitBtn();
        selectAge(ageData);
        selectAge(ageDataSelect);
        selectAnnual(annualData);
        selectAnnual(annualDataSelect);
        selectSaving(savingData);
        selectSaving(savingDataSelect);
        selectKnowledge(knowledgeData);
        selectKnowledge(knowledgeDataSelect);
        clickContinueBtn();
    }
}
