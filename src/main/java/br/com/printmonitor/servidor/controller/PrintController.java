package br.com.printmonitor.servidor.controller;

import br.com.printmonitor.servidor.model.Printer;
import br.com.printmonitor.servidor.repository.PrinterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrintController {

    private final PrinterRepository printerRepository;

    public PrintController(PrinterRepository repository)
    {
        this.printerRepository = repository;
    }

    @GetMapping("/printers")
    public List<Printer> printerList()
    {
        return printerRepository.findAll();
    }
}
