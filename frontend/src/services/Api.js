import axios from "axios";
import { getToken, logout } from "./auth";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  responseType: "json",
});

// api.interceptors.request.use(async (config) => {
//   const token = getToken();
//   const parseJwt = (token) => {
//     const decode = JSON.parse(atob(token.split(".")[1]));
//     console.log(decode);
//     if (decode.exp * 1000 < new Date().getTime()) {
//       logout();
//       console.log("Time Expired");
//     }
//   };
//   if (token) {
//     config.headers.Authorization = `Bearer ${token}`;
//   }
//   return config;
// });

export default api;
