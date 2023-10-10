<template>
  <basemenu></basemenu>
  <div class="container">
    <div class="main">
      <basenavbar></basenavbar>
      <div class="set_component">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import menu from "@/components/Admin/Menu_Chose.vue";
import navbar from "@/components/Admin/NarbarVue.vue";
import store from "@/store";
export default {
  components: {
    basemenu: menu,
    basenavbar: navbar,
  },
  setup() {
    if (localStorage.getItem("jwt") != undefined) {
      store.dispatch("post/authen");
      store.dispatch("list/gethistory");
    }
  },
};
</script>
<style scoped>
.container {
  height: 100vh;
  background: rgb(40, 41, 43);
  padding-left: 250px;
}
.content {
  background: rgb(117, 117, 230);
  padding: 40px;
  position: relative;
}
.select {
  display: flex;
  gap: 10px;
}
.select > div {
  padding: 20px 40px;
  background: white;
  border-radius: 10px;
  display: flex;
  gap: 10px;
}
.main {
  position: relative;
}
.set_component {
  padding-top: 80px;
}
</style>
