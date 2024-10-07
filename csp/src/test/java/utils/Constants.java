
package utils;

import java.io.File;

public class Constants {
	
	
	/**
	 * Boolean Values
	 */
	public static final String C_YES = "YES";
	public static final String C_NO = "NO";

	
	/**
	 * test data properties
	 */
	public static final String C_TESTDATA = "Testdata.properties";
	public static final String C_CONFIG_PROPERTIES = "Config.properties";
	
	/**
	 * Attribute & Values
	 */
	public static final String C_VALUE = "value";
	public static final String C_CLASS = "class";
	public static final String C_ENABLED = "Enabled";
	public static final String C_DISABLED = "Disabled";
	public static final String C_DISPLAYED = "displayed";
	public static final String C_NOT_DISPLAYED = "Not Displayed";
	public static final String C_STYLE = "style";
	
	
	/**
	 * Common Testdata variables
	 */
	public static final String TDROLESOUTOFSTOCKPRODUCT = "tdRolesOutOfStockProduct";
	public static final String TDPENDINGCSOSSIGNITURECARTB2B = "tdPendingCSOSSignitureCartB2b";
	public static final String TDROLERESTRICTIONPRODUCT = "tdroleRestrictionProduct";
	public static final String TDPENDINGCSOSSIGNITUREBACKORDER = "tdPendingSignitureBackOrder";
	public static final String TDROLESREMINDERTITLE = "tdRolesReminderTitle";
	public static final String TD_CDM_EMPTY_RESULTS = "tdCdmEmptyResults";
	public static final String NEW_DONOTSUB_PRODUCT = "newDoNotSubproduct";
	public static final String SOFTBLOCKBYPASS_CHECKED="checked";
	public static final String SOFTBLOCKBYPASS_UNCHECKED="Unchecked";
	public static final String SOFTBLOCKPRODUCT="td_SoftBlock_Product";
	public static final String CARTDETAILS_PAGE="Cart Details";
	public static final String SOFTBLOCKC2PRODUCT="tdSoftBlockC2Product";
	public static final String SOFTBLOCKQUICKADDWARNING="tdSoftBlockQuickAddWarning";
	public static final String SUSPECTQUANTITYPRODUCT="td_suspectQTY_Product";
	public static final String SOFTBLOCKSOURCETEMPLATE="Softblock Template Source";
	public static final String SOFTBLOCK_SOURCE_CSOSTEMPLATE_USERBYPASS_CHECKED="Bypass CSOSTemplate Source";
	public static final String SOFTBLOCK_TARGET_CSOSTEMPLATE_USERBYPASS_CHECKED="BypassChecked CSOSTargetCart";
	public static final String SOFTBLOCK_SOURCE_MIXTEMPLATE="Softblock MixTemplate Source";
	public static final String INFO_ICON_TOOL_TIP_MSG ="Free next-day weekday delivery on orders containing $100 in generics.";
	
	/**
	 * Common constants
	 */
	public static final String LABEL_IS_INCORRECT = "Label is incorrect";
	public static final String MESSAGE_IS_INCORRECT = "message is incorrect";
	public static final String PDP_PAGE = "Master-Catalog";
	public static final String PO_AUTOMATION = "Automation";
	public static final String SUBMITTED_STATUS = "Submitted";
	
	public static final String DROPSHIP_TEST = "Dropship Test";
	public static final String TESTDATA = "Testdata.properties";
	public static final String C2_CART_NO = "C2CartNo";
	public static final String INTERRUPTED_EXCEPTION="InterruptedException";
	public static final String TIMEOUT_EXCEPTION="TimeoutException";
	public static final String C_DISABLEDATTRIBUTE = "disabled";

