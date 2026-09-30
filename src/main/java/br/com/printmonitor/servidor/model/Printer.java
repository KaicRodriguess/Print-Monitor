package br.com.printmonitor.servidor.model;

import jakarta.persistence.*;

@Entity
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long printerId;

    private String printerName;
    private String printerIp;
    private String printerModel;
    private Boolean printerStatus;

    public Printer() {}

    public Printer(Long printerId, String printerName, String printerIp, String printerModel, Boolean printerStatus) {
        this.printerId = printerId;
        this.printerName = printerName;
        this.printerIp = printerIp;
        this.printerModel = printerModel;
        this.printerStatus = printerStatus;
    }

    public long getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Long printerId) {
        this.printerId = printerId;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getPrinterIp() {
        return printerIp;
    }

    public void setPrinterIp(String printerIp) {
        this.printerIp = printerIp;
    }

    public String getPrinterModel() {
        return printerModel;
    }

    public void setPrinterModel(String printerModel) {
        this.printerModel = printerModel;
    }

    public Boolean getPrinterStatus() {
        return printerStatus;
    }

    public void setPrinterStatus(Boolean printerStatus) {
        this.printerStatus = printerStatus;
    }
}
