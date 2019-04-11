package util;

import java.time.LocalDateTime;
import java.util.Date;

public class BusinessProgressInfo {
    LocalDateTime begin;
    LocalDateTime end;
    ReceiptCategory receiptCategory;
    int clientID;
    int operatorID;
    int repositoryID;

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public ReceiptCategory getReceiptCategory() {
        return receiptCategory;
    }

    public void setReceiptCategory(ReceiptCategory receiptCategory) {
        this.receiptCategory = receiptCategory;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }

    public int getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(int repositoryID) {
        this.repositoryID = repositoryID;
    }
}
