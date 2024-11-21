# Contribuindo para LibraryApi

Obrigado por considerar contribuir para o LibraryApi! Este documento fornece as diretrizes e instruções para facilitar sua colaboração.

---

## 📘 Descrição do Projeto

O LibraryApi é uma API desenvolvida em Java com Spring Boot para gerenciamento de bibliotecas. Ele utiliza o banco de dados H2 para armazenamento e será integrado com um front-end desenvolvido em React.

---

## 📥 Como baixar o repositório

Siga os passos abaixo para clonar e configurar o projeto localmente:

1. Clone o repositório:
   ```
   git clone https://github.com/Jadilumi/LibraryApi.git
   ```
2. Acesse a pasta do projeto:

   ```
   cd LibraryApi
   ```
3. Crie um branch para realizar as alterações:

   ```
   git checkout -b nome-do-seu-branch
   ```

---

## ✅ Pré-requisitos

Certifique-se de atender aos seguintes requisitos antes de começar a contribuir:

- **Git** instalado ([veja aqui como instalar](https://git-scm.com/)).
- **Java 17 ou superior** ([baixe aqui](https://adoptium.net/)).
- **Maven** para gerenciar dependências ([baixe aqui](https://maven.apache.org/)).
- Banco de dados **H2** já configurado no projeto.
- Editor de código ou IDE (recomendado: IntelliJ IDEA ou VS Code).
- Todas as dependências instaladas conforme o arquivo `pom.xml`.

---
  
## 🤝 Como contribuir

Siga estas etapas para contribuir para o projeto:

1. **Faça um fork do repositório original**:  
   Clique no botão **Fork** no canto superior direito da página do repositório.

2. **Clone o fork no seu computador**:  
   Execute o comando abaixo no terminal para clonar o repositório em sua máquina:
   
   ```
   git clone https://github.com/seu-usuario/LibraryApi.git
   ```
3. **Crie um branch para trabalhar nas alterações**:  
   Antes de fazer qualquer alteração, crie um novo branch para isolar suas mudanças:
   
   ```
   git checkout -b nome-do-seu-branch
   ```
   
4. **Faça as alterações no código.**

5. **Adicione os arquivos modificados ao controle de versão**:  
   Use o comando abaixo para adicionar os arquivos modificados ao Git:
   ```
   git add .
   ```
6. **Faça um commit das alterações**:  
   Agora, faça um commit descrevendo suas mudanças:
   ```
   git commit -m "Descrição clara da alteração"
   ```
7. **Envie o branch para o seu fork**:  
   Para enviar seu branch para o seu fork no GitHub:
   ```bash
   git push origin nome-do-seu-branch
   ```
8. **Abra um Pull Request (PR)**:  
   - Vá ao repositório original: [LibraryApi](https://github.com/Jadilumi/LibraryApi).
   - Clique em **Pull Requests** > **New Pull Request**.
   - Compare o branch do seu fork com o branch principal (`main`) do repositório original.

9. **Descreva o Pull Request**:  
   Inclua detalhes sobre as alterações feitas, o problema resolvido ou a funcionalidade adicionada.

---

### 📋 Observações importantes

- Siga o padrão de código do projeto.
- Certifique-se de que suas alterações passaram nos testes antes de abrir um PR.
- Para manter seu fork atualizado antes de começar a trabalhar:
   ```
   git fetch upstream
   git merge upstream/develop
   ```
Obrigado por sua contribuição para o LibraryApi! 🎉
