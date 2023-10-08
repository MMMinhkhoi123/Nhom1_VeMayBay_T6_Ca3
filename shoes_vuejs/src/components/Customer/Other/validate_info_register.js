import store from "@/store";
import { reactive } from "vue";
import router from "@/router";
export default () => {
  const err = reactive({
    fullname: null,
    phone: null,
    email: null,
    pass: null,
  });
  const regexp_pasword = /^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{6,})\S$/;
  const regexp_fullname = /^[A-Za-z][A-Za-z0-9_]{7,29}$/;
  const regexp_email =
    /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  const regex_phone = /^[0-9\-+]{9,15}$/;
  const checkname = () => {
    if (!data.fullname) {
      err.fullname = "Vui lòng nhập họ và tên";
    } else {
      regexp_fullname.test(data.fullname) == false
        ? (err.fullname = "Tên không chứa ký tự đặt biệt")
        : null;
    }
  };
  const checkemail = () => {
    if (!data.gmail) {
      err.email = "Vui lòng nhập email";
    } else {
      regexp_email.test(data.gmail) == false
        ? (err.email = "email không hợp lệ")
        : null;
    }
  };
  const checkphone = () => {
    if (!data.phone) {
      err.phone = "Vui lòng nhập số điện thoại";
    } else {
      regex_phone.test(data.phone) == false
        ? (err.phone = "số điện thoại không hợp lệ")
        : null;
    }
  };
  const checkpassword = () => {
    if (!data.password) {
      err.pass = "Vui lòng nhập mật khẩu";
    } else {
      regexp_pasword.test(data.password) == false
        ? (err.pass = "Mật khẩu bao gồm chử in hoa, chử thường và số")
        : null;
    }
  };
  const setnull_err = () => {
    err.email = null;
    err.fullname = null;
    err.pass = null;
    err.phone = null;
  };
  const set_contant_null = () => {
    if (
      err.email != null ||
      err.fullname != null ||
      err.pass != null ||
      err.phone != null
    ) {
      return false;
    }
    return true;
  };
  const data = reactive({
    fullname: null,
    phone: null,
    gmail: null,
    password: null,
  });
  store.state.post.mess_regiter = null;
  const onsubmit = () => {
    setnull_err();
    checkname();
    checkemail();
    checkphone();
    checkpassword();
    console.log(set_contant_null());
    if (set_contant_null()) {
      store.state.post.mess_regiter = null;
      store.dispatch("post/register", data);
      setTimeout(() => {
        if (store.state.post.mess_regiter == null) {
          router.push({ name: "login" });
        }
      }, 200);
    } else {
      console.log("fail register");
    }
  };
  return {
    onsubmit,
    err,
    data,
  };
};
