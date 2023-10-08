import { createStore } from "vuex";
import location from "./Admin/location";
import airfield from "./Admin/airfield";
import trademark from "./Admin/trademark";
import typechair from "./Admin/typechair";
import airplane from "./Admin/airplane";
import flight from "./Admin/fight";
import list from "../store/User/Getlist";
import post from "../store/User/postdata";
import tick from "../store/Admin/ticker";
export default createStore({
  state: {
    airfield: [],
    airplane: [],
    location: [],
    locations: [],
    type_chair: [],
    trademark: [],
    trademarks: [],
    chonse_chair: [],
    menu_filter: {
      sort: {
        select: 0,
      },
      trademark: false,
      price: 200000,
    },
    tool_admin: {
      menu: 0,
    },
    hasNotifi: false,
    info: false,
    search: {
      list_select: null,
      from: null,
      id_from: null,
      to: null,
      id_to: null,
      chair: null,
      id_chair: null,
      Dates_from: null,
      Dates_to: null,
      status: null,
      round_trip: false,
      group_trademark: [],
      filters: false,
    },

    mess_erro: null,
    my_tick_none: {
      round_trip: null,
      id_tick: [],
      id_chose: 0,
      sum_price: 0,
      sum_price_back: 0,
      price_chair_go: 0,
      price_chair_back: 0,
      idchair1: null,
      idchair2: null,
    },

    dataf1: {
      lastname: sessionStorage.getItem("lastname"),
      firtname: sessionStorage.getItem("firtname"),
      email: sessionStorage.getItem("email"),
      phone: sessionStorage.getItem("phone"),
    },

    err: {
      lastname: null,
      firtname: null,
      email: null,
      phone: null,
    },
    dataf2: {
      lastname: sessionStorage.getItem("lastname2"),
      firtname: sessionStorage.getItem("firtname2"),
      briday: sessionStorage.getItem("briday"),
      nationality: sessionStorage.getItem("nationality"),
      passport: sessionStorage.getItem("passport"),
      timelimit: sessionStorage.getItem("timelimit"),
      contry: sessionStorage.getItem("contry"),
    },

    err2: {
      lastname: null,
      firtname: null,
      briday: null,
      nationality: null,
      passport: null,
      timelimit: null,
      contry: null,
    },

    Info_oder: {
      chonse_for_go: null,
      chonse_for_back: null,
    },

    status_register: null,
    chosechair: false,
    shownext: false,
    show_luggage: false,
    shownext2: false,
    chonse_luggage: false,
    chonse_seat: false,
    chose_seat_success: false,
    location_admin: {
      code: null,
      name: null,
      img: null,
    },
    admin: {
      update_airplane: null,
    },
  },

  getters: {
    get_chair_tick: (state) => (value) => {
      const obj = state.tick.filter(
        (e) => e.id == state.my_tick_none.id_tick[value]
      );
      if (obj.length > 0) {
        return state.type_chair.filter((e) =>
          obj[0].id_chair.includes(Number(e.id))
        );
      }
      return [];
    },

    get_trademark: (state) => (value) => {
      return state.trademarks.filter((e) => e.id == value);
    },

    check_chonse_seat: (state) => (id_chair) => {
      if (id_chair == state.my_tick_none.idchair1) {
        return true;
      }
      return false;
    },
  },
  mutations: {},
  actions: {},
  modules: {
    md_location: location,
    md_airfield: airfield,
    md_trademark: trademark,
    md_typechair: typechair,
    md_airplane: airplane,
    md_flight: flight,
    list: list,
    post: post,
    md_tick: tick,
  },
});
