package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FortradePage extends BasePage {

    public FortradePage(AndroidDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class,'logo')]")
    protected WebElement logo;

    @FindBy(xpath = "//div[contains(@class,'logo iirocClass')]")
    protected WebElement logoIiroc;

    @FindBy(xpath = "//div[contains(@class,'logo cysecClass')]")
    protected WebElement logoCysec;

    @FindBy(xpath = "//input[@id='FirstName']")
    protected WebElement firstName;

    @FindBy(xpath = "//input[@id='LastName']")
    protected WebElement lastName;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-Email lcFieldWrapper']//input[@id='EmailForm']")
    protected WebElement email;

    @FindBy(xpath = "//input[@id='PhoneCountryCode']")
    protected WebElement countryCode;

    @FindBy(xpath = "//input[@id='Phone']")
    protected WebElement phoneNumber;

    @FindBy(xpath = "//div[@Name='Send']")
    protected WebElement submitButton;

    @FindBy(xpath = "//div[@Name='SendTermsAgreementAsic']")
    protected WebElement submitBtnAsic;

    @FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyButtonDecline']")
    protected WebElement denyBtn;

    @FindBy(xpath = "//div[@name='ContinueBtn']")
    protected WebElement continueBtn;

    @FindBy(xpath = "//div[@data-cmd='menu']")
    protected WebElement menuBtn;

    @FindBy(xpath = "//div[@id='platformRegulation']")
    protected WebElement regulationMsg;

    @FindBy(xpath = "//div[@class='userExistsLabelInner']")
    protected WebElement alrdRegEmailPopUp;

    @FindBy(xpath = "//iframe[@id='myWidgetIframe']")
    protected WebElement iFrameIConsent;

    @FindBy(xpath = "//div[@class='pushcrew-chrome-style-notification pushcrew-chrome-style-notification-safari']")
    protected WebElement popUpNotification;

    @FindBy(xpath = "(//div[@class='errorValidationIn'])[last()]")
    public WebElement countryCodeErrorMessage;

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

    @FindBy(xpath = "//input[@class='ContinueBtn-Submit']")
    protected WebElement continueBtn2;

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

    @FindBy(xpath = "//a[text()='FRN: 609970']")
    protected WebElement fcaRegulationLink;

    @FindBy(xpath = "//a[text()='CRN: BC1148613']")
    protected WebElement iirocRegulationLink;

    @FindBy(xpath = "//a[text()='ABN: 33 614 683 831 | AFSL: 493520']")
    protected WebElement asicRegulationLink;

    @FindBy(xpath = "//a[text()='CIF license number 385/20']")
    protected WebElement cysecRegulationLink;

    @FindBy(xpath = "//a[text()=' GB21026472']")
    protected WebElement fscRegulationLink;

    @FindBy(xpath = "//input[@class='TokenBack-Button']")
    protected WebElement didNotGetToken;

    @FindBy(xpath = "//label[@name='SentAgainLabel']")
    public WebElement codeIsSent;

    @FindBy(xpath = "//input[@id='Details-Edit-Btn']")
    public WebElement penBtn;

    protected By facebookLinkBy = By.xpath("//a[@class='facebook-links']");

    protected By instagramLinkBy = By.xpath("//a[@href='https://www.instagram.com/fortrade_online_trading/?hl=en']");

    protected By youtubeLinkBy = By.xpath("//a[@href='https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg']");

    By dynamicPercentagesBy = By.xpath("//div[@class='rwLong']//strong[contains(text(), '% of retail investor accounts lose money when trading CFDs with this provider.')]");
  
    By staticPercentagesBy = By.xpath("//div[@class='footerRiskDisclaimer']//b[contains(text(), '% of retail investor accounts lose money when trading CFDs with this provider.')]");

    private String expTextForPopUp = "Invalid email. Please try another or proceed to log in. If needed, reset your password in case it's forgotten.";

    String[] errorMessages = {"Please enter all your given first name(s)",
            "Please enter your last name in alphabetic characters",
            "Invalid email format.",
            "Invalid phone format."};

    String[] sameNamesErrorMessages = {"Your first name must be different from your last name",
            "Your first name must be different from your last name"};

    protected String howToUnsubscribeURL = "https://www.fortrade.com/wp-content/uploads/legal/How_to_guides/How_to_unsubscribe.pdf";

    // Already have an account
    public String appUrl = "https://ready.fortrade.com/";

    protected String fbURL = "https://www.facebook.com/";

    protected String insURL = "https://www.instagram.com/fortrade_online_trading/?hl=en";

    protected String ytURL = "https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg";

    // Financial Conduct Authority (FCA) link
    public String fcaURL = "https://register.fca.org.uk/s/firm?id=001b000000NMdUwAAL";

    // Canadian Investment Regulatory Organization (CIRO) link
    public String iirocURL = "https://www.ciro.ca/investors/choosing-investment-advisor/dealers-we-regulate/fortrade-canada-limited";

    // Australian Securities and Investments Commission (ASIC) link
    public String asicURL = "https://asic.gov.au/online-services/service-availability/";

    // Cyprus Securities and Exchange Commission (CySEC) link
    public String cysecURL = "https://www.cysec.gov.cy/en-GB/entities/investment-firms/cypriot/86639/";

    // Financial Services Commission, Mauritius (FSC) link
    public String fscURL = "https://opr.fscmauritius.org/ords/opr/r/fsc-opr/fsc-online-public-register-opr";

    // Asic regulation - financial service guide document link
    protected String fsgDocumentLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Financial_Services_Guide-ASIC.pdf";

    // Asic regulation - product disclosure statement document link
    protected String pdsDocumentLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Product_Disclosure_Statement-ASIC.pdf";

    // Asic regulation - target market determination document link
    protected String tmdDeterminationLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU-TMD_Policy.pdf";

    public void enterFirstName(String firstNameData) {
        typeText(firstName, firstNameData, "first name");
    }

    public void enterLastName(String lastNameData) {
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

    public void clickDenyBtn() {
        clickElement(denyBtn, "Deny (Cookies) button");
    }

    protected void clickSubmitBtn() {
        if (submitButton.isDisplayed()) {
            clickElement(submitButton, "Submit button");
        } else {
            clickElement(submitBtnAsic, "Submit button - Asic regulation");
        }
    }

    protected void selectAge(String ageData) {
        selectFromDropdown(age, ageData, "Age " + ageData);
    }

    protected void selectAnnual(String annualData) {
        selectFromDropdown(annual, annualData, "Annual income " + annualData);
    }

    protected void selectSaving(String savingData) {
        selectFromDropdown(saving, savingData, "Saving " + savingData);
    }

    protected void selectKnowledge(String knowledgeData) {
        selectFromDropdown(knowledge, knowledgeData, "Knowledge of trading " + knowledgeData);
    }

    protected void selectPLang(String pLangData) {
        selectFromDropdown(pLang, pLangData, "PLang " + pLangData);
    }

    public void clickContinueBtn() {
        clickElement(continueBtn, "continue button");
    }

    public void closeKeyboard() {
        driver.hideKeyboard();
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

    public void unsuccessfullyRegistrationWrongData(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                                    String phoneData) throws IOException {
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
            , String ageData, String annualData, String savingData, String knowledgeData, String selectPLangData, String tokenField0Value
            , String tokenField1Value, String tokenField2Value, String tokenField3Value) {
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
        incorrectToken(tokenField0Value, tokenField1Value, tokenField2Value, tokenField3Value);
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

    public void clickThePenBtn() {
        clickElement(penBtn, "pen button on the 2nd step widget");
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

    public void clickFcaLink(){
        clickElement(fcaRegulationLink,"Financial Conduct Authority (FCA), FRN: 609970. link");
        switchToNewWindow();
    }

    public void clickIirocLink() {
        clickElement(iirocRegulationLink, "Canadian Investor Protection Fund (CIPF). CRN: BC1148613 link");
        switchToNewWindow();
    }

    public void clickAsicLink() {
        clickElement(asicRegulationLink, "Australian Securities and Investments Commission (ASIC) ABN: 33 614 683 831 | AFSL: 493520 link");
        switchToNewWindow();
    }

    public void clickCysecLink() {
        clickElement(cysecRegulationLink, "Cyprus Securities and Exchange Commission (CySEC) with CIF license number 385/20");
        switchToNewWindow();
    }

    public void clickFscLink() {
        clickElement(fscRegulationLink, "Financial Services Commission, Mauritius FSC GB21026472");
        switchToNewWindow();
    }

    public void clickLogo(String regulation,String url){
        try {
            if (regulation.equalsIgnoreCase("iiroc")){
                wait.until(ExpectedConditions.visibilityOf(logoIiroc));
                clickElement(logoIiroc,"iiroc logo");
            } else if(regulation.equalsIgnoreCase("cysec")){
                wait.until(ExpectedConditions.visibilityOf(logoCysec));
                clickElement(logoCysec,"cysec logo");
            }else {
                wait.until(ExpectedConditions.visibilityOf(logo));
                clickElement(logo,regulation + "logo");
            }
            System.out.println("Logo is not clickable");
        } catch (Exception e){
            System.out.println( e + "Logo is clickable");
        }
        assertUrl(url);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertEquals(tabs.size(),1);
    }

    public void clickAlrHaveAnAcc(){
        scrollToElementBy(By.xpath("//div[@class='alreadyHaveAcc']//a[contains(text(),'Already have an account?')]"));
        clickElement(alrHaveAccount,"An already have an account? link");
    }
  
    public void checkPercentages (String textForPercentages){
        Assert.assertEquals(getText(returnDisplayedElement(dynamicPercentagesBy), "dynamicPercentages"), textForPercentages);
        scrollToElementBy(staticPercentagesBy);
        Assert.assertEquals(getText(returnDisplayedElement(staticPercentagesBy), "staticPercentages"), textForPercentages);
    }
    public void secondStepErrorMessage(int numberOfParameters) throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 1; i <= numberOfParameters; i++) {
            Assert.assertEquals(getTextBy(By.xpath("(//div[@class='errorValidation'])[position()=number]".replace("number", String.valueOf(i))),
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
        //clickDenyBtn();
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