	public static final String NONABCPRODUCTSURL = "nonabcproducts";
	public static final String INVENTORYURL = "inventory";
	public static final String CUSTOMERDATAURL = "customerdata";
	public static final String NOTESURL = "notes";
	public static final String CALENDARURL = "calendar";
	public static final String RETURNSURL = "returns";
	public static final String CLAIMSURL = "claim";
	public static final String ACTIVE = "active";
	public static final String OPEN = "open";
	public static final String CATALOGSEARCHPAGE = "CatalogSearchPage";
	public static final String FINDALTERNATIVESPAGE = "FindAlternatives";
	public static final String PDP = "PDP";
	public static final String BETTERPRICEOPPORTUNITYMODAL = "BETTER PRICE OPPORTUNITY MODAL";
	public static final String CONTRACTCOMPLIANCEOPPORTUNITYMODAL = "CONTRACT COMPLIANCE OPPORTUNITY MODAL";
	public static final String QUICKADDMODAL = "QUICKADD";
	public static final String CARTPAGE = "CARTPAGE";
	public static final String PDPPAGE = "PDPPAGE";
	public static final String NOTESPAGE = "NOTESPAGE";
	public static final String PRICEOPPORTUNITYMODAL = "PRICEOPPORTUNITY";
	public static final String FINDALTERNATIVESMODAL = "FINDALTERNATIVES";
	public static final String CONTRACTCOMPLIANCEMODAL = "CONTRACTCOMPLIANCE";
	public static final String TODAYCALENDARMODAL = "TODAYCALENDAR";
	public static final String SUSPECTQTYMODAL = "SUSPECTQUANTITY";
	public static final String MINIMUMQTYMODAL = "MINIMUMQUANTITY";
	public static final String LEARNBARCODEMODAL = "LEARNBARCODE";
	public static final String CREATEREMINDERMODAL = "CREATEREMINDER";
	public static final String POTENTIALDUPLICATEMODAL = "POTENTIALDUPLICATE";
	public static final String CREATENOTEMODAL = "CREATENOTE";
	public static final String VIEWNOTEMODAL = "VIEWNOTE";
	public static final String SHORTDATEDMODAL = "SHORTDATED";
	public static final String PRICEBREAKMODAL = "PRICEBREAK";
	public static final String EDITRETAILPRICEMODAL = "EDITRETAILPRICE";
	public static final String C2CARTPAGE = "C2CART";
	public static final String BACKORDERPAGE = "BACKORDER";
	public static final String MINIMUMQTYEXCEPTION = "MINIMUMQUANTITYEXCEPTION";
	
	public static final String C2_ERROR="Product is a Schedule II Controlled Substance. Returns for this product cannot be completed online. Please contact Customer CARE for more information. ";
	public static final String C2_ERROR_CLAIM= "This Product is a Schedule II Controlled Substance. Claims for this product cannot be completed online. Please contact Customer Service for more information.";
	
	//Niosh Indicator
//	public static final String NIOSH_TOOLTIP_MSG = "NT1 - ANTINEOPLASTIC DRUGS INCLUDING THOSE WITH MANUFACTURER'S SAFE HANDLING GUIDANCE (MSHG)";
	public static final String NIOSH_TOOLTIP_MSG_CART = "NT2 - NON-ANTINEOPLASTIC DRUGS THAT MEET 1 OR MORE OF NIOSH CRITERIA FOR HAZARDOUS DRUG INCLUDING (MSHG)";
	public static final String NIOSH_TOOLTIP_MSG = "NT2 - NON-ANTINEOPLASTIC DRUGS THAT MEET 1 OR MORE OF NIOSH CRITERIA FOR HAZARDOUS DRUG INCLUDING (MSHG)";
	//public static final String NIOSH_TOOLTIP_MSG_CART = "NT1 -ANTINEOPLASTIC DRUGS INCLUDING THOSE WITH MANUFACTURER'S SAFE HANDLING GUIDANCE (MSHG)";
	public static final String NOTIFY_TOOLTIP_MSG = "Add a notification for when this product is back in stock";
	public static final String MATERIAL_NOTE_TOOLTIP_MSG="Anticipated availability date: 12/31/2024 ";
	public static final String CCS_TOOLTIP_MSG="Contact Customer Service";
	public static final String SIGNALCODE_TOOLTIP_MSG="SPECIAL ORDER ITEM: ESTIMATED SHIPPING DATE VARIES. CONTACT CUSTOMER SERVICE FOR MORE INFORMATION.";
	public static final String SIGNALCODE="Special Order";
	
