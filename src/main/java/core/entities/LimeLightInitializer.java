package core.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class LimeLightInitializer {

    public static enum ProcessState { NEW, PROCESSED, PROCESSED_UNSHIPPABLE, ERROR, ERROR_NO_CRM_ACCESS, DOES_NOT_EXIST, ERROR_RETRY_LIMIT_EXCEEDED, PULL_ORDER_LIMIT_EXCEEDED, TEST};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private long accountId;

    @NotNull
    @Column(nullable = false)
    private String apiKey;

    @NotNull
    @Column(nullable = false)
    private String parentOrderId;

    private String grandParentOrderId;

    @NotNull
    @Column(nullable = false)
    private boolean insuranceSelected;

    private String cvv;

    @NotNull
    @Column(nullable = false)
    private boolean active;

    @NotNull
    @Column(nullable = false)
    private Timestamp created;

    private Timestamp processed;

    private ProcessState processState;

    private int attempts;

    private String senderIp;

    private long limeLightOrderId;

    // ----------------------------------------------


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public String getGrandParentOrderId() {
        return grandParentOrderId;
    }

    public void setGrandParentOrderId(String grandParentOrderId) {
        this.grandParentOrderId = grandParentOrderId;
    }

    public boolean isInsuranceSelected() {
        return insuranceSelected;
    }

    public void setInsuranceSelected(boolean insuranceSelected) {
        this.insuranceSelected = insuranceSelected;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getProcessed() {
        return processed;
    }

    public void setProcessed(Timestamp processed) {
        this.processed = processed;
    }

    public ProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(ProcessState processState) {
        this.processState = processState;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public long getLimeLightOrderId() {
        return limeLightOrderId;
    }

    public void setLimeLightOrderId(long limeLightOrderId) {
        this.limeLightOrderId = limeLightOrderId;
    }
}
