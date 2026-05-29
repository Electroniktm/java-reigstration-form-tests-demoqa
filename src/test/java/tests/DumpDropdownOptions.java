package tests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class DumpDropdownOptions {
    @Test
    void dumpStateCityAndSubjectsOptions() throws Exception {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").shouldBe(visible);
        
        StringBuilder sb = new StringBuilder();
        
        // Remove ads using JavaScript
        sb.append("=== Removing ads ===\n");
        executeJavaScript("""
            document.querySelectorAll('[id*="Ad.Plus"], [class*="ad-"], [class*="banner-"]').forEach(el => el.remove());
            document.querySelectorAll('[id*="floating"], [class*="floating"]').forEach(el => el.remove());
        """);
        sb.append("Ads removed\n");
        sleep(1000);
        
        // Now try clicking state
        sb.append("\n=== Clicking state ===\n");
        $("#state").click();
        sleep(1000);
        
        sb.append("=== After clicking state ===\n");
        sb.append("All [role=option] count: ").append($$("[role='option']").size()).append("\n");
        for (int i = 0; i < $$("[role='option']").size(); i++) {
            var el = $$("[role='option']").get(i);
            sb.append("option ").append(i).append(": text='").append(el.text()).append("'\n");
        }
        
        // Close the dropdown
        $("#state").click();
        sleep(500);
        
        // Now try subjects
        sb.append("\n=== Clicking subjects ===\n");
        $("#subjects").click();
        sleep(1000);
        
        sb.append("=== After clicking subjects ===\n");
        sb.append("All [role=option] count: ").append($$("[role='option']").size()).append("\n");
        for (int i = 0; i < $$("[role='option']").size(); i++) {
            var el = $$("[role='option']").get(i);
            sb.append("option ").append(i).append(": text='").append(el.text()).append("'\n");
        }
        
        // Close the dropdown
        $("#subjects").click();
        sleep(500);
        
        // Now try city
        sb.append("\n=== Clicking city ===\n");
        $("#city").click();
        sleep(1000);
        
        sb.append("=== After clicking city ===\n");
        sb.append("All [role=option] count: ").append($$("[role='option']").size()).append("\n");
        for (int i = 0; i < $$("[role='option']").size(); i++) {
            var el = $$("[role='option']").get(i);
            sb.append("option ").append(i).append(": text='").append(el.text()).append("'\n");
        }
        
        java.io.FileWriter writer = new java.io.FileWriter("dropdown-options.txt");
        writer.write(sb.toString());
        writer.close();
        System.out.println("Dropdown options written to dropdown-options.txt");
    }
}