	public static final String INVALID_TEXT_TO_SEARCH="invalidID";

	/**
	 * Page : My Rebates
	 **/
	public static final String INSTANT_REBATE = "W/ Instant Rebate";
	public static final String MONTHLY_REBATE = "W/ Rebate";
	public static final String MONTHLY_REBATE_PDP = "W/ Rebate";
	public static final String INSTANT_REBATE_PDP = "W/ Inst. Rebate";
	public static final String INSTANT_REBATE_LABEL = "Instant Rebate";
	public static final String MONTHLY_REBATE_LABEL = "Monthly Rebate";
	public static final String NOTE_MSG = "Note: Current Period to Date RX $, Net Sales $, PRxO $, and Eligible Rebate Sales include the prior business day. Estimated Rebate %, Estimated Rebate $, and Compliance % are intended for estimation only. Final values may vary. Other terms and conditions may affect rebate outcomes.";
	public static final String ESTIMATES_AS_OF = "As Of";
	public static final String CURRENT_PERIOD_ENDS = "Period";
	public static final String CALENDAR_DAYS_REMAINING = "ENDS IN";
	public static final String ESTIMATED_REBATE_AMT = "Est. Rebate Amt.";
	public static final String ESTIMATED_REBATE_PERCENTAGE = "Estimated Rebate:";
	public static final String TIER_LEVEL = "Tier Level:";
	public static final String PERIOD = "Period:";
	public static final String AS_OF = "Last updated";
	public static final String REBATE_TIER_PROGRESS = "COMPLIANCE";
	public static final String CURRENT_PERIOD_PURCHASES = "Current Sales to Date";
	public static final String PRIOR_PERIOD_PERFORMANCE = "My Rebate Performance Over Time";
	public static final String GENERIC_PURCHASES = "Net Sales";
	public static final String TIER1 = "Tier 1";
	public static final String REBATE_PERCENTAGE = "Rebate %";
	public static final String GENERICS_FORMULARY = "PRxO Generics";
	public static final String RX = "RX";
	public static final String REBATABLE_GENERICS = "Eligible Rebate Sales";
	public static final String REBATE_AMT = "Rebate Amt.";
	public static final String REBATE_PERCENRAGE_ACHIEVED = "Rebate % achieved";
	public static final String CREDIT_MEMO_PENDING = "Credit Memo Pending";
	public static final String NOTEMSG = "Note";
	public static final String CURRENT_COMPLAINCE = "Current Compliance of Total Rx Sales";
	public static final String CURRENT_COMPLAINCE_EXPECTED= "Current Compliance % of Total Rx Sales";
	public static final String CURRENT_PRXO = "Current PRxO Sales";
	public static final String TOTAL_RX_SALES = "Total Rx Sales";
	public static final String COMPLIANCE = "COMPLIANCE";
	
	
	/**
	 * Page : Tools
	 **/
	public static final String CUSTOMERDATAMAINTENANCE = "CustomerDataMaintenance";
	public static final String PHYSICALINVENTORY = "PhysicalInventory";
	public static final String NOTES = "Notes";
	public static final String NONABCPRODUCTS = "NonAbcProducts";
	public static final String LEARNEDBARCODE = "LearnedBarcode";
	public static final String MYUPLOADS = "MyUploads";
	public static final String FORMULARYINDICATORS = "FormularyIndicators";
	public static final String DONOTSUB = "DoNotSub";
	public static final String CALENDAR = "Calendar";
	public static final String SOFTBLOCK = "Softblock";

	/**
	 * Page : Manage Carts 
	 **/
	public static final String TEMPLATES = "Templates";
	public static final String OPENCARTS = "Open Carts";
	public static final String TILEMSG = "Cart Pending Approval";
	
	
	/**
	 *  Page : Support Services
	 *
	 */
	public static final String C_GENERALRETURN = "General Returns";
	public static final String C_RECALL = "Recall";
	public static final String C_SUPPORTCASE = "Support Case";
	public static final String C_CLAIM = "Claim";
	public static final String C_FLURETURNS = "Flu Returns";
	public static final String C_MFCRETURNS = "MFC Returns";

