import axios from "axios";
const location = {
  namespaced: true,
  state: {
    Location: [],
    mess_location: null,
    status_noti: false,
    page: {
      page: 1,
      limit: 12,
      key: "",
    },
  },
  mutations: {
    set_location(state, value) {
      state.Location = value;
    },
  },
  getters: {
    getlocationid: (state) => (value) => {
      if (state.Location.list) {
        return state.Location.list.filter((e) => e.id == value);
      }
      return state.Location.filter((e) => e.id == value);
    },
  },
  actions: {
    // get
    async get_location({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/location", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((reponse) => {
          console.log(reponse);
          commit("set_location", reponse.data);
        });
    },
    // get
    async get_all_location(context) {
      await axios
        .get("http://localhost:8081/admin/location/all")
        .then((reponse) => {
          console.log(reponse);
          context.commit("set_location", reponse.data);
        });
    },
    // post
    async post_location({ commit, state }, data) {
      await axios.post("http://localhost:8081/admin/location", data);
      await axios
        .get("http://localhost:8081/admin/location", {
          params: { limit: state.page.limit, page: state.page.page, key: "" },
        })
        .then((reponse) => {
          commit("set_location", reponse.data);
        });
    },
    //delete
    async delete_locations({ commit, state }, value) {
      await axios
        .delete("http://localhost:8081/admin/location/" + value)
        .then((e) => {
          commit("set_locations", e.data);
        })
        .catch((err) => {
          if (err.response.status == 500) {
            state.mess_location = "Delete fail, location is alredy use !";
          }
        });
      await axios
        .get("http://localhost:8081/admin/location", {
          params: { limit: state.page.limit, page: state.page.page, key: "" },
        })
        .then((reponse) => {
          commit("set_location", reponse.data);
        });
    },
  },
};
export default location;
