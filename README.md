#  Delivery Flash

**Delivery Flash** é um sistema de gerenciamento de entregas desenvolvido como parte da disciplina **Engenharia de Software**, com o objetivo de aplicar e consolidar conceitos de **Programação Orientada a Objetos (POO)**, **boas práticas de arquitetura de software** e o uso do **framework Spring Boot** para construção de aplicações web em Java.

---

##  Tecnologias utilizadas

- **Linguagem:** Java  
- **Framework:** Spring Boot  
- **Banco de dados:** MySQL  
- **Gerenciador de dependências:** Maven  
- **IDE recomendada:** IntelliJ IDEA ou Eclipse  

---

##  Objetivo do projeto

O projeto visa simular o funcionamento de uma aplicação de entregas, abrangendo:
- Cadastro e gerenciamento de clientes e entregadores;  
- Registro e controle de pedidos;  
- Simulação do fluxo de entrega e atualização de status;  
- Organização modular e aplicação de princípios SOLID.

---

##  Estrutura do sistema

O sistema foi desenvolvido em camadas, contemplando:
- **Controller:** gerenciamento das requisições HTTP e integração com as views;  
- **Service:** regras de negócio e validações;  
- **Repository:** persistência dos dados utilizando JPA e MySQL;  
- **Model:** entidades representando os objetos de domínio (Cliente, Pedido, Entregador, etc).  

---

##  Aprendizados e conceitos aplicados

Durante o desenvolvimento, foram aplicados:
- Princípios de **orientação a objetos** (herança, polimorfismo, encapsulamento e abstração);  
- **Modelagem de classes e entidades** com relacionamentos (OneToMany, ManyToOne);  
- Uso de **anotações Spring Boot** para controle e injeção de dependência;  
- Estruturação de **camadas MVC**;  
- **Integração com banco de dados relacional** via Spring Data JPA.

---

##  Considerações finais

Este projeto foi desenvolvido com fins educacionais e tem como principal objetivo o **aperfeiçoamento de práticas de desenvolvimento orientado a objetos** e a **compreensão da arquitetura Spring Boot** aplicada a sistemas reais.