	public static final String C_INSULATEDCOOLERREQTITLETXT = "Insulated Cooler Request";
	public static final String C_INSULATEDCOOLERNOTETEXT = "Note: If you are not sure which size cooler to order, contact Customer Service.";
	public static final String C_INSULATEDCOOLERTOTEMSG = "If you would like to request insulated coolers, please indicate the quantitiy of cooler. If you do not need to order coolers, leave the fields blank.";

	public static final String INSULATED_COOLER_REQUEST_TITLE = "Insulated Cooler Request";
	public static final String INSULATED_COOLER_REQUEST_MSG = "If you would like to request insulated coolers, please indicate the quantitiy of coolers. If you do not need to order insulated coolers, leave the fields blank.";
	public static final String LARGE_RETURN_PACKAGE = "LARGE COLD CHAIN FOAM BOX RETURN PACKAGE";
	public static final String SMALL_RETURN_PACKAGE = "SMALL COLD CHAIN FOAM BOX RETURN PACKAGE";
	public static final String XL_RETURN_PACKAGE = "XL COLD CHAIN FOAM BOX RETURN PACKAGE";
	public static final String QUALIFYING_PRODUCT = "Qualifying Products";
	public static final String QTY = "Qty : ";
	public static final String ABC_NUMBER = "ABC # : ";
	public static final String INSULATED_NOTE = "Note: If you are not sure which size cooler to order, contact Customer Service.";
	public static final String INCORRECTCLAIMS_INVOICE_DATE_COLUMN = "Inv. Date";
	public static final String INCORRECTCLAIMS_INVOICE_DATE_QUANTITY_COLUMN = "Inv. Qty";
	public static final String ERROR_MSG_OTHER_SS_PRODUCTS="Only products classified as Self Service (SS) can be returned through ABC Order.";


	public static final String COLD_TOTE_REQUEST_TITLE = "Cold Tote Request";
	public static final String COLD_TOTE_REQUEST_MSG = "If you would like to request cold totes, please indicate the quantitiy of totes. If you do not need to order cold totes, leave the fields blank.";
	public static final String LARGE_COLD = "LARGE COLD CHAIN RETURNS BOX 15X8.5X8";
	public static final String SMALL_COLD = "SMALL COLD CHAIN RETURNS BOX 15X8X4";
	public static final String COLD_TOTE_NOTE = "Note: If you are not sure which size cooler to order, contact Customer Service.";

	public static final String SALEABLE_PRODUCT_ERROR = "Saleable product is not returnable to AmerisourceBergen";
	public static final String NON_SALEABLE_PRODUCT_ERROR = "Non-Saleable product is returnable to AmerisourceBergen";
	public static final String SALEABLE_PRODUCT_TOOLTIP = "Saleable product is not returnable to AmerisourceBergen. Uncheck Saleable or Remove Quantity";
	public static final String SALEABLE_PRODUCT_SUPPLIER = "Saleable product must be sent back to the supplier.";
	public static final String NON_SALEABLE_RETURNABLE = "Non-Saleable product is returnable to AmerisourceBergen";
	public static final String TOOLTIP_EST_CREDIT = "Final credit amount will be adjusted to reflect goods that are damaged, missing or do not comply with manufacturer's returned goods policies. Fees may apply and will be deducted from the final credit amount.";
	public static final String RETURN_INSULATED_COOLER_REQUEST_MSG = "If you would like to request Insulated Coolers, please indicate the quantitiy of Insulated Cooler. If you do not need to order cooler, leave the fields blank.";
	public static final String MAX_QTY = "9";

	public static final String COLDTOTEREQUEST = "You requested a cold tote(s) with this recall";
	public static final String SHORTAGE_UPONDELIVERY = "Upon Delivery";
	public static final String SALEABLE_TOOLTIP_ERROR_MSG = "This product cannot be returned if it is saleable";
	public static final String NON_SALEABLE_TOOLTIP_ERROR_MSG = "This product cannot be returned if it is non-saleable";
	
