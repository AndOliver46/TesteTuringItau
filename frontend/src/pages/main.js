import React from "react";
import "bootstrap/dist/css/bootstrap.css";

import api from "../services/Api";
import { logout } from "../services/auth";

export default function Main({ history }) {
  const handleLogout = async () => {
    await api.delete("/logout", {}).then((response) => {
      logout(response.data.token);
      history.push("/login");
    });
  };

  return (
    <div className="container">
      <h1 align="center" className="text-primary" style={{ margin: "20px" }}>
        Bem vindo
      </h1>

      <div className="card">
        <div className="card-body">
          <div className="justify-content-center" align="center">
            <button
              style={{ margin: "10px" }}
              type="button"
              className="btn btn-primary"
              //onClick={() => handleInformacoes()}
            >
              Minhas informações
            </button>
          </div>
          <div className="block justify-content-center" align="center">
            <button
              style={{ margin: "10px" }}
              type="button"
              className="btn btn-primary"
              //bonClick={() => handleTransferencia()}
            >
              Realizar transferencia
            </button>
          </div>
          <div className="block justify-content-center" align="center">
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
      </div>
    </div>
  );
}
