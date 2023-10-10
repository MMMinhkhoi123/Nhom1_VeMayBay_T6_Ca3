import axios from "axios";
const tick = {
  namespaced: true,
  state: {
    tick: [],
    page: {
      page: 1,
      limit: 4,
    },
    filter: null,
  },
  mutations: {
    set_tick(state, value) {
      state.tick = value;
    },
  },
  getters: {
    filter_tick: (state) => (value) => {
      const data = {
        list: [],
      };
      if (state.tick.list.length > 0) {
        console.log(value);
        const date_start = new Date(value.datestart);
        const date_end = new Date(value.dateend);
        state.tick.list.filter((e) =>
          e.trademark == value.idtrademark &&
          e.location_complete == value.idlocation &&
          e.time_departure >= date_start.getTime() &&
          e.time_departure <= date_end.getTime()
            ? data.list.push(e)
            : null
        );
        return data;
      }
      return [];
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