	//Return
	public static final String RETURNS_INVOICE_NUMBER_COLUMN = "Invoice#";
	public static final String RETURNS_INVOICE_DATE_COLUMN = "Inv. Date";
	public static final String RETURNS_INVOICE_LINE_COLUMN = "Line";
	public static final String RETURNS_INVOICE_LOT_COLUMN = "Lot #";
	public static final String RETURNS_INVOICE_EXP_COLUMN = "Exp.";
	public static final String RETURNS_INVOICE_PRICE_COLUMN = "Inv. Item Price";
	public static final String RETURNS_INVOICE_QUANTITY_COLUMN = "Inv Qty";
	public static final String RETURNS_INVOICE_ELIG_QUANTITY_COLUMN = "Eligible Qty";
	public static final String RETURNS_INVOICE_RETURN_QUANTITY_COLUMN = "Return Qty";
	public static final String RETURNS_INVOICE_SALEABLE_COLUMN1 = "I'm not sure text is Saleable?";
	public static final String INVALID_PO_CHECK_ERROR="The reference number is in an incorrect format.";
	public static final String CANNOT_ADD_TO_CART_MSG="Cannot Add to";
	public static final String ZERO_RESULTS_MSG="0 Results for";
	public static final String VIEW_MRA_MSG="viewMra";
	public static final String VIEW_SHIPPING_LABEL_MSG="viewShippingLabel";
	public static final String VIEW_CREDIT_MEMO_MSG="viewCreditMemo";
	public static final String TOTE_MSG="Tote:";
	public static final String PICKED_UP_BY_CUSTOMER_DATE="03/10/2022";
	public static final String PICKED_UP_BY_CUSTOMER_TIME="11:17AM ET";
	public static final String RECEIVED_AT_DC_DATE="03/10/2022";
	public static final String RECEIVED_AT_DC_TIME="11:18AM ET";
	public static final String RECEIVED_AT_PARTIAL_DC_DATE="03/11/2022";
	public static final String RECEIVED_AT_PARTIAL_DC_TIME="11:30AM ET";
	public static final String MFC_PICKED_UP_BY_CUSTOMER_DATE="03/11/2022";
	public static final String MFC_PICKED_UP_BY_CUSTOMER_TIME="10:10AM ET";
	public static final String MFC_RECEIVED_AT_DC_DATE="03/11/2022";
	public static final String MFC_RECEIVED_AT_DC_TIME="10:22AM ET";
	public static final String RETURNS_DESCRIPTION_COLUMN = "Description";
	public static final String ABC_COLUMN = "ABC #";
	public static final String NDC_COLUMN = "NDC";
	public static final String UPC_COLUMN = "UPC";
	public static final String LOT_COLUMN = "Lot #";
	public static final String EXP_COLUMN = "Exp.";
	public static final String INVOICE_COLUMN = "Invoice(s) #";
	public static final String QUANTITY_COLUMN = "Qty.";
	public static final String SALEABLE_COLUMN = "Saleable?";
	public static final String EST_CREDIT_COLUMN = "Est. Credit";
	public static final String TOOLTIP_SALEABLE = "Original condition with no defects or adulteration in packaging or labeling and specifically has no stickers or other marketing not part of the manufacturer's original packaging. Full, unopened packages with seals intact. Merchandise must have a minimum of 9 months dating (current plus 8 months).";
	public static final String INVOICE_COLUMN_CLAIMS = "Invoice(s)";
	public static final String REASON_COLUMN = "Reason";
	public static final String RETURNING_COLUMN = "Returning";
	public static final String SALEABLE_DISABLED_VS_MSG = "Virtual Sequestered products are not returnable as Saleable.";
	public static final String SALEABLE_DISABLED_MSG = "This product cannot be returned if it is saleable";
	public static final String NON_SALEABLE_DISABLED_VS_MSG = "Virtual Sequestered products are not returnable as Non-Saleable.";
	
