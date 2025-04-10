package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

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

    @FindBy(xpath = "(//input[contains(@Name,'Send')])[position()=1]")
    protected WebElement submitButton;

    @FindBy(xpath = "//input[@name='SendTermsAgreementAsic']")
    protected WebElement submitBtnAsic;

    @FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyButtonDecline']")
    protected WebElement denyBtn;

    @FindBy(xpath = "//input[@class='ContinueBtn-Submit']")
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
    protected WebElement countryCodeErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    protected WebElement loginToFotrade;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-Age lcFieldWrapper']//select")
    protected WebElement age;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-EstimatedAnnualIncome lcFieldWrapper']//select")
    protected WebElement annual;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-ValueOfSavingAndInvestments lcFieldWrapper']//select")
    protected WebElement saving;

    @FindBy(xpath = "//div[@class='LcWidgetTopWrapper ClField-KnowledgeOfTrading lcFieldWrapper']//select")
    protected WebElement knowledge;

    @FindBy(xpath = "//input[@class='ContinueBtn-Submit']")
    protected WebElement continueBtn2;

    protected By privacyPolicyLinkBy = By.xpath("//div[@class='form-wrapper']//a[text()='Privacy Policy']");

    protected By termsAndConditionsLinkBy = By.xpath("//div[@class='form-wrapper']//a[contains(text(), 'Terms and Conditions')]");

    protected By clickHereLink = By.xpath("//span[@class='allMarketingMaterials']//a[text()='click here']");

    protected By alreadyHaveAnAccountLinkBy = By.xpath("//*[@class='alreadyHaveAcc']//a[contains(text(), 'Already have an account?')]");

    protected By contactUsLinkBy = By.xpath("//*[@class='needHelp']//a[contains(text(), 'Contact Us')]");

    protected By facebookLinkBy = By.xpath("//a[@class='facebook-links']");

    protected By instagramLinkBy = By.xpath("//a[@href='https://www.instagram.com/fortrade_online_trading/?hl=en']");

    protected By youtubeLinkBy = By.xpath("//a[@href='https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg']");

    protected By supportLinkBy = By.xpath("//a[text()='support@fortrade.com']");

    protected By footerRiskWarningLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Risk warning')]");

    protected By footerPrivacyPolicyLinkBy = By.xpath("//div[@class='footerRiskDisclaimer']//a[contains(text(), 'Privacy policy')]");

    protected By fcaRegulationLinkBy = By.xpath("//a[text()='FRN: 609970']");

    protected By ciroRegulationLinkBy = By.xpath("//a[text()='CRN: BC1148613']");

    protected By asicRegulationLinkBy = By.xpath("//a[text()='ABN: 33 614 683 831 | AFSL: 493520']");

    protected By cysecRegulationLinkBy = By.xpath("//a[text()='CIF license number 385/20']");

    protected By fscRegulationLinkBy = By.xpath("//a[text()=' GB21026472']");

    protected By fsgDocument = By.xpath("//div[@class='footerRiskDisclaimer']//div[@class='asicClass']//a[contains(text(),'(FSG)')]");

    protected By pdsDocument = By.xpath("//div[@class='footerRiskDisclaimer']//div[@class='asicClass']//a[contains(text(),'(PDS)')]");

    protected By tmdDocument = By.xpath("//div[@class='footerRiskDisclaimer']//div[@class='asicClass']//a[contains(text(),'(TMD)')]");

    String[] errorMessages = {"Please enter all your given first name(s)",
            "Please enter your last name in alphabetic characters",
            "Invalid email format.",
            "Invalid phone format."};

    String[] sameNamesErrorMessages = {"Your first name must be different from your last name",
            "Your first name must be different from your last name"};

    protected String howToUnsubscribeURL = "https://www.fortrade.com/wp-content/uploads/legal/How_to_guides/How_to_unsubscribe.pdf";

    // Already have an account
    protected String alrHaveAccount = "https://ready.fortrade.com/";

    protected String fbURL = "https://www.facebook.com/";

    protected String insURL = "https://www.instagram.com/fortrade_online_trading/?hl=en";

    protected String ytURL = "https://www.youtube.com/channel/UCNCrGhrDTEN1Hx_20-kFxwg";

    // Financial Conduct Authority (FCA) link
    protected String fcaLink = "https://register.fca.org.uk/s/firm?id=001b000000NMdUwAAL";

    // Canadian Investment Regulatory Organization (CIRO) link
    protected String iirocLink = "https://www.ciro.ca/investors/choosing-investment-advisor/dealers-we-regulate/fortrade-canada-limited";

    // Australian Securities and Investments Commission (ASIC) link
    protected String asicLink = "https://asic.gov.au/online-services/service-availability/";

    // Cyprus Securities and Exchange Commission (CySEC) link
    protected String cysecLink = "https://www.cysec.gov.cy/en-GB/entities/investment-firms/cypriot/86639/";

    // Financial Services Commission, Mauritius (FSC) link
    protected String fscLink = "https://www.fscmauritius.org/en/supervision/register-of-licensees/register-of-licensees-details?licence_no=GB21026472&key=&cat=_GB&code=";

    // Asic regulation - financial service guide document link
    protected String fsgDocumentLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Financial_Services_Guide-ASIC.pdf";

    // Asic regulation - product disclosure statement document link
    protected String pdsDocumentLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU_Product_Disclosure_Statement-ASIC.pdf";

    // Asic regulation - target market determination document link
    protected String tmdDeterminationLink = "https://www.fortrade.com/wp-content/uploads/legal/ASIC/Fort_Securities_AU-TMD_Policy.pdf";

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

    protected void clickDenyBtn() {
        clickElement(denyBtn, "Deny (Cookies) button");
    }

    protected void clickSubmitBtn() {
        if(submitButton.isDisplayed()) {
            clickElement(submitButton, "Submit button");
            } else {
            clickElement(submitBtnAsic,"Submit button - Asic regulation");
        }
        }

    protected void selectAge(String ageData) {
        selectFromDropdown(age, ageData, "Age " + ageData);
    }

    protected void selectAnnual(String annualData) {
        selectFromDropdown(annual, annualData, "Annual income " +annualData);
    }

    protected void selectSaving(String savingData) {
        selectFromDropdown(saving, savingData, "Saving "+ savingData);
    }

    protected void selectKnowledge(String knowledgeData) {
        selectFromDropdown(knowledge, knowledgeData, "Knowledge of trading "+ knowledgeData);
    }

    protected void clickContinueBtn() {
        clickElement(continueBtn, "continue button");
    }

    protected void closeKeyboard() {
        driver.hideKeyboard();
    }

    public void assertUrl(String url){
        wait.until(ExpectedConditions.urlContains(url));
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void accountRegistration(String firstNameData, String lastNameData, String emailData, String countryCodeData,
                                    String phoneData, String ageData, String annualData, String savingData, String knowledgeData) {
        clickDenyBtn();
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
}
