package tests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class DumpPageHtml {
    @Test
    void dumpRegistrationFormHtml() throws Exception {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").shouldBe(visible);
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== All inputs ===\n");
        for (int i = 0; i < $$("input").size(); i++) {
            var el = $$("input").get(i);
            sb.append(i).append(": id=").append(el.getAttribute("id"))
              .append(" name=").append(el.getAttribute("name"))
              .append(" type=").append(el.getAttribute("type")).append("\n");
        }
        sb.append("=== All labels ===\n");
        for (int i = 0; i < $$("label").size(); i++) {
            sb.append(i).append(": ").append($$("label").get(i).text()).append("\n");
        }
        sb.append("=== All divs with id ===\n");
        for (int i = 0; i < $$("div[id]").size() && i < 30; i++) {
            sb.append(i).append(": ").append($$("div[id]").get(i).getAttribute("id")).append("\n");
        }
        
        java.io.FileWriter writer = new java.io.FileWriter("registration-form-inspection.txt");
        writer.write(sb.toString());
        writer.close();
        System.out.println("Inspection written to registration-form-inspection.txt");
    }

    @Test
    void dumpTextBoxHtml() throws Exception {
        open("https://demoqa.com/text-box");
        $("#userName").shouldBe(visible);
        StringBuilder sb = new StringBuilder();
        sb.append("=== All inputs ===\n");
        for (int i = 0; i < $$("input").size(); i++) {
            var el = $$("input").get(i);
            sb.append(i).append(": id=").append(el.getAttribute("id"))
              .append(" name=").append(el.getAttribute("name"))
              .append(" type=").append(el.getAttribute("type"))
              .append(" required=").append(el.getAttribute("required")).append("\n");
        }
        sb.append("=== All labels ===\n");
        for (int i = 0; i < $$("label").size(); i++) {
            sb.append(i).append(": ").append($$("label").get(i).text()).append("\n");
        }
        sb.append("=== Submit button ===\n");
        sb.append("submit: ").append($("#submit").getAttribute("id")).append("\n");
        sb.append("submit text: ").append($("#submit").text()).append("\n");
        sb.append("=== Output ===\n");
        sb.append("output exists: ").append($("#output").exists()).append("\n");
        sb.append("rtpopup exists: ").append($("#rtpopup").exists()).append("\n");
        
        java.io.FileWriter writer = new java.io.FileWriter("text-box-inspection.txt");
        writer.write(sb.toString());
        writer.close();
        System.out.println("Inspection written to text-box-inspection.txt");
    }
}
