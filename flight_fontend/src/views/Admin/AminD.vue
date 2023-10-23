<template>
  <basemenu></basemenu>
  <div class="container">
    <div class="main">
      <basenavbar></basenavbar>
      <div class="set_component">
        <div v-if="this.$route.path == '/admin'" class="group_static">
          <staticcreate
            v-if="$store.state.post.authentication.role.includes('creater')"
          ></staticcreate>
        </div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
import creater_static from "@/views/Admin/Page/StaticCreater.vue";
import menu from "@/components/Admin/Menu_Chose.vue";
import navbar from "@/components/Admin/NarbarVue.vue";
import store from "@/store";
export default {
  components: {
    basemenu: menu,
    basenavbar: navbar,
    staticcreate: creater_static,
    // staticcounttrademark: static_trademark_count,
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
/* Hide scrollbar for Chrome, Safari and Opera */
.group_static::-webkit-scrollbar {
  display: none;
}

/* Hide scrollbar for IE, Edge and Firefox */
.group_static {
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}
.group_static {
  height: 600px;
  overflow-y: scroll;
}
.container {
  height: 100vh;
  background: rgb(255, 255, 255);
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
