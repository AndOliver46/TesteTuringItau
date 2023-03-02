import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.css";

import api from "../services/Api";
import { logout } from "../services/auth";

export default function Main({ history }) {
  const [user, setUser] = useState();
  useEffect(() => {
    api
      .get("/cliente/informacoes")
      .then((response) => setUser(response.data))
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);

  const [valor, setValor] = useState("");
  const [tipo, setTipo] = useState("PIX");
  const [receptor, setReceptor] = useState("");
  const [mensagemErro, setMensagemErro] = useState("");
  const [saldoEmissor, setSaldoEmissor] = useState("");
  const [saldoReceptor, setSaldoReceptor] = useState("");

  const handleTransferir = async (e) => {
    e.preventDefault();
    await api
      .post("/transferencias/transferir", { valor, tipo, receptor })
      .then((response) => {
        setSaldoEmissor(response.data.saldoEmissor);
        setSaldoReceptor(response.data.saldoReceptor);
        document.getElementById("transferenciaSucesso").style.display = "block";
        document.getElementById("transferenciaErro").style.display = "none";
        history.push("/main");
      })
      .catch((error) => {
        document.getElementById("transferenciaSucesso").style.display = "none";
        document.getElementById("transferenciaErro").style.display = "block";
        setMensagemErro(error.response.data.message);
      });
  };

  const handleLogout = async () => {
    await api.delete("/logout", {}).then((response) => {
      logout(response.data.token);
      history.push("/login");
    }).catch((error) => {
      logout();
      history.push("/login");
    });
  };

  return (
    <div className="container">
      <h1 align="center" className="text-primary" style={{ margin: "20px" }}>
        Bem vindo(a)
      </h1>

      <div className="card">
        <div className="App" style={{ padding: "10px" }}>
          <p>Cliente: {user?.cliente.nome}</p>
          <p>CPF: {user?.cliente.cpf}</p>
          <p>Numero da conta: {user?.numero}</p>
          <p>Codigo da agencia: {user?.agencia.codigo} </p>
          <p>Saldo disponível: R$ {user?.saldo}</p>
        </div>
      </div>

      <div
        className="card"
        id="transferenciaSucesso"
        align="center"
        style={{ display: "none" }}
      >
        <div
          className="App"
          style={{ padding: "10px", margin: "0px", color: "#006400" }}
        >
          <p>Sua transferência foi realizada com sucesso!</p>
          <p>Saldo do emissor: R$ {saldoEmissor}</p>
          <p>Saldo do receptor: R$ {saldoReceptor}</p>
        </div>
      </div>

      <div
        className="card"
        id="transferenciaErro"
        align="center"
        style={{ display: "none" }}
      >
        <div
          className="App"
          style={{ padding: "10px", margin: "0px", color: "red" }}
        >
          <p>Sua transferência não foi completada, erro: {mensagemErro}</p>
        </div>
      </div>

      <div className="card">
        <div className="card-body">
          <form onSubmit={handleTransferir}>
            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="receptor">Numero da conta</label>
                <input
                  id="idReceptor"
                  className="form-control"
                  placeholder="Informe o numero da conta de destino"
                  required
                  pattern="^[0-9]{5}$"
                  value={receptor}
                  onChange={(e) => setReceptor(e.target.value)}
                />
              </div>
            </div>
            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="valor">Valor R$ (Somente numeros)</label>
                <input
                  id="valor"
                  type="valor"
                  className="form-control"
                  placeholder="Entre o valor"
                  required
                  pattern="^[0-9]*$"
                  value={valor}
                  onChange={(e) => setValor(e.target.value)}
                />
              </div>
            </div>
            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="tipo">Tipo de transferencia</label>
                <select
                  id="tipo"
                  type="tipo"
                  className="form-control"
                  placeholder="Entre o valor"
                  required
                  value={tipo} // ...force the select's value to match the state variable...
                  onChange={(e) => setTipo(e.target.value)}
                >
                  <option value="PIX">Pix</option>
                  <option value="TED">Ted</option>
                  <option value="DOC">Doc</option>
                </select>
              </div>
            </div>
            <div className="d-flex justify-content-center">
              <div className="block justify-content-center">
                <button
                  type="submit"
                  className="btn btn-primary"
                  align="center"
                  style={{ margin: "10px" }}
                >
                  Realizar transferencia
                </button>
              </div>
              <div className="block justify-content-center">
                <button
                  style={{ margin: "10px" }}
                  type="button"
                  className="btn btn-primary"
                  onClick={() => handleLogout()}
                >
                  Sair
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
