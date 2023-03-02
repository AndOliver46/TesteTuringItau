import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.css";
import { logout } from "../services/auth";

import api from "../services/Api";

export default function Criar({ history }) {
  const [codigoAgencia, setCodigoAgencia] = useState("8569");
  const [numeroConta, setNumeroConta] = useState("");
  const [senha, setSenha] = useState("");
  const [nome, setNome] = useState("");
  const [cpf, setCpf] = useState("");

  const handleCriar = async (e) => {
    logout();
    e.preventDefault();
    await api
      .post("/cliente/criarConta", {
        codigoAgencia,
        numeroConta,
        senha,
        nome,
        cpf,
      })
      .then((response) => {
        const p = document.querySelector("#respostaCriar");
        p.style.color = "#006400";
        p.textContent =
          "Conta criada com sucesso! Numero: " + response.data.numero;
      })
      .catch((error) => {
        if (error.response) {
          console.log(error.response.data.message);
          const p = document.querySelector("#respostaCriar");
          p.textContent = error.response.data.message;
        }
      });
  };

  const handleVoltar = async () => {
    logout();
    history.push("/login");
  };

  return (
    <div className="container">
      <h1 align="center" className="text-primary" style={{ margin: "20px" }}>
        Criar conta
      </h1>

      <div className="card">
        <div className="card-body">
          <form onSubmit={handleCriar}>
            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="tipo">Escolha sua agência</label>
                <select
                  id="tipo"
                  type="tipo"
                  className="form-control"
                  placeholder="Entre o valor"
                  value={codigoAgencia} // ...force the select's value to match the state variable...
                  onChange={(e) => setCodigoAgencia(e.target.value)}
                >
                  <option value="5300">
                    5300 - Avenida Brigadeiro Faria Lima s/n - Itaim Bibi
                  </option>
                  <option value="7420">
                    7420 - Rua Jundiaí, 125 - Paraíso
                  </option>
                  <option value="8569">
                    8569 - Marechal Tito Avenida Marechal Tito, 4334 - Itaim
                    Paulista
                  </option>
                  <option value="0249">
                    0249 - Via Anchieta Rodovia Anchieta - Pista Lateral, 1100 -
                    Sacomã
                  </option>
                </select>
              </div>
            </div>

            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="numeroConta">Numero da conta</label>
                <input
                  id="idNumeroConta"
                  className="form-control"
                  placeholder="Informe o numero da conta"
                  required
                  pattern="^[0-9]{5}$"
                  value={numeroConta}
                  onChange={(e) => setNumeroConta(e.target.value)}
                />
              </div>
            </div>

            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="senha">Senha</label>
                <input
                  id="senha"
                  type="password"
                  className="form-control"
                  placeholder="Entre com a senha"
                  pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8}$"
                  onInvalid="alert('Must contain 6 or more characters');"
                  value={senha}
                  onChange={(e) => setSenha(e.target.value)}
                />
              </div>
            </div>

            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="nome">Nome</label>
                <input
                  id="nome"
                  type="nome"
                  className="form-control"
                  placeholder="Entre com o nome completo"
                  value={nome}
                  onChange={(e) => setNome(e.target.value)}
                />
              </div>
            </div>

            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="cpf">CPF</label>
                <input
                  id="cpf"
                  type="cpf"
                  className="form-control"
                  placeholder="Entre com o CPF"
                  pattern="(^(\d{3}.\d{3}.\d{3}-\d{2})|(\d{11})$)"
                  value={cpf}
                  onChange={(e) => setCpf(e.target.value)}
                />
              </div>
            </div>

            <div className="col-md-6 offset-md-3">
              <p id="respostaCriar" style={{ color: "red" }}></p>
            </div>

            <div className="d-flex justify-content-center">
              <div className="flex justify-content-center" align="center">
                <button
                  type="submit"
                  className="btn btn-primary"
                  align="center"
                  style={{ margin: "10px" }}
                >
                  Abrir conta
                </button>
              </div>
              <div className="flex justify-content-center" align="center">
                <button
                  style={{ margin: "10px" }}
                  type="button"
                  className="btn btn-primary"
                  onClick={() => handleVoltar()}
                >
                  Voltar
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>

      <div className="card">
        <h4 style={{ margin: "10px" }}>Regras:</h4>
        <p style={{ margin: "10px" }}>
          <strong>Numero da conta</strong>: Exatos 5 numeros, sem espaços.
        </p>
        <p style={{ margin: "10px" }}>
          <strong>Senha</strong>: 8 caracteres, pelo menos 1 letra maiúscula,
          pelo menos 1 letra minuscula, pelo menos 1 caractere especial.
        </p>
        <p style={{ margin: "10px" }}>
          <strong>CPF</strong>: 00000000000 ou 000.000.000-00.
        </p>
      </div>
    </div>
  );
}
