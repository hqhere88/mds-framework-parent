package mds.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true) // hidden-source-line
// tag::snippet[]
public class Report {
    @NotBlank
    private String assignee;

    @NotBlank
    private Date due;

    @NotBlank
    private Integer id;

    @NotBlank
    private String report;

    @NotBlank
    private String status;

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Report)) {
            return false;
        }
        Report other = (Report) obj;
        return Objects.equals(id, other.id);
    }
}
// end::snippet[]