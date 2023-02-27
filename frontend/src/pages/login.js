import React, { useState } from "react";

import api from "../services/Api";
import { login } from "../services/auth";

export default function Login({ history }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    await api.post("/login", { username, password }).then((response) => {
      login(response.data.token);
      history.push("/main");
    });
  };

  return (
    <div className="container-fluid center-block">
      <h1 align="center" className="text-primary" style={{ background: "#f28500" }}>
        Banco Itaoliver
      </h1>
      <div className="card">
        <div className="card-body">
          <form onSubmit={handleLogin}>
            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="username">Numero da conta</label>
                <input
                  id="idUsername"
                  className="form-control"
                  placeholder="Informe o numero de sua conta"
                  required
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </div>
            </div>
            <div className="form-group">
              <div className="col-md-6 offset-md-3">
                <label htmlFor="password">Senha</label>
                <input
                  id="password"
                  type="password"
                  className="form-control"
                  placeholder="Entre com a senha"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
            </div>
            <div>
              <div className="block justify-content-center" align="center">
                <button
                  type="submit"
                  className="btn btn-primary"
                  align="center"
                  style={{ margin: "10px" }}
                >
                  Login
                </button>
              </div>
              <div className="block justify-content-center" align="center">
                <button
                  style={{ margin: "10px" }}
                  type="button"
                  className="btn"
                  //onClick={() => handleLogout()}
                >
                  Abrir uma conta
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
