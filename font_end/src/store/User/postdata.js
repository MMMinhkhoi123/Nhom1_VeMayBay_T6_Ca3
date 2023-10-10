import axios from "axios";
const post = {
  namespaced: true,
  state: {
    data_post: {
      nationality: null,
      passport: null,
      email: null,
      fullname: null,
      age: null,
      statuspayment: null,
      tel: null,
    },
    chosego: {
      seat: null,
      idtick: null,
    },
    choseback: {
      seat: null,
      idtick: null,
    },
    data: [],
    data_einvoice: null,
    sumprice: null,
    mess_regiter: null,
    mess_login: null,
    mess_changepass: null,
    authen: null,
    authentication: null,
    data_update: {
      token: localStorage.getItem("jwt"),
      price: sessionStorage.getItem("price"),
      idbill: sessionStorage.getItem("idbill"),
      url: sessionStorage.getItem("url"),
    },
    err: {
      email: null,
    },
    emailvalid: null,
  },
  mutations: {
    set_einvoice(state, value) {
      state.data_post = value;
    },
    set_price(state, value) {
      state.data_update.idbill = value.idbill;
      state.data_update.price = value.price;
      sessionStorage.clear();
      sessionStorage.setItem("url", value.url);
      sessionStorage.setItem("idbill", value.idbill);
      sessionStorage.setItem("price", value.price);
    },
    set_authentication(state, value) {
      state.authentication = value;
    },
    set_messchaneg_pass(state, value) {
      state.mess_changepass = value;
    },
  },
  getters: {},
  actions: {
    async post_einvoice({ commit, state }) {
      console.log(state.data);
      await axios
        .post("http://localhost:8081/user/info", state.data)
        .then((reponse) => {
          commit("set_price", reponse.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async register({ state }, data) {
      await axios
        .post("http://localhost:8081/api/register", data)
        .then((reponse) => {
          console.log(reponse);
        })
        .catch((err) => {
          state.mess_regiter = err.response.data.mess;
        });
    },
    async login({ state }, data) {
      await axios
        .post("http://localhost:8081/api/login", data)
        .then((reponse) => {
          localStorage.clear();
          localStorage.setItem("jwt", reponse.data.accessToken);
          state.authen = reponse.data;
        })
        .catch((err) => {
          state.mess_login = err.response.data.mess;
        });
    },
    async chang_pass({ commit }, data) {
      await axios
        .put("http://localhost:8081/api/pass", data)
        .then((reponse) => {
          console.log(reponse);
          commit("set_messchaneg_pass", reponse.data.mess);
        })
        .catch((err) => {
          commit("set_messchaneg_pass", err.response.data.mess);
        });
    },
    async authen({ commit }) {
      const token = localStorage.getItem("jwt");
      await axios
        .get("http://localhost:8081/api/token", {
          params: {
            tokens: token,
          },
        })
        .then((reponse) => {
          commit("set_authentication", reponse.data);
        })
        .catch((err) => {
          console.log(err.response.status);
        });
    },
    async callemail({ state }, data) {
      await axios
        .get("http://localhost:8081/api/pass", {
          params: {
            email: data.email,
          },
        })
        .then((reponse) => {
          state.emailvalid = reponse.data.mess;
        })
        .catch((err) => {
          state.err.email = err.response.data.mess;
        });
    },
  },
};
export default post;
