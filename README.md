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
   git merge upstream/develop
   ```

### Fluxo de trabalho 
Use o PlantUML para melhor entendimento

   ```
   @startuml
   start
   :Desenvolver em uma Branch;
   :Commit das alterações;
   :Push para o repositório remoto;
   :Pedido de Merge para a branch `develop`;
   if (Merge Avaliado?) then (sim)
     :Aplicar Merge para `develop`;
     :Testes e revisão;
     :Pedido de Merge para `main`;
     if (Merge Avaliado?) then (sim)
       :Aplicar Merge para `main`;
       :Deploy para produção;
     else (não)
       :Revisar Merge para `main`;
     endif
   else (não)
     :Revisar Merge para `develop`;
   endif
   stop
   @enduml
   ```

![image](https://github.com/user-attachments/assets/daac90ab-bf40-4983-93e1-122ddb397781)


### Lista de comandos GIT (mais usados durante o desenvolvimento)

### 1. Inicialização e Clonagem
Inicializar um novo repositório Git
- git init

### Clonar um repositório remoto
- git clone <url-do-repositorio>

### 2. Trabalhando com Branches
Verificar as branches locais
- git branch

### Criar uma nova branch e mudar para ela
- git checkout -b <nome-da-branch>

### Mudar para uma branch existente
- git checkout <nome-da-branch>

### Listar todas as branches (locais e remotas)
- git branch -a

### 3. Trabalhando com Commits
Adicionar arquivos ao staging (preparar para commit)
- git add <arquivo>

### Adicionar todos os arquivos modificados
- git add .

### Fazer um commit
- git commit -m "Mensagem do commit"

### Verificar o status dos arquivos
- git status

### 4. Trabalhando com Repositórios Remotos
 Verificar os repositórios remotos configurados
- git remote -v

### Adicionar um repositório remoto
- git remote add origin <url-do-repositorio>

### Enviar commits para o repositório remoto
- git push origin <nome-da-branch>

### Baixar alterações do repositório remoto
- git fetch

### Atualizar a branch local com as mudanças do repositório remoto
- git pull origin <nome-da-branch>

### 5. Merge de Branches
Fazer o merge de uma branch em outra
- git checkout develop
- git merge <nome-da-branch>

### Verificar se existem conflitos de merge
- git status

### Resolver conflitos de merge
### Abra os arquivos conflitantes e faça as alterações necessárias.
- git add <arquivo-resolvido>

### Commitar após resolver conflitos
- git commit -m "Resolução de conflito"

### 6. Trabalhando com Pull Requests (PR)
### Criar um Pull Request (via interface web do GitHub/GitLab/Bitbucket)
### Acesse o repositório na interface web e crie um PR para a branch `develop` ou `main`.

### 7. Revisão de Pull Request e Merge
### Fazer merge do PR após revisão (via interface web)

### 8. Rebasing (opcional)
Rebase da branch local com a branch de destino (normalmente, `develop`)
- git checkout <nome-da-branch>
- git fetch origin
- git rebase origin/develop

### 9. Limpeza e Manutenção do Repositório
Excluir uma branch local após o merge
- git branch -d <nome-da-branch>

### Excluir uma branch remota após o merge
- git push origin --delete <nome-da-branch>

### 10. Outros Comandos Úteis
### Visualizar o histórico de commits
- git log

### Visualizar o histórico de commits com gráficos
- git log --oneline --graph --all


### 📄 Licença

Este projeto está licenciado sob a licença [Creative Commons](LICENSE) - veja o arquivo [LICENSE.md](LICENSE) para mais detalhes.

---

Obrigado por sua contribuição para o **LibraryApi**! 🎉
