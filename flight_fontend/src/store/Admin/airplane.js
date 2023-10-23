import axios from "axios";
const airplane = {
  namespaced: true,
  state: {
    airplane: [],
    update_airplane: {
      array_type: [],
      statuschonse: null,
      id_trademark: null,
      id: null,
      name: null,
    },
    page: {
      page: 1,
      limit: 3,
      key: "",
    },
    mess_airplane: null,
  },
  mutations: {
    set_airplane(state, value) {
      state.airplane = value;
    },
  },
  getters: {
    getairplaneid: (state) => (value) => {
      if (state.airplane.list) {
        return state.airplane.list.filter((e) => e.id == Number(value));
      }
      return state.airplane.filter((e) => e.id == Number(value));
    },
  },
  actions: {
    async get_airplane({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/airplane", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_airplane", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    async get_all_airplane(context) {
      await axios
        .get("http://localhost:8081/admin/airplane/all")
        .then((e) => {
          context.commit("set_airplane", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //post
    async post_airplane({ commit, state }, data) {
      await axios.post("http://localhost:8081/admin/airplane", data);
      await axios
        .get("http://localhost:8081/admin/airplane", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_airplane", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //update
    async update_airplane({ commit, state }, data) {
      await axios
        .put("http://localhost:8081/admin/airplane", data)
        .catch((e) => {
          console.log(e);
        });
      await axios
        .get("http://localhost:8081/admin/airplane", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_airplane", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //delete
    async delete_airplane({ commit, state }, value) {
      await axios
        .delete("http://localhost:8081/admin/airplane/" + value)
        .catch((r) => {
          if (r.response.status == 500) {
            state.mess_airplane = "Delete fail , airplane is already use !";
          }
        });
      await axios
        .get("http://localhost:8081/admin/airplane", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_airplane", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
  },
};
export default airplane;
