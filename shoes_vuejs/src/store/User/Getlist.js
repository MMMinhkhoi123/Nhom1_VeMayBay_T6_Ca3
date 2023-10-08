import axios from "axios";
const list = {
  namespaced: true,
  state: {
    Location: [],
    Typechair: [],
    trademark: [],
    history: [],
    tick: [],
    search: {
      from: null,
      to: null,
      exits_chair: null,
      date: null,
      round_trip: null,
      select: null,
      price: null,
      group_trademark: [],
      sle: null,
      Dates_to: null,
    },
    name: {
      chair: null,
      from: null,
      to: null,
    },
    my_ticker: {
      id_tick: [],
      id_chair: [],
      id_seat: [],
    },
    my_aply: {
      chose_seat: [],
    },
    my_payment: {
      sum1: 0,
      sum2: 0,
    },
    data: {
      token: localStorage.getItem("jwt"),
    },
  },
  mutations: {
    set_location(state, value) {
      state.Location = value;
    },
    set_typechair(state, value) {
      state.Typechair = value;
    },
    set_trademarks(state, value) {
      state.trademark = value;
    },
    set_tick(state, value) {
      state.tick = value;
    },
    set_history(state, value) {
      state.history = value;
    },
  },

  getters: {
    typechairtick: (state) => (value) => {
      const obj = state.tick.filter(
        (e) => e.id == state.my_ticker.id_tick[value]
      );
      if (obj.length > 0) {
        return state.Typechair.filter((e) =>
          obj[0].typechair.includes(Number(e.id))
        );
      }
      return [];
    },
    getchosechair: () => (array, value, side) => {
      return array[0].listchair.filter(
        (e) => e.typechair == value && e.side == side
      );
    },
    trademark: (state) => (value) => {
      return state.trademark.filter((e, index) => index < value);
    },
    typechair: (state) => (value) => {
      return state.Typechair.filter((e) => e.id == value);
    },
    trademark_id: (state) => (value) => {
      return state.trademark.filter((e) => e.id == value);
    },
    location: (state) => (value) => {
      return state.Location.filter((e, index) => index < value);
    },
    location_id: (state) => (value) => {
      return state.Location.filter((e) => e.id == value);
    },
    location2: (state) => (value) => {
      const x = state.Location.filter((e) => e.contry != "VN");
      return x.filter((e, index) => index < value);
    },
    tick: (state) => (value) => {
      return state.tick.filter((e) => e.id == value);
    },
    searchdata: (state) => (array) => {
      //click select list
      if (state.search.select != null) {
        return state.tick.filter(
          (e) => e.location_complete == state.search.select
        );
      }
      if (array != undefined) {
        if (state.search.price > 200000) {
          return array.filter((e) => e.price <= state.search.price);
        }
        return array.filter((e) =>
          state.search.group_trademark.includes(Number(e.trademark))
        );
      }
      const datacheck = (value, array) => {
        for (var x in array) {
          if (array[x] == value) {
            return true;
          }
        }
        return false;
      };
      const check_date = (date1, date2) => {
        const dates = new Date(date1);
        const dates2 = new Date(date2);
        console.log(dates);
        console.log(dates2);
        if (
          dates.getDate() == dates2.getDate() &&
          dates.getMonth() == dates2.getMonth() &&
          dates.getFullYear() == dates2.getFullYear()
        ) {
          return true;
        } else {
          return false;
        }
      };
      //neu khong khu hoi
      console.log(state.search.round_trip);
      if (state.search.round_trip == "false") {
        var date = new Date(state.search.date);
        return state.tick.filter(
          (e) =>
            e.location_departure == state.search.from &&
            e.location_complete == state.search.to &&
            check_date(e.time_departure, date.getTime()) == true &&
            datacheck(state.search.exits_chair, e.typechair) == true
        );

        // neu co khu hoi
      } else {
        var dates = new Date(state.search.date);
        var dates2 = new Date(state.search.Dates_to);
        console.log(state.search.Dates_to);
        return state.tick.filter(
          (e) =>
            (e.location_departure == state.search.from &&
              e.location_complete == state.search.to &&
              check_date(e.time_departure, dates.getTime()) == true &&
              datacheck(state.search.exits_chair, e.typechair) == true) ||
            (e.location_departure == state.search.to &&
              e.location_complete == state.search.from &&
              check_date(e.time_departure, dates2.getTime()) == true &&
              datacheck(state.search.exits_chair, e.typechair) == true)
        );
      }
    },
  },
  actions: {
    // get history
    async gethistory({ commit }) {
      const token = localStorage.getItem("jwt");
      await axios
        .get("http://localhost:8081/user/history", {
          params: {
            token: token,
          },
        })
        .then((reponse) => {
          commit("set_history", reponse.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // get
    async get_all_location(context) {
      await axios
        .get("http://localhost:8081/admin/location/all")
        .then((reponse) => {
          context.commit("set_location", reponse.data);
        });
    },
    async get_all_trademarks(context) {
      await axios
        .get("http://localhost:8081/admin/trademark/all")
        .then((e) => {
          context.commit("set_trademarks", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
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
    async get_tickx(context) {
      await axios
        .get("http://localhost:8081/admin/tick/all")
        .then((e) => {
          context.commit("set_tick", e.data);
        })
        .catch((r) => {
          console.log(r);
        });
    },
  },
};
export default list;
