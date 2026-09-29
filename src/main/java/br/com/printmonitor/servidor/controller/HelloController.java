package br.com.printmonitor.servidor.controller;


import br.com.printmonitor.servidor.model.Printer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello World!";
    }

    @GetMapping("/printer-test")
    public Printer printerTest()
    {
        return new Printer(1, "RICOH SP377SFNw", "192.168.103.26", "MONO", true);
    }

}
