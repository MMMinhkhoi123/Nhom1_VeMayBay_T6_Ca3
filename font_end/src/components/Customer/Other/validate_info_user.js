import router from "@/router";
import store from "@/store";
import { ref } from "vue";

// import router from "@/router";
export default () => {
  const regex_phone = /^[0-9\-+]{9,15}$/;
  const regex_email =
    /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  const regex_passport = /^[A-PR-WY][1-9]\d\s?\d{4}[1-9]$/;

  const check_firt = () => {
    if (store.state.dataf1.firtname == null) {
      store.state.err.firtname = "Tên không được bỏ trống";
    } else {
      store.state.err.firtname = null;
    }
  };
  const check_lastname = () => {
    if (store.state.dataf1.lastname == null) {
      store.state.err.lastname = "Tên không được bỏ trống";
    } else {
      store.state.err.lastname = null;
    }
  };
  const check_phone = () => {
    console.log(store.state.dataf1.phone);
    if (store.state.dataf1.phone == null) {
      store.state.err.phone = "Số điện thoại không được bỏ trống";
    } else if (regex_phone.test(store.state.dataf1.phone) == false) {
      store.state.err.phone = "Số điện thoại Không hợp lệ";
    } else {
      store.state.err.phone = null;
    }
  };
  const check_email = () => {
    if (store.state.dataf1.email == null) {
      store.state.err.email = "email không được bỏ trống";
    } else if (regex_email.test(store.state.dataf1.email) == false) {
      store.state.err.email = "email Không hợp lệ";
    } else {
      store.state.err.email = null;
    }
  };
  // check 2
  const check_firt2 = () => {
    if (
      store.state.dataf2.firtname == null ||
      store.state.dataf2.firtname == ""
    ) {
      store.state.err2.firtname = "Tên không được bỏ trống";
    } else {
      store.state.err2.firtname = null;
    }
  };
  const check_last2 = () => {
    if (
      store.state.dataf2.lastname == null ||
      store.state.dataf2.lastname == ""
    ) {
      store.state.err2.lastname = "Tên không được bỏ trống";
    } else {
      store.state.err2.lastname = null;
    }
  };
  const check_birtday = () => {
    if (store.state.dataf2.briday == null || store.state.dataf2.briday == "") {
      store.state.err2.briday = "Ngày sinh không được bỏ trống";
    } else {
      const dates = new Date(store.state.dataf2.briday);
      const dates2 = new Date();
      if (dates.getTime() > dates2.getTime()) {
        store.state.err2.briday = "Ngày sinh Không hợp lệ";
      }
      if (dates2.getFullYear() - dates.getFullYear() < 18) {
        store.state.err2.briday = "Không đủ 18 tuổi";
      } else {
        store.state.err2.briday = null;
      }
    }
  };
  const check_nationlity = () => {
    if (
      store.state.dataf2.nationality == null ||
      store.state.dataf2.nationality == ""
    ) {
      store.state.err2.nationality = "Quốc tịch không được bỏ trống";
    } else {
      store.state.err2.nationality = null;
    }
  };
  const check_passport = () => {
    if (
      store.state.dataf2.passport == null ||
      store.state.dataf2.passport == ""
    ) {
      store.state.err2.passport = "Số hộ chiếu không được bỏ trống";
    } else if (regex_passport.test(store.state.dataf2.passport) == false) {
      store.state.err2.passport = "Số hộ chiếu Không hợp lệ";
    } else {
      store.state.err2.passport = null;
    }
  };
  const check_timelimit = () => {
    if (
      store.state.dataf2.timelimit == null ||
      store.state.dataf2.timelimit == ""
    ) {
      store.state.err2.timelimit = "thời gian không được bỏ trống";
    } else {
      const dates = new Date(store.state.dataf2.timelimit);
      const dates2 = new Date();
      if (dates.getTime() < dates2.getTime()) {
        store.state.err2.timelimit = "đã hết hạn sử dụng";
      } else {
        store.state.err2.timelimit = null;
      }
    }
  };
  const check_contry = () => {
    if (store.state.dataf2.contry == null || store.state.dataf2.contry == "") {
      store.state.err2.contry = "quốc gia cấp không được bỏ trống";
    } else {
      store.state.err2.contry = null;
    }
  };
  const checksum_auth = () => {
    if (
      store.state.err2.lastname == null &&
      store.state.err2.firtname == null &&
      store.state.err2.briday == null &&
      store.state.err2.nationality == null &&
      store.state.err2.passport == null &&
      store.state.err2.timelimit == null &&
      store.state.err2.contry == null
    ) {
      return true;
    }
    return false;
  };
  const next = () => {
    check_firt();
    check_lastname();
    check_email();
    check_phone();
    check_firt2();
    check_last2();
    check_birtday();
    check_nationlity();
    check_passport();
    check_timelimit();
    check_contry();
    if (store.state.post.authentication != null) {
      if (checksum_auth() == true) {
        store.state.info = true;
        store.state.shownext2 = true;
      }
    } else if (
      store.state.err.phone == null &&
      store.state.err.email == null &&
      store.state.err.lastname == null &&
      store.state.err.firtname == null &&
      store.state.err2.lastname == null &&
      store.state.err2.firtname == null &&
      store.state.err2.briday == null &&
      store.state.err2.nationality == null &&
      store.state.err2.passport == null &&
      store.state.err2.timelimit == null &&
      store.state.err2.contry == null
    ) {
      store.state.info = true;
      store.state.shownext2 = true;
    }
  };

  const data = ref(false);
  const checkinfo1 = () => {
    check_firt();
    check_lastname();
    check_phone();
    check_email();
    if (
      store.state.err.phone == null &&
      store.state.err.email == null &&
      store.state.err.lastname == null &&
      store.state.err.firtname == null
    ) {
      sessionStorage.setItem("email", store.state.dataf1.email);
      sessionStorage.setItem("phone", store.state.dataf1.phone);
      sessionStorage.setItem("firtname", store.state.dataf1.firtname);
      sessionStorage.setItem("lastname", store.state.dataf1.lastname);
      data.value = !data.value;
    }
  };

  const data2 = ref(false);
  const checkinfo2 = () => {
    check_firt2();
    check_last2();
    check_birtday();
    check_nationlity();
    check_passport();
    check_timelimit();
    check_contry();
    if (
      store.state.err2.lastname == null &&
      store.state.err2.firtname == null &&
      store.state.err2.briday == null &&
      store.state.err2.nationality == null &&
      store.state.err2.passport == null &&
      store.state.err2.timelimit == null &&
      store.state.err2.contry == null
    ) {
      sessionStorage.setItem("lastname2", store.state.dataf2.lastname);
      sessionStorage.setItem("firtname2", store.state.dataf2.firtname);
      sessionStorage.setItem("briday", store.state.dataf2.briday);
      sessionStorage.setItem("nationality", store.state.dataf2.nationality);
      sessionStorage.setItem("passport", store.state.dataf2.passport);
      sessionStorage.setItem("timelimit", store.state.dataf2.timelimit);
      sessionStorage.setItem("contry", store.state.dataf2.contry);
      data2.value = !data2.value;
    }
  };

  const aplyy = () => {
    store.state.post.data = [];
    if (store.state.post.authentication) {
      store.state.post.data_post.email = store.state.post.authentication.email;
      store.state.post.data_post.tel = store.state.post.authentication.phone;
      store.state.post.data_post.fullname =
        store.state.post.authentication.fullname;
    } else {
      store.state.post.data_post.email = store.state.dataf1.email;
      store.state.post.data_post.tel = store.state.dataf1.phone;
      store.state.post.data_post.fullname =
        store.state.dataf1.lastname + store.state.dataf1.firtname;
    }
    store.state.post.data_post.passport = store.state.dataf2.passport;
    store.state.post.data_post.nationality = store.state.dataf2.nationality;
    store.state.post.data_post.age = store.state.dataf2.briday;

    if (store.state.list.my_ticker.id_tick.length > 1) {
      store.state.post.chosego.seat = store.state.list.my_ticker.id_seat[0];
      store.state.post.chosego.idtick = store.state.list.my_ticker.id_tick[0];
      store.state.post.choseback.seat = store.state.list.my_ticker.id_seat[1];
      store.state.post.choseback.idtick = store.state.list.my_ticker.id_tick[1];
      var go = Object.assign(
        store.state.post.chosego,
        store.state.post.data_post
      );
      var back = Object.assign(
        store.state.post.choseback,
        store.state.post.data_post
      );
      store.state.post.data.push(go);
      store.state.post.data.push(back);
    } else {
      store.state.post.chosego.seat = store.state.list.my_ticker.id_seat[0];
      store.state.post.chosego.idtick = store.state.list.my_ticker.id_tick[0];
      var gos = Object.assign(
        store.state.post.chosego,
        store.state.post.data_post
      );
      store.state.post.data.push(gos);
    }
    store.dispatch("post/post_einvoice");
  };

  const nextpayment = () => {
    if (store.state.list.my_ticker.id_tick.length == 1) {
      if (store.state.list.my_ticker.id_seat.length < 1) {
        store.state.hasNotifi = true;
      } else {
        aplyy();
        router.push({
          name: "payment",
          query: { id_tick1: store.state.list.my_ticker.id_tick[0], step: 3 },
        });
      }
    } else {
      if (store.state.list.my_ticker.id_seat[0] == undefined) {
        store.state.hasNotifi = true;
      } else if (store.state.list.my_ticker.id_seat[1] == undefined) {
        store.state.hasNotifi = true;
      } else {
        aplyy();
        router.push({
          name: "payment",
          query: {
            id_tick1: store.state.list.my_ticker.id_tick[0],
            id_tick2: store.state.list.my_ticker.id_tick[1],
            step: 3,
          },
        });
      }
    }
  };
  return {
    next,
    nextpayment,
    checkinfo1,
    data,
    data2,
    checkinfo2,
  };
};
