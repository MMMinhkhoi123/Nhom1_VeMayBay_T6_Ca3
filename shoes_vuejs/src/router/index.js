import { createRouter, createWebHistory } from "vue-router";
const routes = [
  {
    path: "/",
    name: "home",
    component: import("@/views/Customer/HomeView.vue"),
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
    component: import("../views/Admin/AminD.vue"),
    children: [
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
