# PrintMonitor

Sistema de monitoramento de impressoras para ambientes com muitos equipamentos e redes separadas (caso de uso: uma faculdade privada). O objetivo é acompanhar o uso e os recursos de cada impressora (páginas impressas, toner, papel) e gerar relatórios diários, mensais e anuais em gráficos.

> **Status:** em desenvolvimento. Projeto de estudo, construído passo a passo.

## Objetivos

- Cadastrar e organizar impressoras por setor/prédio
- Coletar via **SNMP** contadores de páginas, nível de toner e status das bandejas
- Gerar alertas (toner baixo, papel acabando, impressora offline)
- Gerar relatórios diários, mensais e anuais em gráficos
- Controlar acesso por perfil (administrador e técnico)

## Arquitetura

A rede é dividida em várias redes isoladas, então o sistema é separado em duas partes:

| Componente | Função |
|---|---|
| **Agente** | Roda dentro de cada rede, consulta as impressoras por SNMP e envia as leituras ao servidor. Se o servidor estiver fora do ar, guarda as leituras e reenvia depois. |
| **Servidor central** | API REST, banco de dados, regras de alerta e geração de relatórios. |
| **Dashboard** | Interface web com painel e gráficos. |

```
[Impressoras] <--SNMP-- [Agente] --HTTPS--> [Servidor] --> [Banco de dados]
                                                 ^
                                            [Dashboard]
```

## Tecnologias

- Java 21
- Spring Boot 4 (Spring Web, Spring Data JPA)
- Maven
- H2 (banco em memória, apenas para desenvolvimento; depois PostgreSQL ou MySQL)

## Estrutura atual

```
servidor/
└── src/main/java/br/com/printmonitor/servidor/
    ├── ServidorApplication.java
    ├── controller/
    │   ├── HelloController.java
    │   └── PrintController.java
    ├── model/
    │   └── Printer.java
    └── repository/
        └── PrinterRepository.java
```

## Como executar

### Pré-requisitos

- JDK 21
- Maven 3.9+ (ou o wrapper `mvnw` do projeto)
- IntelliJ IDEA (ou outra IDE Java)

### Passos

1. Clone o repositório e abra a pasta `servidor` na IDE.
2. Aguarde o Maven baixar as dependências.
3. Execute a classe `ServidorApplication`.
4. Acesse `http://localhost:8080/hello`.

### Observações

- **Pouca memória (Windows):** se aparecer o erro `os::commit_memory ... errno=1455`, limite a JVM em *Run > Edit Configurations > Modify options > Add VM options*:
  ```
  -Xms64m -Xmx256m -XX:MaxMetaspaceSize=192m -XX:ReservedCodeCacheSize=64m -XX:TieredStopAtLevel=1
  ```
- **Console do H2 (Spring Boot 4):** para acessar `/h2-console`, adicione ao `pom.xml`:
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-h2console</artifactId>
  </dependency>
  ```
  e habilite `spring.h2.console.enabled=true` no `application.properties`.

## Configuração (`application.properties`)

```properties
spring.datasource.url=jdbc:h2:mem:printmonitor
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Endpoints

| Método | Rota | Descrição | Situação |
|---|---|---|---|
| GET | `/hello` | Teste de funcionamento do servidor | pronto |
| GET | `/printer-test` | Retorna uma impressora fixa em JSON (teste) | pronto |
| GET | `/printers` | Lista as impressoras do banco | pronto |
| POST | `/printers` | Cadastra uma impressora | pronto |

## Modelagem (UML)

A modelagem do projeto inclui:

- Diagrama de casos de uso (Administrador, Técnico e Agente de Coleta)
- Diagrama de classes (Local, Agente, Impressora, Leitura, NivelSuprimento, StatusBandeja, Alerta, Usuario, Relatorio)
- Diagrama de sequência do fluxo de coleta
- Diagrama de implantação (agentes por rede + servidor central)

Decisão de projeto: a **Leitura** (retrato tirado a cada coleta) é separada da **Impressora** (cadastro). Toner e bandejas são classes próprias, para suportar impressoras monocromáticas e coloridas no mesmo modelo.

## Roadmap

- [x] Ambiente configurado (IntelliJ, JDK 21, Spring Boot)
- [x] Primeiro endpoint (Hello World)
- [x] Entidade `Printer` e repositório JPA
- [x] Listagem de impressoras (`GET /printers`)
- [x] Cadastro de impressoras (`POST /printers`)
- [ ] Padronizar nomes do código e do UML (português ou inglês)
- [ ] Persistência em arquivo / migração para PostgreSQL ou MySQL
- [ ] Entidades `Leitura`, `NivelSuprimento`, `StatusBandeja`, `Alerta`
- [ ] Agente de coleta com SNMP
- [ ] Envio das leituras do agente ao servidor
- [ ] Alertas
- [ ] Relatórios e gráficos (diário, mensal, anual)
- [ ] Autenticação e perfis de usuário
- [ ] Dashboard web
- [ ] Implantação

## Licença

Projeto de estudo. Licença a definir.