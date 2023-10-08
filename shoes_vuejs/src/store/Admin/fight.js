import axios from "axios";
const airplane = {
  namespaced: true,
  state: {
    flight: [],
    flightall: [],
    page: {
      page: 1,
      limit: 2,
    },
    mess_flight: null,
    data_check: {
      id_airplane: null,
      id_locationgo: null,
      id_locationand: null,
      id_airfield: null,
      hour_go: null,
      min_go: null,
      date_go: null,
      hour_move: null,
      min_move: null,
      price: null,
      hourfull: null,
      minful: null,
      dateful: null,
      id: null,
    },
  },
  mutations: {
    setflight(state, value) {
      state.flight = value;
    },
    setflightall(state, value) {
      state.flightall = value;
    },
  },
  getters: {
    getfligthid: (state) => (value) => {
      if (state.flightall.filter((e) => e.id == value)[0]) {
        return state.flightall.filter((e) => e.id == value)[0];
      }
      return null;
    },
  },
  actions: {
    //post
    async post_flight({ commit, state }, data) {
      await axios.post("http://localhost:8081/admin/flight", data);
      await axios
        .get("http://localhost:8081/admin/flight", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
          },
        })
        .then((reponse) => {
          commit("setflight", reponse.data);
        })
        .catch((err) => console.log(err));
    },
    //delete
    async delete_flight({ commit, state }, data) {
      await axios
        .delete("http://localhost:8081/admin/flight/" + data)
        .catch((err) => {
          if (err.response.status == 500) {
            state.mess_flight = "Delete fail , flight is already use !";
          }
        });
      await axios
        .get("http://localhost:8081/admin/flight", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
          },
        })
        .then((reponse) => {
          commit("setflight", reponse.data);
        })
        .catch((err) => console.log(err));
    },
    //put
    async put_flight({ commit, state }, data) {
      await axios.put("http://localhost:8081/admin/flight", data);
      await axios
        .get("http://localhost:8081/admin/flight", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
          },
        })
        .then((reponse) => {
          commit("setflight", reponse.data);
        })
        .catch((err) => console.log(err));
    },
    //Get
    async get_flight({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/flight", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
          },
        })
        .then((reponse) => {
          commit("setflight", reponse.data);
        })
        .catch((err) => console.log(err));
    },
    async get_all_flight({ commit }) {
      await axios
        .get("http://localhost:8081/admin/flight/all")
        .then((reponse) => {
          commit("setflightall", reponse.data);
        })
        .catch((err) => console.log(err));
    },
  },
};
export default airplane;
