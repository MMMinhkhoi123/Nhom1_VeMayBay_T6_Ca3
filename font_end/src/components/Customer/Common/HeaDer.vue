<template>
  <div class="header">
    <div class="logo">
      <a @click="home"
        >airMax <strong class="hin">Pro</strong>
        <font-awesome-icon class="icon" :icon="['far', 'copyright']" />
      </a>
    </div>
    <div class="acount">
      <button
        v-if="this.$store.state.post.authentication == null"
        @click="gotoscount"
        class="acount_link"
      >
        acount
      </button>
      <div
        v-if="this.$store.state.post.authentication != null"
        class="lauthencatid"
      >
        <div @click="this.$router.push({ name: 'history' })">
          <span><font-awesome-icon :icon="['fas', 'ticket']" /></span>
        </div>
        <div @click="this.$router.push({ name: 'profile' })">
          <span><font-awesome-icon :icon="['far', 'user']" /></span>
          {{ this.$store.state.post.authentication.fullname }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import router from "@/router";
import store from "@/store";
export default {
  setup() {
    const get = () => {
      router.push({ name: "list" });
    };
    const gotoscount = () => {
      router.push({ name: "login" });
    };
    const home = () => {
      store.state.list.name.from = null;
      store.state.list.name.to = null;
      store.state.list.name.hair = null;

      store.state.list.search.from = null;
      store.state.list.search.to = null;
      store.state.list.search.exits_chair = null;
      store.state.list.search.date = null;
      store.state.list.search.round_trip = null;
      router.push({ name: "list" });
    };
    return { get, gotoscount, home };
  },
};
</script>
<style scoped>
.icon {
  margin: 0 5px;
}
.left {
  display: flex;
  gap: 30px;
}
.menu {
  display: flex;
  border: 1px solid black;
}
.menu > div {
  padding: 20px 10px;
}
.header {
  padding: 20px;
  display: flex;
  justify-content: space-between;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 11;
  background: rgb(27, 27, 27);
}
.header > div {
  padding: 0 10px;
}
a {
  font-size: 30px;
  color: white !important;
}
.logo {
  font-weight: 800;
}
.hin {
  color: rgb(64, 31, 255);
}
.acount_link {
  padding: 10px 30px;
  border: none;
  font-weight: 900;
  margin-right: 10px;
}
.lauthencatid {
  display: flex;
  gap: 10px;
}
.lauthencatid > div {
  cursor: pointer;
  border-radius: 5px;
  display: flex;
  color: white;
  font-weight: 800;
  font-size: 20px;
  align-items: center;
  gap: 10px;
}
.lauthencatid > div > span {
  display: block;
  width: 40px;
  border-radius: 50%;
  height: 40px;
  background: rgb(100, 97, 97);
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