	//Support Case
	public static final String C_SUPPORTSERVICES = "Support Services";
	public static final String C_SUBMITCASEWARNING = "Are you sure you want to submit this support case?";
	public static final String SUPPORTCASEDISCARDMSG1 = "Are you sure you want to discard?";
	public static final String SUPPORTCASEDISCARDMSG2 = "Your message will NOT be sent.";
	public static final String SUPPORTCASEINVALIDEMAIL = "invalidEmailTesting";
	public static final String SUPPORTCASEINVALIDEMAILERROR = "Please use a valid email format.";
	public static final String SUPPORTCASEWAITINGFORREPLY = "Waiting for Reply";
	public static final String AUTOMATIONEDITCASE = "AutomationEditCase";
	public static final String FIRST_NAME = "Test";
	public static final String LAST_NAME = "Automation";
	public static final String SUPPORT_SERVICE = "Support Services";
	public static final String ACKNOWLEDGEMENT_TITLE = "Acknowledgement";
	public static final String I_AGREE_CHECKBOX_TEXT = "I Agree to the Terms and Conditions";
	public static final String ACKNOWLEDGMENT_MSG = "The person signing is duly authorized to sign on behalf of the pharmacy making this return, and represents and warrants he/she has read the statement on the Return Goods Acknowledgement and certifies that it is true and complete.";
	public static final String ACKNOWLEDGEMENT_HYPERLINK = "VIEW RETURN GOODS ACKNOWLEDGEMENT";
	public static final String ESIGNRETURNACKNOWLEDEMENT = "Return Goods";
		
	//Claims
	public static final String CLAIMS_INVOICE_NUMBER_COLUMN = "Invoice #";
	public static final String CLAIMS_INVOICE_DATE_COLUMN = "Inv Date";
	public static final String CLAIMS_INVOICE_EXP_COLUMN = "Exp Date";
	public static final String CLAIMS_INVOICE_PRICE_COLUMN = "Inv Item Price";
	public static final String CLAIMS_INVOICE_RETURN_QUANTITY_COLUMN = "Return Qty";
	public static final String COMPLAINTS_INV_ITEM_PRICE = "Inv Item Price";
	public static final String COMPLAINTS_RECEIVED_QTY = "Received Qty";
	public static final String NOTE = "NOTE: Once submitted, a claim cannot be modified online.";
	public static final String NONABCPRODERROR = "Product Not Found";
	public static final String NONABCCARTERROR = "Unknown Product for";
	
	// Attribute Values
	public static final String C_DAMAGEDCLAIM = "Damaged";
	public static final String C_OVERAGECLAIM = "Overage";
	public static final String C_SHORTAGECLAIM = "Shortage";
	public static final String C_SHORTDATEDCLAIM = "ShortDated";
	public static final String C_INCORRECTPRODUCTCLAIM = "Incorrect Product";
	public static final String C_INCORRECTSHORTAGEPRODUCTCLAIM = "Incorrect Shortage Product";

	
	/**
	 * /**
	 *  Receiving Page
	 *
	 */
	public static final String RECEIVEMODAL_MAIN_MSG = "\"Receive All\" function will overwrite any previously entered quantities with the Invoice Quantity.";
	public static final String RECEIVEMODAL_MSG = "Click Ok to complete.";
	public static final String TOAST_MSG_HEADER = "Invoice Received";
	public static final String TOAST_MSG1 = "Invoice";
	public static final String TOAST_MSG2 = "moved to History";
	public static final String DEFAULT_FILTER = "CSOS Invoices";
	public static final String COMPLETE_STATUS = "Complete";
	public static final String INPROGRESS_STATUS = "In Progress";
	public static final String REOPEN_CONFIRMATION_MAIN_MSG = "Click OK to submit all changes made to items on this invoice.";
	public static final String REOPEN_CONFIRMATION_MAIN_HEADER = "Receive Confirmation";


	/** Download file **/
	public static final String DOWNLOAD_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "downloads" + File.separator;

	public static final String DELETECONFIRMATION = "Are you sure you want to delete the selected Non-ABC product from this account?";

	//Tools
	public static final String TDNOPRODFOUND = "No Products Found";


	//Flu Pre Orer
	
