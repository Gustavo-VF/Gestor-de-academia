# GestorAcademia

## Nome do Projeto
GestorAcademia - Sistema de Gestão de Academia

## Nome completo dos integrantes
- Gustavo Vaz Ferreria

## Tema escolhido
Sistema de gestão para academia de ginástica.

## Descrição do problema resolvido
O sistema permite o gerenciamento de uma academia, controlando os planos oferecidos, os usuários cadastrados e as matrículas dos alunos. O administrador pode cadastrar e editar planos e editar usuarios usuários, enquanto cada aluno pode se cadastrar caso não tenha um login e visualizar e gerenciar suas próprias matrículas.

## Lista de entidades implementadas
- **Plano** - representa os planos de treino oferecidos pela academia (id, nome, descrição, duração, preço, modalidade, status)
- **Usuario** - representa os usuários do sistema, podendo ser aluno ou administrador (id, nome, CPF, email, senha, perfil)
- **Matricula** - representa o vínculo entre um usuário e um plano (id, data de início, data de vencimento, status, observação, planoId, usuárioId)

## Instruções para execução

### Pré-requisitos
- Java 17 ou superior
- MariaDB rodando em `localhost:3306`
- Usuário: `root`, Senha: `root` 

### Passos
1. Extraia o arquivo `projeto.zip`
2. Abra o terminal na pasta do projeto
3. Execute o comando:
```
./gradlew run
```
4. O sistema criará o banco de dados `academia` e as tabelas automaticamente na primeira execução.
5. Login padrão do administrador:
   - E-mail: `admin@academia.com`
   - Senha: `admin123`
   
   Login padrão do usuario: // mas pode ser criados outros usuarios 
   - E-mail: `user@email.com`
   - Senha: `senha123`


## Link para o vídeo
https://youtu.be/cNFcyJjVUAo
