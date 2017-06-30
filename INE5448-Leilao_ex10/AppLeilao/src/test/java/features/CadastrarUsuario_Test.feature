#Author: ernani.santos@posgrad.ufsc.br
Feature: Cadastrar Usuario
  Cadastrar usuarios

@CadastrarUsuarioSucesso
Scenario: Cadastrar Usuario com Sucesso
Given O nome de usuario "Ernani Cesar"
And o enderco "Campus Universitario"
And e o CPF "055.761.919-00"
And e o e-mail "ernani.santos@posgrad.ufsc.br"
When O usuario nao existir anteriormente
Then O sistema deve cadastrar o usuario com sucesso