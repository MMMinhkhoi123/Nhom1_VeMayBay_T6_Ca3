import store from "@/store";
import { reactive } from "vue";
export default () => {
  const erro = reactive({
    timego: null,
    timemove: null,
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
  const set_mess_erro = () => {
    const datemove = new Date(
      store.state.md_flight.data_check.timemove +
        " " +
        store.state.md_flight.data_check.date_move
    );
    const datego = new Date(
      store.state.md_flight.data_check.timego +
        " " +
        store.state.md_flight.data_check.date_go
    );

    !checknull(store.state.md_flight.data_check.timego) ||
    !checknull(store.state.md_flight.data_check.date_go)
      ? (erro.timego = "Không bỏ trống")
      : !checkdate(datego)
      ? (erro.timego = "Thời gian đi không hợp lệ")
      : (erro.timego = null);

    !checknull(store.state.md_flight.data_check.timemove) ||
    !checknull(store.state.md_flight.data_check.date_move)
      ? (erro.timemove = "Không bỏ trống")
      : datemove.getTime() < datego.getTime()
      ? (erro.timemove = "Thời gian không hợp lệ")
      : (erro.timemove = null);

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
      checknull(erro.timego) ||
      checknull(erro.timemove) ||
      checknull(erro.id_airfield) ||
      checknull(erro.id_airplane) ||
      checknull(erro.id_locationgo) ||
      checknull(erro.id_locationand) ||
      checknull(erro.price)
    ) {
      console.log("sai roi ba oi");
    } else {
      // const datex = new Date(store.state.md_flight.data_check.date_go);
      // datex.setHours(store.state.md_flight.data_check.timego.split(":", 2)[0]);
      // datex.setMinutes(
      //   store.state.md_flight.data_check.timego.split(":", 2)[1]
      // );
      settimeful();

      // const datey =
      //   datex.getTime() +
      //   store.state.md_flight.data_check.timemove.split(":", 2)[0] *
      //     60 *
      //     60 *
      //     1000 +
      //   store.state.md_flight.data_check.timemove.split(":", 2)[1] * 60 * 1000;

      const datemove = new Date(
        store.state.md_flight.data_check.timemove +
          " " +
          store.state.md_flight.data_check.date_move
      );
      const datego = new Date(
        store.state.md_flight.data_check.timego +
          " " +
          store.state.md_flight.data_check.date_go
      );
      datapost.id_airfield = store.state.md_flight.data_check.id_airfield;
      datapost.id_airplane = store.state.md_flight.data_check.id_airplane;
      datapost.id_location_departure =
        store.state.md_flight.data_check.id_locationgo;
      datapost.id_location_complete =
        store.state.md_flight.data_check.id_locationand;
      datapost.price = store.state.md_flight.data_check.price;
      datapost.date_departure = datego.getTime();
      datapost.date_complete = datemove.getTime();
      datapost.timemove =
        store.state.md_flight.data_check.timego.split(":", 2)[0] +
        " Giờ " +
        store.state.md_flight.data_check.timego.split(":", 2)[1] +
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
