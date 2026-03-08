# Object-Oriented Programming (OOP)

This repository contains a Java-based console application developed to manage the operations of a Health Center. The system allows the registration and management of patients, families, health professionals, and medical services.

The main objective of this project is to apply Object-Oriented Programming (OOP)


The code conteined a severalm functions such as:

->Patient registration and management
->Family association
->Health professional registration
->Scheduling and management of healthcare services
->Data persistence using file serialization

Each module of the system was designed to demonstrate core programming concepts including:

->Object-Oriented Programming
->Data structures using ArrayList
->Command-line interaction
->Data validation and logical rules
->File input/output and object serialization



------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Este repositório contém uma aplicação em Java executada em consola, desenvolvida para gerir as operações de um Centro de Saúde. O sistema permite o registo e gestão de utentes, famílias, profissionais de saúde e serviços médicos.

O sistema suporta várias funcionalidades principais, tais como:

Registo e gestão de utentes
Associação de utentes a famílias
Registo de profissionais de saúde
Marcação e gestão de cuidados de saúde
Persistência de dados através de serialização de ficheiros

Cada módulo do sistema foi desenvolvido para demonstrar conceitos fundamentais de programação, incluindo:

Programação Orientada a Objetos
Estruturas de dados com ArrayList
Interação através da linha de comandos
Validação de dados e regras lógicas
Entrada e saída de ficheiros com serialização de objetos


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



User commands:

| Command                         | Description                                  |
| ------------------------------- | -------------------------------------------- |
| `RU <NomeUtente> <FaixaEtaria>` | Register a new patient                       |
| `LU`                            | List all registered patients                 |
| `AF <NomeUtente> <NomeFamilia>` | Associate a patient with a family            |
| `DF <NomeUtente>`               | Remove a patient from a family               |
| `MF <NomeFamilia>`              | List patients belonging to a specific family |             
| `RF <NomeFamilia>`              | Register a new family                        |
| `LF`                            | List all registered families                 |
| `RP <NomeProfissional> <Categoria>` | Register a health professional           |
| `LP`                             | List all health professionals               |
| `MC <NomeUtente>`                | Schedule healthcare services for a patient  |
| `CC <NomeUtente>`                | Cancel healthcare services for a patient    |
| `LCU <NomeUtente>`               | List services scheduled for a patient       |
| `LCF <NomeFamilia>`              | List services scheduled for a family        |
| `LSP <NomeProfissional> <Categoria>` | List services performed by a professional |
| `LMS <Servico>`                   | List all appointments for a specific service |
| `L`                              | Load saved system data                      |
| `G`                              | Save current system data                    |
