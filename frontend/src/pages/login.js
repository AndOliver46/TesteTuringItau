import React, { useState } from "react";

import api from "../services/Api";
import { login, logout, getToken } from "../services/auth";

export default function Login({ history }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    logout();
    e.preventDefault();
    await api
      .post("/login", { username, password })
      .then((response) => {
        login(response.data.token);
        history.push("/main");
      })
      .catch((error) => {
        if (error.response) {
          console.log(error.response.data.message);
          const p = document.querySelector("#erroSenha");
          p.textContent = error.response.data.message;
        }
      });
  };

  const handleCriar = async () => {
    logout();
    history.push("/criarConta");
  };

  return (
    <div
      style={{
        height: "100vh",
      }}
    >
      <div
        className="card"
        style={{
          margin: "40px auto 0px auto",
          boxSizing: "border-box",
          width: "390px",
          background: "#fff",
          borderRadius: "10px",
          overflow: "hidden",
          padding: "70px 55px 33px",
        }}
      >
        <p
          style={{
            fontSize: "30px",
            color: "#333",
            lineHeight: "1.2",
            textAlign: "center",
          }}
        >
          <strong>Faça seu login</strong>
        </p>
        <div className="card-body">
          <form onSubmit={handleLogin}>
            <div className="form-group">
              <div
                style={{
                  width: "100%",
                  height: "50px",
                }}
              >
                <label htmlFor="username">Numero da conta</label>
                <input
                  id="username"
                  className="form-control"
                  required
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </div>
            </div>
            <div
              className="form-group"
              style={{
                marginTop: "30px",
              }}
            >
              <div>
                <label htmlFor="password">Senha</label>
                <input
                  id="password"
                  type="password"
                  className="form-control"
                  required
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
            </div>

            <div className="block justify-content-center" align="center">
              <p id="erroSenha" style={{ color: "red" }}></p>
            </div>

            <div>
              <div className="block justify-content-center">
                <button
                  type="submit"
                  className="btn btn-primary"
                  align="center"
                  style={{
                    padding: "0 20px",
                    width: "100%",
                    height: "50px",
                    margin: "20px 0px",
                    outline: "none!important",
                    border: "none",
                    borderRadius: "15px",
                  }}
                >
                  LOGIN
                </button>
              </div>
              <div className="block justify-content-center" align="center">
                <button
                  style={{ margin: "10px" }}
                  type="button"
                  className="btn"
                  onClick={() => handleCriar()}
                >
                  Ainda não sou cliente >
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