	public static final String titleFluContacts = "Flu Contacts";
	public static final String titletextFluConatcts = "Identify all those who should receive flu communications, including preorder confirmations, shipping notices and reminders.";
	public static final String firstnameToolTip = "First Name is required";
	public static final String lastnameToolTip = "Last Name is required";
	public static final String phoneToolTip = "Phone # is required";
	public static final String emailToolTip = "Email is required";
	public static final String notefirstline = "Refer to terms and conditions for information relevant to editing previously submitted preorders.";
	public static final String noteSecondLine = "For any questions, contact 866.281.4FLU (4358) or flu@amerisourcebergen.com.";
	public static final String modalTitle = "Unsaved Changes";
	public static final String modalMessage = "Leaving this page will result in a loss of all unsaved changes. Are you sure you want to continue?";
	public static final String toolTipMessage = "Preorder quantity can no longer be reduced.";
	public static final String removeToolTipMessage = "At least one Contact is required for Flu preorder";
	public static final String textTitle = "The following contacts have been selected to receive flu communications.";
	public static final String uniquePONumberTextMessage = "Please enter a unique PO number or leave blank for auto-generated PO";
	public static final String duplicatePONumberRepeatedText = "PO number cannot be repeated for this vaccines season";
	
	/*
	 * Covid 19 
	 */
	
	public static final String C_COVIDCDPLINKTEXT = "COVID-19 CDC-FPP CUSTOMER DATA PORTAL";
	public static final String C_COVIDCDPLINK = "https://covidtest.amerisourcebergen.com/CDCPortal/";
	public static final String C_COVIDNCPDPTEXT = "Applies to selected account - ";
	
	/*
	 * Price Break Indicator
	 */
	public static final String EXPECTEDPRICEBREAKTOOLTIPHEADER = "Pricing Options:";
	public static final String EXPECTEDPRICEBREAKTOOLTIPINSTRUCTION = "Pricing discount will apply in the cart";
	public static final String EXPECTEDPRICEBREAKTOOLTIPTABLEHEADERQUANTITY = "Quantity:";
	public static final String EXPECTEDPRICEBREAKTOOLTIPTABLEHEADERACQCOST= "Acq. Cost:";
	
	/*
	 * Contract Product Attributes
	 */
	public static final String PRODUCT_DESCRIPTION="PRODUCT DESCRIPTION";
	public static final String CONTRACT_TOOLTIP_MSG_DESC = "PRXO";
	public static final String CONTRACT_TOOLTIP_MSG_NUMBER = "4000000623";
	
	/**
	 * Page : New Carts Page
	 **/
	public static final String NEWCARTTEMPLATES = "TEMPLATES";
	public static final String NEWCARTOPENCARTS = "OPENCARTS";
	public static final String NO_PROMO_PRICE_SELECTED = " Promo pricing is applied to 0 of 2 eligible Products";
	public static final String APPLY_PROMO_PRICE_MSG = "Would you like to apply promo pricing to all the eligible products in your cart?";
	public static final String PROMO_PRICE_SELECTED = " Promo pricing is applied to 2 of 2 eligible Products";
	public static final String ELIGIBLE_PROMO_PRICE_MSG = "All eligible products will be purchased at the promo price.";
	public static final String PROMO_APPLIED_MSG = "Promo applied to eligible products";
	public static final String TWO_PROMO_PRICE_SELECTED = " Promo pricing is applied to 1 of 2 eligible Products";
	public static final String PROMO_SELECTED = " Promo pricing is applied to 1 of 1 eligible Products";
	public static final String MARK_ALL_DO_NOT_SUB = "Mark all as Do Not Sub";
	
	/*
	 * Settings & Preferences Page
	 */
	public static final String DATEFORMAT = "MM/DD/YYYY";
	
	/*
	 * Browse By Category Page
	 */
	public static final String CATALOGSEARCHPAGEURL = "catalog-search";
	
	/*
	 * Marketplace Enrollment Page
	 */
	public static final String VIEW_TERMS_CONDITIONS="3PMP-PILOT-Customer-TC-Final-v2-7.22.pdf";
	
	/*
	 * IEGP
	 */
	public static final String HEXCODEFORCOLORGREEN = "#99f1d1";
	
	/*
	 * Walgreens
	 */
	public static final String RXBADGE = "RX";
	public static final String SSBADGE = "SS";
	
