import axios from "axios";
const tick = {
  namespaced: true,
  state: {
    tick: [],
    page: {
      page: 1,
      limit: 5,
    },
  },
  mutations: {
    set_tick(state, value) {
      state.tick = value;
    },
  },
  actions: {
    async get_tick({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/tick", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
          },
        })
        .then((reponse) => {
          console.log(reponse);
          commit("set_tick", reponse.data);
        })
        .catch((err) => console.log(err));
    },
  },
};
export default tick;
