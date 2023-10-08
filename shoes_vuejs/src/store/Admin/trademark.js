import axios from "axios";
const trademark = {
  namespaced: true,
  state: {
    //  array
    trademark: [],
    mess_trademark: null,
    status_noti: false,
    page: {
      page: 1,
      limit: 8,
      key: "",
    },
  },
  mutations: {
    set_trademarks(state, value) {
      state.trademark = value;
    },
  },
  actions: {
    //get
    async set_trademarks({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/trademark", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_trademarks", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    async set_all_trademarks(context) {
      await axios
        .get("http://localhost:8081/admin/trademark/all")
        .then((e) => {
          context.commit("set_trademarks", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //post
    async post_trademark({ commit, state }, data) {
      await axios.post("http://localhost:8081/admin/trademark", data);
      await axios
        .get("http://localhost:8081/admin/trademark", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_trademarks", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //delete
    async delete_trademarks({ commit, state }, value) {
      await axios
        .delete("http://localhost:8081/admin/trademark/" + value)
        .catch((err) => {
          if (err.response.status == 500) {
            state.mess_trademark = "Delete fail, trademakr is alredy use !";
          }
        });
      await axios
        .get("http://localhost:8081/admin/trademark", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((e) => {
          commit("set_trademarks", e.data);
        });
    },
  },
};
export default trademark;
