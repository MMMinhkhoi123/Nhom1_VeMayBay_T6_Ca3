import axios from "axios";
const list = {
  namespaced: true,
  state: {},
  mutations: {},
  getters: {},
  actions: {
    async register(data) {
      await axios
        .post("http://localhost:8081/api/register", data)
        .then((reponse) => {
          console.log(reponse);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
export default list;
