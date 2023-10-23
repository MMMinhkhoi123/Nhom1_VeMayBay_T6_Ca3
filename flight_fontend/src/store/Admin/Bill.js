import axios from "axios";
const customer = {
  namespaced: true,
  state: {
    bills: [],
    bills_all: [],
    page: {
      page: 1,
      limit: 2,
      key: "",
    },
  },
  mutations: {
    setbills(state, value) {
      state.bills = value;
    },
    setbills_all(state, value) {
      state.bills_all = value;
    },
  },
  getters: {
    bills_status: (state) => (value) => {
      return state.bills_all.filter((e) => e.status == value);
    },
  },
  actions: {
    async get_bill({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/prbill", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((reponse) => {
          console.log(reponse);
          commit("setbills", reponse.data);
        })
        .catch((err) => console.log(err));
    },
    async get_bill_all({ commit, state }) {
      console.log(state);
      await axios
        .get("http://localhost:8081/admin/prbill-all")
        .then((reponse) => {
          console.log(reponse);
          commit("setbills_all", reponse.data);
        })
        .catch((err) => console.log(err));
    },
  },
};
export default customer;
