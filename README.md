# LibraryApi

**LibraryApi** é uma API desenvolvida em **Java** com **Spring Boot** para o gerenciamento de bibliotecas. Utiliza o banco de dados **H2** e será integrada com um front-end desenvolvido em **React**.

## 🚀 Começando

Essas instruções irão ajudá-lo a rodar o projeto localmente para desenvolvimento e testes.

### 📋 Pré-requisitos

Antes de começar, você precisará instalar as seguintes ferramentas:

- **Git**: [Instalar Git](https://git-scm.com/)
- **Java 17 ou superior**: [Baixar Java](https://adoptium.net/)
- **Maven**: [Baixar Maven](https://maven.apache.org/)
- **IDE** (recomendado: IntelliJ IDEA ou VS Code)

### 🔧 Instalando

1. **Clone o repositório**

   Clone o repositório do **LibraryApi** para sua máquina local:
   ```
   git clone https://github.com/Jadilumi/LibraryApi.git
   ```
   
 2.  **Acesse a pasta do projeto**
   ```
   cd LibraryApi
   ```

 3.  **Instale as dependências**
   Se você já tiver o Maven instalado, basta rodar o comando:
   ```
   mvn install
   ```

 4.  **Execute o projeto**
   Para rodar o projeto localmente, use o comando:
   ```
   mvn spring-boot:run
   ```
A API será executada em `http://localhost:8080`.

## 🎯 Funcionalidades

- Gerenciamento de livros
- Registro de usuários
- Empréstimos de livros

## ⚙️ Tecnologias Usadas

- Java 17
- Spring Boot
- H2 Database (para persistência de dados)
- Maven (para gerenciamento de dependências)
- React (para o front-end)

## 🤝 Como contribuir

Para contribuir com este projeto, siga as etapas descritas no arquivo [CONTRIBUTING.md](CONTRIBUTING.md).

### Observações

- Certifique-se de que suas alterações estão bem testadas antes de enviar um pull request.
- Mantenha seu fork atualizado antes de começar a trabalhar:
   ```
   git fetch upstream
   git merge upstream/main
   ```
## 📄 Licença

Este projeto está licenciado sob a licença [Creative Commons](LICENSE) - veja o arquivo [LICENSE.md](LICENSE) para mais detalhes.

---

## Autores

- José Milton de Oliveira Neto - 11035714
- Dielder Leal                 - 01461719
- Jailton Júnior               - 01225496
- Leonardo Emanuel             - 01418337
- Lucas Crespo                 - 01419654

Obrigado por sua contribuição para o **LibraryApi**! 🎉
