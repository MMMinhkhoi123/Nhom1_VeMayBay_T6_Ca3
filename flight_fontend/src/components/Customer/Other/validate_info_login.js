// import router from "@/router";
import router from "@/router";
import store from "@/store";
import { reactive, ref } from "vue";
export default () => {
  const data = reactive({
    email: sessionStorage.getItem("email"),
    password: sessionStorage.getItem("password"),
  });
  const remenber = ref(false);
  const err = reactive({
    email: null,
    password: null,
  });
  const show = (event, value1, value2) => {
    console.log(value1);
    const input = document.getElementById(value1);
    const icon = document.getElementById(value2);
    if (input.getAttribute("type") == "text") {
      input.setAttribute("type", "password");
      icon.style.color = "gray";
    } else {
      input.setAttribute("type", "text");
      icon.style.color = "blue";
    }
  };
  const validate = () => {
    data.email == null ? (err.email = "Không bỏ trống") : (err.email = null);
    data.password == null
      ? (err.password = "Không bỏ trống")
      : (err.password = null);
  };
  const loading = ref(false);
  const onsubmit = () => {
    validate();
    if (err.email == null && err.password == null) {
      loading.value = true;
      store.state.post.mess_login = null;
      store.dispatch("post/login", data);
      const test = setInterval(() => {
        if (store.state.post.authen != null) {
          if (store.state.post.mess_login == null) {
            loading.value = false;
            if (remenber.value == true) {
              sessionStorage.setItem("email", data.email);
              sessionStorage.setItem("password", data.password);
            }
            if (!store.state.post.authen.name_role.includes("user")) {
              router.push({ name: "admin" });
            } else {
              router.push({ name: "list" });
            }
            clearInterval(test);
          }
        }
      }, 200);
    }
  };
  return {
    validate,
    data,
    err,
    show,
    onsubmit,
    remenber,
    loading,
  };
};