	/*
	 * Marketplace Pilot Page
	 */
	public static final String TERMS_CONDITIONS="marketplace-pilot";
	
	/*
	 * Payment Methods Tab
	 */
	public static final String BANK_INFO="There are no payment methods set up for this account. Add a";
	
	/*
	 * Recurring Orders Page
	 */
	public static final String RECURRING_ORDERS_VIEW_DETAILS_PAGE_URL="ro-display";
	public static final String RECURRING_ORDERS_DASHBOARD_PAGE_URL="ro-dashboard";
	
	/*
	 * New DSCSA Reporting Page
	 */
	public static final String NEW_DSCSA_REPORTING_PAGE_URL="new-dscsa-report";
	public static final String INFORMATIONAL_REGULATORY_URL="informational-regulatory-reports";
	
	/*
	 * DSCSA General Returns
	 */
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_NUMBER_COLUMN = "Invoice #";
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_DATE_COLUMN = "Inv. Date";
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_LINE_COLUMN = "Line";
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_LOT_COLUMN = "Lot #";
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_EXP_COLUMN = "Exp.";
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_PRICE_COLUMN = "Inv. Item Price";
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_QUANTITY_COLUMN = "Inv. Qty.";
	public static final String DSCSA_RETURNS_CLAIMS_INVOICE_ELIG_QUANTITY_COLUMN = "Eligible Qty";
	public static final String DSCSA_RETURNS_INVOICE_SALEABLE_COLUMN = "Saleable?";
	public static final String DSCSA_RETURNS_INVOICE_RETURN_QUANTITY_COLUMN = "Return Qty";
	public static final String DSCSA_RETURNS_INVOICE_RECEIVED_QTY_COLUMN = "Received Qty";
	public static final String DSCSA_RETURNS_INVOICE_SHORTAGE_QTY_COLUMN = "Shortage Qty";
	public static final String DSCSA_RETURNS_INVOICE_SHORTDATED_QTY_COLUMN = "Shortdated Qty";
	public static final int DSCSA_CLAIM_REFRESH_COUNT = 50;
	
	/*
	 * DSCSA Page URL
	 */
	public static final String DSCSAPAGEURL = "dscsa-resource";
	
	/*
	 * BIOSIMILAR INFORMATION HUB Page URL
	 */
	public static final String BIOSIMILAR_INFORMATION_HUB_URL = "biosimilar-hub";
	
	/*
	 * Costco Central Fill Order Type Values
	 */
	public static final String COSTCO_Central_FILL_STANDARD_ORDER_TYPE_VALUE= "ZCOF";
	public static final String COSTCO_Central_FILL_CSOS_ORDER_TYPE_VALUE= "ZCCS";
	public static final String COSTCO_Central_FILL_C2_ORDER_TYPE_VALUE= "ZCOC";
	public static final String COSTCO_Central_FILL_LABEL_ORDER_TYPE_VALUE= "ZCOF";
	
	/*
	 * Old DSCSA Reporting Page
	 */
	public static final String OLD_DSCSA_REPORTING_PAGE_URL="dscsa-report";
	
	/*
	 * User Management Page
	 */
	public static final String USER_MANAGEMENT_PAGE_URL="user-management";
	
	/*
	 * Support Services Page
	 */
	public static final String SUPPORT_SERVICES_PAGE_URL = "self-service";
	
	/*
	 * My Invoices Page
	 */
	public static final String MY_INVOICES_PAGE_URL = "my-invoices";
	
	/*
	 * Receiving Page
	 */
	public static final String RECEIVING_PAGE_URL = "receiving";
	
	/*
	 * Open Carts Page
	 */
	public static final String OPEN_CARTS_PAGE_URL = "open-carts";
	
	/*
	 * My Rebates  Page
	 */
	public static final String MY_REBATES_PAGE_URL = "my-rebates";
	
	/*
	 * Seasonal Vaccines Preorder Page
	 */
	public static final String SEASONAL_VACCINES_PREORDER_PAGE_URL = "seasonalvaccinespreorder";
	
	/*
	 * DSCSA Reporting Resource Page
	 */
	public static final String DSCSA_REPORTING_PAGE_URL="dscsa-resource";
}