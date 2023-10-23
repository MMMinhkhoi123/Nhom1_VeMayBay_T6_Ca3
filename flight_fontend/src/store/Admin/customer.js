import axios from "axios";
const customer = {
  namespaced: true,
  state: {
    customer: [],
    page: {
      page: 1,
      limit: 3,
      key: "",
    },
  },
  mutations: {
    setcustomer(state, value) {
      state.customer = value;
    },
  },
  getters: {
    getcustomerid: (state) => (value) => {
      if (state.customer.length != 0) {
        return state.customer.list.filter((e) => e.id == value)[0];
      }
      return [];
    },
    getcustomer_idtick: (state) => (value) => {
      const x = {
        list: [],
      };
      if (state.customer.length != 0) {
        state.customer.list.filter((e) =>
          e.idticker == value ? x.list.push(e) : null
        );
        return x;
      }
      return x;
    },
  },
  actions: {
    async get_customer({ commit, state }) {
      await axios
        .get("http://localhost:8081/admin/customer", {
          params: {
            limit: state.page.limit,
            page: state.page.page,
            key: state.page.key,
          },
        })
        .then((reponse) => {
          commit("setcustomer", reponse.data);
        })
        .catch((err) => console.log(err));
    },
  },
};
export default customer;
