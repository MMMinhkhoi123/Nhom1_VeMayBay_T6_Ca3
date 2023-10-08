import store from "@/store";
import { reactive } from "vue";
export default () => {
  const erro = reactive({
    hour_go: null,
    min_go: null,
    date_go: null,
    hour_move: null,
    min_move: null,
    price: null,
    id_airfield: null,
    id_airplane: null,
    id_locationgo: null,
    id_locationand: null,
  });
  const checkdate = (value) => {
    let date = new Date(value);
    let datenow = new Date();
    if (date.getTime() < datenow.getTime()) {
      return false;
    } else {
      return true;
    }
  };
  const checknull = (value) => (value == null ? false : true);
  const checkmin = (value) => (value < 0 || value > 60 ? false : true);
  const checkhour = (value) => (value < 0 || value > 24 ? false : true);
  const set_mess_erro = () => {
    !checknull(store.state.md_flight.data_check.hour_go)
      ? (erro.hour_go = "Không bỏ trống")
      : !checkhour(store.state.md_flight.data_check.hour_go)
      ? (erro.hour_go = "Không hợp lệ")
      : (erro.hour_go = null);
    !checknull(store.state.md_flight.data_check.min_go)
      ? (erro.min_go = "Không bỏ trống")
      : !checkmin(store.state.md_flight.data_check.min_go)
      ? (erro.min_go = "Không hợp lệ")
      : (erro.min_go = null);
    !checknull(store.state.md_flight.data_check.date_go)
      ? (erro.date_go = "Không bỏ trống")
      : !checkdate(store.state.md_flight.data_check.date_go)
      ? (erro.date_go = "Không hợp lệ")
      : (erro.date_go = null);

    !checknull(store.state.md_flight.data_check.min_move)
      ? (erro.min_move = "Không bỏ trống")
      : !checkmin(store.state.md_flight.data_check.min_move)
      ? (erro.min_move = "Không hợp lệ")
      : (erro.min_move = null);
    !checknull(store.state.md_flight.data_check.hour_move)
      ? (erro.hour_move = "Không bỏ trống")
      : (erro.hour_move = null);
    !checknull(store.state.md_flight.data_check.price)
      ? (erro.price = "Không bỏ trống")
      : store.state.md_flight.data_check.price < 0
      ? (erro.price = "Không hợp lệ")
      : (erro.price = null);
    !checknull(store.state.md_flight.data_check.id_airfield)
      ? (erro.id_airfield = "Không bỏ trống")
      : (erro.id_airfield = null);
    !checknull(store.state.md_flight.data_check.id_airplane)
      ? (erro.id_airplane = "Không bỏ trống")
      : (erro.id_airplane = null);
    !checknull(store.state.md_flight.data_check.id_locationgo)
      ? (erro.id_locationgo = "Không bỏ trống")
      : (erro.id_locationgo = null);
    !checknull(store.state.md_flight.data_check.id_locationand)
      ? (erro.id_locationand = "Không bỏ trống")
      : (erro.id_locationand = null);
  };
  const settimeful = () => {
    store.state.md_flight.data_check.dateful =
      store.state.md_flight.data_check.date_go;
    store.state.md_flight.data_check.hourfull =
      store.state.md_flight.data_check.hour_go;
    store.state.md_flight.data_check.minful =
      store.state.md_flight.data_check.min_go;
  };
  const datapost = reactive({
    id_airfield: null,
    id_airplane: null,
    id_location_departure: null,
    id_location_complete: null,
    price: null,
    sale: 0,
    date_departure: null,
    date_complete: null,
    timemove: null,
    id: null,
  });
  const validate = () => {
    set_mess_erro();
    if (
      checknull(erro.hour_go) ||
      checknull(erro.min_go) ||
      checknull(erro.date_go) ||
      checknull(erro.hour_move) ||
      checknull(erro.min_move) ||
      checknull(erro.id_airfield) ||
      checknull(erro.id_airplane) ||
      checknull(erro.id_locationgo) ||
      checknull(erro.id_locationand)
    ) {
      console.log("sai roi ba oi");
    } else {
      const datex = new Date(store.state.md_flight.data_check.date_go);
      datex.setHours(store.state.md_flight.data_check.hour_go);
      datex.setMinutes(store.state.md_flight.data_check.min_go);
      settimeful();
      const datey =
        datex.getTime() +
        store.state.md_flight.data_check.hour_move * 60 * 60 * 1000 +
        store.state.md_flight.data_check.min_move * 60 * 1000;
      datapost.id_airfield = store.state.md_flight.data_check.id_airfield;
      datapost.id_airplane = store.state.md_flight.data_check.id_airplane;
      datapost.id_location_departure =
        store.state.md_flight.data_check.id_locationgo;
      datapost.id_location_complete =
        store.state.md_flight.data_check.id_locationand;
      datapost.price = store.state.md_flight.data_check.price;
      datapost.date_departure = datex.getTime();
      datapost.date_complete = datey;
      datapost.timemove =
        store.state.md_flight.data_check.hour_move +
        " Giờ " +
        store.state.md_flight.data_check.min_move +
        " phút ";
      datapost.id = store.state.md_flight.data_check.id;
      if (store.state.md_flight.data_check.id == null) {
        store.dispatch("md_flight/post_flight", datapost);
        store.state.md_flight.mess_flight = null;
        setTimeout(() => {
          store.state.md_flight.mess_flight = "Create flight success !";
        }, 100);
      } else {
        store.dispatch("md_flight/put_flight", datapost);
        store.state.md_flight.mess_flight = null;
        setTimeout(() => {
          store.state.md_flight.mess_flight = "Update flight success !";
          store.state.md_flight.data_check.id = null;
        }, 100);
      }
    }
  };
  return {
    erro,
    validate,
  };
};
