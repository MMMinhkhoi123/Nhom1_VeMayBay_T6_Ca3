import { createApp } from "vue";
import { VueFire } from "vuefire";
import { firebaseApp } from "@/Filebase/index";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./assets/CSS/all.css";
import "@/icon";
/* import font awesome icon component */
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

createApp(App)
  .component("font-awesome-icon", FontAwesomeIcon)
  .use(store)
  .use(router)
  .use(VueFire, {
    firebaseApp,
  })
  .mount("#app");
