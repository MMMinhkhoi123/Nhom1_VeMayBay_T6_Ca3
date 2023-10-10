import { createRouter, createWebHistory } from "vue-router";
import home from "@/views/Customer/HomeView.vue";
import store from "@/store";
// import store from "@/store";
const routes = [
  {
    path: "/",
    name: "home",
    component: home,
    children: [
      {
        path: "history",
        name: "history",
        component: import("../views/Customer/HistoryTicketBuy.vue"),
      },
      {
        path: "list",
        name: "list",
        component: import("../views/Customer/HomeList.vue"),
      },
      {
        path: "search",
        name: "search",
        component: import("../views/Customer/FilterCard.vue"),
      },
      {
        path: "booking",
        name: "booking",
        component: import("../views/Customer/InfoOder.vue"),
      },
      {
        path: "payment",
        name: "payment",
        component: import("../views/Customer/PayMents.vue"),
      },
      {
        path: "profile",
        name: "profile",
        component: import("../views/Customer/ProFile.vue"),
      },
    ],
  },
  {
    path: "/acount",
    name: "acount",
    component: import("../views/Customer/AcountVue.vue"),
    children: [
      {
        path: "login",
        name: "login",
        component: import("../components/Acount/LoginCPN.vue"),
      },
      {
        path: "signip",
        name: "signup",
        component: import("../components/Acount/SignUp.vue"),
      },
      {
        path: "forget",
        name: "forget",
        component: import("../components/Acount/ForGetPass.vue"),
      },
    ],
  },
  {
    path: "/admin",
    name: "admin",
    meta: { requiresAuth: true },
    beforeEnter(to, from, next) {
      var x = 50;
      store.dispatch("post/authen");
      const y = setInterval(() => {
        if (store.state.post.authentication) {
          if (!store.state.post.authentication.role.includes("user")) {
            clearInterval(y);
            next();
          } else if (x == 0) {
            clearInterval(y);
            next({ name: "login" });
          }
        } else if (x == 0) {
          clearInterval(y);
          next({ name: "login" });
        }
        x = x - 10;
      }, x);
      console.log(from);
    },
    component: import("../views/Admin/AminD.vue"),
    children: [
      {
        path: "profiless",
        name: "profiless",
        component: import("../views/Admin/Page/ProFiless.vue"),
      },
      {
        path: "Dashboard",
        name: "dashboard",
        component: import("../views/Admin/Page/DashBoard.vue"),
      },
      {
        path: "airfield",
        name: "airfield",
        component: import("../views/Admin/Page/AirField.vue"),
      },
      {
        name: "trademark",
        path: "trademark",
        component: import("../views/Admin/Page/TradeMark.vue"),
      },
      {
        name: "location",
        path: "locations",
        component: import("../views/Admin/Page/LocaTion.vue"),
      },
      {
        name: "airplane",
        path: "airplane",
        component: import("../views/Admin/Page/AirPlane.vue"),
      },
      {
        name: "typechair",
        path: "typechair",
        component: import("../views/Admin/Page/TypeChair.vue"),
      },
      {
        path: "ticker",
        name: "ticker",
        component: import("../views/Admin/Page/TicKer.vue"),
      },
      {
        name: "flight",
        path: "flight",
        component: import("../views/Admin/Page/FlightV.vue"),
      },
      {
        name: "customer-ticker",
        path: "customer/:ticker",
        beforeEnter(to, from, next) {
          console.log(from);
          if (store.state.post.authentication.role.includes("checker")) {
            next();
          } else {
            return next({ name: "login" });
          }
        },
        component: import("../views/Admin/Page/CustoMer.vue"),
      },
      {
        name: "customer",
        path: "customer",
        beforeEnter(to, from, next) {
          console.log(from);
          if (store.state.post.authentication.role.includes("checker")) {
            next();
          } else {
            return next({ name: "login" });
          }
        },
        component: import("../views/Admin/Page/CustoMer.vue"),
      },
      {
        name: "customer-detail",
        path: "customers/:id",
        beforeEnter(to, from, next) {
          console.log(from);
          if (store.state.post.authentication.role.includes("checker")) {
            next();
          } else {
            return next({ name: "login" });
          }
        },
        component: import("../views/Admin/Page/Detail_Customer.vue"),
      },
    ],
  },
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
router.beforeEach(async (to, from) => {
  console.log(from);
  if (to.name == "acount") {
    return { name: "login" };
  }
});
export default router;
