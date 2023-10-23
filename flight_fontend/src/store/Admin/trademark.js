import axios from "axios";
const trademark = {
  namespaced: true,
  state: {
    //  array
    trademark: [],
    trademark_all: [],
    mess_trademark: null,
    status_trademark: false,
    status_noti: false,
    page: {
      page: 1,
      limit: 8,
      key: "",
    },
    data: {
      id: 0,
      name: null,
      img: null,
      status: false,
    },
    erro: {
      name: null,
      img: null,
    },
    file_img: null,
  },
  mutations: {
    set_trademarks(state, value) {
      state.trademark = value;
    },
    set_trademarks_all(state, value) {
      state.trademark_all = value;
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
          console.log(e);
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
          context.commit("set_trademarks_all", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
    //post
    async post_trademark({ commit, state }) {
      const form = new FormData();
      form.append("file_trademark", state.file_img);
      for (var key in state.data) {
        form.append(key, state.data[key]);
      }
      await axios
        .postForm("http://localhost:8081/admin/trademark", form)
        .then((e) => {
          (state.mess_trademark = e.data.mess),
            (state.status_trademark = e.data.status);
        })
        .catch((e) => {
          console.log(e);
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
        })
        .catch((r) => {
          console.log(r);
        });
    },

    async put_trademark({ commit, state }) {
      alert("krorkfo");
      const form = new FormData();
      for (var key in state.data) {
        form.append(key, state.data[key]);
      }
      await axios
        .postForm("http://localhost:8081/admin/trademark", form)
        .then((e) => {
          (state.mess_trademark = e.data.mess),
            (state.status_trademark = e.data.status);
        })
        .catch((e) => {
          console.log(e);
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
        })
        .catch((r) => {
          console.log(r);
        });
    },

    //delete
    async delete_trademarks({ commit, state }, value) {
      await axios
        .delete("http://localhost:8081/admin/trademark/" + value)
        .then((e) => {
          (state.mess_trademark = e.data.mess),
            (state.status_trademark = e.data.status);
        })
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
