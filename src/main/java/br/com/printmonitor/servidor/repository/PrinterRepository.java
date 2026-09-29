package br.com.printmonitor.servidor.repository;

import br.com.printmonitor.servidor.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterRepository extends JpaRepository<Printer, Integer > {

}
