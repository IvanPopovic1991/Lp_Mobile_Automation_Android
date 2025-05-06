package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    @FindBy(xpath = "//input[@class='Send-Button Send-Button-Step1']")
    protected WebElement submitButton;

    @FindBy(xpath = "//div[@class='userExistsLabelInner']")
    protected WebElement alrdRegEmailPopUp;

    @FindBy(xpath = "(//div[@class='errorValidationIn'])[last()]")
    protected WebElement countryCodeErrorMessage;

    @FindBy(xpath = "//header//div[@class='logo']")
    protected WebElement fortradeLogo;

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    protected WebElement loginToFortrade;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-Age lcFieldWrapper']//select")
    protected WebElement age;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-EstimatedAnnualIncome lcFieldWrapper']//select")
    protected WebElement annual;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-ValueOfSavingAndInvestments lcFieldWrapper']//select")
    protected WebElement saving;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-KnowledgeOfTrading lcFieldWrapper']//select")
    protected WebElement knowledge;

    @FindBy(xpath = "//input[@class='ContinueBtn-Submit']")
    protected WebElement continueBtn;

    @FindBy(xpath = "//div[@class='nav-button']")
    protected WebElement startTradingBtn;

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

    protected By privacyPolicyLinkBy = By.xpath("//span[@class='MarketingMaterials2']//span[@class='fscClass']//a[text()='Privacy Policy']");

    protected By termsAndConditionsLinkBy = By.xpath("//span[@class='MarketingMaterials2']//span[@class='fscClass']//a[text()=' Terms and Conditions']");

    protected By clickHereLinkBy = By.xpath("//span[@class='MarketingMaterials2']//a[text()='click here']");

    protected By alreadyHaveAnAccountLinkBy = By.xpath("//*[@class='alreadyHaveAcc']//a[contains(text(), 'Already have an account?')]");

    protected By contactUsLinkBy = By.xpath("//*[@class='needHelp']//a[contains(text(), 'Contact Us')]");

    protected By facebookLinkBy = By.xpath("//a[@class='facebook-links']");

    protected By instagramLinkBy = By.xpath("//a[@href='https://www.instagram.com/fortrade_online_trading/?hl=en']");

    protected By youtubeLinkBy = By.xpath("//a[@href='https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg']");

    protected By infoLinkBy = By.xpath("//div[@class='col-md-12 text-center']//a[text()='info@fortrade.com']");

    protected By supportLinkBy = By.xpath("//a[text()='support@fortrade.com']");

    protected By footerRiskWarningLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Risk warning')]");

    protected By footerPrivacyPolicyLinkBy = By.xpath("//div[@class='fscClass']//a[contains(text(),'Privacy policy')]");

    protected By footerPrivacyPolicyFortradeRLinkBy = By.xpath("//div[@class='fscClass']//a[contains(text(), 'Privacy policy')]");

    protected By fcaRegulationLinkBy = By.xpath("//a[text()='FRN: 609970']");

    protected By ciroRegulationLinkBy = By.xpath("//a[text()='CRN: BC1148613']");

    protected By asicRegulationLinkBy = By.xpath("//a[text()='ABN: 33 614 683 831 | AFSL: 493520']");

    protected By cysecRegulationLinkBy = By.xpath("//a[text()='CIF license number 385/20']");

    protected By fscRegulationLinkBy = By.xpath("//a[text()=' GB21026472']");

    private String expTextForPopUp = "Invalid email. Please try another or proceed to log in. If needed, reset your password in case it's forgotten.";


    String[] errorMessages = {"Please enter all your given first name(s)",
            "Please enter your last name in alphabetic characters",
            "Invalid email format.",
            "Invalid phone format."};

    String[] sameNamesErrorMessages = {"Your first name must be different from your last name",
            "Your first name must be different from your last name"};

    // Privacy Policy document link
    public String privacyPolicyFSC = "https://www.fortrade.com/fortrade-ma-privacy-policy/";

    // Terms and conditions document link
    public String termsAndConditionsFSC = "https://www.fortrade.com/fortrade-mauritius-client-agreement/";

    //How to unsubscribe document link
    public String howToUnsubscribeURL = "https://www.fortrade.com/wp-content/uploads/legal/How_to_guides/How_to_unsubscribe.pdf";

    // Already have an account link
    public String alrHaveAccount = "https://pro.fortrade.com/";

    // Privacy policy document Footer link
    public String privacyPolicyFSCFooter = "https://www.fortrade.com/wp-content/uploads/legal/FSC/Fortrade_MA_Privacy_Policy.pdf";

    // Financial Services Commission, Mauritius (FSC) link
    public String fscLink = "https://www.fscmauritius.org/en/supervision/register-of-licensees/register-of-licensees-details?licence_no=GB21026472&key=&cat=_GB&code=";

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

    protected void closeKeyboard() {
        driver.hideKeyboard();
    }

    protected void clickContinueBtn() {
        clickElement(continueBtn, "continue button");
    }

    public void accountRegistration(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                    String phoneData, String ageData, String annualData, String savingData, String knowledgeData) {
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
        clickContinueBtn();
    }

    public void unsuccessfullyRegistrationWrongData(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                                    String phoneData){
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
            , String ageData, String annualData, String savingData, String knowledgeData,String tokenField0Value
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
        incorrectToken(tokenField0Value,tokenField1Value,tokenField2Value,tokenField3Value);
        closeKeyboard();
        clickContinueBtn();
    }

    public void firstStepWidget(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                String phoneData){
        enterFirstName(firstNameData);
        enterLastName(lastNameData);
        enterEmail(emailData);
        enterCountryCode(countryCodeData);
        enterPhone(phoneData);
        closeKeyboard();
        clickSubmitBtn();
    }

    public void assertPopUpAlreadyRegisteredAccount(){
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

}
