import axios from "axios";
const airfield = {
  namespaced: true,
  state: {
    airfield: [],
    mess_airfield: null,
    status_noti: false,
    page: {
      page: 1,
      limit: 10,
      key: "",
    },
  },
  getters: {
    getairfieldid: (state) => (value) => {
      if (state.airfield.list) {
        return state.airfield.list.filter((e) => e.id == value);
      }
      return state.airfield.filter((e) => e.id == value);
    },
  },
  mutations: {
    set_airfield(state, value) {
      state.airfield = value;
    },
  },
  actions: {
    //Get
    async set_airfield({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/airfield", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_airfield", e.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async set_all_airfield(context) {
      await axios
        .get("http://localhost:8081/admin/airfield/all")
        .then((e) => {
          context.commit("set_airfield", e.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //post
    async post_airfield({ commit, state }, data) {
      await axios.post("http://localhost:8081/admin/airfield", data);
      await axios
        .get("http://localhost:8081/admin/airfield", {
          params: { limit: state.page.limit, page: state.page.page, key: "" },
        })
        .then((e) => {
          commit("set_airfield", e.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //delete
    async delete_airfield({ commit, state }, value) {
      await axios
        .delete("http://localhost:8081/admin/airfield/" + value)
        .catch((err) => {
          if (err.response.status == 500) {
            state.mess_airfield = "Delete fail, airfield is alredy use !";
          }
        });
      await axios
        .get("http://localhost:8081/admin/airfield", {
          params: { limit: state.page.limit, page: state.page.page, key: "" },
        })
        .then((e) => {
          commit("set_airfield", e.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
export default airfield;
