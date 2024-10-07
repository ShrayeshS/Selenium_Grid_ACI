package helper;

import com.epam.reportportal.listeners.ItemStatus;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.Launch;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ReportPortalScreenshot {
    private static final Logger log = LogManager.getLogger(ReportPortalScreenshot.class);

    public void screenshot(WebDriver driver) {
        ReportPortalMessage message = null;
        TakesScreenshot ts = (TakesScreenshot)driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            Date d = new Date();
            FileUtils.copyFile(srcFile, new File("./screenshots/" + d.toString().replace(":", "_") + ".png"));
            String rp_message = "attached screenshot";
            log.info(rp_message);
            message = new ReportPortalMessage(srcFile, rp_message);
        } catch (IOException e) {
            log.warn("Cannot send screenshot to ReportPortal as a ReportPortalMessage");
        }
        Launch.currentLaunch().getStepReporter().sendStep(ItemStatus.INFO, "attached screenshot - sendStep", srcFile);
    }

}
