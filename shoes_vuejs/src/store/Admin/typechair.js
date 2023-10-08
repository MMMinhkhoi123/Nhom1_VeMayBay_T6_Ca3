import axios from "axios";
const typechair = {
  namespaced: true,
  state: {
    //  array
    typechair: [],
    mess_chaier: null,
    status_noti: false,
    admin: {
      update_airplane: null,
    },
  },
  getters: {
    getonetypechair: (state) => (value) => {
      return state.typechair.filter((e) => e.id == Number(value));
    },
  },
  mutations: {
    set_typechair(state, value) {
      state.typechair = value;
    },
  },
  actions: {
    //post
    async post_typechair(context, data) {
      await axios.post("http://localhost:8081/admin/type-chair", data);
      await axios
        .get("http://localhost:8081/admin/type-chair")
        .then((e) => {
          context.commit("set_typechair", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //get
    async get_typechair(context) {
      await axios
        .get("http://localhost:8081/admin/type-chair")
        .then((e) => {
          context.commit("set_typechair", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //delete
    async delete_typechair({ state, commit }, value) {
      await axios
        .delete("http://localhost:8081/admin/type-chair/" + value)
        .catch((r) => {
          if (r.response.status == 500) {
            state.mess_chaier = "Delete fail , type chair is already use !";
          }
        });
      await axios.get("http://localhost:8081/admin/type-chair").then((e) => {
        commit("set_typechair", e.data);
      });
    },
  },
};
export default typechair;
